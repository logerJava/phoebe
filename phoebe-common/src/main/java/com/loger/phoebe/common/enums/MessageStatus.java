package com.loger.phoebe.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.common.enums
 * @date 2022/4/21 13:43
 * @description:
 */
@Getter
@ToString
@AllArgsConstructor
public enum MessageStatus {

    /**
     * 10.新建 20.停用 30.启用 40.等待发送 50.发送中 60.发送成功 70.发送失败
     */
    INIT(10, "初始化状态"),
    STOP(20, "停用"),
    RUN(30, "启用"),
    PENDING(40, "等待发送"),
    SENDING(50, "发送中"),
    SEND_SUCCESS(60, "发送成功"),
    SEND_FAIL(70, "发送失败");

    private Integer code;
    private String description;

}
