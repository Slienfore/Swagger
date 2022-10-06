package com.swagger.controller;

import com.swagger.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Hello 接口控制类")
// 为该类中的所有方法都添加上 @ResponseBody 注解
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello swagger!";
    }

    // 只要接口中包含实体类, 那么实体类将会被扫描到 Swagger 中
    @PostMapping("/user")
    public User user() {
        return new User();
    }

    // 给当前请求以及请求参数添加注释
    @PostMapping("/annotation/{username}")
    @ApiOperation("处理用户姓名")
    public String annotation(@ApiParam("用户名") @PathVariable("username") String username) {
        return "hello " + username;
    }

    // 用户注释
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public User register(@ApiParam("用户信息") @RequestBody User user) {
        return user;
    }
}