package cn.itdiary.generator.config;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import lombok.Setter;

/**
 * @Author it-learning-diary
 * @Description 默认代码生成策略配置类
 * @Date 2023/1/23 20:56
 * @Version 1.0
 */
@Setter
public class DefaultGeneratorStrategy {

    /***
     * 数据源配置
     */
    private DataSourceConfig.Builder datasourceConfig;

    /**
     * 全局配置(生成代码保存的等位置信息)
     */
    private GlobalConfig.Builder globalConfig;

    /**
     * 生成包相关配置
     */
    private static PackageConfig.Builder packageConfig;

    /**
     * 匹配数据源和自动生成代码的相关策略配置
     */
    private static StrategyConfig.Builder strategyConfig;

    /**
     * 模板相关配置
     */
    private static AbstractTemplateEngine templateEngine;

    /**
     * 类加载时默认初始化
     */
    static {
    }

    public DefaultGeneratorStrategy(DataSourceConfig.Builder datasourceConfig) {
    }
}
