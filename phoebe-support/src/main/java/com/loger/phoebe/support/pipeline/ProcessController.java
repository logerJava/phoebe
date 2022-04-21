package com.loger.phoebe.support.pipeline;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.loger.phoebe.common.enums.RespStatusEnum;
import com.loger.phoebe.common.vo.BasicResultVO;
import com.loger.phoebe.support.exception.ProcessException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.support.pipeline
 * @date 2022/4/20 15:46
 * @description: 流程控制器
 */
@Slf4j
@Data
public class ProcessController {

    /**
     * 模板映射
     */
    private Map<String, ProcessTemplate> templateConfig = null;


    public ProcessContext process(ProcessContext context){
        /* 前置检查 */
        try {
            preCheck(context);
        }catch (ProcessException e){
            e.getProcessContext();
        }

        /* 遍历流程 */
        List<BusinessProcess> processList = templateConfig.get(context.getCode()).getProcessList();
        for (BusinessProcess businessProcess : processList) {
            businessProcess.process(context);
            if(context.getNeedBreak()){
                break;
            }
        }
        return context;
    }

    /**
     * 执行前置检查
     * @param context
     * @throws ProcessException
     */
    private void preCheck(ProcessContext context) throws ProcessException {
        // 上下文
        if (context == null) {
            context = new ProcessContext();
            context.setResponse(BasicResultVO.fail(RespStatusEnum.CONTEXT_IS_NULL));
            throw new ProcessException(context);
        }

        // 业务代码
        String businessCode = context.getCode();
        if (StrUtil.isBlank(businessCode)) {
            context.setResponse(BasicResultVO.fail(RespStatusEnum.BUSINESS_CODE_IS_NULL));
            throw new ProcessException(context);
        }

        // 执行模板
        ProcessTemplate processTemplate = templateConfig.get(businessCode);
        if (processTemplate == null) {
            context.setResponse(BasicResultVO.fail(RespStatusEnum.PROCESS_TEMPLATE_IS_NULL));
            throw new ProcessException(context);
        }

        // 执行模板列表
        List<BusinessProcess> processList = processTemplate.getProcessList();
        if (CollUtil.isEmpty(processList)) {
            context.setResponse(BasicResultVO.fail(RespStatusEnum.PROCESS_LIST_IS_NULL));
            throw new ProcessException(context);
        }

    }


}
