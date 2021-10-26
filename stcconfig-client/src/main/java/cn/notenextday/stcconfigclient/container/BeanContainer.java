package cn.notenextday.stcconfigclient.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态对象容器
 *
 * @Author xingzhi.lv
 * @Version 2.1
 * @Date 2021/10/26 13:20
 */
public class BeanContainer {

    private BeanContainer() {
    }

    private static volatile BeanContainer beanContainer = new BeanContainer();

    private volatile Map<String, BeanNode> dataMap = new HashMap<>();

    /**
     * 获取容器
     */
    public static BeanContainer container() {
        return BeanContainer.beanContainer;
    }

    class BeanNode {
        private Object object;
        private List<String> fieldList;

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }

        public List<String> getFieldList() {
            return fieldList;
        }

        public void setFieldList(List<String> fieldList) {
            this.fieldList = fieldList;
        }
    }

    public Map<String, BeanNode> getDataMap() {
        return dataMap;
    }

}
