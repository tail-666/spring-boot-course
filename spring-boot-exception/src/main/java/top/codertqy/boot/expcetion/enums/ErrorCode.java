package top.codertqy.boot.expcetion.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: 27258
 * @Date: 2025/9/19
 */
@AllArgsConstructor
@Getter
public enum ErrorCode {
    // 逗号分隔，结尾分号
    BAD_REQUEST(400,"请求参数不合法"),
    UNAUTHORIZED(401, "登录失效，请重新登录"),
    FORBIDDEN(403, "无权限访问"),
    NOT_FOUND(404, "未找到资源"),
    INTERNAL_SERVER_ERROR(500, "服务器异常,请稍后再试");


    // 枚举的变量是不可变的，所以使用final修饰
    private final int code;
    private final String msg;
}
