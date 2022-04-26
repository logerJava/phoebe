package com.loger.phoebe.handler.handler;

import com.loger.phoebe.common.domain.TaskInfo;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.handler.handler
 * @date 2022/4/26 13:19
 * @description:
 */
public interface Handler {

    /**
     * 处理器
     * @param taskInfo
     */
    void doHandler(TaskInfo taskInfo);

}
