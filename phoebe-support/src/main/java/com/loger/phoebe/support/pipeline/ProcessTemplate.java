package com.loger.phoebe.support.pipeline;

import lombok.Data;

import java.util.List;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.support.pipeline
 * @date 2022/4/20 17:16
 * @description: 业务执行模板
 */
@Data
public class ProcessTemplate {

    private List<BusinessProcess> processList;

}
