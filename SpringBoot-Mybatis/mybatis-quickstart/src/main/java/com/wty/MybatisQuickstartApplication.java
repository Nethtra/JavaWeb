package com.wty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//0数据库名mybatis中建了user表
//1根据表中字段新建pojo实体类  查询的结果会被封装到实体类中
//2在application.properties中配置mybatis连接mysql
//3在持久层定义接口 方法 编写sql
//4在test进行测试
@SpringBootApplication
public class MybatisQuickstartApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisQuickstartApplication.class, args);
    }

}
