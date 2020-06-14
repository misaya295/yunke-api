/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : db-yunke

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 14/06/2020 14:52:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) NOT NULL,
  `scope` varchar(255) NOT NULL,
  `authorized_grant_types` varchar(255) NOT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int NOT NULL,
  `refresh_token_validity` int DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` tinyint DEFAULT NULL,
  `origin_secret` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='OAuth2 认证授权客户端表';

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('app', '', '$2a$10$8Qk/efslEpO1Af1kyw/rp.DdJGsdnET8UCp1vGDzpQEa.1qBklvua', 'all', 'password,refresh_token', '', NULL, 86400, 8640000, NULL, 1, '123456');
INSERT INTO `oauth_client_details` VALUES ('yunke', NULL, '$2a$10$aSZTvMOtUAYUQ.75z2n3ceJd6dCIk9Vy3J/SKZUE4hBLd6sz7.6ge', 'all', 'password,refresh_token', NULL, NULL, 86400, 8640000, NULL, 1, '123456');
COMMIT;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `code` varchar(255) DEFAULT NULL,
  `authentication` blob,
  KEY `code_index` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='OAuth2认证授权授权码表';

-- ----------------------------
-- Records of oauth_code
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_certificate
-- ----------------------------
DROP TABLE IF EXISTS `t_certificate`;
CREATE TABLE `t_certificate` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '考证ID',
  `user_id` int NOT NULL COMMENT '考证人ID',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `title` varchar(60) NOT NULL COMMENT '证书名称',
  `cost` double(10,2) DEFAULT NULL COMMENT '费用',
  `time` varchar(32) DEFAULT NULL COMMENT '考证时间',
  `invoice` varchar(255) DEFAULT NULL COMMENT '发票',
  `certificate` varchar(255) DEFAULT NULL COMMENT '证书',
  `success` int DEFAULT NULL COMMENT '通过状态(0:失败/1:成功)',
  `reimbursement` int DEFAULT NULL COMMENT '是否已报销（0否/1是）',
  `state` int NOT NULL COMMENT '状态（0正在考取/1已完成）',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `考证人` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='资格证表';

-- ----------------------------
-- Records of t_certificate
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_copyright
-- ----------------------------
DROP TABLE IF EXISTS `t_copyright`;
CREATE TABLE `t_copyright` (
  `copyright_uuid` varchar(32) NOT NULL COMMENT '软件著作权ID',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `introduction` varchar(255) DEFAULT NULL COMMENT '简介',
  `start_time` varchar(32) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(32) DEFAULT NULL COMMENT '结束时间',
  `certificate` varchar(255) DEFAULT NULL COMMENT '证书',
  `application_form` varchar(100) DEFAULT NULL COMMENT '申请书',
  `origin_file` varchar(100) DEFAULT NULL COMMENT '源文件',
  `agreement` varchar(100) DEFAULT NULL COMMENT '软件协议',
  `cost` double(10,2) DEFAULT NULL COMMENT '费用',
  `invoice` varchar(255) DEFAULT NULL COMMENT '发票',
  `item_uuid` varchar(32) DEFAULT NULL COMMENT '项目ID',
  `state` int DEFAULT NULL COMMENT '状态(1:进行中/2:已完成/3:申报中)',
  `reimbursement` int DEFAULT NULL COMMENT '是否已报销（0否/1是）',
  PRIMARY KEY (`copyright_uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='软件著作权_任务表';

-- ----------------------------
-- Records of t_copyright
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `parent_id` bigint NOT NULL COMMENT '上级部门ID',
  `dept_name` varchar(100) NOT NULL COMMENT '部门名称',
  `order_num` int DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of t_dept
-- ----------------------------
BEGIN;
INSERT INTO `t_dept` VALUES (1, 0, '云课教师组', 1, '2018-01-04 15:42:26', '2020-06-13 18:34:51');
INSERT INTO `t_dept` VALUES (2, 1, '教务处', 1, '2018-01-04 15:42:34', '2020-06-03 23:42:11');
INSERT INTO `t_dept` VALUES (3, 1, '学生处', 2, '2018-01-04 15:42:29', '2020-06-03 23:42:24');
INSERT INTO `t_dept` VALUES (4, 0, '云课学生组', 2, '2018-01-04 15:42:36', '2020-06-13 18:35:01');
INSERT INTO `t_dept` VALUES (10, 4, '前端开发', 1, '2020-06-03 23:43:21', '2020-06-13 18:35:23');
INSERT INTO `t_dept` VALUES (47, 4, '后端开发', 2, '2020-06-13 18:35:40', NULL);
INSERT INTO `t_dept` VALUES (48, 4, '网络安全', 3, '2020-06-13 18:35:46', NULL);
INSERT INTO `t_dept` VALUES (49, 4, 'UI设计', 4, '2020-06-13 18:36:01', NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_funding
-- ----------------------------
DROP TABLE IF EXISTS `t_funding`;
CREATE TABLE `t_funding` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `verifier_id` int DEFAULT NULL COMMENT '审核人ID',
  `certifier_id` int DEFAULT NULL COMMENT '证明人ID',
  `name` varchar(60) NOT NULL COMMENT '名称',
  `type` varchar(100) DEFAULT NULL COMMENT '类型',
  `apply_time` varchar(32) NOT NULL COMMENT '申请时间',
  `success_time` varchar(32) DEFAULT NULL COMMENT '报销成功时间',
  `invoice` varchar(255) DEFAULT NULL COMMENT '发票',
  `cost` double(10,2) DEFAULT NULL COMMENT '费用',
  `card` varchar(32) DEFAULT NULL COMMENT '银行卡号',
  `proposer_id` int NOT NULL COMMENT '申请人的ID',
  `state` int DEFAULT NULL COMMENT '申请状态(1申请中/2报销中/3报销成功/4申请失败)',
  `task_uuid` varchar(32) DEFAULT NULL COMMENT '对应的任务UUID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `经手人` (`verifier_id`) USING BTREE,
  KEY `proverUUID` (`certifier_id`) USING BTREE,
  KEY `proposer_id` (`proposer_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='经费表';

-- ----------------------------
-- Records of t_funding
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_items
-- ----------------------------
DROP TABLE IF EXISTS `t_items`;
CREATE TABLE `t_items` (
  `items_uuid` varchar(32) NOT NULL COMMENT '项目ID',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `introduction` varchar(1500) DEFAULT NULL COMMENT '简介',
  `start_time` varchar(32) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(32) DEFAULT NULL COMMENT '结束时间',
  `cost` double(10,2) DEFAULT NULL COMMENT '费用',
  `invoice` varchar(255) DEFAULT NULL COMMENT '发票',
  `specification` varchar(255) DEFAULT NULL COMMENT '项目说明书',
  `url` varchar(255) DEFAULT NULL COMMENT '源文件',
  `state` int DEFAULT NULL COMMENT '状态(1:进行中/2:已完成)',
  `reimbursement` int DEFAULT NULL COMMENT '是否已报销（0否/1是）',
  PRIMARY KEY (`items_uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='项目_任务表';

-- ----------------------------
-- Records of t_items
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `username` varchar(50) DEFAULT NULL COMMENT '操作用户',
  `operation` text COMMENT '操作内容',
  `time` decimal(11,0) DEFAULT NULL COMMENT '耗时',
  `method` text COMMENT '操作方法',
  `params` text COMMENT '方法参数',
  `ip` varchar(64) DEFAULT NULL COMMENT '操作者IP',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `location` varchar(50) DEFAULT NULL COMMENT '操作地点',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `t_log_create_time` (`create_time`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=136 DEFAULT CHARSET=utf8 COMMENT='用户操作日志表';

-- ----------------------------
-- Records of t_log
-- ----------------------------
BEGIN;
INSERT INTO `t_log` VALUES (1, 'chachae', '新增部门', 79, 'com.cloudx.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=7, parentId=5, deptName=111212, orderNum=0, createTime=Sat May 30 17:04:10 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '169.254.202.65', '2020-05-30 17:04:11', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (2, 'chachae', '删除部门', 165, 'com.cloudx.server.system.controller.DeptController.deleteDepts()', ' deptIds: \"7\"', '169.254.202.65', '2020-05-30 17:04:18', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (3, 'chachae', '更新部门', 62, 'com.cloudx.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=3, parentId=2, deptName=开发二部, orderNum=2, createTime=null, updateTime=Sat May 30 17:56:53 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '169.254.202.65', '2020-05-30 17:56:54', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (4, 'chachae', '更新部门', 38, 'com.cloudx.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=3, parentId=1, deptName=开发二部, orderNum=2, createTime=null, updateTime=Sat May 30 17:57:11 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '169.254.202.65', '2020-05-30 17:57:11', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (5, 'chachae', '新增部门', 52, 'com.cloudx.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=8, parentId=3, deptName=开发二部（南方分部）, orderNum=0, createTime=Sat May 30 17:58:46 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '169.254.202.65', '2020-05-30 17:58:46', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (6, 'chachae', '新增角色', 130, 'com.cloudx.server.system.controller.RoleController.addRole()', ' role: \"Role(roleId=5, roleName=辅导员, remark=辅导员, createTime=Sun May 31 22:48:15 CST 2020, updateTime=null, menuIds=3)\"', '169.254.113.159', '2020-05-31 22:48:16', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (7, 'chachae', '新增用户', 206, 'com.cloudx.server.system.controller.UserController.addUser()', ' user: \"SystemUser(userId=19, username=fudaoyuan, password=$2a$10$WJqH8KEzpQ4y7pW5NM.KAeCnE217w8Rqh9/R94HDczqmfjPFP/am., deptId=4, email=fhu@qq.com, mobile=, status=1, createTime=Sun May 31 22:49:41 CST 2020, updateTime=null, lastLoginTime=null, sex=0, avatar=default.jpg, description=null, deptName=null, createTimeFrom=null, createTimeTo=null, roleId=5, roleName=null, deptIds=)\"', '169.254.113.159', '2020-05-31 22:49:42', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (8, 'chachae', '删除用户', 102, 'com.cloudx.server.system.controller.UserController.deleteUsers()', ' userIds: \"19\"', '169.254.113.159', '2020-05-31 22:50:36', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (9, 'chachae', '删除角色', 143, 'com.cloudx.server.system.controller.RoleController.deleteRoles()', ' roleIds: \"5\"', '169.254.113.159', '2020-05-31 22:50:42', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (10, 'chachae', '更新角色', 393, 'com.cloudx.server.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=null, remark=管理员, createTime=null, updateTime=Mon Jun 01 15:42:38 CST 2020, menuIds=1,3,135,130,13,12,11,4,14,15,16,131,5,132,17,18,19,6,133,20,21,22,163,167,164,165,166,2,180,10,136,24,150,151,152,173,174,175,176,177,178,179,156,157,159,158,160,154,155,168,169,170,171,172)\"', '169.254.97.217', '2020-06-01 15:42:39', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (11, 'chachae', '新增菜单', 113, 'com.oes.server.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=195, parentId=0, menuName=考试服务, path=/examination, component=Layout, expression=null, icon=el-icon-document-checked, type=0, orderNum=3, createTime=Wed Jun 03 21:18:03 CST 2020, updateTime=null)\"', '127.0.0.1', '2020-06-03 21:18:04', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (12, 'chachae', '更新角色', 319, 'com.oes.server.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=null, remark=管理员, createTime=null, updateTime=Wed Jun 03 21:18:23 CST 2020, menuIds=1,3,135,130,11,12,13,4,131,14,15,16,5,132,17,18,19,6,133,20,21,22,163,164,165,166,167,2,180,10,136,24,150,151,152,195,173,174,175,176,177,178,179,156,157,159,158,160,154,155,168,169,170,171,172)\"', '127.0.0.1', '2020-06-03 21:18:24', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (13, 'chachae', '新增菜单', 32, 'com.oes.server.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=196, parentId=195, menuName=试题类型管理, path=/examination/type, component=febs/examination/type/Index, expression=null, icon=, type=0, orderNum=0, createTime=Wed Jun 03 21:22:07 CST 2020, updateTime=null)\"', '127.0.0.1', '2020-06-03 21:22:08', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (14, 'chachae', '更新角色', 545, 'com.oes.server.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=null, remark=管理员, createTime=null, updateTime=Wed Jun 03 21:22:28 CST 2020, menuIds=1,3,130,135,13,11,12,4,131,14,15,16,5,132,17,18,19,6,133,22,20,21,163,167,166,165,164,2,180,10,24,136,150,152,151,195,196,173,174,175,176,177,178,179,156,157,159,158,160,154,155,168,169,170,171,172)\"', '127.0.0.1', '2020-06-03 21:22:29', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (15, 'chachae', '新增菜单', 446, 'com.oes.server.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=197, parentId=196, menuName=新增类型, path=null, component=null, expression=null, icon=null, type=1, orderNum=null, createTime=Wed Jun 03 21:39:50 CST 2020, updateTime=null)\"', '127.0.0.1', '2020-06-03 21:39:51', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (16, 'chachae', '新增菜单', 61, 'com.oes.server.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=198, parentId=196, menuName=修改类型, path=null, component=null, expression=null, icon=null, type=1, orderNum=null, createTime=Wed Jun 03 21:40:48 CST 2020, updateTime=null)\"', '127.0.0.1', '2020-06-03 21:40:49', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (17, 'chachae', '新增菜单', 30, 'com.oes.server.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=199, parentId=196, menuName=删除类型, path=null, component=null, expression=null, icon=null, type=1, orderNum=null, createTime=Wed Jun 03 21:41:09 CST 2020, updateTime=null)\"', '127.0.0.1', '2020-06-03 21:41:09', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (18, 'chachae', '更新角色', 292, 'com.oes.server.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=null, remark=管理员, createTime=null, updateTime=Wed Jun 03 21:41:21 CST 2020, menuIds=1,3,135,130,11,12,13,4,131,14,15,16,5,132,17,18,19,6,133,20,21,22,163,164,165,166,167,2,180,10,136,24,150,151,152,173,174,175,176,177,178,179,195,196,199,198,197,156,157,159,158,160,154,155,168,169,170,171,172)\"', '127.0.0.1', '2020-06-03 21:41:22', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (19, 'chachae', '更新角色', 89, 'com.oes.server.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=196, parentId=195, menuName=试题类型管理, path=/examination/type, component=oes/examination/type/Index, perms=type:vie, icon=, type=0, orderNum=0, createTime=null, updateTime=Wed Jun 03 21:54:37 CST 2020)\"', '127.0.0.1', '2020-06-03 21:54:38', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (20, 'chachae', '更新角色', 38, 'com.oes.server.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=196, parentId=195, menuName=试题类型管理, path=/examination/type, component=oes/examination/type/Index, perms=type:view, icon=, type=0, orderNum=0, createTime=null, updateTime=Wed Jun 03 21:54:54 CST 2020)\"', '127.0.0.1', '2020-06-03 21:54:55', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (21, 'chachae', '更新角色', 350, 'com.oes.server.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=null, remark=管理员, createTime=null, updateTime=Wed Jun 03 23:39:14 CST 2020, menuIds=1,3,135,130,11,12,13,4,131,14,15,16,5,132,17,18,19,6,133,20,21,22,163,164,165,166,167,2,180,10,136,24,150,151,152,173,174,175,176,177,178,179,195,196,199,197,156,157,159,158,160,154,155,168,169,170,171,172)\"', '127.0.0.1', '2020-06-03 23:39:15', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (22, 'chachae', '更新角色', 315, 'com.oes.server.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=null, remark=管理员, createTime=null, updateTime=Wed Jun 03 23:40:00 CST 2020, menuIds=1,3,135,130,11,12,13,4,131,14,15,16,5,132,17,18,19,6,133,20,21,22,163,164,165,166,167,2,180,10,136,24,150,151,152,173,174,175,176,177,178,179,195,196,199,198,197,156,157,159,158,160,154,155,168,169,170,171,172)\"', '127.0.0.1', '2020-06-03 23:40:01', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (23, 'chachae', '删除部门', 104, 'com.oes.server.system.controller.DeptController.deleteDepts()', ' deptIds: \"8\"', '127.0.0.1', '2020-06-03 23:41:14', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (24, 'chachae', '更新部门', 42, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=1, parentId=0, deptName=核心部门, orderNum=1, createTime=null, updateTime=Wed Jun 03 23:42:05 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:42:05', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (25, 'chachae', '更新部门', 49, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=2, parentId=1, deptName=教务处, orderNum=1, createTime=null, updateTime=Wed Jun 03 23:42:10 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:42:11', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (26, 'chachae', '更新部门', 39, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=3, parentId=1, deptName=学生处, orderNum=2, createTime=null, updateTime=Wed Jun 03 23:42:24 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:42:24', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (27, 'chachae', '更新部门', 38, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=4, parentId=0, deptName=计算机科学与工程学院, orderNum=2, createTime=null, updateTime=Wed Jun 03 23:42:39 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:42:40', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (28, 'chachae', '更新部门', 39, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=5, parentId=0, deptName=外国语学院, orderNum=3, createTime=null, updateTime=Wed Jun 03 23:42:49 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:42:50', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (29, 'chachae', '更新部门', 46, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=6, parentId=0, deptName=机电工程学院, orderNum=4, createTime=null, updateTime=Wed Jun 03 23:42:57 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:42:58', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (30, 'chachae', '新增部门', 48, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=9, parentId=4, deptName=计算机科学与技术, orderNum=0, createTime=Wed Jun 03 23:43:09 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:43:10', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (31, 'chachae', '新增部门', 33, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=10, parentId=4, deptName=网络工程, orderNum=1, createTime=Wed Jun 03 23:43:20 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:43:21', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (32, 'chachae', '新增部门', 42, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=11, parentId=4, deptName=数字媒体技术, orderNum=2, createTime=Wed Jun 03 23:43:28 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:43:28', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (33, 'chachae', '新增部门', 37, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=12, parentId=4, deptName=软件工程, orderNum=3, createTime=Wed Jun 03 23:43:34 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:43:35', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (34, 'chachae', '新增部门', 39, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=13, parentId=4, deptName=大数据技术, orderNum=0, createTime=Wed Jun 03 23:43:42 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:43:43', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (35, 'chachae', '新增部门', 50, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=14, parentId=4, deptName=网络空间安全, orderNum=0, createTime=Wed Jun 03 23:43:50 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:43:50', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (36, 'chachae', '更新部门', 39, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=13, parentId=4, deptName=大数据技术, orderNum=1, createTime=null, updateTime=Wed Jun 03 23:44:10 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:44:11', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (37, 'chachae', '更新部门', 44, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=14, parentId=4, deptName=网络空间安全, orderNum=2, createTime=null, updateTime=Wed Jun 03 23:44:16 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:44:17', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (38, 'chachae', '更新部门', 36, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=13, parentId=4, deptName=大数据技术, orderNum=2, createTime=null, updateTime=Wed Jun 03 23:44:22 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:44:22', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (39, 'chachae', '更新部门', 45, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=13, parentId=4, deptName=大数据技术, orderNum=3, createTime=null, updateTime=Wed Jun 03 23:44:29 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:44:30', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (40, 'chachae', '更新部门', 36, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=14, parentId=4, deptName=网络空间安全, orderNum=3, createTime=null, updateTime=Wed Jun 03 23:44:36 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:44:36', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (41, 'chachae', '更新部门', 31, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=13, parentId=4, deptName=大数据技术, orderNum=4, createTime=null, updateTime=Wed Jun 03 23:44:42 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:44:42', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (42, 'chachae', '更新部门', 42, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=14, parentId=4, deptName=网络空间安全, orderNum=4, createTime=null, updateTime=Wed Jun 03 23:44:53 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:44:53', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (43, 'chachae', '更新部门', 32, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=14, parentId=4, deptName=网络空间安全, orderNum=5, createTime=null, updateTime=Wed Jun 03 23:44:56 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:44:57', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (44, 'chachae', '新增部门', 46, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=15, parentId=9, deptName=16计科1班, orderNum=0, createTime=Wed Jun 03 23:45:20 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:45:21', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (45, 'chachae', '新增部门', 40, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=16, parentId=9, deptName=16计科2班, orderNum=0, createTime=Wed Jun 03 23:45:26 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:45:26', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (46, 'chachae', '新增部门', 41, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=17, parentId=9, deptName=17计科1班, orderNum=0, createTime=Wed Jun 03 23:45:30 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:45:31', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (47, 'chachae', '新增部门', 31, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=18, parentId=9, deptName=17计科2班, orderNum=3, createTime=Wed Jun 03 23:45:43 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:45:43', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (48, 'chachae', '更新部门', 38, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=16, parentId=9, deptName=16计科2班, orderNum=1, createTime=null, updateTime=Wed Jun 03 23:45:51 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:45:52', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (49, 'chachae', '更新部门', 41, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=17, parentId=9, deptName=17计科1班, orderNum=2, createTime=null, updateTime=Wed Jun 03 23:46:00 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:46:00', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (50, 'chachae', '新增部门', 38, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=19, parentId=9, deptName=17计科3班, orderNum=4, createTime=Wed Jun 03 23:46:19 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:46:20', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (51, 'chachae', '新增部门', 41, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=20, parentId=9, deptName=17计科4班, orderNum=5, createTime=Wed Jun 03 23:46:26 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:46:26', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (52, 'chachae', '新增部门', 56, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=21, parentId=9, deptName=17计科5班, orderNum=6, createTime=Wed Jun 03 23:46:40 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:46:41', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (53, 'chachae', '新增部门', 39, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=22, parentId=10, deptName=16网工1班, orderNum=0, createTime=Wed Jun 03 23:47:14 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:47:15', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (54, 'chachae', '新增部门', 42, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=23, parentId=10, deptName=16网工2班, orderNum=0, createTime=Wed Jun 03 23:47:22 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:47:22', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (55, 'chachae', '新增部门', 46, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=24, parentId=10, deptName=17网工1班, orderNum=0, createTime=Wed Jun 03 23:47:28 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:47:29', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (56, 'chachae', '新增部门', 44, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=25, parentId=10, deptName=17网工2班, orderNum=0, createTime=Wed Jun 03 23:47:34 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:47:34', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (57, 'chachae', '新增部门', 35, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=26, parentId=11, deptName=17数媒1班, orderNum=0, createTime=Wed Jun 03 23:47:45 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:47:46', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (58, 'chachae', '新增部门', 36, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=27, parentId=11, deptName=17数媒2班, orderNum=0, createTime=Wed Jun 03 23:47:52 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:47:53', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (59, 'chachae', '更新部门', 31, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=25, parentId=10, deptName=17网工2班, orderNum=3, createTime=null, updateTime=Wed Jun 03 23:48:05 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:48:06', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (60, 'chachae', '更新部门', 42, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=24, parentId=10, deptName=17网工1班, orderNum=2, createTime=null, updateTime=Wed Jun 03 23:48:15 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-03 23:48:15', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (61, 'chachae', '新增角色', 128, 'com.oes.server.system.controller.RoleController.addRole()', ' role: \"Role(roleId=6, roleName=教师, remark=, createTime=Thu Jun 04 00:23:00 CST 2020, updateTime=null, menuIds=195,196)\"', '192.168.1.4', '2020-06-04 00:23:01', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (62, 'chachae', '更新角色', 99, 'com.oes.server.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=6, roleName=null, remark=教师，负责出题、出卷、复查试卷等工作。, createTime=null, updateTime=Thu Jun 04 00:23:26 CST 2020, menuIds=195,196)\"', '192.168.1.4', '2020-06-04 00:23:26', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (63, 'chachae', '更新角色', 120, 'com.oes.server.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=6, roleName=null, remark=负责出题、出卷、复查试卷等工作。, createTime=null, updateTime=Thu Jun 04 00:23:32 CST 2020, menuIds=195,196)\"', '192.168.1.4', '2020-06-04 00:23:33', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (64, 'chachae', '新增角色', 48, 'com.oes.server.system.controller.RoleController.addRole()', ' role: \"Role(roleId=7, roleName=学生, remark=, createTime=Thu Jun 04 00:24:21 CST 2020, updateTime=null, menuIds=195)\"', '192.168.1.4', '2020-06-04 00:24:21', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (65, 'chachae', '更新角色', 124, 'com.oes.server.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=7, roleName=null, remark=可进行考试、成绩查看和分析, createTime=null, updateTime=Thu Jun 04 00:24:47 CST 2020, menuIds=195)\"', '192.168.1.4', '2020-06-04 00:24:48', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (66, 'chachae', '更新角色', 196, 'com.oes.server.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=6, roleName=null, remark=负责出题、出卷、复查试卷等工作, createTime=null, updateTime=Thu Jun 04 00:24:50 CST 2020, menuIds=195,196)\"', '192.168.1.4', '2020-06-04 00:24:51', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (67, 'chachae', '新增部门', 131, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=28, parentId=0, deptName=财经学院, orderNum=5, createTime=Thu Jun 04 12:04:08 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:04:09', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (68, 'chachae', '新增部门', 32, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=29, parentId=0, deptName=工商管理学院, orderNum=6, createTime=Thu Jun 04 12:04:18 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:04:19', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (69, 'chachae', '新增部门', 46, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=30, parentId=0, deptName=电气与电子工程学院, orderNum=7, createTime=Thu Jun 04 12:04:27 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:04:28', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (70, 'chachae', '新增部门', 38, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=31, parentId=0, deptName=建筑工程学院, orderNum=8, createTime=Thu Jun 04 12:04:40 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:04:41', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (71, 'chachae', '新增部门', 32, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=32, parentId=0, deptName=国际教育学院, orderNum=9, createTime=Thu Jun 04 12:05:02 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:05:02', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (72, 'chachae', '新增部门', 27, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=33, parentId=0, deptName=艺术学院, orderNum=10, createTime=Thu Jun 04 12:05:25 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:05:26', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (73, 'chachae', '新增部门', 38, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=34, parentId=0, deptName=继续教育学院, orderNum=12, createTime=Thu Jun 04 12:05:33 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:05:33', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (74, 'chachae', '更新部门', 63, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=15, parentId=9, deptName=16计科1班, orderNum=1, createTime=null, updateTime=Thu Jun 04 12:07:21 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:07:21', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (75, 'chachae', '新增部门', 32, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=35, parentId=9, deptName=18计1班, orderNum=9, createTime=Thu Jun 04 12:11:48 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:11:49', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (76, 'chachae', '更新部门', 61, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=35, parentId=9, deptName=18计科1班, orderNum=9, createTime=null, updateTime=Thu Jun 04 12:11:58 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:11:58', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (77, 'chachae', '新增部门', 39, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=36, parentId=9, deptName=18计科2班, orderNum=10, createTime=Thu Jun 04 12:12:05 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:12:05', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (78, 'chachae', '更新部门', 43, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=35, parentId=9, deptName=18计科1班, orderNum=8, createTime=null, updateTime=Thu Jun 04 12:12:16 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:12:16', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (79, 'chachae', '更新部门', 36, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=36, parentId=9, deptName=18计科2班, orderNum=9, createTime=null, updateTime=Thu Jun 04 12:12:21 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:12:22', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (80, 'chachae', '新增部门', 40, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=37, parentId=9, deptName=19计科1班, orderNum=10, createTime=Thu Jun 04 12:12:47 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:12:48', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (81, 'chachae', '新增部门', 37, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=38, parentId=13, deptName=18大数据班, orderNum=1, createTime=Thu Jun 04 12:13:10 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:13:11', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (82, 'chachae', '新增部门', 48, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=39, parentId=10, deptName=18网工1班, orderNum=3, createTime=Thu Jun 04 12:13:27 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:13:27', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (83, 'chachae', '更新部门', 49, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=22, parentId=10, deptName=16网工1班, orderNum=1, createTime=null, updateTime=Thu Jun 04 12:13:39 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:13:40', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (84, 'chachae', '更新部门', 41, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=23, parentId=10, deptName=16网工2班, orderNum=2, createTime=null, updateTime=Thu Jun 04 12:13:43 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:13:44', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (85, 'chachae', '更新部门', 91, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=24, parentId=10, deptName=17网工1班, orderNum=3, createTime=null, updateTime=Thu Jun 04 12:13:53 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:13:54', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (86, 'chachae', '更新部门', 39, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=25, parentId=10, deptName=17网工2班, orderNum=4, createTime=null, updateTime=Thu Jun 04 12:14:01 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:14:02', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (87, 'chachae', '更新部门', 77, 'com.oes.server.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=39, parentId=10, deptName=18网工1班, orderNum=5, createTime=null, updateTime=Thu Jun 04 12:14:09 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:14:09', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (88, 'chachae', '新增部门', 63, 'com.oes.server.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=40, parentId=12, deptName=17软工1班, orderNum=1, createTime=Thu Jun 04 12:14:25 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-04 12:14:25', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (89, 'chachae', '新增菜单', 84, 'com.oes.server.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=200, parentId=195, menuName=课程管理, path=/examination/course, component=oes/examination/course/Index, perms=course:view, icon=, type=0, orderNum=0, createTime=Thu Jun 04 15:00:41 CST 2020, updateTime=null)\"', '127.0.0.1', '2020-06-04 15:00:42', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (90, 'chachae', '更新角色', 321, 'com.oes.server.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=null, remark=管理员, createTime=null, updateTime=Thu Jun 04 15:00:50 CST 2020, menuIds=1,3,135,130,11,12,13,4,131,14,15,16,5,132,17,18,19,6,133,20,21,22,163,164,165,166,167,2,180,10,136,24,150,151,152,195,200,196,199,198,197,173,174,175,176,177,178,179,156,157,159,158,160,154,155,168,169,170,171,172)\"', '127.0.0.1', '2020-06-04 15:00:51', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (91, 'chachae', '更新角色', 487, 'com.oes.server.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=null, remark=管理员, createTime=null, updateTime=Thu Jun 04 16:33:57 CST 2020, menuIds=1,3,13,12,11,130,135,4,16,15,14,131,5,19,18,17,132,6,133,20,21,22,163,164,166,167,165,2,180,10,136,24,150,151,152,195,196,197,198,199,200,201,202,203,173,174,175,176,177,178,179,156,157,159,158,160,154,155,168,169,170,171,172)\"', '127.0.0.1', '2020-06-04 16:33:58', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (92, 'chachae', '更新用户', 190, 'com.oes.server.system.controller.UserController.updateUser()', ' user: \"SystemUser(userId=1, username=null, fullName=张三, password=null, deptId=2, email=chachae@qq.com, mobile=13670459539, status=1, createTime=null, updateTime=Thu Jun 04 21:43:50 CST 2020, lastLoginTime=null, sex=0, avatar=c7c4ee7be3eb4e73a19887dc713505145.jpg, description=我是作者。, deptName=教务处, createTimeFrom=null, createTimeTo=null, roleId=1, roleName=管理员, deptIds=1,2,3,4,5,6,28,29,30,31,32,33,34)\"', '127.0.0.1', '2020-06-04 21:43:50', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (93, 'chachae', '更新用户', 313, 'com.oes.server.system.controller.UserController.updateUser()', ' user: \"SystemUser(userId=2, username=null, fullName=李四, password=null, deptId=4, email=scott@hotmail.com, mobile=17720888888, status=1, createTime=null, updateTime=Thu Jun 04 22:51:50 CST 2020, lastLoginTime=null, sex=2, avatar=BiazfanxmamNRoxxVxka.png, description=null, deptName=计算机科学与工程学院, createTimeFrom=null, createTimeTo=null, roleId=6,3, roleName=教师,系统监控员,注册用户, deptIds=)\"', '127.0.0.1', '2020-06-04 22:51:51', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (94, 'chachae', '更新角色', 1000, 'com.oes.server.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=null, remark=管理员, createTime=null, updateTime=Fri Jun 05 22:40:07 CST 2020, menuIds=1,3,13,12,11,130,135,4,16,15,14,131,5,19,18,17,132,6,133,20,21,22,163,164,166,167,165,2,180,10,136,24,150,151,152,195,196,197,198,199,200,201,202,203,173,174,175,176,177,178,179,154,155,168,169,170,171,172)\"', '127.0.0.1', '2020-06-05 22:40:08', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (95, 'chachae', '更新角色', 405, 'com.oes.server.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=2, roleName=null, remark=可查看，新增，导出, createTime=null, updateTime=Fri Jun 05 22:40:23 CST 2020, menuIds=1,3,4,5,6,2,10,150,173,174,175,176,177,178,179,154,155,168,169,170,171,172)\"', '127.0.0.1', '2020-06-05 22:40:24', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (96, 'chachae', '删除角色', 211, 'com.oes.server.system.controller.MenuController.deleteMenus()', ' menuIds: \"156,157,159,158,160\"', '127.0.0.1', '2020-06-05 22:40:39', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (97, 'chachae', '新增菜单', 142, 'com.oes.server.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=205, parentId=204, menuName=新增试题, path=null, component=null, perms=question:add, icon=null, type=1, orderNum=null, createTime=Fri Jun 05 23:53:16 CST 2020, updateTime=null)\"', '192.168.1.4', '2020-06-05 23:53:17', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (98, 'chachae', '新增菜单', 198, 'com.oes.server.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=206, parentId=204, menuName=修改试题, path=null, component=null, perms=question:update, icon=null, type=1, orderNum=null, createTime=Fri Jun 05 23:53:29 CST 2020, updateTime=null)\"', '192.168.1.4', '2020-06-05 23:53:30', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (99, 'chachae', '新增菜单', 165, 'com.oes.server.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=207, parentId=204, menuName=删除试题, path=null, component=null, perms=question:delete, icon=null, type=1, orderNum=null, createTime=Fri Jun 05 23:53:42 CST 2020, updateTime=null)\"', '192.168.1.4', '2020-06-05 23:53:43', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (100, 'chachae', '更新角色', 812, 'com.oes.server.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=null, remark=管理员, createTime=null, updateTime=Fri Jun 05 23:54:05 CST 2020, menuIds=1,3,135,11,12,13,130,4,131,14,15,16,5,17,18,19,132,6,133,20,21,22,163,167,164,165,166,2,180,10,136,24,150,151,152,195,196,199,198,197,200,201,203,202,204,207,206,205,173,174,175,176,177,178,179,154,155,168,169,170,171,172)\"', '192.168.1.4', '2020-06-05 23:54:06', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (101, 'chachae', '更新角色', 296, 'com.oes.server.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=6, roleName=null, remark=负责出题、出卷、复查试卷等工作, createTime=null, updateTime=Sat Jun 06 16:52:34 CST 2020, menuIds=195,196,200,204,207,206,205)\"', '127.0.0.1', '2020-06-06 16:52:35', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (102, 'chachae', '新增菜单', 52, 'com.oes.server.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=208, parentId=195, menuName=试卷管理, path=/examination/paper, component=oes/examination/paper/Index, perms=paper:view, icon=, type=0, orderNum=0, createTime=Sun Jun 07 22:29:31 CST 2020, updateTime=null)\"', '127.0.0.1', '2020-06-07 22:29:31', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (103, 'chachae', '新增菜单', 36, 'com.oes.server.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=209, parentId=208, menuName=增加, path=null, component=null, perms=paper:add, icon=null, type=1, orderNum=null, createTime=Sun Jun 07 22:30:40 CST 2020, updateTime=null)\"', '127.0.0.1', '2020-06-07 22:30:41', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (104, 'chachae', '新增菜单', 26, 'com.oes.server.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=210, parentId=208, menuName=删除, path=null, component=null, perms=paper:delete, icon=null, type=1, orderNum=null, createTime=Sun Jun 07 22:31:06 CST 2020, updateTime=null)\"', '127.0.0.1', '2020-06-07 22:31:06', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (105, 'chachae', '更新角色', 290, 'com.oes.server.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=null, remark=管理员, createTime=null, updateTime=Sun Jun 07 22:32:56 CST 2020, menuIds=1,3,13,12,135,11,130,4,14,15,16,131,5,19,17,18,132,6,20,21,22,133,163,164,165,166,167,2,180,10,136,24,150,151,152,173,174,175,176,177,178,179,195,200,203,202,201,208,210,209,196,199,198,197,204,205,206,207,154,155,168,169,170,171,172)\"', '127.0.0.1', '2020-06-07 22:32:57', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (106, 'chachae', '更新角色', 151, 'com.oes.server.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=6, roleName=null, remark=负责出题、出卷、复查试卷等工作, createTime=null, updateTime=Mon Jun 08 16:28:44 CST 2020, menuIds=195,200,208,210,209,196,204,205,206,207)\"', '127.0.0.1', '2020-06-08 16:28:45', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (107, 'chachae', '更新角色', 61, 'com.oes.server.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=6, roleName=null, remark=负责出题、出卷、复查试卷等工作, createTime=null, updateTime=Mon Jun 08 18:36:09 CST 2020, menuIds=195,200,208,210,209,196,204,205,207)\"', '127.0.0.1', '2020-06-08 18:36:10', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (108, 'chachae', '更新角色', 35, 'com.oes.server.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=210, parentId=208, menuName=删除试卷, path=null, component=null, perms=paper:delete, icon=null, type=1, orderNum=null, createTime=null, updateTime=Mon Jun 08 18:45:37 CST 2020)\"', '127.0.0.1', '2020-06-08 18:45:38', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (109, 'chachae', '更新角色', 29, 'com.oes.server.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=209, parentId=208, menuName=增加试卷, path=null, component=null, perms=paper:add, icon=null, type=1, orderNum=null, createTime=null, updateTime=Mon Jun 08 18:45:43 CST 2020)\"', '127.0.0.1', '2020-06-08 18:45:44', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (110, 'chachae', '新增菜单', 24, 'com.oes.server.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=211, parentId=208, menuName=修改试卷, path=null, component=null, perms=paper:update, icon=null, type=1, orderNum=null, createTime=Mon Jun 08 18:46:01 CST 2020, updateTime=null)\"', '127.0.0.1', '2020-06-08 18:46:01', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (111, 'chachae', '更新角色', 241, 'com.oes.server.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=null, remark=管理员, createTime=null, updateTime=Mon Jun 08 18:46:21 CST 2020, menuIds=1,3,11,12,13,130,135,4,14,15,16,131,5,17,18,19,132,6,20,21,22,133,163,164,165,166,167,2,180,10,136,24,150,151,152,173,174,175,176,177,178,179,195,204,207,205,206,200,203,202,201,196,199,198,197,208,210,209,211,154,155,168,169,170,171,172)\"', '127.0.0.1', '2020-06-08 18:46:21', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (112, '201704044', '新增用户', 280, 'com.yunke.core.module.system.controller.UserController.addUser()', ' user: \"SystemUser(userId=20, username=201704043, fullName=null, password=$2a$10$m.QF1aubeZyAzSTkT6w1reuXO4gLJGNaX/9AbQyMbo4UNc0DE/0kO, deptId=5, email=, mobile=, status=1, createTime=Sat Jun 13 18:32:30 CST 2020, updateTime=null, lastLoginTime=null, sex=0, avatar=default.jpg, description=null, eduPassword=null, noteId=3, noteName=null, deptName=null, createTimeFrom=null, createTimeTo=null, roleId=7, roleName=null, deptIds=4)\"', '127.0.0.1', '2020-06-13 18:32:31', '');
INSERT INTO `t_log` VALUES (113, '201704044', '删除用户', 113, 'com.yunke.core.module.system.controller.UserController.deleteUsers()', ' userIds: \"4,5,6,7,20\"', '127.0.0.1', '2020-06-13 18:32:46', '');
INSERT INTO `t_log` VALUES (114, '201704044', '删除部门', 107, 'com.yunke.core.module.system.controller.DeptController.deleteDepts()', ' deptIds: \"5,6,28,29,30,31,32,33,34\"', '127.0.0.1', '2020-06-13 18:33:21', '');
INSERT INTO `t_log` VALUES (115, '201704044', '删除部门', 99, 'com.yunke.core.module.system.controller.DeptController.deleteDepts()', ' deptIds: \"26,27,40,41,42,43,44,45,46,38,14\"', '127.0.0.1', '2020-06-13 18:34:12', '');
INSERT INTO `t_log` VALUES (116, '201704044', '删除部门', 180, 'com.yunke.core.module.system.controller.DeptController.deleteDepts()', ' deptIds: \"15,16,17,18,19,20,21,35,36,37\"', '127.0.0.1', '2020-06-13 18:34:27', '');
INSERT INTO `t_log` VALUES (117, '201704044', '删除部门', 198, 'com.yunke.core.module.system.controller.DeptController.deleteDepts()', ' deptIds: \"9,11,12,13\"', '127.0.0.1', '2020-06-13 18:34:34', '');
INSERT INTO `t_log` VALUES (118, '201704044', '更新部门', 53, 'com.yunke.core.module.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=1, parentId=0, deptName=云课教师组, orderNum=1, createTime=null, updateTime=Sat Jun 13 18:34:51 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-13 18:34:51', '');
INSERT INTO `t_log` VALUES (119, '201704044', '更新部门', 150, 'com.yunke.core.module.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=4, parentId=0, deptName=云课学生组, orderNum=2, createTime=null, updateTime=Sat Jun 13 18:35:01 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-13 18:35:01', '');
INSERT INTO `t_log` VALUES (120, '201704044', '删除部门', 134, 'com.yunke.core.module.system.controller.DeptController.deleteDepts()', ' deptIds: \"23,24,25,39\"', '127.0.0.1', '2020-06-13 18:35:09', '');
INSERT INTO `t_log` VALUES (121, '201704044', '更新部门', 29, 'com.yunke.core.module.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=10, parentId=4, deptName=前端开发, orderNum=1, createTime=null, updateTime=Sat Jun 13 18:35:23 CST 2020, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-13 18:35:23', '');
INSERT INTO `t_log` VALUES (122, '201704044', '删除部门', 108, 'com.yunke.core.module.system.controller.DeptController.deleteDepts()', ' deptIds: \"22\"', '127.0.0.1', '2020-06-13 18:35:29', '');
INSERT INTO `t_log` VALUES (123, '201704044', '新增部门', 86, 'com.yunke.core.module.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=47, parentId=4, deptName=后端开发, orderNum=2, createTime=Sat Jun 13 18:35:39 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-13 18:35:40', '');
INSERT INTO `t_log` VALUES (124, '201704044', '新增部门', 34, 'com.yunke.core.module.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=48, parentId=4, deptName=网络安全, orderNum=3, createTime=Sat Jun 13 18:35:46 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-13 18:35:46', '');
INSERT INTO `t_log` VALUES (125, '201704044', '新增部门', 29, 'com.yunke.core.module.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=49, parentId=4, deptName=UI设计, orderNum=4, createTime=Sat Jun 13 18:36:01 CST 2020, updateTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2020-06-13 18:36:01', '');
INSERT INTO `t_log` VALUES (126, '201704044', '更新用户', 60, 'com.yunke.core.module.system.controller.UserController.updateUser()', ' user: \"SystemUser(userId=1, username=null, fullName=陈岳欣, password=null, deptId=47, email=chachae@qq.com, mobile=13670459539, status=1, createTime=null, updateTime=Sat Jun 13 18:37:12 CST 2020, lastLoginTime=null, sex=0, avatar=c7c4ee7be3eb4e73a19887dc713505145.jpg, description=我是作者。, eduPassword=null, noteId=2, noteName=毕业生, deptName=教务处, createTimeFrom=null, createTimeTo=null, roleId=1, roleName=管理员, deptIds=1,2,3,4)\"', '192.168.1.4', '2020-06-13 18:37:13', '');
INSERT INTO `t_log` VALUES (127, '201704044', '更新用户', 58, 'com.yunke.core.module.system.controller.UserController.updateUser()', ' user: \"SystemUser(userId=2, username=null, fullName=李四, password=null, deptId=10, email=scott@hotmail.com, mobile=17720888888, status=1, createTime=null, updateTime=Sat Jun 13 18:37:19 CST 2020, lastLoginTime=null, sex=2, avatar=BiazfanxmamNRoxxVxka.png, description=null, eduPassword=null, noteId=1, noteName=教师, deptName=云课学生组, createTimeFrom=null, createTimeTo=null, roleId=6,3, roleName=教师,系统监控员, deptIds=)\"', '192.168.1.4', '2020-06-13 18:37:19', '');
INSERT INTO `t_log` VALUES (128, '201704044', '更新用户', 70, 'com.yunke.core.module.system.controller.UserController.updateUser()', ' user: \"SystemUser(userId=3, username=null, fullName=王五, password=null, deptId=49, email=Jane@hotmail.com, mobile=13679554032, status=1, createTime=null, updateTime=Sat Jun 13 18:37:30 CST 2020, lastLoginTime=null, sex=1, avatar=2dd7a2d09fa94bf8b5c52e5318868b4d9.jpg, description=null, eduPassword=null, noteId=3, noteName=null, deptName=云课学生组, createTimeFrom=null, createTimeTo=null, roleId=6,3, roleName=教师,系统监控员, deptIds=1)\"', '192.168.1.4', '2020-06-13 18:37:31', '');
INSERT INTO `t_log` VALUES (129, '201704044', '更新用户', 227, 'com.yunke.core.module.system.controller.UserController.updateUser()', ' user: \"SystemUser(userId=1, username=null, fullName=陈岳欣, password=null, deptId=47, email=chachae@qq.com, mobile=13670459539, status=1, createTime=null, updateTime=Sun Jun 14 00:00:46 CST 2020, lastLoginTime=null, sex=0, avatar=c7c4ee7be3eb4e73a19887dc713505145.jpg, description=我是作者。, eduPassword=null, noteId=1, noteName=毕业生, deptName=后端开发, createTimeFrom=null, createTimeTo=null, roleId=1, roleName=管理员, deptIds=1,2,3,4)\"', '127.0.0.1', '2020-06-14 00:00:47', '');
INSERT INTO `t_log` VALUES (130, '201704044', '更新用户', 283, 'com.yunke.core.module.system.controller.UserController.updateUser()', ' user: \"SystemUser(userId=2, username=null, fullName=李四, password=null, deptId=47, email=scott@hotmail.com, mobile=17720888888, status=1, createTime=null, updateTime=Sun Jun 14 12:15:39 CST 2020, lastLoginTime=null, sex=0, avatar=BiazfanxmamNRoxxVxka.png, description=null, eduPassword=null, noteId=1, noteName=教师, deptName=云课教师组, createTimeFrom=null, createTimeTo=null, roleId=6,3, roleName=教师,系统监控员, deptIds=4,10,47,48,49)\"', '127.0.0.1', '2020-06-14 12:15:40', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (131, '201704044', '更新用户', 117, 'com.yunke.core.module.system.controller.UserController.updateUser()', ' user: \"SystemUser(userId=1, username=null, fullName=陈岳欣, password=null, deptId=1, email=chachae@qq.com, mobile=13670459539, status=1, createTime=null, updateTime=Sun Jun 14 12:16:14 CST 2020, lastLoginTime=null, sex=0, avatar=c7c4ee7be3eb4e73a19887dc713505145.jpg, description=我是作者。, eduPassword=null, noteId=1, noteName=教师, deptName=云课教师组, createTimeFrom=null, createTimeTo=null, roleId=1, roleName=管理员, deptIds=1,2,3,4,10,47,48,49)\"', '127.0.0.1', '2020-06-14 12:16:15', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (132, '201704044', '新增菜单', 140, 'com.yunke.core.module.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=212, parentId=0, menuName=工作室管理, path=/studio, component=Layout, perms=, icon=el-icon-notebook-2, type=0, orderNum=3, createTime=Sun Jun 14 13:39:03 CST 2020, updateTime=null)\"', '127.0.0.1', '2020-06-14 13:39:04', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (133, '201704044', '更新角色', 300, 'com.yunke.core.module.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=null, remark=管理员, createTime=null, updateTime=Sun Jun 14 13:39:40 CST 2020, menuIds=1,3,135,130,13,12,11,4,131,16,15,14,5,132,19,18,17,6,21,133,22,20,163,165,164,166,167,2,180,10,136,24,150,152,151,212,154,155,168,169,170,171,172)\"', '127.0.0.1', '2020-06-14 13:39:40', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (134, '201704044', '新增菜单', 81, 'com.yunke.core.module.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=213, parentId=212, menuName=证书管理, path=/studio/certificate, component=yunke/studio/role/Index, perms=, icon=, type=0, orderNum=1, createTime=Sun Jun 14 13:41:27 CST 2020, updateTime=null)\"', '127.0.0.1', '2020-06-14 13:41:27', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (135, '201704044', '更新角色', 372, 'com.yunke.core.module.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=null, remark=管理员, createTime=null, updateTime=Sun Jun 14 13:41:37 CST 2020, menuIds=1,3,135,130,13,12,11,4,131,16,15,14,5,19,132,18,17,6,133,22,21,20,163,164,165,166,167,2,180,10,136,24,150,152,151,212,213,154,155,168,169,170,171,172)\"', '127.0.0.1', '2020-06-14 13:41:38', '内网IP|0|0|内网IP|内网IP');
COMMIT;

-- ----------------------------
-- Table structure for t_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_login_log`;
CREATE TABLE `t_login_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `login_time` datetime NOT NULL COMMENT '登录时间',
  `location` varchar(50) DEFAULT NULL COMMENT '登录地点',
  `ip` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `system` varchar(50) DEFAULT NULL COMMENT '操作系统',
  `browser` varchar(50) DEFAULT NULL COMMENT '浏览器',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `t_login_log_login_time` (`login_time`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COMMENT='登录日志表';

-- ----------------------------
-- Records of t_login_log
-- ----------------------------
BEGIN;
INSERT INTO `t_login_log` VALUES (1, 'chachae', '2020-05-27 18:42:49', '内网IP|0|0|内网IP|内网IP', '127.0.0.1', 'OSX', 'MSEdge');
INSERT INTO `t_login_log` VALUES (74, '陈岳欣', '2020-06-14 14:44:10', '内网IP|0|0|内网IP|内网IP', '127.0.0.1', 'Mac OS X 10.15.4', 'Microsoft Edge 83');
INSERT INTO `t_login_log` VALUES (72, '201704044', '2020-06-14 13:49:54', '内网IP|0|0|内网IP|内网IP', '127.0.0.1', 'Mac OS X 10.15.4', 'Microsoft Edge 83');
INSERT INTO `t_login_log` VALUES (73, '201704044', '2020-06-14 14:23:43', '内网IP|0|0|内网IP|内网IP', '127.0.0.1', 'Mac OS X 10.15.4', 'Microsoft Edge 83');
COMMIT;

-- ----------------------------
-- Table structure for t_match
-- ----------------------------
DROP TABLE IF EXISTS `t_match`;
CREATE TABLE `t_match` (
  `match_uuid` varchar(32) NOT NULL COMMENT '比赛ID',
  `title` varchar(100) NOT NULL COMMENT '比赛名称',
  `level` int DEFAULT NULL COMMENT '比赛等级(0:国家级/1:省级/2:校级)',
  `type` int DEFAULT NULL COMMENT '比赛类型（0:个人/1:团队）',
  `application_form` varchar(255) DEFAULT NULL COMMENT '申请书',
  `time` varchar(32) DEFAULT NULL COMMENT '比赛时间',
  `cost` double(10,2) DEFAULT NULL COMMENT '费用',
  `invoice` varchar(255) DEFAULT NULL COMMENT '发票',
  `certificate` varchar(255) DEFAULT NULL COMMENT '证书',
  `state` int DEFAULT NULL COMMENT '状态(1:进行中/2:已结束)',
  `reimbursement` int DEFAULT NULL COMMENT '是否已报销（0否/1是）',
  PRIMARY KEY (`match_uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='比赛_任务表';

-- ----------------------------
-- Records of t_match
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_match_member_awards
-- ----------------------------
DROP TABLE IF EXISTS `t_match_member_awards`;
CREATE TABLE `t_match_member_awards` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` int DEFAULT NULL COMMENT '用户id(为空是团队赛，团队奖)',
  `rank_code` int DEFAULT NULL COMMENT '奖项(1：一等，2：二等，3：三等，4：特等，5：优胜，6、无)',
  `task_uuid` varchar(32) NOT NULL COMMENT '关联 比赛UUID',
  `certificate` varchar(255) DEFAULT NULL COMMENT '奖状',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `userUUID` (`user_id`) USING BTREE,
  KEY `taskUUID` (`task_uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='比赛人员奖项表';

-- ----------------------------
-- Records of t_match_member_awards
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_members
-- ----------------------------
DROP TABLE IF EXISTS `t_members`;
CREATE TABLE `t_members` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '标识字段',
  `user_id` int NOT NULL COMMENT '成员ID',
  `task_uuid` varchar(32) NOT NULL COMMENT '任务ID',
  `state` int NOT NULL COMMENT '1为负责人，2为成员，3为指导老师',
  PRIMARY KEY (`id`,`user_id`,`task_uuid`) USING BTREE,
  KEY `taskUUID` (`task_uuid`) USING BTREE,
  KEY `id` (`id`) USING BTREE,
  KEY `task_user` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='任务成员的中间表';

-- ----------------------------
-- Records of t_members
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单/按钮ID',
  `parent_id` bigint NOT NULL COMMENT '上级菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单/按钮名称',
  `path` varchar(255) DEFAULT NULL COMMENT '对应路由path',
  `component` varchar(255) DEFAULT NULL COMMENT '对应路由组件component',
  `perms` varchar(50) DEFAULT NULL COMMENT '权限表达式',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `type` char(2) NOT NULL COMMENT '类型 0菜单 1按钮',
  `order_num` double(20,0) DEFAULT NULL COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`) USING BTREE,
  KEY `t_menu_parent_id` (`parent_id`) USING BTREE,
  KEY `t_menu_menu_id` (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of t_menu
-- ----------------------------
BEGIN;
INSERT INTO `t_menu` VALUES (1, 0, '系统管理', '/system', 'Layout', NULL, 'el-icon-set-up', '0', 1, '2017-12-27 16:39:07', '2019-07-20 16:19:04');
INSERT INTO `t_menu` VALUES (2, 0, '系统监控', '/monitor', 'Layout', NULL, 'el-icon-data-line', '0', 2, '2017-12-27 16:45:51', '2019-01-23 06:27:12');
INSERT INTO `t_menu` VALUES (3, 1, '用户管理', '/system/user', 'yunke/system/user/Index', 'user:view', '', '0', 1, '2017-12-27 16:47:13', '2019-01-22 06:45:55');
INSERT INTO `t_menu` VALUES (4, 1, '角色管理', '/system/role', 'yunke/system/role/Index', 'role:view', '', '0', 2, '2017-12-27 16:48:09', '2018-04-25 09:01:12');
INSERT INTO `t_menu` VALUES (5, 1, '菜单管理', '/system/menu', 'yunke/system/menu/Index', 'menu:view', '', '0', 3, '2017-12-27 16:48:57', '2018-04-25 09:01:30');
INSERT INTO `t_menu` VALUES (6, 1, '部门管理', '/system/dept', 'yunke/system/dept/Index', 'dept:view', '', '0', 4, '2017-12-27 16:57:33', '2018-04-25 09:01:40');
INSERT INTO `t_menu` VALUES (10, 2, '系统日志', '/monitor/systemlog', 'yunke/monitor/systemlog/Index', 'log:view', '', '0', 2, '2017-12-27 17:00:50', '2020-04-13 11:38:04');
INSERT INTO `t_menu` VALUES (11, 3, '新增用户', '', '', 'user:add', NULL, '1', NULL, '2017-12-27 17:02:58', NULL);
INSERT INTO `t_menu` VALUES (12, 3, '修改用户', '', '', 'user:update', NULL, '1', NULL, '2017-12-27 17:04:07', NULL);
INSERT INTO `t_menu` VALUES (13, 3, '删除用户', '', '', 'user:delete', NULL, '1', NULL, '2017-12-27 17:04:58', NULL);
INSERT INTO `t_menu` VALUES (14, 4, '新增角色', '', '', 'role:add', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `t_menu` VALUES (15, 4, '修改角色', '', '', 'role:update', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `t_menu` VALUES (16, 4, '删除角色', '', '', 'role:delete', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `t_menu` VALUES (17, 5, '新增菜单', '', '', 'menu:add', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `t_menu` VALUES (18, 5, '修改菜单', '', '', 'menu:update', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `t_menu` VALUES (19, 5, '删除菜单', '', '', 'menu:delete', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `t_menu` VALUES (20, 6, '新增部门', '', '', 'dept:add', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `t_menu` VALUES (21, 6, '修改部门', '', '', 'dept:update', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `t_menu` VALUES (22, 6, '删除部门', '', '', 'dept:delete', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `t_menu` VALUES (24, 10, '删除日志', '', '', 'log:delete', NULL, '1', NULL, '2017-12-27 17:11:45', NULL);
INSERT INTO `t_menu` VALUES (130, 3, '导出Excel', NULL, NULL, 'user:export', NULL, '1', NULL, '2019-01-23 06:35:16', NULL);
INSERT INTO `t_menu` VALUES (131, 4, '导出Excel', NULL, NULL, 'role:export', NULL, '1', NULL, '2019-01-23 06:35:36', NULL);
INSERT INTO `t_menu` VALUES (132, 5, '导出Excel', NULL, NULL, 'menu:export', NULL, '1', NULL, '2019-01-23 06:36:05', NULL);
INSERT INTO `t_menu` VALUES (133, 6, '导出Excel', NULL, NULL, 'dept:export', NULL, '1', NULL, '2019-01-23 06:36:25', NULL);
INSERT INTO `t_menu` VALUES (135, 3, '密码重置', NULL, NULL, 'user:reset', NULL, '1', NULL, '2019-01-23 06:37:00', NULL);
INSERT INTO `t_menu` VALUES (136, 10, '导出Excel', NULL, NULL, 'log:export', NULL, '1', NULL, '2019-01-23 06:37:27', NULL);
INSERT INTO `t_menu` VALUES (150, 2, '登录日志', '/monitor/loginlog', 'yunke/monitor/loginlog/Index', 'monitor:loginlog', '', '0', 3, '2019-07-22 13:41:17', '2020-04-13 11:38:08');
INSERT INTO `t_menu` VALUES (151, 150, '删除日志', NULL, NULL, 'loginlog:delete', NULL, '1', NULL, '2019-07-22 13:43:04', NULL);
INSERT INTO `t_menu` VALUES (152, 150, '导出Excel', NULL, NULL, 'loginlog:export', NULL, '1', NULL, '2019-07-22 13:43:30', NULL);
INSERT INTO `t_menu` VALUES (154, 0, '其他模块', '/others', 'Layout', '', 'el-icon-shopping-bag-1', '0', 6, '2019-07-25 10:16:16', '2020-04-14 18:38:20');
INSERT INTO `t_menu` VALUES (155, 154, '导入导出', '/others/eximport', 'yunke/others/eximport/Index', 'others:eximport', '', '0', 1, '2019-07-25 10:19:31', NULL);
INSERT INTO `t_menu` VALUES (163, 1, '客户端管理', '/client', 'yunke/system/client/Index', 'client:view', '', '0', 5, '2019-09-26 22:58:09', NULL);
INSERT INTO `t_menu` VALUES (164, 163, '新增', NULL, NULL, 'client:add', NULL, '1', NULL, '2019-09-26 22:58:21', NULL);
INSERT INTO `t_menu` VALUES (165, 163, '修改', NULL, NULL, 'client:update', NULL, '1', NULL, '2019-09-26 22:58:43', NULL);
INSERT INTO `t_menu` VALUES (166, 163, '删除', NULL, NULL, 'client:delete', NULL, '1', NULL, '2019-09-26 22:58:55', NULL);
INSERT INTO `t_menu` VALUES (167, 163, '解密', NULL, NULL, 'client:decrypt', NULL, '1', NULL, '2019-09-26 22:59:08', NULL);
INSERT INTO `t_menu` VALUES (168, 0, '静态组件', '/components', 'Layout', '', 'el-icon-present', '0', 7, '2019-12-02 16:41:28', '2020-04-14 18:38:23');
INSERT INTO `t_menu` VALUES (169, 168, '二级菜单', '/two', 'demos/two/Index', '', '', '0', 1, '2019-12-02 16:41:51', NULL);
INSERT INTO `t_menu` VALUES (170, 169, '三级菜单', '/three', 'demos/two/three/Index', '', '', '0', 1, '2019-12-02 16:42:09', NULL);
INSERT INTO `t_menu` VALUES (171, 168, 'MarkDown', '/components/markdown', 'demos/markdown', '', '', '0', 2, '2019-12-02 16:42:34', NULL);
INSERT INTO `t_menu` VALUES (172, 168, '富文本编辑器', '/components/tinymce', 'demos/tinymce', '', '', '0', 3, '2019-12-02 16:42:50', NULL);
INSERT INTO `t_menu` VALUES (180, 2, '监控面板', '/monitor/dashboard', 'yunke/monitor/dashboard/Index', 'monitor:dashboard', '', '0', 1, '2020-04-13 09:44:09', '2020-04-13 11:38:00');
INSERT INTO `t_menu` VALUES (181, 154, '个人博客', '/others/blog', 'yunke/others/blog/Index', '', '', '0', 2, '2020-04-13 16:11:48', '2020-04-13 16:12:26');
INSERT INTO `t_menu` VALUES (182, 154, '数据权限', '/others/datapermission', 'yunke/others/datapermission/Index', 'others:datapermission', '', '0', 3, '2020-04-14 14:51:35', '2020-04-14 15:37:19');
INSERT INTO `t_menu` VALUES (183, 0, '任务调度', '/job', 'Layout', '', 'el-icon-alarm-clock', '0', 5, '2020-04-14 18:39:35', '2020-04-14 18:39:53');
INSERT INTO `t_menu` VALUES (212, 0, '工作室管理', '/studio', 'Layout', '', 'el-icon-notebook-2', '0', 3, '2020-06-14 13:39:04', NULL);
INSERT INTO `t_menu` VALUES (213, 212, '证书管理', '/studio/certificate', 'yunke/studio/certificate/Index', '', '', '0', 1, '2020-06-14 13:41:27', NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_note
-- ----------------------------
DROP TABLE IF EXISTS `t_note`;
CREATE TABLE `t_note` (
  `note_id` int NOT NULL AUTO_INCREMENT COMMENT '备注主键',
  `note_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注类型',
  PRIMARY KEY (`note_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='备注表';

-- ----------------------------
-- Records of t_note
-- ----------------------------
BEGIN;
INSERT INTO `t_note` VALUES (1, '教师');
INSERT INTO `t_note` VALUES (2, '毕业生');
INSERT INTO `t_note` VALUES (3, '在校生');
INSERT INTO `t_note` VALUES (4, '考核中');
COMMIT;

-- ----------------------------
-- Table structure for t_orientation
-- ----------------------------
DROP TABLE IF EXISTS `t_orientation`;
CREATE TABLE `t_orientation` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '方向ID',
  `orientation` varchar(50) NOT NULL COMMENT '方向名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='方向表';

-- ----------------------------
-- Records of t_orientation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_postgraduate
-- ----------------------------
DROP TABLE IF EXISTS `t_postgraduate`;
CREATE TABLE `t_postgraduate` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '考研ID',
  `user_id` int NOT NULL COMMENT '考研人ID',
  `time` varchar(32) DEFAULT NULL COMMENT '报考时间',
  `school` varchar(75) DEFAULT NULL COMMENT '报考学校',
  `orientation` varchar(75) DEFAULT NULL COMMENT '报考方向',
  `exam` varchar(10) DEFAULT NULL COMMENT '统考成绩',
  `state` int NOT NULL COMMENT '状态（0:正在考取/1:已完成）',
  `success` int DEFAULT NULL COMMENT '通过状态(0:失败/1:成功)',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `考研人` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='考研人员表';

-- ----------------------------
-- Records of t_postgraduate
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
BEGIN;
INSERT INTO `t_role` VALUES (1, '管理员', '管理员', '2017-12-27 16:23:11', '2020-06-14 13:41:38');
INSERT INTO `t_role` VALUES (2, '注册用户', '可查看，新增，导出', '2019-01-04 14:11:28', '2020-06-05 22:40:23');
INSERT INTO `t_role` VALUES (3, '系统监控员', '负责系统监控模块', '2019-09-01 10:30:25', '2019-09-01 10:30:37');
INSERT INTO `t_role` VALUES (6, '教师', '负责出题、出卷、复查试卷等工作', '2020-06-04 00:23:01', '2020-06-08 18:36:10');
INSERT INTO `t_role` VALUES (7, '学生', '可进行考试、成绩查看和分析', '2020-06-04 00:24:21', '2020-06-04 00:24:48');
COMMIT;

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `role_id` bigint NOT NULL,
  `menu_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `t_role_menu` VALUES (1, 1);
INSERT INTO `t_role_menu` VALUES (1, 2);
INSERT INTO `t_role_menu` VALUES (1, 3);
INSERT INTO `t_role_menu` VALUES (1, 4);
INSERT INTO `t_role_menu` VALUES (1, 5);
INSERT INTO `t_role_menu` VALUES (1, 6);
INSERT INTO `t_role_menu` VALUES (1, 10);
INSERT INTO `t_role_menu` VALUES (1, 11);
INSERT INTO `t_role_menu` VALUES (1, 12);
INSERT INTO `t_role_menu` VALUES (1, 13);
INSERT INTO `t_role_menu` VALUES (1, 14);
INSERT INTO `t_role_menu` VALUES (1, 15);
INSERT INTO `t_role_menu` VALUES (1, 16);
INSERT INTO `t_role_menu` VALUES (1, 17);
INSERT INTO `t_role_menu` VALUES (1, 18);
INSERT INTO `t_role_menu` VALUES (1, 19);
INSERT INTO `t_role_menu` VALUES (1, 20);
INSERT INTO `t_role_menu` VALUES (1, 21);
INSERT INTO `t_role_menu` VALUES (1, 22);
INSERT INTO `t_role_menu` VALUES (1, 24);
INSERT INTO `t_role_menu` VALUES (1, 130);
INSERT INTO `t_role_menu` VALUES (1, 131);
INSERT INTO `t_role_menu` VALUES (1, 132);
INSERT INTO `t_role_menu` VALUES (1, 133);
INSERT INTO `t_role_menu` VALUES (1, 135);
INSERT INTO `t_role_menu` VALUES (1, 136);
INSERT INTO `t_role_menu` VALUES (1, 150);
INSERT INTO `t_role_menu` VALUES (1, 151);
INSERT INTO `t_role_menu` VALUES (1, 152);
INSERT INTO `t_role_menu` VALUES (1, 154);
INSERT INTO `t_role_menu` VALUES (1, 155);
INSERT INTO `t_role_menu` VALUES (1, 163);
INSERT INTO `t_role_menu` VALUES (1, 164);
INSERT INTO `t_role_menu` VALUES (1, 165);
INSERT INTO `t_role_menu` VALUES (1, 166);
INSERT INTO `t_role_menu` VALUES (1, 167);
INSERT INTO `t_role_menu` VALUES (1, 168);
INSERT INTO `t_role_menu` VALUES (1, 169);
INSERT INTO `t_role_menu` VALUES (1, 170);
INSERT INTO `t_role_menu` VALUES (1, 171);
INSERT INTO `t_role_menu` VALUES (1, 172);
INSERT INTO `t_role_menu` VALUES (1, 180);
INSERT INTO `t_role_menu` VALUES (1, 212);
INSERT INTO `t_role_menu` VALUES (1, 213);
INSERT INTO `t_role_menu` VALUES (2, 1);
INSERT INTO `t_role_menu` VALUES (2, 2);
INSERT INTO `t_role_menu` VALUES (2, 3);
INSERT INTO `t_role_menu` VALUES (2, 4);
INSERT INTO `t_role_menu` VALUES (2, 5);
INSERT INTO `t_role_menu` VALUES (2, 6);
INSERT INTO `t_role_menu` VALUES (2, 10);
INSERT INTO `t_role_menu` VALUES (2, 150);
INSERT INTO `t_role_menu` VALUES (2, 154);
INSERT INTO `t_role_menu` VALUES (2, 155);
INSERT INTO `t_role_menu` VALUES (2, 168);
INSERT INTO `t_role_menu` VALUES (2, 169);
INSERT INTO `t_role_menu` VALUES (2, 170);
INSERT INTO `t_role_menu` VALUES (2, 171);
INSERT INTO `t_role_menu` VALUES (2, 172);
INSERT INTO `t_role_menu` VALUES (2, 173);
INSERT INTO `t_role_menu` VALUES (2, 174);
INSERT INTO `t_role_menu` VALUES (2, 175);
INSERT INTO `t_role_menu` VALUES (2, 176);
INSERT INTO `t_role_menu` VALUES (2, 177);
INSERT INTO `t_role_menu` VALUES (2, 178);
INSERT INTO `t_role_menu` VALUES (2, 179);
INSERT INTO `t_role_menu` VALUES (3, 2);
INSERT INTO `t_role_menu` VALUES (3, 10);
INSERT INTO `t_role_menu` VALUES (3, 24);
INSERT INTO `t_role_menu` VALUES (3, 136);
INSERT INTO `t_role_menu` VALUES (3, 148);
INSERT INTO `t_role_menu` VALUES (3, 149);
INSERT INTO `t_role_menu` VALUES (3, 150);
INSERT INTO `t_role_menu` VALUES (3, 151);
INSERT INTO `t_role_menu` VALUES (3, 152);
INSERT INTO `t_role_menu` VALUES (3, 153);
INSERT INTO `t_role_menu` VALUES (6, 195);
INSERT INTO `t_role_menu` VALUES (6, 196);
INSERT INTO `t_role_menu` VALUES (6, 200);
INSERT INTO `t_role_menu` VALUES (6, 204);
INSERT INTO `t_role_menu` VALUES (6, 205);
INSERT INTO `t_role_menu` VALUES (6, 207);
INSERT INTO `t_role_menu` VALUES (6, 208);
INSERT INTO `t_role_menu` VALUES (6, 209);
INSERT INTO `t_role_menu` VALUES (6, 210);
INSERT INTO `t_role_menu` VALUES (7, 195);
COMMIT;

-- ----------------------------
-- Table structure for t_school_assets
-- ----------------------------
DROP TABLE IF EXISTS `t_school_assets`;
CREATE TABLE `t_school_assets` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '学校资产ID',
  `inclusion_date` varchar(32) DEFAULT NULL COMMENT '收录日期',
  `assets_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资产名称',
  `assets_num` varchar(10) DEFAULT NULL COMMENT '资产编号',
  `price` double(10,2) DEFAULT NULL COMMENT '资产价格',
  `scrap_date` varchar(32) DEFAULT NULL COMMENT '报废日期',
  `scrap_detail` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报废信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='学校资产表\r\n';

-- ----------------------------
-- Records of t_school_assets
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_school_assets_repair
-- ----------------------------
DROP TABLE IF EXISTS `t_school_assets_repair`;
CREATE TABLE `t_school_assets_repair` (
  `id` int NOT NULL COMMENT '维修信息ID',
  `repair_date` varchar(32) DEFAULT NULL COMMENT '维修日期',
  `assets_name` int DEFAULT NULL COMMENT '学校资产ID',
  `repair_price` double(10,2) DEFAULT NULL COMMENT '维修价格',
  `repair_invoice` varchar(32) DEFAULT NULL COMMENT '发票',
  `repair_prover_user_info_uuid` int NOT NULL COMMENT '维修证明人【userIndoUuid】',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `repair_school_prover` (`repair_prover_user_info_uuid`) USING BTREE,
  KEY `学校资产ID` (`assets_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='维修信息表\r\n';

-- ----------------------------
-- Records of t_school_assets_repair
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_thesis
-- ----------------------------
DROP TABLE IF EXISTS `t_thesis`;
CREATE TABLE `t_thesis` (
  `thesis_uuid` varchar(32) NOT NULL COMMENT '论文ID',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `introduction` varchar(255) DEFAULT NULL COMMENT '摘要',
  `paper_type` int DEFAULT NULL COMMENT '论文类型(1核心/ 2普通）',
  `time` varchar(32) DEFAULT NULL COMMENT '更新时间',
  `url` varchar(255) DEFAULT NULL COMMENT '论文下载',
  `cost` double(10,2) DEFAULT NULL COMMENT '花费',
  `state` int DEFAULT NULL COMMENT '状态（1进行中/2已完成）',
  `invoice` varchar(255) DEFAULT NULL COMMENT '发票',
  `reimbursement` int DEFAULT NULL COMMENT '是否已报销（0否/1是）',
  PRIMARY KEY (`thesis_uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='论文_任务表';

-- ----------------------------
-- Records of t_thesis
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `full_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `status` char(1) NOT NULL COMMENT '状态 1老师/2毕业生/3在校4考核/0禁用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最近访问时间',
  `ssex` char(1) DEFAULT NULL COMMENT '性别 0男 1女 2保密',
  `is_tab` char(1) DEFAULT NULL COMMENT '是否开启tab，0关闭 1开启',
  `theme` varchar(10) DEFAULT NULL COMMENT '主题',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `note_id` bigint DEFAULT NULL COMMENT '用户备注（1：教师，2：毕业，3：在校，4：考核）',
  PRIMARY KEY (`user_id`) USING BTREE,
  KEY `t_user_username` (`username`) USING BTREE,
  KEY `t_user_mobile` (`mobile`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (1, '201704044', '陈岳欣', '$2a$10$eG9uK3ujGwqWZCIXRCZgPOv0Rmh9KiDxNCf/rzz.MvATAh9uhZZ6e', 1, 'chachae@qq.com', '13670459539', '1', '2019-06-14 20:39:22', '2020-06-14 12:16:15', '2020-06-14 14:44:10', '0', '1', 'white', 'c7c4ee7be3eb4e73a19887dc713505145.jpg', '我是作者。', 1);
INSERT INTO `t_user` VALUES (2, 'scott', '李四', '$2a$10$VSZ8g8rmw5pvZzYn0cAGBOKvJBrmh5FNexpgzVkxSrVUrC2ViO99S', 47, 'scott@hotmail.com', '17720888888', '1', '2019-07-20 19:00:32', '2020-06-14 12:15:40', '2020-06-07 21:12:52', '0', NULL, NULL, 'BiazfanxmamNRoxxVxka.png', NULL, 1);
INSERT INTO `t_user` VALUES (3, 'Jane', '王五', '$2a$10$/YDnX1OPBCRcXHQx.aR3tu8f3JfM2ABdWv1fE.PZ32ijAbvqnPz5a', 1, 'Jane@hotmail.com', '13679554032', '1', '2019-09-01 10:31:21', '2020-06-13 18:37:31', '2020-06-08 16:29:00', '1', NULL, NULL, '2dd7a2d09fa94bf8b5c52e5318868b4d9.jpg', NULL, 3);
COMMIT;

-- ----------------------------
-- Table structure for t_user_connection
-- ----------------------------
DROP TABLE IF EXISTS `t_user_connection`;
CREATE TABLE `t_user_connection` (
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'cloudx系统用户名',
  `provider_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '第三方平台名称',
  `provider_user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '第三方平台账户ID',
  `provider_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '第三方平台用户名',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '第三方平台昵称',
  `image_url` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '第三方平台头像',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_name`,`provider_name`,`provider_user_id`) USING BTREE,
  UNIQUE KEY `UserConnectionRank` (`user_name`,`provider_name`,`provider_user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统用户社交账户关联表';

-- ----------------------------
-- Records of t_user_connection
-- ----------------------------
BEGIN;
INSERT INTO `t_user_connection` VALUES ('chachae', 'GITEE', '2172962', 'chachae', 'chachae', 'https://portrait.gitee.com/uploads/avatars/user/724/2172962_chachae_1578967843.png', NULL, '');
INSERT INTO `t_user_connection` VALUES ('chachae', 'GITHUB', '25251252', 'chachae', 'yue.xin', 'https://avatars3.githubusercontent.com/u/25251252?v=4', NULL, '一天撸码25小时。');
COMMIT;

-- ----------------------------
-- Table structure for t_user_data_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_user_data_permission`;
CREATE TABLE `t_user_data_permission` (
  `user_id` bigint NOT NULL,
  `dept_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`dept_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户数据权限关联表';

-- ----------------------------
-- Records of t_user_data_permission
-- ----------------------------
BEGIN;
INSERT INTO `t_user_data_permission` VALUES (1, 1);
INSERT INTO `t_user_data_permission` VALUES (1, 2);
INSERT INTO `t_user_data_permission` VALUES (1, 3);
INSERT INTO `t_user_data_permission` VALUES (1, 4);
INSERT INTO `t_user_data_permission` VALUES (1, 10);
INSERT INTO `t_user_data_permission` VALUES (1, 47);
INSERT INTO `t_user_data_permission` VALUES (1, 48);
INSERT INTO `t_user_data_permission` VALUES (1, 49);
INSERT INTO `t_user_data_permission` VALUES (2, 4);
INSERT INTO `t_user_data_permission` VALUES (2, 10);
INSERT INTO `t_user_data_permission` VALUES (2, 47);
INSERT INTO `t_user_data_permission` VALUES (2, 48);
INSERT INTO `t_user_data_permission` VALUES (2, 49);
INSERT INTO `t_user_data_permission` VALUES (3, 1);
INSERT INTO `t_user_data_permission` VALUES (15, 1);
INSERT INTO `t_user_data_permission` VALUES (15, 2);
INSERT INTO `t_user_data_permission` VALUES (16, 4);
COMMIT;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`role_id`,`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
BEGIN;
INSERT INTO `t_user_role` VALUES (1, 1);
INSERT INTO `t_user_role` VALUES (2, 3);
INSERT INTO `t_user_role` VALUES (3, 3);
INSERT INTO `t_user_role` VALUES (2, 6);
INSERT INTO `t_user_role` VALUES (3, 6);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
