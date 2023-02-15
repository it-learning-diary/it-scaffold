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
     * 代码生成路径
     */
    @NotBlank(message = "代码生成存放路径不能为空")
    private String outputDir;

    /**
     * 是否启用swagger
     */
    private Boolean enableSwagger;


}
