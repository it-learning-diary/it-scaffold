package ${basepackage}.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Description: 数据源配置相关信息
 * @Author: it-learning-diary
 * @Version: v1
 * @Date: 2023/2/14:11:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSourceConfigDTO {

    /**
     * 数据库连接Url
     */
    @NotBlank(message = "数据库连接Url不能为空")
    private String url;

    /**
     * 账号
     */
    @NotBlank(message = "数据账号不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "数据密码不能为空")
    private String password;

}
