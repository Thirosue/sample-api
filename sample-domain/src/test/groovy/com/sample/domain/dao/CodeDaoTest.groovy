package com.sample.domain.dao

import com.sample.domain.base.AbstractTest
import com.sample.domain.dao.system.CodeDao
import com.sample.domain.dto.system.Code
import org.seasar.doma.jdbc.SelectOptions
import org.spockframework.util.Assert
import org.springframework.beans.factory.annotation.Autowired

import static java.util.stream.Collectors.toList

class CodeDaoTest extends AbstractTest {

    @Autowired
    CodeDao codeDao

    def "selectAllの結果がnullではないこと"() {
        expect:
        def where = new Code()
        def options = SelectOptions.get().offset(0).limit(10)
        def codeList = codeDao.selectAll(where, options, toList())
        Assert.notNull(codeList)
    }
}
