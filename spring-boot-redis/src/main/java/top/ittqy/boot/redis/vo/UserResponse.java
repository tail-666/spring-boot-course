package top.ittqy.boot.redis.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: 27258
 * @Date: 2025/9/26
 * @Description: 注册成功后的响应
 */
@Data
public class UserResponse {
    private Integer id;
    private String username;
    private String nickname;
    private String phone;
    private String avatar;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
