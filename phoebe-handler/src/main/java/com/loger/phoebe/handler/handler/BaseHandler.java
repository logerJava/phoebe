package com.loger.phoebe.handler.handler;

import com.loger.phoebe.common.domain.TaskInfo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.handler.handler
 * @date 2022/4/26 13:20
 * @description:
 */
public abstract class BaseHandler implements Handler {

    /**
     * 标识渠道的 Code
     * 子类初始化时指定
     */
    protected Integer channelCode;

    @Autowired
    private HandlerHolder handlerHolder;


    /**
     * 初始化渠道同 handler 的映射关系
     */
    @PostConstruct
    private void init(){
        handlerHolder.putHandler(channelCode, this);
    }

    @Override
    public void doHandler(TaskInfo taskInfo) {
        // TODO: 流量控制
        handler(taskInfo);
    }

    /**
     * 统一处理 handler 接口
     * @param taskInfo
     * @return
     */
    public abstract boolean handler(TaskInfo taskInfo);



}
