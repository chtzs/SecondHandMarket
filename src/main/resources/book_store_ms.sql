/*
 Navicat MySQL Data Transfer

 Source Server         : CHT
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : book_store_ms

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 19/06/2022 13:00:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book_category
-- ----------------------------
DROP TABLE IF EXISTS `book_category`;
CREATE TABLE `book_category`  (
  `category_id` bigint NOT NULL COMMENT '类别ID',
  `category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别名',
  `category_info` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of book_category
-- ----------------------------
INSERT INTO `book_category` VALUES (1, '科幻', '科幻小说');
INSERT INTO `book_category` VALUES (2, '武侠', '武侠xxxxx');
INSERT INTO `book_category` VALUES (3, '情感4', '情感Xxxx');
INSERT INTO `book_category` VALUES (8, '1516541', '16541');
INSERT INTO `book_category` VALUES (1434055707165990914, '\"xxx\"', '\"shit\"');
INSERT INTO `book_category` VALUES (1436313024326561793, '887', '99');

-- ----------------------------
-- Table structure for book_info
-- ----------------------------
DROP TABLE IF EXISTS `book_info`;
CREATE TABLE `book_info`  (
  `book_id` bigint NOT NULL COMMENT '书籍编号',
  `book_press` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '出版社名',
  `book_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书名',
  `book_author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `book_status` int NULL DEFAULT NULL COMMENT '在售状态，上架0，下架1',
  `book_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `book_stock` int NULL DEFAULT NULL COMMENT '剩余库存',
  `book_icon` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书籍图片',
  `isbn` bigint NOT NULL COMMENT 'ISBN编号，唯一区别一本书',
  PRIMARY KEY (`book_id`) USING BTREE,
  FULLTEXT INDEX `book_press`(`book_press`, `book_name`, `book_author`) WITH PARSER `ngram`
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of book_info
-- ----------------------------
INSERT INTO `book_info` VALUES (1, '重庆出版社', '三体全套', '刘慈欣', 0, 55.80, 100, 'https://img.alicdn.com/bao/uploaded/i1/1049653664/O1CN01vnN2aI1cw9oLKlRDr_!!0-item_pic.jpg_180x180.jpg', 9787229030933);
INSERT INTO `book_info` VALUES (2, '化学工业出版社', '克莱因壶11', '冈岛二人', 0, 24.00, 69, NULL, 9787122346032);
INSERT INTO `book_info` VALUES (3, '北京联合出版社', '房思琪的初恋乐园', '林奕含', 0, 45.00, 100, NULL, 9787559614636);
INSERT INTO `book_info` VALUES (4, '外语教学与研究出版社', '夏日终曲', '安德烈·艾席蒙', 0, 40.00, 100, NULL, 9787513598255);
INSERT INTO `book_info` VALUES (5, '广州朗声出版社', '射雕英雄传', '金庸', 0, 92.30, 100, NULL, 9787546205618);
INSERT INTO `book_info` VALUES (6, '南海出版公司', '解忧杂货店', '东野圭吾', 0, 29.50, 100, NULL, 9787544270878);
INSERT INTO `book_info` VALUES (1433701557983109121, '浩天出版社', '陈浩天的性感写真', '陈浩天', 0, 100.52, 99, 'xxx', 999);

-- ----------------------------
-- Table structure for book_loss
-- ----------------------------
DROP TABLE IF EXISTS `book_loss`;
CREATE TABLE `book_loss`  (
  `book_id` bigint NOT NULL COMMENT '损耗的 书籍编号',
  `loss_num` int NOT NULL COMMENT '损耗的数目，可以为0',
  PRIMARY KEY (`book_id`) USING BTREE,
  CONSTRAINT `book_loss_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book_info` (`book_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of book_loss
-- ----------------------------
INSERT INTO `book_loss` VALUES (1, 14);

-- ----------------------------
-- Table structure for category_relationship
-- ----------------------------
DROP TABLE IF EXISTS `category_relationship`;
CREATE TABLE `category_relationship`  (
  `category_id` bigint NOT NULL COMMENT '类别ID',
  `book_id` bigint NOT NULL COMMENT '书籍ID',
  PRIMARY KEY (`category_id`, `book_id`) USING BTREE,
  INDEX `category_id`(`category_id`, `book_id`) USING BTREE,
  INDEX `book_id`(`book_id`) USING BTREE,
  CONSTRAINT `category_relationship_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `book_category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `category_relationship_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book_info` (`book_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category_relationship
-- ----------------------------
INSERT INTO `category_relationship` VALUES (1, 1);
INSERT INTO `category_relationship` VALUES (3, 3);
INSERT INTO `category_relationship` VALUES (3, 4);
INSERT INTO `category_relationship` VALUES (2, 5);
INSERT INTO `category_relationship` VALUES (3, 6);

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `order_id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单号',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `total_price` decimal(20, 2) NOT NULL COMMENT '总价',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `order_status` int NOT NULL DEFAULT 0 COMMENT '0为已创建，1为已付款，2为已取消',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `idx_order_id`(`order_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1434046569241493505 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (123456, 1433301361398661122, 95.00, '2021-08-30 17:03:03', 0);
INSERT INTO `order_detail` VALUES (1433320738697281538, 1433301361398661122, 870.00, '2021-09-02 06:47:48', 2);
INSERT INTO `order_detail` VALUES (1434045687686860802, 1, 870.00, '2021-09-04 06:48:30', 2);
INSERT INTO `order_detail` VALUES (1434046509711736833, 1, 870.00, '2021-09-04 06:51:46', 2);
INSERT INTO `order_detail` VALUES (1434046569241493505, 1, 870.00, '2021-09-04 06:52:00', 2);

-- ----------------------------
-- Table structure for order_relationship
-- ----------------------------
DROP TABLE IF EXISTS `order_relationship`;
CREATE TABLE `order_relationship`  (
  `order_id` bigint NOT NULL COMMENT '订单编号',
  `book_id` bigint NOT NULL COMMENT '图书编号',
  `book_quantity` int NULL DEFAULT NULL COMMENT '该书购买数量',
  PRIMARY KEY (`order_id`, `book_id`) USING BTREE,
  INDEX `book_id`(`book_id`, `order_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  CONSTRAINT `order_relationship_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book_info` (`book_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_relationship_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `order_detail` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_relationship
-- ----------------------------
INSERT INTO `order_relationship` VALUES (123456, 3, 1);
INSERT INTO `order_relationship` VALUES (123456, 4, 1);
INSERT INTO `order_relationship` VALUES (1433320738697281538, 1, 10);
INSERT INTO `order_relationship` VALUES (1433320738697281538, 2, 13);
INSERT INTO `order_relationship` VALUES (1434045687686860802, 1, 10);
INSERT INTO `order_relationship` VALUES (1434045687686860802, 2, 13);
INSERT INTO `order_relationship` VALUES (1434046509711736833, 1, 10);
INSERT INTO `order_relationship` VALUES (1434046509711736833, 2, 13);
INSERT INTO `order_relationship` VALUES (1434046569241493505, 1, 10);
INSERT INTO `order_relationship` VALUES (1434046569241493505, 2, 13);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` bigint NOT NULL COMMENT '登录ID',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `status` int NOT NULL DEFAULT 2 COMMENT '用户状态，0为店长，1为员工，2为普通用户',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '加密后的密码',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '盐',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `login_name`(`login_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 'admin', 'super', 0, 'eb6a22b45ab11afc900dd9678aba58eb8f03a909fc6640b9008ac43c363d67c0', '845fd105acbf41b2adcc793dedd71b37');
INSERT INTO `user_info` VALUES (1433301361398661122, '陈浩天', 'username', 0, 'e70440f47319d7e4dbff3a6e401926565436932334d4b1121bd66f9dbd5dabad', 'fdf7ae597c2e4d07a5af8ce543d5458d');
INSERT INTO `user_info` VALUES (1435476640657358850, 'ttt', 'username', 2, '4a7266ee981a811b06499c71e3bb6a50441d4c59dddf6c0dd482f6687458a893', 'd7792c4d885c449bad148e3dfacbc354');
INSERT INTO `user_info` VALUES (1436298938612310018, 'cht', 'username', 2, 'a85688f514531ed98317a0b8e8b6baf437c552b97520bf168fede3319dd7c905', '0283d982a30a4a0b8219e6faecae9329');

SET FOREIGN_KEY_CHECKS = 1;
