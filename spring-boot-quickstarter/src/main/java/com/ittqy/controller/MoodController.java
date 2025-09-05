package com.ittqy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 27258
 * @Date: 2025/9/5
 */
@RestController
public class MoodController {
    @Value("${my.mood.today}")
    private String today;
    @Value("${my.mood.content}")
    private String content;
    @Value("${my.mood.author}")
    private String author;

    @GetMapping("/mood")
    public String mood() {
        return "今天是" + today + "，心情是" + content + "，作者是" + author;
    }
}
