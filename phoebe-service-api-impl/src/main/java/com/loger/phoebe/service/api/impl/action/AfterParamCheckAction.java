package com.loger.phoebe.service.api.impl.action;

import com.loger.phoebe.service.api.impl.domain.SendTaskModel;
import com.loger.phoebe.support.pipeline.BusinessProcess;
import com.loger.phoebe.support.pipeline.ProcessContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.service.api.impl.action
 * @date 2022/4/20 17:17
 * @description: 后置参数检查
 */
@Slf4j
@Service
public class AfterParamCheckAction implements BusinessProcess<SendTaskModel> {


    @Override
    public void process(ProcessContext<SendTaskModel> context) {
        log.info("后置参数检查 ...");
    }


}
