package com.loger.phoebe.web.controller;

import com.alibaba.fastjson.JSON;
import com.loger.phoebe.common.enums.RespStatusEnum;
import com.loger.phoebe.common.vo.BasicResultVO;
import com.loger.phoebe.service.api.domain.MessageParam;
import com.loger.phoebe.service.api.domain.SendRequest;
import com.loger.phoebe.service.api.domain.SendResponse;
import com.loger.phoebe.service.api.enums.BusinessCode;
import com.loger.phoebe.service.api.service.SendService;
import com.loger.phoebe.support.domain.MessageTemplate;
import com.loger.phoebe.web.service.MessageTemplateService;
import com.loger.phoebe.web.vo.MessageTemplateParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.web.controller
 * @date 2022/4/21 14:04
 * @description:
 */
@RestController
@RequestMapping("/messageTemplate")
// @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
public class MessageTemplateController {

    @Autowired
    private MessageTemplateService messageTemplateService;

    @Autowired
    private SendService sendService;

    @PostMapping("/save")
    public BasicResultVO saveOfUpdate(@RequestBody MessageTemplate messageTemplate){
        return BasicResultVO.success(messageTemplateService.saveOrUpdate(messageTemplate));
    }

    /**
     * 测试发送接口
     */
    @PostMapping("test")
    public BasicResultVO test(@RequestBody MessageTemplateParam messageTemplateParam) {

        Map<String, String> variables = JSON.parseObject(messageTemplateParam.getMsgContent(), Map.class);
        MessageParam messageParam = MessageParam.builder().receiver(messageTemplateParam.getReceiver()).variables(variables).build();
        SendRequest sendRequest = SendRequest.builder().code(BusinessCode.COMMON_SEND.getCode()).messageTemplateId(messageTemplateParam.getId()).messageParam(messageParam).build();
        SendResponse response = sendService.send(sendRequest);
        if (response.getCode() != RespStatusEnum.SUCCESS.getCode()) {
            return BasicResultVO.fail(response.getMsg());
        }
        return BasicResultVO.success(response);
    }



}
