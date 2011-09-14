# MySQL-Front 4.2  (Build 2.83)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

#
# Dumping data for table qt_menu
#
LOCK TABLES `qt_menu` WRITE;
/*!40000 ALTER TABLE `qt_menu` DISABLE KEYS */;

/*!40000 ALTER TABLE `qt_menu` ENABLE KEYS */;
UNLOCK TABLES;

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
LOCK TABLES `qt_news` WRITE;
/*!40000 ALTER TABLE `qt_news` DISABLE KEYS */;

INSERT INTO `qt_news` VALUES (3,'测试标题长度为多少个字符长度','<p>测试用<b>动态标题1</b></p>\r\n<p>这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的</p>\r\n<p>测试<span style=\"color:#e56600;\">用动态标题</span>1</p>\r\n<p>测<span style=\"background-color:#b8d100;\">试用动态</span>标题1</p>\r\n<p style=\"text-align:center;\"><img src=\"/Jh-oa/attached/20110914095851_632.jpg\" alt=\"\" border=\"0\" /></p>\r\n<p>新闻列表：</p>\r\n<p></p>\r\n<ul><li>总则</li>\r\n<li>第一章</li>\r\n<li>第二章</li>\r\n<li>第三章</li>\r\n</ul>\r\n<div style=\"text-align:center;\"><img src=\"/Jh-oa/attached/20110914103131_863.jpg\" alt=\"\" border=\"0\" /><br />\r\n</div>\r\n<div>你好，世界，<a href=\"http://a\">人生就是一场游戏</a>！</div>','测试用动态标题1\r\n这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的这行很长很长的\r\n测试用动态标题1\r\n测试用动态标题1\r\n\r\n新闻列表：\r\n\r\n总则\r\n第一章\r\n第二章\r\n第三章\r\n\r\n\r\n\r\n你好，世界，人生就是一场游戏！',2,'2011-09-09 14:10:39','2011-09-14 11:06:22');
INSERT INTO `qt_news` VALUES (7,'test1111','test','test',2,'2011-09-14 16:48:19','2011-09-14 16:48:38');
/*!40000 ALTER TABLE `qt_news` ENABLE KEYS */;
UNLOCK TABLES;

#
# Table structure for table qt_operate
#

CREATE TABLE `qt_operate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `optvalue` varchar(255) DEFAULT NULL COMMENT '操作值',
  `optname` varchar(255) DEFAULT NULL COMMENT '操作描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作表';

#
# Dumping data for table qt_operate
#
LOCK TABLES `qt_operate` WRITE;
/*!40000 ALTER TABLE `qt_operate` DISABLE KEYS */;

/*!40000 ALTER TABLE `qt_operate` ENABLE KEYS */;
UNLOCK TABLES;

#
# Table structure for table qt_permission
#

CREATE TABLE `qt_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuID` int(11) DEFAULT NULL COMMENT '菜单ID',
  `resourceID` int(11) DEFAULT NULL COMMENT '资源ID',
  `optID` int(11) DEFAULT NULL COMMENT '操作ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

#
# Dumping data for table qt_permission
#
LOCK TABLES `qt_permission` WRITE;
/*!40000 ALTER TABLE `qt_permission` DISABLE KEYS */;

/*!40000 ALTER TABLE `qt_permission` ENABLE KEYS */;
UNLOCK TABLES;

#
# Table structure for table qt_resource
#

CREATE TABLE `qt_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resourcename` varchar(255) DEFAULT NULL COMMENT '资源名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

#
# Dumping data for table qt_resource
#
LOCK TABLES `qt_resource` WRITE;
/*!40000 ALTER TABLE `qt_resource` DISABLE KEYS */;

/*!40000 ALTER TABLE `qt_resource` ENABLE KEYS */;
UNLOCK TABLES;

#
# Table structure for table qt_rolepermission
#

CREATE TABLE `qt_rolepermission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleID` int(11) DEFAULT NULL COMMENT '角色ID',
  `permissionID` int(11) DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限对应表';

#
# Dumping data for table qt_rolepermission
#
LOCK TABLES `qt_rolepermission` WRITE;
/*!40000 ALTER TABLE `qt_rolepermission` DISABLE KEYS */;

/*!40000 ALTER TABLE `qt_rolepermission` ENABLE KEYS */;
UNLOCK TABLES;

#
# Table structure for table qt_userrole
#

CREATE TABLE `qt_userrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleID` int(11) DEFAULT NULL COMMENT '角色ID',
  `userID` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色对应表';

#
# Dumping data for table qt_userrole
#
LOCK TABLES `qt_userrole` WRITE;
/*!40000 ALTER TABLE `qt_userrole` DISABLE KEYS */;

/*!40000 ALTER TABLE `qt_userrole` ENABLE KEYS */;
UNLOCK TABLES;

#
# Table structure for table qt_user
#

CREATE TABLE `qt_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL DEFAULT '' COMMENT '学号',
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `addtime` timestamp NULL DEFAULT NULL COMMENT '添加时间',
  `modifytime` timestamp NULL DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Dumping data for table qt_user
#
LOCK TABLES `qt_user` WRITE;
/*!40000 ALTER TABLE `qt_user` DISABLE KEYS */;

INSERT INTO `qt_user` VALUES (2,'200826490109','李斌斌','123456','2011-09-07 15:16:58','2011-09-07 15:16:58');
/*!40000 ALTER TABLE `qt_user` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
