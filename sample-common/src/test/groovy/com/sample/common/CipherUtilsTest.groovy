package com.sample.common

import com.sample.common.util.CipherUtils
import spock.lang.Specification

class CipherUtilsTest extends Specification {

    def password = "password"

    def "password生成"() {
        expect:
        System.out.println(CipherUtils.encryptAES(password))
    }

    def "password突合"() {
        expect:
        CipherUtils.decrypt("mcM3yWWT+8sre6MjlFUpww==", CipherUtils.secretKey) == password
    }
}
