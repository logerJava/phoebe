package com.loger.phoebe.support.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.support.utils
 * @date 2022/4/21 13:14
 * @description:
 */
public class CodeGenerateUtils {

    static String url = "jdbc:mysql://127.0.0.1:3306/phoebe";
    static String userName = "root";
    static String passWord = "123456";


    public static void main(String[] args) {

        FastAutoGenerator.create(url, userName, passWord)
                .globalConfig(builder -> {
                    builder.author("loger") // 设置作者
                            // .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://mybatisplus-generate"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.loger.phoebe.support.domain") // 设置父包名
                            .moduleName("phoebe-support") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapper, "D://mybatisplus-generate")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("message_template"); // 设置需要生成的表名
                            // .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                // .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }


}
