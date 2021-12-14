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
 * 环境
 *
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/7/30 10:06
 * https://www.jianshu.com/p/4b7641399b5f
 */
@Controller
@RequestMapping("/env")
public class EnvController {
    private static final Logger logger = LoggerFactory.getLogger(EnvController.class);

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping(value = "/getEnvList", produces = "application/json;charset=utf-8")
    public Map<String, Object> getEnvList(@RequestBody JSONObject json) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(ServerUrlConstant.BASE_URL + ServerUrlConstant.SERVER_ENV_LIST, json, String.class);
        List<Map> envList = JSON.parseArray(response, Map.class);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count", envList.size());
        map.put("data", envList);
        return map;
    }

    /**
     * 详情
     */
    @ResponseBody
    @RequestMapping(value = "/getEnvInfo", produces = "application/json;charset=utf-8")
    public Map<String, Object> getEnvInfo(@RequestBody JSONObject json) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(ServerUrlConstant.BASE_URL + ServerUrlConstant.SERVER_ENV_INFO, json, String.class);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count", 1);
        map.put("data", response);
        return map;
    }

    /**
     * 新增
     */
    @ResponseBody
    @RequestMapping(value = "/add", produces = "application/json;charset=utf-8")
    public Map<String, Object> add(@RequestBody JSONObject json) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(ServerUrlConstant.BASE_URL + ServerUrlConstant.SERVER_ADD_ENV, json, String.class);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "成功");
        return map;
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping(value = "/update", produces = "application/json;charset=utf-8")
    public Map<String, Object> update(@RequestBody JSONObject json) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(ServerUrlConstant.BASE_URL + ServerUrlConstant.SERVER_UPDATE_ENV, json, String.class);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "成功");
        return map;
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping(value = "/delete", produces = "application/json;charset=utf-8")
    public Map<String, Object> delete(@RequestBody JSONObject json) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(ServerUrlConstant.BASE_URL + ServerUrlConstant.SERVER_DELETE_ENV, json, String.class);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "成功");
        return map;
    }
}
