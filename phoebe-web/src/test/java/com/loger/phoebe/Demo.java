package com.loger.phoebe;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Random;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe
 * @date 2022/7/26 14:39
 * @description:
 */
public class Demo {

    public static void main(String[] args) {
        MessageTypeSmsConfig[] messageTypeSmsConfigs = loadBalance(getMessageTypeSmsConfig(30));
        System.out.println(JSONObject.toJSONString(messageTypeSmsConfigs));
    }

    private static MessageTypeSmsConfig[] loadBalance(List<MessageTypeSmsConfig> messageTypeSmsConfigs) {

        int total = 0;
        for (MessageTypeSmsConfig channelConfig : messageTypeSmsConfigs) {
            total += channelConfig.getWeights();
        }

        // 生成一个随机数[1,total]，看落到哪个区间
        Random random = new Random();
        int index = random.nextInt(total) + 1;

        MessageTypeSmsConfig supplier = null;
        MessageTypeSmsConfig supplierBack = null;
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


    private static List<MessageTypeSmsConfig> getMessageTypeSmsConfig(Integer msgType) {

        String apolloKey = "msgTypeSmsConfig";
        String messagePrefix = "message_type_";

        String property = "[{\"message_type_10\":[{\"weights\":80,\"scriptName\":\"TencentSmsScript\"},{\"weights\":20,\"scriptName\":\"YunPianSmsScript\"}]},{\"message_type_20\":[{\"weights\":20,\"scriptName\":\"YunPianSmsScript\"}]},{\"message_type_30\":[{\"weights\":20,\"scriptName\":\"TencentSmsScript\"}]},{\"message_type_40\":[{\"weights\":20,\"scriptName\":\"TencentSmsScript\"}]}]";
        JSONArray jsonArray = JSON.parseArray(property);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONArray array = jsonArray.getJSONObject(i).getJSONArray(messagePrefix + msgType);
            if (CollUtil.isNotEmpty(array)) {
                List<MessageTypeSmsConfig> result = JSON.parseArray(JSON.toJSONString(array), MessageTypeSmsConfig.class);
                return result;
            }
        }
        return null;
    }

}
