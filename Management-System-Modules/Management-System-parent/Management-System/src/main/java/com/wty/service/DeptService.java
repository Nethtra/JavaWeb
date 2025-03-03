package com.wty.service;

import com.wty.pojo.Dept;

import java.util.List;

/**
 * @author 王天一
 * @version 1.0
 */
public interface DeptService {
    /**
     * 查询所有部门信息
     *
     * @return
     */
    List<Dept> listDept();

    /**
     * 根据id删除部门信息
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 新增部门
     *
     * @param dept
     */
    void createDept(Dept dept);

    /**
     * 根据id查询部门信息
     *
     * @param id
     * @return
     */
    Dept selectById(Integer id);

    /**
     * 根据id修改部门信息
     *
     * @param dept
     */
    void updateById(Dept dept);
}
