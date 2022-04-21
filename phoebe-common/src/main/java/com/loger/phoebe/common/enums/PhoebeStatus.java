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
public enum PhoebeStatus {

    /**
     * 10.待审核 20.审核成功 30.被拒绝'
     */
    WAIT_AUDIT(10, "待审核"),
    AUDIT_SUCCESS(20, "审核成功"),
    AUDIT_REJECT(30, "被拒绝");

    private Integer code;
    private String description;

}
