package com.loger.phoebe.handler.domain.sms;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.handler.domain.sms
 * @date 2022/7/26 16:16
 * @description:
 */
@Data
@Builder
public class SmsParam {

    /**
     * 业务Id
     */
    private Long messageTemplateId;

    /**
     * 10.通知类消息 20.营销类消息 30.验证码类消息
     */
    private Integer msgType;

    /**
     * 需要发送的手机号
     */
    private Set<String> phones;

    /**
     * 发送文案
     */
    private String content;
}
