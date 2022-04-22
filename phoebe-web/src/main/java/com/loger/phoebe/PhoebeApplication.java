package com.loger.phoebe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe
 * @date 2022/4/20 14:55
 * @description:
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.loger.phoebe.support.dao"})
public class PhoebeApplication {
    public static void main(String[] args) {
        SpringApplication.run(PhoebeApplication.class, args);
    }
}
