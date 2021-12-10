package cn.notenextday.stcconfigweb.controller;

import cn.notenextday.stcconfigweb.constant.ServerUrlConstant;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 配置文件
 *
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/7/30 10:06
 * https://www.jianshu.com/p/4b7641399b5f
 */
@Controller
@RequestMapping("/config")
public class ConfigController {
    private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

    /**
     * 配置文件列表
     */
    @ResponseBody
    @RequestMapping(value = "/getConfigList", produces = "application/json;charset=utf-8")
    public Map<String, Object> getConfigList(@RequestBody JSONObject json) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(ServerUrlConstant.BASE_URL + ServerUrlConstant.SERVER_CONFIG_LIST, json, String.class);
        List<Map> envList = JSON.parseArray(response, Map.class);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count", envList.size());
        map.put("data", envList);
        return map;
    }

    /**
     * 新增环境
     */
    @ResponseBody
    @RequestMapping(value = "/add", produces = "application/json;charset=utf-8")
    public Map<String, Object> add(@RequestBody JSONObject json) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(ServerUrlConstant.BASE_URL + ServerUrlConstant.SERVER_ADD_CONFIG, json, String.class);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "成功");
        return map;
    }
}
