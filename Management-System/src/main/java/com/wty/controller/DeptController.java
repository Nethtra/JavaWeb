package com.wty.controller;

import com.wty.anno.Log;
import com.wty.pojo.Dept;
import com.wty.pojo.Result;
import com.wty.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author 王天一
 * @version 1.0
 */
@Slf4j//使用此注解替代获取日志记录对象的代码  由lombok提供
@RestController
@RequestMapping("/depts")//提取路径公共的部分  可以简化书写路径
public class DeptController {
    //    private static Logger log = (Logger) LoggerFactory.getLogger(DeptController.class);//获取日志记录对象
    //注入对象
    @Autowired//一定不要忘了
    private DeptService deptService;

    /**
     * 1查询所有部门信息  用于点击左侧栏后展示部门的数据
     *
     * @return
     */
    //method指定请求方式只能为GET   RequestMethod是一个枚举类  简写为GetMapping  其他的方法也是一样
    //@RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping//("/depts")
    public Result listDept() {
        log.info("查询所有部门信息");//不要用sout记录日志
        List<Dept> depts = deptService.listDept();
        return Result.success(depts);
    }//1apifox中发起请求测试
    //2前后端联调测试：启动文件夹中的nginx 然后访问localhost:90
    //nginx会把请求转给后端tomcat

    /**
     * 2根据id删除部门信息
     *
     * @param id
     * @return
     */
    @Log
    @DeleteMapping("/{id}")//路径参数
    public Result deleteById(@PathVariable Integer id) {
        log.info("删除部门信息{}", id);//{}参数占位符，会被,之后的替换
        deptService.deleteById(id);
        return Result.success();
    }//分别用apifox和网页测试

    /**
     * 3新增部门  前端传递的是json的部门名
     *
     * @param dept
     * @return
     */
    //这里有一个问题 如果删了部门再添加的话主键是unique增长的 但前端显示的部门还是按顺序的 删除的时候要用主键id 这就对不上了
    @Log
    @PostMapping//("/depts")//使用RequestBody将json封装到对象中
    public Result createDept(@RequestBody Dept dept) {
        deptService.createDept(dept);
        return Result.success();
    }//分别用apifox和网页测试

    //修改部门分为两步  1先数据回显，就是查询部门   2然后update修改

    /**
     * 4根据id查询部门信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id) {
        Dept dept = deptService.selectById(id);
        return Result.success(dept);
    }

    /**
     * 5根据id修改部门  前端传递了id和部门名称
     *
     * @param dept
     * @return
     */
    @Log
    @PutMapping//("/depts")
    public Result updateById(@RequestBody Dept dept) {
        deptService.updateById(dept);
        return Result.success();
    }
}
