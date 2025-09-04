package com.ittqy.springbootquickstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootQuickstartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootQuickstartApplication.class, args);
        System.out.println("http://localhost:8080/hello");
    }

}
