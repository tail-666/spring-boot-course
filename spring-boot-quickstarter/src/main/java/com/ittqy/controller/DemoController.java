package com.ittqy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 27258
 * @Date: 2025/9/4
 */
@RestController
public class DemoController {
    @GetMapping("/demo")
    public String demo() {
        return "demo";
    }

    @GetMapping("/demo2")
    public String demo2() {
        return "demo2";
    }
}
