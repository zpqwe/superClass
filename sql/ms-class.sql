/*
SQLyog Ultimate v12.08 (32 bit)
MySQL - 8.0.15 : Database - ms_class
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ms_class` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE `ms_class`;

/*Table structure for table `lesson` */

DROP TABLE IF EXISTS `lesson`;

CREATE TABLE `lesson` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `cover` varchar(45) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '课程表',
  `video_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `lesson` */

insert  into `lesson`(`id`,`title`,`cover`,`price`,`description`,`create_time`,`video_url`) values (1,'springcloud视频教程','xxx','5','springcloud','2019-10-16 07:45:57','http://next.ke.qq.com');

/*Table structure for table `lesson_user` */

DROP TABLE IF EXISTS `lesson_user`;

CREATE TABLE `lesson_user` (
  `lesson_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `lesson_user` */

insert  into `lesson_user`(`lesson_id`,`user_id`) values (1,1);

/*Table structure for table `mysql_test_view` */

DROP TABLE IF EXISTS `mysql_test_view`;

/*!50001 DROP VIEW IF EXISTS `mysql_test_view` */;
/*!50001 DROP TABLE IF EXISTS `mysql_test_view` */;

/*!50001 CREATE TABLE  `mysql_test_view`(
 `id` int(11) ,
 `title` varchar(45) ,
 `cover` varchar(45) ,
 `price` decimal(10,0) ,
 `description` varchar(500) ,
 `create_time` datetime ,
 `video_url` varchar(255) 
)*/;

/*View structure for view mysql_test_view */

/*!50001 DROP TABLE IF EXISTS `mysql_test_view` */;
/*!50001 DROP VIEW IF EXISTS `mysql_test_view` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mysql_test_view` AS select `lesson`.`id` AS `id`,`lesson`.`title` AS `title`,`lesson`.`cover` AS `cover`,`lesson`.`price` AS `price`,`lesson`.`description` AS `description`,`lesson`.`create_time` AS `create_time`,`lesson`.`video_url` AS `video_url` from `lesson` WITH CASCADED CHECK OPTION */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
