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
//案例  从emp.xml中获取员工数据，封装到Emp中，返回统一的响应结果Result，然后在页面渲染展示
//1在pom.xml中引入dom4j依赖用于解析xml文件
//2引入XMLParserUtils工具类   员工实体类Emp   emp.xml
//3引入前端页面到resources/static目录下（springboot项目的静态资源（html js css等前端资源）一般存放在classpath:/static classpath:/public下）
//4编写controller程序 处理与响应数据
/*
@RestController
public class EmpController {
    @RequestMapping("/listEmp")//从前端html得知路径
    public Result list() {

        //数据访问
        //1加载并解析emp.xml 将数据封装到emp
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        System.out.println(file);
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);

        //逻辑处理
        //2数据转换 性别1男2女 职位1讲师 2班主任 3就业指导
        empList.stream().forEach(emp -> {
            String gender = emp.getGender();
            if ("1".equals(gender)) {
                emp.setGender("男");
            } else if ("2".equals(gender)) {
                emp.setGender("女");
            }
            String job = emp.getJob();
            if ("1".equals(job)) {
                emp.setJob("讲师");
            } else if ("2".equals(job)) {
                emp.setJob("班主任");
            } else if ("3".equals(job)) {
                emp.setJob("就业指导");
            }
        });

        //接收请求响应数据
        //3给前端响应Result
        return Result.success(empList);
    }
    //可以debug后使用apifox发送请求 一步步看empList的的数据 然后resume 可以看到返回给前端的数据
    //或者直接运行 使用浏览器访问http://localhost:8080/emp.html即可看到页面
    //访问/empList只能看到返回给前端的json数据，所以真正访问还是得访问html，在emp.html可以看到使用了axios异步请求，在加载完成后请求了后端的数据，路径就是/empList
}
*/


//回看上面的代码，所有操作：数据访问 逻辑处理 接受请求响应数据都写在同一方法中  不便于修改维护
//使用三层架构
//Controller层负责接收请求、响应数据
//Service层负责对数据的逻辑处理
//Dao层负责对数据的访问  Dao层使用面向接口编程来应对不同的数据来源
//分层后每层遵循了单一职责原则，只干一件事，代码复用性强，便于程序的维护与后期扩展
/*
@RestController
public class EmpController {
    private EmpService empService = new EmpServiceA();

    @RequestMapping("/listEmp")//从前端html得知路径
    public Result list() {
        //调用Service获取经过逻辑处理后的数据
        List<Emp> empList = empService.listEmp();
        //接收请求响应数据
        //3给前端响应Result
        return Result.success(empList);
    }
}
*/
//修改后的代码分为三层架构  运行后可以看到效果相同
//controller调用service  service调用dao   dao获取数据然后返回给service进行逻辑处理 service将逻辑处理后的数据再返回给controller
//最后由controller响应给前端


//当使用三层架构后，Service中仍有new Dao的代码 Controller中仍有new Service的代码，
//即各层之间仍是耦合的，例如当Service中的类要改名时，Controller也要跟着更改new的类名
//使用ioc控制反转和di依赖注入来进行分层解耦
//1Service和Dao层的实现类交给容器管理，使类成为容器中的bean 使用@Component注解 实现控制反转
//2Controller和Service在运行时注入需要的对象，Controller和Service中的其他层对象使用@Autowired注解 实现依赖注入
@RestController
public class EmpController {
    @Autowired//运行时容器会提供该类型的bean对象并赋值
    private EmpService empService;

    @RequestMapping("/listEmp")//从前端html得知路径
    public Result list() {
        //调用Service获取经过逻辑处理后的数据
        List<Emp> empList = empService.listEmp();
        //接收请求响应数据
        //3给前端响应Result
        return Result.success(empList);
    }
}
//例如Service中有两个不同业务逻辑时，可以使用@Compotent来决定将谁加入容器管理 从而控制Controller使用哪一个业务逻辑
//注意 当Service中的AB都使用@Compotent时会报错，即同类型的bean有多个，注入时不知道注入哪一个 Autowired按照类型注入
//解决方法看飞书
//切换逻辑时只需要改@Compotent注解即可

//@Compotent的衍生注解 @Controller  @Service  @Repository 分别用来标注Controller Service Dao层的控制反转
//使用@Compotent和后面的都行 建议使用衍生注解