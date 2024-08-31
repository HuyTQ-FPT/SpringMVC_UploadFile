CREATE DATABASE  IF NOT EXISTS `tcom` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tcom`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: tcom
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `filestore`
--

DROP TABLE IF EXISTS `filestore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `filestore` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) NOT NULL,
  `create_date` datetime NOT NULL,
  `change_date` datetime DEFAULT NULL,
  `folderid` bigint DEFAULT '0',
  `file_data` varbinary(255) DEFAULT NULL,
  `fid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `id_idx` (`folderid`),
  KEY `FKis15w6nw25x58yipu95fauyu` (`fid`),
  CONSTRAINT `FKis15w6nw25x58yipu95fauyu` FOREIGN KEY (`fid`) REFERENCES `folderstore` (`fid`),
  CONSTRAINT `FOLDERID` FOREIGN KEY (`folderid`) REFERENCES `folderstore` (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filestore`
--

LOCK TABLES `filestore` WRITE;
/*!40000 ALTER TABLE `filestore` DISABLE KEYS */;
INSERT INTO `filestore` VALUES (20,'HUYC#.txt','2024-08-31 03:19:45','2024-08-31 10:03:41',11,_binary 'tranquanghuyty01@icloud.com/Qhuy2001',NULL),(21,'New folder/Iphone.txt','2024-08-31 11:50:55',NULL,19,_binary 'tranquanghuyty01@icloud.com/Qhuy2001',NULL),(23,'Iphone.txt','2024-08-31 12:18:15',NULL,7,_binary 'tranquanghuyty01@icloud.com/Qhuy2001',NULL),(26,'New folder/HUY1.txt','2024-08-31 16:14:17',NULL,22,_binary 'tranquanghuyty01@icloud.com/Qhuy2001',NULL),(27,'New folder/Iphone.txt','2024-08-31 16:14:17',NULL,22,_binary 'tranquanghuyty01@icloud.com/Qhuy2001',NULL);
/*!40000 ALTER TABLE `filestore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folderstore`
--

DROP TABLE IF EXISTS `folderstore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `folderstore` (
  `fid` bigint NOT NULL AUTO_INCREMENT,
  `folder_name` varchar(255) NOT NULL,
  `create_date` datetime NOT NULL,
  `pre_folder` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`fid`),
  UNIQUE KEY `id_UNIQUE` (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folderstore`
--

LOCK TABLES `folderstore` WRITE;
/*!40000 ALTER TABLE `folderstore` DISABLE KEYS */;
INSERT INTO `folderstore` VALUES (1,'TCOM','2024-08-29 00:00:00',0),(6,'FPT','2024-08-29 00:00:00',0),(7,'Hola','2024-08-29 00:00:00',6),(8,'CauGiay','2024-08-29 00:00:00',6),(9,'Viettel','2024-08-29 00:00:00',0),(10,'DuAn1','2024-08-29 00:00:00',4),(11,'DuAn2','2024-08-29 00:00:00',3),(12,'DuAn4','2024-08-29 00:00:00',5),(17,'Spring','2024-08-31 01:36:39',16),(19,'DuAnC#','2024-08-31 03:10:36',3),(20,'C#FPT','2024-08-31 10:04:09',3),(21,'XXXL','2024-08-31 12:18:35',7),(22,'New folder','2024-08-31 16:14:17',9);
/*!40000 ALTER TABLE `folderstore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'tcom'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-31 17:15:46
