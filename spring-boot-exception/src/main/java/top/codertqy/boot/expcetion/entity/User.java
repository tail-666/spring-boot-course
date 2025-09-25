package top.codertqy.boot.expcetion.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * @Author: 27258
 * @Date: 2025/9/19
 */
@Data
public class User {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    @Max(value = 100, message = "年龄不能大于100岁")
    @Min(value = 1, message = "年龄不能小于1岁")
    private int age;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;
}
