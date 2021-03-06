package cn.notenextday.stcconfigclient.aware;

import cn.notenextday.stcconfigclient.annotations.StcconfigValue;
import cn.notenextday.stcconfigclient.container.BeanContainer;
import cn.notenextday.stcconfigclient.container.BeanNode;
import cn.notenextday.stcconfigclient.container.FieldNode;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 初始化扫描StcconfigValue注解配置属性到容器中管理
 *
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/8/6 16:27
 */
@Component
public class StcconfigValueAware implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        List<FieldNode> fieldList = new ArrayList<>();
        for (Field field : fields) {
            if (field.getType() != String.class && field.getType() != Integer.class) {
                return bean;
            }
            Annotation[] annotations = field.getAnnotations();
            if (annotations.length == 0) {
                return bean;
            }
            for (Annotation annotation : annotations) {
                if (annotation instanceof StcconfigValue) {
                    FieldNode fieldNode = new FieldNode();
                    fieldNode.setFieldName(field.getName());
                    fieldNode.setAnnokeyName(((StcconfigValue) annotation).key());
                    fieldNode.setAnnoFileName(((StcconfigValue) annotation).fileName());
                    fieldList.add(fieldNode);
                }
            }
        }
        if (fieldList.size() > 0) {
            // bean注册到动态对象容器中<beanName : (Object, <List<field>>)>
            BeanNode beanNode = new BeanNode();
            beanNode.setObject(bean);
            beanNode.setFieldList(fieldList);
            BeanContainer.container().getDataMap().put(beanName, beanNode);
        }
        return bean;
    }

}
