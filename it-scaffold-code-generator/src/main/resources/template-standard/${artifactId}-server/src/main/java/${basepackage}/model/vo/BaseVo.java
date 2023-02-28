package cn.itdiary.generator.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @Description: 响应基础参数实体
 * @Author: it-learning-diary
 * @Version: v1
 * @Date: 2023/2/14:11:53
 */
@Data
@AllArgsConstructor
public class BaseVo<T> {

    private int code;

    private String message;

    private T data;

    public BaseVo(int code) {
        this.code = code;
    }

    public BaseVo(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BaseVo success() {
        return new BaseVo(HttpStatus.OK.value());
    }

    public static BaseVo success(String message) {
        return new BaseVo(HttpStatus.OK.value(), message);
    }

    public static <T> BaseVo success(String message, T data) {
        return new BaseVo(HttpStatus.OK.value(), message, data);
    }

    /**
     * 自定义返回实体类型
     * @param code
     * @param message
     * @param data
     * @return
     * @param <T>
     */
    public static <T> BaseVo buildReturnVo(int code, String message, T data) {
        return new BaseVo(HttpStatus.OK.value(), message, data);
    }

}
