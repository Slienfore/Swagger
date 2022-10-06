package com.swagger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 为该类中的所有方法都添加上 @ResponseBody 注解
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello swagger!";
    }

}