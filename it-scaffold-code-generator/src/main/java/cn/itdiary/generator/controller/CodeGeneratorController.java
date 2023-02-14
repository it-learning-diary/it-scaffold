package cn.itdiary.generator.controller;

import cn.itdiary.generator.model.dto.CodeGeneratorDTO;
import cn.itdiary.generator.model.vo.BaseVo;
import cn.itdiary.generator.service.CodeGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Description: 代码自动生成功能入口
 * @Author: it-learning-diary
 * @Version: v1
 * @Date: 2023/2/14:11:51
 */
@RestController
@RequestMapping("/code/")
@RequiredArgsConstructor
public class CodeGeneratorController {

    private final CodeGeneratorService codeGeneratorService;

    /**
     * 自动生成代码
     *
     * @param param
     * @return
     */
    @PostMapping("generator")
    public BaseVo<Void> generateCodeByParam(@RequestBody @Valid CodeGeneratorDTO param) {
        BaseVo<Void> result = codeGeneratorService.generateCodeByParam(param);
        return result;
    }

}
