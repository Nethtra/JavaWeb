package com.wty;

import com.wty.mapper.EmpMapper;
import com.wty.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class MybatisCrudApplicationTests {
    @Autowired
    private EmpMapper empMapper;

    //1
    @Test
    public void deleteTest() {
        empMapper.delete(17);
    }

    //2
    @Test
    public void insertTest() {
        //先new一个对象
        Emp emp = new Emp();
        emp.setUsername("tom1");
        emp.setName("汤姆");
        emp.setGender((short) 1);
        emp.setImage("1.txt");
        emp.setJob((short) 1);
        emp.setDeptId(1);
        emp.setEntryDate(LocalDate.of(1900, 1, 1));
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.insert(emp);
        System.out.println(emp.getId());
    }

    //3
    @Test
    public void updateTest() {
        Emp emp = new Emp();
        emp.setId(19);
        emp.setUsername("tom20");
        emp.setName("汤姆");
        emp.setGender((short) 1);
        emp.setImage("20.txt");
        emp.setJob((short) 1);
        emp.setDeptId(1);
        emp.setEntryDate(LocalDate.of(1900, 1, 1));
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Test
    public void selectByIdTest() {
        Emp emp = empMapper.selectById(1);
        System.out.println(emp);
    }

    @Test
    public void selectByConditionsTest() {
        List<Emp> empList = empMapper.selectByConditions("张", (short) 1, LocalDate.of(2010, 1, 1), LocalDate.of(2020, 1, 1));
        System.out.println(empList);
    }

}
