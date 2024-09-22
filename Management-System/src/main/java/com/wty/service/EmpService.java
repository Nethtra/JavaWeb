package com.wty.service;

import com.wty.pojo.Emp;
import com.wty.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * @author 王天一
 * @version 1.0
 */
public interface EmpService {
    /**
     * 分页查询员工
     *
     * @param page
     * @param pageSize
     * @return
     */
    PageBean paginatedSelect(Integer page, Integer pageSize);

    /**
     * 条件分页查询员工
     *
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @param page
     * @param pageSize
     * @return
     */

    PageBean realPaginatedSelect(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

    /**
     * 根据id删除员工
     *
     * @param ids
     */
    void deleteById(List<Integer> ids);

    /**
     * 新增员工
     *
     * @param emp
     */
    void createEmp(Emp emp);

    /**
     * 根据id查询员工信息
     *
     * @param id
     * @return
     */
    Emp selectById(Integer id);

    /**
     * 根据id修改员工信息
     *
     * @param emp
     */
    void updateById(Emp emp);

    /**
     * 用户登陆
     *
     * @param emp
     * @return
     */
    Emp login(Emp emp);
}
