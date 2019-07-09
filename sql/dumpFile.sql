CREATE DATABASE  IF NOT EXISTS `bubblegum` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bubblegum`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bubblegum
-- ------------------------------------------------------
-- Server version	5.7.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `chat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_bubble` bit(1) NOT NULL,
  `name` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (1,_binary '','BAUM'),(2,_binary '','Bubble Test'),(3,_binary '','Bubble Test');
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversation_access_key`
--

DROP TABLE IF EXISTS `conversation_access_key`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `conversation_access_key` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `chat_id` int(11) NOT NULL,
  `creation_date` bigint(20) NOT NULL,
  `hash` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_User_has_Chat_Chat2_idx` (`chat_id`),
  KEY `fk_User_has_Chat_User2_idx` (`user_id`),
  CONSTRAINT `fk_User_has_Chat_Chat2` FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Chat_User2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation_access_key`
--

LOCK TABLES `conversation_access_key` WRITE;
/*!40000 ALTER TABLE `conversation_access_key` DISABLE KEYS */;
INSERT INTO `conversation_access_key` VALUES (4,3,1,1561967765963,'dd2a6a0f-ce7f-41c7-8064-39bc144e4739-6057fd8a-6608-3334-9bc8-3410bdd82983'),(5,2,1,1562046127113,'614c7577-3ca8-4709-ae10-8cad634cd85b-29d5c02e-1a99-3eb9-be4a-03e35b64c2ac'),(6,5,2,1562657777119,'87dbbd22-e528-4de8-9cdd-b7c08accc8d4-4630ea30-460f-3947-8825-1bc9db6ede17'),(9,6,3,1562671608453,'50c2699e-b6d7-4179-a458-11bce8168870-75eca02f-14a4-31b8-8973-ac7b8efa71f7');
/*!40000 ALTER TABLE `conversation_access_key` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_verification_key`
--

DROP TABLE IF EXISTS `email_verification_key`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `email_verification_key` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `hash` text NOT NULL,
  `creation_date` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_Email_verification_key_User1_idx` (`user_id`),
  CONSTRAINT `fk_Email_verification_key_User1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_verification_key`
--

LOCK TABLES `email_verification_key` WRITE;
/*!40000 ALTER TABLE `email_verification_key` DISABLE KEYS */;
INSERT INTO `email_verification_key` VALUES (48,4,'67951e28-e450-4de2-9d3c-3e59433d21aa-84778407-50d2-35cb-8668-aa7abdd168a3',1561968987399),(49,5,'6d65b3b9-1d8e-4dac-8fb6-5d2d460c9845-5b0606bb-2ee7-3b86-8e72-0c5669f35acf',1562657614001),(50,6,'d0594f29-2112-4a17-a960-7ad384166a0c-5a01aed7-42f4-31e5-b4b9-60eb1143cb4b',1562658028891),(51,7,'06623f9b-175d-4abe-8a59-0c67bd6e5e0e-b0152dcb-c977-3b3e-b05a-9e63953f8469',1562658056563);
/*!40000 ALTER TABLE `email_verification_key` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invite`
--

DROP TABLE IF EXISTS `invite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `invite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_accepted` bit(1) NOT NULL,
  `sent_by_user` int(11) NOT NULL,
  `for_chat` int(11) NOT NULL,
  `invited_user` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Einladung_User_in_Chat1_idx` (`sent_by_user`,`for_chat`),
  KEY `fk_Einladung_User1_idx` (`invited_user`),
  CONSTRAINT `fk_Einladung_User1` FOREIGN KEY (`invited_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_Einladung_User_in_Chat1` FOREIGN KEY (`sent_by_user`, `for_chat`) REFERENCES `user_in_chat` (`user_id`, `chat_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invite`
--

LOCK TABLES `invite` WRITE;
/*!40000 ALTER TABLE `invite` DISABLE KEYS */;
INSERT INTO `invite` VALUES (1,_binary '',2,1,3),(3,_binary '\0',6,3,5);
/*!40000 ALTER TABLE `invite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` text NOT NULL,
  `sent_at` bigint(20) NOT NULL,
  `sent_by_user` int(11) NOT NULL,
  `sent_in_chat` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Nachricht_User_idx` (`sent_by_user`),
  KEY `fk_Nachricht_Chat1_idx` (`sent_in_chat`),
  CONSTRAINT `fk_Nachricht_Chat1` FOREIGN KEY (`sent_in_chat`) REFERENCES `chat` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_Nachricht_User` FOREIGN KEY (`sent_by_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'asd',1561967582832,2,1),(2,'asd',1561967588664,2,1),(3,'asd',1561967589335,2,1),(4,'asd',1561967590015,2,1),(5,'asd',1561967590560,2,1),(6,'asd',1561967591367,2,1),(7,'asd',1561967591878,2,1),(8,'asd',1561967592503,2,1),(9,'asd',1561967593527,2,1),(10,'asd',1561967594215,2,1),(11,'asd',1561967594511,2,1),(12,'asd',1561967594735,2,1),(13,'asd',1561967594935,2,1),(14,'asd',1561967595143,2,1),(15,'asd',1561967595327,2,1),(16,'asd',1561967595512,2,1),(17,'asd',1561967595719,2,1),(18,'dfg',1562671612754,6,3),(19,'dfgrhjk',1562671614161,6,3);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` text NOT NULL,
  `name` varchar(45) NOT NULL,
  `email_verified` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'Ich bin cool','nicola@schueepp.net','$­&’;-6+ø²Q¥)>iÞÑ4ïŒÝ;‘þâíŒ=:©','Nicola Schüepp',_binary ''),(3,'Hey there! I am chewing a Bubble!','nicola.schueepp@usterstrasse.ch','$­&’;-6+ø²Q¥)>iÞÑ4ïŒÝ;‘þâíŒ=:©','Chicken',_binary ''),(4,'Hey there! I am chewing a Bubble!','joshualuetzelschwab@gmail.com','$­&’;-6+ø²Q¥)>iÞÑ4ïŒÝ;‘þâíŒ=:©','JoshSpam',_binary '\0'),(5,'Hey there! I am chewing a Bubble!','test@test.com','$2a$12$6A9rvjduIl9iAlxjThHA9OhgSGuQKq9J7vEMd5aEbzE9T5p2xjMV6','test',_binary '\0'),(6,'Hey there! I am chewing a Bubble!','test1@test1.com','$2a$12$/vfQH5IL61dU3yBijfC3AuqqmJuVN9w3pV238miUQEUeZNsxAAAIa','test1',_binary '\0'),(7,'Hey there! I am chewing a Bubble!','test2@test2.com','$2a$12$dG7.Ysm4NdLr6pMHgNu4Nuu/k04ab4EJHjQKnGrAWymj7QdVf6jz6','test2',_binary '\0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_in_chat`
--

DROP TABLE IF EXISTS `user_in_chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_in_chat` (
  `user_id` int(11) NOT NULL,
  `chat_id` int(11) NOT NULL,
  `is_admin` bit(1) NOT NULL,
  PRIMARY KEY (`user_id`,`chat_id`),
  KEY `fk_User_has_Chat_Chat1_idx` (`chat_id`),
  KEY `fk_User_has_Chat_User1_idx` (`user_id`),
  CONSTRAINT `fk_user_has_chat_chat1` FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_chat_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_in_chat`
--

LOCK TABLES `user_in_chat` WRITE;
/*!40000 ALTER TABLE `user_in_chat` DISABLE KEYS */;
INSERT INTO `user_in_chat` VALUES (2,1,_binary ''),(3,1,_binary '\0'),(5,2,_binary ''),(6,3,_binary '');
/*!40000 ALTER TABLE `user_in_chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_read_message`
--

DROP TABLE IF EXISTS `user_read_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_read_message` (
  `user_id` int(11) NOT NULL,
  `message_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`message_id`),
  KEY `fk_User_has_Message_Message1_idx` (`message_id`),
  KEY `fk_User_has_Message_User1_idx` (`user_id`),
  CONSTRAINT `fk_User_has_Message_Message1` FOREIGN KEY (`message_id`) REFERENCES `message` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Message_User1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_read_message`
--

LOCK TABLES `user_read_message` WRITE;
/*!40000 ALTER TABLE `user_read_message` DISABLE KEYS */;
INSERT INTO `user_read_message` VALUES (2,1),(3,1),(2,2),(3,2),(2,3),(3,3),(2,4),(3,4),(2,5),(3,5),(2,6),(3,6),(2,7),(3,7),(2,8),(3,8),(2,9),(3,9),(2,10),(3,10),(2,11),(3,11),(2,12),(3,12),(2,13),(3,13),(2,14),(3,14),(2,15),(3,15),(2,16),(3,16),(2,17),(3,17),(6,18),(6,19);
/*!40000 ALTER TABLE `user_read_message` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-09 15:21:49
