package com.wty.controller;

import com.wty.pojo.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author 王天一
 * @version 1.0
 */

//在controller中接收请求并响应数据
//请求参数的接收
@RestController
public class RequestController {
    //1简单参数
    //原始方式：通过HttpServletRequest获取请求参数
//    @RequestMapping("/simpleParam")//请求路径
//    public String simpleParam(HttpServletRequest request) {
//        String name = request.getParameter("name");
//        String age = request.getParameter("age");
//        //获取到的数据默认是字符串 需要手动转型
//        int ageInt = Integer.parseInt(age);
//        System.out.println(name + ageInt);
//        return "OK";//返回给前端
//        //启动类后去apifox模拟发起请求 使用get post均可
//    }

    //SpringBoot方式
//    @RequestMapping("/simpleParam")//请求路径
//    //保证前端发起的请求名与形参变量名一致
//    public String simpleParam(String name,int age) {
//        //会自动进行转型
//        System.out.println(name + age);
//        return "OK";//返回给前端
//    }

    //当请求参数名与方法形参名不一致时，使用@RequestParam注解完成映射
    @RequestMapping("/simpleParam")//请求路径
    //注解请求参数名
    //@RequestParam有一个属性required默认为true  所以如果没有传递该请求参数会报错，可以改为false来改为可选传递
    public String simpleParam(@RequestParam(name = "name", required = false) String Username, int age) {
        //会自动进行转型
        System.out.println(Username + age);//没接收到的话就是null
        return "OK";//返回给前端
    }


    //2实体参数
    //simplePojo
    //当请求参数很多时，可以把它们封装到一个实体类的对象中，便于接收，每一个请求参数都是这个实体类的一个属性，请求参数名要与实体类的属性名一致
    //实体类写在pojo包下
    @RequestMapping("/simplePojo")
    public String simplePojo(User user) {
        System.out.println(user);
        return "OK";
    }

    //complexPojo
    //比较复杂的实体参数  比如实体类的属性中有另一个实体类的对象
    //注意一下apifox中请求参数的格式
    @RequestMapping("/complexPojo")
    public String complexPojo(User user) {
        System.out.println(user);
        return "OK";
    }

    //3数组/集合参数
    //数组参数
    //请求参数名要与数组名一致
    //html表单中的复选框  提交时如果在后端接收就需要数组或者集合
    //   也是注意apifox中的请求格式
    @RequestMapping("/arrayParam")
    public String arrayParam(String[] hobby) {
        System.out.println(Arrays.toString(hobby));
        return "OK";
    }

    //集合参数
    //请求参数名要与集合名一致  并且要用RequestParam注解绑定list集合  因为默认情况下会封装到数组中
    @RequestMapping("/listParam")
    public String listParam(@RequestParam List<String> hobby) {
        System.out.println(hobby);
        return "OK";
    }

    //4日期事件参数
    //请求参数名要与形参变量名一致
    //必须使用DateTimeFormat注解来指定传递过来的日期格式
    //也注意apifox中的格式
    @RequestMapping("/dateParam")
    public String dateParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateTime) {
        System.out.println(updateTime);
        return "OK";
    }

    //5json格式参数
    //json格式的参数必须放在请求体中 所以必须用post方法
    //注意一下json的格式
    //一般用pojo实体对象接收  需要使json中的键名与对象的属性名一致 还需要使用RequestBody注解声明
    @RequestMapping("/jsonParam")
    public String jsonParam(@RequestBody User user) {
        System.out.println(user);
        return "OK";
    }

    //6路径参数
    //参数成为url的一部分
    //需要在指定url时{}括起路径参数
    @RequestMapping("/pathParam/{id}/{name}")
    //必须使用pathVariable注解来获取url中的参数并绑定给形参    注意路径中的变量名要与形参名一致
    public String pathParam(@PathVariable Integer id, @PathVariable String name) {
        System.out.println(id + name);
        return "OK";
    }
}
