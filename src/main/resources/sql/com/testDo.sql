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
-- Table structure for testdosql
-- ----------------------------
DROP TABLE IF EXISTS `testdosql1`;
CREATE TABLE `testdosql1` (
                           `Id` int(11) NOT NULL,
                           `name` tinytext NOT NULL COMMENT '名字',
                           `varCh` varchar(255) DEFAULT NULL,
                           `bigI` bigint(20) DEFAULT NULL,
                           PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
