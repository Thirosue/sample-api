package com.sample.domain.dao

import com.sample.domain.dao.system.StaffDao
import com.sample.domain.base.AbstractTest
import org.spockframework.util.Assert
import org.springframework.beans.factory.annotation.Autowired

class StaffDaoTest extends AbstractTest {

    @Autowired
    StaffDao staffDao

    def setup() {
        dataSetupByFile("sql/StaffDaoTest_1.sql")
    }

    def "ID/PSが合致しない場合、担当者が取得できないこと"() {
        expect:
        def result = staffDao.auth("test@sample.com", "aaa")
        result == Optional.empty()
    }

    def "ID/PSが合致する場合、担当者が取得できること"() {
        expect:
        def result = staffDao.auth("test@sample.com", "passw0rd")
        Assert.notNull(result.get().email)
    }
}
