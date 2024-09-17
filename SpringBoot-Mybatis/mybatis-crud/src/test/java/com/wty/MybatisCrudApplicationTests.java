package com.wty;

import com.wty.mapper.EmpMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisCrudApplicationTests {
    @Autowired
    private EmpMapper empMapper;

    //1
    @Test
    public void deleteTest() {
        empMapper.delete(17);
    }
}
