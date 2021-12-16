package cn.notenextday.stcconfigclient.util;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件读取解析工具
 *
 * @Author xingzhi.lv
 * @Version 2.1
 * @Date 2021/12/15 15:56
 */
public class FileReadToTypeUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileReadToTypeUtil.class);

    /**
     * yaml文件解析
     *
     * @param filePath
     * @return
     */
    public static Map<String, Object> yaml2Map(String filePath) {
        try {
            YamlMapFactoryBean yaml = new YamlMapFactoryBean();
            yaml.setResources(new ClassPathResource(filePath));
            return yaml.getObject();
        } catch (Exception e) {
            logger.error("[stcconfig][client] yaml读取配置文件失败", e);
        }
        return new HashMap<>();
    }

    /**
     * properties文件解析
     *
     * @param filePath
     * @return
     */
    public static Map<String, Object> properties2Map(String filePath) {
        Map<String, Object> dataMap = new HashMap<>();
        try (
                FileInputStream fileInputStream = new FileInputStream(filePath);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        ) {
            String configKeyAndValue = "";
            while ((configKeyAndValue = bufferedReader.readLine()) != null) {
                logger.info("[stcconfig][client] properties读取配置: {}", configKeyAndValue);
                if (Strings.isEmpty(configKeyAndValue)) {
                    continue;
                }
                int index = configKeyAndValue.indexOf("=");
                if (index == -1) {
                    continue;
                }
                String key = configKeyAndValue.substring(0, index);
                String value = configKeyAndValue.substring(index + 1);
                dataMap.put(key, value);
            }
        } catch (Exception ex) {
            logger.error("[stcconfig][client] properties读取配置文件失败");
        }
        return dataMap;
    }

}
