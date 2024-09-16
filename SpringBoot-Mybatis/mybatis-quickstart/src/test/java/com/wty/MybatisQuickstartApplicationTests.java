package com.wty;

import com.wty.mapper.UserMapper;
import com.wty.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest//Springboot整合单元测试的注解
class MybatisQuickstartApplicationTests {
    //依赖注入
    @Autowired
    private UserMapper userMapper;

    //测试方法
    @Test
    public void testListUser() {
        List<User> userList = userMapper.listEmp();
//        System.out.println(userMapper.listEmp());
        userList.stream().forEach(user -> {
            System.out.println(user);
        });
    }

}
