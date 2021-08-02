package cn.notenextday.stcconfig.controller;

import cn.notenextday.stcconfig.dto.request.BaseRequest;
import cn.notenextday.stcconfig.dto.request.ClientGetConfigRequest;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/7/30 10:06
 */
@Controller
@RequestMapping("/client")
public class ClientController {

    @ResponseBody
    @RequestMapping(value = "/getConfigFile", method = {RequestMethod.POST}, produces = "application/json;charset=utf-8")
    public String getConfigFile(@RequestBody BaseRequest baseRequest) {
        JSONObject.parseArray(baseRequest.getData(), ClientGetConfigRequest.class);
        return "";
    }
}
