package com.wty.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 王天一
 * @version 1.0
 */
@Mapper
public interface EmpMapper {
    //1根据主键id删除数据
    //注意sql语句不能写死 而是要根据前端传递的id删除
    //要使用mybatis中提供的参数占位符#{}
    //其实增删改语句都是有返回值的  返回此次操作影响的记录数  但是可以声明为void
    @Delete("delete from emp where id=#{id}")
    public void delete(Integer id);
//    public int delete(Integer id);
}
