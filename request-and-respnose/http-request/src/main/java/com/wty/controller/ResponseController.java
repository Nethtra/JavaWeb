package com.wty.controller;

import com.wty.pojo.Address;
import com.wty.pojo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王天一
 * @version 1.0
 */
@RestController//@RestController包含ResponseBody    ctrl单击@RestController查看
//ResponseBody可以作用在方法或者类上 作用是将方法的返回值直接响应给前端，所以在刚才RequestController中才能直接显示给前端ok
//如果返回值是对象或者集合 会转换为json格式再响应
public class ResponseController {
/*    //返回值为字符串
    @RequestMapping("/returnStr")
    public String returnStr() {
        System.out.println("hello world");
        return "hello world";
    }

    //返回值为对象
    @RequestMapping("/returnObj")
    public Address returnObj() {
        Address address = new Address();
        address.setProvince("beijing");
        address.setCity("beijing");
        return address;
    }

    //返回值为集合
    @RequestMapping("/returnList")
    public List<Address> returnList() {
        List<Address> list = new ArrayList<>();
        Address address1 = new Address();
        address1.setProvince("shanghai");
        address1.setCity("shanghai");
        Address address2 = new Address();
        address2.setProvince("guangdong");
        address2.setCity("guangzhou");
        list.add(address1);
        list.add(address2);
        return list;
    }*/
    //用apifox发起请求后可以看到
    //响应给前端的数据有多种且没有统一的规范，前端需要解析多种格式的数据，不便于开发和管理


    //实际开发中会设置统一的响应结果 返回一个规定的Result对象 这样前端响应的就是统一格式的json数据，前端只需要解析一种格式
//改写
    @RequestMapping("/returnStr")
    public Result returnStr() {
        System.out.println("hello world");
        return Result.success("hello world");
    }

    //返回值为对象
    @RequestMapping("/returnObj")
    public Result returnObj() {
        Address address = new Address();
        address.setProvince("beijing");
        address.setCity("beijing");
        return Result.success(address);
    }

    //返回值为集合
    @RequestMapping("/returnList")
    public Result returnList() {
        List<Address> list = new ArrayList<>();
        Address address1 = new Address();
        address1.setProvince("shanghai");
        address1.setCity("shanghai");
        Address address2 = new Address();
        address2.setProvince("guangdong");
        address2.setCity("guangzhou");
        list.add(address1);
        list.add(address2);
        return Result.success(list);
    }
    //把数据封装到result对象中 直接return result对象
    //此时再使用apifox查看返回给前端的数据   格式变得规整
}
