package com.wty.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 王天一
 * @version 1.0
 */
//配置属性类
//用来简化@Value 当参数很多时使用   将配置文件中的属性注入到此类中的属性中，然后使之成为bean，在用到时直接注入然后调用get方法
@Data//
@Component//
@ConfigurationProperties(prefix = "aliyun.oss")//prefix 前缀
public class AliOSSProperties {
    private String endPoint;
    private String bucketName;
}
