package cn.notenextday.stcconfigclient;

import cn.notenextday.stcconfigclient.manage.PullConfigFileManage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@SpringBootTest
class StcconfigClientApplicationTests {

    @Resource
    private PullConfigFileManage pullConfigFileManage;

    @Test
    void pullFileTest() throws IOException, InterruptedException {
        pullConfigFileManage.init();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        countDownLatch.await();
    }
}
