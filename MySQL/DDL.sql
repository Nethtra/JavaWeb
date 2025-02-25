# ddl
# 操作数据库
show databases ;
select database();
create database if not exists db01;
use db01;
drop database if exists db01;

# 操作表
# 创建表
# 时一般表名tb开头  字段加入约束
create table tb_user(
    id int primary key auto_increment comment 'id 非空且唯一',
    username varchar(20) not null unique comment '姓名',
    name varchar(10) not null comment '昵称',
    age int comment '年龄',
    gender char(1) default '男' comment '性别'
)comment '用户表';
# sql数据类型
# 分为数值类型 字符串类型 日期类型
# 常用
# 数值类型 tinyint int bigint 默认有符号 如果是无符号在后面加上unsigned
# 字符串类型 char()定长字符串  varchar()变长字符串
# 日期类型 date  datetime
# 字符串和日期时间类型都要加''

# 设计表案例：员工表     使用图形化界面创建该表
# 先分析每个字段的约束和数据类型
# 注意几点：性别和职位等字段使用 tinyint unsigned
# 图像 varchar类型 存放url
# 默认增加id create_time update_time等字段

# 查看表结构
show tables;-- 查看当前数据库下的所有表
desc tb_emp;-- 查看指定表结构
show create table tb_emp;-- 查看创建表的sql语句  或者右键表->go to ddl

# 修改表结构
# 添加字段
alter table tb_emp add phone_number char(11) not null comment '手机号';
# 修改字段数据类型 comment
alter table tb_emp modify phone_number char(13);
# 修改字段名 数据类型 comment
alter table tb_emp change phone_number phone char(12);
# 删除字段
alter table tb_emp drop column phone;
# 重命名表
rename table tb_emp to emp_tb;


# 删除表
-- delete 只能delete一行  不能delete一个单元格
drop table if exists tb_emp;

# 以上操作图形化界面操作也要会



# dml
# insert
# 插入部分字段 注意creat_time和update_time   可以使用sql中提供的now函数，返回当前系统时间
# 执行后打开表刷新数据
insert into tb_emp (username, name, gender, create_time, update_time)
values ('tom','汤姆',1,now(),now());
# 插入全部字段
insert into tb_emp (id, username, password, name, gender, image, job, entry_date, create_time, update_time)
values (null,'jerry','654321','jierui',1,null,1,'2000-1-1',now(),now());
# 插入全部字段只需要写value
insert into tb_emp value (null,'jerry2','654321','jierui',1,null,1,'2000-1-1',now(),now());
# 插入多组数据 在value后面,
insert into tb_emp (username, name, gender, create_time, update_time)
values ('lisi', '李四', 1, now(), now()),
       ('zhangsan', '张三', 1, now(), now());

# update
# 条件 where
# 注意要一并更改update_time    更改多个字段用,间隔
update tb_emp set name='王五',update_time=now() where id=1;
# 如果不加where条件则会为整张表更新此字段
update tb_emp set entry_date='2000-11-1',update_time=now();

# delete
# 不加where就是删除表中的所有数据
# 和删除表不一样 删除表是drop table
delete from tb_emp where id=4;

# 打开新console
# 右键localhost navigation->jump to query console->new query console




