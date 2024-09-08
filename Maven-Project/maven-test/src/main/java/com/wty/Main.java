package com.wty;

/**
 * @author 王天一
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("hello maven");
        //在右侧maven面板Lifecycle选择生命周期指令
        //编译后会在target目录存放字节码文件
        //运行package 可以看到也会执行前面的test  也可以主动选择跳过
        //compile和package后的文件都会放在target目录下  clean会把target清理掉 install会将jar包放到本地仓库
    }
}
