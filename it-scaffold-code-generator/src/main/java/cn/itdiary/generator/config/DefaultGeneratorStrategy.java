package cn.itdiary.generator.config;

import com.baomidou.mybatisplus.generator.config.*;
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
     * 获取默认的数据源配置 -> 在此处可以设置通用的全局配置
     *
     * @return
     */
    public static DataSourceConfig.Builder getDefaultDatasourceConfig(String url, String username, String password) {
        DataSourceConfig.Builder dataSourceConfig = new DataSourceConfig.Builder(url, username, password);
        return dataSourceConfig;
    }

    /**
     * 获取默认的数据源配置 -> 在此处可以设置通用的全局配置
     *
     * @return
     */
    public static GlobalConfig.Builder getDefaultGlobalConfig() {
        GlobalConfig.Builder globalConfig = new GlobalConfig.Builder();
        return globalConfig;
    }


    /**
     * 获取默认的包配置 -> 在此处可以设置通用的包全局配置
     *
     * @return
     */
    public static PackageConfig.Builder getDefaultPackageConfig() {
        PackageConfig.Builder packageConfig = new PackageConfig.Builder();
        return packageConfig;
    }

    /**
     * 获取默认的策略配置 -> 在此处可以设置通用的包全局配置
     *
     * @return
     */
    public static StrategyConfig.Builder getDefaultStrategyConfig() {
        StrategyConfig.Builder strategyConfig = new StrategyConfig.Builder();
        // 默认覆盖之前生成的文件
        strategyConfig.entityBuilder().enableFileOverride();
        strategyConfig.mapperBuilder().enableFileOverride();
        strategyConfig.serviceBuilder().enableFileOverride();
        strategyConfig.controllerBuilder().enableFileOverride();
        return strategyConfig;
    }


    /**
     * 获取默认模板设置
     *
     * @return
     */
    public static TemplateConfig.Builder getDefaultTemplateConfig() {
        TemplateConfig.Builder templateConfig = new TemplateConfig.Builder();
        return templateConfig;
    }

}
