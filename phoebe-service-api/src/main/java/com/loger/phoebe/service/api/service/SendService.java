package com.loger.phoebe.service.api.service;

import com.loger.phoebe.service.api.domain.BatchSendRequest;
import com.loger.phoebe.service.api.domain.SendRequest;
import com.loger.phoebe.service.api.domain.SendResponse;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.service.api.service
 * @date 2022/4/20 15:18
 * @description:
 */
public interface SendService {

    /**
     * 单文案发送接口
     * @param sendRequest
     * @return
     */
    SendResponse send(SendRequest sendRequest);

    /**
     * 多文案发送接口
     * @param batchSendRequest
     * @return
     */
    SendResponse batchSend(BatchSendRequest batchSendRequest);

}
