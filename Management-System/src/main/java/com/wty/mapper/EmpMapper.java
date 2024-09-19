package com.wty.mapper;

import com.wty.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * @author 王天一
 * @version 1.0
 */
@Mapper
public interface EmpMapper {
    /**
     * 查询员工的总数
     *
     * @return
     */
    @Select("select count(*) from emp")
    Long count();

    /**
     * 分页查询员工
     *
     * @param start
     * @param pageSize
     * @return
     */
    @Select("select * from emp limit #{start},#{pageSize}")
    //别忘了用注解
    List<Emp> paginatedSelect(@Param("start") Long start, @Param("pageSize") Integer pageSize);

    /**
     * 使用分页插件查询员工
     *
     * @return
     */
    @Select("select * from emp")
    List<Emp> paginatedSelect2();

    /**
     * 条件分页查询员工
     *
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    //条件要使用动态sql 所以要在xml中写sql  注意在resource中创建包用/不是.
    List<Emp> realPaginatedSelect(@Param("name") String name, @Param("gender") Short gender, @Param("begin") LocalDate begin, @Param("end") LocalDate end);
}
