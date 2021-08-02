package cn.notenextday.stcconfig.enums;

/**
 * ZK路径级别
 *
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/8/2 11:17
 */
public enum RootPathType {

    STCCONFIG(1, "stcconfig"),
    ENV(2, "env"),
    PROJECT(3, "project"),
    CONFIG(4, "config");

    private Integer level;
    private String pathName;

    RootPathType(Integer level, String pathName) {
        this.level = level;
        this.pathName = pathName;
    }
}
