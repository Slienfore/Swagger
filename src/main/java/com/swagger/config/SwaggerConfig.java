package com.swagger.config;

import com.swagger.controller.HelloController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // 配置 Swagger 的 Docket 的 Bean 实例
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 是否进行
                .enable(true)
                .select()
                // 扫描方式
                // basePackage => 指定需要扫描的包(basePackage("com.swagger.controller"))
                // any 扫描当前 web 项目下所有的包
                // none 不扫描
                // withMethodAnnotation 扫描 '方法上' 指定请求类型注解.class 文件(RequestMapping.class)
                // withClassAnnotation 扫描 '类' 上的请求类型注解.class 文件(RequestMapping.class)
                .apis(RequestHandlerSelectors.basePackage("com.swagger.controller"))
                // 扫描过滤路径
                // PathSelectors.ant("") => 请求路径中与该路径相同时候才会进行匹配
                // PathSelectors.any("") => 匹配任何路径
                // PathSelectors.none("") => 不进行匹配
                // PathSelectors.regex("") => 使用正则表达式进行匹配
                .paths(PathSelectors.ant("/Slienfore/**"))
                .build();// 工厂模式(Factory)进行构造
    }

    // 配置 Swagger 信息
    private ApiInfo apiInfo() {
        // 作者信息
        Contact contact = new Contact(
                "Slienfore",
                "https://github.com/",
                "slienfore@163.com");

        return new ApiInfo(
                "Slienfore 的 swagger 文档",
                "历经千帆",
                "1.0",
                "https://github.com/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }
}