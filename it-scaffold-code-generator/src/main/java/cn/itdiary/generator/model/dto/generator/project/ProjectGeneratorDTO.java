package cn.itdiary.generator.model.dto.generator.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String projectName;

    /**
     * 创建人
     */
    private String author;

    /**
     * 项目生成路径，如：D://project
     */
    private String generatorPath;


}
