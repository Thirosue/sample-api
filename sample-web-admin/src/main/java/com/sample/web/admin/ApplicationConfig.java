package com.sample.web.admin;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import com.sample.web.base.BaseApplicationConfig;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableCaching // JCacheを有効可する
@EnableSwagger2
public class ApplicationConfig extends BaseApplicationConfig {

}
