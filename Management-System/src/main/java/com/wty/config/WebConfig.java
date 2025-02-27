package com.wty.config;

import com.wty.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 王天一
 * @version 1.0
 * <p>
 * 新建的配置类
 */
//2再配置注册拦截器
@Configuration//声明为配置类
public class WebConfig implements WebMvcConfigurer {
    @Autowired//注入
    private LoginCheckInterceptor loginCheckInterceptor;

    //重写这个方法注册  并设置拦截路径
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //指定拦截路径   排除的拦截路径
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
