package cn.notenextday.stcconfigclient.dto;

import java.util.List;

/**
 * 节点
 *
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/7/28 11:42
 */
public class NodeDTO {

    public NodeDTO() {
    }

    public NodeDTO(String path, String data, String version) {
        setPath(path);
        setData(data);
    }

    public NodeDTO(String path, Integer data, String version) {
        setPath(path);
        setData("" + data);
    }

    /**
     * 路径
     */
    private String path;
    /**
     * 数据(ID)
     */
    private String data;
    /**
     * 版本
     */
    private String version;
    /**
     * 孩子节点集合
     */
    private List<NodeDTO> childrenNodeList;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<NodeDTO> getChildrenNodeList() {
        return childrenNodeList;
    }

    public void setChildrenNodeList(List<NodeDTO> childrenNodeList) {
        this.childrenNodeList = childrenNodeList;
    }

    @Override
    public String toString() {
        return "NodeDTO{" +
                "path='" + path + '\'' +
                ", data='" + data + '\'' +
                ", version='" + version + '\'' +
                ", childrenNodeList=" + childrenNodeList +
                '}';
    }
}
