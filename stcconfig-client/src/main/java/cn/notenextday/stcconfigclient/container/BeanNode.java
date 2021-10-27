package cn.notenextday.stcconfigclient.container;

import java.util.List;

/**
 * 动态对象容器节点
 *
 * @Author xingzhi.lv
 * @Version 2.1
 * @Date 2021/10/26 18:01
 */
public class BeanNode {
    private Object object;
    private List<FieldNode> fieldList;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public List<FieldNode> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<FieldNode> fieldList) {
        this.fieldList = fieldList;
    }
}
