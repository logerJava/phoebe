package com.loger.phoebe.support.service.impl;

import cn.hutool.setting.dialect.Props;
import com.loger.phoebe.support.service.ConfigService;
import com.loger.phoebe.support.utils.NacosUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.support.service.impl
 * @date 2022/8/1 16:32
 * @description:
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    /**
     * 本地配置
     */
    private static final String PROPERTIES_PATH = "local.yml";
    private Props props = new Props(PROPERTIES_PATH, StandardCharsets.UTF_8);

    @Value("${phoebe.nacos.enabled}")
    private Boolean enableNacos;

    @Autowired
    private NacosUtils nacosUtils;

    @Override
    public String getProperty(String key, String defaultValue) {
        if (enableNacos) {
            return nacosUtils.getProperty(key, defaultValue);
        } else {
            return props.getProperty(key, defaultValue);
        }
    }
}
