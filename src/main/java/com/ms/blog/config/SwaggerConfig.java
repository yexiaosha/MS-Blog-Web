package com.ms.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 接口文档组件配置
 * @author wyh
 * @date 2023/01/05 18:11
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30)
                .enable(true)
                .groupName("GearStudio")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ms.blog.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
