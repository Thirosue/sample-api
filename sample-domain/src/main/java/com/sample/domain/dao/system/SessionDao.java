package com.sample.domain.dao.system;

import com.sample.domain.dto.system.Session;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.Optional;

@ConfigAutowireable
@Dao
public interface SessionDao {

    /**
     * セッションを1件取得します。
     *
     * @param session
     * @return
     */
    @Select
    Optional<Session> select(Session session);

    /**
     * セッションを登録します。
     *
     * @param session
     * @return
     */
    @Insert
    int insert(Session session);

    /**
     * セッションを更新します。
     *
     * @param session
     * @return
     */
    @Update
    int update(Session session);

    /**
     * セッションを削除します。
     *
     * @param session
     * @return
     */
    @Delete
    int delete(Session session);
}
