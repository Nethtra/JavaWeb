package com.wty.interceptor;

import com.alibaba.fastjson2.JSON;
import com.wty.pojo.Result;
import com.wty.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 王天一
 * @version 1.0
 * 登陆检查拦截器   和过滤器逻辑基本相同
 */
@Component//交给容器管理
@Slf4j
//1先定义
public class LoginCheckInterceptor implements HandlerInterceptor {
    //ctrl+o快速重写方法
    //与Filter的逻辑基本一致，所以直接复制doFilter方法   到preHandle 然后改一下即可
    @Override //目标资源（controller中的方法）运行前运行   true放行   false不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandel");
        //因为/login在配置类中已经排除  所以不需要在Interceptor中再判断
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
            return false;
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
            return false;
        }

        //3上面步骤都完成后才说明令牌校验成功  放行
        //放行直接return true
        log.info("令牌合法，放行");
        return true;
    }

    @Override //目标资源方法即controller方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override //视图渲染完毕后运行   最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}//测试























