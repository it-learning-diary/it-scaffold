package cn.itdiary.generator.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.itdiary.generator.config.DefaultGeneratorStrategy;
import cn.itdiary.generator.model.dto.*;
import cn.itdiary.generator.model.dto.generator.project.ProjectGeneratorDTO;
import cn.itdiary.generator.model.vo.BaseVo;
import cn.itdiary.generator.service.CodeGeneratorService;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

/**
 * @Description: 自动生成代码逻辑处理
 * @Author: it-learning-diary
 * @Version: v1
 * @Date: 2023/2/14:14:06
 */
@Slf4j(topic = "CodeGeneratorServiceImpl ")
@Service
public class CodeGeneratorServiceImpl implements CodeGeneratorService {

    /**
     * 根据参数自动生成代码
     *
     * @param param
     * @return
     */
    @Override
    public BaseVo<Void> generateCodeByParam(CodeGeneratorDTO param) {
        DataSourceConfigDTO dataSourceConfigDto = param.getDataSourceConfigDto();
        GlobalConfigDTO globalConfigDto = param.getGlobalConfigDto();
        PackageConfigDTO packageConfigDto = param.getPackageConfigDto();
        StrategyConfigDTO strategyConfigDto = param.getStrategyConfigDto();
        // 获取默认配置策略
        DataSourceConfig.Builder defaultDatasourceConfig = DefaultGeneratorStrategy.getDefaultDatasourceConfig(dataSourceConfigDto.getUrl(), dataSourceConfigDto.getUsername(), dataSourceConfigDto.getPassword());
        GlobalConfig.Builder defaultGlobalConfig = DefaultGeneratorStrategy.getDefaultGlobalConfig();
        PackageConfig.Builder defaultPackageConfig = DefaultGeneratorStrategy.getDefaultPackageConfig();
        StrategyConfig.Builder defaultStrategyConfig = DefaultGeneratorStrategy.getDefaultStrategyConfig();
        TemplateConfig.Builder defaultTemplateConfig = DefaultGeneratorStrategy.getDefaultTemplateConfig();
        try {
            AutoGenerator autoGenerator = new AutoGenerator(defaultDatasourceConfig.build());
            String outputDir = globalConfigDto.getOutputDir();
            if (Objects.nonNull(globalConfigDto.getMavenProject()) && globalConfigDto.getMavenProject()) {
                outputDir += "/src/main/java";
            }
            // 全局策略
            defaultGlobalConfig.author(globalConfigDto.getAuthor())
                    // 代码生成路径
                    .outputDir(outputDir);
            if (Objects.nonNull(globalConfigDto.getEnableSwagger()) && globalConfigDto.getEnableSwagger()) {
                // 启动swagger
                defaultGlobalConfig.enableSwagger();
            }
            autoGenerator.global(defaultGlobalConfig.build());

            // 包策略，设置父包名
            defaultPackageConfig.parent(packageConfigDto.getParentPackageName());
            if (StrUtil.isNotBlank(packageConfigDto.getModulePackageName())) {
                // 设置模块包名
                defaultPackageConfig.moduleName(packageConfigDto.getModulePackageName());
            }
            autoGenerator.packageInfo(defaultPackageConfig.build());

            // 生成策略
            if (!CollectionUtils.isEmpty(strategyConfigDto.getIncludeTableNameList())) {
                defaultStrategyConfig.addInclude(strategyConfigDto.getIncludeTableNameList());
            }
            if (!CollectionUtils.isEmpty(strategyConfigDto.getExcludeTableNameList())) {
                defaultStrategyConfig.addExclude(strategyConfigDto.getExcludeTableNameList());
            }
            // 启动使用lombok注解
            if (Objects.nonNull(strategyConfigDto) && strategyConfigDto.getEnableLombok()) {
                defaultStrategyConfig.entityBuilder().enableLombok();
            }
            autoGenerator.strategy(defaultStrategyConfig.build());

            // 设置模板
            autoGenerator.template(defaultTemplateConfig.build()).execute(new FreemarkerTemplateEngine());

            return BaseVo.success();
        } catch (Exception e) {
            log.error("generateCodeByParam in error:{}", e);
            return BaseVo.buildReturnVo(HttpStatus.INTERNAL_SERVER_ERROR.value(), "程序处理异常,请稍后", null);
        }
    }

    /**
     * 根据参数自动生成项目
     *
     * @param param
     * @return
     */
    @Override
    public BaseVo<Void> generateProjectByParam(ProjectGeneratorDTO param) {
        try {

        } catch (Exception e) {
            log.error("generateProjectByParam in error::{}", e);
        }
        return null;
    }
}
