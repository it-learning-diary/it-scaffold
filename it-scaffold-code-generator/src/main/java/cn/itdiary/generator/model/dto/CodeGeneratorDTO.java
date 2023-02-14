package cn.itdiary.generator.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Description: 代码自动生成参数实体
 * @Author: it-learning-diary
 * @Version: v1
 * @Date: 2023/2/14:12:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeGeneratorDTO {

    /**
     * 数据源配置相关信息
     */
    @NotNull(message = "数据源配置信息不能为空")
    @Valid
    private DataSourceConfigDTO dataSourceConfigDto;

    /**
     * 全局信息配置，如代码生成着，代码存放路径等
     */
    @NotNull(message = "全局信息配置不能为空")
    @Valid
    private GlobalConfigDTO globalConfigDto;

    /**
     * 代码存放的包相关配置，如父包名，子模块包名，mapper文件路径等
     */
    @NotNull(message = "包相关配置不能为空")
    @Valid
    private PackageConfigDTO packageConfigDto;

    /**
     * 表生成策略配置，如配置需要生成哪些表，排除哪些前缀的表等
     */
    @NotNull(message = "表生成策略配置不能为空")
    @Valid
    private StrategyConfigDTO strategyConfigDto;

}
