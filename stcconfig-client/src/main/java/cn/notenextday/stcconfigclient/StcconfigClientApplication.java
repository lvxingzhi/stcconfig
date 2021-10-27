package cn.notenextday.stcconfigclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StcconfigClientApplication {
    private static final Logger logger = LoggerFactory.getLogger(StcconfigClientApplication.class);

    public static void main(String[] args) {
        logger.info("[stcconfig][client]启动");
        SpringApplication.run(StcconfigClientApplication.class, args);
        logger.info("[stcconfig][client]启动成功");
    }

}
