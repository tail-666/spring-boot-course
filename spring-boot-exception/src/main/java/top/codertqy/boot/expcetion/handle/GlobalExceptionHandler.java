package top.codertqy.boot.expcetion.handle;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.codertqy.boot.expcetion.common.Result;
import top.codertqy.boot.expcetion.exception.ServerException;

/**
 * @Author: 27258
 * @Date: 2025/9/19
 */
// 全局统一异常处理
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 自定义统一处理服务异常
     * @param e
     * @return
     */
    // 其他地方只要丢ServerException，都会被这个方法处理
    @ExceptionHandler(ServerException.class)
    public Result<String> handleServerException(ServerException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

    /*
     兜底的异常处理,如果没有被上述的捕获，则会进入这个方法
     */
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        return Result.error(e.getMessage());
    }
}
