package cn.notenextday.stcconfigserver.dto;

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
        // do nothing
    }

    public NodeDTO(String path, String data, Integer version) {
        setPath(path);
        setData(data);
        setVersion(version);
    }

    public NodeDTO(String path, Integer data, Integer version) {
        setPath(path);
        setData("" + data);
        setVersion(version);
    }

    public NodeDTO(String path, Integer data, Integer version, String fileName) {
        setPath(path);
        setData("" + data);
        setFileName(fileName);
        setVersion(version);
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
    private Integer version;
    /**
     * 文件名
     */
    private String fileName;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public List<NodeDTO> getChildrenNodeList() {
        return childrenNodeList;
    }

    public void setChildrenNodeList(List<NodeDTO> childrenNodeList) {
        this.childrenNodeList = childrenNodeList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "NodeDTO{" +
                "path='" + path + '\'' +
                ", data='" + data + '\'' +
                ", version='" + version + '\'' +
                ", fileName='" + fileName + '\'' +
                ", childrenNodeList=" + childrenNodeList +
                '}';
    }
}
