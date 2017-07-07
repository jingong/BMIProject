/*
Navicat MySQL Data Transfer

Source Server         : jiajingong
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : bmi

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-07-07 12:33:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bmiinfo`
-- ----------------------------
DROP TABLE IF EXISTS `bmiinfo`;
CREATE TABLE `bmiinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(45) DEFAULT NULL,
  `height` varchar(45) DEFAULT NULL,
  `weight` varchar(45) DEFAULT NULL,
  `bmi` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bmiinfo
-- ----------------------------
INSERT INTO `bmiinfo` VALUES ('2', '2017/7/4 下午5:20:05', '452', '11', '0.54');
INSERT INTO `bmiinfo` VALUES ('4', '2017/7/4 下午5:20:09', '455', '44', '2.13');
INSERT INTO `bmiinfo` VALUES ('5', '2017/7/4 下午5:20:12', '123', '56', '37.02');
INSERT INTO `bmiinfo` VALUES ('7', '2017/7/4 下午5:20:18', '233', '111', '20.45');
INSERT INTO `bmiinfo` VALUES ('8', '2017/7/4 下午5:19:20', '1111', '222', '1.8');
INSERT INTO `bmiinfo` VALUES ('10', '2017/7/5 上午8:39:54', '180', '76', '23.46');
INSERT INTO `bmiinfo` VALUES ('11', '2017/7/5 上午8:40:16', '180', '76', '23.46');
INSERT INTO `bmiinfo` VALUES ('12', '2017/7/5 上午8:40:26', '180', '76', '23.46');
INSERT INTO `bmiinfo` VALUES ('13', '2017/7/5 上午8:58:30', '155', '65', '27.06');
INSERT INTO `bmiinfo` VALUES ('14', '2017/7/5 上午9:00:48', '165', '76', '27.92');
INSERT INTO `bmiinfo` VALUES ('15', '2017/7/5 上午9:01:50', '178', '80', '25.25');
INSERT INTO `bmiinfo` VALUES ('16', '2017/7/5 上午10:05:09', '456', '123', '5.92');
INSERT INTO `bmiinfo` VALUES ('17', '2017/7/5 上午10:10:50', '123', '12', '7.93');
INSERT INTO `bmiinfo` VALUES ('20', '2017/7/5 上午10:17:32', '156', '45', '18.49');
INSERT INTO `bmiinfo` VALUES ('21', '2017/7/5 上午10:25:08', '156', '45', '18.49');
INSERT INTO `bmiinfo` VALUES ('22', '2017/7/5 上午11:03:15', '128', '345', '210.57');
