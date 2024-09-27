package com.wty.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 王天一
 * @version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
//属性名要与接口文档对应
public class PageBean {
    private Long total;//总记录数
    private List rows;//当前页的数据列表  list不写泛型，因为在实际开发中，传的不一定是只有emp,还可以是其他类，不能写死
}
