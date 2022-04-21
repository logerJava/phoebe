package com.loger.phoebe.service.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.service.api.domain
 * @date 2022/4/20 15:18
 * @description:
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendRequest {

    /**
     * 执行业务类型(默认填写 "send")
     */
    private String code;

    /**
     * 消息模板id
     */
    private Long messageTemplateId;

    /**
     * 消息相关参数
     */
    private MessageParam messageParam;

}
