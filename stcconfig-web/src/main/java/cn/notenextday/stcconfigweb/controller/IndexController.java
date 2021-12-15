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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 主页
 *
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/7/30 10:06
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    /**
     * 菜单列表
     */
    @ResponseBody
    @RequestMapping(value = "/menuList", produces = "application/json;charset=utf-8")
    public Map<String, Object> menuList() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(ServerUrlConstant.BASE_URL + ServerUrlConstant.SERVER_ENV_LIST, null, String.class);
        List<Map> envList = JSON.parseArray(response, Map.class);
        Map<String, Object> homeInfo = new HashMap<>();
        homeInfo.put("title", "首页");
        homeInfo.put("href", "stcconfig/page/welcome.html?t=1");
        Map<String, Object> logoInfo = new HashMap<>();
        logoInfo.put("title", "STC CONFIG");
        logoInfo.put("image", "stcconfig/images/logo.jpg");
        logoInfo.put("href", "_self");
        List<Map<String, Object>> menuInfo = new ArrayList<>();
        List<Map<String, Object>> menuInfoChild = new ArrayList<>();
        Map<String, Object> menuInfoTab = new HashMap<>();
        menuInfo.add(menuInfoTab);
        menuInfoTab.put("title", "配置中心");
        menuInfoTab.put("icon", "fa fa-address-book");
        menuInfoTab.put("href", "");
        menuInfoTab.put("target", "_self");
        menuInfoTab.put("child", menuInfoChild);
        Map<String, Object> envMenu = new HashMap<>();
        Map<String, Object> projectMenu = new HashMap<>();
        Map<String, Object> confMenu = new HashMap<>();
        Map<String, Object> historyMenu = new HashMap<>();
        Map<String, Object> userMenu = new HashMap<>();
        Map<String, Object> serverMenu = new HashMap<>();
        menuInfoChild.add(envMenu);
        menuInfoChild.add(projectMenu);
        menuInfoChild.add(confMenu);
//        menuInfoChild.add(historyMenu);
//        menuInfoChild.add(userMenu);
//        menuInfoChild.add(serverMenu);
        envMenu.put("title", "环境管理");
        envMenu.put("href", "stcconfig/page/env.html");
        envMenu.put("icon", "fa fa-files-o");
        envMenu.put("target", "_self");

        projectMenu.put("title", "项目管理");
        projectMenu.put("href", "stcconfig/page/project.html");
        projectMenu.put("icon", "fa fa-file");
        projectMenu.put("target", "_self");

        confMenu.put("title", "配置管理");
        confMenu.put("href", "stcconfig/page/config.html");
        confMenu.put("icon", "fa fa-file-word-o");
        confMenu.put("target", "_self");

//        historyMenu.put("title", "历史记录");
//        historyMenu.put("href", "page/menu.html");
//        historyMenu.put("icon", "fa fa-history");
//        historyMenu.put("target", "_self");
//
//        userMenu.put("title", "用户管理");
//        userMenu.put("href", "page/setting.html");
//        userMenu.put("icon", "fa fa-users");
//        userMenu.put("target", "_self");
//
//        serverMenu.put("title", "服务监控");
//        serverMenu.put("href", "page/config.html");
//        serverMenu.put("icon", "fa fa-warning");
//        serverMenu.put("target", "_self");


        Map<String, Object> map = new HashMap<>();
        map.put("homeInfo", homeInfo);
        map.put("logoInfo", logoInfo);
        map.put("menuInfo", menuInfo);
        map.put("data", envList);
        return map;
    }

    /**
     * 测试列表
     */
    @ResponseBody
    @RequestMapping(value = "/list", produces = "application/json;charset=utf-8")
    public Map<String, Object> list() {
        List<Object> resultList = new ArrayList<>();
        Map<String, Object> object1 = new HashMap<>();
        object1.put("id", 1);
        object1.put("envName", "提莫");
        object1.put("createTime", "2021-09-09");
        object1.put("updateTime", "2021-10-09");
        object1.put("parentId", -1);
        Map<String, Object> object2 = new HashMap<>();
        object2.put("id", 1);
        object2.put("envName", "咕米");
        object2.put("createTime", "2021-09-09");
        object2.put("updateTime", "2021-10-09");
        object2.put("parentId", -1);
        resultList.add(object1);
        resultList.add(object2);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count", 2);
        map.put("data", resultList);
        return map;
    }
}
