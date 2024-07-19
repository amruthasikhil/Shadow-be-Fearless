/*
SQLyog Community Edition- MySQL GUI v8.03 
MySQL - 5.6.12-log : Database - shadow_be_fearless
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`shadow_be_fearless` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `shadow_be_fearless`;

/*Table structure for table `chat` */

DROP TABLE IF EXISTS `chat`;

CREATE TABLE `chat` (
  `Chat_id` int(11) NOT NULL AUTO_INCREMENT,
  `From_id` int(11) DEFAULT NULL,
  `To_id` int(11) DEFAULT NULL,
  `Message` varchar(50) DEFAULT NULL,
  `Date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Chat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

/*Data for the table `chat` */

insert  into `chat`(`Chat_id`,`From_id`,`To_id`,`Message`,`Date`) values (1,0,0,'hloo','2021-04-17'),(2,0,0,'hloo','2021-04-17'),(3,0,0,'hloo','2021-04-17'),(4,0,0,'hlo','2021-04-17'),(5,0,0,'hlo','2021-04-17'),(6,0,0,'hlo','2021-04-17'),(7,0,1,'hai','2021-04-17'),(8,0,1,'kkoooii','2021-04-17'),(9,3,1,'hi','2021-04-17'),(10,3,1,'hi','2021-04-17'),(11,3,1,'hi','2021-04-17'),(12,3,1,'kk','2021-04-17'),(13,1,3,'haaa','2021-04-17'),(14,3,1,'fg','2021-04-17'),(15,3,1,'fg','2021-04-17'),(16,3,1,'fg','2021-04-17'),(17,3,1,'jj','2021-04-17'),(18,1,3,'jhgfdfghj','2021-04-17'),(19,3,24,'haiii','2021-04-25'),(20,24,2,'hello','2021-04-25'),(21,3,24,'hellooo','2021-04-25'),(22,24,3,'hello','2021-04-25'),(23,24,3,'good morning','2021-04-25'),(24,24,3,'heyy','2021-04-25');

/*Table structure for table `complaint` */

DROP TABLE IF EXISTS `complaint`;

CREATE TABLE `complaint` (
  `Complaint_id` int(11) NOT NULL AUTO_INCREMENT,
  `User_id` int(11) DEFAULT NULL,
  `Complaint` varchar(50) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  `Reply` varchar(50) DEFAULT NULL,
  `Date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Complaint_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `complaint` */

insert  into `complaint`(`Complaint_id`,`User_id`,`Complaint`,`Status`,`Reply`,`Date`) values (1,3,'Name','ok','sure','2021-04-23'),(2,3,'helpppp','ok','okk','2021-04-25'),(3,24,'hellloo','pending','pending','2021-04-25');

/*Table structure for table `dangerous_spot` */

DROP TABLE IF EXISTS `dangerous_spot`;

CREATE TABLE `dangerous_spot` (
  `Dangerous_id` int(11) NOT NULL AUTO_INCREMENT,
  `Location` varchar(50) DEFAULT NULL,
  `Latitude` varchar(50) DEFAULT NULL,
  `Longitude` varchar(50) DEFAULT NULL,
  `Added_Dangerous_Spot` varchar(50) DEFAULT NULL,
  `Login_id` int(11) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Dangerous_id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=latin1;

/*Data for the table `dangerous_spot` */

insert  into `dangerous_spot`(`Dangerous_id`,`Location`,`Latitude`,`Longitude`,`Added_Dangerous_Spot`,`Login_id`,`status`) values (11,'mlp','45','56','user',9,'Approved'),(22,'ktkl','56','90','user',2,NULL),(33,'pkd','23','89','user',3,NULL),(34,'ekm','90','99','police',5,NULL),(59,'fgjk','56','12','police',8,NULL),(87,'kjhg','67','89','police',7,NULL),(88,'gh','25','57',NULL,3,NULL),(89,'Calicut','346','344',NULL,3,NULL),(90,'Calicut','346','344',NULL,3,NULL),(91,'eranhipalam','11.23224','75.64653','user',3,'pending'),(92,'kakkodi','11.2898','75.6900','user',3,'pending'),(95,'454','hf','233','police',5,'Approved'),(96,'cherukulam','11.898','75.8989','police',21,'Approved');

/*Table structure for table `emergency` */

DROP TABLE IF EXISTS `emergency`;

CREATE TABLE `emergency` (
  `Emergency_id` int(11) NOT NULL AUTO_INCREMENT,
  `User_id` int(11) DEFAULT NULL,
  `Date` varchar(50) DEFAULT NULL,
  `Latitude` varchar(50) DEFAULT NULL,
  `Longitude` varchar(50) DEFAULT NULL,
  `Location` varchar(50) DEFAULT NULL,
  `Help` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Emergency_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;

/*Data for the table `emergency` */

insert  into `emergency`(`Emergency_id`,`User_id`,`Date`,`Latitude`,`Longitude`,`Location`,`Help`) values (23,1,'9-2-21','45','56','mlp',NULL),(24,NULL,'\"+curdate+\"','\"+latitude+\"','\"+longitude+\"','\"+location+\"','\"+help+\"'),(25,3,'2021-04-16','11.2587267','75.7831139','Kairali Theatre Building','do'),(26,3,'2021-04-17','11.205991799999998','75.8641071','Kozhikode Bypass','help'),(27,3,'2021-04-17','11.2591251','75.7842038','17/420','helpppp'),(28,3,'2021-04-17','11.2591251','75.7842038','17/420','ter'),(29,3,'2021-04-25','','','','anybody'),(30,3,'2021-04-25','','','','help'),(31,3,'2021-04-25','','','','help'),(32,24,'2021-04-25','11.4379193','75.8201658','Naminda Panayi Road','help'),(33,24,'2021-04-25','11.4379193','75.8201658','Naminda Panayi Road','help'),(34,24,'2021-04-25','11.4379193','75.8201658','Naminda Panayi Road','help'),(35,24,'2021-04-25','11.4379193','75.8201658','Naminda Panayi Road','help'),(36,24,'2021-04-25','11.4379193','75.8201658','Naminda Panayi Road','help'),(37,24,'2021-04-25','11.4379193','75.8201658','Naminda Panayi Road','help'),(38,24,'2021-04-25','11.4379193','75.8201658','Naminda Panayi Road','help'),(39,24,'2021-04-25','11.4379193','75.8201658','Naminda Panayi Road','help'),(40,24,'2021-04-25','11.4379193','75.8201658','Naminda Panayi Road','help'),(41,24,'2021-04-25','11.4379193','75.8201658','Naminda Panayi Road','help'),(42,24,'2021-04-25','11.4379193','75.8201658','Naminda Panayi Road','help'),(43,24,'2021-04-25','11.4379193','75.8201658','Naminda Panayi Road','help'),(44,24,'2021-04-25','11.4379193','75.8201658','Naminda Panayi Road','help'),(45,24,'2021-04-25','11.4379193','75.8201658','Naminda Panayi Road','help'),(46,24,'2021-04-25','11.4379193','75.8201658','Naminda Panayi Road','help'),(47,24,'2021-04-25','11.4379193','75.8201658','Naminda Panayi Road','help'),(48,24,'2021-04-25','11.4379193','75.8201658','Naminda Panayi Road','help'),(49,24,'2021-04-25','11.4379193','75.8201658','Naminda Panayi Road','help');

/*Table structure for table `emergency number` */

DROP TABLE IF EXISTS `emergency number`;

CREATE TABLE `emergency number` (
  `Emergency_Number_id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  `Phone_Number` varchar(50) DEFAULT NULL,
  `User_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Emergency_Number_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `emergency number` */

insert  into `emergency number`(`Emergency_Number_id`,`Name`,`Phone_Number`,`User_id`) values (1,'\"+name+\"','\"+phonenumber+\"',NULL),(2,'\"+name+\"','\"+phonenumber+\"',0),(3,'hi','7890',2),(4,'brother','123457',3),(5,'gs','155528999',9),(6,'pappa','457625755',3),(7,'dad','8086911615',24);

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `Feedback_id` int(11) NOT NULL AUTO_INCREMENT,
  `User_id` int(11) DEFAULT NULL,
  `Feedback` varchar(50) DEFAULT NULL,
  `Date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

insert  into `feedback`(`Feedback_id`,`User_id`,`Feedback`,`Date`) values (2,1,'good','2020-02-12');

/*Table structure for table `idea` */

DROP TABLE IF EXISTS `idea`;

CREATE TABLE `idea` (
  `Idea_id` int(11) NOT NULL AUTO_INCREMENT,
  `User_id` int(11) DEFAULT NULL,
  `Idea_Tittle` varchar(50) DEFAULT NULL,
  `Idea_Details` varchar(50) DEFAULT NULL,
  `Date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Idea_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `idea` */

insert  into `idea`(`Idea_id`,`User_id`,`Idea_Tittle`,`Idea_Details`,`Date`) values (2,3,'hi','welcome','2021-04-17'),(3,3,'hhh','vjf','2021-04-25'),(4,3,'Help','We can conduct seminar','2021-04-25'),(5,24,'good','good morning','2021-04-25');

/*Table structure for table `location` */

DROP TABLE IF EXISTS `location`;

CREATE TABLE `location` (
  `Location_id` int(11) NOT NULL AUTO_INCREMENT,
  `Login_id` int(11) DEFAULT NULL,
  `Location` varchar(50) DEFAULT NULL,
  `Latitude` varchar(50) DEFAULT NULL,
  `Longitude` varchar(50) DEFAULT NULL,
  `Date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `location` */

insert  into `location`(`Location_id`,`Login_id`,`Location`,`Latitude`,`Longitude`,`Date`) values (1,1,'mlp','12','34','2-2-2021'),(2,2,'ktkl','45','35','3-3-2021');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`type`) values (1,'anu','424575322555','user'),(2,'athi','1234','user'),(3,'sayi','l','user'),(4,'a','b','block'),(5,'bh','ll','police'),(6,'gg@gmail.com','1234','subadmin'),(7,'ah','pp','police'),(8,'shahina@gmail.com','9876543210','police'),(9,'shahina@gmail.com','shahina','subadmin'),(10,'shahina@gmail.com','9876543210','subadmin'),(11,'shahina@gmail.com','9876543210','subadmin'),(12,'shahina@gmail.com','9876543210','subadmin'),(13,'shahina@gmail.com','9876543210','subadmin'),(14,'shahina@gmail.com','9876543210','police'),(15,'shahina@gmail.com','9876543210','subadmin'),(16,'admin@gmail.com','a','admin'),(17,'vvv@gmail.com','9877445563','police'),(18,'aswa@gmail.com','k','subadmin'),(19,'kk@gmail.com','9622331144','subadmin'),(20,'kk@gmail.com','9622331144','subadmin'),(21,'kasab2021@gmail.com','pp','police'),(24,'fgf','a','user'),(25,'fbff','565898545','user');

/*Table structure for table `login_page` */

DROP TABLE IF EXISTS `login_page`;

CREATE TABLE `login_page` (
  `Login_id` int(11) NOT NULL AUTO_INCREMENT,
  `User_Name` varchar(50) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `User_Type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `login_page` */

/*Table structure for table `notification` */

DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification` (
  `Notification_id` int(11) NOT NULL AUTO_INCREMENT,
  `Message` varchar(50) DEFAULT NULL,
  `Date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Notification_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `notification` */

insert  into `notification`(`Notification_id`,`Message`,`Date`) values (5,'nnkj','2021-02-18'),(6,'yrhyjki,liuj','2021-02-18'),(7,'hjikl','2021-02-19'),(10,'pp','2021-04-24');

/*Table structure for table `police` */

DROP TABLE IF EXISTS `police`;

CREATE TABLE `police` (
  `Police_id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  `Gender` varchar(50) DEFAULT NULL,
  `DOB` varchar(50) DEFAULT NULL,
  `Station_Name` varchar(50) DEFAULT NULL,
  `City` varchar(50) DEFAULT NULL,
  `District` varchar(50) DEFAULT NULL,
  `State` varchar(50) DEFAULT NULL,
  `Pin` varchar(50) DEFAULT NULL,
  `Phone_Number` varchar(50) DEFAULT NULL,
  `Email_id` varchar(50) DEFAULT NULL,
  `Image` varchar(100) DEFAULT NULL,
  `Login_id` int(11) DEFAULT NULL,
  `lati` bigint(20) DEFAULT NULL,
  `longi` bigint(20) DEFAULT NULL,
  `place` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Police_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `police` */

insert  into `police`(`Police_id`,`Name`,`Gender`,`DOB`,`Station_Name`,`City`,`District`,`State`,`Pin`,`Phone_Number`,`Email_id`,`Image`,`Login_id`,`lati`,`longi`,`place`) values (1,'Hiba','Female','2013-02-28   ','malappuram','kottakkal','malappuram','kerala','676503','9633221145','ghhsg@gmail.com','/static/subadmin_images/u2.jpg',5,NULL,NULL,NULL),(6,'shahina','Female','2021-02-11','trg','uihiu','malappuram','kerala','676506','9876543210','shahina@gmail.com','/static/police_images/DSC_0874.JPG',7,NULL,NULL,NULL),(7,'athira','Female','2021-02-10 ','4444','uihiu','malappuram','kerala','676506','9876543210','shahina@gmail.com','/static/subadmin_images/team-2.jpg',8,NULL,NULL,NULL),(8,'megha','Female','2021-02-10','ghh','hhjk','malappuram','kerala','676506','9876543210','shahina@gmail.com','/static/police_images/2017-10-15-17-29-23-958.jpg',14,NULL,NULL,NULL),(10,'Arun','male','2021-04-29','kasaba','calicut','kozhikkod','kerala','673600','9633221144','kasab2021@gmail.com','/static/police_images/1.jpg',21,11,76,'calicut');

/*Table structure for table `safe` */

DROP TABLE IF EXISTS `safe`;

CREATE TABLE `safe` (
  `Safe_id` int(11) NOT NULL AUTO_INCREMENT,
  `Location` varchar(50) DEFAULT NULL,
  `Latitude` varchar(50) DEFAULT NULL,
  `Longitude` varchar(50) DEFAULT NULL,
  `Subadmin_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Safe_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `safe` */

insert  into `safe`(`Safe_id`,`Location`,`Latitude`,`Longitude`,`Subadmin_id`) values (1,'malappuram','12','123',NULL),(4,'kottakkal','1234','3333','6'),(5,'palakaad','56','56','6');

/*Table structure for table `site_info` */

DROP TABLE IF EXISTS `site_info`;

CREATE TABLE `site_info` (
  `Info_id` int(11) NOT NULL AUTO_INCREMENT,
  `Message` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

/*Data for the table `site_info` */

insert  into `site_info`(`Info_id`,`Message`) values (18,'oooookk');

/*Table structure for table `subadmin` */

DROP TABLE IF EXISTS `subadmin`;

CREATE TABLE `subadmin` (
  `Subadmin_id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  `Gender` varchar(50) DEFAULT NULL,
  `DOB` varchar(50) DEFAULT NULL,
  `City` varchar(50) DEFAULT NULL,
  `District` varchar(50) DEFAULT NULL,
  `State` varchar(50) DEFAULT NULL,
  `Pin` varchar(50) DEFAULT NULL,
  `Phone_Number` varchar(50) DEFAULT NULL,
  `Email_id` varchar(50) DEFAULT NULL,
  `Image` varchar(100) DEFAULT NULL,
  `Login_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Subadmin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `subadmin` */

insert  into `subadmin`(`Subadmin_id`,`Name`,`Gender`,`DOB`,`City`,`District`,`State`,`Pin`,`Phone_Number`,`Email_id`,`Image`,`Login_id`) values (11,'athulya nadh','Female','2021-02-09','revathi','malappuram','kerala','676506','9876543210','shahina@gmail.com','/static/subadmin_images/2017-11-04-16-35-27-003.jpg',6),(13,'anu','Female','2021-02-26','mpm','malappuram','kerala','676506','9876543210','shahina@gmail.com','/static/subadmin_images/2019-06-23-18-23-45-626-1.jpg',13),(14,'shahla','Female','2021-02-09','uihiu','malappuram','kerala','676506','9876543210','shahina@gmail.com','/static/subadmin_images/2017-10-05-09-50-18-365.jpg',15),(15,'Aswani sen','male','2021-04-24','koyilandy','malappuram','kerala','632255','9388552233','aswa@gmail.com','/static/subadmin_images/1317-Deepika-Padukone’s-Beauty-Tips-And-Fitness-Secrets-Revealed.jpg.webp',18),(16,'jithin','male','2021-04-23','kkd','kozhikkod','kerala','673600','9622331144','kk@gmail.com','/static/subadmin_images/team-3.jpg',19);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `User_id` int(10) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  `Gender` varchar(50) DEFAULT NULL,
  `DOB` varchar(50) DEFAULT NULL,
  `House_Number` varchar(50) DEFAULT NULL,
  `City` varchar(50) DEFAULT NULL,
  `District` varchar(50) DEFAULT NULL,
  `Pin` varchar(50) DEFAULT NULL,
  `Image` varchar(100) DEFAULT NULL,
  `Phone_Number` varchar(50) DEFAULT NULL,
  `Email_ID` varchar(50) DEFAULT NULL,
  `Login_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`User_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`User_id`,`Name`,`Gender`,`DOB`,`House_Number`,`City`,`District`,`Pin`,`Image`,`Phone_Number`,`Email_ID`,`Login_id`) values (1,'Fazila','female','04/24/21','963','Malappuram','malappuram','632211','/static/user/2021_04_24_16_39_10.555489.jpg','8566223145','fa@gmail.com',24),(2,'sayi','female','10/24/21','456','calicut','kozhikode','673912','/static/police_images/DSC_0874.JPG','985236147','sayi@gmail.com',3),(3,'amal','male','04/30/21','fhg','fhg','fhf','57546','/static/user/2021_04_25_15_37_55.078373.jpg','565898545','fbff',25);

/*Table structure for table `vehicle` */

DROP TABLE IF EXISTS `vehicle`;

CREATE TABLE `vehicle` (
  `Vehicle_id` int(11) NOT NULL AUTO_INCREMENT,
  `Register_Number` varchar(50) DEFAULT NULL,
  `Chasis_Number` varchar(50) DEFAULT NULL,
  `Type_of_Vehicle` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Vehicle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `vehicle` */

insert  into `vehicle`(`Vehicle_id`,`Register_Number`,`Chasis_Number`,`Type_of_Vehicle`) values (2,'8oiuyhg','45','car'),(6,'345','345','Bike'),(7,'345','345','Bike'),(8,'5656565','345','Bike'),(9,'kl12345','8989','Car'),(11,'7778797','778','Car');

/*Table structure for table `vehicle_allocation` */

DROP TABLE IF EXISTS `vehicle_allocation`;

CREATE TABLE `vehicle_allocation` (
  `Allocate_id` int(11) NOT NULL AUTO_INCREMENT,
  `Vehicle_id` int(11) DEFAULT NULL,
  `Police_id` int(11) DEFAULT NULL,
  `Allocated_Date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Allocate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `vehicle_allocation` */

insert  into `vehicle_allocation`(`Allocate_id`,`Vehicle_id`,`Police_id`,`Allocated_Date`) values (1,NULL,NULL,NULL),(2,1,1,'1'),(3,2,2,'2021-01-22'),(4,3,7,'2021-02-18'),(5,9,5,'2021-04-24');

/*Table structure for table `visual` */

DROP TABLE IF EXISTS `visual`;

CREATE TABLE `visual` (
  `Visual_id` int(11) NOT NULL AUTO_INCREMENT,
  `Police_id` int(11) DEFAULT NULL,
  `File_Name` varchar(50) DEFAULT NULL,
  `Location` varchar(50) DEFAULT NULL,
  `Date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Visual_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2224 DEFAULT CHARSET=latin1;

/*Data for the table `visual` */

insert  into `visual`(`Visual_id`,`Police_id`,`File_Name`,`Location`,`Date`) values (1111,8,'\\static\\bbc.jpg','Malappuram','24-2-21'),(2223,5,'/static/visual/2021_04_25_16_13_45.409788.jpg',' kolkata','2021-04-25');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
