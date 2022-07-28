package com.loger.phoebe.handler.domain.sms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.handler.domain.sms
 * @date 2022/7/26 16:15
 * @description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageTypeSmsConfig {

    /**
     * 权重(决定着流量的占比)
     */
    private Integer weights;

    /**
     * 运营商名称
     */
    private String supplierName;

}
