package cn.notenextday.stcconfig.controller;

import cn.notenextday.stcconfig.constant.ControllerPathConstant;
import cn.notenextday.stcconfig.constant.HttpConstant;
import cn.notenextday.stcconfig.dto.ConfigDTO;
import cn.notenextday.stcconfig.dto.entity.ConfigInfoDO;
import cn.notenextday.stcconfig.dto.request.BaseRequest;
import cn.notenextday.stcconfig.dto.request.ClientGetConfigRequest;
import cn.notenextday.stcconfig.manage.controller.ClientManage;
import cn.notenextday.stcconfig.service.ConfigInfoService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/7/30 10:06
 */
@Controller
@RequestMapping(ControllerPathConstant.CLIENT)
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private static final String FILE_CONTENT_CONSTANT = "attchement;filename=";
    @Resource
    private ClientManage clientManage;
    @Resource
    private ConfigInfoService configInfoService;

    /**
     * @param baseRequest
     * @return see ConfigReadController
     */
    @ResponseBody
    @RequestMapping(value = "/getConfigFile", method = {RequestMethod.POST}, produces = HttpConstant.HTTP_CONTENT_TYPE_UTF8)
    public ResponseEntity<Byte[]> getConfigFile(@RequestBody BaseRequest baseRequest) {
        ClientGetConfigRequest request = JSONObject.parseObject(baseRequest.getData(), ClientGetConfigRequest.class);
        ConfigDTO configDTO = clientManage.splitForConfigDTO(request.getPath());
        ConfigInfoDO configInfoDO = configInfoService.findById(configDTO.getConfigId());
        // 查询配置信息
        HttpHeaders headers = new HttpHeaders();
        try {
            String fileName = URLEncoder.encode(configInfoDO.getConfigFileName(), StandardCharsets.UTF_8.name());
            headers.add(HttpHeaders.CONTENT_DISPOSITION, FILE_CONTENT_CONSTANT + fileName);
            return new ResponseEntity(configInfoDO.getConfigFileContent().getBytes(StandardCharsets.UTF_8), headers, HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {
            logger.error("[controller] 下载配置文件失败,{}", e);
        }
        return null;
    }

}
