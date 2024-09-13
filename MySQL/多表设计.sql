create database db03;
use db03;
# 多表设计
# 一对多
# 部门一   员工多    一个部门对应多个员工  一是父表  多是子表
# 员工表
drop table tb_emp;
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
#     加入部门字段来关联两张表
    dept_id int unsigned comment '归属部门id',
    create_time datetime         not null comment '创建时间',
    update_time datetime         not null comment '修改时间'
) comment '员工表';
# 部门表
create table tb_dept
(
    id          int unsigned primary key auto_increment comment 'id',
    name        varchar(10) unique not null comment '部门名称',
    create_time datetime           not null comment '创建时间',
    update_time datetime           not null comment '修改时间'
);

# 插入测试数据
insert into tb_dept (id, name, create_time, update_time)
values (1, '学工部', now(), now()),
       (2, '教研部', now(), now()),
       (3, '咨询部', now(), now()),
       (4, '就业部', now(), now()),
       (5, '人事部', now(), now());

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
       (11, 'luzhangke', '123456', '鹿杖客', 1, '11.jpg', 1, '2007-02-01', 1, now(), now()),
       (12, 'hebiweng', '123456', '鹤笔翁', 1, '12.jpg', 1, '2008-08-18', 1, now(), now()),
       (13, 'fangdongbai', '123456', '方东白', 1, '13.jpg', 2, '2012-11-01', 2, now(), now()),
       (14, 'zhangsanfeng', '123456', '张三丰', 1, '14.jpg', 2, '2002-08-01', 2, now(), now()),
       (15, 'yulianzhou', '123456', '俞莲舟', 1, '15.jpg', 2, '2011-05-01', 2, now(), now()),
       (16, 'songyuanqiao', '123456', '宋远桥', 1, '16.jpg', 2, '2010-01-01', 2, now(), now()),
       (17, 'chenyouliang', '123456', '陈友谅', 1, '17.jpg', NULL, '2015-03-21', NULL, now(), now());

# 想刚才这个仅仅用一个字段关联了两张表，并没有在数据库层面关联，所以导致比如在tb_dept中删除一个部门的字段，tb_emp中不会更新
# 影响了数据的完整性与一致性
# 要使用外键约束
# 学会外键约束的语法和图形化界面操作   注意是子表添加外键去关联父表的主键   即tb_emp去关联tb_dept
# foreign key 物理外键 不再使用


# 一对一
# 在任意一表添加外键并unique去关联另一张表的主键
# 用户和身份证
create table tb_user
(
    id     int unsigned primary key auto_increment comment 'ID',
    name   varchar(10)      not null comment '姓名',
    gender tinyint unsigned not null comment '性别, 1 男  2 女',
    phone  char(11) comment '手机号',
    degree varchar(10) comment '学历'
) comment '用户信息表';

insert into tb_user
values (1, '白眉鹰王', 1, '18812340001', '初中'),
       (2, '青翼蝠王', 1, '18812340002', '大专'),
       (3, '金毛狮王', 1, '18812340003', '初中'),
       (4, '紫衫龙王', 2, '18812340004', '硕士');


create table tb_user_card
(
    id           int unsigned primary key auto_increment comment 'ID',
    nationality  varchar(10)  not null comment '民族',
    birthday     date         not null comment '生日',
    idcard       char(18)     not null comment '身份证号',
    issued       varchar(20)  not null comment '签发机关',
    expire_begin date         not null comment '有效期限-开始',
    expire_end   date comment '有效期限-结束',
    user_id      int unsigned not null unique comment '用户ID',
    constraint fk_user_id foreign key (user_id) references tb_user (id)
) comment '用户信息表';

insert into tb_user_card
values (1, '汉', '1960-11-06', '100000100000100001', '朝阳区公安局', '2000-06-10', null, 1),
       (2, '汉', '1971-11-06', '100000100000100002', '静安区公安局', '2005-06-10', '2025-06-10', 2),
       (3, '汉', '1963-11-06', '100000100000100003', '昌平区公安局', '2006-06-10', null, 3),
       (4, '回', '1980-11-06', '100000100000100004', '海淀区公安局', '2008-06-10', '2028-06-10', 4);

# 多对多
# 建立一张中间表 中间表的两个外键分别关联两张表的主键
create table tb_student
(
    id   int auto_increment primary key comment '主键ID',
    name varchar(10) comment '姓名',
    no   varchar(10) comment '学号'
) comment '学生表';
insert into tb_student(name, no)
values ('黛绮丝', '2000100101'),
       ('谢逊', '2000100102'),
       ('殷天正', '2000100103'),
       ('韦一笑', '2000100104');


create table tb_course
(
    id   int auto_increment primary key comment '主键ID',
    name varchar(10) comment '课程名称'
) comment '课程表';
insert into tb_course (name)
values ('Java'),
       ('PHP'),
       ('MySQL'),
       ('Hadoop');

# 中间表
create table tb_student_course
(
    id         int auto_increment comment '主键' primary key,
    student_id int not null comment '学生ID',
    course_id  int not null comment '课程ID',
    constraint fk_courseid foreign key (course_id) references tb_course (id),
    constraint fk_studentid foreign key (student_id) references tb_student (id)
) comment '学生课程中间表';

insert into tb_student_course(student_id, course_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 2),
       (2, 3),
       (3, 4);