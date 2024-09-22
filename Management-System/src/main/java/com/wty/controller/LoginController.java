package com.wty.controller;

import com.wty.pojo.Emp;
import com.wty.pojo.Result;
import com.wty.service.EmpService;
import com.wty.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王天一
 * @version 1.0
 */
@RestController
@Slf4j
public class LoginController {
    @Autowired
    private EmpService empService;

    /**
     * 13用户登陆功能  登陆认证
     *
     * @param emp
     * @return
     */
    //1)基本思路就是根据用户名和密码查用户信息 如果有这个人就放行  没有就报错
    //注意本质上的逻辑还是查询员工信息  所以要用EmpService
    //2)登陆下发jwt令牌
    //3)收到请求拦截校验
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("登陆用户信息{}", emp);
        Emp e = empService.login(emp);
//看接口文档描述  如果登陆成功就要返回jwt令牌  前端下次请求会在请求头中携带
        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", emp.getId());//设置要在PayLoad的信息 跟前端规定好
            claims.put("name", emp.getName());
            claims.put("username", emp.getUsername());
            String jwt = JwtUtils.generateJwt(claims);//登陆成功获取gwt令牌
            return Result.success(jwt);//返回
            //apifox测试
            //联调测试f12 login时可以看到后端返回了令牌 存储在Application Localstorage中
            //下次请求可以看到请求头中包含了令牌  token:
        }
//        return e != null ? Result.success() : Result.error("用户名或密码错误");
        return Result.error("用户名或密码错误");
    }//测试
}
