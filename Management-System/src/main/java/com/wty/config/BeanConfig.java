package com.wty.config;

import com.wty.controller.DeptController;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定义第三方Bean的配置类
 *
 * @author 王天一
 * @version 1.0
 */
@Slf4j
@Configuration
public class BeanConfig {
    //SAXReader  dom4j中的一个类  假如我们想让它成为bean
    @Bean                   //假设bean中还要用到另一个bean 直接在参数中写即可 spring会自动装配进去
    public SAXReader saxReader(DeptController deptController) {//通过@Bean注册的bean默认名称就是这个方法名
        log.warn("注入的bean已经实例化"+deptController);
        return new SAXReader();
    }//在项目启动时会运行@Bean的方法生成第三方bean  所以运行test可以看到两个bean实例化了
}
