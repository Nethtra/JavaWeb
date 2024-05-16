package com.wty.dao.impl;

import com.wty.dao.EmpDao;
import com.wty.pojo.Emp;
import com.wty.utils.XmlParserUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 王天一
 * @version 1.0
 */
/*
public class EmpDaoA implements EmpDao {
    @Override
    public List<Emp> listEmp() {
        //数据访问
        //1加载并解析emp.xml 将数据封装到emp
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        System.out.println(file);
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);
        return empList;
    }
}
*/
@Component//将当前类交给容器管理，使成为容器中的bean
public class EmpDaoA implements EmpDao {
    @Override
    public List<Emp> listEmp() {
        //数据访问
        //1加载并解析emp.xml 将数据封装到emp
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        System.out.println(file);
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);
        return empList;
    }
}
