/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.5.37-log : Database - adminlte
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`adminlte` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `adminlte`;

/*Table structure for table `oauth_access_token` */

DROP TABLE IF EXISTS `oauth_access_token`;

CREATE TABLE `oauth_access_token` (
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `token_id` varchar(255) DEFAULT NULL,
  `token_expired_seconds` int(11) DEFAULT '-1',
  `authentication_id` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `token_type` varchar(255) DEFAULT NULL,
  `refresh_token_expired_seconds` int(11) DEFAULT '-1',
  `refresh_token` varchar(255) DEFAULT NULL,
  UNIQUE KEY `token_id` (`token_id`),
  UNIQUE KEY `refresh_token` (`refresh_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_access_token` */

insert  into `oauth_access_token`(`create_time`,`token_id`,`token_expired_seconds`,`authentication_id`,`username`,`client_id`,`token_type`,`refresh_token_expired_seconds`,`refresh_token`) values ('2018-08-08 17:50:53','84d86c9933a762078e1bc8c8b531df04',43200,'8ec304a56dc8b1d8cee6e20aabfa21fb','test','test','Bearer',2592000,NULL),('2018-08-08 19:16:41','3283b06c6ea098bafed0ff5f6589797d',43200,'6694afcfca6edb8072d087d024612055','test','test','Bearer',2592000,'966e1688dcc10e8c326093d9a682d916'),('2018-10-30 21:13:24','043dd65f19ea2164229347532a9e72a5',43200,'adcc280eb037ca8c2e897df011fa32eb','test','test','Bearer',2592000,'9d21898784d4936f6a1883d0611b09d5'),('2018-10-30 21:33:23','d8bf8ba0a4362ff1068e46cf606ae5b4',43200,'bcfb493d93f0f3820c94b4022dc8285b','test','mobile','Bearer',2592000,'5965b1b9ec8874095b2258ce15923f3f');

/*Table structure for table `oauth_client_details` */

DROP TABLE IF EXISTS `oauth_client_details`;

CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `client_name` varchar(255) DEFAULT NULL,
  `client_uri` varchar(255) DEFAULT NULL,
  `client_icon_uri` varchar(255) DEFAULT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `grant_types` varchar(255) DEFAULT NULL,
  `redirect_uri` varchar(255) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT '-1',
  `refresh_token_validity` int(11) DEFAULT '-1',
  `description` varchar(4096) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `archived` tinyint(1) DEFAULT '0',
  `trusted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_client_details` */

insert  into `oauth_client_details`(`client_id`,`client_secret`,`client_name`,`client_uri`,`client_icon_uri`,`resource_ids`,`scope`,`grant_types`,`redirect_uri`,`roles`,`access_token_validity`,`refresh_token_validity`,`description`,`create_time`,`archived`,`trusted`) values ('mobile','mobile','Mobile Client','http://andaily.com','http://andaily.com/favicon.ico','mobile-resource','read write','password,refresh_token','http://localhost:7777/spring-oauth-client/authorization_code_callback','22',-1,-1,NULL,'2018-08-06 15:31:34',0,0),('test','test','Test Client','http://andaily.com','http://andaily.com/favicon.ico','os-resource','read write','authorization_code,password,refresh_token,client_credentials','http://localhost:8080/lab-xiaoyue/goods/zcc.do','22',-1,-1,NULL,'2018-08-06 15:31:34',0,0);

/*Table structure for table `oauth_code` */

DROP TABLE IF EXISTS `oauth_code`;

CREATE TABLE `oauth_code` (
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `code` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_code` */

insert  into `oauth_code`(`create_time`,`code`,`username`,`client_id`) values ('2018-08-06 17:09:37','9d12550786f14a35ee066b5cb0f90677','admin','test');

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` varchar(50) NOT NULL,
  `guid` varchar(255) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `archived` tinyint(1) DEFAULT '0',
  `version` int(11) DEFAULT '0',
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `roles` */

insert  into `roles`(`id`,`guid`,`create_time`,`archived`,`version`,`role_name`) values ('21','bf04a75c-994a-11e8-ae07-00163e0c4062','2018-08-06 15:31:34',0,0,'Admin'),('22','bf04a8f7-994a-11e8-ae07-00163e0c4062','2018-08-06 15:31:34',0,0,'User'),('386f599e51224b3d8b8cb26f33bf33a8','6727ae15-5420-4ee0-b8c9-c9e8781585dc','2018-11-06 16:27:33',0,0,'zcc');

/*Table structure for table `roles_permissions` */

DROP TABLE IF EXISTS `roles_permissions`;

CREATE TABLE `roles_permissions` (
  `id` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `roles_id` varchar(50) NOT NULL,
  `permission` varchar(255) NOT NULL,
  KEY `roles_id_index` (`roles_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `roles_permissions` */

insert  into `roles_permissions`(`id`,`create_time`,`roles_id`,`permission`) values ('1','2018-08-06 15:31:34','21','user:create'),('2','2018-08-06 15:31:34','21','user:edit'),('3','2018-08-06 15:31:34','21','user:list'),('4','2018-08-06 15:31:34','21','user:delete'),('4a61e22df0d14064a86555a642a1acc6','2018-08-06 15:31:34','22','user:list'),('7ce4f7d8ab9a4318ae1721c6f7742c6c','2018-11-07 14:42:02','386f599e51224b3d8b8cb26f33bf33a8','4a61e22df0d14064a86555a642a1acc6'),('15c3ee5108124ca298b660ad070362a4','2018-11-07 14:42:02','386f599e51224b3d8b8cb26f33bf33a8','6cb971d404354e82ad5b751a8fdfe65a'),('70a1f53ebab740c1b539195dd44b852e','2018-11-07 14:42:02','386f599e51224b3d8b8cb26f33bf33a8','df707283d4204feda4ff2413d4a84223'),('ab0ec0c335da425a89843eb6b2ad87f7','2018-11-07 14:42:02','386f599e51224b3d8b8cb26f33bf33a8','fdbaf68def3b471493aa413e63a7001f'),('1358d68d36174503bdda9cc7768610b9','2018-11-07 14:42:02','386f599e51224b3d8b8cb26f33bf33a8','2cd59ee87c264ab3a1784027868b73f8');

/*Table structure for table `urls` */

DROP TABLE IF EXISTS `urls`;

CREATE TABLE `urls` (
  `id` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `url` varchar(255) NOT NULL,
  `number` varchar(10) DEFAULT NULL,
  KEY `id_index` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `urls` */

insert  into `urls`(`id`,`create_time`,`url`,`number`) values ('4a61e22df0d14064a86555a642a1acc6','2018-11-06 18:16:12','/zcc/test','01'),('656e9ccc38204665b8d0abc0c43a50f8','2018-11-07 10:47:02','/ll/test','02'),('b3fc6b4e1386469788b8b4b6ca963d49','2018-11-07 10:47:16','/green/test','03'),('6cb971d404354e82ad5b751a8fdfe65a','2018-11-07 10:47:25','/joy/test','01'),('df707283d4204feda4ff2413d4a84223','2018-11-07 11:11:10','/aaaaaaaaa/bbbbbbbbbbbb/ccccccccc/dddddddddddddddd','01'),('fdbaf68def3b471493aa413e63a7001f','2018-11-07 11:11:30','123','01'),('dfcfa8f66a104ec18fcdfd652b783dc4','2018-11-07 11:11:39','001','03'),('d6e668d9ca4443a896c0e7e10bb627b6','2018-11-07 11:11:44','002','05'),('e568d9b54d254a07aa4dbf3faad472d0','2018-11-07 11:11:50','003','02'),('98298b50f0844a14815453ea1ed36063','2018-11-07 11:12:23','004','02'),('2cd59ee87c264ab3a1784027868b73f8','2018-11-07 11:12:27','005','01'),('825c9cb7f0d24215a76694d4d64b56f3','2018-11-07 11:12:33','007','03'),('114c197f12aa46b3ac9d0379147309f7','2018-11-07 11:12:40','006','02'),('160eec4cc63945a0942e2fb7b3700cfb','2018-11-07 11:12:47','008','02'),('b1eb5479659e4f288e5ebf547bba262a','2018-11-07 11:12:51','009','02');

/*Table structure for table `user_roles` */

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `id` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `users_id` varchar(50) NOT NULL,
  `roles_id` varchar(50) NOT NULL,
  KEY `users_id_index` (`users_id`),
  KEY `roles_id_index` (`roles_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_roles` */

insert  into `user_roles`(`id`,`create_time`,`users_id`,`roles_id`) values ('1','2018-08-06 15:31:34','21','21'),('2','2018-08-06 15:31:34','22','22'),('ecaf56622f0a44ada60751b0362038e4','2018-11-07 11:10:48','2ceb4cf0d3e24bedb1bbdf673a74eb53','386f599e51224b3d8b8cb26f33bf33a8');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` varchar(50) NOT NULL,
  `guid` varchar(255) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `archived` tinyint(1) DEFAULT '0',
  `version` int(11) DEFAULT '0',
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `default_user` tinyint(1) DEFAULT '0',
  `last_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  UNIQUE KEY `username` (`username`),
  KEY `username_index` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`id`,`guid`,`create_time`,`archived`,`version`,`password`,`username`,`default_user`,`last_login_time`) values ('21','befc4a33-994a-11e8-ae07-00163e0c4062','2018-08-06 15:31:34',0,0,'21232f297a57a5a743894a0e4a801fc3','admin',1,NULL),('22','befc4ca9-994a-11e8-ae07-00163e0c4062','2018-08-06 15:31:34',0,0,'098f6bcd4621d373cade4e832627b4f6','test',0,NULL),('2ceb4cf0d3e24bedb1bbdf673a74eb53','9119f3c4-8a7b-41f1-a48b-262deafbdd04','2018-11-07 11:07:54',0,0,'fe785e7fab65b451c81e4b876c8216d8','zcc',0,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
