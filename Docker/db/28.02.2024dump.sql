-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: drawtivity_db
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `points`
--

DROP TABLE IF EXISTS `points`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `points` (
  `point_id` int NOT NULL AUTO_INCREMENT,
  `task_id` int NOT NULL,
  `x` float DEFAULT NULL,
  `y` float DEFAULT NULL,
  PRIMARY KEY (`point_id`),
  KEY `task_id_idx` (`task_id`),
  CONSTRAINT `task_id` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `points`
--

LOCK TABLES `points` WRITE;
/*!40000 ALTER TABLE `points` DISABLE KEYS */;
INSERT INTO `points` VALUES (1,2,12.3,12.3),(2,8,10,10),(3,8,15,15),(4,8,20,20),(5,8,25,25),(6,8,10,10),(7,8,15,15),(8,8,20,20),(9,8,25,25),(14,12,10,10),(15,12,15,15),(18,9,20,20),(19,9,25,25),(20,13,10,10),(21,13,15,15);
/*!40000 ALTER TABLE `points` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tasks` (
  `task_id` int NOT NULL AUTO_INCREMENT,
  `owner_id` int DEFAULT NULL,
  `title` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `category` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `image` blob NOT NULL,
  `deviation` float NOT NULL,
  PRIMARY KEY (`task_id`),
  KEY `idx_tasks_task_id` (`task_id`),
  KEY `owner_id_idx` (`owner_id`),
  CONSTRAINT `owner_id` FOREIGN KEY (`owner_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (2,2,'test1','test1','test1','test1',_binary 'test1',33.12),(4,2,'test1','test1','test1','test1',_binary 'test1',33.12),(5,2,'test1','test1','test1','test1',_binary 'test1',3),(8,2,'test1','test1','ABOBA','test1',_binary 'test1',3),(9,2,'River','Find the river on map','test1','test1',_binary 'test1',3),(12,6,'Brain','Select brain tumor','Medicine','test1',_binary 'test1',3),(13,4,'testTitle','testDescription','testCategory','testType',_binary 'âPNG\r\n\Z\n\0\0\0\rIHDR\0\0\0\0\0\0\0\0\0\‡w=¯\0\0\0sBIT|dà\0\0\0	pHYs\0\0\0¶\0\0\0¶\›}ˇ8\0\0\0tEXtSoftware\0www.inkscape.orgõ\Ó<\Z\0\0BIDATHâµñOlE\∆3ªõ]\€]o\\R\'¢î©\…%TÚ†V®†T™ä(HTäPß\"\Ë° ıáˆJ/ z!T	$\"Ñ\‘CEép q§Ü™ƒüpN«ç\Ì\ÿY\Ô\„êÿõ\∆Òùfˆ{˚Ω˜f\ﬁ{ªBkM\«\ﬂzAX˙u3\ÿ\÷–∫\Ôç\Î2\0hÚ\Â[I\«/D¨f\’hh_|ò∫Òﬁóa:¢\Ì`Ò¬Ö®YqÜ\Ï´\œ—óïœß[\r›ßVà=Y†˙]Çµo˜oíØ$\Ì\⁄\ÁK\Áps_Ωê∫~Ω∂\È†8~\È9%\ƒÄã\‘\À\“÷ûZófg4Ø-\0p\Ô£C]ë àj™Ü(£\ƒC¿ö\‘˙•\‰çk_ã\≈Ò∑üu∞B\œjˆü/\0∞Ú\Ÿ\–n¶\0>\»1T∫q\0!{±⁄Ñ*m\"Ñ\ÀΩ]å~sMØ Ñ+µ`ä<Ù\r÷ëë\0	\Ë¨˜†ÆµL\…\‘\Â˘;\ÓâµõyÙx9tΩ\‹+*uy˛é\ƒ≤±Ã™\·û\\Fò\·â8\√úcïˆ\«*8√ïP[aj‹ì\À\ƒ2´~ê5*É\ƒ“´\ÿG™Tß4Ê£®™Ä\·6ÒN-u	yßñ¡Z´öe,¿>\\#ñ+a&\⁄w•2&J§≠ΩôÒ∆ä\0¯Eõç_¢Xè¨#\Ï\Ó∂\¬;Û\'˛o˙´a%\›\È(ë:ˆ.ê\n\Õ˜øcqoï˝/ \—\‰ˇ7uM\ﬁD\ÍY¥8\€…Ωˇ\…4?˝Z\"w\Ë˜^ÑÑ\Á\0P*\◊)ï\◊Y-\◊)›ØsÙ\—oN\‰B\¬◊≥&Z\Ê\√\⁄r°P¶Z€†Z\€\‡˜?\Ó?0–Ö\¬}°e^b3Ä\ﬂ\…\rx†h∂>ñ1#ù,¢\≈\’Nˆ\‹\È‚ÆΩ´x‹µ9wz§õ˙\n£ì\≈VâáØ\0s[˘˛∏\√≈âb;äàqq\"G\‹\È§\Ê–ÉW[~\⁄sn\Ê\Ãq§1M\«\Ën6S\ﬂ¸\Ã?.±p∑u÷á=F?¿≥O¡4ª*\›G9≤7oow\00˚¸¿µN\'{Ä\\\"˝\’\Ì¢\Î£\ﬂ\ \‰c\‡â=äœ°ÇW€ë∑\—\›\…Ÿõ∑!ïEãw	©Æ–®Ö~R\ŸNq\À`+æ1âd*Û˜P\‹¸mi5®\Ãc3åNwí¯#ê3˙7\Ô\’\0\0\0\0IENDÆB`Ç',12.4);
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'Ilya','Tsygankov','test@test.com','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),(4,'ABOBA','TEST','TEST@TEST.TEST','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),(5,'Roman','Hog','romanhog@gmail.com','$2a$10$TLqteqTqCSuqlNz7xZkb6exOkGVdLgVPcGpXMHApnEO8lofWeFI7S'),(6,'John','Magly','johnmagly@gmail.com','$2a$10$rzlHd2f1wyjlkiKm7I2a3OocRnz4UnhS3eVrcRlhYmOavCKmqFs4q'),(7,'Mark','Brown','markbwrown@gmail.com','$2a$10$bofeN4aqNg3u36WIf8i3C.JPon9jNJPXWq6TJLSFPBCjshL7P89EK'),(8,'Joseph','Miller','josephmiller@gmail.com','$2a$10$TLYAL3TMRXumh4HNaAlqGuVfZw6bbAxPgJnjl9g2GBCrnxLlJV15O'),(9,'ttt','ttt','tttt@gmail.com','$2a$10$kWeRxfhM3zElE3YZWtAQo.5PctlaesUYdy6xmBIMD9LiMBoA2sTTu'),(10,'ttt','ttt','33333@gmail.com','$2a$10$xd0SWCpLT8oskG5rP.aQbelg6OpyOhbHva6QkDvQM.VX.EAu0GC7q');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id_idx` (`role_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(2,2);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-28 22:10:18
