package com.ittqy.springbootquickstart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * #Author: 27258
 * #Date: 2025/9/4
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
    @GetMapping("/list")
    public List<String> getList(){
        return List.of("1", "2", "3");
    }

}
