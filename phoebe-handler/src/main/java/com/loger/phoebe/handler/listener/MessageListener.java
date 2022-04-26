package com.loger.phoebe.handler.listener;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.loger.phoebe.common.domain.TaskInfo;
import com.loger.phoebe.handler.handler.HandlerHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.handler.listener
 * @date 2022/4/26 10:18
 * @description:
 */
@Slf4j
@Component
@RocketMQMessageListener(
        consumerGroup = "${rocketmq.consumer.group}",
        topic = "${phoebe.business.topicName}",
        consumeMode = ConsumeMode.CONCURRENTLY
)
public class MessageListener implements RocketMQListener<String> {

    @Autowired
    private HandlerHolder handlerHolder;


    @Override
    public void onMessage(String messageStr) {
        if(StrUtil.isNotEmpty(messageStr)){

            List<TaskInfo> taskInfoList = JSON.parseArray(messageStr, TaskInfo.class);

            for (TaskInfo taskInfo : taskInfoList) {
                // TODO: 线程池处理
                handlerHolder.route(taskInfo.getSendChannel()).doHandler(taskInfo);
            }

        }

        log.info("msg : {}", messageStr);
    }

}
