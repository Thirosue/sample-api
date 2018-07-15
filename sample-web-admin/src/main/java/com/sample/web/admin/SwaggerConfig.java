package com.sample.web.admin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger設定
 */
@Configuration
public class SwaggerConfig  {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api")
                .select()
                .paths(PathSelectors.regex("/api.*"))
                .build()
                .apiInfo(apiinfo());
    }

    private ApiInfo apiinfo() {
        return new ApiInfoBuilder()
                .title("REST API List")
                .description("REST APIの一覧です。")
                .version("1.0")
                .build();
    }
}