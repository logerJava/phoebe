package com.loger.phoebe.handler.supplier.sms.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Throwables;
import com.loger.phoebe.common.constant.SendAccountConstant;
import com.loger.phoebe.common.dto.account.NetEaseSmsAccount;
import com.loger.phoebe.common.enums.SmsStatus;
import com.loger.phoebe.handler.domain.sms.SmsParam;
import com.loger.phoebe.handler.supplier.sms.BaseSupplierHandler;
import com.loger.phoebe.handler.supplier.sms.SupplierHandler;
import com.loger.phoebe.handler.utils.NetEaseUtils;
import com.loger.phoebe.support.domain.SmsRecord;
import com.loger.phoebe.support.utils.AccountUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.handler.supplier.sms.impl
 * @date 2022/7/26 16:25
 * @description:
 */
@Slf4j
@Component
public class NetEaseSmsSupplier extends BaseSupplierHandler implements SupplierHandler {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccountUtils accountUtils;

    public NetEaseSmsSupplier() {
        supplierName = "NetEaseSmsSupplier";
    }


    @Override
    public List<SmsRecord> send(SmsParam smsParam) {

        try {
            NetEaseSmsAccount netEaseSmsAccount = accountUtils.getAccount(
                    smsParam.getMsgType(),
                    SendAccountConstant.SMS.NETEASE_ACCOUNT_KEY,
                    SendAccountConstant.SMS.PREFIX,
                    NetEaseSmsAccount.class);
            HttpEntity<MultiValueMap<String, Object>> request = assembleRequest(smsParam, netEaseSmsAccount);
            JSONObject responseJson = restTemplate.postForObject(netEaseSmsAccount.getUrl(), request, JSONObject.class);
            return assembleSmsRecord(smsParam, responseJson.getLong("obj"), netEaseSmsAccount);
        } catch (Exception e) {
            log.error("NetEaseSmsSupplier#send fail:{},params:{}", Throwables.getStackTraceAsString(e), JSON.toJSONString(smsParam));
            return null;
        }
    }

    private List<SmsRecord> assembleSmsRecord(SmsParam smsParam, Long sendId, NetEaseSmsAccount netEaseSmsAccount) {
        List<SmsRecord> smsRecordList = new ArrayList<>();
        for (String phone : smsParam.getPhones()) {
            SmsRecord smsRecord = SmsRecord.builder()
                    .sendDate(Integer.valueOf(DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN)))
                    .messageTemplateId(smsParam.getMessageTemplateId())
                    .phone(Long.valueOf(phone.replace("'","")))
                    .supplierId(netEaseSmsAccount.getSupplierId())
                    .supplierName(netEaseSmsAccount.getSupplierName())
                    .msgContent(smsParam.getContent())
                    .seriesId(String.valueOf(sendId))
                    .chargingNum(1)
                    .status(SmsStatus.SEND_SUCCESS.getCode())
                    .reportContent("")
                    .created(Math.toIntExact(DateUtil.currentSeconds()))
                    .updated(Math.toIntExact(DateUtil.currentSeconds()))
                    .build();
            smsRecordList.add(smsRecord);
        }
        return smsRecordList;
    }


    /**
     * 组装
     * @param smsParam
     * @param netEaseSmsAccount
     * @return
     */
    private HttpEntity<MultiValueMap<String, Object>> assembleRequest(SmsParam smsParam, NetEaseSmsAccount netEaseSmsAccount) {

        HttpHeaders httpHeaders = NetEaseUtils.getNetEaseAuthHeaders(
                netEaseSmsAccount.getAppkey(),
                netEaseSmsAccount.getAppsecret(),
                "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, Object> postMap = new LinkedMultiValueMap<>();
        postMap.add("templateid", netEaseSmsAccount.getTemplateid());
        postMap.add("mobiles", smsParam.getPhones());
        postMap.add("params", JSON.parseArray(smsParam.getContent()));

        // 请求头, 请求体
        return new HttpEntity<>(postMap, httpHeaders);
    }


}
