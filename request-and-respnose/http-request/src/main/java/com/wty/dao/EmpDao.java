package com.wty.dao;

import com.wty.pojo.Emp;

import java.util.List;

/**
 * @author 王天一
 * @version 1.0
 */
public interface EmpDao {
    //从xml文件中获取员工集合
    public List<Emp> listEmp();
}
