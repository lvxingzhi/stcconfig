package cn.notenextday.democlient.service;

import cn.notenextday.stcconfigclient.annotations.StcconfigValue;
import org.springframework.stereotype.Component;

/**
 * @Author xingzhi.lv
 * @Version 2.1
 * @Date 2021/10/27 11:20
 */
@Component
public class DemoClientBean {

    @StcconfigValue(key = "name", fileName = "app2.properties")
    private String name;
    @StcconfigValue(key = "age", fileName = "app4.yaml")
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
