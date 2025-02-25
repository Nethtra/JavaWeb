package com.wty.controller;

import com.wty.pojo.Emp;
import com.wty.pojo.Result;
import com.wty.service.EmpService;
import com.wty.service.impl.EmpServiceA;
import com.wty.utils.XmlParserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 王天一
 * @version 1.0
 */
//请求响应案例：从xml获取员工信息 并返回统一的响应结果  在前端页面渲染展示
//首先引入dom4j依赖（用于解析xml文件，员工信息存放在xml文件中）
//新建XmlParserUtils工具类 （用来将xml中的员工信息提取出来并放到实体对象中）并在pojo中新建实体类Emp来存放员工对象
//建立前端静态页面(放在resources的static中 或者在resources中新建public) 引入emp.xml
//然后开始编写controller方法处理请求响应数据
@RestController
public class EmpController {
    /*    //查看前端emp.html可以发现 在钩子方法mounted中发起异步请求 请求路径是listEmp 且没有携带参数
        @RequestMapping("/listEmp")
        public Result listEmp() {
            //总体思路
            //1加载并解析xml
            //2转换数据格式12男女
            //3响应回前端


            //1获取xml文件路径
            String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
            System.out.println(file);
            List<Emp> empList = XmlParserUtils.parse(file, Emp.class);//封装到集合中
            //2转换数据格式
            //试了试增强for也可以
    //        for (Object emp :empList) {
    //            String gender = ((Emp) emp).getGender();
    //            if (gender.equals("1"))
    //                ((Emp) emp).setGender("男");
    //            else if (gender.equals("2"))
    //                ((Emp) emp).setGender("女");
    //        }

            empList.stream().forEach(emp -> {
                String gender = emp.getGender();
                if (gender.equals("1"))
                    emp.setGender("男");
                else if (gender.equals("2"))
                    emp.setGender("女");

                String job = emp.getJob();
                if (job.equals("1"))
                    emp.setJob("讲师");
                else if (job.equals("2"))
                    emp.setJob("班主任");
                else if (job.equals("3"))
                    emp.setJob("校长");

            });
            //3
            return Result.success(empList);
            //经过controller的操作数据就会以json的格式响应给前端
            //注意最后在浏览器访问的是localhost:8080/emp.html而不是/empList
            //因为应该是由前端访问/empList来请求后端
            //浏览器访问html 通过html来调起请求后端并返回数据给前端渲染展示

            //https://www.cnblogs.com/chengxuxiaoyuan/p/17689586.html
            //stream 是 Java 8 引入的一个方法，用于处理集合（如 List, Set 等）中的元素。
            //它提供了一种声明性的方法来处理数据，类似于 SQL 语句。
            //stream 方法返回一个 Stream 对象，允许你使用一系列的中间操作
            //（如 filter, map, sorted 等）和终端操作（如 forEach, collect, reduce 等）来处理数据。
            //forEach 是 Java 8 引入的一个方法，用于对 Stream 中的每个元素执行指定的操作。
            // 它是一个终端操作，意味着一旦调用，流的元素将被处理，并且流将不再可用。
            // 在你的代码中，forEach 用于遍历 empList 中的每个 Emp 对象，并根据其 gender 和 job 属性的值进行转换。



        }*/
    //上面的代码将数据访问  业务逻辑  请求响应全写在一起 代码耦合性高复用性差难以维护
    //使用三层架构来分层开发  controller  service   dao
    //service和dao层采用面向接口开发
    //controller调用service  service调用dao
    //dao获取数据然后返回给service进行逻辑处理 service将逻辑处理后的数据再返回给controller
    //最后由controller响应给前端
//    private EmpService empService = new EmpServiceA();
    @Autowired//运行时容器会提供该类型的bean对象并赋值
    private EmpService empService;

    @RequestMapping("/listEmp")
    public Result listEmp() {
        List<Emp> empList = empService.listEmp();
        return Result.success(empList);
    }

    //当使用三层架构后，Service中仍有new Dao的代码 Controller中仍有new Service的代码，
//即各层之间仍是耦合的 会相互影响，例如当Service中的类要改名时，Controller也要跟着更改new的类名
//使用ioc控制反转和di依赖注入来进行分层解耦
//先删除new对象的代码
//1Service和Dao层的实现类交给容器管理，使类成为容器中的bean 而不再由程序自己new对象 使用@Component注解 实现控制反转
//2Controller和Service在运行时注入需要的对象，Controller和Service中的其他层对象使用@Autowired注解 实现依赖注入
//比如当需要更改业务逻辑时，只要更改Service中交由容器管理的类

    //@Compotent的衍生注解 @Controller  @Service  @Repository 分别用来标注Controller Service Dao层的控制反转
//使用@Compotent和后面的都行 建议使用衍生注解
}













