package cn.notenextday.stcconfigweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * 控制层对外接口
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
    public List<String> list(String name) {
        System.out.println("NAME: " + name);
        List<String> resultList = new ArrayList<>();
        resultList.add("提莫");
        resultList.add("咕米");
        return resultList;
    }

}
