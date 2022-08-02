package com.loger.phoebe.support.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.loger.phoebe.common.constant.PhoebeConstant;
import com.loger.phoebe.support.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.support.utils
 * @date 2022/8/2 10:01
 * @description:
 */
@Component
public class AccountUtils {

    @Autowired
    private ConfigService configService;

    public <T> T getAccount(Integer sendAccount, String key, String prefix, Class<T> clazz){
        String accountValues = configService.getProperty(key, PhoebeConstant.NACOS_DEFAULT_VALUE_JSON_ARRAY);
        JSONArray jsonArray = JSON.parseArray(accountValues);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            T object = jsonObject.getObject(prefix + sendAccount, clazz);
            if (object != null) {
                return object;
            }
        }
        return null;
    }



}
