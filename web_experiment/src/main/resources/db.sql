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