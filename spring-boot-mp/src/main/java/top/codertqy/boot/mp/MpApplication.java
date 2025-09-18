package top.codertqy.boot.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: 27258
 * @Date: 2025/9/18
 */
@SpringBootApplication
// 在启动类上加上自动加上mapper的扫描，不需要对每个mapper都进行@Mapper注解
@MapperScan("top.codertqy.boot.mp.mapper")
public class MpApplication {
    public static void main(String[] args) {
        SpringApplication.run(MpApplication.class, args);
    }
}
