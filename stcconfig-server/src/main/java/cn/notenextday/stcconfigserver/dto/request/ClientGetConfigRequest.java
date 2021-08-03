package cn.notenextday.stcconfigserver.dto.request;

/**
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/7/30 10:20
 */
public class ClientGetConfigRequest {

    /**
     * 文件路径
     */
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ClientGetConfigRequest{" +
                "path='" + path + '\'' +
                '}';
    }
}
