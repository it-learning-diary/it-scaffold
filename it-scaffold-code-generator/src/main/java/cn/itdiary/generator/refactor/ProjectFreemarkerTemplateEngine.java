package cn.itdiary.generator.refactor;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import freemarker.template.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProjectFreemarkerTemplateEngine extends AbstractTemplateEngine {
    private Configuration configuration;
    @Override
    public AbstractTemplateEngine init(ConfigBuilder configBuilder) {
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding(ConstVal.UTF8);
        configuration.setClassForTemplateLoading(FreemarkerTemplateEngine.class, StringPool.SLASH);
        return this;
    }

    @Override
    public String templateFilePath(String filePath) {
        return filePath + ".ftl";
    }

    @Override
    public AbstractTemplateEngine batchOutput() {
        try {
            ConfigBuilder config = this.getConfigBuilder();
            List<TableInfo> tableInfoList = new ArrayList<>();
            TableInfo tableInfo1 = new TableInfo(config,"demo");
            tableInfo1.setEntityName("Demo");
            tableInfo1.setComment("测试");
            tableInfo1.processTable();
            tableInfoList.add(tableInfo1);
            tableInfoList.forEach(tableInfo -> {
                Map<String, Object> objectMap = this.getObjectMap(config, tableInfo);
                Optional.ofNullable(config.getInjectionConfig()).ifPresent(t -> {
                    // 添加自定义属性
                    t.beforeOutputFile(tableInfo, objectMap);
                    // 输出自定义文件
                    outputCustomFile(t.getCustomFiles(), tableInfo, objectMap);
                });
                // entity
                outputEntity(tableInfo, objectMap);
                // mapper and xml
                outputMapper(tableInfo, objectMap);
                // service
                outputService(tableInfo, objectMap);
                // controller
                outputController(tableInfo, objectMap);
            });
        } catch (Exception e) {
            throw new RuntimeException("无法创建文件，请检查配置信息！", e);
        }
        return this;
    }
}
