package com.loger.phoebe;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe
 * @date 2022/4/20 14:55
 * @description:
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.loger.phoebe.support.dao"})
@NacosPropertySource(dataId = "platform_config.yaml", autoRefreshed = true)
public class PhoebeApplication {

    @NacosInjected
    private NamingService namingService;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private Integer serverPort;


    public static void main(String[] args) {
        SpringApplication.run(PhoebeApplication.class, args);
    }

    /**
     * 服务注册到 Nacos
     * @throws NacosException
     */
    @PostConstruct
    public void registerService() throws NacosException {
        namingService.registerInstance(applicationName, "127.0.0.1", serverPort);
    }


}
