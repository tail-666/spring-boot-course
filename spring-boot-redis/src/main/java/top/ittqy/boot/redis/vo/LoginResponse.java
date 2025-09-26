package top.ittqy.boot.redis.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 27258
 * @Date: 2025/9/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private  String token;
    private  String phone;
    private  String message;

    public LoginResponse(String token, String phone) {
        this.token = token;
        this.phone = phone;
        this.message = "登录成功";
    }
}
