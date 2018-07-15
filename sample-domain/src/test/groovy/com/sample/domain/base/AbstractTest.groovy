package com.sample.domain.base

import org.apache.commons.io.FileUtils
import org.apache.commons.lang3.StringUtils
import org.seasar.doma.jdbc.Config
import org.seasar.doma.jdbc.builder.UpdateBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

@Transactional // default:テスト後にロールバックする
@SpringBootTest(webEnvironment = NONE)
abstract class AbstractTest extends Specification {

    @Autowired
    Config config

    def dataSetupByFile(target) {
        def codeSql = getClass().getClassLoader().getResource(target)
        def codeSqlFile = new File(codeSql.toURI())

        FileUtils.readLines(codeSqlFile, "UTF8")
                .stream()
                .map{ line -> line.trim() }
                .filter{ line -> StringUtils.isNotBlank(line) && !StringUtils.startsWith(line, "--") }
                .forEach{ line -> UpdateBuilder.newInstance(config).sql(line).execute().finalize() }
    }
}
