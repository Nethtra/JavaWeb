package com.wty.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 王天一
 * @version 1.0
 */
//数据库表采用下划线  实体类中用驼峰命名
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Short gender;
    private String image;
    private Short job;
    private LocalDate entryDate;//注意这个是只有日期 下面要具体时间
    private Integer deptId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
