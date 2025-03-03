package com.wty.aop;

import com.alibaba.fastjson2.JSONObject;
import com.wty.mapper.OperateLogMapper;
import com.wty.pojo.OperateLog;
import com.wty.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 切面类  记录增删改操作日志
 *
 * @author 王天一
 * @version 1.0
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private OperateLogMapper operateLogMapper;

    //思路：就是要在增删改执行后调用Mapper插入日志  要传入OperateLog类型的参数，所以需要先获取这些参数
    //然后就是使用@annotation切入点表达式 用自定义注解来匹配目标方法
    @Around("@annotation(com.wty.anno.Log)")//使用环绕通知   annotation方式匹配  里面写自定义注解的全类名 在controller中的增删改使用注解
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //1操作人id
        //考虑前端每次发起请求都会携带jwt令牌，令牌携带了自定义的部分信息 就包括id
        //通过HttpServletRequest对象来获取jwt令牌 并解析出id
        String jwt = httpServletRequest.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);//claim是一个Map集合 存放了自定义信息
        Integer operateUser = (Integer) claims.get("id");
        //2操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        //3目标类名
        String className = joinPoint.getTarget().getClass().getName();
        //4目标方法名
        String methodName = joinPoint.getSignature().getName();
        //5目标方法的参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();//执行原始方法
        long end = System.currentTimeMillis();

        //6目标方法的返回值  用到了fastjson依赖 将返回值先转成json
        String returnValue = JSONObject.toJSONString(result);

        //7执行耗时
        long costTime = begin - end;


        //调用Mapper记录日志
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        operateLogMapper.insert(operateLog);

        log.info("记录操作日志{}", operateLog);

        return result;

    }//应该是有问题的  如果出异常操作失败的话就不会记录日志
}
