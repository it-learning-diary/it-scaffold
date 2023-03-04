package cn.itdiary.generator.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.system.SystemUtil;
import cn.itdiary.generator.config.DefaultGeneratorStrategy;
import cn.itdiary.generator.model.constant.GeneratorConstant;
import cn.itdiary.generator.model.dto.*;
import cn.itdiary.generator.model.dto.generator.project.ProjectGeneratorDTO;
import cn.itdiary.generator.model.vo.BaseVo;
import cn.itdiary.generator.service.CodeGeneratorService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.util.RuntimeUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
     * freemarker配置实例
     */
    private static Configuration freemarkerConfiguration = new Configuration(Configuration.VERSION_2_3_21);

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
        String templateName = null;
        // 设定模板
        if (Objects.nonNull(param.getNotExistCommonModule()) && param.getNotExistCommonModule()) {
            templateName = GeneratorConstant.TEMPLATE_NO_COMMON;
        } else {
            templateName = GeneratorConstant.TEMPLATE_STANDARD;
        }

        try {
            // 封装具体数据
            Map<String, Object> generatorProjectParam = buildProjectMap(param);

            // 加载模板地址(对应文件的classpath路径)
            URL templateUrl = Thread.currentThread().getContextClassLoader().getResource(templateName);
            if (null == templateUrl) {
                log.error("查询不到模板信息，模板名称::{]", templateName);
                return BaseVo.fail(HttpStatus.NOT_FOUND.value(), "查询不到模板信息");
            }

            // 模板相关配置
            freemarkerConfiguration.setDefaultEncoding("UTF-8");
            freemarkerConfiguration.setDirectoryForTemplateLoading(new File(templateUrl.getPath()));
            freemarkerConfiguration.setTemplateExceptionHandler(TemplateExceptionHandler.DEBUG_HANDLER);

            // 处理文件保存地址(传入的可能是：\\或者/或者直接是文件名，此时导出的文件需要拼接文件名称)
            File outputFile;
            String outputFileParentPath;
            if (StrUtil.endWith(param.getGeneratorPath(), StrUtil.BACKSLASH) || StrUtil.endWith(param.getGeneratorPath(), StrUtil.SLASH)) {
                outputFileParentPath = param.getGeneratorPath() + param.getArtifactId();
            } else {
                outputFileParentPath = param.getGeneratorPath() + StrUtil.SLASH + param.getArtifactId();
            }
            outputFile = new File(outputFileParentPath);

            // 创建对应文件路径
            FileUtil.mkdir(outputFile);

            // 遍历模板下的所有文件，替换对应的占位符
            String templateUrlPath = templateUrl.getPath();
            File templateFile = new File(templateUrlPath);
            File[] listFiles = templateFile.listFiles();

            // 递归执行
            for (File template : listFiles) {
                generatorTemplateFile(outputFile, template, generatorProjectParam);
            }
            // 弹出生成目录
            openDir(outputFileParentPath);
        } catch (Exception e) {
            log.error("generateProjectByParam in error::{}", e);
            BaseVo.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
        return BaseVo.success();
    }

    /**
     * 递归创建对应的模板文件
     *
     * @param templateFile
     * @param data
     */
    private void generatorTemplateFile(File parentFile, File templateFile, Map<String, Object> data) throws Exception {
        // 文件
        if (templateFile.isFile()) {
            // 设置加载模板地址(需要指定，不然的话会出现template not found...)
            freemarkerConfiguration.setDirectoryForTemplateLoading(new File(templateFile.getParent()));
            // 寻找到具体模板
            Template template = freemarkerConfiguration.getTemplate(templateFile.getName());

            // 替换占位符后的文件输出
            File outFile = SystemUtil.getOsInfo().isWindows() ? new File(parentFile.getAbsolutePath() + StrUtil.BACKSLASH + templateFile.getName()) : new File(parentFile.getAbsolutePath() + StrUtil.SLASH + templateFile.getName());
            if (!outFile.exists()) {
                boolean success = outFile.createNewFile();
                if (!success) {
                    System.out.println("创建文件失败！" + outFile.getAbsolutePath());
                }
            }
            // 1、说明，freemarker中如果占位符的值不存在或者为空，都会抛出异常，
            // 2、因此日志配置文件中存在读取application.yml文件的占位符，需要跳过占位符替换
            // 3、try(){}方式自动关闭流
            try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outFile));) {
                if (!"logback-spring.xml".equals(templateFile.getName())) {
                    // 模板数据合并
                    template.process(data, writer);
                } else {
                    writer.write(template.toString());
                }
            }
            // 文件夹
        } else {
            // 替换包名占位符
            String fileName = templateFile.getName().replace("${basepackage}", String.valueOf(data.get("basepackage")));
            // 替换项目名称占位符
            fileName = fileName.replace("${artifactId}", String.valueOf(data.get("artifactId")));
            // .号替换成分割线，用于创建文件夹
            if (fileName.contains(StrUtil.DOT)) {
                fileName = SystemUtil.getOsInfo().isWindows() ? fileName.replace(StrUtil.DOT, StrUtil.BACKSLASH)
                        : fileName.replace(StrUtil.DOT, StrUtil.SLASH);
            }
            String templateFileParentDirName = SystemUtil.getOsInfo().isWindows() ? (parentFile.getAbsolutePath() + StrUtil.BACKSLASH + fileName)
                    : (parentFile.getAbsolutePath() + StrUtil.SLASH + fileName);

            if (!FileUtil.isDirectory(templateFileParentDirName)) {
                FileUtil.mkdir(templateFileParentDirName);
            }
            File[] files = templateFile.listFiles();
            if (files == null || files.length == 0) {
                return;
            }
            // 递归调用
            for (File item : files) {
                generatorTemplateFile(new File(templateFileParentDirName), item, data);
            }
        }
    }

    /**
     * 封装创建项目的参数
     *
     * @param param
     * @return
     */
    private Map<String, Object> buildProjectMap(ProjectGeneratorDTO param) {
        Map<String, Object> data = new HashMap<>();
        data.put(GeneratorConstant.AUTHOR, param.getArtifactId());
        data.put(GeneratorConstant.GROUP_ID, param.getGroupId());
        data.put(GeneratorConstant.ARTIFACT_ID, param.getArtifactId());
        data.put(GeneratorConstant.BASE_PACKAGE, param.getBasePackage());
        return data;
    }

    /**
     * 打开输出目录
     */
    public void openDir(String openDir) {
        if (StringUtils.isBlank(openDir) || !new File(openDir).exists()) {
            log.error("未找到输出目录：", openDir);
        } else {
            try {
                RuntimeUtils.openDir(openDir);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
