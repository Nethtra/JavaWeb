package com.wty.controller;

import com.wty.pojo.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author 王天一
 * @version 1.0
 */
//在后端获取请求参数
@RestController
public class RequestController {
    //1简单参数
    //原始方式 通过HttpServletRequest手动获取
//    @RequestMapping("/simpleParam")//指定请求路径
//    public String simpleParam(HttpServletRequest request) {
//        //调用方法  取出来的都是字符串
//        String name = request.getParameter("name");
//        String ageStr = request.getParameter("age");
//        //手动类型转换
//        int age = Integer.parseInt(ageStr);
//        System.out.println(name + ":" + age);
//        return "ok";//返回给前端
//        //启动类后 然后去apifox发起请求测试
//    }

    //springboot简化方式
//    @RequestMapping("/simpleParam")//指定请求路径
//    //保证方法形参名与前端的请求参数名一致 会自动进行类型转换
//    public String simpleParam(String name, int age) {
//        System.out.println(name + ":" + age);//在控制台打印
//        return "ok";//返回给前端
//        //启动类后 然后去apifox发起请求测试 get或者post
//    }

    //如果形参名与请求参数名不一致  使用映射
    @RequestMapping("/simpleParam")//指定请求路径
    //使用注解将name映射到username  RequestParam有一个属性required默认为true 代表前端请求必须传递该参数 不传递就报错
    //可以试试在apifox中不传递name参数
    public String simpleParam(@RequestParam(name = "name" /*代表非必须,required = false*/) String username, int age) {
        System.out.println(username + ":" + age);//在控制台打印
        return "ok";//返回给前端
        //启动类后 然后去apifox发起请求测试 get或者post
    }


    //2实体参数
    //当前端传递请求参数很多时 可以将这些参数封装到一个实体类中接收 但要保证请求参数名与实体类中的属性名一致
    @RequestMapping("/simplePojo")
    public String simplePojo(User user) {
        System.out.println(user);
        return "ok";
    }

    //复杂实体参数 类里套了另一个类的属性
    @RequestMapping("/complexPojo")
    public String complexPojo(User user) {
        System.out.println(user);
        return "ok";
    }//注意前端get请求的写法


    //3数组集合参数
    //前端例如复选框提交多个值时 后端可以使用数组或者集合接收
    //数组
    //使用数组接收时 保证请求参数名与数组名一致 会自动添加到数组中
    @RequestMapping("/arrayParam")
    public String arrayParam(String[] hobby) {
        System.out.println(Arrays.toString(hobby));
        return "ok";
    }

    //集合  使用集合接收时必须添加注解来绑定集合 并且保证集合名与请求参数名一致
    @RequestMapping("/listParam")
    public String listParam(@RequestParam List<String> hobby) {
        System.out.println(hobby);
        return "ok";
    }


    //4日期参数
    @RequestMapping("/dateParam")
    //必须使用@DateTimeFormat注解指定日期格式 前端格式必须与指定格式一致
    public String dateParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime ldt) {
        System.out.println(ldt);
        return "ok";
    }


    //5json格式参数
    //json只能放在请求体中 通常使用实体类接收 json中的键名要与实体类的属性名相同 并且要使用@RequestBody注解 才能将json封装到实体对象中
    @RequestMapping("/jsonParam")
    //狗操的copilot
    public String jsonParam(@RequestBody User user) {
        System.out.println(user);
        return "ok";
    }


    //6路径参数
    //参数是url的一部分 使用@RequestMapping指定请求路径时 使用{}包裹请求参数名   形参需要与@RequestMapping中的一致并且要使用@PathVariable注解
    @RequestMapping("/pathParam/{id}/{name}")
    public String pathParam(@PathVariable int id, @PathVariable String name) {
        System.out.println(id + ":" + name);
        return "ok";
    }
}
