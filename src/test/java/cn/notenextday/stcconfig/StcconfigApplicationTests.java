package cn.notenextday.stcconfig;

import cn.notenextday.stcconfig.dao.ConfigInfoDao;
import cn.notenextday.stcconfig.manage.StcconfigRegisterZkManage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class StcconfigApplicationTests {

    @Resource
    private StcconfigRegisterZkManage stcconfigRegisterZkManage;

    @Resource
    private ConfigInfoDao configInfoDao;

    @Test
    void daoTest() {
        configInfoDao.selectByPrimaryKey(1);
    }

    @Test
    void registerTest(){
        stcconfigRegisterZkManage.init();
    }


}
