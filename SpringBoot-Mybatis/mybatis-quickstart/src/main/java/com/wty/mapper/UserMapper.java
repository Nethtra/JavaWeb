package com.wty.mapper;

import com.wty.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 王天一
 * @version 1.0
 */
//mapper就是dao
@Mapper//Mybatis规范 定义持久层接口 然后在接口中定义方法
//在运行时会自动生成该接口的实体类对象（动态代理） 并将该对象交给容器管理成为bean
//然后在运行时使用依赖注入即可
public interface UserMapper {
    @Select("select * from user")//这里定义sql语句  查询全部员工信息
    //注意配置sql提示
    public List<User> listEmp();

    //多个参数 使用@Param指定名称
    @Select("select * from user where id=#{id} and age=#{age}")
    public User selectUserByIdAge(@Param("id") Integer id, @Param("age") Integer age);
}
