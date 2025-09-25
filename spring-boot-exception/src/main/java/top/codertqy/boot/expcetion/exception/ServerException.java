package top.codertqy.boot.expcetion.exception;

import lombok.Getter;
import lombok.Setter;
import top.codertqy.boot.expcetion.enums.ErrorCode;

/**
 * @Author: 27258
 * @Date: 2025/9/19
 * @Description: 自定义异常
 */
@Getter
@Setter
public class ServerException extends RuntimeException{
    // code是自己的，
    private int code;
    private String message;

    public ServerException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.message = errorCode.getMsg();
    }

    public ServerException(String message) {
        super(message);
        // 不是定义的code，则默认为500
        this.code = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
        this.message = message;
    }
}
