package com.wty.service.impl;

import com.wty.dao.EmpDao;
import com.wty.dao.impl.EmpDaoA;
import com.wty.pojo.Emp;
import com.wty.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 王天一
 * @version 1.0
 */
/*
public class EmpServiceA implements EmpService {
    //先创建EmpDao对象
    private EmpDao empDao = new EmpDaoA();

    @Override
    public List<Emp> listEmp() {
        //调用Dao层对象获取数据
        List<Emp> empList = empDao.listEmp();

        //逻辑处理
        //2数据转换 性别1男2女 职位1讲师 2班主任 3就业指导
        empList.stream().forEach(emp -> {
            String gender = emp.getGender();
            if ("1".equals(gender)) {
                emp.setGender("男");
            } else if ("2".equals(gender)) {
                emp.setGender("女");
            }
            String job = emp.getJob();
            if ("1".equals(job)) {
                emp.setJob("讲师");
            } else if ("2".equals(job)) {
                emp.setJob("班主任");
            } else if ("3".equals(job)) {
                emp.setJob("就业指导");
            }
        });
        return empList;
    }
}
*/
//@Component//将当前类交给容器管理，使成为容器中的bean
public class EmpServiceA implements EmpService {
    //先创建EmpDao对象
    @Autowired//运行时容器会提供该类型的bean对象并赋值
    private EmpDao empDao;

    @Override
    public List<Emp> listEmp() {
        //调用Dao层对象获取数据
        List<Emp> empList = empDao.listEmp();

        //逻辑处理
        //2数据转换 性别1男2女 职位1讲师 2班主任 3就业指导
        empList.stream().forEach(emp -> {
            String gender = emp.getGender();
            if ("1".equals(gender)) {
                emp.setGender("男");
            } else if ("2".equals(gender)) {
                emp.setGender("女");
            }
            String job = emp.getJob();
            if ("1".equals(job)) {
                emp.setJob("讲师");
            } else if ("2".equals(job)) {
                emp.setJob("班主任");
            } else if ("3".equals(job)) {
                emp.setJob("就业指导");
            }
        });
        return empList;
    }
}
