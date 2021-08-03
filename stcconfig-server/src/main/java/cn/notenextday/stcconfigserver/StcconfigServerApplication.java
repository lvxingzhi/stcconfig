package cn.notenextday.stcconfigserver;

import cn.notenextday.stcconfigserver.manage.config.StcconfigRegisterManage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class StcconfigServerApplication {


	public static void main(String[] args) {
		SpringApplication.run(StcconfigServerApplication.class, args);
	}

}
