package top.ittqy.filter_interceptor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Author: 27258
 * @Date: 2025/10/8
 * @Description: 登录请求对象
 */
@Data
public class LoginRequest {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}