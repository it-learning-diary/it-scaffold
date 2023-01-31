package cn.diary.test;

import cn.itdiary.generator.GeneratorApplication;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

/**
* @Author it-learning-diary
* @Description 代码测试类
* @Date 2023/1/23 9:32
* @Version 1.0
*/
@SpringBootTest(classes = GeneratorApplication.class)

public class ProjectTest {

    @Value("${server.port}")
    private Integer serverPort;




    @Test
    public void test(){

    }

}
