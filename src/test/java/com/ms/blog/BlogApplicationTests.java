package com.ms.blog;

import com.ms.blog.service.CronJobService;
import java.util.Date;
import javax.annotation.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class BlogApplicationTests {

    @Resource
    CronJobService cronJobService;

    @Test
    void contextLoads() {
        A a = new A();
        a.id=1;
        a.name="aaa";
        B b=new B();
        b.id=2;
        b.name="b";
        b.nikeName="xxx";

        C c = new C();
        c.date=new Date();
        c.name="c";
        c.id=3;

        A ac = new A();
        B d = new B();
        BeanUtils.copyProperties(c,d);
        log.info(d.toString());
    }

    @Test
    void test(){
      cronJobService.updatePopularTag();
    }

}

@Data
class A{
    Integer id;
    String name;
}

@Data
class B{
    Integer id;
    String name;
    String nikeName;
}

@Data
class C{
    Integer id;
    String name;
    Date date;

}
