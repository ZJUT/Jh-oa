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
# Table structure for table qt_academy
#

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

#
# Table structure for table qt_department
#

CREATE TABLE `qt_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departmentname` varchar(255) DEFAULT NULL COMMENT '部门名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='部门表';

#
# Dumping data for table qt_department
#

INSERT INTO `qt_department` VALUES (1,'主席团');
INSERT INTO `qt_department` VALUES (2,'技术前端设计');
INSERT INTO `qt_department` VALUES (3,'技术视觉传达');
INSERT INTO `qt_department` VALUES (4,'技术后台程序');
INSERT INTO `qt_department` VALUES (5,'技术服务器部');
INSERT INTO `qt_department` VALUES (6,'精弘feel电台');
INSERT INTO `qt_department` VALUES (7,'精弘开源');
INSERT INTO `qt_department` VALUES (8,'精弘下载');
INSERT INTO `qt_department` VALUES (9,'精弘家园');
INSERT INTO `qt_department` VALUES (10,'精弘商铺');
INSERT INTO `qt_department` VALUES (11,'精弘资讯');
INSERT INTO `qt_department` VALUES (12,'精弘论坛');
INSERT INTO `qt_department` VALUES (13,'常务朝晖部');
INSERT INTO `qt_department` VALUES (14,'常务组织部');
INSERT INTO `qt_department` VALUES (15,'常务活动部');
INSERT INTO `qt_department` VALUES (16,'常务办公室');

#
# Table structure for table qt_event
#

CREATE TABLE `qt_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '事件标题',
  `content` text COMMENT '事件内容',
  `modifytime` timestamp NULL DEFAULT NULL COMMENT '最后编辑时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='大事件表';

#
# Dumping data for table qt_event
#

INSERT INTO `qt_event` VALUES (3,'平板的产品趋势','<span class=\"Apple-style-span\" style=\"font-family:simsun;font-size:14px;line-height:25px;background-color:#f5f8fd;\"><p style=\"margin-top:14px;margin-bottom:14px;padding-top:0px;padding-right:0px;padding-bottom:0px;padding-left:0px;\">纵观本次CES上展示的平板电脑，均是以高分辨率、高配置、软件应用、外设应用、滑盖以及搭载坞站变身笔记本等特色为主。</p>\r\n<p align=\"center\" sizcache=\"3\" sizset=\"148\" style=\"margin-top:14px;margin-bottom:14px;padding-top:0px;padding-right:0px;padding-bottom:0px;padding-left:0px;\"><br clear=\"all\" style=\"font-size:0px;line-height:0;\" />\r\n<img alt=\"CES \" align=\"no\" src=\"http://2e.zol-img.com.cn/product/57_450x337/298/ceP3d4RxarehI.jpg\" style=\"border-top-width:1px;border-right-width:1px;border-bottom-width:1px;border-left-width:1px;border-style:initial;border-color:initial;cursor:pointer;border-bottom-color:black;border-bottom-style:solid;border-left-color:black;border-left-style:solid;border-top-color:black;border-top-style:solid;border-right-color:black;border-right-style:solid;\" /><br />\r\n联想最新的平板电脑</p>\r\n<p style=\"margin-top:14px;margin-bottom:14px;padding-top:0px;padding-right:0px;padding-bottom:0px;padding-left:0px;\">&nbsp;&nbsp;&nbsp; 据美国证券公司Caris &amp; Company首席分析师Craig Ellis分析，本次大展上会出现至少69款平板电脑。按照核心处理器划分的话，其中基于<a href=\"http://detail.zol.com.cn/notebook_index/subcate16_list_s1250_1.html\" class=\"hui14_line\" style=\"color:#000099;text-decoration:underline;\"><span style=\"color:#333333; ;\">Intel Atom</span></a>平台的将有18款，<a href=\"http://detail.zol.com.cn/vga_index/subcate6_list_s2136_1.html\" class=\"hui14_line\" style=\"color:#000099;text-decoration:underline;\"><span style=\"color:#333333; ;\">NVIDIA</span></a>&nbsp;Tegra也能占据14款，还有10款飞思卡尔、6款德州仪器，此外高通、Marvell等也都有露脸的机会。处理器平台各有各的特色，比如<a href=\"http://detail.zol.com.cn/cpu_index/subcate28_125_list_1.html\" class=\"hui14_line\" style=\"color:#000099;text-decoration:underline;\"><span style=\"color:#333333; ;\">Intel</span></a>的功耗低，待机时间长，NVIDIA的视频感受对平板来说绝对可以称得上是顶级的。</p>\r\n<p style=\"margin-top:14px;margin-bottom:14px;padding-top:0px;padding-right:0px;padding-bottom:0px;padding-left:0px;\">&nbsp;&nbsp;&nbsp; 屏幕尺寸在7.1英寸至10.1英寸，对于笔记本电脑来说，这是一个空白区域，而平板产品则恰好卡在这个尺寸区域中，这个尺寸是移动和视觉感受最完美的平衡。屏幕分辨率大多采用1280×800，超高配置以及超高的分辨率是本届CES上平板电脑的最大亮点。</p>\r\n<p align=\"center\" style=\"margin-top:14px;margin-bottom:14px;padding-top:0px;padding-right:0px;padding-bottom:0px;padding-left:0px;\"><a href=\"http://detail.zol.com.cn/picture_index_581/index5801449.shtml\" style=\"color:#000099;text-decoration:underline;\"><img alt=\"新产业下的新机会 CES平板 \" align=\"no\" src=\"http://2b.zol-img.com.cn/product/58_450x337/449/ceD0p20xL8c.jpg\" style=\"border-top-width:1px;border-right-width:1px;border-bottom-width:1px;border-left-width:1px;border-style:initial;border-color:initial;border-bottom-color:black;border-bottom-style:solid;border-left-color:black;border-left-style:solid;border-top-color:black;border-top-style:solid;border-right-color:black;border-right-style:solid;\" /></a></p>\r\n<p align=\"center\" style=\"margin-top:14px;margin-bottom:14px;padding-top:0px;padding-right:0px;padding-bottom:0px;padding-left:0px;\"><a href=\"http://detail.zol.com.cn/picture_index_581/index5801448.shtml\" style=\"color:#000099;text-decoration:underline;\"><img alt=\"新产业下的新机会 CES平板 \" align=\"no\" src=\"http://2a.zol-img.com.cn/product/58_240x180/448/cePUi3Efl6Kg.jpg\" style=\"border-top-width:1px;border-right-width:1px;border-bottom-width:1px;border-left-width:1px;border-style:initial;border-color:initial;border-bottom-color:black;border-bottom-style:solid;border-left-color:black;border-left-style:solid;border-top-color:black;border-top-style:solid;border-right-color:black;border-right-style:solid;\" /></a>&nbsp;<a href=\"http://detail.zol.com.cn/picture_index_581/index5801450.shtml\" style=\"color:#000099;text-decoration:underline;\"><img alt=\"新产业下的新机会 CES平板 \" align=\"no\" src=\"http://2c.zol-img.com.cn/product/58_240x180/450/cedVAIdhjLSJQ.jpg\" style=\"border-top-width:1px;border-right-width:1px;border-bottom-width:1px;border-left-width:1px;border-style:initial;border-color:initial;border-bottom-color:black;border-bottom-style:solid;border-left-color:black;border-left-style:solid;border-top-color:black;border-top-style:solid;border-right-color:black;border-right-style:solid;\" /></a><br />\r\n宏碁加扩展坞的平板</p>\r\n<p style=\"margin-top:14px;margin-bottom:14px;padding-top:0px;padding-right:0px;padding-bottom:0px;padding-left:0px;\">&nbsp;&nbsp;&nbsp; 新的产业还促进了平板电脑周边产品的发展，像平板电脑的皮套，像平板电脑的包等，而给平板电脑加扩展坞变成笔记本成为今年CES最为流行的趋势。这种产品形态能够帮助用户更好的操作平板电脑，加入扩展坞后，平板电脑不仅有了全键盘，而且还增加了更多的接口，比如USB、VGA输出等，这种做法也是日后的趋势，相信会有更多的周边厂商投入这个产业。</p>\r\n</span>','2011-09-30 21:13:43');

#
# Table structure for table qt_ffile
#

CREATE TABLE `qt_ffile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `filename` varchar(255) DEFAULT NULL COMMENT '文件名',
  `showname` varchar(255) DEFAULT NULL COMMENT '显示名',
  `addtime` timestamp NULL DEFAULT NULL COMMENT '上传时间',
  `userID` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件表';

#
# Dumping data for table qt_ffile
#


#
# Table structure for table qt_ke
#

CREATE TABLE `qt_ke` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL COMMENT '用户ID',
  `kevalue` varchar(255) DEFAULT NULL COMMENT '课程表值[以0代表没课，1代表有课]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='课表';

#
# Dumping data for table qt_ke
#

INSERT INTO `qt_ke` VALUES (9,2,'00000000100000010000000000001000000100000010000001000000000000010000000000000');

#
# Table structure for table qt_menu
#

CREATE TABLE `qt_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuname` varchar(255) DEFAULT NULL COMMENT '菜单名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='菜单表';

#
# Dumping data for table qt_menu
#

INSERT INTO `qt_menu` VALUES (7,'个人中心');
INSERT INTO `qt_menu` VALUES (9,'用户管理');
INSERT INTO `qt_menu` VALUES (10,'菜单管理');
INSERT INTO `qt_menu` VALUES (11,'资源管理');
INSERT INTO `qt_menu` VALUES (12,'操作管理');
INSERT INTO `qt_menu` VALUES (13,'角色管理');
INSERT INTO `qt_menu` VALUES (14,'用户角色管理');
INSERT INTO `qt_menu` VALUES (15,'权限管理');
INSERT INTO `qt_menu` VALUES (16,'动态管理');
INSERT INTO `qt_menu` VALUES (17,'角色权限管理');
INSERT INTO `qt_menu` VALUES (18,'全局菜单');
INSERT INTO `qt_menu` VALUES (19,'学院管理');
INSERT INTO `qt_menu` VALUES (20,'部门管理');
INSERT INTO `qt_menu` VALUES (21,'课表管理');
INSERT INTO `qt_menu` VALUES (22,'大事件管理');
INSERT INTO `qt_menu` VALUES (23,'文件管理');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='新闻动态表';

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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='操作表';

#
# Dumping data for table qt_operator
#

INSERT INTO `qt_operator` VALUES (2,'单条数据查看','show');
INSERT INTO `qt_operator` VALUES (4,'添加视图','viewAdd');
INSERT INTO `qt_operator` VALUES (5,'添加','add');
INSERT INTO `qt_operator` VALUES (6,'删除','delete');
INSERT INTO `qt_operator` VALUES (7,'编辑视图','viewModify');
INSERT INTO `qt_operator` VALUES (8,'编辑','modify');
INSERT INTO `qt_operator` VALUES (9,'筛选视图','viewFilter');
INSERT INTO `qt_operator` VALUES (10,'筛选','filter');
INSERT INTO `qt_operator` VALUES (11,'列表','list');
INSERT INTO `qt_operator` VALUES (12,'分页列表','listByPage');
INSERT INTO `qt_operator` VALUES (13,'批量删除','batchDelete');
INSERT INTO `qt_operator` VALUES (14,'私有数据查看','showMyself');
INSERT INTO `qt_operator` VALUES (15,'私有数据编辑视图','viewModifyMyself');
INSERT INTO `qt_operator` VALUES (16,'私有数据编辑','modifyMyself');
INSERT INTO `qt_operator` VALUES (17,'私有数据筛选视图','viewFilterMyself');
INSERT INTO `qt_operator` VALUES (18,'私有数据筛选','filterMyself');
INSERT INTO `qt_operator` VALUES (19,'私有数据列表','listMyself');
INSERT INTO `qt_operator` VALUES (20,'私有数据分页列表','listByPageMyself');
INSERT INTO `qt_operator` VALUES (21,'私有数据批量删除','batchDeleteMyself');
INSERT INTO `qt_operator` VALUES (22,'后台管理主页','manager');
INSERT INTO `qt_operator` VALUES (23,'上传图片','uploadImg');
INSERT INTO `qt_operator` VALUES (24,'退出系统','anonymous_logout');
INSERT INTO `qt_operator` VALUES (25,'私有数据添加视图','viewAddMyself');
INSERT INTO `qt_operator` VALUES (26,'私有数据添加','addMyself');
INSERT INTO `qt_operator` VALUES (27,'导出用户基本信息视图','viewExportUser');
INSERT INTO `qt_operator` VALUES (28,'导出用户基本信息','exportUser');
INSERT INTO `qt_operator` VALUES (29,'上传文件','uploadFile');

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
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8 COMMENT='权限表';

#
# Dumping data for table qt_permission
#

INSERT INTO `qt_permission` VALUES (7,10,3,4,'添加菜单视图');
INSERT INTO `qt_permission` VALUES (8,10,3,5,'添加菜单');
INSERT INTO `qt_permission` VALUES (9,10,3,6,'删除菜单');
INSERT INTO `qt_permission` VALUES (10,10,3,7,'编辑菜单视图');
INSERT INTO `qt_permission` VALUES (11,10,3,8,'编辑菜单');
INSERT INTO `qt_permission` VALUES (12,10,3,11,'列表菜单');
INSERT INTO `qt_permission` VALUES (13,11,6,4,'添加资源视图');
INSERT INTO `qt_permission` VALUES (14,11,6,5,'添加资源');
INSERT INTO `qt_permission` VALUES (15,11,6,6,'删除资源');
INSERT INTO `qt_permission` VALUES (16,11,6,7,'编辑资源视图');
INSERT INTO `qt_permission` VALUES (17,11,6,8,'编辑资源');
INSERT INTO `qt_permission` VALUES (18,11,6,11,'列表资源');
INSERT INTO `qt_permission` VALUES (19,12,7,4,'添加操作视图');
INSERT INTO `qt_permission` VALUES (20,12,7,5,'添加操作');
INSERT INTO `qt_permission` VALUES (21,12,7,6,'删除操作');
INSERT INTO `qt_permission` VALUES (22,12,7,7,'编辑操作视图');
INSERT INTO `qt_permission` VALUES (23,12,7,8,'编辑操作');
INSERT INTO `qt_permission` VALUES (24,12,7,11,'列表操作');
INSERT INTO `qt_permission` VALUES (25,9,8,2,'查看用户');
INSERT INTO `qt_permission` VALUES (26,9,8,4,'添加用户视图');
INSERT INTO `qt_permission` VALUES (27,9,8,5,'添加用户');
INSERT INTO `qt_permission` VALUES (28,9,8,7,'编辑用户视图');
INSERT INTO `qt_permission` VALUES (29,9,8,8,'编辑用户');
INSERT INTO `qt_permission` VALUES (30,9,8,10,'筛选用户');
INSERT INTO `qt_permission` VALUES (31,16,9,2,'查看动态');
INSERT INTO `qt_permission` VALUES (32,16,9,4,'添加动态视图');
INSERT INTO `qt_permission` VALUES (33,16,9,5,'添加动态');
INSERT INTO `qt_permission` VALUES (34,16,9,7,'编辑动态视图');
INSERT INTO `qt_permission` VALUES (35,16,9,8,'编辑动态');
INSERT INTO `qt_permission` VALUES (36,16,9,10,'筛选动态');
INSERT INTO `qt_permission` VALUES (37,16,9,13,'批量删除动态');
INSERT INTO `qt_permission` VALUES (38,13,10,4,'添加角色视图');
INSERT INTO `qt_permission` VALUES (39,13,10,5,'添加角色');
INSERT INTO `qt_permission` VALUES (40,13,10,6,'删除角色');
INSERT INTO `qt_permission` VALUES (41,13,10,7,'编辑角色视图');
INSERT INTO `qt_permission` VALUES (42,13,10,8,'编辑角色');
INSERT INTO `qt_permission` VALUES (43,13,10,11,'列表角色');
INSERT INTO `qt_permission` VALUES (44,14,11,4,'添加用户角色视图');
INSERT INTO `qt_permission` VALUES (45,14,11,5,'添加用户角色');
INSERT INTO `qt_permission` VALUES (46,14,11,7,'编辑用户角色视图');
INSERT INTO `qt_permission` VALUES (47,14,11,8,'编辑用户角色');
INSERT INTO `qt_permission` VALUES (48,14,11,10,'筛选用户角色');
INSERT INTO `qt_permission` VALUES (49,14,11,13,'批量删除用户角色');
INSERT INTO `qt_permission` VALUES (50,15,12,4,'添加权限视图');
INSERT INTO `qt_permission` VALUES (51,15,12,5,'添加权限');
INSERT INTO `qt_permission` VALUES (52,15,12,7,'编辑权限视图');
INSERT INTO `qt_permission` VALUES (53,15,12,8,'编辑权限');
INSERT INTO `qt_permission` VALUES (54,15,12,10,'筛选权限');
INSERT INTO `qt_permission` VALUES (55,15,12,13,'批量删除权限');
INSERT INTO `qt_permission` VALUES (56,17,13,4,'添加角色权限视图');
INSERT INTO `qt_permission` VALUES (57,17,13,5,'添加角色权限');
INSERT INTO `qt_permission` VALUES (58,17,13,6,'删除角色权限');
INSERT INTO `qt_permission` VALUES (59,17,13,7,'编辑角色权限视图');
INSERT INTO `qt_permission` VALUES (60,17,13,8,'编辑角色权限');
INSERT INTO `qt_permission` VALUES (61,17,13,10,'筛选角色权限');
INSERT INTO `qt_permission` VALUES (62,18,14,22,'查看后台管理主页');
INSERT INTO `qt_permission` VALUES (63,18,14,23,'上传图片');
INSERT INTO `qt_permission` VALUES (64,18,14,24,'用户退出');
INSERT INTO `qt_permission` VALUES (65,19,15,4,'添加学院视图');
INSERT INTO `qt_permission` VALUES (66,19,15,5,'添加学院');
INSERT INTO `qt_permission` VALUES (67,19,15,6,'删除学院');
INSERT INTO `qt_permission` VALUES (68,19,15,7,'编辑学院视图');
INSERT INTO `qt_permission` VALUES (69,19,15,8,'编辑学院');
INSERT INTO `qt_permission` VALUES (70,19,15,11,'列表学院');
INSERT INTO `qt_permission` VALUES (71,9,8,13,'批量删除用户');
INSERT INTO `qt_permission` VALUES (72,20,16,4,'添加部门视图');
INSERT INTO `qt_permission` VALUES (73,20,16,5,'添加部门');
INSERT INTO `qt_permission` VALUES (74,20,16,6,'删除部门');
INSERT INTO `qt_permission` VALUES (75,20,16,7,'编辑部门视图');
INSERT INTO `qt_permission` VALUES (76,20,16,8,'编辑部门');
INSERT INTO `qt_permission` VALUES (77,20,16,11,'列表部门');
INSERT INTO `qt_permission` VALUES (78,7,8,14,'个人资料查看');
INSERT INTO `qt_permission` VALUES (79,7,8,15,'编辑个人资料视图');
INSERT INTO `qt_permission` VALUES (80,7,8,16,'编辑个人资料');
INSERT INTO `qt_permission` VALUES (81,21,17,10,'筛选课表');
INSERT INTO `qt_permission` VALUES (82,21,17,14,'私有数据查看');
INSERT INTO `qt_permission` VALUES (83,21,17,15,'私有数据编辑视图');
INSERT INTO `qt_permission` VALUES (84,21,17,25,'私有数据添加视图');
INSERT INTO `qt_permission` VALUES (85,21,17,26,'私有数据添加');
INSERT INTO `qt_permission` VALUES (86,21,17,16,'私有数据编辑');
INSERT INTO `qt_permission` VALUES (87,21,17,13,'批量删除');
INSERT INTO `qt_permission` VALUES (88,21,17,7,'编辑课表视图');
INSERT INTO `qt_permission` VALUES (89,21,17,8,'编辑课表');
INSERT INTO `qt_permission` VALUES (90,21,17,4,'添加课表视图');
INSERT INTO `qt_permission` VALUES (91,21,17,5,'添加课表');
INSERT INTO `qt_permission` VALUES (92,21,17,2,'单个课表查看');
INSERT INTO `qt_permission` VALUES (93,22,18,4,'添加大事件视图');
INSERT INTO `qt_permission` VALUES (94,22,18,5,'添加大事件');
INSERT INTO `qt_permission` VALUES (95,22,18,7,'编辑大事件视图');
INSERT INTO `qt_permission` VALUES (96,22,18,8,'编辑大事件');
INSERT INTO `qt_permission` VALUES (97,22,18,10,'筛选大事件');
INSERT INTO `qt_permission` VALUES (98,22,18,13,'批量删除大事件');
INSERT INTO `qt_permission` VALUES (99,22,18,2,'查看大事件');
INSERT INTO `qt_permission` VALUES (100,9,8,27,'导出用户基本信息视图');
INSERT INTO `qt_permission` VALUES (101,9,8,28,'导出用户基本信息');
INSERT INTO `qt_permission` VALUES (102,23,19,2,'文件查看');
INSERT INTO `qt_permission` VALUES (103,23,19,5,'文件添加');
INSERT INTO `qt_permission` VALUES (104,23,19,10,'文件筛选');
INSERT INTO `qt_permission` VALUES (105,23,19,18,'私有文件筛选');
INSERT INTO `qt_permission` VALUES (106,23,19,7,'文件编辑视图');
INSERT INTO `qt_permission` VALUES (107,23,19,8,'文件编辑');
INSERT INTO `qt_permission` VALUES (108,23,19,15,'私有文件编辑视图');
INSERT INTO `qt_permission` VALUES (109,23,19,16,'私有文件编辑');
INSERT INTO `qt_permission` VALUES (110,23,19,4,'文件添加视图');
INSERT INTO `qt_permission` VALUES (111,23,19,25,'私有文件添加视图');
INSERT INTO `qt_permission` VALUES (112,23,19,26,'私有文件添加');
INSERT INTO `qt_permission` VALUES (113,23,19,14,'私有文件查看');
INSERT INTO `qt_permission` VALUES (114,23,19,29,'上传文件');

#
# Table structure for table qt_resource
#

CREATE TABLE `qt_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resourcename` varchar(255) DEFAULT NULL COMMENT '资源描述',
  `resourcevalue` varchar(255) DEFAULT NULL COMMENT '资源值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='资源表';

#
# Dumping data for table qt_resource
#

INSERT INTO `qt_resource` VALUES (3,'菜单资源','menu');
INSERT INTO `qt_resource` VALUES (6,'资源','resource');
INSERT INTO `qt_resource` VALUES (7,'操作资源','operator');
INSERT INTO `qt_resource` VALUES (8,'用户资源','user');
INSERT INTO `qt_resource` VALUES (9,'动态资源','news');
INSERT INTO `qt_resource` VALUES (10,'角色资源','role');
INSERT INTO `qt_resource` VALUES (11,'用户角色资源','userrole');
INSERT INTO `qt_resource` VALUES (12,'权限资源','permission');
INSERT INTO `qt_resource` VALUES (13,'角色权限资源','rolepermission');
INSERT INTO `qt_resource` VALUES (14,'全局资源','global');
INSERT INTO `qt_resource` VALUES (15,'学院资源','academy');
INSERT INTO `qt_resource` VALUES (16,'部门资源','department');
INSERT INTO `qt_resource` VALUES (17,'课表资源','ke');
INSERT INTO `qt_resource` VALUES (18,'大事件资源','event');
INSERT INTO `qt_resource` VALUES (19,'文件资源','ffile');

#
# Table structure for table qt_role
#

CREATE TABLE `qt_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='角色表';

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
) ENGINE=InnoDB AUTO_INCREMENT=1594 DEFAULT CHARSET=utf8 COMMENT='角色权限对应表';

#
# Dumping data for table qt_rolepermission
#

INSERT INTO `qt_rolepermission` VALUES (1486,1,10);
INSERT INTO `qt_rolepermission` VALUES (1487,1,100);
INSERT INTO `qt_rolepermission` VALUES (1488,1,101);
INSERT INTO `qt_rolepermission` VALUES (1489,1,102);
INSERT INTO `qt_rolepermission` VALUES (1490,1,103);
INSERT INTO `qt_rolepermission` VALUES (1491,1,104);
INSERT INTO `qt_rolepermission` VALUES (1492,1,105);
INSERT INTO `qt_rolepermission` VALUES (1493,1,106);
INSERT INTO `qt_rolepermission` VALUES (1494,1,107);
INSERT INTO `qt_rolepermission` VALUES (1495,1,108);
INSERT INTO `qt_rolepermission` VALUES (1496,1,109);
INSERT INTO `qt_rolepermission` VALUES (1497,1,11);
INSERT INTO `qt_rolepermission` VALUES (1498,1,110);
INSERT INTO `qt_rolepermission` VALUES (1499,1,111);
INSERT INTO `qt_rolepermission` VALUES (1500,1,112);
INSERT INTO `qt_rolepermission` VALUES (1501,1,113);
INSERT INTO `qt_rolepermission` VALUES (1502,1,114);
INSERT INTO `qt_rolepermission` VALUES (1503,1,12);
INSERT INTO `qt_rolepermission` VALUES (1504,1,13);
INSERT INTO `qt_rolepermission` VALUES (1505,1,14);
INSERT INTO `qt_rolepermission` VALUES (1506,1,15);
INSERT INTO `qt_rolepermission` VALUES (1507,1,16);
INSERT INTO `qt_rolepermission` VALUES (1508,1,17);
INSERT INTO `qt_rolepermission` VALUES (1509,1,18);
INSERT INTO `qt_rolepermission` VALUES (1510,1,19);
INSERT INTO `qt_rolepermission` VALUES (1511,1,20);
INSERT INTO `qt_rolepermission` VALUES (1512,1,21);
INSERT INTO `qt_rolepermission` VALUES (1513,1,22);
INSERT INTO `qt_rolepermission` VALUES (1514,1,23);
INSERT INTO `qt_rolepermission` VALUES (1515,1,24);
INSERT INTO `qt_rolepermission` VALUES (1516,1,25);
INSERT INTO `qt_rolepermission` VALUES (1517,1,26);
INSERT INTO `qt_rolepermission` VALUES (1518,1,27);
INSERT INTO `qt_rolepermission` VALUES (1519,1,28);
INSERT INTO `qt_rolepermission` VALUES (1520,1,29);
INSERT INTO `qt_rolepermission` VALUES (1521,1,30);
INSERT INTO `qt_rolepermission` VALUES (1522,1,31);
INSERT INTO `qt_rolepermission` VALUES (1523,1,32);
INSERT INTO `qt_rolepermission` VALUES (1524,1,33);
INSERT INTO `qt_rolepermission` VALUES (1525,1,34);
INSERT INTO `qt_rolepermission` VALUES (1526,1,35);
INSERT INTO `qt_rolepermission` VALUES (1527,1,36);
INSERT INTO `qt_rolepermission` VALUES (1528,1,37);
INSERT INTO `qt_rolepermission` VALUES (1529,1,38);
INSERT INTO `qt_rolepermission` VALUES (1530,1,39);
INSERT INTO `qt_rolepermission` VALUES (1531,1,40);
INSERT INTO `qt_rolepermission` VALUES (1532,1,41);
INSERT INTO `qt_rolepermission` VALUES (1533,1,42);
INSERT INTO `qt_rolepermission` VALUES (1534,1,43);
INSERT INTO `qt_rolepermission` VALUES (1535,1,44);
INSERT INTO `qt_rolepermission` VALUES (1536,1,45);
INSERT INTO `qt_rolepermission` VALUES (1537,1,46);
INSERT INTO `qt_rolepermission` VALUES (1538,1,47);
INSERT INTO `qt_rolepermission` VALUES (1539,1,48);
INSERT INTO `qt_rolepermission` VALUES (1540,1,49);
INSERT INTO `qt_rolepermission` VALUES (1541,1,50);
INSERT INTO `qt_rolepermission` VALUES (1542,1,51);
INSERT INTO `qt_rolepermission` VALUES (1543,1,52);
INSERT INTO `qt_rolepermission` VALUES (1544,1,53);
INSERT INTO `qt_rolepermission` VALUES (1545,1,54);
INSERT INTO `qt_rolepermission` VALUES (1546,1,55);
INSERT INTO `qt_rolepermission` VALUES (1547,1,56);
INSERT INTO `qt_rolepermission` VALUES (1548,1,57);
INSERT INTO `qt_rolepermission` VALUES (1549,1,58);
INSERT INTO `qt_rolepermission` VALUES (1550,1,59);
INSERT INTO `qt_rolepermission` VALUES (1551,1,60);
INSERT INTO `qt_rolepermission` VALUES (1552,1,61);
INSERT INTO `qt_rolepermission` VALUES (1553,1,62);
INSERT INTO `qt_rolepermission` VALUES (1554,1,63);
INSERT INTO `qt_rolepermission` VALUES (1555,1,64);
INSERT INTO `qt_rolepermission` VALUES (1556,1,65);
INSERT INTO `qt_rolepermission` VALUES (1557,1,66);
INSERT INTO `qt_rolepermission` VALUES (1558,1,67);
INSERT INTO `qt_rolepermission` VALUES (1559,1,68);
INSERT INTO `qt_rolepermission` VALUES (1560,1,69);
INSERT INTO `qt_rolepermission` VALUES (1561,1,7);
INSERT INTO `qt_rolepermission` VALUES (1562,1,70);
INSERT INTO `qt_rolepermission` VALUES (1563,1,71);
INSERT INTO `qt_rolepermission` VALUES (1564,1,72);
INSERT INTO `qt_rolepermission` VALUES (1565,1,73);
INSERT INTO `qt_rolepermission` VALUES (1566,1,74);
INSERT INTO `qt_rolepermission` VALUES (1567,1,75);
INSERT INTO `qt_rolepermission` VALUES (1568,1,76);
INSERT INTO `qt_rolepermission` VALUES (1569,1,77);
INSERT INTO `qt_rolepermission` VALUES (1570,1,78);
INSERT INTO `qt_rolepermission` VALUES (1571,1,79);
INSERT INTO `qt_rolepermission` VALUES (1572,1,8);
INSERT INTO `qt_rolepermission` VALUES (1573,1,80);
INSERT INTO `qt_rolepermission` VALUES (1574,1,81);
INSERT INTO `qt_rolepermission` VALUES (1575,1,82);
INSERT INTO `qt_rolepermission` VALUES (1576,1,83);
INSERT INTO `qt_rolepermission` VALUES (1577,1,84);
INSERT INTO `qt_rolepermission` VALUES (1578,1,85);
INSERT INTO `qt_rolepermission` VALUES (1579,1,86);
INSERT INTO `qt_rolepermission` VALUES (1580,1,87);
INSERT INTO `qt_rolepermission` VALUES (1581,1,88);
INSERT INTO `qt_rolepermission` VALUES (1582,1,89);
INSERT INTO `qt_rolepermission` VALUES (1583,1,9);
INSERT INTO `qt_rolepermission` VALUES (1584,1,90);
INSERT INTO `qt_rolepermission` VALUES (1585,1,91);
INSERT INTO `qt_rolepermission` VALUES (1586,1,92);
INSERT INTO `qt_rolepermission` VALUES (1587,1,93);
INSERT INTO `qt_rolepermission` VALUES (1588,1,94);
INSERT INTO `qt_rolepermission` VALUES (1589,1,95);
INSERT INTO `qt_rolepermission` VALUES (1590,1,96);
INSERT INTO `qt_rolepermission` VALUES (1591,1,97);
INSERT INTO `qt_rolepermission` VALUES (1592,1,98);
INSERT INTO `qt_rolepermission` VALUES (1593,1,99);

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
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱地址',
  `cornet` varchar(255) DEFAULT NULL COMMENT '短号',
  `telephone` varchar(255) DEFAULT NULL COMMENT '电话号码长号',
  `academyID` int(11) DEFAULT NULL COMMENT '学院ID',
  `departmentID` int(11) DEFAULT NULL COMMENT '部门ID',
  `major` varchar(255) DEFAULT NULL COMMENT '专业',
  `location` varchar(255) DEFAULT NULL COMMENT '校区',
  `dormitory` varchar(255) DEFAULT NULL COMMENT '宿舍',
  `islock` int(11) DEFAULT '0' COMMENT '是否锁定',
  `bbs` varchar(255) DEFAULT NULL COMMENT '论坛ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Dumping data for table qt_user
#

INSERT INTO `qt_user` VALUES (2,'200826490109','李斌斌','1','2011-09-07 15:16:58','2011-10-10 20:45:00','qingtian16265@163.com','687541','13656667541',4,1,'软件工程(2+3)0801','屏峰','',0,'qingtian16265');
INSERT INTO `qt_user` VALUES (3,'200826490108','李小斌','1','2011-10-02 21:25:56','2011-10-13 14:07:07','qingtian@163.com','1','',0,2,'','','',0,'');
INSERT INTO `qt_user` VALUES (4,'2','2','2','2011-10-02 21:26:08','2011-10-02 21:26:08','2','2','',0,1,'','','',0,NULL);

#
# Table structure for table qt_userrole
#

CREATE TABLE `qt_userrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleID` int(11) DEFAULT NULL COMMENT '角色ID',
  `userID` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='用户角色对应表';

#
# Dumping data for table qt_userrole
#

INSERT INTO `qt_userrole` VALUES (14,1,2);

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
