package top.ittqy.boot.redis.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.ittqy.boot.redis.cache.RedisCache;
import top.ittqy.boot.redis.cache.RedisKeys;
import top.ittqy.boot.redis.dto.LoginRequest;
import top.ittqy.boot.redis.enums.ErrorCode;
import top.ittqy.boot.redis.exception.ServerException;
import top.ittqy.boot.redis.service.LoginService;
import top.ittqy.boot.redis.utils.CommonUtils;
import top.ittqy.boot.redis.vo.LoginResponse;

import java.util.UUID;

/**
 * @Author: 27258
 * @Date: 2025/9/25
 */
@Slf4j
@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final RedisCache redisCache;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String phone = loginRequest.getPhone();

        String inputCode = loginRequest.getCode();
        // 校验手机号格式
        if (!CommonUtils.checkPhone(phone)) {
            throw new ServerException(ErrorCode.PHONE_ERROR);
        }
        // 校验验证码是否为空
        if (inputCode == null || inputCode.trim().isEmpty()) {
            throw new ServerException(ErrorCode.PHONE_CODE_EMPTY);
        }
        // 从Redis中获取验证码 先判断验证码是否过期或者存在
        String redisKey = RedisKeys.getSmsKey(phone);
        Object o = redisCache.get(redisKey);
        if (o == null) {
            throw new ServerException(ErrorCode.PHONE_CODE_EXPIRED);
        }
        String redisCode =  o.toString();

        // 验证码不匹配
        if (!inputCode.equals(redisCode)) {
            throw new ServerException(ErrorCode.PHONE_CODE_ERROR);
        }
        // 验证成功 删除Redis中的验证码
        redisCache.delete(redisKey);

        // 生成token并返回登录信息
        String token = generateToken(phone);

        return new LoginResponse(token, phone);
    }

    private String generateToken(String phone) {
        return UUID.randomUUID().toString().replace("-", "") + phone.hashCode();
    }
}
