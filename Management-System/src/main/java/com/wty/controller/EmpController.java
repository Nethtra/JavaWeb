package com.wty.controller;

import com.wty.pojo.PageBean;
import com.wty.pojo.Result;
import com.wty.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * @author 王天一
 * @version 1.0
 */
@Slf4j
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    //分页查询   前端要传递的是页码和每页的记录数  后端返回总记录数和当前页的数据列表
    //要返回两个类型的值 考虑把他们封装到实体类或者使用map集合 这里封装到实体类PageBean
    //mapper中定义查询总数和分页查询的sql   在service中进行逻辑封装成PageBean   然后在controller中返回

    /**
     * 6分页查询员工
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/emps1")
    //@RequestParam设置前端没有传递时的默认值  controller的参数名与前端传递的一样才能正确接收
    public Result paginatedSelect(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询页码{}，每页记录{}", page, pageSize);
        PageBean pageBean = empService.paginatedSelect(page, pageSize);
        return Result.success(pageBean);
    }//apifox和网页验证

    /**
     * 7分页条件查询
     *
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @param page
     * @param pageSize
     * @return
     */
    //其实上面真正要讲的是这个，只不过先讲的分页  所以先把上面的改为emps1
    @GetMapping("/emps")
    public Result realPaginatedSelect(String name, Short gender,
                                      //注意这里的日期格式化，前端传递参数的时候月和日要补0，要不会出转换错误
                                      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                                      @RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer pageSize) {

        log.info("分页查询页码{}，每页记录{}，姓名{}，性别{}", page, pageSize, name, gender);
        PageBean pageBean = empService.realPaginatedSelect(name, gender, begin, end, page, pageSize);
        return Result.success(pageBean);

    }

}
