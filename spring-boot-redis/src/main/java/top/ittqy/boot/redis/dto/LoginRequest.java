package top.ittqy.boot.redis.dto;

import lombok.Data;

/**
 * @Author: 27258
 * @Date: 2025/9/25
 */
@Data
public class LoginRequest {
    private String phone;
    private String code;
}
