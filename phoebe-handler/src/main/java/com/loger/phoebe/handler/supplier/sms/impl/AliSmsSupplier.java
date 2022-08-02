package com.loger.phoebe.handler.supplier.sms.impl;

import com.alibaba.fastjson.JSON;
import com.loger.phoebe.handler.domain.sms.SmsParam;
import com.loger.phoebe.handler.supplier.sms.BaseSupplierHandler;
import com.loger.phoebe.handler.supplier.sms.SupplierHandler;
import com.loger.phoebe.support.domain.SmsRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.handler.supplier.sms.impl
 * @date 2022/7/28 10:47
 * @description:
 */
@Slf4j
@Component
public class AliSmsSupplier extends BaseSupplierHandler implements SupplierHandler {

    @Autowired
    private RestTemplate restTemplate;


    public AliSmsSupplier() {
        supplierName = "AliSmsSupplier";
    }

    @Override
    public List<SmsRecord> send(SmsParam smsParam) {
        // 模拟发送失败
        log.error("AliSmsSupplier#send fail:{},params:{}", "阿里短信发送失败", JSON.toJSONString(smsParam));
        return null;
    }
}
