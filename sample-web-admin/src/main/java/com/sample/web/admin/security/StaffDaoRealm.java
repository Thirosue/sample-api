package com.sample.web.admin.security;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sample.domain.dao.system.StaffDao;
import com.sample.domain.dao.system.StaffRoleDao;
import com.sample.domain.dto.system.Staff;
import com.sample.domain.dto.system.StaffRole;
import com.sample.web.base.security.BaseRealm;

import lombok.extern.slf4j.Slf4j;

/**
 * 管理側 認証認可
 */
@Component
@Slf4j
public class StaffDaoRealm extends BaseRealm {

    @Autowired
    StaffDao staffDao;

    @Autowired
    StaffRoleDao staffRoleDao;

    @Override
    protected UserDetails getLoginUser(String email) {
        Staff staff = null;
        List<GrantedAuthority> authorityList = null;

        try {
            // login_idをメールアドレスと見立てる
            Staff where = new Staff();
            where.setEmail(email);

            // 担当者を取得して、セッションに保存する
            staff = staffDao.select(where)
                    .orElseThrow(() -> new UsernameNotFoundException("no staff found [id=" + email + "]"));

            // 担当者権限を取得する
            List<StaffRole> staffRoles = staffRoleDao.selectByStaffId(staff.getId(), toList());

            // 役割キーにプレフィックスをつけてまとめる
            Set<String> roleKeys = staffRoles.stream().map(StaffRole::getRoleKey).collect(toSet());

            // 権限キーをまとめる
            Set<String> permissionKeys = staffRoles.stream().map(StaffRole::getPermissionKey).collect(toSet());

            // 役割と権限を両方ともGrantedAuthorityとして渡す
            Set<String> authorities = new HashSet<>();
            authorities.addAll(roleKeys);
            authorities.addAll(permissionKeys);
            authorityList = AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]));

            return new LoginStaff(staff, authorityList);

        } catch (Exception e) {
            if (!(e instanceof UsernameNotFoundException)) {
                // 入力間違い以外の例外はログ出力する
                log.error("failed to getLoginUser. ", e);
                throw e;
            }

            // 0件例外がスローされた場合は何もしない
            // それ以外の例外は、認証エラーの例外で包む
            throw new UsernameNotFoundException("could not select staff.", e);
        }
    }
}
