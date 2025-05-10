package com.wtwlc.web_experiment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 指定要扫描的controller包路径
                .apis(RequestHandlerSelectors.basePackage("com.wtwlc.web_experiment.controller"))
                // 排除包含"error"的路径
                .paths(PathSelectors.regex("^(?!.*error).*$"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Web Experiment API")
                .description("Web Experiment项目接口文档")
                .version("1.0")
                .build();
    }
}


// @EnableSwagger2
//3.0版本后不用再加入@Enableopenapi @Enableswagger2这两个注解，
//public class SwaggerConfig {
//}