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
INSERT INTO `auth` VALUES (1,'$2a$10$cNCNS6PTjTnyOv1mPxI1wumOOnd9x0GlLWlVjkg1iUqaNtLVmGCSi',1),(2,'$2a$10$h59k.3KA2Kf3IarzN34TMe9792tpXN5J1wEzbw6SN7DTrGhPjmlV6',2),(3,'$2a$10$NAXAtsZ.9Ue1MO0LRg4ydezLIIWy8FZnONPcL70b/lvpfa3aky5ji',3),(4,'$2a$10$Gf/azo7XXyocoASELk/XuO9rBNvTkWvGr1W/5LyTxO0HadIbldmhK',4),(5,'$2a$10$q2fQQIPF2oks4Hznv/tfD.aMJQqL2nOcVhVefjjoJXA1Qio3XNTsC',5);
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'kiwi','How to eat kiwi elegantly','a boring book',7,5,8,'9999999999999',1,2800,2077,4,2,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/csapp.jpg',2,'生物',2),(2,'George R.R. Martin','A Game of Thrones','The first book in the A Song of Ice and Fire series',8,800,601,'9780553386790',50,2500,1996,300,151,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/A%20Game%20of%20Thrones.jpg',4,'文学',20),(3,'J.K. Rowling','Harry Potter and the Philosopher\'s Stone','The first book in the Harry Potter series',7,1000,700,'9780747532699',100,2000,1997,500,200,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Harry%20Potter%20and%20the%20Philosopher\'s%20Stone.jpg',11,'文学',93),(5,'Dan Brown','The Da Vinci Code','A thriller novel involving a murder in the Louvre Museum',5,1200,900,'9781400079179',80,1800,2003,200,250,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Da%20Vinci%20Code.jpg',1,'社会学',1100),(6,'Agatha Christie','Murder on the Orient Express','A detective novel featuring Hercule Poirot investigating a murder on a train',6,1500,1200,'9780062693662',70,1500,1934,400,300,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Murder%20on%20the%20Orient%20Express.jpg',1,'文学',106),(7,'Jane Austen','Pride and Prejudice','A classic novel exploring themes of love, marriage, and social status',1,900,700,'9780141439518',30,1588,1813,200,150,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Pride%20and%20Prejudice.jpg',4,'英语书',99),(8,'Harper Lee','To Kill a Mockingbird','A classic novel set in the 1930s that explores racial injustice and the loss of innocence',7,1000,800,'9780061120084',50,1100,1960,300,200,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/To%20Kill%20a%20Mockingbird.jpg',1,'英语书',1000),(9,'F. Scott Fitzgerald','The Great Gatsby','A novel set in the Jazz Age that explores themes of wealth, love, and the American Dream',7,1200,1001,'9780743273565',80,1000,1925,400,250,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Great%20Gatsby.jpg',1,'特价书',1000),(10,'Ernest Hemingway','The Old Man and the Sea','A novella about an old fisherman\'s struggle with a giant marlin while battling the forces of nature',7,800,600,'9780684801223',30,8000,1952,200,150,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/To%20Kill%20a%20Mockingbird.jpg',0,'英语书',10002),(11,'Markus Zusak','The Book Thief','A historical novel set in Nazi Germany, narrated by Death and centered around a young girl named Liesel Meminger',7,1500,1200,'9780375842207',70,12000,2005,400,300,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Book%20Thief.jpg',0,'历史',100),(12,'Khaled Hosseini','The Kite Runner','A novel set in Afghanistan that follows the friendship between two boys against the backdrop of political turmoil',7,1000,800,'9781594480003',50,1121,2003,300,200,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/The%20Kite%20Runner.jpg',3,'历史',1097),(13,'Gillian Flynn','Gone Girl','A psychological thriller about a woman who disappears on her fifth wedding anniversary, leaving behind a series of clues',7,1200,1000,'9780307588371',80,1001,2012,400,250,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/Gone%20Girl.jpg',0,'文学',100),(15,'kiwi','好书','一本好书',6,5,4,'1425867512345',1,5000,2024,3,2,'https://web-data-1319894912.cos.ap-shanghai.myqcloud.com/csapp.jpg',1000,'数学',100);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_rate`
--

LOCK TABLES `book_rate` WRITE;
/*!40000 ALTER TABLE `book_rate` DISABLE KEYS */;
INSERT INTO `book_rate` VALUES (1,4,9,1),(2,4,1,1),(3,2,2,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12),(13),(14),(15);
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
INSERT INTO `cart_item` VALUES (9,1,10,3),(10,1,11,3),(12,1,13,3),(13,1,9,3),(14,1,8,3),(15,4,1,3),(18,10,1,15);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'test\n','2024-06-01 15:22:14',NULL,1,1),(2,'test\n','2024-06-01 15:22:21','test1',1,1);
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
  `number` int DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb033an1f8qmpbnfl0a6jb5njs` (`book_id`),
  KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`),
  CONSTRAINT `FKb033an1f8qmpbnfl0a6jb5njs` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (2,1,2,2),(3,1,2,3),(4,1,2,4),(5,1,9,5),(6,1,8,6),(7,1,1,7),(8,1,3,8),(9,1,5,8),(10,1,2,9),(11,1,1,9),(12,1,6,10),(13,4,7,11),(14,3,12,11),(15,10,3,12);
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,'上海交通大学闵行校区 东27宿舍楼','2024-04-20 09:14:31.163790','陈启炜','13123128852',1),(3,'上海交通大学闵行校区 东27宿舍楼','2024-05-31 09:15:29.517223','陈启炜','13123128852',1),(4,'上海交通大学闵行校区 东27宿舍楼','2024-05-31 09:19:09.293853','陈启炜','13123128852',1),(5,'上海交通大学闵行校区 东27宿舍楼','2024-05-31 09:19:26.059674','陈启炜','13123128852',1),(6,'上海交通大学闵行校区 东27宿舍楼','2024-05-31 14:10:47.210425','陈启炜','13123128852',1),(7,'上海交通大学闵行校区 东27宿舍楼','2024-06-01 02:44:12.182934','陈启炜','13123128852',1),(8,'上海交通大学闵行校区','2024-06-01 11:56:56.588627','启炜 Chen','13123128852',1),(9,'上海交通大学闵行校区 东27宿舍楼','2024-06-01 12:50:29.275328','启炜 Chen','13123128852',3),(10,'上海交通大学闵行校区 东27宿舍楼','2024-06-01 16:06:04.418511','启炜 Chen','13123128852',1),(11,'上海交通大学闵行校区 东27宿舍楼','2024-06-02 08:52:19.616821','启炜 Chen','13123128852',1),(12,'上海交通大学闵行校区 东27宿舍楼','2024-06-02 09:13:17.296473','启炜 Chen','13123128852',5);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_items`
--

DROP TABLE IF EXISTS `orders_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_items` (
  `order_id` int NOT NULL,
  `items_id` int NOT NULL,
  UNIQUE KEY `UK_7qrg5pfgjon82yhgwfqrdijm5` (`items_id`),
  KEY `FKij1wwgx6o198ubsx1oulpopem` (`order_id`),
  CONSTRAINT `FKij1wwgx6o198ubsx1oulpopem` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKl3w3fx5rbjq0tbb2i0xidwabh` FOREIGN KEY (`items_id`) REFERENCES `order_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_items`
--

LOCK TABLES `orders_items` WRITE;
/*!40000 ALTER TABLE `orders_items` DISABLE KEYS */;
INSERT INTO `orders_items` VALUES (2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(8,9);
/*!40000 ALTER TABLE `orders_items` ENABLE KEYS */;
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
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `cart_id` int DEFAULT NULL,
  `role` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `balance` int DEFAULT NULL,
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
INSERT INTO `user` VALUES (1,NULL,NULL,'kiwi','aaa@sjtu.edu.cn','test1',3,1,0,88785),(2,NULL,NULL,'我是用户2','test@sjtu.edu.cn','test3',NULL,1,0,100),(3,NULL,NULL,'我是哈哈哈','779490624@qq.com','test2',8,0,0,100),(4,NULL,NULL,'可爱~','13123128852@163.com','test4',NULL,0,0,1000),(5,NULL,NULL,'aaa\n','779490624@qq.com','test5',15,0,0,80000);
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

-- Dump completed on 2024-06-02 18:28:05
