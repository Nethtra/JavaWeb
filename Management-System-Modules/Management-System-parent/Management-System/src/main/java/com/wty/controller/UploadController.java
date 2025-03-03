package com.wty.controller;

import com.aliyuncs.exceptions.ClientException;
import com.wty.pojo.Result;
import com.wty.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 王天一
 * @version 1.0
 */

@RestController
@Slf4j
public class UploadController {
    @Autowired//注入
    private AliOSSUtils aliOSSUtils;

    /**
     * 10上传文件接口
     *
     * @param image
     * @return
     * @throws IOException
     * @throws ClientException
     */
    //注意上传是单独的一个接口而不是做到insert中 在刚才的9新增员工中 insert操作是在上传之后，所以insert之前所有的字段已经完备
    //基本思路是后端先接收上传的文件，调用工具类AliOSSUtils然后存到oss并返回给前端url，用于图像的回显，等到提交表单数据时再一并insert到mysql中
    @PostMapping("/upload")//注意用的是post
    public Result upload(MultipartFile image) throws IOException, ClientException {
        String url = aliOSSUtils.upload(image);
        log.info("上传图片的url{}，文件名[}", url,image.getOriginalFilename());
        return Result.success(url);
    }//去前端连掉测试可以看到上传完图片能立即回显 创建完员工也能看到图片了 表中也有image的连接
    // 所以新增员工的需求其实是用了两个接口

}
