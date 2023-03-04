package cn.itdiary.generator.model.dto.generator.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Author it-learning-diary
 * @Description 项目自动生成陈女士
 * @Date 2023/2/19 16:10
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectGeneratorDTO {

    /**
     * 创建人
     */
    @NotBlank(message = "创建人不能为空")
    private String author;
    /**
     * 分组名称
     */
    @NotBlank(message = "分组名称不能为空")
    private String groupId;

    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空")
    private String artifactId;

    /**
     * 包空间，如cn.test
     */
    @NotBlank(message = "包空间值不能为空")
    private String basePackage;

    /**
     * 项目生成路径，如：D://project
     */
    @NotBlank(message = "项目生成路径不能为空")
    private String generatorPath;

    /**
     * 生成项目是否包含通用common服务
     */
    private Boolean notExistCommonModule;

}
