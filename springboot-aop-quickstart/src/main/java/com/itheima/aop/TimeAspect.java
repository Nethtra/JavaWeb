package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author 王天一
 * @version 1.0
 * 1aop入门
 * 统计方法的执行耗时
 * aop程序放在aop包下
 */
@Component
@Slf4j
@Aspect//标识当前类为AOP类
public class TimeAspect {
//    @Around("com.itheima.aop.AdviceTypeAspect.pointCut()")
    @Around("execution(* com.itheima.service.*.*(..))")//切入点表达式  指定要接管的原始方法 当运行这些方法时就会执行这段逻辑
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();//运行原始方法    result是原始方法的返回值
        long end = System.currentTimeMillis();
        log.info(joinPoint.getSignature() + "方法执行时间{}ms", (end - begin));//joinPoint.getSignature()获取原始方法的名称
        return result;//将原来方法的返回值返回  注意位置
        //注意如果原始方法有返回值的话 在通知里必须return回去 要不运行原始方法时返回会失败
    }//可以看到并没有修改service中的方法就完成了执行耗时的统计 减少了重复代码
}























