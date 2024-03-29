package com.loger.phoebe.handler.handler.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Throwables;
import com.loger.phoebe.common.constant.PhoebeConstant;
import com.loger.phoebe.common.constant.SendAccountConstant;
import com.loger.phoebe.common.domain.TaskInfo;
import com.loger.phoebe.common.dto.model.SmsContentModel;
import com.loger.phoebe.common.enums.ChannelType;
import com.loger.phoebe.handler.domain.sms.MessageTypeSmsConfig;
import com.loger.phoebe.handler.domain.sms.SmsParam;
import com.loger.phoebe.handler.handler.BaseHandler;
import com.loger.phoebe.handler.handler.Handler;
import com.loger.phoebe.handler.supplier.sms.SupplierHolder;
import com.loger.phoebe.support.dao.SmsRecordDao;
import com.loger.phoebe.support.domain.SmsRecord;
import com.loger.phoebe.support.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.handler.handler.impl
 * @date 2022/4/26 13:27
 * @description:
 */
@Slf4j
@Component
public class SmsHandler extends BaseHandler implements Handler {

    @Autowired
    private SupplierHolder supplierHolder;
    @Autowired
    private SmsRecordDao smsRecordDao;
    @Autowired
    private ConfigService configService;

    public SmsHandler(){
        channelCode = ChannelType.SMS.getCode();
    }

    @Override
    public boolean handler(TaskInfo taskInfo) {
        SmsParam smsParam = SmsParam.builder()
                .phones(taskInfo.getReceiver())
                .content(getSmsContent(taskInfo))
                .messageTemplateId(taskInfo.getMessageTemplateId())
                .msgType(taskInfo.getMsgType())
                .build();
        try {
            // 动态流量负载
            String flowRatio = configService.getProperty(SendAccountConstant.SMS.WEIGHT_KEY, PhoebeConstant.NACOS_DEFAULT_VALUE_JSON_ARRAY);
            MessageTypeSmsConfig[] messageTypeSmsConfigs =
                    loadBalance(JSONObject.parseArray(flowRatio, MessageTypeSmsConfig.class));
            for (MessageTypeSmsConfig messageTypeSmsConfig : messageTypeSmsConfigs) {
                List<SmsRecord> recordList = supplierHolder.route(messageTypeSmsConfig.getSupplierName()).send(smsParam);
                if (CollUtil.isNotEmpty(recordList)) {
                    // 循环内放插入这种写法有问题, 不要这么写, 这里我懒得改了, 就是这么个意思 ...
                    for (SmsRecord smsRecord : recordList) {
                        smsRecordDao.insert(smsRecord);
                    }
                    return true;
                }
            }
            return true;
        }catch (Exception e){
            log.error("SmsHandler#handler fail:{},params:{}",
                    Throwables.getStackTraceAsString(e), JSON.toJSONString(smsParam));
        }

        return false;
    }


    /**
     * 流量负载
     * 根据权重优先顺序取出运营商, 其他作为备份
     * @param messageTypeSmsConfigs
     * @return
     */
    private static MessageTypeSmsConfig[] loadBalance(List<MessageTypeSmsConfig> messageTypeSmsConfigs) {

        int total = 0;
        for (MessageTypeSmsConfig channelConfig : messageTypeSmsConfigs) {
            total += channelConfig.getWeights();
        }

        // 生成一个随机数[1,total]，看落到哪个区间
        Random random = new Random();
        int index = random.nextInt(total) + 1;

        MessageTypeSmsConfig supplier;
        MessageTypeSmsConfig supplierBack;
        for (int i = 0; i < messageTypeSmsConfigs.size(); ++i) {
            if (index <= messageTypeSmsConfigs.get(i).getWeights()) {
                supplier = messageTypeSmsConfigs.get(i);

                // 取下一个供应商
                int j = (i + 1) % messageTypeSmsConfigs.size();
                if (i == j) {
                    return new MessageTypeSmsConfig[]{supplier};
                }
                supplierBack = messageTypeSmsConfigs.get(j);
                return new MessageTypeSmsConfig[]{supplier, supplierBack};
            }
            index -= messageTypeSmsConfigs.get(i).getWeights();
        }
        return null;
    }


    /**
     * 如果有输入链接，则把链接拼在文案后
     * <p>
     * PS: 这里可以考虑将链接 转 短链
     * PS: 如果是营销类的短信，需考虑拼接 回TD退订 之类的文案
     */
    private String getSmsContent(TaskInfo taskInfo) {
        SmsContentModel smsContentModel = (SmsContentModel) taskInfo.getContentModel();
        if (StrUtil.isNotBlank(smsContentModel.getUrl())) {
            return smsContentModel.getContent() + " " + smsContentModel.getUrl();
        } else {
            return smsContentModel.getContent();
        }
    }


}
