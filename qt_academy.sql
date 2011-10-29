# MySQL-Front 4.2  (Build 2.83)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: jhoa
# ------------------------------------------------------
# Server version 5.1.54-community

USE `jhoa`;

#
# Table structure for table qt_academy
#

DROP TABLE IF EXISTS `qt_academy`;
CREATE TABLE `qt_academy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `academyname` varchar(255) DEFAULT NULL COMMENT '学院名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='学院表';

#
# Dumping data for table qt_academy
#

INSERT INTO `qt_academy` VALUES (1,'化材学院');
INSERT INTO `qt_academy` VALUES (2,'机械学院');
INSERT INTO `qt_academy` VALUES (3,'信息学院');
INSERT INTO `qt_academy` VALUES (4,'计算机、软件学院');
INSERT INTO `qt_academy` VALUES (5,'经贸学院');
INSERT INTO `qt_academy` VALUES (6,'建工学院');
INSERT INTO `qt_academy` VALUES (7,'生环学院');
INSERT INTO `qt_academy` VALUES (8,'教科学院');
INSERT INTO `qt_academy` VALUES (9,'人文学院');
INSERT INTO `qt_academy` VALUES (10,'健行学院');
INSERT INTO `qt_academy` VALUES (11,'外语学院');
INSERT INTO `qt_academy` VALUES (12,'法学院');
INSERT INTO `qt_academy` VALUES (13,'理学院');
INSERT INTO `qt_academy` VALUES (14,'成教学院');
INSERT INTO `qt_academy` VALUES (15,'之江学院');
INSERT INTO `qt_academy` VALUES (16,'艺术学院');
INSERT INTO `qt_academy` VALUES (17,'药学院');
INSERT INTO `qt_academy` VALUES (18,'国际学院');
INSERT INTO `qt_academy` VALUES (19,'政管学院');

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
