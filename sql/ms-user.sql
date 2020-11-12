/*
SQLyog Ultimate v12.08 (32 bit)
MySQL - 8.0.15 : Database - ms_user
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ms_user` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE `ms_user`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `money` decimal(10,0) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `reg_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`money`,`role`,`reg_time`) values (1,'itmuch','111','955','user','2019-10-16 06:53:36');

/*Table structure for table `user_account_event_log` */

DROP TABLE IF EXISTS `user_account_event_log`;

CREATE TABLE `user_account_event_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `money` decimal(10,0) DEFAULT NULL,
  `event` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_account_event_log` */

insert  into `user_account_event_log`(`id`,`user_id`,`money`,`event`,`create_time`,`description`) values (1,1,'5','购买课程','2020-11-04 18:14:05','1购买了id为1的课程'),(2,1,'5','购买课程','2020-11-06 14:26:05','1购买了id为1的课程'),(3,1,'5','购买课程','2020-11-06 14:27:01','1购买了id为1的课程'),(4,1,'5','购买课程','2020-11-06 14:47:02','1购买了id为1的课程'),(5,1,'5','购买课程','2020-11-08 23:27:21','1购买了id为1的课程'),(6,1,'5','购买课程','2020-11-08 23:29:04','1购买了id为1的课程'),(7,1,'5','购买课程','2020-11-08 23:32:55','1购买了id为1的课程'),(8,1,'5','购买课程','2020-11-09 17:41:51','1购买了id为1的课程'),(9,1,'5','购买课程','2020-11-09 17:45:33','1购买了id为1的课程'),(10,1,'5','购买课程','2020-11-11 12:58:45','1购买了id为1的课程');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
