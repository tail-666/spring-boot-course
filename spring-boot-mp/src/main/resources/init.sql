-- 建库
CREATE DATABASE IF NOT EXISTS my_mp;
USE my_mp;


-- 建表语句
CREATE TABLE `user_account` (
                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                `username` varchar(50) NOT NULL COMMENT '用户名',
                                `password` varchar(255) NOT NULL COMMENT '密码',
                                `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
                                `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
                                `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
                                `avatar_url` varchar(255) DEFAULT NULL COMMENT '头像URL',
                                `status` tinyint(1) DEFAULT '1' COMMENT '状态：1-启用，0-禁用',
                                `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除：1-已删除，0-未删除',
                                `version` int DEFAULT '0' COMMENT '版本号（乐观锁）',
                                `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `uk_user_username` (`username`),
                                UNIQUE KEY `uk_user_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户账户表';

INSERT INTO user_account (
    username, password, nickname, email, phone, avatar_url,
    status, deleted, version, create_time, update_time
) VALUES
      ('zhang.wei', '$2a$10$eACCYoNOHEqYve6PwuRlBO9nKhl8jZiB.7TQvVR3JgWMVL.Qw.JF6', '张伟', 'zhang.wei@techmail.com', '17034567890', 'https://i.pravatar.cc/300?u=zhangwei', 1, 0, 0, NOW(), NOW()),
      ('li.xiaolan', '$2a$10$eACCYoNOHEqYve6PwuRlBO9nKhl8jZiB.7TQvVR3JgWMVL.Qw.JF6', '小兰', 'li.xiaolan@outlook.com', '17034567891', 'https://i.pravatar.cc/300?u=lixiaolan', 1, 0, 0, NOW(), NOW()),
      ('wang_peng', '$2a$10$eACCYoNOHEqYve6PwuRlBO9nKhl8jZiB.7TQvVR3JgWMVL.Qw.JF6', '王鹏', 'wang.peng@company.net', '17034567892', 'https://i.pravatar.cc/300?u=wangpeng', 1, 0, 0, NOW(), NOW()),
      ('chenmei_007', '$2a$10$eACCYoNOHEqYve6PwuRlBO9nKhl8jZiB.7TQvVR3JgWMVL.Qw.JF6', '陈美', 'chenmei007@gmail.com', '17034567893', 'https://i.pravatar.cc/300?u=chenmei007', 1, 0, 0, NOW(), NOW()),
      ('zhao.yun', '$2a$10$eACCYoNOHEqYve6PwuRlBO9nKhl8jZiB.7TQvVR3JgWMVL.Qw.JF6', '赵云', 'zhao.yun@enterprise.com', '17034567894', 'https://i.pravatar.cc/300?u=zhaoyun', 1, 0, 0, NOW(), NOW()),
      ('xiao_bai', '$2a$10$eACCYoNOHEqYve6PwuRlBO9nKhl8jZiB.7TQvVR3JgWMVL.Qw.JF6', '小白', 'xiaobai@demo.org', '17034567895', 'https://i.pravatar.cc/300?u=xiaobai', 1, 0, 0, NOW(), NOW()),
      ('liu.jun', '$2a$10$eACCYoNOHEqYve6PwuRlBO9nKhl8jZiB.7TQvVR3JgWMVL.Qw.JF6', '刘军', 'liu.jun@techhub.cn', '17034567896', 'https://i.pravatar.cc/300?u=liujun', 1, 0, 0, NOW(), NOW()),
      ('sun.li', '$2a$10$eACCYoNOHEqYve6PwuRlBO9nKhl8jZiB.7TQvVR3JgWMVL.Qw.JF6', '孙莉', 'sun.li@star.com', '17034567897', 'https://i.pravatar.cc/300?u=sunli', 1, 0, 0, NOW(), NOW()),
      ('zhou.xin', '$2a$10$eACCYoNOHEqYve6PwuRlBO9nKhl8jZiB.7TQvVR3JgWMVL.Qw.JF6', '周鑫', 'zhou.xin@future.io', '17034567898', 'https://i.pravatar.cc/300?u=zhouxin', 1, 0, 0, NOW(), NOW()),
      ('wu.yan', '$2a$10$eACCYoNOHEqYve6PwuRlBO9nKhl8jZiB.7TQvVR3JgWMVL.Qw.JF6', '吴艳', 'wu.yan@cloudsys.com', '17034567899', 'https://i.pravatar.cc/300?u=wuyan', 1, 0, 0, NOW(), NOW()),
      ('ma.ling', '$2a$10$eACCYoNOHEqYve6PwuRlBO9nKhl8jZiB.7TQvVR3JgWMVL.Qw.JF6', '马玲', 'ma.ling@testlab.com', '17134567800', 'https://i.pravatar.cc/300?u=maling', 1, 0, 0, NOW(), NOW()),
      ('hu.tao', '$2a$10$eACCYoNOHEqYve6PwuRlBO9nKhl8jZiB.7TQvVR3JgWMVL.Qw.JF6', '胡涛', 'hu.tao@innovate.cn', '17134567801', 'https://i.pravatar.cc/300?u=hutao', 1, 0, 0, NOW(), NOW()),
      ('xu.zhe', '$2a$10$eACCYoNOHEqYve6PwuRlBO9nKhl8jZiB.7TQvVR3JgWMVL.Qw.JF6', '徐哲', 'xu.zhe@nextgen.net', '17134567802', 'https://i.pravatar.cc/300?u=xuzhe', 1, 0, 0, NOW(), NOW()),
      ('lin.xia', '$2a$10$eACCYoNOHEqYve6PwuRlBO9nKhl8jZiB.7TQvVR3JgWMVL.Qw.JF6', '林夏', 'lin.xia@sunrise.com', '17134567803', 'https://i.pravatar.cc/300?u=linxia', 1, 0, 0, NOW(), NOW()),
      ('gao.feng', '$2a$10$eACCYoNOHEqYve6PwuRlBO9nKhl8jZiB.7TQvVR3JgWMVL.Qw.JF6', '高峰', 'gao.feng@summit.org', '17134567804', 'https://i.pravatar.cc/300?u=gao_feng', 1, 0, 0, NOW(), NOW()),
      ('tang.yi', '$2a$10$eACCYoNOHEqYve6PwuRlBO9nKhl8jZiB.7TQvVR3JgWMVL.Qw.JF6', '唐毅', 'tang.yi@digital.com', '17134567805', 'https://i.pravatar.cc/300?u=tangyi', 1, 0, 0, NOW(), NOW()),
      ('lu.xin', '$2a$10$eACCYoNOHEqYve6PwuRlBO9nKhl8jZiB.7TQvVR3JgWMVL.Qw.JF6', '陆鑫', 'lu.xin@smartech.com', '17134567806', 'https://i.pravatar.cc/300?u=luxin', 1, 0, 0, NOW(), NOW()),
      ('han.mei', '$2a$10$eACCYoNOHEqYve6PwuRlBO9nKhl8jZiB.7TQvVR3JgWMVL.Qw.JF6', '韩梅', 'han.mei@websoft.cn', '17134567807', 'https://i.pravatar.cc/300?u=hanmei', 1, 0, 0, NOW(), NOW()),
      ('disabled_user', '$2a$10$eACCYoNOHEqYve6PwuRlBO9nKhl8jZiB.7TQvVR3JgWMVL.Qw.JF6', '王小梅', 'disabled@test.com', '17134567808', 'https://i.pravatar.cc/300?u=disabled', 0, 0, 0, NOW(), NOW()),
      ('deleted_user', '$2a$10$eACCYoNOHEqYve6PwuRlBO9nKhl8jZiB.7TQvVR3JgWMVL.Qw.JF6', '赵果果', 'deleted@old.com', '17134567809', 'https://i.pravatar.cc/300?u=deleted', 1, 1, 0, NOW(), NOW());