/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.110(windows)
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : 192.168.1.110:3306
 Source Schema         : fvb_db

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 23/08/2021 16:19:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for testsql
-- ----------------------------
DROP TABLE IF EXISTS `testsql`;
CREATE TABLE `testsql` (
  `Id` int(11) NOT NULL,
  `name` tinytext,
  `varCh` varchar(255) DEFAULT NULL,
  `bigI` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;