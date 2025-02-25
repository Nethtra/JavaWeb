# dql
create database db02;
use db02;
# 准备测试数据
create table tb_emp
(
    id          int unsigned primary key auto_increment comment 'ID',
    username    varchar(20)      not null unique comment '用户名',
    password    varchar(32) default '123456' comment '密码',
    name        varchar(10)      not null comment '姓名',
    gender      tinyint unsigned not null comment '性别, 说明: 1 男, 2 女',
    image       varchar(300) comment '图像',
    job         tinyint unsigned comment '职位, 说明: 1 班主任,2 讲师, 3 学工主管, 4 教研主管',
    entrydate   date comment '入职时间',
    create_time datetime         not null comment '创建时间',
    update_time datetime         not null comment '修改时间'
) comment '员工表';

INSERT INTO tb_emp (id, username, password, name, gender, image, job, entrydate, create_time, update_time)
VALUES (1, 'jinyong', '123456', '金庸', 1, '1.jpg', 4, '2000-01-01', '2022-10-27 16:35:33', '2022-10-27 16:35:35'),
       (2, 'zhangwuji', '123456', '张无忌', 1, '2.jpg', 2, '2015-01-01', '2022-10-27 16:35:33', '2022-10-27 16:35:37'),
       (3, 'yangxiao', '123456', '杨逍', 1, '3.jpg', 2, '2008-05-01', '2022-10-27 16:35:33', '2022-10-27 16:35:39'),
       (4, 'weiyixiao', '123456', '韦一笑', 1, '4.jpg', 2, '2007-01-01', '2022-10-27 16:35:33', '2022-10-27 16:35:41'),
       (5, 'changyuchun', '123456', '常遇春', 1, '5.jpg', 2, '2012-12-05', '2022-10-27 16:35:33',
        '2022-10-27 16:35:43'),
       (6, 'xiaozhao', '123456', '小昭', 2, '6.jpg', 3, '2013-09-05', '2022-10-27 16:35:33', '2022-10-27 16:35:45'),
       (7, 'jixiaofu', '123456', '纪晓芙', 2, '7.jpg', 1, '2005-08-01', '2022-10-27 16:35:33', '2022-10-27 16:35:47'),
       (8, 'zhouzhiruo', '123456', '周芷若', 2, '8.jpg', 1, '2014-11-09', '2022-10-27 16:35:33', '2022-10-27 16:35:49'),
       (9, 'dingminjun', '123456', '丁敏君', 2, '9.jpg', 1, '2011-03-11', '2022-10-27 16:35:33', '2022-10-27 16:35:51'),
       (10, 'zhaomin', '123456', '赵敏', 2, '10.jpg', 1, '2013-09-05', '2022-10-27 16:35:33', '2022-10-27 16:35:53'),
       (11, 'luzhangke', '123456', '鹿杖客', 1, '11.jpg', 2, '2007-02-01', '2022-10-27 16:35:33',
        '2022-10-27 16:35:55'),
       (12, 'hebiweng', '123456', '鹤笔翁', 1, '12.jpg', 2, '2008-08-18', '2022-10-27 16:35:33', '2022-10-27 16:35:57'),
       (13, 'fangdongbai', '123456', '方东白', 1, '13.jpg', 1, '2012-11-01', '2022-10-27 16:35:33',
        '2022-10-27 16:35:59'),
       (14, 'zhangsanfeng', '123456', '张三丰', 1, '14.jpg', 2, '2002-08-01', '2022-10-27 16:35:33',
        '2022-10-27 16:36:01'),
       (15, 'yulianzhou', '123456', '俞莲舟', 1, '15.jpg', 2, '2011-05-01', '2022-10-27 16:35:33',
        '2022-10-27 16:36:03'),
       (16, 'songyuanqiao', '123456', '宋远桥', 1, '16.jpg', 2, '2010-01-01', '2022-10-27 16:35:33',
        '2022-10-27 16:36:05'),
       (17, 'chenyouliang', '12345678', '陈友谅', 1, '17.jpg', null, '2015-03-21', '2022-10-27 16:35:33',
        '2022-10-27 16:36:07'),
       (18, 'zhang1', '123456', '张一', 1, '2.jpg', 2, '2015-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:09'),
       (19, 'zhang2', '123456', '张二', 1, '2.jpg', 2, '2012-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:11'),
       (20, 'zhang3', '123456', '张三', 1, '2.jpg', 2, '2018-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:13'),
       (21, 'zhang4', '123456', '张四', 1, '2.jpg', 2, '2015-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:15'),
       (22, 'zhang5', '123456', '张五', 1, '2.jpg', 2, '2016-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:17'),
       (23, 'zhang6', '123456', '张六', 1, '2.jpg', 2, '2012-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:19'),
       (24, 'zhang7', '123456', '张七', 1, '2.jpg', 2, '2006-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:21'),
       (25, 'zhang8', '123456', '张八', 1, '2.jpg', 2, '2002-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:23'),
       (26, 'zhang9', '123456', '张九', 1, '2.jpg', 2, '2011-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:25'),
       (27, 'zhang10', '123456', '张十', 1, '2.jpg', 2, '2004-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:27'),
       (28, 'zhang11', '123456', '张十一', 1, '2.jpg', 2, '2007-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:29'),
       (29, 'zhang12', '123456', '张十二', 1, '2.jpg', 2, '2020-01-01', '2022-10-27 16:35:33', '2022-10-27 16:36:31');
# 基本查询
# 查询部分字段
select name,entrydate from tb_emp;
# 查询所有字段
select id, username, password, name, gender, image, job, entrydate, create_time, update_time from tb_emp;
select * from tb_emp;-- 这一种方式性能较低，尽量用上面的
# 查询时起别名
select name 姓名,entrydate 入职日期 from tb_emp;-- as可以省略
select name '姓 /名',entrydate 入职日期 from tb_emp;-- 如果别名中有特殊符号会引起语法冲突，可以用''
# 去重查询 distinct
select distinct job from tb_emp;-- 可以看到有一个null 因为有一个没有分配职位



# 条件查询  where
# 使用运算符来构造条件
# 单条件查询   为了方便先使用*
select * from tb_emp where name='杨逍';
select * from tb_emp where id<=5;
# 如果是null 用is或者is not 不用=
select * from tb_emp where job is null ;
select * from tb_emp where job is not null;
# 不等于有两种写法
select * from tb_emp where password!='123456';
select * from tb_emp where password<>'123456';
# between and 在范围之内  左边是最小值 右边是最大值
select * from tb_emp where entrydate>='2000-1-1'and entrydate<='2010-1-1';
select * from tb_emp where entrydate between '2000-1-1' and '2010-1-1';
# 多条件同时查询 之间用and或者&&连接
select * from tb_emp where entrydate between '2000-1-1' and '2010-1-1' and gender=2;
select * from tb_emp where entrydate between '2000-1-1' and '2010-1-1' && gender=2;
# 多条件任意一个成立  使用or连接 或者用in
select * from tb_emp where job=1 or job=2 or job=3;
select * from tb_emp where job in(1,2,3);
# 模糊匹配 like _匹配一个字符  %匹配多个字符包括0
select * from tb_emp where name like'__';-- 查两个字的
select * from tb_emp where name like '张%';-- 查姓张的




# 聚合函数
# 将一列数据进行纵向计算  注意只能写在select之后
# count 三种写法 字段 常量 * 统计数量  注意不会统计null
select count(id) from tb_emp;
select count(job) from tb_emp;
select count('a') from tb_emp;
select count(*) from tb_emp;
# max min
select max(entrydate) from tb_emp;
select min(entrydate) from tb_emp;
# avg
select avg(id) from tb_emp;
# sum
select sum(id) from tb_emp;




# 分组查询group by 分组字段 having 分组后条件过滤
# 只要使用group by select的就只能是聚合函数和分组字段
# 根据性别分组，查询男女数量
select gender,count(*) from tb_emp group by gender;
# *根据职位分组查询2015年以前入且职位人数大于2的职位  聚合函数的条件要在having判断
# 2015年这个条件在分组前，人数》2是分组后的筛选
select job,count(*) from tb_emp where entrydate<='2015-1-1' group by job having count(*)>2;
# where和having比较

# 排序查询 order by
# 如果有多个字段条件，则当第一个字段相同时才会根据第二个字段排序
select * from tb_emp order by entrydate asc;-- 升序
select * from tb_emp order by entrydate desc ;-- 降序
# 根据入职时间升序排序，如果入职时间相同根据更新时间降序排序
select * from tb_emp order by entrydate ,update_time desc;

# 分页查询
# limit 起始索引，查询数
# 索引从0开始     这个查询数就是每页的数量
# 查询第一页，每页五条
select * from tb_emp limit 0,5;
# 查询第二页，每页五条
select * from tb_emp limit 5,5;
# 查询第三页，每页五条
select * from tb_emp limit 10,5;
# 实际开发中前端传递过来的是页数，所以索引要自己计算
# 起始索引的计算方式(页数-1)*每页个数


# 案例：查询姓名中含张，性别为男，入职时间在00-1-1到15-12-31之间，的第一页数据，每页十条，根据最后修改时间倒叙排序
# 提取 条件 分组 顺序 分页 聚合函数 然后按关键字顺序写sql
select *
from tb_emp
where name like '%张%'
  and gender = 1
  and entrydate between '2000-1-1' and '2015-12-31'
order by update_time desc
limit 0,10;
# 1统计男女员工数目并转换展示  if流程控制函数 if(表达式,true,false)
select if(gender=1,'男性员工','女性员工') 性别,count(*) 总数 from tb_emp group by gender;
select gender,count(*) from tb_emp group by gender;# 与这个比较
# 2统计职位信息并转换展示  case流程控制函数  case 表达式 when 值 then
select case job
           when 1 then '班主任'
           when 2 then '讲师'
           when 3 then '学工主管'
           when 4 then '教研主管'
           else '未分配' end 职位, count(*) 总数
from tb_emp
group by job;