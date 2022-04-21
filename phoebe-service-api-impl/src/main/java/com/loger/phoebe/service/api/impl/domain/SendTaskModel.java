package com.loger.phoebe.service.api.impl.domain;

import com.loger.phoebe.common.domain.TaskInfo;
import com.loger.phoebe.service.api.domain.MessageParam;
import com.loger.phoebe.support.pipeline.ProcessModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.service.api.impl.domain
 * @date 2022/4/20 15:41
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendTaskModel implements ProcessModel {

    /**
     * 消息模板Id
     */
    private Long messageTemplateId;

    /**
     * 请求参数
     */
    private List<MessageParam> messageParamList;

    /**
     * 发送任务的信息
     */
    private List<TaskInfo> taskInfo;


}
