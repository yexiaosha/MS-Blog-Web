package com.ms.blog;

import com.ms.blog.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class BlogApplicationTests {

    @Test
    void contextLoads() {
        log.info(Md5Util.encodePassword("12345678"));//25d55ad283aa400af464c76d713c07ad
    }

}
