package cn.notenextday.stcconfigclient;

import cn.notenextday.stcconfigclient.manage.PullConfigFileManage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class StcconfigClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(StcconfigClientApplication.class, args);
    }

}
