package com.sample.domain.dao

import com.sample.domain.base.AbstractTest
import com.sample.domain.dao.system.SessionDao
import com.sample.domain.dto.system.Session
import org.spockframework.util.Assert
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDateTime

class SessionDaoTest extends AbstractTest {

    @Autowired
    SessionDao sessionDao

    def setup() {
        dataSetupByFile("sql/SessionDaoTest_1.sql")
    }

    def "有効期間内のセッションが取得できること"() {
        when:
        def where = new Session()
        where.cookie = "uuid_1"
        where.expires = LocalDateTime.now()

        Optional<Session> result = sessionDao.select(where)

        then:
        Assert.notNull(result.get().cookie)
    }

    def "セッションタイムアウトの場合、emptyが返ること"() {
        when:
        def where = new Session()
        where.cookie = "uuid_2"
        where.expires = LocalDateTime.now()

        Optional<Session> result = sessionDao.select(where)

        then:
        result == Optional.empty()
    }
}
