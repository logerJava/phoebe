package com.loger.phoebe.handler.supplier.sms;

import com.loger.phoebe.handler.domain.sms.SmsParam;
import com.loger.phoebe.support.domain.SmsRecord;

import java.util.List;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.handler.supplier
 * @date 2022/7/26 16:12
 * @description:
 */
public interface SupplierHandler {

    /**
     * 发送短信
     * @param smsParam
     * @return 渠道商接口返回值
     */
    List<SmsRecord> send(SmsParam smsParam);

}
