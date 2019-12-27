/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : yb

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2019-12-27 17:20:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for yb_agree
-- ----------------------------
DROP TABLE IF EXISTS `yb_agree`;
CREATE TABLE `yb_agree` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户 id',
  `creat_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '插入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of yb_agree
-- ----------------------------

-- ----------------------------
-- Table structure for yb_comment
-- ----------------------------
DROP TABLE IF EXISTS `yb_comment`;
CREATE TABLE `yb_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fcmid` bigint(20) DEFAULT NULL COMMENT '被评论的朋友圈id',
  `commentator_id` bigint(20) DEFAULT NULL COMMENT '评论人id',
  `parent_comment_id` bigint(20) DEFAULT NULL COMMENT '父评论id',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '评论的内容',
  `created` datetime DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yb_comment
-- ----------------------------
INSERT INTO `yb_comment` VALUES ('130', '38', '43', '0', '美', '2018-12-24 15:37:48');
INSERT INTO `yb_comment` VALUES ('131', '38', '43', '0', '超美<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/50/pcmoren_huaixiao_org.png\" alt=\"[坏笑]\" data-w-e=\"1\">', '2018-12-24 15:37:58');
INSERT INTO `yb_comment` VALUES ('132', '39', '9', '0', '可以啊<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/50/pcmoren_huaixiao_org.png\" alt=\"[坏笑]\" data-w-e=\"1\">', '2018-12-24 15:40:50');
INSERT INTO `yb_comment` VALUES ('133', '38', '9', '131', '嗯', '2018-12-24 15:40:59');
INSERT INTO `yb_comment` VALUES ('134', '39', '9', '132', '21313', '2018-12-24 16:36:46');
INSERT INTO `yb_comment` VALUES ('135', '39', '9', '0', '9999<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/3c/pcmoren_wu_org.png\" alt=\"[污]\" data-w-e=\"1\">', '2018-12-24 16:36:57');
INSERT INTO `yb_comment` VALUES ('136', '38', '9', '131', '55556565<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/50/pcmoren_huaixiao_org.png\" alt=\"[坏笑]\" data-w-e=\"1\">', '2018-12-24 16:40:08');
INSERT INTO `yb_comment` VALUES ('137', '38', '9', '0', '<span style=\"font-style: italic;\">哈哈</span>', '2018-12-24 17:43:46');
INSERT INTO `yb_comment` VALUES ('138', '38', '9', '0', '<span style=\"font-weight: bold;\">呵呵</span>', '2018-12-24 17:43:56');
INSERT INTO `yb_comment` VALUES ('139', '38', '9', '0', '<span style=\"text-decoration-line: underline;\">嗷嗷</span>', '2018-12-24 17:48:23');

-- ----------------------------
-- Table structure for yb_friend_cirle_message
-- ----------------------------
DROP TABLE IF EXISTS `yb_friend_cirle_message`;
CREATE TABLE `yb_friend_cirle_message` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '消息表id',
  `uid` bigint(10) DEFAULT NULL COMMENT '用户id',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '该用户发表的朋友圈内容',
  `picture` varchar(500) DEFAULT NULL COMMENT '图片地址',
  `location` varchar(200) DEFAULT NULL COMMENT '发表朋友圈地址',
  `praise_num` int(10) DEFAULT '0' COMMENT '点赞数 默认0',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yb_friend_cirle_message
-- ----------------------------
INSERT INTO `yb_friend_cirle_message` VALUES ('34', '9', '<p>?今天又约到一个妹子<br></p>', 'http://localhost:8080/static\\upload/2736a3af5a6f4a07a7e39435842d50361.jpg', '广东省 广州市 越秀区', '0', '2018-12-24 15:33:08');
INSERT INTO `yb_friend_cirle_message` VALUES ('35', '9', '<p>今天好开心啊</p>', 'http://localhost:8080/static\\upload/5eefd8a1441446449df1074f0a4d746ft.jpg', '广东省 广州市 越秀区', '0', '2018-12-24 15:34:49');
INSERT INTO `yb_friend_cirle_message` VALUES ('36', '9', '<p>6666666666666</p>', 'http://localhost:8080/static\\upload/b1021cd666a540d8b2ed5ff2706f5b3e动.gif', '广东省 广州市 越秀区', '0', '2018-12-24 15:35:31');
INSERT INTO `yb_friend_cirle_message` VALUES ('38', '43', '<p>我美么<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/3c/pcmoren_wu_org.png\" alt=\"[污]\" data-w-e=\"1\"></p>', 'http://localhost:8080/static\\upload/077171e96c0349d2b9af93d398a8ff15衣.jpg', '广东省 广州市 越秀区', '1', '2018-12-24 15:37:41');
INSERT INTO `yb_friend_cirle_message` VALUES ('39', '42', '<p>lalalallalalalalal</p>', 'http://localhost:8080/static\\upload/1328f060f71d4393b51a7d0c24332cf1a.jpg', '广东省 广州市 越秀区', '2', '2018-12-24 15:39:43');
INSERT INTO `yb_friend_cirle_message` VALUES ('40', '40', '<p>我是大猫</p>', 'http://localhost:8080/static\\upload/973ebed551f1468e89fc635ab01a9212405629b9128b427588b0ad3bd8ae7f78b.jpg', '广东省 广州市 越秀区', '0', '2018-12-24 15:44:08');
INSERT INTO `yb_friend_cirle_message` VALUES ('41', '9', '<p>55556655565</p>', 'http://localhost:8080/static\\upload/946a6cf0902d4865819c52ad3755295549ff51fda40248e38c8ba06b6e5e175c热巴.jpg', '广东省 广州市 越秀区', '0', '2018-12-24 16:40:30');

-- ----------------------------
-- Table structure for yb_praise_detail
-- ----------------------------
DROP TABLE IF EXISTS `yb_praise_detail`;
CREATE TABLE `yb_praise_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `praise_uid` bigint(20) DEFAULT NULL COMMENT '点赞的用户id',
  `fcmid` bigint(20) DEFAULT NULL COMMENT '朋友圈的id',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yb_praise_detail
-- ----------------------------
INSERT INTO `yb_praise_detail` VALUES ('75', '42', '39', '2018-12-24 15:39:48');
INSERT INTO `yb_praise_detail` VALUES ('77', '9', '39', '2018-12-24 16:39:37');
INSERT INTO `yb_praise_detail` VALUES ('78', '9', '38', '2018-12-24 17:48:34');

-- ----------------------------
-- Table structure for yb_record
-- ----------------------------
DROP TABLE IF EXISTS `yb_record`;
CREATE TABLE `yb_record` (
  `id` int(50) NOT NULL AUTO_INCREMENT COMMENT '聊天记录 ID',
  `message` varchar(255) NOT NULL COMMENT '聊天内容',
  `created` datetime NOT NULL COMMENT '发送方',
  `sender_id` int(50) NOT NULL,
  `receiver_id` varchar(50) NOT NULL,
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '消息类型--1 代表已读 0 代表未读',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=198 DEFAULT CHARSET=utf8 COMMENT='聊天记录表';

-- ----------------------------
-- Records of yb_record
-- ----------------------------
INSERT INTO `yb_record` VALUES ('125', '121221', '2018-12-24 09:29:33', '2', '1', '0');
INSERT INTO `yb_record` VALUES ('126', '哈哈哈', '2018-12-24 09:32:10', '2', '1', '0');
INSERT INTO `yb_record` VALUES ('127', 'wwq1', '2018-12-24 09:42:24', '2', '1', '0');
INSERT INTO `yb_record` VALUES ('128', 'wqeqw', '2018-12-24 09:44:32', '1', '2', '0');
INSERT INTO `yb_record` VALUES ('129', 'wqw', '2018-12-24 09:44:40', '2', '1', '0');
INSERT INTO `yb_record` VALUES ('130', 'ww', '2018-12-24 09:45:44', '1', '2', '0');
INSERT INTO `yb_record` VALUES ('131', 'dddddddd', '2018-12-24 09:46:50', '1', '2', '0');
INSERT INTO `yb_record` VALUES ('132', 'kkkkkkkkkkkkkkk', '2018-12-24 09:51:32', '2', '1', '0');
INSERT INTO `yb_record` VALUES ('133', 'asaaasds', '2018-12-24 09:52:03', '1', '2', '0');
INSERT INTO `yb_record` VALUES ('134', 'wqw', '2018-12-24 09:53:17', '1', '3', '0');
INSERT INTO `yb_record` VALUES ('135', 'www', '2018-12-24 09:57:19', '1', '3', '0');
INSERT INTO `yb_record` VALUES ('136', 'qwqw', '2018-12-24 09:57:22', '3', '1', '0');
INSERT INTO `yb_record` VALUES ('137', '212112', '2018-12-24 09:57:25', '1', '3', '0');
INSERT INTO `yb_record` VALUES ('138', '哈哈哈', '2018-12-24 09:58:14', '2', '1', '0');
INSERT INTO `yb_record` VALUES ('139', '哈哈哈', '2018-12-24 09:58:22', '2', '1', '0');
INSERT INTO `yb_record` VALUES ('140', 'mdas', '2018-12-24 09:58:39', '3', '2', '0');
INSERT INTO `yb_record` VALUES ('141', '草拟吗', '2018-12-24 09:58:44', '2', '1', '0');
INSERT INTO `yb_record` VALUES ('142', 'dsds', '2018-12-24 09:58:49', '3', '2', '0');
INSERT INTO `yb_record` VALUES ('143', '我曹', '2018-12-24 09:59:02', '2', '3', '0');
INSERT INTO `yb_record` VALUES ('144', 'fdd', '2018-12-24 09:59:05', '3', '2', '0');
INSERT INTO `yb_record` VALUES ('145', 'dsfsd', '2018-12-24 09:59:08', '3', '2', '0');
INSERT INTO `yb_record` VALUES ('146', 'fsdsd', '2018-12-24 09:59:10', '3', '2', '0');
INSERT INTO `yb_record` VALUES ('147', 'dfs', '2018-12-24 09:59:11', '3', '2', '0');
INSERT INTO `yb_record` VALUES ('148', '嗷嗷', '2018-12-24 09:59:11', '2', '3', '0');
INSERT INTO `yb_record` VALUES ('149', 'www', '2018-12-24 10:08:26', '1', '3', '0');
INSERT INTO `yb_record` VALUES ('150', '111', '2018-12-24 10:08:38', '2', '3', '0');
INSERT INTO `yb_record` VALUES ('151', 'sawqqw', '2018-12-24 10:08:46', '1', '2', '0');
INSERT INTO `yb_record` VALUES ('152', 'wwww', '2018-12-24 10:08:48', '1', '2', '0');
INSERT INTO `yb_record` VALUES ('153', 'www', '2018-12-24 10:08:50', '1', '2', '0');
INSERT INTO `yb_record` VALUES ('154', '&nbsp; &nbsp;&nbsp;', '2018-12-24 10:08:53', '2', '3', '0');
INSERT INTO `yb_record` VALUES ('155', 'aaa', '2018-12-24 10:08:57', '2', '3', '0');
INSERT INTO `yb_record` VALUES ('156', 'qqqq', '2018-12-24 10:08:59', '2', '3', '0');
INSERT INTO `yb_record` VALUES ('157', 'qwqqw', '2018-12-24 10:09:04', '1', '2', '0');
INSERT INTO `yb_record` VALUES ('158', 'qwqw', '2018-12-24 10:09:07', '1', '2', '0');
INSERT INTO `yb_record` VALUES ('159', '骚包', '2018-12-24 10:12:58', '1', '2', '0');
INSERT INTO `yb_record` VALUES ('160', 'AAA', '2018-12-24 10:13:18', '2', '3', '0');
INSERT INTO `yb_record` VALUES ('161', '骚包', '2018-12-24 10:13:40', '1', '2', '0');
INSERT INTO `yb_record` VALUES ('162', '爱', '2018-12-24 10:14:03', '1', '2', '0');
INSERT INTO `yb_record` VALUES ('163', 'SDASASDASDASD', '2018-12-24 10:14:13', '2', '3', '0');
INSERT INTO `yb_record` VALUES ('185', 'wqqq', '2018-12-24 12:46:37', '10', '14', '0');
INSERT INTO `yb_record` VALUES ('186', 'hello', '2018-12-24 13:54:29', '14', '10', '0');
INSERT INTO `yb_record` VALUES ('187', '约炮啊', '2018-12-24 13:54:44', '10', '14', '0');
INSERT INTO `yb_record` VALUES ('188', 'pp', '2018-12-24 14:00:37', '10', '42', '0');
INSERT INTO `yb_record` VALUES ('189', 'sa', '2018-12-24 14:00:56', '42', '10', '0');
INSERT INTO `yb_record` VALUES ('190', 'wq', '2018-12-24 16:01:14', '22', '10', '0');
INSERT INTO `yb_record` VALUES ('191', 'wqqw', '2018-12-24 16:01:21', '10', '22', '0');
INSERT INTO `yb_record` VALUES ('192', 'wqwqw', '2018-12-24 16:01:24', '22', '10', '0');
INSERT INTO `yb_record` VALUES ('193', 'wqwqw', '2018-12-24 16:01:27', '10', '22', '0');
INSERT INTO `yb_record` VALUES ('194', '约吗', '2018-12-24 16:34:03', '43', '9', '0');
INSERT INTO `yb_record` VALUES ('195', '开吗', '2018-12-24 16:34:17', '9', '43', '0');
INSERT INTO `yb_record` VALUES ('196', '1805', '2018-12-24 16:34:22', '43', '9', '0');
INSERT INTO `yb_record` VALUES ('197', '12', '2018-12-24 16:35:21', '43', '9', '0');

-- ----------------------------
-- Table structure for yb_timeline
-- ----------------------------
DROP TABLE IF EXISTS `yb_timeline`;
CREATE TABLE `yb_timeline` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '时间轴id',
  `uid` bigint(20) DEFAULT NULL COMMENT '用户id',
  `fcmid` bigint(20) DEFAULT NULL COMMENT '朋友圈消息表id',
  `is_own` tinyint(1) DEFAULT '1' COMMENT '是否是自己的消息 1/是  0/不是',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yb_timeline
-- ----------------------------
INSERT INTO `yb_timeline` VALUES ('97', '9', '34', '1', '2018-12-24 15:33:08');
INSERT INTO `yb_timeline` VALUES ('98', '22', '34', '0', '2018-12-24 15:33:08');
INSERT INTO `yb_timeline` VALUES ('99', '9', '35', '1', '2018-12-24 15:34:49');
INSERT INTO `yb_timeline` VALUES ('100', '22', '35', '0', '2018-12-24 15:34:49');
INSERT INTO `yb_timeline` VALUES ('101', '9', '36', '1', '2018-12-24 15:35:31');
INSERT INTO `yb_timeline` VALUES ('102', '22', '36', '0', '2018-12-24 15:35:31');
INSERT INTO `yb_timeline` VALUES ('106', '43', '36', '0', '2018-12-24 15:36:44');
INSERT INTO `yb_timeline` VALUES ('107', '43', '35', '0', '2018-12-24 15:36:44');
INSERT INTO `yb_timeline` VALUES ('108', '43', '34', '0', '2018-12-24 15:36:44');
INSERT INTO `yb_timeline` VALUES ('109', '43', '38', '1', '2018-12-24 15:37:41');
INSERT INTO `yb_timeline` VALUES ('110', '9', '38', '0', '2018-12-24 15:37:41');
INSERT INTO `yb_timeline` VALUES ('112', '42', '36', '0', '2018-12-24 15:38:52');
INSERT INTO `yb_timeline` VALUES ('113', '42', '35', '0', '2018-12-24 15:38:52');
INSERT INTO `yb_timeline` VALUES ('114', '42', '34', '0', '2018-12-24 15:38:52');
INSERT INTO `yb_timeline` VALUES ('115', '42', '39', '1', '2018-12-24 15:39:43');
INSERT INTO `yb_timeline` VALUES ('116', '9', '39', '0', '2018-12-24 15:39:43');
INSERT INTO `yb_timeline` VALUES ('117', '40', '40', '1', '2018-12-24 15:44:08');
INSERT INTO `yb_timeline` VALUES ('119', '42', '36', '0', '2018-12-24 15:47:39');
INSERT INTO `yb_timeline` VALUES ('120', '42', '35', '0', '2018-12-24 15:47:39');
INSERT INTO `yb_timeline` VALUES ('121', '42', '34', '0', '2018-12-24 15:47:39');
INSERT INTO `yb_timeline` VALUES ('122', '9', '39', '0', '2018-12-24 15:47:39');
INSERT INTO `yb_timeline` VALUES ('123', '42', '36', '0', '2018-12-24 16:39:28');
INSERT INTO `yb_timeline` VALUES ('124', '42', '35', '0', '2018-12-24 16:39:28');
INSERT INTO `yb_timeline` VALUES ('125', '42', '34', '0', '2018-12-24 16:39:28');
INSERT INTO `yb_timeline` VALUES ('126', '9', '39', '0', '2018-12-24 16:39:28');
INSERT INTO `yb_timeline` VALUES ('127', '9', '41', '1', '2018-12-24 16:40:30');
INSERT INTO `yb_timeline` VALUES ('128', '22', '41', '0', '2018-12-24 16:40:30');
INSERT INTO `yb_timeline` VALUES ('129', '43', '41', '0', '2018-12-24 16:40:30');
INSERT INTO `yb_timeline` VALUES ('130', '42', '41', '0', '2018-12-24 16:40:30');
INSERT INTO `yb_timeline` VALUES ('131', '42', '41', '0', '2018-12-24 16:40:30');
INSERT INTO `yb_timeline` VALUES ('132', '43', '41', '0', '2019-12-03 11:14:15');
INSERT INTO `yb_timeline` VALUES ('133', '42', '41', '0', '2019-12-03 11:15:00');

-- ----------------------------
-- Table structure for yb_user
-- ----------------------------
DROP TABLE IF EXISTS `yb_user`;
CREATE TABLE `yb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `email` varchar(32) NOT NULL COMMENT '邮箱',
  `gender` int(1) DEFAULT '1' COMMENT '性别',
  `birth` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '出生日期',
  `picture` varchar(500) DEFAULT NULL COMMENT '头像地址',
  `created` date DEFAULT NULL COMMENT '创建时间',
  `location` varchar(500) DEFAULT NULL COMMENT '地理位置信息',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机号',
  `updated` date DEFAULT NULL,
  `is_online` varchar(255) DEFAULT '0' COMMENT '在线状态 1：在线  0：离线',
  `is_role` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `id` (`id`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of yb_user
-- ----------------------------
INSERT INTO `yb_user` VALUES ('1', 'admin', 'f379eaf3c831b04de153469d1bec345e', '12asd@qq.com', '1', '2018-12-18 00:00:00', 'http://localhost:8080/static\\upload/9921a372ad51419882ba3d6ba70ea645美女.jpg', null, '广州', null, '2018-12-24', '0', '1');
INSERT INTO `yb_user` VALUES ('6', 'cfw', 'e10adc3949ba59abbe56e057f20f883e', 'cfw@qq.com', '1', '2018-12-06 00:00:00', '/static/upload/4.jpg', '2018-12-19', '广州,110', '110', '2018-12-22', '0', '0');
INSERT INTO `yb_user` VALUES ('7', 'qyl', 'e10adc3949ba59abbe56e057f20f883e', 'qyl@qq.com', '1', '2018-12-06 00:00:00', '/static/upload/4.jpg', '2018-12-19', '广州', '110', '2018-12-19', '0', '0');
INSERT INTO `yb_user` VALUES ('8', 'gjh', 'e10adc3949ba59abbe56e057f20f883e', 'gjh@qq.com', '1', '2018-12-06 00:00:00', '/static/upload/4.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('9', 'cqt', 'e10adc3949ba59abbe56e057f20f883e', 'cqt@qq.com', '1', '2018-12-06 00:00:00', '/static/upload/4.jpg', '2018-12-20', '广州', '110', '2018-12-24', '1', '0');
INSERT INTO `yb_user` VALUES ('10', 'zd', 'e10adc3949ba59abbe56e057f20f883e', 'zd@qq.com', '1', '2018-12-06 00:00:00', '/static/upload/6.jpg', '2018-12-20', '广州', '1100', '2018-12-24', '1', '0');
INSERT INTO `yb_user` VALUES ('11', '钱', 'e10adc3949ba59abbe56e057f20f883e', '12234@qq.com', '0', '2018-12-06 00:00:00', '/static/upload/x.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('12', '用户名', 'e10adc3949ba59abbe56e057f20f883e', '57872@qq.com', '0', '2018-12-06 00:00:00', '/static/upload/v.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('13', '啊啊', 'e10adc3949ba59abbe56e057f20f883e', 'sjjia@qq.com', '0', '2018-12-06 00:00:00', '/static/upload/n.jpg', '2018-12-21', '广州', '110', '2018-12-21', '0', '0');
INSERT INTO `yb_user` VALUES ('14', 'a1', 'e10adc3949ba59abbe56e057f20f883e', 'a', '0', '2018-12-06 00:00:00', '/static/upload/d.jpg', '2018-12-21', '广州', '110', '2018-12-24', '0', '0');
INSERT INTO `yb_user` VALUES ('15', 'b1', 'e10adc3949ba59abbe56e057f20f883e', 'b', '0', '2018-12-21 00:00:00', '/static/upload/s.jpg', '2018-12-21', '广州', '110', '2018-12-21', '0', '0');
INSERT INTO `yb_user` VALUES ('16', 'c1', 'e10adc3949ba59abbe56e057f20f883e', 'c', '0', '2018-12-14 00:00:00', '/static/upload/h.jpg', '2018-12-21', '广州', '110', '2018-12-21', '0', '0');
INSERT INTO `yb_user` VALUES ('17', 'd1', 'e10adc3949ba59abbe56e057f20f883e', 'd', '0', '2018-12-06 00:00:00', '/static/upload/k.jpg', '2018-12-21', '广州', '110', '2018-12-21', '0', '0');
INSERT INTO `yb_user` VALUES ('19', 'f1', 'e10adc3949ba59abbe56e057f20f883e', 'f', '0', '2018-12-06 00:00:00', '/static/upload/u.jpg', '2018-12-21', '广州', '110', '2018-12-21', '0', '0');
INSERT INTO `yb_user` VALUES ('20', 'g1', 'e10adc3949ba59abbe56e057f20f883e', 'g', '0', '2018-12-06 00:00:00', '/static/upload/t.jpg', '2018-12-21', '广州', '110', '2018-12-21', '0', '0');
INSERT INTO `yb_user` VALUES ('22', 'i1', 'e10adc3949ba59abbe56e057f20f883e', 'i', '0', '2018-12-06 00:00:00', '/static/upload/a.jpg', '2018-12-21', '广州', '110', '2018-12-24', '1', '0');
INSERT INTO `yb_user` VALUES ('23', 'j1', 'e10adc3949ba59abbe56e057f20f883e', 'j', '0', '2018-12-06 00:00:00', '/static/upload/aa.jpg', '2018-12-21', '广州', '110', '2018-12-21', '0', '0');
INSERT INTO `yb_user` VALUES ('25', 'l1', 'e10adc3949ba59abbe56e057f20f883e', 'l', '0', '2018-12-06 00:00:00', '/static/upload/ac.jpg', '2018-12-21', '广州', '110', '2018-12-21', '0', '0');
INSERT INTO `yb_user` VALUES ('26', 'm1', 'e10adc3949ba59abbe56e057f20f883e', 'm', '0', '2018-12-06 00:00:00', '/static/upload/w.jpg', '2018-12-21', '广州', '110', '2018-12-21', '0', '0');
INSERT INTO `yb_user` VALUES ('27', 'n1', 'e10adc3949ba59abbe56e057f20f883e', 'n', '0', '2018-12-06 00:00:00', '/static/upload/s.jpg', '2018-12-21', '广州', '110', '2018-12-21', '0', '0');
INSERT INTO `yb_user` VALUES ('28', 'o1', 'e10adc3949ba59abbe56e057f20f883e', 'o', '0', '2018-12-06 00:00:00', '/static/upload/u.jpg', '2018-12-21', '广州', '110', '2018-12-21', '0', '0');
INSERT INTO `yb_user` VALUES ('29', 'p1', 'e10adc3949ba59abbe56e057f20f883e', 'p', '0', '2018-12-06 00:00:00', '/static/upload/g.jpg', '2018-12-21', '广州', '110', '2018-12-21', '0', '0');
INSERT INTO `yb_user` VALUES ('30', 'q1', 'e10adc3949ba59abbe56e057f20f883e', 'q', '0', '2018-12-06 00:00:00', '/static/upload/e.jpg', '2018-12-21', '广州', '110', '2018-12-21', '0', '0');
INSERT INTO `yb_user` VALUES ('31', 'r1', 'e10adc3949ba59abbe56e057f20f883e', 'r', '0', '2018-12-06 00:00:00', '/static/upload/v.jpg', '2018-12-21', '广州', '110', '2018-12-21', '0', '0');
INSERT INTO `yb_user` VALUES ('32', 'aa1', 'e10adc3949ba59abbe56e057f20f883e', 'aa1@qq.com', '0', '2018-12-06 00:00:00', '/static/upload/c.jpg', '2018-12-21', '广州', '110', '2018-12-21', '0', '0');
INSERT INTO `yb_user` VALUES ('42', 'dmxs', 'e10adc3949ba59abbe56e057f20f883e', 'dmxs@qq.com', '0', null, '/static/upload/z.jpg', '2018-12-23', null, null, '2018-12-24', '0', '0');
INSERT INTO `yb_user` VALUES ('43', 'ddd', 'e10adc3949ba59abbe56e057f20f883e', 'ddd@qq.com', '0', null, '/static/upload/aa.jpg', '2018-12-23', null, null, '2018-12-24', '1', '0');
INSERT INTO `yb_user` VALUES ('45', 'cf', 'e10adc3949ba59abbe56e057f20f883e', 'cf@qq.com', '0', '2018-12-06 00:00:00', '/static/upload/5.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('46', 'a', 'e10adc3949ba59abbe56e057f20f883e', 'a@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/a.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('47', 'b', 'e10adc3949ba59abbe56e057f20f883e', 'b@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/b.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('48', 'c', 'e10adc3949ba59abbe56e057f20f883e', 'c@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/c.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('49', 'd', 'e10adc3949ba59abbe56e057f20f883e', 'd@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/d.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('50', 'e', 'e10adc3949ba59abbe56e057f20f883e', 'e@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/e.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('51', 'f', 'e10adc3949ba59abbe56e057f20f883e', 'f@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/f.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('52', 'g', 'e10adc3949ba59abbe56e057f20f883e', 'g@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/g.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('53', 'h', 'e10adc3949ba59abbe56e057f20f883e', 'h@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/h.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('54', 'i', 'e10adc3949ba59abbe56e057f20f883e', 'i@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/i.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('55', 'j', 'e10adc3949ba59abbe56e057f20f883e', 'j@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/j.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('56', 'k', 'e10adc3949ba59abbe56e057f20f883e', 'k@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/k.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('57', 'l', 'e10adc3949ba59abbe56e057f20f883e', 'l@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/l.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('58', 'm', 'e10adc3949ba59abbe56e057f20f883e', 'm@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/m.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('59', 'n', 'e10adc3949ba59abbe56e057f20f883e', 'n@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/n.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('60', 'o', 'e10adc3949ba59abbe56e057f20f883e', 'o@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/o.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('61', 'p', 'e10adc3949ba59abbe56e057f20f883e', 'p@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/p.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('63', 'q', 'e10adc3949ba59abbe56e057f20f883e', 'q@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/q.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('64', 'r', 'e10adc3949ba59abbe56e057f20f883e', 'r@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/r.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('65', 's', 'e10adc3949ba59abbe56e057f20f883e', 's@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/s.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('66', 't', 'e10adc3949ba59abbe56e057f20f883e', 't@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/t.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('67', 'u', 'e10adc3949ba59abbe56e057f20f883e', 'u@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/u.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('68', 'v', 'e10adc3949ba59abbe56e057f20f883e', 'v@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/v.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('69', 'w', 'e10adc3949ba59abbe56e057f20f883e', 'w@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/w.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('70', 'x', 'e10adc3949ba59abbe56e057f20f883e', 'x@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/x.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('71', 'y', 'e10adc3949ba59abbe56e057f20f883e', 'y@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/y.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('72', 'z', 'e10adc3949ba59abbe56e057f20f883e', 'z@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/z.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('73', 'aa', 'e10adc3949ba59abbe56e057f20f883e', 'aa@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/aa.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('74', 'ab', 'e10adc3949ba59abbe56e057f20f883e', 'ab@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/ab.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');
INSERT INTO `yb_user` VALUES ('75', 'ac', 'e10adc3949ba59abbe56e057f20f883e', 'ac@qq.com', '0', '2018-12-20 00:00:00', '/static/upload/ac.jpg', '2018-12-20', '广州', '110', '2018-12-20', '0', '0');

-- ----------------------------
-- Table structure for yb_user_goodfriend
-- ----------------------------
DROP TABLE IF EXISTS `yb_user_goodfriend`;
CREATE TABLE `yb_user_goodfriend` (
  `id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '好友表ID',
  `updated` date NOT NULL,
  `uid` bigint(50) NOT NULL COMMENT '用户ID',
  `friend_id` bigint(50) NOT NULL COMMENT '好友ID',
  `created` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='好友表';

-- ----------------------------
-- Records of yb_user_goodfriend
-- ----------------------------
INSERT INTO `yb_user_goodfriend` VALUES ('10', '2018-12-24', '9', '22', '2018-12-24');
INSERT INTO `yb_user_goodfriend` VALUES ('11', '2018-12-24', '9', '43', '2018-12-24');
INSERT INTO `yb_user_goodfriend` VALUES ('12', '2018-12-24', '9', '42', '2018-12-24');
INSERT INTO `yb_user_goodfriend` VALUES ('14', '2018-12-24', '10', '22', '2018-12-24');
INSERT INTO `yb_user_goodfriend` VALUES ('15', '2018-12-24', '9', '42', '2018-12-24');

-- ----------------------------
-- Table structure for yb_user_info
-- ----------------------------
DROP TABLE IF EXISTS `yb_user_info`;
CREATE TABLE `yb_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户详情ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `profession` varchar(50) DEFAULT NULL COMMENT '职业',
  `personal_signature` varchar(50) DEFAULT NULL COMMENT '个性签名',
  `hobby` varchar(50) DEFAULT NULL COMMENT '爱好',
  `updated` date NOT NULL,
  `created` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='生涯信息表';

-- ----------------------------
-- Records of yb_user_info
-- ----------------------------
INSERT INTO `yb_user_info` VALUES ('1', '1', '学生', '多读书多看报', '代码', '2018-12-21', '2018-12-21');
INSERT INTO `yb_user_info` VALUES ('2', '2', '学生', '多读书多看报', '代码', '2018-12-21', '2018-12-21');
INSERT INTO `yb_user_info` VALUES ('3', '3', '学生', '多读书多看报', '代码', '2018-12-21', '2018-12-21');
INSERT INTO `yb_user_info` VALUES ('4', '4', '学生', '多读书多看报', '代码', '2018-12-21', '2018-12-21');
INSERT INTO `yb_user_info` VALUES ('5', '5', '学生', '多读书多看报', '代码', '2018-12-21', '2018-12-21');
INSERT INTO `yb_user_info` VALUES ('6', '6', '学生', '多读书多看报', '代码', '2018-12-22', '2018-12-19');
INSERT INTO `yb_user_info` VALUES ('7', '7', '学生', '多读书多看报', '代码', '2018-12-21', '2018-12-21');
INSERT INTO `yb_user_info` VALUES ('8', '8', '学生', '多读书多看报', '代码', '2018-12-21', '2018-12-21');
INSERT INTO `yb_user_info` VALUES ('9', '9', '学生', '多读书多看报', '代码', '2018-12-24', '2018-12-20');
INSERT INTO `yb_user_info` VALUES ('10', '10', '学生1', '多读书多看报', '代码', '2018-12-24', '2018-12-20');
INSERT INTO `yb_user_info` VALUES ('11', '11', '学生', '多读书多看报', '代码', '2018-12-21', '2018-12-21');
INSERT INTO `yb_user_info` VALUES ('12', '12', '学生', '多读书多看报', '代码', '2018-12-21', '2018-12-21');
INSERT INTO `yb_user_info` VALUES ('13', '13', '学生', '多读书多看报', '代码', '2018-12-21', '2018-12-21');
INSERT INTO `yb_user_info` VALUES ('14', '14', '学生', '多读书多看报', '代码', '2018-12-24', '2018-12-21');
INSERT INTO `yb_user_info` VALUES ('15', '15', '学生', '多读书多看报', '代码', '2018-12-21', '2018-12-21');
INSERT INTO `yb_user_info` VALUES ('16', '16', '学生', '多读书多看报', '代码', '2018-12-21', '2018-12-21');
INSERT INTO `yb_user_info` VALUES ('17', '17', '学生', '多读书多看报', '代码', '2018-12-21', '2018-12-21');
INSERT INTO `yb_user_info` VALUES ('18', '18', '学生', '多读书多看报', '代码', '2018-12-21', '2018-12-21');
INSERT INTO `yb_user_info` VALUES ('19', '19', '学生', '多读书多看报', '代码', '2018-12-21', '2018-12-21');
INSERT INTO `yb_user_info` VALUES ('20', '20', '学生', '多读书多看报', '代码', '2018-12-21', '2018-12-21');
INSERT INTO `yb_user_info` VALUES ('21', '40', '学生', '多读书多看报', '代码', '2018-12-23', '2018-12-23');
INSERT INTO `yb_user_info` VALUES ('22', '42', '学生', '多读书多看报', '代码', '2018-12-23', '2018-12-23');
INSERT INTO `yb_user_info` VALUES ('23', '43', '学生', '多读书多看报', '代码', '2018-12-24', '2018-12-23');
INSERT INTO `yb_user_info` VALUES ('24', '76', '学生', '多读书多看报', '代码', '2018-12-24', '2018-12-24');
INSERT INTO `yb_user_info` VALUES ('25', '77', '学生', '多读书多看报', '代码', '2018-12-24', '2018-12-24');
INSERT INTO `yb_user_info` VALUES ('26', '78', null, null, null, '2018-12-24', '2018-12-24');
