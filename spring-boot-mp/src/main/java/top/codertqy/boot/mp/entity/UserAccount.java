package top.codertqy.boot.mp.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: 27258
 * @Date: 2025/9/18
 */
@TableName("user_account")
@Data
public class UserAccount {
    @TableId(type = IdType.AUTO)
    private Long id;
    @NotBlank(message = "用户名不能为空")
    @Size(max=20,message = "用户名长度不能超过20个字符")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(max=255,message = "密码长度过长")
    private String password;

    @Size(max=20,message = "昵称长度不能超过20个字符")
    private String nickname;

    @Email(message = "邮箱格式不正确")
    @Size(max=100,message = "邮箱长度不能超过100")
    private String email;

    @Size(max=11,message = "手机号长度不能超过11个字符")
    private String phone;

    @Size(max=255,message = "头像URL长度不能超过255个字符")
    private String avatarUrl;

    @TableField("status")
    @Min(value = 0, message = "状态值必须为0或1")
    @Max(value = 1, message = "状态值必须为0或1")
    private Integer status;

    // 判断是否被删除
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

    /**
     * 乐观锁版本号
     */
    @Version
    private Integer version;

    // createTime只在创建时更新
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    // updateTime在创建和更新时都更新
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
