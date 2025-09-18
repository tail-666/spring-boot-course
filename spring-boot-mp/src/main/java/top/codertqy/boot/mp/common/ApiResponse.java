package top.codertqy.boot.mp.common;

import lombok.Getter;

/**
 * @Author: 27258
 * @Date: 2025/9/12
 */
@Getter
public class ApiResponse<T> {
    private final int code;
    private final String message;
    private final T data;

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 创建一个成功响应
     *
     * @param msg  响应消息
     * @param data 响应数据
     * @param <T>  响应数据类型
     * @return 响应对象
     */
    public static <T> ApiResponse<T> success(String msg, T data) {
        return new ApiResponse<>(200, msg, data);
    }

    /**
     * 创建一个失败响应
     *
     * @param message 响应消息
     * @param <T>     响应数据类型
     * @return 响应对象
     */
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(400, message, null);
    }
}
