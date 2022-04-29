CREATE DATABASE  IF NOT EXISTS `dreamland` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dreamland`;
-- MySQL dump 10.13  Distrib 8.0.28, for macos11 (x86_64)
--
-- Host: 127.0.0.1    Database: dreamland
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `currency` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(5) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` VALUES (1,'USD','US Dollars');
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dreamtoken_rate`
--

DROP TABLE IF EXISTS `dreamtoken_rate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dreamtoken_rate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `exchange_rate_to_currency` decimal(22,12) NOT NULL,
  `dreamland_fee` decimal(22,12) NOT NULL,
  `id_currency` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_dreamtoken_rate_currency_idx` (`id_currency`),
  CONSTRAINT `fk_dreamtoken_rate_currency` FOREIGN KEY (`id_currency`) REFERENCES `currency` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dreamtoken_rate`
--

LOCK TABLES `dreamtoken_rate` WRITE;
/*!40000 ALTER TABLE `dreamtoken_rate` DISABLE KEYS */;
INSERT INTO `dreamtoken_rate` VALUES (1,'2022-01-01 00:00:00','2022-12-31 23:59:59',0.150000000000,0.010000000000,1),(2,'2023-01-01 00:00:00','2023-12-31 23:59:59',0.200000000000,0.030000000000,1);
/*!40000 ALTER TABLE `dreamtoken_rate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ledger_dreamland`
--

DROP TABLE IF EXISTS `ledger_dreamland`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ledger_dreamland` (
  `id` int NOT NULL AUTO_INCREMENT,
  `insert_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fee` decimal(22,12) NOT NULL,
  `id_dreamtoken` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ledger_dreamland_dreamtoken_idx` (`id_dreamtoken`),
  CONSTRAINT `fk_ledger_dreamland_dreamtoken` FOREIGN KEY (`id_dreamtoken`) REFERENCES `ledger_dreamtoken` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ledger_dreamland`
--

LOCK TABLES `ledger_dreamland` WRITE;
/*!40000 ALTER TABLE `ledger_dreamland` DISABLE KEYS */;
/*!40000 ALTER TABLE `ledger_dreamland` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ledger_dreamtoken`
--

DROP TABLE IF EXISTS `ledger_dreamtoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ledger_dreamtoken` (
  `id` int NOT NULL AUTO_INCREMENT,
  `issue_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `quantity` decimal(22,12) NOT NULL,
  `conversion_to_currency_date` datetime DEFAULT NULL,
  `id_user` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_token_user_idx` (`id_user`),
  CONSTRAINT `fk_token_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ledger_dreamtoken`
--

LOCK TABLES `ledger_dreamtoken` WRITE;
/*!40000 ALTER TABLE `ledger_dreamtoken` DISABLE KEYS */;
/*!40000 ALTER TABLE `ledger_dreamtoken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ledger_user`
--

DROP TABLE IF EXISTS `ledger_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ledger_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `insert_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `entry_type` varchar(45) DEFAULT NULL,
  `token_amount` decimal(22,12) DEFAULT NULL,
  `currency_amount` decimal(22,12) DEFAULT NULL,
  `id_dreamtoken_rate` int NOT NULL,
  `id_user` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ledger_user_dreamtoken_rate_idx` (`id_dreamtoken_rate`),
  KEY `fk_ledger_user_user_idx` (`id_user`),
  CONSTRAINT `fk_ledger_user_dreamtoken_rate` FOREIGN KEY (`id_dreamtoken_rate`) REFERENCES `dreamtoken_rate` (`id`),
  CONSTRAINT `fk_ledger_user_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ledger_user`
--

LOCK TABLES `ledger_user` WRITE;
/*!40000 ALTER TABLE `ledger_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `ledger_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Massimo',0),(2,'Ram',0);
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

-- Dump completed on 2022-04-29 23:31:24
