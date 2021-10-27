package cn.notenextday.stcconfigserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StcconfigServerApplication {
    private static final Logger logger = LoggerFactory.getLogger(StcconfigServerApplication.class);

    public static void main(String[] args) {
        logger.info("[stcconfig][server]启动");
        SpringApplication.run(StcconfigServerApplication.class, args);
        logger.info("[stcconfig][server]启动成功");
    }

}
