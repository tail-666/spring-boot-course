package top.ittqy.boot.redis.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * @Author: 27258
 * @Date: 2025/9/26
 * @Description: 用来注册的参数
 */
@Data
public class RegRequest {
    @NotBlank(message = "用户名不能为空")
    private String username;
    private String password;
    private String nickname;
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

}
