package cn.diary.test;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class ProjectGenerator {
    public static void main(String[] args) {
        // 测试自动生成代码
        FastAutoGenerator.create(
                        "jdbc:mysql://localhost:3306/testitem?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC",
                        "admin",
                        "123456"
                )
                // 全局配置信息，如代码的生成者，目录等
                .globalConfig(builder -> {
                    builder.author("it-learning-diary")
                            // 代码生成路径
                            .outputDir("D://codeGenerator")
                            // 启动swagger
                            .enableSwagger();
                })
                // 设置包相关配置信息，如包名，生成路径
                .packageConfig(builder -> {
                    builder.parent("com.baomidou.mybatisplus.samples.generator") // 设置父包名
                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://codeGenerator")); // 设置mapperXml生成路径
                })
                // 设置生成策略信息
                .strategyConfig(builder -> {
                    // 设置需要生成的表名，多个时用逗号隔开
                    builder.addInclude("demo");
                    // 设置过滤表前缀
                    // .addTablePrefix("t_", "c_");
                })
                // 指定使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

}
