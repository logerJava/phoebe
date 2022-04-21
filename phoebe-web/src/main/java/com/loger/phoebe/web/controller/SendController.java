package com.loger.phoebe.web.controller;

import com.loger.phoebe.service.api.domain.SendRequest;
import com.loger.phoebe.service.api.domain.SendResponse;
import com.loger.phoebe.service.api.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.web.controller
 * @date 2022/4/21 11:10
 * @description:
 */
@RestController
public class SendController {

    @Autowired
    private SendService sendService;

    @PostMapping("/send")
    public SendResponse send(@RequestBody SendRequest sendRequest){
        return sendService.send(sendRequest);
    }


}
