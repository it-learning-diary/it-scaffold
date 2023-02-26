package cn.itdiary.generator.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Description: 全局信息配置，如代码生成着，代码存放路径等
 * @Author: it-learning-diary
 * @Version: v1
 * @Date: 2023/2/14:11:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalConfigDTO {
    /**
     * 作者名称
     */
    @NotBlank(message = "作者名称不能为空")
    private String author;

    /**
     * 代码生成路径，如D盘code目录下的it-test项目则为：D://code//it-test
     */
    @NotBlank(message = "代码生成存放路径不能为空")
    private String outputDir;

    /**
     * 是否启用swagger
     */
    private Boolean enableSwagger;

    /**
     * 生成的代码项目是否是Maven项目，如果是，生成的代码会自动在上面的outputDir后面加上/src/main/java下
     * 即实际生成的代码位置是：outputDir字段的值+/src/main/java
     */
    private Boolean mavenProject;

}
