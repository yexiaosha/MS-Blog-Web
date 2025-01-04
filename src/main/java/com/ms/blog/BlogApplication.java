package com.ms.blog;

import com.ms.blog.validator.FriendLinkValidator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author WangYuhang
 */
@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableAsync
@MapperScan(value = "com.ms.blog.dao")
public class BlogApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BlogApplication.class, args);
        FriendLinkValidator.setFriendLinkValidator(applicationContext);
    }

}
