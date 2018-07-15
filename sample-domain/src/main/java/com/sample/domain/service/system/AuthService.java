package com.sample.domain.service.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.common.util.CipherUtils;
import com.sample.domain.dto.common.SessionInfo;
import com.sample.domain.dto.system.Staff;
import com.sample.domain.exception.SessionExpireException;
import com.sample.domain.repository.system.AuthRepository;
import com.sample.domain.repository.system.StaffRepository;
import com.sample.domain.service.BaseTransactionalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import lombok.val;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static com.sample.domain.dto.system.Session.*;

/**
 * 認証サービス
 */
@Service
public class AuthService extends BaseTransactionalService {

    @Autowired
    AuthRepository authRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * 認証します
     *
     * @return
     */
    public SessionInfo auth(final String email, final String password) throws Exception {
        Assert.notNull(email, "email must not be null");
        Assert.notNull(password, "password must not be null");

        val staff = authRepository.auth(email, CipherUtils.encryptAES(password));
        val roles = staffRepository.findAllRole(staff.getId()).stream().map(role->role.getPermissionKey()).collect(Collectors.toList());

        //セッション生成
        val session = createNewSession(staff.getId());
        val info = modelMapper.map(staff, SessionInfo.class);
        info.setPermissionKeyList(roles);
        info.setCookie(session.getCookie());
        session.setInfo(objectMapper.writeValueAsString(info));
        authRepository.createSession(session);

        return info;
    }

    /**
     * セッション更新
     *
     * @return User
     */
    public SessionInfo put(final String sessionId) throws Exception {
        //セッションを取得する
        val session = authRepository.getSession(createValidCondition(sessionId)).orElseThrow(() -> new SessionExpireException());
        //セッション更新
        authRepository.updateSession(extendSession(session));
        return objectMapper.readValue(session.getInfo(), SessionInfo.class);
    }

    /**
     * セッション削除
     *
     * @return
     */
    public void delete(final String sessionId) { ;
        //セッションを取得する
        val session = authRepository.getSession(createCondition(sessionId)).orElse(null);
        if(session != null) {
            authRepository.deleteSession(session);
        }
    }
}
