package cn.notenextday.stcconfigserver.constant;

/**
 * HTTP常量
 *
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/8/2 19:53
 */
public class HttpConstant {
    private HttpConstant(){}
    public static final String HTTP_CONTENT_TYPE_UTF8 = "application/json;charset=utf-8";
    public static final String URL_PREFIX = "http://";
    public static final String URL_MIDDEL = ":";
    public static final String URL_SUB = "/";
    public static final String DOWN_FILE_URL = "/client/getConfigFile?path=";

    public static String getUrl(String url, String port, String zkConfigNodePath) {
        return URL_PREFIX + url + URL_MIDDEL + port + DOWN_FILE_URL + zkConfigNodePath;
    }
}
