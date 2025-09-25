package top.codertqy.boot.mp.enums;

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
    FORBIDDEN(403, "库存调整失败"),
    NOT_FOUND(404, "库存不足"),
    INTERNAL_SERVER_ERROR(500, "服务器异常,请稍后再试"),
    DELETE_BOOK(407,"逻辑删除失败"),
    UPDATE_BOOK(408,"恢复失败,图书未被删除");


    // 枚举的变量是不可变的，所以使用final修饰
    private final int code;
    private final String msg;
}
