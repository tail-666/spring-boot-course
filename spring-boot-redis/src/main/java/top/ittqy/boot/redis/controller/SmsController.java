package top.ittqy.boot.redis.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.ittqy.boot.redis.result.Result;
import top.ittqy.boot.redis.service.SmsService;

/**
 * @Author: 27258
 * @Date: 2025/9/25
 */
@RestController
@RequestMapping("/api")
public class SmsController {
    @Resource
    private SmsService smsService;

    /**
     * 发送短信
     * @param phone 手机号
     * @return
     */
    @GetMapping("/sms/send")
    public Result<Boolean> sendSms(@RequestParam String phone){
        return Result.ok(smsService.sendSms(phone));
    }
}
