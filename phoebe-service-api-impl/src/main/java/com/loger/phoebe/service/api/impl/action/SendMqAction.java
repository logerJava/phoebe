package com.loger.phoebe.service.api.impl.action;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.base.Throwables;
import com.loger.phoebe.common.enums.RespStatusEnum;
import com.loger.phoebe.common.vo.BasicResultVO;
import com.loger.phoebe.service.api.impl.domain.SendTaskModel;
import com.loger.phoebe.support.pipeline.BusinessProcess;
import com.loger.phoebe.support.pipeline.ProcessContext;
import com.loger.phoebe.support.utils.RocketMQUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.service.api.impl.action
 * @date 2022/4/20 17:17
 * @description: 将消息发送到MQ
 */
@Slf4j
@Service
public class SendMqAction implements BusinessProcess<SendTaskModel> {

    @Autowired
    private RocketMQUtils rocketMQUtils;

    @Value("${phoebe.business.topicName}")
    private String topicName;

    @Override
    public void process(ProcessContext<SendTaskModel> context) {
        SendTaskModel sendTaskModel = context.getProcessModel();
        String message = JSON.toJSONString(sendTaskModel.getTaskInfo(), SerializerFeature.WriteClassName);

        try {
            rocketMQUtils.send(topicName, message);
        } catch (Exception e) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.SERVICE_ERROR));
            log.error("send RocketMQ fail! e:{},params:{}", Throwables.getStackTraceAsString(e)
                    , JSON.toJSONString(CollUtil.getFirst(sendTaskModel.getTaskInfo().listIterator())));
        }
    }
}
