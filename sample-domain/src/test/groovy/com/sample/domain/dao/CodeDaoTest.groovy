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

    def "無効フラグONを検索条件に指定した場合、selectAllの結果が絞り込まれること"() {
        when:
        def where = new Code()
        where.isInvalid = true
        def options = SelectOptions.get().offset(0).limit(10)
        def codeList = codeDao.selectAll(where, options, toList())

        then:
        codeList.size() == 0
    }

    def "無効フラグOFFを検索条件に指定した場合、selectAllの結果が絞り込まれること"() {
        when:
        def where = new Code()
        where.isInvalid = false
        def options = SelectOptions.get().offset(0).limit(10)
        def codeList = codeDao.selectAll(where, options, toList())

        then:
        0 < codeList.size()
    }

    def "コードエイリアスを検索条件に指定した場合、selectAllの結果が絞り込まれること"() {
        when:
        def where = new Code()
        where.codeAlias = 'male'
        def options = SelectOptions.get().offset(0).limit(10)
        def codeList = codeDao.selectAll(where, options, toList())

        then:
        codeList.size() == 1
    }

    def "コード値を検索条件に指定した場合、selectAllの結果が絞り込まれること"() {
        when:
        def where = new Code()
        where.codeValue = '福岡県'
        def options = SelectOptions.get().offset(0).limit(10)
        def codeList = codeDao.selectAll(where, options, toList())

        then:
        codeList.size() == 1
    }

    def "コード分類を検索条件に指定した場合、selectAllの結果が絞り込まれること"() {
        when:
        def where = new Code()
        where.categoryKey = 'GNR0001'
        def options = SelectOptions.get().offset(0).limit(10)
        def codeList = codeDao.selectAll(where, options, toList())

        then:
        codeList.size() == 2
    }
}
