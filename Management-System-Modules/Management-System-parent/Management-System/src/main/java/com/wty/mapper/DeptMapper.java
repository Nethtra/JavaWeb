package com.wty.mapper;

import com.wty.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 王天一
 * @version 1.0
 */
@Mapper
public interface DeptMapper {
    /**
     * 查询所有部门信息
     *
     * @return
     */
    @Select("select * from dept")
    List<Dept> listDept();

    /**
     * 根据id删除部门信息
     *
     * @param id
     */
    @Delete("delete from dept where id=#{id}")
    void deleteById(Integer id);

    /**
     * 新增部门
     *
     * @param dept
     */
    @Insert("insert into dept (name,create_time,update_time)values(#{name},#{createTime},#{updateTime})")
    void createDept(Dept dept);

    /**
     * 根据id查询部门
     *
     * @param id
     * @return
     */
    @Select("select * from dept where id=#{id}")
    Dept selectById(Integer id);

    /**
     * 根据id修改部门信息
     *
     * @param dept
     */
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void updateById(Dept dept);
}
