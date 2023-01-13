package com.ms.blog;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class BlogApplicationTests {

    @Test
    void contextLoads() {
        for (int i = 0; i < 10; i++) {
            String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
            log.info(checkCode);
        }

    }

}
