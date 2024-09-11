package com.wty.dao.impl;


import com.wty.pojo.Emp;
import com.wty.utils.XmlParserUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 王天一
 * @version 1.0
 */
@Component
public class EmpDaoA implements com.wty.dao.EmpDao {
    @Override
    public List<Emp> listEmp() {
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        System.out.println(file);
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);//封装到集合中
        return empList;
    }
}
