package com.loger.phoebe.service.api.impl.service;

import com.loger.phoebe.common.vo.BasicResultVO;
import com.loger.phoebe.service.api.domain.BatchSendRequest;
import com.loger.phoebe.service.api.domain.SendRequest;
import com.loger.phoebe.service.api.domain.SendResponse;
import com.loger.phoebe.service.api.impl.domain.SendTaskModel;
import com.loger.phoebe.service.api.service.SendService;
import com.loger.phoebe.support.pipeline.ProcessContext;
import com.loger.phoebe.support.pipeline.ProcessController;
import com.loger.phoebe.support.pipeline.ProcessModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.service.api.impl.service
 * @date 2022/4/20 15:49
 * @description:
 */
@Service
public class SendServiceImpl implements SendService {

    @Autowired
    private ProcessController processController;

    @Override
    public SendResponse send(SendRequest sendRequest) {
        SendTaskModel sendTaskModel = SendTaskModel.builder()
                .messageTemplateId(sendRequest.getMessageTemplateId())
                .messageParamList(Collections.singletonList(sendRequest.getMessageParam()))
                .build();

        ProcessContext context = ProcessContext.builder()
                .code(sendRequest.getCode())
                .processModel(sendTaskModel)
                .needBreak(false)
                .response(BasicResultVO.success()).build();

        ProcessContext process = processController.process(context);

        return new SendResponse(process.getResponse().getStatus(), process.getResponse().getMsg());
    }

    @Override
    public SendResponse batchSend(BatchSendRequest batchSendRequest) {
        return null;
    }
}
