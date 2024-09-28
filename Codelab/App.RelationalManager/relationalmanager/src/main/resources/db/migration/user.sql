/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80402 (8.4.2)
 Source Host           : localhost:3306
 Source Schema         : pos_dev

 Target Server Type    : MySQL
 Target Server Version : 80402 (8.4.2)
 File Encoding         : 65001

 Date: 28/09/2024 10:36:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL COMMENT 'Identifier',
  `username` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'Username > 8 char < 25 chars',
  `firstname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `lastname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'should @',
  `created_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'Jialun', 'Jialun', 'Tao', '(1223) 05 5467', 'jialuntao@icloud.com', '2015-11-29 17:05:42');
INSERT INTO `user` VALUES (2, 'Ka Fai', 'Ka Fai', 'Hung', '5782 703693', 'kfhung@gmail.com', '2024-03-19 09:09:02');
INSERT INTO `user` VALUES (3, 'Xiaoming', 'Xiaoming', 'Gao', '70-4806-3690', 'xiaomga@mail.com', '2011-11-09 23:10:39');
INSERT INTO `user` VALUES (4, 'Yuito', 'Yuito', 'Sano', '(121) 776 8485', 'sanyuito8@mail.com', '2014-12-07 00:48:39');
INSERT INTO `user` VALUES (5, 'Evelyn', 'Evelyn', 'Henderson', '66-360-9644', 'henderson7@icloud.com', '2000-05-29 19:56:59');
INSERT INTO `user` VALUES (6, 'Micheal', 'Micheal', 'Martin', '330-616-2584', 'micma@outlook.com', '2015-05-04 22:18:55');
INSERT INTO `user` VALUES (7, 'Momoe', 'Momoe', 'Inoue', '135-4802-4377', 'inom1119@yahoo.com', '2017-08-14 18:39:22');
INSERT INTO `user` VALUES (8, 'Cho Yee', 'Cho Yee', 'Lok', '3-9339-5575', 'lcy82@yahoo.com', '2017-10-18 21:52:17');
INSERT INTO `user` VALUES (9, 'Jeff', 'Jeff', 'Guzman', '212-673-4343', 'jeff1965@hotmail.com', '2005-07-18 23:30:12');
INSERT INTO `user` VALUES (10, 'Robin', 'Robin', 'Torres', '3-2179-2962', 'torresr2@hotmail.com', '2003-10-21 13:22:24');

SET FOREIGN_KEY_CHECKS = 1;
