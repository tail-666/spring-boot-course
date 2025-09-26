package top.ittqy.boot.redis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ittqy.boot.redis.dto.RegRequest;
import top.ittqy.boot.redis.entity.User;
import top.ittqy.boot.redis.mapper.UserMapper;
import top.ittqy.boot.redis.service.UserService;
import top.ittqy.boot.redis.utils.PasswordUtil;
import top.ittqy.boot.redis.vo.UserResponse;

import java.time.LocalDateTime;

/**
 * @Author: 27258
 * @Date: 2025/9/26
 */
@Service
@Slf4j
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void saveRegUser(RegRequest regRequest) {
        // 1. 检查手机号是否已存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", regRequest.getPhone());
        if (this.count(queryWrapper) > 0) {
            throw new RuntimeException("该手机号已被注册");
        }

        // 2. 构造 User 对象
        User user = new User();
        user.setUsername(regRequest.getUsername());
        user.setPhone(regRequest.getPhone());
        user.setNickname(regRequest.getNickname());
        user.setPassword(PasswordUtil.encryptPassword(regRequest.getPassword())); // 加密存储
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        // 3. 保存到数据库
        this.save(user);
    }

    // 可选：根据 phone 查询用户并返回 VO
    public UserResponse getUserVOByPhone(String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        User user = this.getOne(queryWrapper);
        if (user == null) return null;

        UserResponse vo = new UserResponse();
        log.info("user: {}", user);
        BeanUtils.copyProperties(user, vo); // 属性拷贝（需确保字段名一致）
        return vo;
    }
}
