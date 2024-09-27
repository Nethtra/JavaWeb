package com.wty.mapper;

import com.wty.pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 王天一
 * @version 1.0
 */
@Mapper
public interface DeptLogMapper {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Insert("insert into dept_log(create_time,description) values (#{createTime},#{description})")
    void insertLog(DeptLog deptLog);
}
