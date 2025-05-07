-- 登录用户表
CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户唯一标识，自增主键',
                        `name` varchar(255) DEFAULT NULL COMMENT '用户名',
                        `password` varchar(255) DEFAULT NULL COMMENT '用户密码（建议加密存储）',
                        `email` varchar(255) DEFAULT NULL COMMENT '用户邮箱地址',
                        `birthday` datetime DEFAULT NULL COMMENT '用户出生日期',
                        `money` float DEFAULT NULL COMMENT '用户账户余额',
                        `avatar` varchar(45) DEFAULT NULL COMMENT '用户头像地址或文件名',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- 用户联系信息表
CREATE TABLE `user_contact` (
                                `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '联系人信息ID，自增主键',
                                `date` date NOT NULL COMMENT '记录创建日期或注册日期',
                                `name` varchar(45) NOT NULL COMMENT '联系人姓名',
                                `province` varchar(45) DEFAULT NULL COMMENT '所在省份',
                                `city` varchar(45) DEFAULT NULL COMMENT '所在城市',
                                `address` varchar(45) DEFAULT NULL COMMENT '详细地址',
                                `zip` varchar(45) DEFAULT NULL COMMENT '邮政编码',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- auto-generated definition
create table article
(
    id        int auto_increment
        primary key,
    author    varchar(20)   null,
    title     varchar(50)   null,
    author_id int           null,
    content   varchar(2048) null,
    constraint article_user_id_fk
        foreign key (author_id) references user (id)
            on update cascade
);

-- 模拟user数据
INSERT INTO `user` (`name`, `password`, `email`, `birthday`, `money`, `avatar`) VALUES
                                                                                    ('张三', '123456', 'zhangsan@example.com', '1990-05-15 00:00:00', 1000.50, 'avatar1.jpg'),
                                                                                    ('李四', '123456', 'lisi@example.com', '1985-08-22 00:00:00', 2500.75, 'avatar2.jpg'),
                                                                                    ('王五', '123456', 'wangwu@example.com', '1992-11-03 00:00:00', 800.25, 'avatar3.jpg'),
                                                                                    ('赵六', '123456', 'zhaoliu@example.com', '1988-04-18 00:00:00', 1500.00, 'avatar4.jpg'),
                                                                                    ('钱七', '123456', 'qianqi@example.com', '1995-07-30 00:00:00', 3000.00, 'avatar5.jpg'),
                                                                                    ('孙八', '123456', 'sunba@example.com', '1993-02-14 00:00:00', 1200.50, 'avatar6.jpg'),
                                                                                    ('周九', '123456', 'zhoujiu@example.com', '1987-09-25 00:00:00', 1800.75, 'avatar7.jpg'),
                                                                                    ('吴十', '123456', 'wushi@example.com', '1991-06-08 00:00:00', 950.00, 'avatar8.jpg'),
                                                                                    ('郑十一', '123456', 'zhengshiyi@example.com', '1989-12-12 00:00:00', 2100.25, 'avatar9.jpg'),
                                                                                    ('王十二', '123456', 'wangshier@example.com', '1994-03-27 00:00:00', 1750.50, 'avatar10.jpg');