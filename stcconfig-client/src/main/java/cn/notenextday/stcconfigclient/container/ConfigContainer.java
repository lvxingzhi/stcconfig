package cn.notenextday.stcconfigclient.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置容器[管理配置]
 *
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/8/5 14:24
 */
public class ConfigContainer {

    private ConfigContainer() {
    }

    private static volatile ConfigContainer configContainer = new ConfigContainer();

    private volatile Map<String, List<String>> dataMap = new HashMap<>();

    /**
     * 获取容器
     */
    public static ConfigContainer container() {
        return configContainer;
    }

    public Map<String, List<String>> getDataMap() {
        return dataMap;
    }

}
