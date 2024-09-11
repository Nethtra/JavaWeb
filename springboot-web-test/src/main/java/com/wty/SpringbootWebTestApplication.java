package com.wty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//SpringBoot入门：使用SpringBoot开发一个Web应用，当浏览器发起请求/hello后，给浏览器返回字符串hello world
@SpringBootApplication
//启动类 用于启动springboot工程
//application.properties SpringBoot项目的配置文件
//在启动类启动SpringBoot项目
public class SpringbootWebTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebTestApplication.class, args);
    }

}
