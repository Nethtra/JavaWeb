package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author 王天一
 * @version 1.0
 * 2演示通知的类型
 */
@Component
@Slf4j
@Aspect
public class AdviceTypeAspect {
    //可以将公共的切入点表达式提取出来 用Pointcut声明    降低耦合
    //如果方法是public 还可以被其他AOP类引用
    @Pointcut("execution(* com.itheima.service.impl.DeptServiceImpl.*(..))")
    public void pointCut() {
    }


    //注意前后指的是目标方法之前之后，所以后面三个都是原始方法执行完后才会添加的逻辑
    //然后只有Around需要显示调用原始方法  返回值必须是Object

    //Before通知
    @Before("pointCut()")
    public void before() {
        log.info("before通知");
    }

    //Around通知
    //around通知可以在原始方法前后都执行逻辑   但是必须显示地调用原始方法
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("around通知前");
        Object result = joinPoint.proceed();
        log.info("around通知后");
        return result;
    }

    //After通知  有异常也通知
    @After("pointCut()")
    public void after() {
        log.info("after通知");
    }

    //AfterReturning 返回后通知，有异常就不会通知
    @AfterReturning("pointCut()")
    public void afterReturning() {
        log.info("AfterReturning通知");
    }

    //AfterThrowing通知
    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        log.info("AfterThrowing通知");
    }

}//在前端发起一个请求  观察通知出现的时机





















