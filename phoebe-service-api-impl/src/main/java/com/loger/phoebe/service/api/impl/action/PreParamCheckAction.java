package com.loger.phoebe.service.api.impl.action;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.loger.phoebe.common.enums.RespStatusEnum;
import com.loger.phoebe.common.vo.BasicResultVO;
import com.loger.phoebe.service.api.domain.MessageParam;
import com.loger.phoebe.service.api.impl.domain.SendTaskModel;
import com.loger.phoebe.support.pipeline.BusinessProcess;
import com.loger.phoebe.support.pipeline.ProcessContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.service.api.impl.action
 * @date 2022/4/20 17:17
 * @description: 前置参数校验
 */
@Slf4j
@Service
public class PreParamCheckAction implements BusinessProcess<SendTaskModel> {

    @Override
    public void process(ProcessContext<SendTaskModel> context) {
        SendTaskModel sendTaskModel = context.getProcessModel();

        Long messageTemplateId = sendTaskModel.getMessageTemplateId();
        List<MessageParam> messageParamList = sendTaskModel.getMessageParamList();

        // 没有传入 消息模板Id 或者 messageParam
        if (messageTemplateId == null || CollUtil.isEmpty(messageParamList)) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));
            return;
        }

        // 过滤 receiver=null 的messageParam
        List<MessageParam> resultMessageParamList = messageParamList.stream()
                .filter(messageParam -> !StrUtil.isBlank(messageParam.getReceiver()))
                .collect(Collectors.toList());
        if (CollUtil.isEmpty(resultMessageParamList)) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));
            return;
        }

        sendTaskModel.setMessageParamList(resultMessageParamList);
    }
}
