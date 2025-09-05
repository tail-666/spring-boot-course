package com.ittqy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 27258
 * @Date: 2025/9/5
 */

@RestController
public class SwitchController {
    @Value("${my.feature.helloSwitch}")
    private boolean isHelloEnabled;
    @Value("${my.feature.closeMsg}")
    private String closeMsg;

    @GetMapping("/switch")
    public String switchFeature() {
        if(isHelloEnabled){
            return "接口开放中！欢迎访问我的第一个 Spring Boot 项目";
        }else{
            return closeMsg;
        }
    }
}
