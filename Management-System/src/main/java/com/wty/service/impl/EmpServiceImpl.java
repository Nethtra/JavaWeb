package com.wty.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wty.mapper.EmpMapper;
import com.wty.pojo.Emp;
import com.wty.pojo.PageBean;
import com.wty.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 王天一
 * @version 1.0
 */
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean paginatedSelect(Integer page, Integer pageSize) {
//        Long count = empMapper.count();
//        //注意计算起始索引
//        List<Emp> empList = empMapper.paginatedSelect((long) (page - 1) * pageSize, pageSize);
//        //在service中封装PageBean
//        return new PageBean(count, empList);
//        //所以service就是纯业务逻辑  计算sql起始索引和封装成PageBean都是在这里完成  controller只要接收响应 mapper只要查数据库


        //使用分页插件简化（也不是很简）  先在pom.xml中引入依赖
        PageHelper.startPage(page, pageSize);//设置参数
        List<Emp> empList = empMapper.paginatedSelect2();//正常查询
        Page<Emp> pages = (Page<Emp>) empList;//转换为Page类型  才能用Page的方法
        return new PageBean(pages.getTotal(), pages.getResult());//封装为PageBean
        //注意controller中的代码并不用更改，因为只是逻辑的变化
        //mapper中要改成正常select * from emp 插件会自动执行分页查询

    }

    @Override
    public PageBean realPaginatedSelect(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);//设置参数
        List<Emp> empList = empMapper.realPaginatedSelect(name, gender, begin, end);//正常查询
        Page<Emp> pages = (Page<Emp>) empList;//转换为Page类型  才能用Page的方法
        return new PageBean(pages.getTotal(), pages.getResult());//封装为PageBean返回
    }

    @Override
    public void deleteById(List<Integer> ids) {
        empMapper.deleteById(ids);
    }

    @Override
    public void createEmp(Emp emp) {
        //补充数据
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.createEmp(emp);
    }

    @Override
    public Emp selectById(Integer id) {
        return empMapper.selectById(id);
    }

    @Override
    public void updateById(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());//更新修改时间
        empMapper.updateById(emp);

    }

    @Override
    public Emp login(Emp emp) {
        Emp e = empMapper.selectByUsernameAndPsw(emp);
        return e;
    }
}
