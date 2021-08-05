package cn.notenextday.stcconfigserver;

import cn.notenextday.stcconfigserver.manage.config.StcconfigRegisterManage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;

@ComponentScan(basePackages="cn.notenextday.stcconfigserver")
@SpringBootTest
class StcconfigServerApplicationTests {

    @Resource
    private StcconfigRegisterManage stcconfigRegisterManage;

    @Test
    void contextLoads() {
    }

    @Test
    void initTest() {
        stcconfigRegisterManage.init();
    }

}
