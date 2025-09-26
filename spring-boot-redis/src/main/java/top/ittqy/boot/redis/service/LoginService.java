package top.ittqy.boot.redis.service;

import top.ittqy.boot.redis.dto.LoginRequest;
import top.ittqy.boot.redis.vo.LoginResponse;

/**
 * @Author: 27258
 * @Date: 2025/9/25
 */

public interface LoginService {
    /**
     * 验证码登录
     * @param loginRequest 登录请求参数
     * @return 登录响应
     */
    LoginResponse login(LoginRequest loginRequest);
}
