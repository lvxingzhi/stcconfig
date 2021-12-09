package cn.notenextday.stcconfigserver.controller;

import cn.notenextday.stcconfigserver.constant.ControllerPathConstant;
import cn.notenextday.stcconfigserver.constant.HttpConstant;
import cn.notenextday.stcconfigserver.dto.ConfigDTO;
import cn.notenextday.stcconfigserver.dto.entity.ConfigInfoDO;
import cn.notenextday.stcconfigserver.manage.controller.ClientManage;
import cn.notenextday.stcconfigserver.service.ConfigInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 对客户端暴露接口
 *
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
     * 配置下载
     */
    @GetMapping(value = "/getConfigFile", produces = HttpConstant.HTTP_CONTENT_TYPE_UTF8)
    public ResponseEntity<Byte[]> getConfigFile(@RequestParam String path) {
        ConfigDTO configDTO = clientManage.splitForConfigDTO(path);
        ConfigInfoDO configInfoDO = configInfoService.findById(configDTO.getConfigId());
        try {
            // 查询配置信息
            HttpHeaders headers = new HttpHeaders();
            String fileName = URLEncoder.encode(configInfoDO.getConfigFileName(), StandardCharsets.UTF_8.name());
            headers.add(HttpHeaders.CONTENT_DISPOSITION, FILE_CONTENT_CONSTANT + fileName);
            return new ResponseEntity(configInfoDO.getConfigFileContent().getBytes(StandardCharsets.UTF_8), headers, HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {
            logger.error("[controller] 下载配置文件失败", e);
        }
        return null;
    }

}
