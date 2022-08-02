package com.loger.phoebe.support.service;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.support.service
 * @date 2022/8/1 16:30
 * @description:
 */
public interface ConfigService {

    /**
     * 读取配置
     * @param key
     * @param defaultValue
     * @return
     */
    String getProperty(String key, String defaultValue);

}
