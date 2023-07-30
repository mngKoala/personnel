-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.38 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 ep 的数据库结构
CREATE DATABASE IF NOT EXISTS `ep` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `ep`;

-- 导出  表 ep.sys_admin 结构
CREATE TABLE IF NOT EXISTS `sys_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(63) NOT NULL DEFAULT '' COMMENT '管理员名称',
  `password` varchar(63) NOT NULL DEFAULT '' COMMENT '管理员密码',
  `last_login_ip` varchar(63) DEFAULT '' COMMENT '最近一次登录IP地址',
  `last_login_time` datetime DEFAULT NULL COMMENT '最近一次登录时间',
  `avatar` varchar(255) DEFAULT '''' COMMENT '头像图片',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  `role_ids` varchar(127) DEFAULT '[]' COMMENT '角色列表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- 正在导出表  ep.sys_admin 的数据：~1 rows (大约)
INSERT INTO `sys_admin` (`id`, `username`, `password`, `last_login_ip`, `last_login_time`, `avatar`, `add_time`, `update_time`, `deleted`, `role_ids`) VALUES
	(1, 'admin123', '$2a$10$.rEfyBb/GURD9P2p0fRg/OAJGloGNDkJS4lY0cQHeqDgsbdTylBpu', '127.0.0.1', '2023-07-09 22:51:07', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '2023-02-01 00:00:00', '2023-07-09 22:51:07', 0, '[1]');

-- 导出  表 ep.sys_log 结构
CREATE TABLE IF NOT EXISTS `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '管理员',
  `ip` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '管理员地址',
  `type` int(11) DEFAULT NULL COMMENT '操作分类',
  `action` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作动作',
  `status` tinyint(1) DEFAULT NULL COMMENT '操作状态',
  `result` varchar(127) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作结果，或者成功消息，或者失败消息',
  `comment` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '补充信息',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='操作日志表';

-- 正在导出表  ep.sys_log 的数据：~8 rows (大约)
INSERT INTO `sys_log` (`id`, `admin`, `ip`, `type`, `action`, `status`, `result`, `comment`, `add_time`, `update_time`, `deleted`) VALUES
	(63, 'admin123', '127.0.0.1', 1, '登录', 1, '', '', '2023-07-09 21:45:54', '2023-07-09 21:45:54', 0),
	(64, 'admin123', '127.0.0.1', 1, '退出', 1, '', '', '2023-07-09 21:53:18', '2023-07-09 21:53:18', 0),
	(65, 'admin123', '127.0.0.1', 1, '登录', 1, '', '', '2023-07-09 21:53:22', '2023-07-09 21:53:22', 0),
	(66, 'admin123', '127.0.0.1', 1, '退出', 1, '', '', '2023-07-09 22:20:40', '2023-07-09 22:20:40', 0),
	(67, 'admin123', '127.0.0.1', 1, '登录', 1, '', '', '2023-07-09 22:21:27', '2023-07-09 22:21:27', 0),
	(68, 'admin123', '127.0.0.1', 1, '登录', 1, '', '', '2023-07-09 22:46:20', '2023-07-09 22:46:20', 0),
	(69, 'admin123', '127.0.0.1', 1, '退出', 1, '', '', '2023-07-09 22:50:54', '2023-07-09 22:50:54', 0),
	(70, 'admin123', '127.0.0.1', 1, '登录', 1, '', '', '2023-07-09 22:51:07', '2023-07-09 22:51:07', 0);

-- 导出  表 ep.sys_notice 结构
CREATE TABLE IF NOT EXISTS `sys_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(63) DEFAULT NULL COMMENT '通知标题',
  `content` varchar(511) DEFAULT NULL COMMENT '通知内容',
  `admin_id` int(11) DEFAULT '0' COMMENT '创建通知的管理员ID，如果是系统内置通知则是0.',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知表';

-- 正在导出表  ep.sys_notice 的数据：~0 rows (大约)

-- 导出  表 ep.sys_notice_admin 结构
CREATE TABLE IF NOT EXISTS `sys_notice_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_id` int(11) DEFAULT NULL COMMENT '通知ID',
  `notice_title` varchar(63) DEFAULT NULL COMMENT '通知标题',
  `admin_id` int(11) DEFAULT NULL COMMENT '接收通知的管理员ID',
  `read_time` datetime DEFAULT NULL COMMENT '阅读时间，如果是NULL则是未读状态',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知管理员表';

-- 正在导出表  ep.sys_notice_admin 的数据：~0 rows (大约)

-- 导出  表 ep.sys_permission 结构
CREATE TABLE IF NOT EXISTS `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `permission` varchar(63) DEFAULT NULL COMMENT '权限',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- 正在导出表  ep.sys_permission 的数据：~1 rows (大约)
INSERT INTO `sys_permission` (`id`, `role_id`, `permission`, `add_time`, `update_time`, `deleted`) VALUES
	(1, 1, '*', '2023-07-09 00:00:00', '2023-07-09 00:00:00', 0);

-- 导出  表 ep.sys_role 结构
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(63) NOT NULL COMMENT '角色名称',
  `desc` varchar(1023) DEFAULT NULL COMMENT '角色描述',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 正在导出表  ep.sys_role 的数据：~1 rows (大约)
INSERT INTO `sys_role` (`id`, `name`, `desc`, `enabled`, `add_time`, `update_time`, `deleted`) VALUES
	(1, '超级管理员', '所有模块的权限', 1, '2019-01-01 00:00:00', '2019-01-01 00:00:00', 0);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
