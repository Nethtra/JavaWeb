package com.wty.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解类 用来注解切入点方法
 *
 * @author 王天一
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)//指定生效的时间
@Target(ElementType.METHOD)//指定作用范围
public @interface Log {
}
