/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : springboot2springsecurity

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 09/04/2018 09:59:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`  (
  `id` bigint(10) NOT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES (1, '/user/delete/{id}');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ROLE_SYSTEM_ADMIN');
INSERT INTO `role` VALUES (2, 'ROLE_ADMIN');
INSERT INTO `role` VALUES (3, 'ROLE_USER');

-- ----------------------------
-- Table structure for role_authority
-- ----------------------------
DROP TABLE IF EXISTS `role_authority`;
CREATE TABLE `role_authority`  (
  `id` bigint(10) NOT NULL,
  `role_id` bigint(10) NULL DEFAULT NULL COMMENT '角色 id',
  `authority_id` bigint(10) NULL DEFAULT NULL COMMENT '权限 id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_authority
-- ----------------------------
INSERT INTO `role_authority` VALUES (1, 2, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'system_admin', '$2a$10$tUxT7Ud3T6Dj4tXxoV8NX.LD9rTb2xiFdwd3RCJ.DaGGU6WhPDzQG');
INSERT INTO `user` VALUES (2, 'admin', '$2a$10$tUxT7Ud3T6Dj4tXxoV8NX.LD9rTb2xiFdwd3RCJ.DaGGU6WhPDzQG');
INSERT INTO `user` VALUES (3, 'user', '$2a$10$tUxT7Ud3T6Dj4tXxoV8NX.LD9rTb2xiFdwd3RCJ.DaGGU6WhPDzQG');
INSERT INTO `user` VALUES (4, 'demo4', '$2a$10$tUxT7Ud3T6Dj4tXxoV8NX.LD9rTb2xiFdwd3RCJ.DaGGU6WhPDzQG');
INSERT INTO `user` VALUES (5, 'demo5', '$2a$10$tUxT7Ud3T6Dj4tXxoV8NX.LD9rTb2xiFdwd3RCJ.DaGGU6WhPDzQG');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(10) NOT NULL,
  `role_id` bigint(10) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 1, 2);
INSERT INTO `user_role` VALUES (3, 1, 3);
INSERT INTO `user_role` VALUES (4, 2, 2);
INSERT INTO `user_role` VALUES (5, 2, 3);
INSERT INTO `user_role` VALUES (6, 3, 3);
INSERT INTO `user_role` VALUES (7, 3, 1);


