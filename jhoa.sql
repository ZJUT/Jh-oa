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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

#
# Dumping data for table qt_menu
#


#
# Table structure for table qt_news
#

CREATE TABLE `qt_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_hungarian_ci COMMENT '富文本内容',
  `stext` varchar(255) DEFAULT NULL COMMENT '纯文本内容',
  `username` varchar(255) DEFAULT NULL COMMENT '发布者',
  `addtime` timestamp NULL DEFAULT NULL COMMENT '添加时间',
  `modifytime` timestamp NULL DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='新闻动态表';

#
# Dumping data for table qt_news
#

INSERT INTO `qt_news` VALUES (1,'开始页面调整测试','<b>页面内容调整</b>','页面内容调整','2','2011-09-06 14:59:00',NULL);

#
# Table structure for table qt_user
#

CREATE TABLE `qt_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL DEFAULT '' COMMENT '学号',
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `roleID` tinyint(3) DEFAULT '0' COMMENT '角色ID',
  `addtime` timestamp NULL DEFAULT NULL COMMENT '添加时间',
  `modifytime` timestamp NULL DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Dumping data for table qt_user
#

INSERT INTO `qt_user` VALUES (2,'200826490109','李斌斌','123456',0,'2011-09-07 15:16:58','2011-09-07 15:16:58');

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
