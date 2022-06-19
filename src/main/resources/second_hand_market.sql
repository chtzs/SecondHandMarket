/*
 Navicat MySQL Data Transfer

 Source Server         : CHT
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : second_hand_market

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 19/06/2022 13:07:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bids
-- ----------------------------
DROP TABLE IF EXISTS `bids`;
CREATE TABLE `bids`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '报价id',
  `goods_id` int NULL DEFAULT NULL COMMENT '商品id',
  `buyer_id` int NULL DEFAULT NULL COMMENT '卖家id',
  `offer` decimal(10, 2) NULL DEFAULT NULL COMMENT '报价',
  `create_date` datetime NULL DEFAULT NULL COMMENT '报价创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE,
  INDEX `buyer_id`(`buyer_id`) USING BTREE,
  CONSTRAINT `bids_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `bids_ibfk_2` FOREIGN KEY (`buyer_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bids
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `seller_id` int NOT NULL COMMENT '卖家id',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名称',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品描述',
  `fake_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '原价',
  `actual_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '标价',
  `functionality` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '功能状态',
  `goods_condition` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '成色',
  `post_date` datetime NULL DEFAULT NULL COMMENT '发布日期',
  `view_count` int NULL DEFAULT 0 COMMENT '浏览次数',
  `wanted_count` int NULL DEFAULT 0 COMMENT '被想要次数',
  `status` int NULL DEFAULT 0 COMMENT '审核中，已发布，已卖出，等等',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `saler_id`(`seller_id`) USING BTREE,
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 146 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (27, 1, '海南大苹果', '海南的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 1346, 0);
INSERT INTO `goods` VALUES (28, 1, '海南大香蕉', '海南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (29, 1, '海南大梨', '海南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (30, 1, '海南大葡萄', '海南的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (31, 1, '海南大西瓜', '海南的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (32, 1, '海南小苹果', '海南的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (33, 1, '海南小香蕉', '海南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (34, 1, '海南小梨', '海南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (35, 1, '海南小葡萄', '海南的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (36, 1, '海南小西瓜', '海南的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (37, 1, '海南香苹果', '海南的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (38, 1, '海南香香蕉', '海南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (39, 1, '海南香梨', '海南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (40, 1, '海南香葡萄', '海南的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (41, 1, '海南香西瓜', '海南的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (42, 1, '海南水晶苹果', '海南的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (43, 1, '海南水晶香蕉', '海南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (44, 1, '海南水晶梨', '海南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (45, 1, '海南水晶葡萄', '海南的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (46, 1, '海南水晶西瓜', '海南的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (47, 1, '海南苹果', '海南的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (48, 1, '海南香蕉', '海南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (49, 1, '海南梨', '海南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (50, 1, '海南葡萄', '海南的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (51, 1, '海南西瓜', '海南的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (52, 1, '海南终极苹果', '海南的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (53, 1, '海南终极香蕉', '海南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (54, 1, '海南终极梨', '海南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (55, 1, '海南终极葡萄', '海南的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:05', 0, 0, 0);
INSERT INTO `goods` VALUES (56, 1, '海南终极西瓜', '海南的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (57, 1, '新疆大苹果', '新疆的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (58, 1, '新疆大香蕉', '新疆的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (59, 1, '新疆大梨', '新疆的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (60, 1, '新疆大葡萄', '新疆的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (61, 1, '新疆大西瓜', '新疆的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (62, 1, '新疆小苹果', '新疆的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (63, 1, '新疆小香蕉', '新疆的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (64, 1, '新疆小梨', '新疆的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (65, 1, '新疆小葡萄', '新疆的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (66, 1, '新疆小西瓜', '新疆的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (67, 1, '新疆香苹果', '新疆的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (68, 1, '新疆香香蕉', '新疆的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (69, 1, '新疆香梨', '新疆的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (70, 1, '新疆香葡萄', '新疆的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (71, 1, '新疆香西瓜', '新疆的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (72, 1, '新疆水晶苹果', '新疆的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (73, 1, '新疆水晶香蕉', '新疆的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (74, 1, '新疆水晶梨', '新疆的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (75, 1, '新疆水晶葡萄', '新疆的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (76, 1, '新疆水晶西瓜', '新疆的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (77, 1, '新疆苹果', '新疆的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (78, 1, '新疆香蕉', '新疆的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (79, 1, '新疆梨', '新疆的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (80, 1, '新疆葡萄', '新疆的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (81, 1, '新疆西瓜', '新疆的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (82, 1, '新疆终极苹果', '新疆的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (83, 1, '新疆终极香蕉', '新疆的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (84, 1, '新疆终极梨', '新疆的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (85, 1, '新疆终极葡萄', '新疆的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (86, 1, '新疆终极西瓜', '新疆的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (87, 1, '山东大苹果', '山东的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (88, 1, '山东大香蕉', '山东的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (89, 1, '山东大梨', '山东的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (90, 1, '山东大葡萄', '山东的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (91, 1, '山东大西瓜', '山东的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (92, 1, '山东小苹果', '山东的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (93, 1, '山东小香蕉', '山东的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (94, 1, '山东小梨', '山东的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (95, 1, '山东小葡萄', '山东的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (96, 1, '山东小西瓜', '山东的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (97, 1, '山东香苹果', '山东的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (98, 1, '山东香香蕉', '山东的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (99, 1, '山东香梨', '山东的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (100, 1, '山东香葡萄', '山东的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (101, 1, '山东香西瓜', '山东的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (102, 1, '山东水晶苹果', '山东的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (103, 1, '山东水晶香蕉', '山东的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (104, 1, '山东水晶梨', '山东的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (105, 1, '山东水晶葡萄', '山东的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (106, 1, '山东水晶西瓜', '山东的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (107, 1, '山东苹果', '山东的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (108, 1, '山东香蕉', '山东的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (109, 1, '山东梨', '山东的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (110, 1, '山东葡萄', '山东的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (111, 1, '山东西瓜', '山东的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (112, 1, '山东终极苹果', '山东的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (113, 1, '山东终极香蕉', '山东的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (114, 1, '山东终极梨', '山东的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (115, 1, '山东终极葡萄', '山东的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (116, 1, '山东终极西瓜', '山东的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (117, 1, '云南大苹果', '云南的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (118, 1, '云南大香蕉', '云南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (119, 1, '云南大梨', '云南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (120, 1, '云南大葡萄', '云南的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (121, 1, '云南大西瓜', '云南的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (122, 1, '云南小苹果', '云南的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (123, 1, '云南小香蕉', '云南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (124, 1, '云南小梨', '云南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (125, 1, '云南小葡萄', '云南的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (126, 1, '云南小西瓜', '云南的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (127, 1, '云南香苹果', '云南的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (128, 1, '云南香香蕉', '云南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (129, 1, '云南香梨', '云南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (130, 1, '云南香葡萄', '云南的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (131, 1, '云南香西瓜', '云南的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (132, 1, '云南水晶苹果', '云南的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (133, 1, '云南水晶香蕉', '云南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (134, 1, '云南水晶梨', '云南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (135, 1, '云南水晶葡萄', '云南的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (136, 1, '云南水晶西瓜', '云南的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (137, 1, '云南苹果', '云南的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (138, 1, '云南香蕉', '云南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (139, 1, '云南梨', '云南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (140, 1, '云南葡萄', '云南的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (141, 1, '云南西瓜', '云南的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (142, 1, '云南终极苹果', '云南的', 1005.00, 5.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (143, 1, '云南终极香蕉', '云南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (144, 1, '云南终极梨', '云南的', 1003.00, 3.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (145, 1, '云南终极葡萄', '云南的', 1010.00, 10.00, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);
INSERT INTO `goods` VALUES (146, 1, '云南终极西瓜', '云南的', 1000.50, 0.50, '甜美多汁！', '全新！', '2022-06-13 01:28:06', 0, 0, 0);

-- ----------------------------
-- Table structure for messages
-- ----------------------------
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `session_id` int NULL DEFAULT NULL COMMENT '会话id',
  `sender_id` int NULL DEFAULT NULL COMMENT '发送方id',
  `receiver_id` int NULL DEFAULT NULL COMMENT '接收方id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发送内容',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sender_id`(`sender_id`) USING BTREE,
  INDEX `receiver_id`(`receiver_id`) USING BTREE,
  INDEX `session_id`(`session_id`) USING BTREE,
  CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `messages_ibfk_3` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `messages_ibfk_4` FOREIGN KEY (`session_id`) REFERENCES `sessions` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of messages
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `goods_id` int NULL DEFAULT NULL COMMENT '商品id',
  `buyer_id` int NULL DEFAULT NULL COMMENT '卖家id',
  `pay_date` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `actual_pay` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际付款',
  `delivery_status` int NULL DEFAULT NULL COMMENT '商品状态，在运输中，已成功送达，已成功丢失等等',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for reviews
-- ----------------------------
DROP TABLE IF EXISTS `reviews`;
CREATE TABLE `reviews`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '审核id',
  `goods_id` int NULL DEFAULT NULL COMMENT '被审核的商品id',
  `status` int NOT NULL DEFAULT 2 COMMENT '管理员审核决定，0为通过，1为拒绝，2为审核中',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE,
  CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 120 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reviews
-- ----------------------------
INSERT INTO `reviews` VALUES (1, 27, 0);
INSERT INTO `reviews` VALUES (2, 28, 0);
INSERT INTO `reviews` VALUES (3, 29, 0);
INSERT INTO `reviews` VALUES (4, 30, 0);
INSERT INTO `reviews` VALUES (5, 31, 0);
INSERT INTO `reviews` VALUES (6, 32, 0);
INSERT INTO `reviews` VALUES (7, 33, 0);
INSERT INTO `reviews` VALUES (8, 34, 0);
INSERT INTO `reviews` VALUES (9, 35, 0);
INSERT INTO `reviews` VALUES (10, 36, 0);
INSERT INTO `reviews` VALUES (11, 37, 0);
INSERT INTO `reviews` VALUES (12, 38, 0);
INSERT INTO `reviews` VALUES (13, 39, 0);
INSERT INTO `reviews` VALUES (14, 40, 0);
INSERT INTO `reviews` VALUES (15, 41, 0);
INSERT INTO `reviews` VALUES (16, 42, 0);
INSERT INTO `reviews` VALUES (17, 43, 0);
INSERT INTO `reviews` VALUES (18, 44, 0);
INSERT INTO `reviews` VALUES (19, 45, 0);
INSERT INTO `reviews` VALUES (20, 46, 0);
INSERT INTO `reviews` VALUES (21, 47, 0);
INSERT INTO `reviews` VALUES (22, 48, 0);
INSERT INTO `reviews` VALUES (23, 49, 0);
INSERT INTO `reviews` VALUES (24, 50, 0);
INSERT INTO `reviews` VALUES (25, 51, 0);
INSERT INTO `reviews` VALUES (26, 52, 0);
INSERT INTO `reviews` VALUES (27, 53, 0);
INSERT INTO `reviews` VALUES (28, 54, 0);
INSERT INTO `reviews` VALUES (29, 55, 0);
INSERT INTO `reviews` VALUES (30, 56, 0);
INSERT INTO `reviews` VALUES (31, 57, 0);
INSERT INTO `reviews` VALUES (32, 58, 0);
INSERT INTO `reviews` VALUES (33, 59, 0);
INSERT INTO `reviews` VALUES (34, 60, 0);
INSERT INTO `reviews` VALUES (35, 61, 0);
INSERT INTO `reviews` VALUES (36, 62, 0);
INSERT INTO `reviews` VALUES (37, 63, 0);
INSERT INTO `reviews` VALUES (38, 64, 0);
INSERT INTO `reviews` VALUES (39, 65, 0);
INSERT INTO `reviews` VALUES (40, 66, 0);
INSERT INTO `reviews` VALUES (41, 67, 0);
INSERT INTO `reviews` VALUES (42, 68, 0);
INSERT INTO `reviews` VALUES (43, 69, 0);
INSERT INTO `reviews` VALUES (44, 70, 0);
INSERT INTO `reviews` VALUES (45, 71, 0);
INSERT INTO `reviews` VALUES (46, 72, 0);
INSERT INTO `reviews` VALUES (47, 73, 0);
INSERT INTO `reviews` VALUES (48, 74, 0);
INSERT INTO `reviews` VALUES (49, 75, 0);
INSERT INTO `reviews` VALUES (50, 76, 0);
INSERT INTO `reviews` VALUES (51, 77, 0);
INSERT INTO `reviews` VALUES (52, 78, 0);
INSERT INTO `reviews` VALUES (53, 79, 0);
INSERT INTO `reviews` VALUES (54, 80, 0);
INSERT INTO `reviews` VALUES (55, 81, 0);
INSERT INTO `reviews` VALUES (56, 82, 0);
INSERT INTO `reviews` VALUES (57, 83, 0);
INSERT INTO `reviews` VALUES (58, 84, 0);
INSERT INTO `reviews` VALUES (59, 85, 0);
INSERT INTO `reviews` VALUES (60, 86, 0);
INSERT INTO `reviews` VALUES (61, 87, 0);
INSERT INTO `reviews` VALUES (62, 88, 0);
INSERT INTO `reviews` VALUES (63, 89, 0);
INSERT INTO `reviews` VALUES (64, 90, 0);
INSERT INTO `reviews` VALUES (65, 91, 0);
INSERT INTO `reviews` VALUES (66, 92, 0);
INSERT INTO `reviews` VALUES (67, 93, 0);
INSERT INTO `reviews` VALUES (68, 94, 0);
INSERT INTO `reviews` VALUES (69, 95, 0);
INSERT INTO `reviews` VALUES (70, 96, 0);
INSERT INTO `reviews` VALUES (71, 97, 0);
INSERT INTO `reviews` VALUES (72, 98, 0);
INSERT INTO `reviews` VALUES (73, 99, 0);
INSERT INTO `reviews` VALUES (74, 100, 0);
INSERT INTO `reviews` VALUES (75, 101, 0);
INSERT INTO `reviews` VALUES (76, 102, 0);
INSERT INTO `reviews` VALUES (77, 103, 0);
INSERT INTO `reviews` VALUES (78, 104, 0);
INSERT INTO `reviews` VALUES (79, 105, 0);
INSERT INTO `reviews` VALUES (80, 106, 0);
INSERT INTO `reviews` VALUES (81, 107, 0);
INSERT INTO `reviews` VALUES (82, 108, 0);
INSERT INTO `reviews` VALUES (83, 109, 0);
INSERT INTO `reviews` VALUES (84, 110, 0);
INSERT INTO `reviews` VALUES (85, 111, 0);
INSERT INTO `reviews` VALUES (86, 112, 0);
INSERT INTO `reviews` VALUES (87, 113, 0);
INSERT INTO `reviews` VALUES (88, 114, 0);
INSERT INTO `reviews` VALUES (89, 115, 0);
INSERT INTO `reviews` VALUES (90, 116, 0);
INSERT INTO `reviews` VALUES (91, 117, 0);
INSERT INTO `reviews` VALUES (92, 118, 0);
INSERT INTO `reviews` VALUES (93, 119, 0);
INSERT INTO `reviews` VALUES (94, 120, 0);
INSERT INTO `reviews` VALUES (95, 121, 0);
INSERT INTO `reviews` VALUES (96, 122, 0);
INSERT INTO `reviews` VALUES (97, 123, 0);
INSERT INTO `reviews` VALUES (98, 124, 0);
INSERT INTO `reviews` VALUES (99, 125, 0);
INSERT INTO `reviews` VALUES (100, 126, 0);
INSERT INTO `reviews` VALUES (101, 127, 0);
INSERT INTO `reviews` VALUES (102, 128, 0);
INSERT INTO `reviews` VALUES (103, 129, 0);
INSERT INTO `reviews` VALUES (104, 130, 0);
INSERT INTO `reviews` VALUES (105, 131, 0);
INSERT INTO `reviews` VALUES (106, 132, 0);
INSERT INTO `reviews` VALUES (107, 133, 0);
INSERT INTO `reviews` VALUES (108, 134, 0);
INSERT INTO `reviews` VALUES (109, 135, 0);
INSERT INTO `reviews` VALUES (110, 136, 0);
INSERT INTO `reviews` VALUES (111, 137, 0);
INSERT INTO `reviews` VALUES (112, 138, 0);
INSERT INTO `reviews` VALUES (113, 139, 0);
INSERT INTO `reviews` VALUES (114, 140, 0);
INSERT INTO `reviews` VALUES (115, 141, 0);
INSERT INTO `reviews` VALUES (116, 142, 0);
INSERT INTO `reviews` VALUES (117, 143, 0);
INSERT INTO `reviews` VALUES (118, 144, 0);
INSERT INTO `reviews` VALUES (119, 145, 0);
INSERT INTO `reviews` VALUES (120, 146, 0);

-- ----------------------------
-- Table structure for sessions
-- ----------------------------
DROP TABLE IF EXISTS `sessions`;
CREATE TABLE `sessions`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `goods_id` int NOT NULL,
  `buyer_id` int NULL DEFAULT NULL,
  `seller_id` int NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE,
  INDEX `buyer_id`(`buyer_id`) USING BTREE,
  INDEX `saler_id`(`seller_id`) USING BTREE,
  INDEX `create_time`(`create_date`) USING BTREE,
  CONSTRAINT `sessions_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sessions_ibfk_2` FOREIGN KEY (`buyer_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sessions_ibfk_3` FOREIGN KEY (`seller_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sessions
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '内部id，唯一',
  `phone_number` bigint NOT NULL COMMENT '手机号',
  `nickname` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '盐',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像url',
  `gender` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `identity` int NOT NULL DEFAULT 0 COMMENT '用户权限，0是用户，1是管理员',
  `register_date` datetime NULL DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `phone_number`(`phone_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 110, '陈浩天_0', '95b0171a431d47b563c20151bbebd746a68207a687a0bf2bad282d0fa8ef9ccc', '0d97be34776e4ccb98db488427c923e7', NULL, NULL, 1, '2022-06-08 02:57:45');
INSERT INTO `users` VALUES (2, 111, '陈浩天_1', '53777ac9ebd249aab24e57f56f5da3e8e9c6a687cb1d58d3e420f7bd3e58affd', 'ffc4d68f535e403eb76ee2712454f62f', NULL, NULL, 0, '2022-06-08 02:59:27');
INSERT INTO `users` VALUES (3, 112, '陈浩天_2', 'b8822eb1629bae69aba2d9c9c603d825b582bfd101f42754d1ba4e01ac8ebce', 'c11bce2545444b978241ed37a484dcd5', NULL, NULL, 0, '2022-06-08 02:59:28');
INSERT INTO `users` VALUES (4, 113, '陈浩天_3', 'b741c18f8080d75e9624a70c809bc80b972d2823c459dcc1b1781a12a9e24139', '49fca2516ea04d64b69ec983f75a8e2a', NULL, NULL, 0, '2022-06-08 02:59:28');
INSERT INTO `users` VALUES (5, 114, '陈浩天_4', '3f2e54915ffc3cc34943cef4c4a3be8be218e24b8264c8d1798a2001fb30223c', '1a4285eaeb1c46d9914ff8fdc2aced28', NULL, NULL, 0, '2022-06-08 02:59:28');
INSERT INTO `users` VALUES (6, 115, '陈浩天_5', '2716b5e8f10c2e10eb6834b599242872bd4b576747812d2d8839bbe9323c8eab', '3869cd26ba724c3bb06cda9fb234d2ab', NULL, NULL, 0, '2022-06-08 02:59:28');
INSERT INTO `users` VALUES (7, 116, '陈浩天_6', '424e62615086e90573b622866bfeaf946c56acced7536c1e4940df44a01e839a', '306b08e2c8494bcb8037a715e389d478', NULL, NULL, 0, '2022-06-08 02:59:28');
INSERT INTO `users` VALUES (8, 117, '陈浩天_7', 'fd47a00c9bb5077b5a12d1dcbc1501628db9ad4e48c1cd3324093333ee631a54', 'ec46f8135b1047dd9262005b15dcc37e', NULL, NULL, 0, '2022-06-08 02:59:28');
INSERT INTO `users` VALUES (9, 118, '陈浩天_8', '5d01858fb9995c5a2c72a46f43c4e4bca795bfdd61701a70a4a0208e921f7a6e', '05bcd7012f924d779bd9fa06f8e599cd', NULL, NULL, 0, '2022-06-08 02:59:28');
INSERT INTO `users` VALUES (10, 119, '陈浩天_9', '46a04c1a280446d047e11b5368f3eb89b78ece5632e2727ada68083adec8e7bc', 'ec89b8a3e18a449d86b3395725bd33db', NULL, NULL, 0, '2022-06-08 02:59:28');
INSERT INTO `users` VALUES (11, 120, '陈浩天_10', 'ee108fd3b0811e83e84e7272c5d4d556e3bdf93bae10269e89a52a3a9f205eb6', '72d240fdbb8a45c2b917690ab43ab330', NULL, NULL, 0, '2022-06-08 02:59:28');

-- ----------------------------
-- Table structure for users_want
-- ----------------------------
DROP TABLE IF EXISTS `users_want`;
CREATE TABLE `users_want`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `goods_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE,
  CONSTRAINT `users_want_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `users_want_ibfk_2` FOREIGN KEY (`goods_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users_want
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
