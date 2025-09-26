package top.ittqy.boot.redis.controller;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.ittqy.boot.redis.dto.LoginRequest;
import top.ittqy.boot.redis.dto.RegRequest;
import top.ittqy.boot.redis.result.Result;
import top.ittqy.boot.redis.service.LoginService;
import top.ittqy.boot.redis.service.UserService;
import top.ittqy.boot.redis.vo.LoginResponse;
import top.ittqy.boot.redis.vo.UserResponse;

/**
 * @Author: 27258
 * @Date: 2025/9/26
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    private final LoginService loginService;
    private final UserService userService;
    /**
     * 登录操作
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = loginService.login(loginRequest);
        return Result.ok(loginResponse);
    }

    /**
     * 注册操作
     * @param regRequest
     * @return
     */
    @PostMapping("/reg")
    public Result<UserResponse> reg(@RequestBody @Validated RegRequest regRequest) {
        try {
            userService.saveRegUser(regRequest);
            UserResponse user = userService.getUserVOByPhone(regRequest.getPhone());
            return Result.ok(user);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 查询用户信息
     * @param phone
     * @return
     */
    @GetMapping("/getUserInfo")
    public Result<UserResponse> getUserInfo(String phone) {
        UserResponse user = userService.getUserVOByPhone(phone);
        return Result.ok(user);
    }
}
