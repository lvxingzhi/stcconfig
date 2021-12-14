package cn.notenextday.stcconfigserver.controller;

import cn.notenextday.stcconfigserver.constant.ControllerPathConstant;
import cn.notenextday.stcconfigserver.constant.HttpConstant;
import cn.notenextday.stcconfigserver.dto.entity.EnvInfoDO;
import cn.notenextday.stcconfigserver.manage.controller.WebApiManage;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 查询环境详情
     */
    @ResponseBody
    @RequestMapping(value = "/getEnvInfo", produces = HttpConstant.HTTP_CONTENT_TYPE_UTF8)
    public String getEnvInfo(@RequestBody JSONObject json) {
        logger.info("查询环境详情");
        return webApiManage.getEnvInfo(json);
    }

    /**
     * 添加环境
     */
    @ResponseBody
    @RequestMapping(value = "/addEnv", produces = HttpConstant.HTTP_CONTENT_TYPE_UTF8)
    public String addEnv(@RequestBody JSONObject json) {
        logger.info("添加环境");
        webApiManage.addEnv(json);
        return "成功";
    }

    /**
     * 修改环境
     */
    @ResponseBody
    @RequestMapping(value = "/updateEnv", produces = HttpConstant.HTTP_CONTENT_TYPE_UTF8)
    public String updateEnv(@RequestBody JSONObject json) {
        logger.info("修改环境");
        webApiManage.updateEnv(json);
        return "成功";
    }

    /**
     * 删除环境
     */
    @ResponseBody
    @RequestMapping(value = "/deleteEnv", produces = HttpConstant.HTTP_CONTENT_TYPE_UTF8)
    public String deleteEnv(@RequestBody JSONObject json) {
        logger.info("删除环境");
        webApiManage.deleteEnv(json);
        return "成功";
    }

    /**
     * 查询配置文件列表
     */
    @ResponseBody
    @RequestMapping(value = "/getConfigList", produces = HttpConstant.HTTP_CONTENT_TYPE_UTF8)
    public String getConfigList(@RequestBody JSONObject json) {
        logger.info("查询环境列表");
        return webApiManage.getConfigList(json);
    }

    /**
     * 查询配置文件详情
     */
    @ResponseBody
    @RequestMapping(value = "/getConfigInfo", produces = HttpConstant.HTTP_CONTENT_TYPE_UTF8)
    public String getConfigInfo(@RequestBody JSONObject json) {
        logger.info("查询环境详情");
        return webApiManage.getConfigInfo(json);
    }

    /**
     * 添加配置
     */
    @ResponseBody
    @RequestMapping(value = "/addConfig", produces = HttpConstant.HTTP_CONTENT_TYPE_UTF8)
    public String addConfig(@RequestBody JSONObject json) {
        logger.info("添加配置");
        webApiManage.addConfig(json);
        return "成功";
    }

    /**
     * 修改配置
     */
    @ResponseBody
    @RequestMapping(value = "/updateConfig", produces = HttpConstant.HTTP_CONTENT_TYPE_UTF8)
    public String updateConfig(@RequestBody JSONObject json) {
        logger.info("修改配置");
        webApiManage.updateConfig(json);
        return "成功";
    }

    /**
     * 删除配置
     */
    @ResponseBody
    @RequestMapping(value = "/deleteConfig", produces = HttpConstant.HTTP_CONTENT_TYPE_UTF8)
    public String deleteConfig(@RequestBody JSONObject json) {
        logger.info("删除配置");
        webApiManage.deleteConfig(json);
        return "成功";
    }

    /**
     * 查询项目列表
     */
    @ResponseBody
    @RequestMapping(value = "/getProjectList", produces = HttpConstant.HTTP_CONTENT_TYPE_UTF8)
    public String getProjectList(@RequestBody JSONObject json) {
        logger.info("查询项目列表");
        return webApiManage.getProjectList(json);
    }

    /**
     * 查询项目详情
     */
    @ResponseBody
    @RequestMapping(value = "/getProjectInfo", produces = HttpConstant.HTTP_CONTENT_TYPE_UTF8)
    public String getProjectInfo(@RequestBody JSONObject json) {
        logger.info("查询项目详情");
        return webApiManage.getProjectInfo(json);
    }

    /**
     * 添加项目
     */
    @ResponseBody
    @RequestMapping(value = "/addProject", produces = HttpConstant.HTTP_CONTENT_TYPE_UTF8)
    public String addProject(@RequestBody JSONObject json) {
        logger.info("添加项目");
        webApiManage.addProject(json);
        return "成功";
    }

    /**
     * 修改项目
     */
    @ResponseBody
    @RequestMapping(value = "/updateProject", produces = HttpConstant.HTTP_CONTENT_TYPE_UTF8)
    public String updateProject(@RequestBody JSONObject json) {
        logger.info("修改项目");
        webApiManage.updateProject(json);
        return "成功";
    }

    /**
     * 删除项目
     */
    @ResponseBody
    @RequestMapping(value = "/deleteProject", produces = HttpConstant.HTTP_CONTENT_TYPE_UTF8)
    public String deleteProject(@RequestBody JSONObject json) {
        logger.info("添加项目");
        webApiManage.deleteProject(json);
        return "成功";
    }


}
