package com.wty.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解类 用来标注切入点方法@annotation
 * 注解类里不用写内容，因为我们只需要用它来标记
 *
 * @author 王天一
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)//指定生效的时间
@Target(ElementType.METHOD)//指定注解作用范围
public @interface Log {
}
