create database db04;
use db04;
# 多表查询
# 数据准备
-- 部门管理
create table tb_dept
(
    id          int unsigned primary key auto_increment comment '主键ID',
    name        varchar(10) not null unique comment '部门名称',
    create_time datetime    not null comment '创建时间',
    update_time datetime    not null comment '修改时间'
) comment '部门表';

insert into tb_dept (id, name, create_time, update_time)
values (1, '学工部', now(), now()),
       (2, '教研部', now(), now()),
       (3, '咨询部', now(), now()),
       (4, '就业部', now(), now()),
       (5, '人事部', now(), now());


-- 员工管理
create table tb_emp
(
    id          int unsigned primary key auto_increment comment 'ID',
    username    varchar(20)      not null unique comment '用户名',
    password    varchar(32) default '123456' comment '密码',
    name        varchar(10)      not null comment '姓名',
    gender      tinyint unsigned not null comment '性别, 说明: 1 男, 2 女',
    image       varchar(300) comment '图像',
    job         tinyint unsigned comment '职位, 说明: 1 班主任,2 讲师, 3 学工主管, 4 教研主管, 5 咨询师',
    entrydate   date comment '入职时间',
    dept_id     int unsigned comment '部门ID',
    create_time datetime         not null comment '创建时间',
    update_time datetime         not null comment '修改时间'
) comment '员工表';

INSERT INTO tb_emp
(id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time)
VALUES (1, 'jinyong', '123456', '金庸', 1, '1.jpg', 4, '2000-01-01', 2, now(), now()),
       (2, 'zhangwuji', '123456', '张无忌', 1, '2.jpg', 2, '2015-01-01', 2, now(), now()),
       (3, 'yangxiao', '123456', '杨逍', 1, '3.jpg', 2, '2008-05-01', 2, now(), now()),
       (4, 'weiyixiao', '123456', '韦一笑', 1, '4.jpg', 2, '2007-01-01', 2, now(), now()),
       (5, 'changyuchun', '123456', '常遇春', 1, '5.jpg', 2, '2012-12-05', 2, now(), now()),
       (6, 'xiaozhao', '123456', '小昭', 2, '6.jpg', 3, '2013-09-05', 1, now(), now()),
       (7, 'jixiaofu', '123456', '纪晓芙', 2, '7.jpg', 1, '2005-08-01', 1, now(), now()),
       (8, 'zhouzhiruo', '123456', '周芷若', 2, '8.jpg', 1, '2014-11-09', 1, now(), now()),
       (9, 'dingminjun', '123456', '丁敏君', 2, '9.jpg', 1, '2011-03-11', 1, now(), now()),
       (10, 'zhaomin', '123456', '赵敏', 2, '10.jpg', 1, '2013-09-05', 1, now(), now()),
       (11, 'luzhangke', '123456', '鹿杖客', 1, '11.jpg', 5, '2007-02-01', 3, now(), now()),
       (12, 'hebiweng', '123456', '鹤笔翁', 1, '12.jpg', 5, '2008-08-18', 3, now(), now()),
       (13, 'fangdongbai', '123456', '方东白', 1, '13.jpg', 5, '2012-11-01', 3, now(), now()),
       (14, 'zhangsanfeng', '123456', '张三丰', 1, '14.jpg', 2, '2002-08-01', 2, now(), now()),
       (15, 'yulianzhou', '123456', '俞莲舟', 1, '15.jpg', 2, '2011-05-01', 2, now(), now()),
       (16, 'songyuanqiao', '123456', '宋远桥', 1, '16.jpg', 2, '2007-01-01', 2, now(), now()),
       (17, 'chenyouliang', '123456', '陈友谅', 1, '17.jpg', NULL, '2015-03-21', NULL, now(), now());
# 部门员工一对多
select * from tb_emp,tb_dept;-- 多表查询时会产生笛卡尔积  因此需要消除无效的笛卡尔积
select * from tb_emp,tb_dept where tb_emp.dept_id=tb_dept.id;

# 多表查询分类
# 内连接查询
# 显示内连接  查询姓名和对应部门  where消除笛卡尔积
select * from tb_emp,tb_dept where tb_emp.dept_id=tb_dept.id;
# 隐式内连接  查询姓名和对应部门  on后消除笛卡尔积
select tb_emp.name,tb_dept.name from tb_emp join tb_dept on tb_emp.dept_id=tb_dept.id;
# 如果表名过长 可以起别名  但是如果起了别名的话  前后的也只能用别名
select a.name,b.name from tb_emp a join tb_dept b on a.dept_id=b.id;

# 外连接查询  左边的就是左表  右边的就是右表
# 左外连接  可以看到结果和前面不同的是多一条没有分配部门的员工的数据  因为会查询交集数据和左表的所有数据，即使有数据没有关联
select e.name,d.name from tb_emp e left join tb_dept d on e.dept_id=d.id;
# 右外连接  这一条sql也会查询出没有员工的部门
select e.name,d.name from tb_emp e right join tb_dept d on e.dept_id=d.id;

# 子查询（嵌套查询）

# 标量子查询  子查询返回的结果是单行单列的值
# 1查询教研部的所有员工信息      **先分解查询语句**     因为员工表中没有部门的名称只有id 所以先根据名称查到部门的id 再查员工信息
select id from tb_dept where name='教研部';
select * from tb_emp where dept_id=2;
# 两者组合成子查询
select * from tb_emp where (select id from tb_dept where name='教研部');
# 2查询在方东白之后入职的员工信息 先查方东白什么时候入职的 再查之后的员工
select entrydate from tb_emp where name='方东白';
select * from tb_emp where entrydate>'2012-11-1';
# 组合
select * from tb_emp where entrydate> (select entrydate from tb_emp where name='方东白');

# 列子查询  子查询返回的结果是一列
# 查询教研部和咨询部的所有员工信息
select id from tb_dept where name='教研部' or name='咨询部';
select * from tb_emp where dept_id in(3,2);
select * from tb_emp where dept_id in(select id from tb_dept where name='教研部' or name='咨询部');

# 行子查询  子查询返回的结果是一行
# 查询与韦一笑的入职日期及职位相同的员工信息
# 先查韦一笑的日期和职位
select entrydate,job from tb_emp where name='韦一笑';
select * from tb_emp where entrydate='2007-1-1' and job=2;
# 注意组合的时候 可以换一种where条件的写法
select * from tb_emp where (entrydate,job)=(select entrydate,job from tb_emp where name='韦一笑');

# 表子查询   子查询返回的结果是一张表  经常作为临时表
# 查询入职日期是2006-1-1之后的员工信息和部门名称
# 首先看到要查员工信息和部门名称 最后肯定是要用到两张表 所以先查出2006-1-1之后的员工信息作为临时表  然后用多表查询 左外连接陈友谅
select * from tb_emp where entrydate>'2006-1-1';
# 好好理解这一句
select e.*,d.name from (select * from tb_emp where entrydate>'2006-1-1') e left join tb_dept d on e.dept_id=d.id;
