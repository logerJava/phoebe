package com.loger.phoebe.support.pipeline;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.support.pipeline
 * @date 2022/4/20 16:12
 * @description:
 */
public interface BusinessProcess<T extends ProcessModel> {

    /**
     * 真正处理逻辑
     * @param context
     */
    void process(ProcessContext<T> context);
}
