package top.codertqy.boot.expcetion.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.codertqy.boot.expcetion.common.Result;
import top.codertqy.boot.expcetion.entity.User;
import top.codertqy.boot.expcetion.service.TestService;

/**
 * @Author: 27258
 * @Date: 2025/9/19
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private TestService testService;

    @GetMapping("/{id}")
    public Result<String> test(@PathVariable Integer id) {
        if (id == 1) {
            testService.method1();
        } else if (id == 2) {
            testService.method2();
        } else {
            // int i = 1 / 0;
            return Result.ok("请求成功");
        }
        return Result.ok("请求成功");
    }

    /**
     * 测试校验异常处理
     * @param user
     * @param bindingResult 是一个参数，用于接收参数的校验结果信息
     */
    @PostMapping("/user")
    public Result<User> createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        // 如果校验结果中有错误，则返回错误信息
        if (bindingResult.hasErrors()) {
            return Result.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return Result.ok(user);
    }

}
