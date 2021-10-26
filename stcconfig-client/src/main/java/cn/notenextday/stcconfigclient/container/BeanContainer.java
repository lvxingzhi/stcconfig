package cn.notenextday.stcconfigclient.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态对象容器
 *
 * @Author xingzhi.lv
 * @Version 2.1
 * @Date 2021/10/26 13:20
 */
public class BeanContainer {

    private BeanContainer() {
    }

    private static volatile BeanContainer beanContainer = new BeanContainer();
    /**
     * key: 配置文件名
     * value: Bean节点(bean对象, 配置属性集合)
     */
    private volatile Map<String, BeanNode> dataMap = new HashMap<>();

    /**
     * 获取容器
     */
    public static BeanContainer container() {
        return BeanContainer.beanContainer;
    }

    public Map<String, BeanNode> getDataMap() {
        return dataMap;
    }

}
