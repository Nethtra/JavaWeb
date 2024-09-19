package com.wty.service.impl;

import com.wty.mapper.DeptMapper;
import com.wty.pojo.Dept;
import com.wty.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 王天一
 * @version 1.0
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> listDept() {

        return deptMapper.listDept();
    }

    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public void createDept(Dept dept) {
        //补充属性   创建时间和更新时间
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.createDept(dept);
    }

    @Override
    public Dept selectById(Integer id) {
        return deptMapper.selectById(id);
    }

    @Override
    public void updateById(Dept dept) {
        //补充属性
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateById(dept);
    }
}
