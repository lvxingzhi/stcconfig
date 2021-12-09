package cn.notenextday.stcconfigserver.controller;

import cn.notenextday.stcconfigserver.constant.ControllerPathConstant;
import cn.notenextday.stcconfigserver.constant.HttpConstant;
import cn.notenextday.stcconfigserver.manage.controller.WebApiManage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 对WEB暴露接口
 *
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/12/06 10:06
 */
@Controller
@RequestMapping(ControllerPathConstant.WEBAPI)
public class WebApiController {
    private static final Logger logger = LoggerFactory.getLogger(WebApiController.class);
    @Resource
    private WebApiManage webApiManage;

    /**
     * 查询环境列表
     */
    @ResponseBody
    @RequestMapping(value = "/getEnvList", produces = HttpConstant.HTTP_CONTENT_TYPE_UTF8)
    public String getEnvList() {
        logger.info("查询环境列表");
        return webApiManage.getEnvList();
    }

}
