package com.sample.domain.dto.system;

import com.sample.domain.dto.common.DomaDtoImpl;
import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.*;
import lombok.val;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.sample.domain.Const.*;

@Table(name = "session")
@Entity
@Getter
@Setter
public class Session extends DomaDtoImpl {

    private static final long serialVersionUID = 4825745231712286767L;

    @Id
    @Column(name = "session_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //ユーザID
    @Column(name = "id")
    Long userId;

    //cookieId
    String cookie;

    //セッション情報
    String info;

    //有効期限
    LocalDateTime expires;

    public static Session createValidCondition(String sessionId) {
        val where = new Session();
        where.setCookie(sessionId);
        where.setExpires(LocalDateTime.now());
        return where;
    }

    public static Session createCondition(String sessionId) {
        val where = new Session();
        where.setCookie(sessionId);
        return where;
    }

    public static Session extendSession(Session session) {
        session.setExpires(LocalDateTime.now().plusMinutes(SESSION_EXPIRES));
        return session;
    }

    public static Session createNewSession(Long userId) {
        val session = new Session();
        session.setUserId(userId);
        session.setCookie(UUID.randomUUID().toString());
        session.setExpires(LocalDateTime.now().plusMinutes(SESSION_EXPIRES));
        return session;
    }
}
