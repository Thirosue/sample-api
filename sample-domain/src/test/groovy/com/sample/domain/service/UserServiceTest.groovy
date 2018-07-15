package com.sample.domain.service

import com.sample.domain.base.AbstractTest
import com.sample.domain.dto.common.Pageable
import com.sample.domain.dto.user.User
import com.sample.domain.service.users.UserService
import org.springframework.beans.factory.annotation.Autowired

class UserServiceTest extends AbstractTest {

    @Autowired
    UserService userService

    def "存在しないメールアドレスで絞り込んだ場合、0件が返ること"() {
        when:
        def where = new User()
        where.setEmail("aaaa")

        def pages = userService.findAll(where, Pageable.DEFAULT_PAGEABLE)

        then:
        pages.getCount() == 0
    }
}
