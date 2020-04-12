/*
 Navicat Premium Data Transfer

 Source Server         : audit-guoyi
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : cq-cdb-c1r4n3wb.sql.tencentcdb.com:63981
 Source Schema         : gyzxnew

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 18/09/2019 14:58:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dic
-- ----------------------------
DROP TABLE IF EXISTS `sys_dic`;
CREATE TABLE `sys_dic`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id自增列',
  `keycode` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编码',
  `title` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `ishidkey` int(11) NOT NULL DEFAULT 0 COMMENT '是否隐藏子表sys_dic_content>keycode，keycode作为某些特殊使用时防止被篡改，则隐藏',
  `isadd` int(11) NOT NULL DEFAULT 0 COMMENT '是否允许添加0-否,1-是',
  `isedit` int(11) NOT NULL DEFAULT 0 COMMENT '是否允许编辑0-否,1-是',
  `isdel` int(11) NOT NULL DEFAULT 0 COMMENT '是否允许删除(0-否,1-是)',
  `issys` int(11) NOT NULL DEFAULT 0 COMMENT '是否系统用字典（值为1时不进行展示）0-否,1-是',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_dic_keycode_Idx`(`keycode`) USING BTREE,
  INDEX `sys_dic_id_Idx`(`id`) USING BTREE,
  INDEX `sys_dic_isedit_Idx`(`isedit`) USING BTREE,
  INDEX `sys_dic_issys_Idx`(`issys`) USING BTREE,
  INDEX `sys_dic_isadd_Idx`(`isadd`) USING BTREE,
  INDEX `sys_dic_isdel_Idx`(`isdel`) USING BTREE,
  INDEX `sys_dic_ishidkey_Idx`(`ishidkey`) USING BTREE,
  INDEX `sys_dic_titl_Idx`(`title`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典定义' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dic
-- ----------------------------
INSERT INTO `sys_dic` VALUES (1, 'kindid  ', '剂型', 0, 1, 1, 1, 0);
INSERT INTO `sys_dic` VALUES (2, 'eattime ', '吃药时间', 0, 0, 0, 1, 0);
INSERT INTO `sys_dic` VALUES (3, 'tude', '用药禁忌', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (4, 'hidetype ', '药方可见状态', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (5, ' cardtype', '证件类型', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (6, 'method', ' 特殊用法字', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (7, 'levelid', '医院级别', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (8, 'labelids', '就诊人标签', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (9, 'jobid', '职称', 0, 0, 0, 0, 1);
INSERT INTO `sys_dic` VALUES (10, 'starname ', '名医标签', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (11, 'nation', '民族', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (12, 'phrasestype', '医生常用语类型', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (13, 'membertype', '用户类型', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (14, 'registsource', '注册来源', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (15, 'member_flow', '会员资金流水变动类型', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (16, 'member_payaccount', '账户类型', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (17, 'paytype ', '提现类型', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (18, 'otr_activity', '活动类型', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (19, 'otr_comment_type', '评价类型', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (20, 'otr_comment', '评价标签', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (21, 'otr_wechat_itext', '消息类型', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (22, 'otr_welfare', '福利类型', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (23, 'otr_wx_template_msg', '微信模版消息(开发使用)', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (24, 'timetype', ' 坐诊时段', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (25, 'sre_ unit ', '单位', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (26, 'sre_state', '问题模版', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (27, 'adv_advice_status_code ', '会话业务状态', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (28, 'adv_prescript_order_status', '药方业务状态', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (29, 'reg_satatus', '挂号业务状态', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (30, 'copywriting', '文案类型', 0, 1, 1, 1, 0);
INSERT INTO `sys_dic` VALUES (31, 'specialtime', '特殊时期', 0, 0, 0, 0, 0);
INSERT INTO `sys_dic` VALUES (36, 'registerType', '挂号级别', 0, 0, 0, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;


