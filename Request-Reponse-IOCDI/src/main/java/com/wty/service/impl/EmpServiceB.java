package com.wty.service.impl;

import com.wty.dao.EmpDao;
import com.wty.pojo.Emp;
import com.wty.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 王天一
 * @version 1.0
 */
@Component
public class EmpServiceB implements EmpService {
    @Autowired
    private EmpDao empDao;

    @Override
    public List<Emp> listEmp() {
        List<Emp> empList = empDao.listEmp();
        empList.stream().forEach(emp -> {
            String gender = emp.getGender();
            if (gender.equals("1"))
                emp.setGender("男的");
            else if (gender.equals("2"))
                emp.setGender("女的");

            String job = emp.getJob();
            if (job.equals("1"))
                emp.setJob("讲师");
            else if (job.equals("2"))
                emp.setJob("班主任");
            else if (job.equals("3"))
                emp.setJob("校长");

        });
        return empList;
    }
}
