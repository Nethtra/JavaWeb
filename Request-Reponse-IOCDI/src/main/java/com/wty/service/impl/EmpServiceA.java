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
//@Component//将当前类交给容器管理，使成为容器中的bean
public class EmpServiceA implements EmpService {
    //需要先newDao对象
    //注意是接口类型接收
//    private EmpDao empDao = new EmpDaoA();
    @Autowired//运行时容器会提供该类型的bean对象并赋值
    private EmpDao empDao;

    @Override
    public List<Emp> listEmp() {
        List<Emp> empList = empDao.listEmp();
        empList.stream().forEach(emp -> {
            String gender = emp.getGender();
            if (gender.equals("1"))
                emp.setGender("男");
            else if (gender.equals("2"))
                emp.setGender("女");

            String job = emp.getJob();
            if (gender.equals("1"))
                emp.setJob("讲师");
            else if (gender.equals("2"))
                emp.setJob("班主任");
            else if (gender.equals("3"))
                emp.setJob("校长");

        });
        return empList;
    }
}
