package com.sample.domain.repository.system;

import com.sample.domain.dao.system.SessionDao;
import com.sample.domain.dao.system.StaffDao;
import com.sample.domain.dto.system.Session;
import com.sample.domain.dto.system.Staff;
import com.sample.domain.exception.AuthException;
import com.sample.domain.service.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.sample.domain.util.DomaUtils.createSelectOptions;

/**
 * 認証リポジトリ
 */
@Repository
public class AuthRepository extends BaseRepository {

    @Autowired
    StaffDao staffDao;

    @Autowired
    SessionDao sessionDao;

    /**
     * 認証
     *
     * @param email
     * @param password
     * @return
     */
    public Staff auth(String email, String password) {
        return staffDao.auth(email, password).orElseThrow(() -> new AuthException());
    }

    /**
     * セッション生成
     *
     * @param session
     * @return
     */
    public int createSession(Session session) {
        return sessionDao.insert(session);
    }

    /**
     * セッション更新
     *
     * @param session
     * @return
     */
    public int updateSession(Session session) {
        return sessionDao.update(session);
    }

    /**
     * セッション削除
     *
     * @param session
     * @return
     */
    public int deleteSession(Session session) {
        return sessionDao.delete(session);
    }

    /**
     * セッション検索
     *
     * @param session
     * @return
     */
    public Optional<Session> getSession(Session session) {
        return sessionDao.select(session);
    }

}
