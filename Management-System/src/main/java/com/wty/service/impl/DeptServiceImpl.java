package com.wty.service.impl;

import com.wty.mapper.DeptLogMapper;
import com.wty.mapper.DeptMapper;
import com.wty.mapper.EmpMapper;
import com.wty.pojo.Dept;
import com.wty.pojo.DeptLog;
import com.wty.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogMapper deptLogMapper;

    @Override
    public List<Dept> listDept() {

        return deptMapper.listDept();
    }

    //16Spring事务管理
    //这个之前写的太草率了，删除部门后应该部门下的员工也一并删除
    //所以在EmpMapper中新增方法 根据deptId删除员工 然后在这里调用
    //多次增删改的操作需要使用事务来保证数据的安全性
    //Spring中提供@Transactional注解   将当前方法交由Spring管理事务  出现异常会自动回滚
    //注意还要在.yml中开启Spring事务管理日志
    //*两个属性 rollbackFor和propagation
    @Override
    @Transactional(rollbackFor = Exception.class)//默认出现RuntimeException才回滚  rollbackFor属性指定回滚的层级   全部异常都回滚
    public void deleteById(Integer id) {
        try {
            deptMapper.deleteById(id);
            int i = 1 / 0;//模拟执行过程中出现异常
            empMapper.deleteByDeptId(id);
        } finally {
            //不管怎样最后都要记录日志  即要插入数据
            //propagation属性 传播行为
            //这里要建新表 dept_log  新pojo DeptLog   新 service DeptLogService  新mapper DeptLogMapper
            //insertLog   propagation属性设置为需要新事务  因为默认调用到是加入当前事务  如果失败了  记录日志的操作也会回滚 所以必须开新事务
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("删除了" + id + "号部门的员工和部门数据");
            deptLogMapper.insertLog(deptLog);
        }
    }//在前端尝试删除部门可以在日志中看到数据被保护rollback

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
