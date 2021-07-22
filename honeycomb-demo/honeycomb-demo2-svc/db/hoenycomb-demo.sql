/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50729
Source Host           : localhost:3306
Source Database       : honeycomb-demo

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2020-12-09 10:02:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for demo_account
-- ----------------------------
DROP TABLE IF EXISTS `demo_account`;
CREATE TABLE `demo_account` (
  `account_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '账户号',
  `account_code` varchar(20) NOT NULL COMMENT '账户编码',
  `union_id` varchar(100) NOT NULL COMMENT '立方用户标识',
  `user_name` varchar(100) NOT NULL DEFAULT '',
  `account_status` enum('ACTIVE','FREEZE','DESTROYING','DESTROY') NOT NULL DEFAULT 'ACTIVE' COMMENT '开户状态',
  `terminal` varchar(10) NOT NULL COMMENT '终端',
  `is_recharge` int(1) NOT NULL DEFAULT '0' COMMENT '是否充值过',
  `unconscious_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '无感支付状态1开0未开',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `active_time` datetime NOT NULL COMMENT '开户时间',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `UK_ACCOUNT_CODE` (`account_code`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='钱包账户表';

-- ----------------------------
-- Records of demo_account
-- ----------------------------
INSERT INTO `demo_account` VALUES ('1', '60202012031035374427', '45ee8288-a41b-4901-8c6b-c321c8e8f28d', '', 'DESTROY', '1', '0', '0', '2020-12-03 10:35:38', '2020-12-03 10:35:38', '2020-12-03 10:35:38', '0');
INSERT INTO `demo_account` VALUES ('2', '60202012031042443754', '96778f2f-e16b-424d-bf3c-2d92586ab75f', '', 'ACTIVE', '1', '0', '0', '2020-12-03 10:42:45', '2020-12-03 10:42:45', '2020-12-03 10:42:45', '0');
INSERT INTO `demo_account` VALUES ('3', '60202012031108502354', '62d65267-d29c-4abe-be1c-722db4bfc4b2', '', 'ACTIVE', '1', '0', '0', '2020-12-03 11:08:50', '2020-12-03 11:08:50', '2020-12-03 11:08:50', '0');
INSERT INTO `demo_account` VALUES ('4', '60202012031523518207', '11f313e3-3fa8-44cc-9114-b466298fe1c2', '', 'ACTIVE', '1', '0', '0', '2020-12-03 15:23:51', '2020-12-03 15:23:51', '2020-12-03 15:23:51', '0');
INSERT INTO `demo_account` VALUES ('5', '60202012041318443981', 'f22e1d0c-6369-4c34-ae1d-9d2902889de7', '', 'ACTIVE', '1', '0', '0', '2020-12-04 13:18:45', '2020-12-04 13:18:45', '2020-12-04 13:18:45', '0');
INSERT INTO `demo_account` VALUES ('6', '60202012041505182816', '45ee8288-a41b-4901-8c6b-c321c8e8f28d', '', 'DESTROY', '3', '0', '0', '2020-12-04 15:05:18', '2020-12-04 15:05:18', '2020-12-04 15:05:18', '0');
INSERT INTO `demo_account` VALUES ('7', '60202012041721226646', '45ee8288-a41b-4901-8c6b-c321c8e8f28d', '', 'ACTIVE', '3', '0', '0', '2020-12-04 17:21:23', '2020-12-04 17:21:23', '2020-12-04 17:21:23', '0');
