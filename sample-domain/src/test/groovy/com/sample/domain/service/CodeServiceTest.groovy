package com.sample.domain.service

import com.sample.domain.base.AbstractTest
import com.sample.domain.dto.common.Pageable
import com.sample.domain.dto.system.Code
import com.sample.domain.service.system.CodeService
import org.springframework.beans.factory.annotation.Autowired

class CodeServiceTest extends AbstractTest {

    @Autowired
    CodeService codeService

    def "getCodeの結果がnullではないこと"() {
        when:
        def where = new Code()
        def pages = codeService.findAll(where, Pageable.NO_LIMIT_PAGEABLE)

        then:
        pages.getCount() >= 1
    }
}
