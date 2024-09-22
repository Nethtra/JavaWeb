package com.wty.filter;


import ch.qos.logback.classic.spi.EventArgUtil;
import com.alibaba.fastjson2.JSON;
import com.wty.pojo.Result;
import com.wty.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 王天一
 * @version 1.0
 * <p>
 * 14登陆检查过滤器   拦截请求 检查令牌
 */
@Slf4j
@WebFilter(urlPatterns = "/*")//拦截路径    拦截所有请求
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1登陆请求不需要拦截 所以要先获取url判断是不是登陆请求
        //强转
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURL().toString();//获取请求url
        if (url.contains("login")) {
            log.info("请求为login，放行");
            filterChain.doFilter(request, response);//放行
            return;//注意要直接return
        }
        //2如果不是login请求  就要检查令牌
        //令牌存在header的token字段中   这是跟前端约定好的
        String jwt = request.getHeader("token");
        //2.1如果令牌为空或者没有令牌  hasLength这个方法会同时判断字符串为null或者空串
        if (!StringUtils.hasLength(jwt)) {
            log.info("令牌为空");
            //因为这里不是controller controller的@RestController会自动将返回对象转为json  所以要手动转json
            //使用alibaba fastjson依赖
            Result error = Result.error("NOT_LOGIN");//先拿到返回对象
            String notLogin = JSON.toJSONString(error);//转成json字符串
            //一定要想清楚这里不是controller 所以很多事情要自己写
            response.getWriter().write(notLogin);//返回错误信息
            return;
        }
        //2.2如果存在令牌  判断解析是否成功
        //直接trycatch  如果出异常就是解析失败  和上面一样直接返回
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            log.info("令牌解析失败");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSON.toJSONString(error);
            response.getWriter().write(notLogin);
            return;
        }

        //3上面步骤都完成后才说明令牌校验成功  放行
        filterChain.doFilter(request, response);
    }//打断点 在apifox中测试登陆请求    查询所有部门（注意在header中携带token）
    //联调测试  试试直接访问登陆以外的页面 会被送到login页面
}













