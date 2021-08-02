package cn.notenextday.stcconfig.service;

import cn.notenextday.stcconfig.dao.EnvInfoDao;
import cn.notenextday.stcconfig.dto.entity.EnvInfoDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/7/29 16:04
 */
@Service
public class EnvInfoService {
    @Resource
    private EnvInfoDao envInfoDao;

    /**
     * 查询环境列表
     */
    public List<EnvInfoDO> selectAll(){
        return envInfoDao.selectAll();
    }

}
