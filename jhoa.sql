# MySQL-Front 4.2  (Build 2.83)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: jhoa
# ------------------------------------------------------
# Server version 5.1.54-community

DROP DATABASE IF EXISTS `jhoa`;
CREATE DATABASE `jhoa` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `jhoa`;

#
# Table structure for table qt_menu
#

CREATE TABLE `qt_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuname` varchar(255) DEFAULT NULL COMMENT '菜单名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='菜单表';

#
# Dumping data for table qt_menu
#

INSERT INTO `qt_menu` VALUES (7,'菜单1');
INSERT INTO `qt_menu` VALUES (9,'菜单2');

#
# Table structure for table qt_news
#

CREATE TABLE `qt_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_hungarian_ci COMMENT '富文本内容',
  `stext` varchar(255) DEFAULT NULL COMMENT '纯文本内容',
  `userID` int(11) DEFAULT NULL COMMENT '发布者ID',
  `addtime` timestamp NULL DEFAULT NULL COMMENT '添加时间',
  `modifytime` timestamp NULL DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='新闻动态表';

#
# Dumping data for table qt_news
#

INSERT INTO `qt_news` VALUES (3,'测试标题长度为多少个字符长度＝HELLO','<p>测试用<b>动态标题1</b></p>\r\n<p>这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的</p>\r\n<p>测试<span style=\"color:#e56600;\">用动态标题</span>1</p>\r\n<p>测<span style=\"background-color:#b8d100;\">试用动态</span>标题1</p>\r\n<p>新闻列表：</p>\r\n<p></p>\r\n<ul><li>总则</li>\r\n<li>第一章</li>\r\n<li>第二章</li>\r\n<li>第三章</li>\r\n</ul>\r\n<div>你好，世界，<a href=\"http://a\">人生就是一场游戏</a>！</div>','测试用动态标题1\r\n这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的\r\n测试用动态标题1\r\n测试用动态标题1\r\n新闻列表：\r\n\r\n总则\r\n第一章\r\n第二章\r\n第三章\r\n\r\n你好，世界，人生就是一场游戏！',2,'2011-09-09 14:10:39','2011-09-15 11:05:14');

#
# Table structure for table qt_operator
#

CREATE TABLE `qt_operator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `optname` varchar(255) DEFAULT NULL COMMENT '操作描述',
  `optvalue` varchar(255) DEFAULT NULL COMMENT '操作值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='操作表';

#
# Dumping data for table qt_operator
#

INSERT INTO `qt_operator` VALUES (2,'单条数据查看','show');
INSERT INTO `qt_operator` VALUES (4,'列表数据','list');

#
# Table structure for table qt_permission
#

CREATE TABLE `qt_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuID` int(11) DEFAULT NULL COMMENT '菜单ID',
  `resourceID` int(11) DEFAULT NULL COMMENT '资源ID',
  `optID` int(11) DEFAULT NULL COMMENT '操作ID',
  `description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='权限表';

#
# Dumping data for table qt_permission
#

INSERT INTO `qt_permission` VALUES (3,7,3,2,'菜单1下对资源1的单条数据查看show');
INSERT INTO `qt_permission` VALUES (4,7,3,4,'菜单1下对资源2的列表数据list');
INSERT INTO `qt_permission` VALUES (5,9,6,2,'菜单2下对资源1的单条数据查看show');
INSERT INTO `qt_permission` VALUES (6,9,6,4,'菜单2下对资源2的列表数据list');

#
# Table structure for table qt_resource
#

CREATE TABLE `qt_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resourcename` varchar(255) DEFAULT NULL COMMENT '资源描述',
  `resourcevalue` varchar(255) DEFAULT NULL COMMENT '资源值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='资源表';

#
# Dumping data for table qt_resource
#

INSERT INTO `qt_resource` VALUES (3,'资源1','1');
INSERT INTO `qt_resource` VALUES (6,'资源2','2');

#
# Table structure for table qt_role
#

CREATE TABLE `qt_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色表';

#
# Dumping data for table qt_role
#

INSERT INTO `qt_role` VALUES (1,'超级管理员');
INSERT INTO `qt_role` VALUES (6,'部员');
INSERT INTO `qt_role` VALUES (7,'部长');
INSERT INTO `qt_role` VALUES (8,'主席团');

#
# Table structure for table qt_rolepermission
#

CREATE TABLE `qt_rolepermission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleID` int(11) DEFAULT NULL COMMENT '角色ID',
  `permissionID` int(11) DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='角色权限对应表';

#
# Dumping data for table qt_rolepermission
#


#
# Table structure for table qt_user
#

CREATE TABLE `qt_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL DEFAULT '' COMMENT '学号',
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '姓名',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `addtime` timestamp NULL DEFAULT NULL COMMENT '添加时间',
  `modifytime` timestamp NULL DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Dumping data for table qt_user
#

INSERT INTO `qt_user` VALUES (2,'200826490109','李斌斌','1','2011-09-07 15:16:58','2011-09-07 15:16:58');
INSERT INTO `qt_user` VALUES (6,'200826490108','测试用户001','1','2011-09-15 17:01:44','2011-09-16 17:10:18');
INSERT INTO `qt_user` VALUES (7,'200826490110','测试用户002','1','2011-09-16 17:10:30','2011-09-16 17:10:30');
INSERT INTO `qt_user` VALUES (8,'200826490111','测试用户003','4','2011-09-16 17:35:08','2011-09-16 17:36:21');

#
# Table structure for table qt_userrole
#

CREATE TABLE `qt_userrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleID` int(11) DEFAULT NULL COMMENT '角色ID',
  `userID` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='用户角色对应表';

#
# Dumping data for table qt_userrole
#

INSERT INTO `qt_userrole` VALUES (13,6,7);
INSERT INTO `qt_userrole` VALUES (14,1,2);
INSERT INTO `qt_userrole` VALUES (15,7,6);
INSERT INTO `qt_userrole` VALUES (16,1,8);

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
