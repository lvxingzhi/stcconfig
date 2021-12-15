package cn.notenextday.stcconfigclient.enums;

import java.util.LinkedHashMap;

/**
 * 配置类型和后缀名
 *
 * @Author xingzhi.lv
 * @Version 2.1
 * @Date 2021/12/11 15:26
 */
public enum ConfigFileTypeEnum {
    PROPERTIES(1, "properties"),
    YAML(2, "yaml"),
    TXT(3, "txt");

    private Integer type;
    private String name;

    ConfigFileTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public static LinkedHashMap<Integer, String> getEnumMap() {
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
        for (ConfigFileTypeEnum c : ConfigFileTypeEnum.values()) {
            map.put(c.getType(), c.getName());
        }
        return map;
    }

    public static ConfigFileTypeEnum getEnumByCode(Integer type) {
        for (ConfigFileTypeEnum obj : ConfigFileTypeEnum.values()) {
            if (obj.getType().equals(type)) {
                return obj;
            }
        }
        return null;
    }

    public static ConfigFileTypeEnum getEnumByName(String name) {
        for (ConfigFileTypeEnum obj : ConfigFileTypeEnum.values()) {
            if (obj.getName().equals(name)) {
                return obj;
            }
        }
        return null;
    }

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }


}
