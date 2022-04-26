package com.loger.phoebe.handler.handler.impl;

import com.loger.phoebe.common.domain.TaskInfo;
import com.loger.phoebe.common.enums.ChannelType;
import com.loger.phoebe.handler.handler.BaseHandler;
import com.loger.phoebe.handler.handler.Handler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.handler.handler.impl
 * @date 2022/4/26 13:25
 * @description:
 */
@Slf4j
@Component
public class EmailHandler extends BaseHandler implements Handler {


    public EmailHandler(){
        channelCode = ChannelType.EMAIL.getCode();
    }


    @Override
    public boolean handler(TaskInfo taskInfo) {
        log.info("发送 emial : {}", taskInfo);
        return false;
    }


}
