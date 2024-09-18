package com.wty.mapper;

import com.wty.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author 王天一
 * @version 1.0
 */
@Mapper
public interface EmpMapper {
    //1根据id删除员工数据
    //注意sql语句不能写死 而是要根据前端传递的id删除
    //要使用mybatis中提供的参数占位符#{}  占位符最后会成为? 让sql成为预编译sql 防止sql注入并提高性能
    //其实增删改语句都是有返回值的  返回此次操作影响的记录数  但是可以声明为void
    //查看mybatis日志
    @Delete("delete from emp where id=#{id}")
    public void delete(Integer id);
//    public int delete(Integer id);

    //2新增员工
    //因为id自增所以不用写，密码的话这不是注册账号
    //insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time)
    //values ('tom','汤姆',1,'1.jjj',1,'1970-1-1',1,now(),now())
    //这里也不能写死  而是前端传递的参数，可以封装到一个pojo对象中  在#{}中写**对象的属性名** 不是表中的字段名

    //如果插入后要使用到插入条目的主键  需要加这个注解  主键会封装回对象中
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{image},#{job},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    public void insert(Emp emp);

    //3更新员工信息
    //注意占位符中写的是属性名
    @Update("update emp set username=#{username},name=#{name},gender=#{gender},image=#{image},job=#{job}," +
            "dept_id=#{deptId},entrydate=#{entryDate},update_time=#{updateTime} where id=#{id}   ")
    public void update(Emp emp);

    //4根据id查询员工信息
    //可以看到有几个字段显示null  deptId=null, createTime=null, updateTime=null
    //因为表中字段与类的属性名没有对应 Column使用下划线 而Property使用驼峰命名 导致不能封装成功
    //发现一个问题 entryDate也没有对应上  但是封装成功了 所以意思是不区分大小写？
    @Select("select * from emp where id = #{id}")
    public Emp selectById(Integer id);
    //解决方法1给没有对应的字段起别名为驼峰命名
//    @Select("select username,password, name, gender, image, job, entrydate, " +
//            "dept_id deptId, create_time createTime, update_time updateTime from emp where id = #{id}")
//    public Emp selectById(Integer id);

    //解决方法2使用注解
//    @Results({
//            @Result(column = "dept_id", property = "deptId"),
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })
//    @Select("select * from emp where id = #{id}")
//    public Emp selectById(Integer id);

    //3开启mybatis自动驼峰命名映射  会自动将 a_column转化为aColumn
    //在properties配置文件中  mybatis.configuration.map-underscore-to-camel-case=true
    //即可继续使用原方法


    //5条件查询员工信息
    //查询名字中含张，男性，入职日期在2010-1-1到2020-1-1之间的员工信息，并根据update_time降序
    //select * from emp where name like '%张% ' and gender=1 and entrydate between '2010-1-1' and '2020-1-1'
    //因为参数也不好封装为对象，所以直接传
    //注意 **#{}不能出现在字符串中间**   所以只能使用${}拼接符号  不会生成预编译sql
    //但会产生sql注入问题  所以使用sql的concat函数
//    @Select("select * from emp where name like '%${name}% ' and gender=#{gender} and entrydate between #{begin} and #{end} ")
    //这里还有一个问题 如果接口中有多个参数的话，编译器不会保留参数的名字，会变成默认名字arg1 arg2等(只有一个的话不会有问题)，向sql中赋值时需要使用@param来注解 不然会出现BindingException
    // 但是springboot2.几版本应该已经默认解决此问题  不知道是不是阿里脚手架的原因 不行  还是得用注解
    //@param中对应sql中的名


//    @Select("select * from emp where name like concat('%',#{name},'%') and gender=#{gender} and entrydate between #{begin} and #{end} order by update_time desc ")
//    public List<Emp> selectByConditions(@Param("name") String name, @Param("gender") Short gender,
//                                        @Param("begin") LocalDate begin, @Param("end") LocalDate end);


    //6xml方式配置sql（上面都是注解方式）  即将sql语句写在xml中
    //注意xml文件定义的规范
    public List<Emp> selectByConditions(@Param("name") String name, @Param("gender") Short gender,
                                        @Param("begin") LocalDate begin, @Param("end") LocalDate end);

}
