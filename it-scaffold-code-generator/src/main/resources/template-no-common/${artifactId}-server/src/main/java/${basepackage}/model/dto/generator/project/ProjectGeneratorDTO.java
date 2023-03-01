package ${basepackage}.model.dto.generator.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
* @Author it-learning-diary
* @Description  项目自动生成陈女士
* @Date 2023/2/19 16:10
* @Version 1.0
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectGeneratorDTO {

    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空")
    private String projectName;

    /**
     * 创建人
     */
    @NotBlank(message = "创建人不能为空")
    private String author;

    /**
     * 项目生成路径，如：D://project
     */
    @NotBlank(message = "项目生成路径不能为空")
    private String generatorPath;


}
