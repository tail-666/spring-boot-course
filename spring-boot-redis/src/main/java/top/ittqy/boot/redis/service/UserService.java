package top.ittqy.boot.redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ittqy.boot.redis.dto.RegRequest;
import top.ittqy.boot.redis.entity.User;
import top.ittqy.boot.redis.vo.UserResponse;

/**
 * @Author: 27258
 * @Date: 2025/9/26
 */
public interface UserService extends IService<User> {
    /**
     * 保存注册用户
     * @param regRequest
     */
    void saveRegUser(RegRequest regRequest);
    /**
     * 根据手机号查询用户
     * @param phone
     * @return
     */
    UserResponse getUserVOByPhone(String phone);
}
