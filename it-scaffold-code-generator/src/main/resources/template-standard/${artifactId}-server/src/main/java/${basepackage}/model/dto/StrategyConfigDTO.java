package cn.itdiary.generator.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Description: 表生成策略配置，如配置需要生成哪些表，排除哪些前缀的表等
 * @Author: it-learning-diary
 * @Version: v1
 * @Date: 2023/2/14:11:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StrategyConfigDTO {

    /**
     * 需要生成的表名词
     */
    private List<String> includeTableNameList;

    /**
     * 不需要生成的表名词
     */
    private List<String> excludeTableNameList;

    /**
     * 是否启用lombok注解
     */
    private Boolean enableLombok;

}
