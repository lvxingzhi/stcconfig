package cn.notenextday.democlient;

import cn.notenextday.democlient.service.DemoClientBean;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

@SpringBootTest
class DemoClientApplicationTests {

    @Resource
    private DemoClientBean demoClientBean;

    @Test
    void configTest() throws InterruptedException {
        System.out.println("name:"+ demoClientBean.getName());
        System.out.println("age:"+ demoClientBean.getAge());
        CountDownLatch countDownLatch = new CountDownLatch(2);
        countDownLatch.await();
    }

}
