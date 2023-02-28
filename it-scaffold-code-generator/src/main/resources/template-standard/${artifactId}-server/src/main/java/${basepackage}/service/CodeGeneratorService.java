package cn.itdiary.generator.service;

import cn.itdiary.generator.model.dto.CodeGeneratorDTO;
import cn.itdiary.generator.model.dto.generator.project.ProjectGeneratorDTO;
import cn.itdiary.generator.model.vo.BaseVo;

/**
 * @Description: 自动生成代码逻辑处理
 * @Author: it-learning-diary
 * @Version: v1
 * @Date: 2023/2/14:14:06
 */
public interface CodeGeneratorService {

    /**
     * 自动生成代码
     * @param param
     * @return
     */
    BaseVo<Void> generateCodeByParam(CodeGeneratorDTO param);

    /**
     * 自动生成项目
     * @param param
     * @return
     */
    BaseVo<Void> generateProjectByParam(ProjectGeneratorDTO param);

}
