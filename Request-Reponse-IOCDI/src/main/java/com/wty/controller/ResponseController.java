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
//响应数据的格式
//controller中每一个方法都是一个功能接口 定义了请求路径 请求参数 响应数据等

@RestController//=@ResponseBody+@Controller
//@ResponseBody作用在类或者方法上  可以使方法的return直接响应给前端 所以刚才在RequestController中ok才能直接显示
//如果返回类型是对象或集合 会转为json格式返回
public class ResponseController {
    /*@RequestMapping("/returnStr")
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


    //可以发现如果采用上面的响应方式，响应回前端的数据格式各种各样，前端需要解析多种格式的数据，不便于维护和管理
    //实际开发中会为所有的接口设置统一的响应结果Result  统一返回一个Result对象 使前端只获取一种格式的数据

    //将所有接口的返回类型都设置为统一的Result
    @RequestMapping("/returnStr")
    public Result returnStr() {
        System.out.println("hello world");
//        return new Result(1,"success","hello world");
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
    //可以看到响应到前端的数据格式统一为json
}
