package com.wty.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王天一
 * @version 1.0
 */
//实现：当浏览器访问/hello路径时，返回hello world

@RestController//使用注解标记为当前类为请求处理类
public class HelloController {
    //标记当前方法为请求处理方法
    @RequestMapping("/hello")//指定请求路径 当浏览器访问此路径时就会调用下面的请求处理方法hello 来处理这个请求并响应给用户端
    //默认占用8080端口
    public String hello() {
        System.out.println("hello world1");//输出在控制台
        return "hello world";//返回给浏览器的数据
    }
}
