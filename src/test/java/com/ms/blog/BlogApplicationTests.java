package com.ms.blog;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class BlogApplicationTests {

    @Test
    void contextLoads() {
        String a = "123";
        Integer b = Integer.valueOf(a);
        log.info("{}",b);
    }

}
