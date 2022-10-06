package com.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
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
    public Docket docket(Environment environment) {// 获取项目环境
        // => 配置 swagger 仅在生产环境中使用
        // 配置需要使用的 Swagger 环境
        Profiles profiles = Profiles.of("Development", "Test");
        // 判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 配置分组
                .groupName("分组一")
                // 是否启用 Swagger => 关闭的时候将不能进行匹配任何请求
                .enable(flag)
                .select()// 进行扫描配置
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
                .paths(PathSelectors.ant("/*"))
                .build();// 工厂模式(Factory)进行构造
    }

    @Bean
    public Docket docket1() {
        // 配置分组
        return new Docket(DocumentationType.SWAGGER_2).groupName("分组二");
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