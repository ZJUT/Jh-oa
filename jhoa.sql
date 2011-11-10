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
# Table structure for table qt_comment
#

CREATE TABLE `qt_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text COMMENT '内容',
  `userID` int(11) DEFAULT NULL COMMENT '评论ID',
  `addtime` timestamp NULL DEFAULT NULL COMMENT '评论时间',
  `fileID` int(11) DEFAULT NULL COMMENT '关联文件ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='文件评论表';

#
# Dumping data for table qt_comment
#

INSERT INTO `qt_comment` VALUES (20,'测试李小斌的评论',2,'2011-10-26 21:06:49',4);
INSERT INTO `qt_comment` VALUES (21,'2',2,'2011-10-26 21:10:01',4);
INSERT INTO `qt_comment` VALUES (22,'什么',2,'2011-10-26 21:11:36',4);

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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='大事件表';

#
# Dumping data for table qt_event
#

INSERT INTO `qt_event` VALUES (9,'精弘的历史2002-2005','<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2002年5月：“精弘苑”网站诞生-www.zjut.com</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2002年10月:iduck接手精弘,第二版精弘苑</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2002年12月:第一版招生网-zs.zjut.com</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2003年2月:第一版就业网-job.zjut.com</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2003年3月:视听开始</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2003年4月:brad加入</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2003年5月:精弘论坛开始bbs.zjut.com</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2003年8月:垃圾加入</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2003年9月：“心灵有约”网站开始运行-heart.zjut.com</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2003年10月:电视台合作《2046》</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2003年12月:新闻网开始-news.zjut.com</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2004年1月:第一次版聚FB</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2004年1月:FICQ开始-ficq.zjut.com</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2004年3月:**论坛喊停</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2004年4月:第二版论坛</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2004年5月：“精弘博客”开始运行-blog.zjut.com</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2004年5月:论坛一周年文集《记录我们的时代》</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2004年5月:第二次版聚FB</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2004年6月 第一次跳蚤市场</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2004年6月:第二版招生网</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2004年9月:第三版论坛(动网Dvbbs)</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2004年9月:第二版就业网<br />\r\n（开发jhurricane 深红的苜蓿即linchunfei zzn）</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2004年10月26日:Feel电台创立（始祖为\"肚子\"、\"IWS\"）-radio.zjut.com，运行一段时间后喊停，在iduck的协调下，收于精弘服务器。12月重新运行</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2005年1月:第四版精弘网络 （开发elong即Zhu kaiyu&nbsp; 深红的苜蓿)</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2005年2月:公共FTP开始-ftp.zjut.com</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2005年4月:直播开始</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2005年5月:两周年MV,版聚FB</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2005年6月：精弘2周年纪念文化衫<br />\r\n9月好像开始推广文化衫，文化衫设计比赛，爪爪也参加了。不过大家说太幼稚了，囧</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><a href=\"http://blog.myzjut.org/sites/default/files/08122100201d68fdacb784915f.jpg\" target=\"_blank\" style=\"color:#ab0900;\"><img height=\"371\" width=\"400\" align=\"middle\" rel=\"lightbox\" src=\"http://blog.myzjut.org/sites/default/files/08122100201d68fdacb784915f.jpg\" alt=\"\" style=\"border-top-width:0px;border-right-width:0px;border-bottom-width:0px;border-left-width:0px;border-style:initial;border-color:initial;margin-top:5px;margin-right:5px;margin-bottom:5px;margin-left:5px;\" /></a></p>','2011-11-01 21:40:21');
INSERT INTO `qt_event` VALUES (10,'精弘的历史2006-2007','<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2006年3月 不知道是第几次招新的非常拉风的banner<br />\r\n<a href=\"http://blog.myzjut.org/node/sites/default/files/banner.rar\" style=\"text-decoration:none;color:#ab0900;\">通缉令.rar BY爪爪</a></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2006年4月：毕业生留言板</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><a target=\"_blank\" href=\"http://blog.myzjut.org/sites/default/files/byslyb.jpg\" style=\"text-decoration:none;color:#ab0900;\"><img height=\"250\" width=\"200\" rel=\"lightbox\" src=\"http://blog.myzjut.org/sites/default/files/byslyb.jpg\" alt=\"\" style=\"border-top-width:0px;border-right-width:0px;border-bottom-width:0px;border-left-width:0px;border-style:initial;border-color:initial;margin-top:5px;margin-right:5px;margin-bottom:5px;margin-left:5px;\" /></a></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2006年5月：blog比赛</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><a target=\"_blank\" href=\"http://blog.myzjut.org/sites/default/files/bkbs.jpg\" style=\"text-decoration:none;color:#ab0900;\"><img height=\"280\" width=\"200\" rel=\"lightbox\" src=\"http://blog.myzjut.org/sites/default/files/bkbs.jpg\" alt=\"\" style=\"border-top-width:0px;border-right-width:0px;border-bottom-width:0px;border-left-width:0px;border-style:initial;border-color:initial;margin-top:5px;margin-right:5px;margin-bottom:5px;margin-left:5px;\" /></a></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2006年5月：FICQ经常出现问题，然后被关闭</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2006年5月：第1版学生邮件开始运行-mail.zjut.com</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2006年7月：第2版博客-http://blog.zjut.com/index.html</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2006下半年 ：站长大土土毕业离开，技术总监小影、落日的午后离开</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2006下半年： 雨阳继任站长、dweng加入</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2006年9月：吼吼(OHSC)、咩咩加入</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2006年11月：代替“Ficq”的“Jicq”开始运行-jicq.zjut.com</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2006年12月：FEEL电台真情祝福活动</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2007年4月：第四版论坛(php discuz6.0)开始运行-bbs.zjut.com</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><a target=\"_blank\" href=\"http://blog.myzjut.org/sites/default/files/oldbbs.png\" style=\"text-decoration:none;color:#ab0900;\"><img height=\"395\" width=\"200\" rel=\"lightbox\" src=\"http://blog.myzjut.org/sites/default/files/oldbbs.png\" alt=\"\" style=\"border-top-width:0px;border-right-width:0px;border-bottom-width:0px;border-left-width:0px;border-style:initial;border-color:initial;margin-top:5px;margin-right:5px;margin-bottom:5px;margin-left:5px;\" /></a></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2007年6月：“新生论坛”升级</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2007年9月：村长开发新版“毕业生留言”</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2007年9月：论坛升级到Discuz6.1</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2007年10月：电台服务器系统升级</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2007年10月：精弘资讯站内测，并与人文日新社合作。同时该系统自带的X-Space成为新博客系统。</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2007年11月：第一版精弘下载站开始运行-down.zjut.com</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2007年11月：“松鹤斋”并入，改名“精弘下载站”</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><a target=\"_blank\" href=\"http://blog.myzjut.org/sites/default/files/downsite.png\" style=\"text-decoration:none;color:#ab0900;\"><img height=\"172\" width=\"200\" alt=\"\" rel=\"lightbox\" src=\"http://blog.myzjut.org/sites/default/files/downsite.png\" style=\"border-top-width:0px;border-right-width:0px;border-bottom-width:0px;border-left-width:0px;border-style:initial;border-color:initial;margin-top:5px;margin-right:5px;margin-bottom:5px;margin-left:5px;\" /></a></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2007年11月：“精弘邮件”开始内测运行</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2007年11月：第2版学生邮件开始运行-mail.zjut.com</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2007年11月：团队博客-team.zjut.com开通</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><a href=\"http://blog.myzjut.org/sites/default/files/teamblog.png\" style=\"text-decoration:none;color:#ab0900;\"><img height=\"174\" width=\"200\" rel=\"lightbox\" src=\"http://blog.myzjut.org/sites/default/files/teamblog.png\" alt=\"\" style=\"border-top-width:0px;border-right-width:0px;border-bottom-width:0px;border-left-width:0px;border-style:initial;border-color:initial;margin-top:5px;margin-right:5px;margin-bottom:5px;margin-left:5px;\" /></a></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2007年12月：与校会合作直播TOP10</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2007年12月：FEEL电台真情祝福活动</p>','2011-11-01 21:42:03');
INSERT INTO `qt_event` VALUES (11,'精弘的历史2008-2009','<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2008年01月：站长雨阳离任，技术总监dweng(村长)离任</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2008年01月：吼吼(OHSC)继任站长、咩咩任技术总监</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2008年03月：技术部决定全面投入开源技术，<strong>这是精弘技术团队标志性的转折</strong></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2008年04月：“精弘邮件”改用Linux架构</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><a rel=\"lightbox\" href=\"http://blog.myzjut.org/sites/default/files/extmail.png\" class=\"lightbox-processed\" style=\"text-decoration:none;color:#ab0900;\"><img height=\"95\" width=\"200\" rel=\"lightbox\" alt=\"\" src=\"http://blog.myzjut.org/sites/default/files/extmail.png\" style=\"border-top-width:0px;border-right-width:0px;border-bottom-width:0px;border-left-width:0px;border-style:initial;border-color:initial;margin-top:5px;margin-right:5px;margin-bottom:5px;margin-left:5px;\" /></a></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2008年05月：“ubuntu镜像”开通</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><a href=\"http://blog.myzjut.org/sites/default/files/linuxmirror.png\" style=\"text-decoration:none;color:#ab0900;\"><img height=\"222\" width=\"200\" rel=\"lightbox\" alt=\"\" src=\"http://blog.myzjut.org/sites/default/files/linuxmirror.png\" style=\"border-top-width:0px;border-right-width:0px;border-bottom-width:0px;border-left-width:0px;border-style:initial;border-color:initial;margin-top:5px;margin-right:5px;margin-bottom:5px;margin-left:5px;\" /></a></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2008年07月：服务器重新调整，按运行环境重新分配服务器</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2008年07月：技术部恢复网专被批删的帖子</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2008年09月：给下载站腾出专门的下载服务器</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2008年09月：精弘举办软件自由日系列活动</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><a href=\"http://blog.myzjut.org/sites/default/files/sfd.jpg\" style=\"text-decoration:none;color:#ab0900;\"><img height=\"53\" width=\"200\" alt=\"\" rel=\"lightbox\" src=\"http://blog.myzjut.org/sites/default/files/sfd.jpg\" style=\"border-top-width:0px;border-right-width:0px;border-bottom-width:0px;border-left-width:0px;border-style:initial;border-color:initial;margin-top:5px;margin-right:5px;margin-bottom:5px;margin-left:5px;\" /></a></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2008年09月：精弘部门结构重组</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2008年12月：精弘论坛更换服务器，论坛升级</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2008年12月：第五版论坛(php discuz7.0)</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><a target=\"_blank\" href=\"http://blog.myzjut.org/sites/default/files/newbbs.png\" style=\"text-decoration:none;color:#ab0900;\"><img height=\"301\" width=\"200\" alt=\"\" rel=\"lightbox\" src=\"http://blog.myzjut.org/sites/default/files/newbbs.png\" style=\"border-top-width:0px;border-right-width:0px;border-bottom-width:0px;border-left-width:0px;border-style:initial;border-color:initial;margin-top:5px;margin-right:5px;margin-bottom:5px;margin-left:5px;\" /></a></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2008年12月：个人空间升级至精弘家园（Ucenter Home）</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><a target=\"_blank\" href=\"http://blog.myzjut.org/sites/default/files/uchome.png\" style=\"text-decoration:none;color:#ab0900;\"><img width=\"200\" alt=\"\" rel=\"lightbox\" src=\"http://blog.myzjut.org/sites/default/files/uchome.png\" style=\"border-top-width:0px;border-right-width:0px;border-bottom-width:0px;border-left-width:0px;border-style:initial;border-color:initial;margin-top:5px;margin-right:5px;margin-bottom:5px;margin-left:5px;\" /></a></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2008年12月：FEEL电台真情祝福活动</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2009年01月：电台服务器报废。</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2009年02月：电台使用mms直播系统。</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2009年02月：与夜语DV社合作影片《火花》</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2009年03月：FTP搜素引擎（WEB版FTP浏览器）开通。</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><a target=\"_blank\" href=\"http://blog.myzjut.org/sites/default/files/ftpengine.png\" style=\"text-decoration:none;color:#ab0900;\"><img height=\"170\" width=\"200\" alt=\"\" rel=\"lightbox\" src=\"http://blog.myzjut.org/sites/default/files/ftpengine.png\" style=\"border-top-width:0px;border-right-width:0px;border-bottom-width:0px;border-left-width:0px;border-style:initial;border-color:initial;margin-top:5px;margin-right:5px;margin-bottom:5px;margin-left:5px;\" /></a></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2009年03月：学工部站点竣工-www.xgb.zjut.edu.cn</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><a target=\"_blank\" href=\"http://blog.myzjut.org/sites/default/files/xgbpic.png\" style=\"text-decoration:none;color:#ab0900;\"><img height=\"95\" width=\"200\" alt=\"\" rel=\"lightbox\" src=\"http://blog.myzjut.org/sites/default/files/xgbpic.png\" style=\"border-top-width:0px;border-right-width:0px;border-bottom-width:0px;border-left-width:0px;border-style:initial;border-color:initial;margin-top:5px;margin-right:5px;margin-bottom:5px;margin-left:5px;\" /></a></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2009年03月：内网导航站开通</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><a target=\"_blank\" href=\"http://blog.myzjut.org/sites/default/files/go.png\" style=\"text-decoration:none;color:#ab0900;\"><img height=\"144\" width=\"200\" alt=\"\" rel=\"lightbox\" src=\"http://blog.myzjut.org/sites/default/files/go.png\" style=\"border-top-width:0px;border-right-width:0px;border-bottom-width:0px;border-left-width:0px;border-style:initial;border-color:initial;margin-top:5px;margin-right:5px;margin-bottom:5px;margin-left:5px;\" /></a></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2009年03月：JICQ改版，帐号从原来的name@jicq.zjut.com缩短为name@zjut.com，服务器地址为zjut.com</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2009年03月：推出JICQ新页面，web版客户端正式投入使用</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><a target=\"_blank\" href=\"http://blog.myzjut.org/sites/default/files/jicqnewweb.png\" style=\"text-decoration:none;color:#ab0900;\"><img height=\"167\" width=\"200\" alt=\"\" rel=\"lightbox\" src=\"http://blog.myzjut.org/sites/default/files/jicqnewweb.png\" style=\"border-top-width:0px;border-right-width:0px;border-bottom-width:0px;border-left-width:0px;border-style:initial;border-color:initial;margin-top:5px;margin-right:5px;margin-bottom:5px;margin-left:5px;\" /></a></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2009年04月：资讯站升级成Supesite7.0</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2009年05月：FTP服务取消，并入下载站</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2009年07月：第二版下载站上线</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><a href=\"http://blog.myzjut.org/files/downsite2_3.png\" style=\"text-decoration:none;color:#ab0900;\"><img width=\"200\" height=\"106\" alt=\"\" rel=\"lightbox\" src=\"http://team.zjut.com/files/downsite2_3.png\" style=\"border-top-width:0px;border-right-width:0px;border-bottom-width:0px;border-left-width:0px;border-style:initial;border-color:initial;margin-top:5px;margin-right:5px;margin-bottom:5px;margin-left:5px;\" /></a></p>','2011-11-01 21:43:23');
INSERT INTO `qt_event` VALUES (12,'精弘FTP帐号说明','<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">精弘FTP主要有以下帐号（下面将以ftp://帐号:密码@地址的方式显示）</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">普通帐号：</p>\r\n<p class=\"rteindent1\" style=\"margin-top:10px;margin-bottom:15px;margin-left:40px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">端口均为21</p>\r\n<p class=\"rteindent1\" style=\"margin-top:10px;margin-bottom:15px;margin-left:40px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">匿名帐号&nbsp;<a href=\"ftp://ftp.zjut.com/\" title=\"ftp://ftp.zjut.com\" style=\"text-decoration:none;color:#ab0900;\">ftp://ftp.zjut.com</a></p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">可以查看目录，但除了学习资源外，其他资源都不能下载。</p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">单线程 下载：50KB/S 允许60IP同时在线</p>\r\n<p class=\"rteindent1\" style=\"margin-top:10px;margin-bottom:15px;margin-left:40px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">下载帐号&nbsp;<a href=\"ftp://down:down@ftp.zjut.com/\" title=\"ftp://down:down@ftp.zjut.com\" style=\"text-decoration:none;color:#ab0900;\">ftp://down:down@ftp.zjut.com</a></p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">帐号：down</p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">密码：down</p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">不能查看目录，但可以下载所有资源。因为down帐号限制只有100人，而且连的人比较多，所以一般时候都是人满。请尽量选择在人少的时候连接下载，并且有耐心就行了，相信总会连上的。</p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">单线程 下载：100KB/S 允许70IP同时在线</p>\r\n<p class=\"rteindent1\" style=\"margin-top:10px;margin-bottom:15px;margin-left:40px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">上传帐号&nbsp;<a href=\"ftp://up:up@ftp.zjut.com/\" title=\"ftp://up:up@ftp.zjut.com\" style=\"text-decoration:none;color:#ab0900;\">ftp://up:up@ftp.zjut.com</a></p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">帐号：up</p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">密码：up</p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">只能用来上传</p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">双线程 下载：1500KB/S 允许20IP同时在线</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">特殊帐号：</p>\r\n<p class=\"rteindent1\" style=\"margin-top:10px;margin-bottom:15px;margin-left:40px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">均绑定ip，需要修改ip，请发论坛短信给OHSC</p>\r\n<p class=\"rteindent1\" style=\"margin-top:10px;margin-bottom:15px;margin-left:40px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">影视资源VIP&nbsp;<a href=\"ftp://yingshi:yingshi@ftp.zjut.com/\" title=\"ftp://yingshi:yingshi@ftp.zjut.com\" style=\"text-decoration:none;color:#ab0900;\">ftp://yingshi:yingshi@ftp.zjut.com</a></p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">帐号：yingshi</p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">密码：yingshi</p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">由精弘论坛的影视资源版块版主定期评定，奖励对资源版块有贡献的人员，有效期一个月。</p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">单线程 下载：250KB/S 允许10IP同时在线</p>\r\n<p class=\"rteindent1\" style=\"margin-top:10px;margin-bottom:15px;margin-left:40px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">动漫资源VIP&nbsp;<a href=\"ftp://dongman:dongman@ftp.zjut.com/\" title=\"ftp://dongman:dongman@ftp.zjut.com\" style=\"text-decoration:none;color:#ab0900;\">ftp://dongman:dongman@ftp.zjut.com</a></p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">帐号：dongman</p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">密码：dongman</p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">由精弘论坛的影视资源版块版主定期评定，奖励对资源版块有贡献的人员，有效期一个月。</p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">单线程 下载：250KB/S 允许10IP同时在线</p>\r\n<p class=\"rteindent1\" style=\"margin-top:10px;margin-bottom:15px;margin-left:40px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">优秀版主/超级版主/元老&nbsp;<a href=\"ftp://super:super@ftp.zjut.com/\" title=\"ftp://super:super@ftp.zjut.com\" style=\"text-decoration:none;color:#ab0900;\">ftp://super:super@ftp.zjut.com</a></p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">帐号：super</p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">密码：super</p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">被超级版主及精弘管理员评为优秀版主的，奖励spuer账号。优秀版主的使用有效期为一个月。超级版主的使用有效期至职位取消为止。元老有效期至其毕业离校。<br />\r\n单线程 下载：150KB/S 允许4IP同时在线</p>\r\n<p class=\"rteindent1\" style=\"margin-top:10px;margin-bottom:15px;margin-left:40px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">普通版主/贵宾&nbsp;<a href=\"ftp://banzhu:banzhu@ftp.zjut.com/\" title=\"ftp://banzhu:banzhu@ftp.zjut.com\" style=\"text-decoration:none;color:#ab0900;\">ftp://banzhu:banzhu@ftp.zjut.com</a></p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">帐号：banzhu</p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">密码：banzhu</p>\r\n<p class=\"rteindent2\" style=\"margin-top:10px;margin-bottom:15px;margin-left:80px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">所有版主均可以使用改帐号。版主的使用有效期至职位取消为止。贵宾有效期至毕业离校。</p>','2011-11-01 21:44:03');
INSERT INTO `qt_event` VALUES (13,'精弘网络邮件系统非本科生申请说明','<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">由于我们精弘只有本科生的数据，所以请其他需要开通邮箱的同学以班级为单位向我们精弘网络提交申请。请下载下面的文件。打印并填写《精弘网络邮件系统开通申请》再由学院盖章，然后填写《申请名单》，附上姓名和联系电话发送至myzjut@zjut.com。</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">我们收到邮件后会尽快联系您，并获取您手中的《精弘网络邮件系统开通申请》。待我们审核完毕后会把申请的邮箱一次性开通。</p>\r\n<table class=\"sticky-header\" style=\"margin-top:0px;background-image:initial;background-attachment:initial;background-origin:initial;background-clip:initial;color:#333333;font-family:Arial;font-size:13px;position:fixed;top:0px;width:451px;left:450px;visibility:hidden;\"><thead><tr><th style=\"text-align:left;padding-right:1em;border-bottom-width:3px;border-bottom-style:solid;border-bottom-color:#cccccc;width:391px;\"></th>\r\n<th style=\"text-align:left;padding-right:1em;border-bottom-width:3px;border-bottom-style:solid;border-bottom-color:#cccccc;width:32px;\"></th>\r\n</tr>\r\n</thead></table>\r\n<table id=\"attachments\" class=\"sticky-enabled sticky-table\" style=\"color:#333333;font-family:Arial;font-size:13px;\"><thead class=\"tableHeader-processed\"><tr><th style=\"text-align:left;padding-right:1em;border-bottom-width:3px;border-bottom-style:solid;border-bottom-color:#cccccc;\">附件</th>\r\n<th style=\"text-align:left;padding-right:1em;border-bottom-width:3px;border-bottom-style:solid;border-bottom-color:#cccccc;\">大小</th>\r\n</tr>\r\n</thead><tbody style=\"border-top-width:1px;border-top-style:solid;border-top-color:#cccccc;\"><tr class=\"odd\" style=\"background-color:#eeeeee;border-bottom-width:1px;border-bottom-style:solid;border-bottom-color:#cccccc;padding-top:0.1em;padding-right:0.6em;padding-bottom:0.1em;padding-left:0.6em;\"><td style=\"padding-top:0.3em;padding-right:0.3em;padding-bottom:0.3em;padding-left:0.3em;\"><a href=\"http://blog.myzjut.org/files/Jing_Hong_Wang_Luo_You_Jian_Xi_Tong_Kai_Tong_Shen_Qing_.rar\" style=\"text-decoration:none;color:#ab0900;\">Jing_Hong_Wang_Luo_You_Jian_Xi_Tong_Kai_Tong_Shen_Qing_.rar</a></td>\r\n<td style=\"padding-top:0.3em;padding-right:0.3em;padding-bottom:0.3em;padding-left:0.3em;\">4.1 KB</td>\r\n</tr>\r\n</tbody>\r\n</table>','2011-11-01 21:44:37');
INSERT INTO `qt_event` VALUES (14,'使用迅雷下载精弘FTP的简易方法','<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">此方法是利用我们精弘FTP的web站点来下载文件。好处是非常简单，您不需要安装特殊的ftp软件，只要您电脑里有浏览器和迅雷/flashget，就可以轻松地开始下载。坏处是web站点的信息每天只更新一次，所以文件信息不是实时的。</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">具体方法：</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">1.请用您的浏览器访问<a href=\"http://ftp.zjut.com/\" style=\"text-decoration:none;color:#ab0900;\">http://ftp.zjut.com</a>（注意前面是http://而不是ftp://哦）</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2.点击“Web浏览FTP所有内容”<a target=\"_blank\" href=\"http://blog.myzjut.org/sites/default/files/ftp1.png\" style=\"text-decoration:none;color:#ab0900;\"><br />\r\n</a></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">&nbsp;<a href=\"http://blog.myzjut.org/sites/default/files/ftp1.png\" style=\"text-decoration:none;color:#ab0900;\"><img width=\"359\" height=\"200\" alt=\"\" rel=\"lightbox\" src=\"http://blog.myzjut.org/sites/default/files/ftp1.png\" style=\"border-top-width:0px;border-right-width:0px;border-bottom-width:0px;border-left-width:0px;border-style:initial;border-color:initial;margin-top:5px;margin-right:5px;margin-bottom:5px;margin-left:5px;\" /></a></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">3.找到您想下载的文件</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><a target=\"_blank\" href=\"http://blog.myzjut.org/sites/default/files/ftp2.png\" style=\"text-decoration:none;color:#ab0900;\"><img width=\"564\" height=\"200\" alt=\"\" class=\"triggerclass\" rel=\"lightbox\" src=\"http://blog.myzjut.org/sites/default/files/ftp2.png\" style=\"border-top-width:0px;border-right-width:0px;border-bottom-width:0px;border-left-width:0px;border-style:initial;border-color:initial;margin-top:5px;margin-right:5px;margin-bottom:5px;margin-left:5px;\" /></a></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">4.右键点击那个文件的连接，选择使用迅雷下载</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><a target=\"_blank\" href=\"http://blog.myzjut.org/sites/default/files/ftp3.png\" style=\"text-decoration:none;color:#ab0900;\"><img width=\"153\" height=\"200\" alt=\"\" rel=\"lightbox\" src=\"http://blog.myzjut.org/sites/default/files/ftp3.png\" style=\"border-top-width:0px;border-right-width:0px;border-bottom-width:0px;border-left-width:0px;border-style:initial;border-color:initial;margin-top:5px;margin-right:5px;margin-bottom:5px;margin-left:5px;\" /></a></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">5.勾选“登陆到服务器”，并填写帐号密码，如果您没有vip帐号，请都填写down。原始线程数改成1，否则您极有可能被封哦。改好后就可以开始下载了，但是ftp服务器对同时下载人数是有限制的，您不一定马上就可以开始下载。此时您只要把迅雷的任务开着，到轮到您的时候，迅雷就会开始下载。</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><a target=\"_blank\" href=\"http://blog.myzjut.org/sites/default/files/ftp4.png\" style=\"text-decoration:none;color:#ab0900;\"><img width=\"370\" height=\"400\" alt=\"\" rel=\"lightbox\" src=\"http://blog.myzjut.org/sites/default/files/ftp4.png\" style=\"border-top-width:0px;border-right-width:0px;border-bottom-width:0px;border-left-width:0px;border-style:initial;border-color:initial;margin-top:5px;margin-right:5px;margin-bottom:5px;margin-left:5px;\" /></a></p>','2011-11-01 21:44:59');
INSERT INTO `qt_event` VALUES (15,'精弘FTP上传资源及获得VIP帐号说明','<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">精弘FTP的VIP帐号是赠给对精弘FTP有贡献朋友使用的。有效期一个月，每个月会依据会员的贡献评一次VIP，有若干名额，具体参见相关资源板块的说明。</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">一、如何上传？</p>\r\n<p class=\"rteindent1\" style=\"margin-top:10px;margin-bottom:15px;margin-left:40px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">1.准备要上传的文件，修改文件名，在文件名末尾注明您的论坛id</p>\r\n<p class=\"rteindent1\" style=\"margin-top:10px;margin-bottom:15px;margin-left:40px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2.请使用FTP工具（推荐）FlashFXP登陆ftp.zjut.com，账号密码都是up</p>\r\n<p class=\"rteindent1\" style=\"margin-top:10px;margin-bottom:15px;margin-left:40px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">3.选择相应的目录，传上你要传的文件</p>\r\n<p class=\"rteindent1\" style=\"margin-top:10px;margin-bottom:15px;margin-left:40px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">4.去对应版块发帖说明（这步很重要，这是评价您贡献的依据）</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">二、哪些板块可以发帖说明？</p>\r\n<p class=\"rteindent1\" style=\"margin-top:10px;margin-bottom:15px;margin-left:40px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">1.<a href=\"http://bbs.zjut.com/forumdisplay.php?fid=186\" style=\"text-decoration:none;color:#ab0900;\">影视资源</a></p>\r\n<p class=\"rteindent1\" style=\"margin-top:10px;margin-bottom:15px;margin-left:40px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">2.<a href=\"http://bbs.zjut.com/forumdisplay.php?fid=227\" style=\"text-decoration:none;color:#ab0900;\">动漫资源</a></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">三、如何评价贡献？</p>\r\n<p class=\"rteindent1\" style=\"margin-top:10px;margin-bottom:15px;margin-left:40px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">每个月末，资源板块的斑竹都会统计该板块用户在该月的上传的资源量，并以此排名，选择优秀的会员。这些资源量包括内网资源和外网资源。也就是说无论您是否在精弘ftp上传文件，只要您在资源板块发布资源，您就有贡献。</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">四、资源VIP有哪些？</p>\r\n<p class=\"rteindent1\" style=\"margin-top:10px;margin-bottom:15px;margin-left:40px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">影视资源VIP</p>\r\n<p class=\"rteindent1\" style=\"margin-top:10px;margin-bottom:15px;margin-left:40px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">动漫资源VIP</p>\r\n<p class=\"rteindent1\" style=\"margin-top:10px;margin-bottom:15px;margin-left:40px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">(帐号密码参见“<a href=\"http://team.zjut.com/MyzjutFTP-id\" style=\"text-decoration:none;color:#ab0900;\">精弘FTP帐号说明</a>”)</p>','2011-11-01 21:45:14');
INSERT INTO `qt_event` VALUES (16,'关闭全文搜索功能','<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">近来论坛比较卡大家也看到了。<br />\r\n发现全文搜索对服务器数据库的压力很大。</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">经过和各位版主的商量，<br />\r\n所以我们决定先关闭论坛的全文搜索功能一段时间。</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">这次涉及的用户组有：管理员，超级版主，版主，学校部门，优秀版主，实习版主，池塘管理员，优秀会员，精弘网络团队，精弘元老。</p>','2011-11-01 21:45:29');
INSERT INTO `qt_event` VALUES (17,'论坛勋章','<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><em id=\"authorposton7832843\">2009-9-30</em></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><em>开辟了论坛勋章可以买卖的先河，</em></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><em>可以买卖的勋章有星座，情侣勋章。</em></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><em>用JHB买卖。标价999.</em></p>','2011-11-01 21:45:44');
INSERT INTO `qt_event` VALUES (18,'精弘广告模式的尝试','<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">这学期尝试了精弘商铺的广告模式~</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">广告模式的平台系统将会在下学期与广告商和用户见面！</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">整个模式采用adsense&nbsp; 的数据显示方式，但加入精弘本身的特色，一步步将它发展起来，一个部门的崛起，得有合适的流动资金，希望我们的这一步能得到大家的支持！</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">--晴天</p>','2011-11-01 21:45:59');
INSERT INTO `qt_event` VALUES (19,'精弘网络2009/2010起部门划分','<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">精弘网络从2009/2010夏天开始，办公室定在东17架空层【认准精弘网络的门牌】</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">精弘网络现组织：</p>\r\n<ol style=\"margin-left:35px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><li>常务组【人力资源调配，财务管理】</li>\r\n<li>技术部<ol style=\"margin-left:35px;\"><li>服务器管理【linux,mysql】</li>\r\n<li>视觉传达【photoshop】</li>\r\n<li>后台程序开发【Jsp,Php,Python】</li>\r\n<li>前端开发【xhtml,javascript,css】</li>\r\n</ol>\r\n</li>\r\n<li>产品部【资讯，论坛，商铺，家园，下载，电台】【服务包括:导航，邮箱，开源镜像，即时通讯】</li>\r\n</ol>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">&nbsp;</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">主要短缺人才：<strong>服务器管理人员，程序开发人员，视觉传达人员</strong></p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">对精弘有兴趣，对技术有兴趣的同学都可以联系我们！可以在论坛短消息我们，ID:qingtian16265,【技术部】</p>','2011-11-01 21:46:13');
INSERT INTO `qt_event` VALUES (20,'精弘论坛','<p class=\"p15\" style=\"margin-top:0pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\"><span style=\"font-size:10.5pt;font-family:宋体;\">一、&nbsp;</span><span style=\"font-size:10.5pt;font-family:宋体;\">精弘论坛是工大师生信息共享、学术交流、娱乐休闲的网络互动平台。2003年5月在校领导的支持下，精弘论坛正式建立，并申请到了“bbs.zjut.edu.cn”域名，标志了其官方论坛的地位。论坛以服务同学为宗旨，不断推陈出新，经过几代人的不懈努力，已发展成为校内具有很高影响力的网络互动平台。</span></p>\r\n<p class=\"p15\" style=\"margin-top:0pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\">&nbsp;</p>\r\n<p class=\"p15\" style=\"margin-top:0pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\"><span style=\"font-size:10.5pt;font-family:宋体;\"><o:p></o:p></span></p>\r\n<p class=\"p15\" style=\"margin-top:0pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\"><span style=\"color:#000000;font-weight:bold;font-size:10.5pt;font-family:宋体;\">二、&nbsp;</span><span style=\"color:#000000;font-weight:bold;font-size:10.5pt;font-family:宋体;\">口号</span><span style=\"color:#000000;font-size:10.5pt;font-family:宋体;\">：</span><span style=\"font-size:10.5pt;font-family:宋体;\">精弘论坛，我们的交流平台！</span><span style=\"color:#000000;font-size:10.5pt;font-family:宋体;\"><o:p></o:p></span></p>\r\n<p class=\"p15\" style=\"margin-top:0pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\">&nbsp;</p>\r\n<p class=\"p16\" style=\"margin-top:0pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\"><span style=\"color:#000000;font-weight:bold;font-size:10.5pt;font-family:宋体;\">三、&nbsp;</span><span style=\"color:#000000;font-weight:bold;font-size:10.5pt;font-family:宋体;\">组织架构：</span><span style=\"color:#000000;font-size:10.5pt;font-family:宋体;\"><o:p></o:p></span></p>\r\n<p class=\"p15\" style=\"margin-top:0pt;margin-bottom:0pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><br />\r\n<span style=\"font-weight:bold;font-size:10.5pt;font-family:宋体;\"><o:p></o:p></span></p>\r\n<p class=\"p0\" style=\"margin-top:0pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\"><span style=\"font-size:10.5pt;font-family:宋体;\">1.&nbsp;</span><span style=\"font-size:10.5pt;font-family:宋体;\">校园生活区：校园生活区是工大学子分享校园生活的喜乐、校内团体组织的活动宣传平台。在这里可以了解到很多的校园信息，而其中新手报到、暑期实践、选课交流以及毕业留言版块是时节性的热门版块，二手版块更是让同学间的物品转让也有了方便的平台，同乡联谊会、心灵家园以及情感空间的存在，更是丰富了大家在校园生活力多姿多彩的生活。</span><span style=\"font-size:10.5pt;font-family:宋体;\"><o:p></o:p></span></p>\r\n<p class=\"p0\" style=\"margin-top:0pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\"><span style=\"font-size:10.5pt;font-family:宋体;\">2.&nbsp;</span><span style=\"font-size:10.5pt;font-family:宋体;\">电脑网络区：电脑网络区是学校电脑网络技术的交流平台。实用技术为主服务普通同学，开发技术为辅促进学术交流。比较有针对性的让同学来交流各种技术问题，其中的硬件数码、软件版块更是能很好的解决大家平时学校里遇到的各种技术性难题，编程版块是ACM爱好者聚集的平台，linux则是宣扬开源的精神，与精弘的开源社区联系密切。</span><span style=\"font-size:10.5pt;font-family:宋体;\"><o:p></o:p></span></p>\r\n<p class=\"p0\" style=\"margin-top:0pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\"><span style=\"font-size:10.5pt;font-family:宋体;\">3.&nbsp;</span><span style=\"font-size:10.5pt;font-family:宋体;\">文化艺术区：文化学术区是文学、艺术作品的交流分享平台。这里集中了论坛里大部分原创性的资源与各种文人才子，是同学们学习交流的一个重要场所。文学、读书、摄影、设计版块贴近同学们的生活，寻找志同道合的人，一起探讨交流心得。</span><span style=\"font-size:10.5pt;font-family:宋体;\"><o:p></o:p></span></p>\r\n<p class=\"p0\" style=\"margin-top:0pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\"><span style=\"font-size:10.5pt;font-family:宋体;\">4.&nbsp;</span><span style=\"font-size:10.5pt;font-family:宋体;\">未来规划区：未来规划区是工大学子关于人生规划的交流平台。包括就业，升学，出国等，让大家能在这里找到自己的人生方向和未来发展目标</span><span style=\"font-size:10.5pt;font-family:宋体;\"><o:p></o:p></span></p>\r\n<p class=\"p0\" style=\"margin-top:0pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\"><span style=\"font-size:10.5pt;font-family:宋体;\">5.&nbsp;</span><span style=\"font-size:10.5pt;font-family:宋体;\">学术科学区：学术科学是理性与智慧交锋、学术与科学融汇的交流平台。让工大学子能在这里找到志同道合的人，一起交流，引发学生对于社会时政的关注、培养学生对学术科学的理性思考、提升学生的学术科学水平。</span><span style=\"font-size:10.5pt;font-family:宋体;\"><o:p></o:p></span></p>\r\n<p class=\"p0\" style=\"margin-top:0pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\"><span style=\"font-size:10.5pt;font-family:宋体;\">6.&nbsp;</span><span style=\"font-size:10.5pt;font-family:宋体;\">休闲娱乐区：休闲娱乐区为广大师生提供一个休闲娱乐全方面的交流平台。这里目前有旅游、体育、音乐、影视等多个分类明确的版块，聚集着一批因为共同爱好而相识、相知的青年，在这里互相交流经验与心得。休闲娱乐区还开设了灌水类版块池塘边，营造一个轻松愉悦的氛围以活跃论坛人气。吸引人气，活跃论坛气氛，让学生们能在学习的空余好好放松自己。</span><span style=\"font-size:10.5pt;font-family:宋体;\"><o:p></o:p></span></p>\r\n<p class=\"p0\" style=\"margin-top:0pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\"><span style=\"font-size:10.5pt;font-family:宋体;\">7.&nbsp;</span><span style=\"font-size:10.5pt;font-family:宋体;\">精弘网络以及精弘站务区：精弘网络以及精弘站务区作为宣传精弘的平台与精弘论坛处理日常事务的场所，由论坛负责人直接管辖。其中精弘网络主要以宣传精弘的各个产品为主，开设了各个产品的版块，由产品部门人员管理；还有会员之家是处理精弘一些线上问题，合作事宜则是处理其他组织与精弘网络进行合作的平台。精弘站务区是精弘内部交流的场所，有精弘论坛的斑竹培训基地实习版主版块，也有精弘论坛斑竹们交流讨论的版块，还有精弘网络团队版块供精弘各部门人员进行线上交流。</span></p>','2011-11-01 21:46:27');
INSERT INTO `qt_event` VALUES (21,'开源社区','<p class=\"p15\" style=\"margin-top:0pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\"><span style=\"color:#000000;font-weight:bold;font-size:10.5pt;font-family:宋体;\">一、&nbsp;</span><span style=\"color:#000000;font-weight:bold;font-size:10.5pt;font-family:宋体;\">简介:</span><span style=\"color:#000000;font-size:10.5pt;font-family:宋体;\">&nbsp;开源社区是一个专注于发展工大开源文化的组织。开源社区成立于2008年9月，为工大师生提供免费的Linux镜像服务、邮箱服务以及即时通讯软件等，让更多的人关注开源技术，用开源技术服务更多人。同时，开源社区作为火狐校园大使，积极开展了火狐社区的开源活动。开源社区作为Google杭州技术用户组（GTUG）的合作组织，与杭州本地的Google开源技术爱好者紧密合作。</span></p>\r\n<p class=\"p15\" style=\"margin-top:0pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\">&nbsp;</p>\r\n<p class=\"p15\" style=\"margin-top:0pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\"><span style=\"color:#000000;font-size:10.5pt;font-family:宋体;\"><o:p></o:p></span></p>\r\n<p class=\"p15\" style=\"margin-top:0pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\"><span style=\"color:#000000;font-weight:bold;font-size:10.5pt;font-family:宋体;\">二、&nbsp;</span><span style=\"color:#000000;font-weight:bold;font-size:10.5pt;font-family:宋体;\">口号</span><span style=\"color:#000000;font-size:10.5pt;font-family:宋体;\">：自由，分享！</span></p>\r\n<p class=\"p15\" style=\"margin-top:0pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\">&nbsp;</p>\r\n<p class=\"p15\" style=\"margin-top:0pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\"><span style=\"color:#000000;font-size:10.5pt;font-family:宋体;\"><o:p></o:p></span></p>\r\n<p class=\"p15\" style=\"margin-top:0pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\"><span style=\"color:#000000;font-weight:bold;font-size:10.5pt;font-family:宋体;\">三、&nbsp;</span><span style=\"color:#000000;font-weight:bold;font-size:10.5pt;font-family:宋体;\">组织架构：</span><span style=\"color:#000000;font-size:10.5pt;font-family:宋体;\"><o:p></o:p></span></p>\r\n<p class=\"p0\" style=\"margin-top:0pt;margin-bottom:0pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><br />\r\n<span style=\"font-size:10.5pt;font-family:宋体;\"><o:p></o:p></span></p>\r\n<p class=\"p0\" style=\"margin-top:0pt;margin-bottom:0pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\"><span style=\"font-size:10.5pt;font-family:宋体;\"><o:p></o:p></span></p>\r\n<p class=\"p0\" style=\"margin-top:0pt;margin-right:16.275pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\"><span style=\"font-size:10.5pt;font-family:宋体;\">1.&nbsp;</span><span style=\"font-size:10.5pt;font-family:宋体;\">即时通讯组：维护即时通讯软件的服务，保证软件的正常使用。</span><span style=\"font-size:10.5pt;font-family:宋体;\"><o:p></o:p></span></p>\r\n<p class=\"p0\" style=\"margin-top:0pt;margin-right:16.275pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\"><span style=\"font-size:10.5pt;font-family:宋体;\">2.&nbsp;</span><span style=\"font-size:10.5pt;font-family:宋体;\">Ubuntu镜像组：维护内网的Ubuntu镜像，保持镜像的及时更新和稳定。</span><span style=\"font-size:10.5pt;font-family:宋体;\"><o:p></o:p></span></p>\r\n<p class=\"p0\" style=\"margin-top:0pt;margin-right:16.275pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\"><span style=\"font-size:10.5pt;font-family:宋体;\">3.&nbsp;</span><span style=\"font-size:10.5pt;font-family:宋体;\">学生邮箱组：维护学生邮箱服务，解决同学使用邮箱碰到的各类问题。</span><span style=\"font-size:10.5pt;font-family:宋体;\"><o:p></o:p></span></p>\r\n<p class=\"p0\" style=\"margin-top:0pt;margin-right:16.275pt;margin-bottom:0pt;margin-left:21pt;color:#333333;font-family:Arial;font-size:13px;line-height:normal;text-indent:-21pt;\"><span style=\"font-size:10.5pt;font-family:宋体;\">4.&nbsp;</span><span style=\"font-size:10.5pt;font-family:宋体;\">开源推广：发展推广工大的开源文化，让更多的人关注开源技术，用开源技术服务更多人。</span></p>','2011-11-01 21:46:43');
INSERT INTO `qt_event` VALUES (22,'09/10学年精弘网络发展规划','<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">精弘在08/09主要发展目标是拓展业务。现在精弘的主要产品已经足够，下一阶段需要做的是巩固现有服务，深化组织改革，提高工作效率，改善成员福利。</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">一、组织结构<br />\r\n组织规划图见附件。</p>\r\n<p style=\"margin-top:10px;margin-bottom:15px;color:#333333;font-family:Arial;font-size:13px;line-height:normal;\">在上阶段改革后，精弘运作效率有显著改善，但是还存在各种问题，比如线下团队闲置问题。下阶段精弘还需要继续改革。新的组织结构充分参考了外部企业，并结合了现有的结构。新的组织简化为3大部门，分别由CTO、COO、日常事务总监主管。<br />\r\n&nbsp;<br />\r\n新组织进一步缩减了线下部门，改名常务部。主要负责精弘的日常事务。该部门一般由新招收的学生为主。在常务部门工作同时，接受技术部门、产品部门的指导，学习技术、业务知识。学习结束后，经考核转入其他部门。<br />\r\n&nbsp;<br />\r\n下一阶段，精弘主打六大产品：论坛、家园、下载、商铺、电台、资讯。每个部门专设产品经理，负责整个产品的发展，对产品负责。每个部门专设一个技术小团队，由服务器管理员、程序开发、视觉设计、前端开发组成。这些人均来自技术部门，日常工作听从产品经理的安排，特殊项目时，由CTO统一管理。<br />\r\n这样做的好处是，充分利用人力资源，确保各产品都有人在维护。<br />\r\n&nbsp;<br />\r\n技术部门，平时主要工作有三部分，一是分成6个团队，维护现有产品；二是组织人员开发新产品或现有产品的扩展；三是技术培训。<br />\r\n&nbsp;<br />\r\n二、产品<br />\r\n精弘发展至今，陆续推出过很多产品，但是随着互联网的发展，有些产品已经不能满足现有用户的需求。这些产品该关的要关，该并的要并。这学期初首要工作就是关停[精弘博客]，目前关停工作已就绪，等待用户备份完后，月底关停。<br />\r\n&nbsp;<br />\r\n精弘论坛，是我们精弘的老牌产品，发展到现在累积了很多经验。下一阶段任务仍就是完善各种管理规则，包括管理会员和管理版主，要求处罚、奖励时有规可依。同时版主的考评、工资奖励也要开始准备起来。<br />\r\n&nbsp;<br />\r\n精弘下载，是上阶段推出的产品，受到了同学们的好评。下一阶段，我们将把精弘FTP合并到精弘下载，实现资源下载的统一，降低用户下载资源的技术门槛。目前这部门即将面临的问题是服务器老化，空间不足，带宽不够。<br />\r\n&nbsp;<br />\r\n精弘商铺，是下阶段要正式运行的新产品。目前运行方案还在规划中。到时候需要设立勤工岗位，定点值班处理学生的备案。<br />\r\n&nbsp;<br />\r\n精弘家园，由精弘博客转型而来，是一个娱乐性质比较强的站点。下阶段的主要工作是，建立管理团队，监督不良信息、开展各类活动。<br />\r\n&nbsp;<br />\r\nFEEL电台，没有太大的变化。下阶段主要是为电台开发一个收听门户。<br />\r\n&nbsp;<br />\r\n精弘资讯，下阶段的主要工作是组建记者团。<br />\r\n&nbsp;<br />\r\n另外，FEEL电台、精弘资讯可能要与校内某个DV社进行合作，发展视频媒体。具体细节还在策划中。<br />\r\n&nbsp;<br />\r\n三、服务器<br />\r\n服务器搬迁的事情，还不能进行，网络中心的机柜还没有买来。我们还需要等待一段时间。<br />\r\n&nbsp;<br />\r\n招生就业服务器搬迁的评估工作已经结束。我们发现招生网改动太多，代码太乱，要迁移过去的难度太大，建议重新开发招生网。目前我们正在尝试搬迁就业网。如果招生网需要重新开发，那么可能需要消耗一年工期。这段时间新老服务器都不可能闲置下来。我们依然会面对服务器紧缺问题。<br />\r\n&nbsp;<br />\r\n继上学期，FEEL电台的服务器报废后。精弘现在有两台服务器偏老，可能会在下一阶段报废。<br />\r\n&nbsp;<br />\r\n四、工资<br />\r\n精弘在以往，技术和美编是有工资的。不过目前出现了问题，由于工资是稳定的，美编的积极性不高了。平时都忙着做自己的事情，经常推托任务。在下一阶段，我们设想全员分配工资。如果工资可控范围比较大(0-500元)，我们想实行按劳分配，充分调动成员的积极性。<br />\r\n&nbsp;<br />\r\n五、广告<br />\r\n精弘的产品已经丰富，我觉得下一阶段，家园、商铺可以尝试做广告。家园以娱乐为主，商铺以商业为主，这种氛围下做广告，均不会影响学校形象。</p>\r\n<table class=\"sticky-header\" style=\"margin-top:0px;background-image:initial;background-attachment:initial;background-origin:initial;background-clip:initial;color:#333333;font-family:Arial;font-size:13px;position:fixed;top:0px;width:232px;left:450px;visibility:hidden;\"><thead><tr><th style=\"text-align:left;padding-right:1em;border-bottom-width:3px;border-bottom-style:solid;border-bottom-color:#cccccc;width:158px;\"></th>\r\n<th style=\"text-align:left;padding-right:1em;border-bottom-width:3px;border-bottom-style:solid;border-bottom-color:#cccccc;width:46px;\"></th>\r\n</tr>\r\n</thead></table>\r\n<table id=\"attachments\" class=\"sticky-enabled sticky-table\" style=\"color:#333333;font-family:Arial;font-size:13px;\"><thead class=\"tableHeader-processed\"><tr><th style=\"text-align:left;padding-right:1em;border-bottom-width:3px;border-bottom-style:solid;border-bottom-color:#cccccc;\">附件</th>\r\n<th style=\"text-align:left;padding-right:1em;border-bottom-width:3px;border-bottom-style:solid;border-bottom-color:#cccccc;\">大小</th>\r\n</tr>\r\n</thead><tbody style=\"border-top-width:1px;border-top-style:solid;border-top-color:#cccccc;\"><tr class=\"odd\" style=\"background-color:#eeeeee;border-bottom-width:1px;border-bottom-style:solid;border-bottom-color:#cccccc;padding-top:0.1em;padding-right:0.6em;padding-bottom:0.1em;padding-left:0.6em;\"><td style=\"padding-top:0.3em;padding-right:0.3em;padding-bottom:0.3em;padding-left:0.3em;\"><a href=\"http://blog.myzjut.org/files/Jing_Hong_Gui_Hua_Tu_.jpg\" style=\"text-decoration:none;color:#ab0900;\">Jing_Hong_Gui_Hua_Tu_.jpg</a></td>\r\n<td style=\"padding-top:0.3em;padding-right:0.3em;padding-bottom:0.3em;padding-left:0.3em;\">32.59 KB</td>\r\n</tr>\r\n</tbody>\r\n</table>','2011-11-01 21:47:20');

#
# Table structure for table qt_ffile
#

CREATE TABLE `qt_ffile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `filename` varchar(255) DEFAULT NULL COMMENT '文件名',
  `showname` varchar(255) DEFAULT NULL COMMENT '显示名',
  `addtime` timestamp NULL DEFAULT NULL COMMENT '上传时间',
  `userID` int(11) DEFAULT NULL COMMENT '用户ID',
  `size` int(11) DEFAULT NULL COMMENT '大小',
  `suffix` varchar(255) DEFAULT NULL COMMENT '后缀',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='文件表';

#
# Dumping data for table qt_ffile
#

INSERT INTO `qt_ffile` VALUES (1,'/Jh-oa/file/20111110222027_538.jpg','hello','2011-11-10 22:20:48',2,51149,'jpg');

#
# Table structure for table qt_ke
#

CREATE TABLE `qt_ke` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL COMMENT '用户ID',
  `kevalue` varchar(255) DEFAULT NULL COMMENT '课程表值[以0代表没课，1代表有课]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='课表';

#
# Dumping data for table qt_ke
#

INSERT INTO `qt_ke` VALUES (9,2,'00000000100000010000000000001000000100000010000001000000000000010000000000000');
INSERT INTO `qt_ke` VALUES (10,3,'10000001000000000000000000000000000000000000000000000000000000000000000000000');

#
# Table structure for table qt_menu
#

CREATE TABLE `qt_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuname` varchar(255) DEFAULT NULL COMMENT '菜单名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='菜单表';

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
INSERT INTO `qt_menu` VALUES (22,'历史管理');
INSERT INTO `qt_menu` VALUES (23,'文件管理');
INSERT INTO `qt_menu` VALUES (24,'评论管理');
INSERT INTO `qt_menu` VALUES (25,'反馈管理');
INSERT INTO `qt_menu` VALUES (26,'产品管理');
INSERT INTO `qt_menu` VALUES (27,'管理团队成员管理');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新闻动态表';

#
# Dumping data for table qt_news
#


#
# Table structure for table qt_operator
#

CREATE TABLE `qt_operator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `optname` varchar(255) DEFAULT NULL COMMENT '操作描述',
  `optvalue` varchar(255) DEFAULT NULL COMMENT '操作值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='操作表';

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
INSERT INTO `qt_operator` VALUES (30,'私有数据删除','deleteMyself');
INSERT INTO `qt_operator` VALUES (31,'评论列表（文件中使用）','ajaxCommentFilter');
INSERT INTO `qt_operator` VALUES (32,'发表评论（文件详细中使用）','ajaxCommentAdd');
INSERT INTO `qt_operator` VALUES (33,'删除评论（文件详细中使用）','ajaxCommentDelete');

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
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8 COMMENT='权限表';

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
INSERT INTO `qt_permission` VALUES (110,23,19,4,'文件添加视图');
INSERT INTO `qt_permission` VALUES (111,23,19,25,'私有文件添加视图');
INSERT INTO `qt_permission` VALUES (112,23,19,26,'私有文件添加');
INSERT INTO `qt_permission` VALUES (113,23,19,14,'私有文件查看');
INSERT INTO `qt_permission` VALUES (114,23,19,29,'上传文件');
INSERT INTO `qt_permission` VALUES (115,23,19,6,'单个文件删除');
INSERT INTO `qt_permission` VALUES (116,23,19,13,'批量删除文件');
INSERT INTO `qt_permission` VALUES (117,23,19,30,'私有数据删除');
INSERT INTO `qt_permission` VALUES (118,23,19,21,'私有数据批量删除');
INSERT INTO `qt_permission` VALUES (122,18,14,31,'评论列表（文件详细中使用）');
INSERT INTO `qt_permission` VALUES (123,18,14,32,'发表评论（文件详细中使用）');
INSERT INTO `qt_permission` VALUES (124,18,14,33,'删除评论（文件详细中使用）');
INSERT INTO `qt_permission` VALUES (125,25,21,25,'私有反馈添加视图');
INSERT INTO `qt_permission` VALUES (126,25,21,26,'私有反馈添加');
INSERT INTO `qt_permission` VALUES (127,25,21,7,'反馈回复视图');
INSERT INTO `qt_permission` VALUES (128,25,21,8,'反馈回复');
INSERT INTO `qt_permission` VALUES (129,25,21,10,'反馈筛选');
INSERT INTO `qt_permission` VALUES (130,25,21,18,'私有反馈筛选');
INSERT INTO `qt_permission` VALUES (131,25,21,14,'私有反馈查看');
INSERT INTO `qt_permission` VALUES (132,25,21,21,'私有反馈批量删除');
INSERT INTO `qt_permission` VALUES (133,25,21,2,'单条反馈查看');
INSERT INTO `qt_permission` VALUES (134,26,22,4,'添加产品视图');
INSERT INTO `qt_permission` VALUES (135,26,22,5,'添加产品');
INSERT INTO `qt_permission` VALUES (136,26,22,6,'删除产品');
INSERT INTO `qt_permission` VALUES (137,26,22,7,'编辑产品视图');
INSERT INTO `qt_permission` VALUES (138,26,22,8,'编辑产品');
INSERT INTO `qt_permission` VALUES (139,26,22,11,'列表产品');
INSERT INTO `qt_permission` VALUES (140,26,22,29,'上传产品LOGO');
INSERT INTO `qt_permission` VALUES (141,27,23,4,'管理团队成员添加视图');
INSERT INTO `qt_permission` VALUES (142,27,23,5,'管理团队成员添加');
INSERT INTO `qt_permission` VALUES (143,27,23,6,'管理团队成员删除');
INSERT INTO `qt_permission` VALUES (144,27,23,7,'管理团队成员编辑视图');
INSERT INTO `qt_permission` VALUES (145,27,23,8,'管理团队成员编辑');
INSERT INTO `qt_permission` VALUES (146,27,23,10,'管理团队成员筛选');
INSERT INTO `qt_permission` VALUES (147,27,23,29,'管理团队成员头像上传');

#
# Table structure for table qt_product
#

CREATE TABLE `qt_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `logo` varchar(255) DEFAULT NULL COMMENT 'logo图片地址',
  `name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `introduce` text COMMENT '产品介绍',
  `link` varchar(255) DEFAULT NULL COMMENT '产品地址',
  `addtime` timestamp NULL DEFAULT NULL COMMENT '上线时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='产品表';

#
# Dumping data for table qt_product
#

INSERT INTO `qt_product` VALUES (2,'/Jh-oa/product/20111110221912_68.png','论坛','这是一个测试<b>用例</b>','http://bbs.zjut.com','2011-11-10 22:19:13');
INSERT INTO `qt_product` VALUES (3,'/Jh-oa/product/20111106220042_473.jpg','商铺','送往迎来','http://shop.zjut.com','2011-11-06 22:00:54');
INSERT INTO `qt_product` VALUES (4,'/Jh-oa/product/20111106220113_808.jpg','电台','电台&nbsp;&nbsp;&nbsp;&nbsp;','http://feel.zjut.com','2011-11-06 22:01:25');
INSERT INTO `qt_product` VALUES (5,'/Jh-oa/product/20111106220412_875.jpg','下载','下载','http://down.zjut.com','2011-11-06 22:04:24');

#
# Table structure for table qt_resource
#

CREATE TABLE `qt_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resourcename` varchar(255) DEFAULT NULL COMMENT '资源描述',
  `resourcevalue` varchar(255) DEFAULT NULL COMMENT '资源值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='资源表';

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
INSERT INTO `qt_resource` VALUES (20,'评论资源','comment');
INSERT INTO `qt_resource` VALUES (21,'反馈资源','suggest');
INSERT INTO `qt_resource` VALUES (22,'产品资源','product');
INSERT INTO `qt_resource` VALUES (23,'管理团队成员资源','team');

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
) ENGINE=InnoDB AUTO_INCREMENT=3375 DEFAULT CHARSET=utf8 COMMENT='角色权限对应表';

#
# Dumping data for table qt_rolepermission
#

INSERT INTO `qt_rolepermission` VALUES (3087,6,102);
INSERT INTO `qt_rolepermission` VALUES (3088,6,105);
INSERT INTO `qt_rolepermission` VALUES (3089,6,111);
INSERT INTO `qt_rolepermission` VALUES (3090,6,112);
INSERT INTO `qt_rolepermission` VALUES (3091,6,113);
INSERT INTO `qt_rolepermission` VALUES (3092,6,114);
INSERT INTO `qt_rolepermission` VALUES (3093,6,117);
INSERT INTO `qt_rolepermission` VALUES (3094,6,118);
INSERT INTO `qt_rolepermission` VALUES (3095,6,122);
INSERT INTO `qt_rolepermission` VALUES (3096,6,123);
INSERT INTO `qt_rolepermission` VALUES (3097,6,124);
INSERT INTO `qt_rolepermission` VALUES (3098,6,125);
INSERT INTO `qt_rolepermission` VALUES (3099,6,126);
INSERT INTO `qt_rolepermission` VALUES (3100,6,130);
INSERT INTO `qt_rolepermission` VALUES (3101,6,131);
INSERT INTO `qt_rolepermission` VALUES (3102,6,132);
INSERT INTO `qt_rolepermission` VALUES (3103,6,62);
INSERT INTO `qt_rolepermission` VALUES (3104,6,63);
INSERT INTO `qt_rolepermission` VALUES (3105,6,64);
INSERT INTO `qt_rolepermission` VALUES (3106,6,78);
INSERT INTO `qt_rolepermission` VALUES (3107,6,79);
INSERT INTO `qt_rolepermission` VALUES (3108,6,80);
INSERT INTO `qt_rolepermission` VALUES (3109,6,82);
INSERT INTO `qt_rolepermission` VALUES (3110,6,83);
INSERT INTO `qt_rolepermission` VALUES (3111,6,84);
INSERT INTO `qt_rolepermission` VALUES (3112,6,85);
INSERT INTO `qt_rolepermission` VALUES (3113,6,86);
INSERT INTO `qt_rolepermission` VALUES (3241,1,10);
INSERT INTO `qt_rolepermission` VALUES (3242,1,100);
INSERT INTO `qt_rolepermission` VALUES (3243,1,101);
INSERT INTO `qt_rolepermission` VALUES (3244,1,102);
INSERT INTO `qt_rolepermission` VALUES (3245,1,103);
INSERT INTO `qt_rolepermission` VALUES (3246,1,104);
INSERT INTO `qt_rolepermission` VALUES (3247,1,105);
INSERT INTO `qt_rolepermission` VALUES (3248,1,11);
INSERT INTO `qt_rolepermission` VALUES (3249,1,110);
INSERT INTO `qt_rolepermission` VALUES (3250,1,111);
INSERT INTO `qt_rolepermission` VALUES (3251,1,112);
INSERT INTO `qt_rolepermission` VALUES (3252,1,113);
INSERT INTO `qt_rolepermission` VALUES (3253,1,114);
INSERT INTO `qt_rolepermission` VALUES (3254,1,115);
INSERT INTO `qt_rolepermission` VALUES (3255,1,116);
INSERT INTO `qt_rolepermission` VALUES (3256,1,117);
INSERT INTO `qt_rolepermission` VALUES (3257,1,118);
INSERT INTO `qt_rolepermission` VALUES (3258,1,12);
INSERT INTO `qt_rolepermission` VALUES (3259,1,122);
INSERT INTO `qt_rolepermission` VALUES (3260,1,123);
INSERT INTO `qt_rolepermission` VALUES (3261,1,124);
INSERT INTO `qt_rolepermission` VALUES (3262,1,125);
INSERT INTO `qt_rolepermission` VALUES (3263,1,126);
INSERT INTO `qt_rolepermission` VALUES (3264,1,127);
INSERT INTO `qt_rolepermission` VALUES (3265,1,128);
INSERT INTO `qt_rolepermission` VALUES (3266,1,129);
INSERT INTO `qt_rolepermission` VALUES (3267,1,13);
INSERT INTO `qt_rolepermission` VALUES (3268,1,130);
INSERT INTO `qt_rolepermission` VALUES (3269,1,131);
INSERT INTO `qt_rolepermission` VALUES (3270,1,132);
INSERT INTO `qt_rolepermission` VALUES (3271,1,133);
INSERT INTO `qt_rolepermission` VALUES (3272,1,134);
INSERT INTO `qt_rolepermission` VALUES (3273,1,135);
INSERT INTO `qt_rolepermission` VALUES (3274,1,136);
INSERT INTO `qt_rolepermission` VALUES (3275,1,137);
INSERT INTO `qt_rolepermission` VALUES (3276,1,138);
INSERT INTO `qt_rolepermission` VALUES (3277,1,139);
INSERT INTO `qt_rolepermission` VALUES (3278,1,14);
INSERT INTO `qt_rolepermission` VALUES (3279,1,140);
INSERT INTO `qt_rolepermission` VALUES (3280,1,141);
INSERT INTO `qt_rolepermission` VALUES (3281,1,142);
INSERT INTO `qt_rolepermission` VALUES (3282,1,143);
INSERT INTO `qt_rolepermission` VALUES (3283,1,144);
INSERT INTO `qt_rolepermission` VALUES (3284,1,145);
INSERT INTO `qt_rolepermission` VALUES (3285,1,146);
INSERT INTO `qt_rolepermission` VALUES (3286,1,147);
INSERT INTO `qt_rolepermission` VALUES (3287,1,15);
INSERT INTO `qt_rolepermission` VALUES (3288,1,16);
INSERT INTO `qt_rolepermission` VALUES (3289,1,17);
INSERT INTO `qt_rolepermission` VALUES (3290,1,18);
INSERT INTO `qt_rolepermission` VALUES (3291,1,19);
INSERT INTO `qt_rolepermission` VALUES (3292,1,20);
INSERT INTO `qt_rolepermission` VALUES (3293,1,21);
INSERT INTO `qt_rolepermission` VALUES (3294,1,22);
INSERT INTO `qt_rolepermission` VALUES (3295,1,23);
INSERT INTO `qt_rolepermission` VALUES (3296,1,24);
INSERT INTO `qt_rolepermission` VALUES (3297,1,25);
INSERT INTO `qt_rolepermission` VALUES (3298,1,26);
INSERT INTO `qt_rolepermission` VALUES (3299,1,27);
INSERT INTO `qt_rolepermission` VALUES (3300,1,28);
INSERT INTO `qt_rolepermission` VALUES (3301,1,29);
INSERT INTO `qt_rolepermission` VALUES (3302,1,30);
INSERT INTO `qt_rolepermission` VALUES (3303,1,31);
INSERT INTO `qt_rolepermission` VALUES (3304,1,32);
INSERT INTO `qt_rolepermission` VALUES (3305,1,33);
INSERT INTO `qt_rolepermission` VALUES (3306,1,34);
INSERT INTO `qt_rolepermission` VALUES (3307,1,35);
INSERT INTO `qt_rolepermission` VALUES (3308,1,36);
INSERT INTO `qt_rolepermission` VALUES (3309,1,37);
INSERT INTO `qt_rolepermission` VALUES (3310,1,38);
INSERT INTO `qt_rolepermission` VALUES (3311,1,39);
INSERT INTO `qt_rolepermission` VALUES (3312,1,40);
INSERT INTO `qt_rolepermission` VALUES (3313,1,41);
INSERT INTO `qt_rolepermission` VALUES (3314,1,42);
INSERT INTO `qt_rolepermission` VALUES (3315,1,43);
INSERT INTO `qt_rolepermission` VALUES (3316,1,44);
INSERT INTO `qt_rolepermission` VALUES (3317,1,45);
INSERT INTO `qt_rolepermission` VALUES (3318,1,46);
INSERT INTO `qt_rolepermission` VALUES (3319,1,47);
INSERT INTO `qt_rolepermission` VALUES (3320,1,48);
INSERT INTO `qt_rolepermission` VALUES (3321,1,49);
INSERT INTO `qt_rolepermission` VALUES (3322,1,50);
INSERT INTO `qt_rolepermission` VALUES (3323,1,51);
INSERT INTO `qt_rolepermission` VALUES (3324,1,52);
INSERT INTO `qt_rolepermission` VALUES (3325,1,53);
INSERT INTO `qt_rolepermission` VALUES (3326,1,54);
INSERT INTO `qt_rolepermission` VALUES (3327,1,55);
INSERT INTO `qt_rolepermission` VALUES (3328,1,56);
INSERT INTO `qt_rolepermission` VALUES (3329,1,57);
INSERT INTO `qt_rolepermission` VALUES (3330,1,58);
INSERT INTO `qt_rolepermission` VALUES (3331,1,59);
INSERT INTO `qt_rolepermission` VALUES (3332,1,60);
INSERT INTO `qt_rolepermission` VALUES (3333,1,61);
INSERT INTO `qt_rolepermission` VALUES (3334,1,62);
INSERT INTO `qt_rolepermission` VALUES (3335,1,63);
INSERT INTO `qt_rolepermission` VALUES (3336,1,64);
INSERT INTO `qt_rolepermission` VALUES (3337,1,65);
INSERT INTO `qt_rolepermission` VALUES (3338,1,66);
INSERT INTO `qt_rolepermission` VALUES (3339,1,67);
INSERT INTO `qt_rolepermission` VALUES (3340,1,68);
INSERT INTO `qt_rolepermission` VALUES (3341,1,69);
INSERT INTO `qt_rolepermission` VALUES (3342,1,7);
INSERT INTO `qt_rolepermission` VALUES (3343,1,70);
INSERT INTO `qt_rolepermission` VALUES (3344,1,71);
INSERT INTO `qt_rolepermission` VALUES (3345,1,72);
INSERT INTO `qt_rolepermission` VALUES (3346,1,73);
INSERT INTO `qt_rolepermission` VALUES (3347,1,74);
INSERT INTO `qt_rolepermission` VALUES (3348,1,75);
INSERT INTO `qt_rolepermission` VALUES (3349,1,76);
INSERT INTO `qt_rolepermission` VALUES (3350,1,77);
INSERT INTO `qt_rolepermission` VALUES (3351,1,78);
INSERT INTO `qt_rolepermission` VALUES (3352,1,79);
INSERT INTO `qt_rolepermission` VALUES (3353,1,8);
INSERT INTO `qt_rolepermission` VALUES (3354,1,80);
INSERT INTO `qt_rolepermission` VALUES (3355,1,81);
INSERT INTO `qt_rolepermission` VALUES (3356,1,82);
INSERT INTO `qt_rolepermission` VALUES (3357,1,83);
INSERT INTO `qt_rolepermission` VALUES (3358,1,84);
INSERT INTO `qt_rolepermission` VALUES (3359,1,85);
INSERT INTO `qt_rolepermission` VALUES (3360,1,86);
INSERT INTO `qt_rolepermission` VALUES (3361,1,87);
INSERT INTO `qt_rolepermission` VALUES (3362,1,88);
INSERT INTO `qt_rolepermission` VALUES (3363,1,89);
INSERT INTO `qt_rolepermission` VALUES (3364,1,9);
INSERT INTO `qt_rolepermission` VALUES (3365,1,90);
INSERT INTO `qt_rolepermission` VALUES (3366,1,91);
INSERT INTO `qt_rolepermission` VALUES (3367,1,92);
INSERT INTO `qt_rolepermission` VALUES (3368,1,93);
INSERT INTO `qt_rolepermission` VALUES (3369,1,94);
INSERT INTO `qt_rolepermission` VALUES (3370,1,95);
INSERT INTO `qt_rolepermission` VALUES (3371,1,96);
INSERT INTO `qt_rolepermission` VALUES (3372,1,97);
INSERT INTO `qt_rolepermission` VALUES (3373,1,98);
INSERT INTO `qt_rolepermission` VALUES (3374,1,99);

#
# Table structure for table qt_suggest
#

CREATE TABLE `qt_suggest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text COMMENT '反馈内容',
  `addtime` timestamp NULL DEFAULT NULL COMMENT '添加时间',
  `reply` text COMMENT '回复内容',
  `replytime` timestamp NULL DEFAULT NULL COMMENT '回复时间',
  `userID` int(11) DEFAULT NULL COMMENT '反馈者',
  `replyUserID` int(11) DEFAULT NULL COMMENT '回复者',
  `stext` varchar(255) DEFAULT NULL COMMENT '纯文本内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='建议反馈表';

#
# Dumping data for table qt_suggest
#

INSERT INTO `qt_suggest` VALUES (8,'测试反馈机制','2011-10-29 21:26:45','此问题是系统临时的问题，已修复，谢谢你提交的反馈！','2011-10-29 21:30:15',3,2,'测试反馈机制');

#
# Table structure for table qt_team
#

CREATE TABLE `qt_team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL COMMENT '用户ID',
  `headimage` varchar(255) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='管理团队表';

#
# Dumping data for table qt_team
#

INSERT INTO `qt_team` VALUES (3,2,'/Jh-oa/team/20111110221157_13.jpg');
INSERT INTO `qt_team` VALUES (4,2,'/Jh-oa/team/20111109224926_100.jpg');
INSERT INTO `qt_team` VALUES (5,2,'/Jh-oa/team/20111109224926_100.jpg');
INSERT INTO `qt_team` VALUES (6,2,'/Jh-oa/team/20111109224926_100.jpg');

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
  `introduce` text COMMENT '简介',
  `simpleinfo` text COMMENT '纯文本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Dumping data for table qt_user
#

INSERT INTO `qt_user` VALUES (2,'200826490109','李斌斌','1','2011-09-07 15:16:58','2011-11-01 21:10:54','qingtian16265@163.com','687541','13656667541',4,1,'软件工程(2+3)0801','屏峰','',0,'qingtian16265','<p><b>这是我的简介</b></p>\r\n<p>天下无人可敌</p>','这是我的简介\r\n天下无人可敌');
INSERT INTO `qt_user` VALUES (3,'200826490108','李小斌','1','2011-10-02 21:25:56','2011-10-24 20:08:30','qingtian@163.com','1','',0,2,'','','',0,'',NULL,NULL);

#
# Table structure for table qt_userrole
#

CREATE TABLE `qt_userrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleID` int(11) DEFAULT NULL COMMENT '角色ID',
  `userID` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='用户角色对应表';

#
# Dumping data for table qt_userrole
#

INSERT INTO `qt_userrole` VALUES (14,1,2);
INSERT INTO `qt_userrole` VALUES (15,6,3);

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
