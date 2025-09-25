package top.codertqy.boot.expcetion.service;

import org.springframework.stereotype.Service;
import top.codertqy.boot.expcetion.enums.ErrorCode;
import top.codertqy.boot.expcetion.exception.ServerException;

/**
 * @Author: 27258
 * @Date: 2025/9/19
 */
@Service
public class TestService {
    public void method1() {
        throw new ServerException("余额不足");
    }

    public void method2() {
        throw new ServerException(ErrorCode.FORBIDDEN);
    }
}
