package cn.itdiary.generator.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Description: 代码存放的包相关配置，如父包名，子模块包名，mapper文件路径等
 * @Author: it-learning-diary
 * @Version: v1
 * @Date: 2023/2/14:11:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageConfigDTO {

    /**
     * 父包名名词，多个单词间使用逗号隔开
     */
    @NotBlank(message = "父包名不能为空")
    private String parentPackageName;

    /**
     * 子模块包名，多个单词使用逗号隔开
     */
    private String modulePackageName;

}
