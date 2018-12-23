/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-09-27 16:02:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_attach
-- ----------------------------
DROP TABLE IF EXISTS `t_attach`;
CREATE TABLE `t_attach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(100) NOT NULL,
  `ftype` varchar(50) DEFAULT NULL,
  `fkey` varchar(100) NOT NULL,
  `author_id` int(10) NOT NULL,
  `created` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_comments
-- ----------------------------
DROP TABLE IF EXISTS `t_comments`;
CREATE TABLE `t_comments` (
  `coid` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) NOT NULL,
  `created` timestamp not null default current_timestamp comment '创建时间',
  `author` varchar(200) NOT NULL,
  `author_id` int(10) DEFAULT NULL,
  `owner_id` int(10) DEFAULT NULL,
  `mail` varchar(100) NOT NULL,
  `url` varchar(200) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `agent` varchar(200) DEFAULT NULL,
  `content` text NOT NULL,
  `type` varchar(16) DEFAULT NULL,
  `STATUS` varchar(16) DEFAULT NULL,
  `parent` int(10) DEFAULT NULL,
  PRIMARY KEY (`coid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_contents
-- ----------------------------
DROP TABLE IF EXISTS `t_contents`;
CREATE TABLE `t_contents` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `thumb_img` varchar(255) DEFAULT NULL,
  `created` timestamp not null default current_timestamp comment '创建时间',
  `modified` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
  `content` text,
  `author_id` int(10) NOT NULL,
  `type` varchar(16) NOT NULL,
  `status` varchar(16) NOT NULL,
  `fmt_type` varchar(16) DEFAULT NULL,
  `tags` varchar(200) DEFAULT NULL,
  `categories` varchar(200) DEFAULT NULL,
  `hits` int(10) DEFAULT NULL,
  `comments_num` int(1) DEFAULT NULL,
  `allow_comment` int(1) DEFAULT NULL,
  `allow_ping` int(1) DEFAULT NULL,
  `allow_feed` int(1) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cid`),
  UNIQUE KEY `cid` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_logs
-- ----------------------------
DROP TABLE IF EXISTS `t_logs`;
CREATE TABLE `t_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(100) NOT NULL,
  `DATA` varchar(2000) DEFAULT NULL,
  `author_id` int(10) NOT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `created` timestamp not null default current_timestamp comment '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_metas
-- ----------------------------
DROP TABLE IF EXISTS `t_metas`;
CREATE TABLE `t_metas` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(200) NOT NULL,
  `type` varchar(32) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `sort` int(4) DEFAULT NULL,
  `parent` int(10) DEFAULT NULL,
  PRIMARY KEY (`mid`),
  UNIQUE KEY `mid` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_options
-- ----------------------------
DROP TABLE IF EXISTS `t_options`;
CREATE TABLE `t_options` (
  `name` varchar(100) NOT NULL,
  `value` text,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_relationships
-- ----------------------------
DROP TABLE IF EXISTS `t_relationships`;
CREATE TABLE `t_relationships` (
  `cid` int(10) NOT NULL,
  `mid` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
  `uid` int(11) NOT NULL,
  `username` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `home_url` varchar(255) DEFAULT NULL,
  `screen_name` varchar(100) DEFAULT NULL,
  `created` timestamp not null default current_timestamp comment '创建时间',
  `activated` timestamp not null default current_timestamp comment '创建时间',
  `logged` int(10) DEFAULT NULL,
  `group_name` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
