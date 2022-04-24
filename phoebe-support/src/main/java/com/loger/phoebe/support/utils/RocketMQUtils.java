package com.loger.phoebe.support.utils;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.support.utils
 * @date 2022/4/24 10:18
 * @description:
 */
@Component
public class RocketMQUtils {


    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送 RocketMQ 消息
     * @param topicName
     * @param jsonMessage
     */
    public void send(String topicName, String jsonMessage){
        rocketMQTemplate.convertAndSend(topicName, jsonMessage);
    }


}
