package com.loger.phoebe.common.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.common.dto.account
 * @date 2022/7/27 15:11
 * @description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NetEaseSmsAccount {

    /**
     * appkey
     */
    private String appkey;

    /**
     * appsecret
     */
    private String appsecret;

    /**
     * templateid
     */
    private String templateid;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 标识渠道商Id
     */
    private Integer supplierId;

    /**
     * 标识渠道商名字
     */
    private String supplierName;



}
