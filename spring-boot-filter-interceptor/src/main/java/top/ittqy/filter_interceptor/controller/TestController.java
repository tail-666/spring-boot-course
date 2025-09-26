package top.ittqy.filter_interceptor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ittqy.filter_interceptor.result.Result;

/**
 * @Author: 27258
 * @Date: 2025/9/26
 */
@RestController
@Slf4j
@RequestMapping("/api")
public class TestController {
    @GetMapping("/test")
    public Result<String> test() {
        log.info("进入 TestController");
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        return Result.ok("hello world");
    }
}
