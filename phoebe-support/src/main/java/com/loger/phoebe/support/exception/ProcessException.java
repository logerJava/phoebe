package com.loger.phoebe.support.exception;

import com.loger.phoebe.common.enums.RespStatusEnum;
import com.loger.phoebe.support.pipeline.ProcessContext;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.support.exception
 * @date 2022/4/21 10:59
 * @description:
 */
public class ProcessException extends RuntimeException {

    /**
     * 流程处理上下文
     */
    private final ProcessContext processContext;

    public ProcessException(ProcessContext processContext) {
        super();
        this.processContext = processContext;
    }

    public ProcessException(ProcessContext processContext, Throwable cause) {
        super(cause);
        this.processContext = processContext;
    }

    @Override
    public String getMessage() {
        if (this.processContext != null) {
            return this.processContext.getResponse().getMsg();
        } else {
            return RespStatusEnum.CONTEXT_IS_NULL.getMsg();
        }
    }

    public ProcessContext getProcessContext() {
        return processContext;
    }


}
