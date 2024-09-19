package com.wty.service;

import com.wty.pojo.PageBean;

import java.time.LocalDate;

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
}
