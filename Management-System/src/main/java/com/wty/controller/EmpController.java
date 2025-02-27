package com.wty.controller;

import com.wty.anno.Log;
import com.wty.pojo.Emp;
import com.wty.pojo.PageBean;
import com.wty.pojo.Result;
import com.wty.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
     * 7条件分页查询
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

    /**
     * 8根据id删除员工
     *
     * @param ids
     * @return
     */
    //注意这个删除既可以删除多个员工也可以删除一个员工   即做成一个接口，不用做成两个
    //动态sql
    @Log
    @DeleteMapping("/emps/{ids}")//注意路径参数注解
    public Result deleteById(@PathVariable List<Integer> ids) {
        log.info("删除的员工id：{}", ids);
        empService.deleteById(ids);
        return Result.success();

    }

    /**
     * 9新增员工
     *
     * @param emp
     * @return
     */
    @Log
    //密码用默认的  主键自增  这两个都不用
    @PostMapping("/emps")//请求体中的如果是json要封装到对象中  使用@RequestBody注解
    public Result createEmp(@RequestBody Emp emp) {
        empService.createEmp(emp);
        return Result.success();

    }

    //修改员工信息  也是分为两个接口  先根据id查询，用于数据回显 再update

    /**
     * 11根据id查询员工信息
     *
     * @param id
     * @return
     */
    @GetMapping("/emps/{id}")//路径参数
    public Result selectById(@PathVariable Integer id) {
        log.info("员工的id{}", id);
        Emp emp = empService.selectById(id);
        return Result.success(emp);
    }

    /**
     * 12根据id修改员工信息
     *
     * @param emp
     * @return
     */
    //使用动态sql
    @Log
    @PutMapping("/emps")
    public Result updateById(@RequestBody Emp emp) {
        log.info("修改员工信息{}", emp.getId());
        empService.updateById(emp);
        return Result.success();
    }
}
