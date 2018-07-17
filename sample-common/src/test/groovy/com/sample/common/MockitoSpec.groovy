package com.sample.common

import spock.lang.Specification

class MockitoSpec extends Specification {

    def "currentTimeMillisの戻り値が100Lであること"() {
        setup:
        def mockedSystem = GroovyMock(System)
        mockedSystem.currentTimeMillis() >> 100L

        expect:
        mockedSystem.currentTimeMillis() == 100L
    }
}
