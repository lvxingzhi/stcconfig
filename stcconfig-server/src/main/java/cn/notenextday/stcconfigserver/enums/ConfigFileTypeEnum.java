package cn.notenextday.stcconfigserver.enums;

/**
 * 配置类型和后缀名
 *
 * @Author xingzhi.lv
 * @Version 2.1
 * @Date 2021/12/11 15:26
 */
public enum ConfigFileTypeEnum {
    PROPERTIES(1, "properties"),
    XML(2, "xml"),
    YAML(3, "yaml"),
    TXT(4, "txt");

    private Integer type;
    private String name;

    ConfigFileTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

}
