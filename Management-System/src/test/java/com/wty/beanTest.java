package com.wty;

import com.wty.controller.DeptController;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * 关于bean的测试
 *
 * @author 王天一
 * @version 1.0
 */
@SpringBootTest
public class beanTest {
    //直接注入IOC容器对象
    @Autowired
    private ApplicationContext applicationContext;

    //测试手动获取bean
    @Test
    public void testGetBean() {
        //这些方法都是Spring提供的
        //1使用bean对象的名称获取  注意这个getBean返回值是Object  bean的默认名称是类名首字母小写
        DeptController bean1 = (DeptController) applicationContext.getBean("deptController");
        System.out.println(bean1);
        //2通过bean对象的类型获取
        DeptController bean2 = applicationContext.getBean(DeptController.class);
        System.out.println(bean2);
        //3根据bean对象的名称和类型获取
        DeptController bean3 = applicationContext.getBean("deptController", DeptController.class);
        System.out.println(bean3);

        //运行输出发现地址值都一样  说明默认情况下bean对象是单例的
    }


    //测试Bean的作用范围
    //bean的作用范围决定了bean是单例还是多例
    @Test
    public void testBeanScope() {
        //循环拿十个bean
        for (int i = 0; i < 10; i++) {
            DeptController bean = (DeptController) applicationContext.getBean("deptController");
            System.out.println(bean);
        }
    }

    //测试第三方bean
    @Test
    public void testThirdPartyBean() {
        SAXReader bean = (SAXReader) applicationContext.getBean("saxReader");
        System.out.println(bean);
    }
}

















