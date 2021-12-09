package cn.notenextday.stcconfigweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
