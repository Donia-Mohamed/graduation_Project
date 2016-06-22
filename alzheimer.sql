CREATE DATABASE  IF NOT EXISTS `alzheimer` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `alzheimer`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: alzheimer
-- ------------------------------------------------------
-- Server version	5.7.9-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `familyposition`
--

DROP TABLE IF EXISTS `familyposition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `familyposition` (
  `family_position_id` int(11) NOT NULL,
  `family_position_value` varchar(50) NOT NULL,
  PRIMARY KEY (`family_position_id`),
  UNIQUE KEY `family_position_value` (`family_position_value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `familyposition`
--

LOCK TABLES `familyposition` WRITE;
/*!40000 ALTER TABLE `familyposition` DISABLE KEYS */;
INSERT INTO `familyposition` VALUES (20,'Aunt (Father side)'),(21,'Aunt (Mother side)'),(7,'Brother'),(39,'Brother-in-law'),(25,'Cousin (female) - (Mother side)'),(24,'Cousin (female)- (Father side)'),(22,'Cousin (male) -(Father side)'),(23,'Cousin (male) -(Mother side)'),(6,'Daughter'),(38,'Daughter-in-law'),(3,'Father'),(35,'Father-in-law'),(17,'Grandchildren'),(16,'Granddaughter'),(13,'Grandfather'),(14,'Grandmother'),(15,'Grandson'),(47,'Half-brother'),(48,'Half-sister'),(9,'Husband'),(4,'Mother'),(36,'Mother-in-law'),(31,'Nephew (brother\'s son)'),(32,'Nephew (sister\'s son)'),(33,'Niece (brother\'s daughter)'),(34,'Niece (sister\'s daughter)'),(8,'Sister'),(40,'Siter-in-law'),(5,'Son'),(37,'Son-in-law'),(46,'Stepbrother'),(44,'Stepdaughter'),(41,'Stepfather'),(42,'Stepmother'),(45,'Stepsister'),(43,'Stepson'),(18,'Uncle (Father side)'),(19,'Uncle (Mother side)'),(10,'Wife');
/*!40000 ALTER TABLE `familyposition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `memories`
--

DROP TABLE IF EXISTS `memories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `memories` (
  `memories_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) NOT NULL,
  `relative_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`memories_id`),
  KEY `patient_id` (`patient_id`),
  KEY `relative_id` (`relative_id`),
  CONSTRAINT `memories_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `memories_ibfk_2` FOREIGN KEY (`relative_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `memories`
--

LOCK TABLES `memories` WRITE;
/*!40000 ALTER TABLE `memories` DISABLE KEYS */;
INSERT INTO `memories` VALUES (1,1,NULL),(2,1,NULL),(3,1,NULL),(4,2,NULL),(8,1,NULL),(12,1,NULL),(13,1,NULL),(16,1,NULL);
/*!40000 ALTER TABLE `memories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `memory`
--

DROP TABLE IF EXISTS `memory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `memory` (
  `memory_id` int(11) NOT NULL,
  `memory_text` varchar(400) DEFAULT NULL,
  `image_url` varchar(200) DEFAULT NULL,
  `video_url` varchar(200) DEFAULT NULL,
  `date_time` varchar(45) NOT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `address` varchar(400) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `patient_id` int(11) NOT NULL,
  PRIMARY KEY (`memory_id`),
  KEY `memory_id` (`memory_id`),
  CONSTRAINT `memory_ibfk_1` FOREIGN KEY (`memory_id`) REFERENCES `memories` (`memories_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `memory`
--

LOCK TABLES `memory` WRITE;
/*!40000 ALTER TABLE `memory` DISABLE KEYS */;
INSERT INTO `memory` VALUES (1,'welcome in my app','dodo@gmail.com1.jpg',NULL,'2016-02-10 08:15:6',NULL,NULL,NULL,NULL,NULL,1),(2,'my name is donia',NULL,NULL,'2016-03-14  02:15:3',NULL,NULL,NULL,NULL,NULL,1),(3,'IRemember',NULL,NULL,'2016-03-14  02:15:3',NULL,NULL,NULL,NULL,NULL,1),(4,'nihal nonna nihoo :D',NULL,NULL,'2016-02-10 08:15:6',NULL,NULL,NULL,NULL,NULL,2),(8,'hello',NULL,NULL,'2016-02-10 08:15:6',NULL,NULL,NULL,NULL,NULL,1),(13,'me',NULL,NULL,'2016-06-18 14:30',NULL,NULL,NULL,NULL,NULL,1),(16,'dono',NULL,NULL,'2016-06-19 13:55',NULL,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `memory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relationship`
--

DROP TABLE IF EXISTS `relationship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relationship` (
  `relation_id` int(11) NOT NULL AUTO_INCREMENT,
  `relative_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `family_position_id` int(11) NOT NULL,
  PRIMARY KEY (`relative_id`,`patient_id`,`relation_id`),
  UNIQUE KEY `relation_id` (`relation_id`),
  KEY `relative_id` (`relative_id`),
  KEY `patient_id` (`patient_id`),
  KEY `family_position_id` (`family_position_id`),
  CONSTRAINT `relationship_ibfk_1` FOREIGN KEY (`relative_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `relationship_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `relationship_ibfk_3` FOREIGN KEY (`family_position_id`) REFERENCES `familyposition` (`family_position_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relationship`
--

LOCK TABLES `relationship` WRITE;
/*!40000 ALTER TABLE `relationship` DISABLE KEYS */;
INSERT INTO `relationship` VALUES (3,4,1,6),(1,3,1,7),(2,3,2,7),(4,7,1,25);
/*!40000 ALTER TABLE `relationship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `relative_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `family_position_id` int(11) NOT NULL,
  PRIMARY KEY (`relative_id`,`patient_id`,`request_id`),
  UNIQUE KEY `request_id` (`request_id`),
  KEY `request_id_2` (`request_id`),
  KEY `patient_id` (`patient_id`),
  KEY `family_position_id` (`family_position_id`),
  CONSTRAINT `request_ibfk_1` FOREIGN KEY (`relative_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `request_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `request_ibfk_3` FOREIGN KEY (`family_position_id`) REFERENCES `familyposition` (`family_position_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,3,6,6),(2,3,5,21);
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trusted`
--

DROP TABLE IF EXISTS `trusted`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trusted` (
  `patient_id` int(11) NOT NULL,
  `relative_id` int(11) NOT NULL,
  PRIMARY KEY (`patient_id`),
  KEY `trusted_ibfk_1_idx` (`patient_id`,`relative_id`),
  KEY `rfk_idx` (`relative_id`),
  CONSTRAINT `pfk` FOREIGN KEY (`patient_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rfk` FOREIGN KEY (`relative_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trusted`
--

LOCK TABLES `trusted` WRITE;
/*!40000 ALTER TABLE `trusted` DISABLE KEYS */;
INSERT INTO `trusted` VALUES (2,3);
/*!40000 ALTER TABLE `trusted` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `birthday` date NOT NULL,
  `gender` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone_num` varchar(15) NOT NULL,
  `home_num` varchar(15) DEFAULT NULL,
  `country` varchar(30) NOT NULL,
  `city` varchar(30) NOT NULL,
  `address` varchar(300) NOT NULL,
  `type` int(11) NOT NULL,
  `password` varchar(50) NOT NULL,
  `longitude` double DEFAULT NULL,
  `image_url` varchar(500) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `mac_Address` varchar(60) DEFAULT NULL,
  `user_token` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'donia','mohamed','1994-04-18',0,'dodo@gmail.com','+201002416766','0643222819','egypt','ismailia','el 5amsa',1,'tXFqyKae5bg6DwE0k1nDTA==',32.2685713,'dodo@gmail.com.jpg',30.6208978,'A4:44:D1:0E:B6:5B','cw5dMIOxhxg:APA91bFhp5d9e8aAEalNUp4GYu8MeQ79wZufL_6XjUnRUHN4CLqcBNTrhK_e_Ompcd4LgGo8M-bCs5p5K7pCnOGOMaQ09p5WqyH0K3VSDsNq0gvSgMTd8mYbHJm2cpMLbEcR2ciGzlgn'),(2,'nihal','hassan','1993-09-20',0,'nihal@gmail.com','+201008101838','','egypt','sweez','sweez',1,'tXFqyKae5bg6DwE0k1nDTA==',NULL,'nihal@gmail.com.jpg',NULL,NULL,'dVAZ-Mvfr_U:APA91bGMCRjT5OUjyAJ3M2CQXkA9nraA-jjU4P5MKO2C_3SYpRNp0jnYvklvUCndKLLWQm5WxJP-3isXtbTIMviCfiifkJp8luYleq2QJNJyvhRUS-0Yc-RXmUJHGaEXs6jwHMDsTRoI'),(3,'mohamed','atef','1993-01-25',1,'atef@gmail.com','+201226487475',NULL,'egypt','portsaid','portsaid',0,'tXFqyKae5bg6DwE0k1nDTA==',32.2685713,'atef@gmail.com.jpg',30.6208978,'BC:6E:64:A4:E2:3E','d-cS3kn4PVk:APA91bH-dPf23nOGmHgh_0BaidD4vGxMvPowReJS4glYefEFiLDDlhIBXdS9sQwhDxmirAYUa0p0-pgAE8lxJhte6teyVmd8cfjXjlcWbMdbN2ao9ZazZMBEuNT76WjKiMJmafsO_zi2'),(4,'doaa','ismail','1993-01-18',0,'doaa@gmail.com','+201121050879',NULL,'egypt','zagazig','zagazig',0,'tXFqyKae5bg6DwE0k1nDTA==',NULL,'doaa@gmail.com.jpg',NULL,NULL,NULL),(5,'safaa','badr','1992-07-21',0,'safaa@gmail.com','+201002416766',NULL,'egypt','el asher','10 th rmdan',1,'tXFqyKae5bg6DwE0k1nDTA==',NULL,'safaa@gmail.com.jpg',NULL,NULL,NULL),(6,'omnia','nour','1989-06-26',0,'omnia.nour7@gmail.com','+201285663496',NULL,'egypt','ismailia','dayry',1,'tXFqyKae5bg6DwE0k1nDTA==',NULL,'omnia.nour7@gmail.com',NULL,NULL,NULL),(7,'mohamed','ayad','1993-01-12',1,'ayad@gmail.com','+201274981164',NULL,'egypt','portsaid','P.S',0,'tXFqyKae5bg6DwE0k1nDTA==',NULL,'ayad@gmail.com.jpg',NULL,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-21 15:31:00
