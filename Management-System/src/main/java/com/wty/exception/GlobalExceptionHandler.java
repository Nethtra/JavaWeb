package com.wty.exception;

import com.wty.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 王天一
 * @version 1.0
 * 15全局异常处理器
 */
//例如添加相同名的部门  f12可以发现添加失败  但前端没有任何提示  因为response的数据格式不是规定的Result
//所以要对异常进行统一的处理  然后返回统一的Result
@RestControllerAdvice//=ControllerAdvice+ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)//指定要捕获的异常  这里捕获所有
    public Result ex(Exception ex) {
        ex.printStackTrace();//打印异常堆栈信息
        return Result.error("操作失败！请联系管理员");//返回给前端的提示
    }
}//使用全局异常处理器后前端再出问题就会提示信息
