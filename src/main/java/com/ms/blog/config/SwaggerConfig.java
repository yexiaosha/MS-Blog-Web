package com.ms.blog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 接口文档组件配置
 * @author wyh
 * @date 2023/01/05 18:11
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI docket(){
        return new OpenAPI().info(new Info()
                .title("Mine Space Land")
                .description("论坛系统前后端分离API测试")
                .version("v1")
        );

    }
}
