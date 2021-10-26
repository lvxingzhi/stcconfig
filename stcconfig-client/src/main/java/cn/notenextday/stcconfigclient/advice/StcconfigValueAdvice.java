package cn.notenextday.stcconfigclient.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.FieldSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * 注解拦截器,扩展属性内容从配置容器中获取
 *
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/8/6 14:20
 */
@Aspect
@Component
@Deprecated
public class StcconfigValueAdvice {
    Logger logger = LoggerFactory.getLogger(StcconfigValueAdvice.class);

    @Pointcut(value = "@annotation(cn.notenextday.stcconfigclient.annotations.StcconfigValue)")
    public void pointCut() {
    }

    @Around("@annotation(cn.notenextday.stcconfigclient.annotations.StcconfigValue)")
    public Object dealPoint(ProceedingJoinPoint point) throws Throwable {
        FieldSignature fieldSignature = (FieldSignature) point.getSignature();
        logger.info(String.valueOf(point.getTarget()));
        Field field = fieldSignature.getField();
        // 不支持的类型不生效
        if (field.getType() != String.class && field.getType() != Integer.class) {
            return point.proceed();
        }
        return null;
    }

    private Object getFieldValue(Object obj, Field field) {
        try {
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
        }
        return null;
    }

    private void setFieldValue(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
            return;
        } catch (Exception e) {
        }
        try {
            char[] tmp = field.getName().toCharArray();
            tmp[0] = Character.toUpperCase(tmp[0]);
            String setter = "set" + new String(tmp);
            obj.getClass().getMethod(setter).invoke(obj, value).toString();
            return;
        } catch (Exception e) {
        }
    }

}
