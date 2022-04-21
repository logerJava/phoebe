package com.loger.phoebe.web.controller;

import com.loger.phoebe.common.vo.BasicResultVO;
import com.loger.phoebe.support.domain.MessageTemplate;
import com.loger.phoebe.web.service.MessageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.web.controller
 * @date 2022/4/21 14:04
 * @description:
 */
@RestController
@RequestMapping("/messageTemplate")
public class MessageTemplateController {

    @Autowired
    private MessageTemplateService messageTemplateService;

    @PostMapping("/save")
    public BasicResultVO saveOfUpdate(@RequestBody MessageTemplate messageTemplate){
        return BasicResultVO.success(messageTemplateService.saveOrUpdate(messageTemplate));
    }



}
