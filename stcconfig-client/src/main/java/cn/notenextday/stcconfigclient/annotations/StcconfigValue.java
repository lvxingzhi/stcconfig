package cn.notenextday.stcconfigclient.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 配置注解
 *
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/8/6 14:07
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StcconfigValue {

    String fileName() default "";

    String key() default "";
}
