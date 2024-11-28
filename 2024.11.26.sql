-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: bookstore
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auth`
--

DROP TABLE IF EXISTS `auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ox9lr2lxr8h7undhmflx4xqky` (`user_id`),
  CONSTRAINT `FK71o3g4vv7a893ax9k7mrh63cd` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth`
--

LOCK TABLES `auth` WRITE;
/*!40000 ALTER TABLE `auth` DISABLE KEYS */;
INSERT INTO `auth` VALUES (1,'$2a$10$1GSIwlgpF/e4tzhZKBrZSujoZrdtT3tQ6pbtX9zs2pdLebJg9wicG',1),(2,'$2a$10$hSsEzdb6aEeYItEE68k3sOnidR/VWpULM5Y4tVgm3sBeckb5LxMc2',2),(3,'$2a$10$KWwagcEHMvjbUB62POxyMe7hFI92Qt13KKxOjUeJIXD18fhijDY.6',3),(4,'$2a$10$1O3x9THGjujsktGCamAiK.vaty4wrk9X2MqDrpIMovJcV147fFrx6',4),(5,'$2a$10$2F..6VBP/zrQ4iBqNxlbv.2O2OlJWOrIOuPgm1Zn5u4rHrZ/aRYh2',5);
/*!40000 ALTER TABLE `auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `discount` int DEFAULT NULL,
  `five_star_number` int DEFAULT NULL,
  `four_star_number` int DEFAULT NULL,
  `isbn` varchar(13) DEFAULT NULL,
  `one_star_number` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `print_year` int DEFAULT NULL,
  `three_star_number` int DEFAULT NULL,
  `two_star_number` int DEFAULT NULL,
  `cover` varchar(255) DEFAULT NULL,
  `sales` int DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'kiwi','How to eat kiwi elegantly','a boring book',1,6,8,'9999999999999',3,3500,2077,5,2,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/images/1719566557103_睡前故事.jpg',8,'生物',4),(2,'George R.R. Martin','A Game of Thrones','The first book in the A Song of Ice and Fire series',8,800,601,'9780553386790',50,2500,1996,300,151,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/A%20Game%20of%20Thrones.jpg',10,'文学',14),(3,'作者','吉伊卡哇','一本好书',8,4,5,'1426657514345',2,500,2005,3,3,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/images/1719566646829_吉伊卡哇.jpg',7,'数学',6),(5,'Dan Brown','The Da Vinci Code','A thriller novel involving a murder in the Louvre Museum and me',5,1200,900,'9781400079179',80,1800,2003,200,250,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Da%20Vinci%20Code.jpg',11,'社会学',1090),(6,'Agatha Christie','Murder on the Orient Express','A detective novel featuring Hercule Poirot investigating a murder on a train',6,1500,1200,'9780062693662',70,1500,1934,400,300,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Murder%20on%20the%20Orient%20Express.jpg',1,'文学',106),(7,'Gillian Flynn','傲慢与偏见','A psychological thriller about a woman who disappears on her fifth wedding anniversary, leaving behind a series of clues',7,1200,1001,'9780743273568',60,1100,1990,500,10,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Murder%20on%20the%20Orient%20Express.jpg',7,'英语书',88),(8,'Harper Lee','To Kill a Mockingbird','A classic novel set in the 1930s that explores racial injustice and the loss of innocence',7,1000,800,'9780061120084',50,1100,1960,300,200,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/To%20Kill%20a%20Mockingbird.jpg',1,'英语书',1000),(9,'F. Scott Fitzgerald','The Great Gatsby','A novel set in the Jazz Age that explores themes of wealth, love, and the American Dream',7,1200,1001,'9780743273565',80,1000,1925,400,250,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Great%20Gatsby.jpg',2,'特价书',999),(10,'Ernest Hemingway','The Old Man and the Sea','A novella about an old fisherman\'s struggle with a giant marlin while battling the forces of nature',7,800,600,'9780684801223',30,8000,1952,200,150,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/To%20Kill%20a%20Mockingbird.jpg',1,'英语书',10001),(11,'Markus Zusak','The Book Thief','A historical novel set in Nazi Germany, narrated by Death and centered around a young girl named Liesel Meminger',7,1500,1200,'9780375842207',70,12000,2005,400,300,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Book%20Thief.jpg',8,'历史',92),(12,'Khaled Hosseini','The Kite Runner','A novel set in Afghanistan that follows the friendship between two boys against the backdrop of political turmoil',7,1000,800,'9781594480003',50,1121,2003,300,200,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Kite%20Runner.jpg',4,'历史',1096),(13,'Gillian Flynn','Gone Girl','A psychological thriller about a woman who disappears on her fifth wedding anniversary, leaving behind a series of clues',7,1200,1000,'9780307588371',80,1001,2012,400,250,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Gone%20Girl.jpg',3,'文学',97);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_rate`
--

DROP TABLE IF EXISTS `book_rate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_rate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `rate` int DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqfx6u1i8ph6lfmhwo9tyopl2f` (`book_id`),
  KEY `FKl6em1phv37skasaaqwn8ahtoh` (`user_id`),
  CONSTRAINT `FKl6em1phv37skasaaqwn8ahtoh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKqfx6u1i8ph6lfmhwo9tyopl2f` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_rate`
--

LOCK TABLES `book_rate` WRITE;
/*!40000 ALTER TABLE `book_rate` DISABLE KEYS */;
INSERT INTO `book_rate` VALUES (1,5,1,3);
/*!40000 ALTER TABLE `book_rate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1),(2),(3),(4);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `number` int DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  `cart_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKis5hg85qbs5d91etr4mvd4tx6` (`book_id`),
  KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`),
  CONSTRAINT `FK1uobyhgl1wvgt1jpccia8xxs3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  CONSTRAINT `FKis5hg85qbs5d91etr4mvd4tx6` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `reply` varchar(255) DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkko96rdq8d82wm91vh2jsfak7` (`book_id`),
  KEY `FK8kcum44fvpupyw6f5baccx25c` (`user_id`),
  CONSTRAINT `FK8kcum44fvpupyw6f5baccx25c` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKkko96rdq8d82wm91vh2jsfak7` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_cover` varchar(255) DEFAULT NULL,
  `book_discount` int DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  `book_price` int DEFAULT NULL,
  `book` varchar(255) DEFAULT NULL,
  `number` int DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`),
  CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (1,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/csapp.jpg',7,1,2800,'How to eat kiwi elegantly',1,1),(2,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/images/1719566557103_睡前故事.jpg',7,1,3500,'How to eat kiwi elegantly',1,2),(3,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/A%20Game%20of%20Thrones.jpg',8,2,2500,'A Game of Thrones',1,3),(4,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Harry%20Potter%20and%20the%20Philosopher\'s%20Stone.jpg',7,3,2000,'Harry Potter and the Philosopher\'s Stone',1,4),(6,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Gone%20Girl.jpg',7,13,1001,'Gone Girl',3,6),(7,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/images/1719566557103_睡前故事.jpg',1,1,3500,'How to eat kiwi elegantly',1,7),(8,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Da%20Vinci%20Code.jpg',5,5,1800,'The Da Vinci Code',1,7),(9,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Pride%20and%20Prejudice.jpg',1,7,1588,'Pride and Prejudice',1,7),(10,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Great%20Gatsby.jpg',7,9,1000,'The Great Gatsby',1,8),(11,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Pride%20and%20Prejudice.jpg',1,7,1588,'Pride and Prejudice',1,9),(12,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Pride%20and%20Prejudice.jpg',1,7,1588,'Pride and Prejudice',1,10),(13,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Pride%20and%20Prejudice.jpg',1,7,1588,'Pride and Prejudice',1,11),(14,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Pride%20and%20Prejudice.jpg',1,7,1588,'Pride and Prejudice',1,12),(15,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Pride%20and%20Prejudice.jpg',1,7,1588,'Pride and Prejudice',1,13),(16,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Pride%20and%20Prejudice.jpg',1,7,1588,'Pride and Prejudice',1,14),(17,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Pride%20and%20Prejudice.jpg',1,7,1588,'Pride and Prejudice',1,15),(18,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Pride%20and%20Prejudice.jpg',1,7,1588,'Pride and Prejudice',1,16),(19,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Pride%20and%20Prejudice.jpg',1,7,1588,'Pride and Prejudice',1,18),(20,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/A%20Game%20of%20Thrones.jpg',8,2,2500,'A Game of Thrones',1,19),(21,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Da%20Vinci%20Code.jpg',5,5,1800,'The Da Vinci Code',1,19),(22,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Da%20Vinci%20Code.jpg',5,5,1800,'The Da Vinci Code',1,20),(23,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Pride%20and%20Prejudice.jpg',1,7,1588,'Pride and Prejudice',1,20),(24,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/A%20Game%20of%20Thrones.jpg',8,2,2500,'A Game of Thrones',1,21),(25,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Pride%20and%20Prejudice.jpg',1,7,1588,'Pride and Prejudice',2,22),(26,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Da%20Vinci%20Code.jpg',5,5,1800,'The Da Vinci Code',1,22),(27,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Da%20Vinci%20Code.jpg',5,5,1800,'The Da Vinci Code',1,23),(28,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Da%20Vinci%20Code.jpg',5,5,1800,'The Da Vinci Code',1,24),(29,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Da%20Vinci%20Code.jpg',5,5,1800,'The Da Vinci Code',1,25),(30,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/A%20Game%20of%20Thrones.jpg',8,2,2500,'A Game of Thrones',1,26),(31,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Da%20Vinci%20Code.jpg',5,5,1800,'The Da Vinci Code',1,27),(36,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Kite%20Runner.jpg',7,12,1121,'The Kite Runner',1,32),(37,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Book%20Thief.jpg',7,11,12000,'The Book Thief',1,33),(38,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Book%20Thief.jpg',7,11,12000,'The Book Thief',1,34),(39,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Book%20Thief.jpg',7,11,12000,'The Book Thief',1,35),(40,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Da%20Vinci%20Code.jpg',5,5,1800,'The Da Vinci Code',1,36),(41,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Book%20Thief.jpg',7,11,12000,'The Book Thief',1,37),(42,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Book%20Thief.jpg',7,11,12000,'The Book Thief',1,38),(43,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Book%20Thief.jpg',7,11,12000,'The Book Thief',1,39),(44,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Book%20Thief.jpg',7,11,12000,'The Book Thief',1,40),(45,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Book%20Thief.jpg',7,11,12000,'The Book Thief',1,41),(46,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/A%20Game%20of%20Thrones.jpg',8,2,2500,'A Game of Thrones',1,42),(47,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/To%20Kill%20a%20Mockingbird.jpg',7,10,8000,'The Old Man and the Sea',1,43),(48,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Da%20Vinci%20Code.jpg',5,5,1800,'The Da Vinci Code',1,44),(49,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Murder%20on%20the%20Orient%20Express.jpg',7,7,1100,'傲慢与偏见',1,45),(50,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Murder%20on%20the%20Orient%20Express.jpg',7,7,1100,'傲慢与偏见',1,46);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `receiver` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`),
  CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'上海交通大学闵行校区 东27宿舍楼','2024-06-26 12:51:19.636025','陈启炜','13123128852',1),(2,'上海交通大学闵行校区 东27宿舍楼','2024-06-28 09:24:56.285008','陈启炜','13123128852',1),(3,'333','2024-06-28 10:04:01.642051','111','15620145632',1),(4,'33','2024-06-28 10:17:18.849452','11','13123128852',3),(5,'上海交通大学闵行校区 东27宿舍楼','2024-06-28 11:22:46.199804','陈启炜','13100000000',1),(6,'上海交通大学闵行校区 东27宿舍楼','2024-06-29 03:18:34.168073','陈启炜','13100000000',4),(7,'上海交通大学闵行校区 东27宿舍楼','2024-06-29 09:45:22.749810','陈启炜','13100000000',5),(8,'上海交通大学闵行校区 东27宿舍楼','2024-05-29 09:46:00.473000','陈启炜','13100000000',5),(9,'上海交通大学闵行校区 东27宿舍楼','2024-10-15 04:00:09.991054','陈启炜','13100000000',1),(10,'上海交通大学闵行校区 东27宿舍楼','2024-10-15 04:09:48.450254','陈启炜','13100000000',1),(11,'上海交通大学闵行校区 东27宿舍楼','2024-10-15 04:11:36.575961','陈启炜','13100000000',1),(12,'上海交通大学闵行校区','2024-10-15 04:44:15.225488','陈启炜','13100000000',1),(13,'上海交通大学闵行校区 东27宿舍楼','2024-10-15 04:58:51.221991','陈启炜','13100000000',1),(14,'上海交通大学闵行校区 东27宿舍楼','2024-10-15 05:08:49.054187','陈启炜','13100000000',1),(15,'上海交通大学闵行校区 东27宿舍楼','2024-10-15 05:10:30.929832','陈启炜','13100000000',1),(16,'上海交通大学闵行校区 东27宿舍楼','2024-10-15 05:13:20.478762','陈启炜','13100000000',1),(18,'上海交通大学闵行校区 东27宿舍楼','2024-10-15 05:21:16.917838','陈启炜','13100000000',1),(19,'上海交通大学闵行校区 东27宿舍楼','2024-10-15 05:42:01.878447','陈启炜','13100000000',1),(20,'上海交通大学闵行校区 东27宿舍楼','2024-10-15 05:47:15.848883','陈启炜','13100000000',1),(21,'上海交通大学闵行校区 东27宿舍楼a','2024-10-15 05:47:47.060193','陈启炜','13100000000',1),(22,'上海交通大学闵行校区','2024-10-15 05:48:32.439989','陈启炜','13100000000',1),(23,'上海交通大学闵行校区 东27宿舍楼','2024-10-15 05:49:19.805715','陈启炜','13100000000',1),(24,'上海交通大学闵行校区 东27宿舍楼','2024-10-15 05:52:51.062130','陈启炜','13100000000',1),(25,'上海交通大学闵行校区 东27宿舍楼','2024-10-15 07:53:59.435583','陈启炜','13100000000',1),(26,'上海交通大学闵行校区 东27宿舍楼','2024-10-15 07:54:19.529798','陈启炜','13100000000',1),(27,'上海交通大学闵行校区 东27宿舍楼','2024-10-15 07:56:03.712768','陈启炜','13100000000',1),(32,'上海交通大学闵行校区 东27宿舍楼','2024-11-15 13:15:39.579508','陈启炜','13100000000',1),(33,'上海交通大学闵行校区 东27宿舍楼','2024-11-15 13:21:31.752064','陈启炜','13100000000',1),(34,'上海交通大学闵行校区 东27宿舍楼','2024-11-15 13:25:12.121529','陈启炜','13100000000',1),(35,'上海交通大学闵行校区 东27宿舍楼','2024-11-15 13:30:00.351524','陈启炜','13100000000',1),(36,'上海交通大学闵行校区 东27宿舍楼','2024-11-15 13:32:48.904230','陈启炜','13100000000',1),(37,'上海交通大学闵行校区 东27宿舍楼','2024-11-15 13:36:04.244775','陈启炜','13100000000',1),(38,'上海交通大学闵行校区 东27宿舍楼','2024-11-15 13:41:15.588477','陈启炜','13100000000',1),(39,'上海交通大学闵行校区','2024-11-15 13:46:40.259592','陈启炜','13100000000',1),(40,'上海交通大学闵行校区 东27宿舍楼','2024-11-15 13:49:40.497837','陈启炜','13123128852',1),(41,'上海交通大学闵行校区 东27宿舍楼','2024-11-15 13:51:49.206662','陈启炜','13123128852',1),(42,'上海交通大学闵行校区 东27宿舍楼','2024-11-15 15:09:03.500787','陈启炜','13123128852',1),(43,'aa','2024-11-15 15:34:18.796396','Kiwi','13123128888',4),(44,'aa','2024-11-15 15:34:49.146051','Kiwi','13123128888',4),(45,'上海交通大学闵行校区 东27宿舍楼','2024-11-16 00:38:23.915508','陈启炜','13123128852',1),(46,'上海交通大学闵行校区 东27宿舍楼','2024-11-16 00:38:51.645919','陈启炜','13123128852',1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `balance` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `cart_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_47dq8urpj337d3o65l3fsjph3` (`cart_id`),
  CONSTRAINT `FKtqa69bib34k2c0jhe7afqsao6` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/images/1719492562959_shanghaijiaotongUniversity.jpg',24170,'aa','779490624@qq.com','test1',1,0,1),(2,NULL,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/images/1719572701767_吉伊卡哇.jpg',100000,NULL,NULL,'test3',0,1,NULL),(3,NULL,NULL,98600,NULL,'kiwi@outlook.sjtu.edu.cn','kiwi',0,0,2),(4,NULL,NULL,84898,NULL,'779490624@qq.com','test2',0,0,3),(5,NULL,NULL,97892,NULL,'7794906@qq.com','test8',0,0,4);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-26  0:05:43
