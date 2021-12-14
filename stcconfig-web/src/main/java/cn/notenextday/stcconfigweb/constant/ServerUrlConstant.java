package cn.notenextday.stcconfigweb.constant;

/**
 * 服务地址
 *
 * @Author xingzhi.lv
 * @Version 2.1
 * @Date 2021/12/8 20:12
 */
public class ServerUrlConstant {
    // 服务前缀
    public static final String BASE_URL = "http://localhost:8100";
    // 环境
    public static final String SERVER_ENV_LIST = "/webapi/getEnvList";
    public static final String SERVER_ENV_INFO = "/webapi/getEnvInfo";
    public static final String SERVER_ADD_ENV = "/webapi/addEnv";
    public static final String SERVER_UPDATE_ENV = "/webapi/updateEnv";
    public static final String SERVER_DELETE_ENV = "/webapi/deleteEnv";
    // 配置文件
    public static final String SERVER_CONFIG_LIST = "/webapi/getConfigList";
    public static final String SERVER_CONFIG_INFO = "/webapi/getConfigInfo";
    public static final String SERVER_ADD_CONFIG = "/webapi/addConfig";
    public static final String SERVER_UPDATE_CONFIG = "/webapi/updateConfig";
    public static final String SERVER_DELETE_CONFIG = "/webapi/deleteConfig";
    // 项目
    public static final String SERVER_PROJECT_LIST = "/webapi/getProjectList";
    public static final String SERVER_PROJECT_INFO = "/webapi/getProjectInfo";
    public static final String SERVER_ADD_PROJECT = "/webapi/addProject";
    public static final String SERVER_UPDATE_PROJECT = "/webapi/updateProject";
    public static final String SERVER_DELETE_PROJECT = "/webapi/deleteProject";
}
