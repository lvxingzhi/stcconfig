package cn.notenextday.stcconfigclient;

import cn.notenextday.stcconfigclient.manage.PullConfigFileManage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
class StcconfigClientApplicationTests {

    @Resource
    private PullConfigFileManage pullConfigFileManage;

    @Test
    void pullFileTest() throws IOException {
        pullConfigFileManage.pullConfigFile(1, 1);
    }
}
