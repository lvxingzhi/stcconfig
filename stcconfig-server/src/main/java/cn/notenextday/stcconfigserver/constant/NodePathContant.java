package cn.notenextday.stcconfigserver.constant;

/**
 * 配置中心节点路径常量
 *
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/7/30 10:40
 */
public class NodePathContant {
    private NodePathContant() {
    }

    public static final String PATH_SUB = "/";
    /**
     * ROOT
     */
    public static final String ROOT_PATH = "/stcconfig";
    /**
     * /stcconfig/env
     */
    public static final String ENV_PATH = "/env";
    public static final String ENV_PATH_ALL = "/stcconfig/env";
    /**
     * /stcconfig/env/xxx/project
     */
    public static final String PROJECT_PATH = "/project";

}
