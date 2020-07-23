-- MySQL dump 10.13  Distrib 5.6.45, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: jspbeginner
-- ------------------------------------------------------
-- Server version	5.6.45-log

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
-- Table structure for table `board2`
--

DROP TABLE IF EXISTS `board2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `board2` (
  `num` int(29) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `pass` varchar(30) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `file` varchar(30) DEFAULT NULL,
  `re_ref` int(11) DEFAULT NULL,
  `re_lev` int(11) DEFAULT NULL,
  `re_seq` int(11) DEFAULT NULL,
  `readcount` varchar(30) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `ip` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board2`
--

LOCK TABLES `board2` WRITE;
/*!40000 ALTER TABLE `board2` DISABLE KEYS */;
INSERT INTO `board2` VALUES (1,'psm211','1234','렌트문의','렌트문의합니다',NULL,1,0,0,'4','2020-07-23 10:32:21',''),(3,'admin','1234','[답글] 렌트관련답글입니다','렌트카 문의는 051-123-4851\r\n연락부탁드립니다',NULL,1,1,1,'4','2020-07-23 10:42:34',''),(4,'psy','1234','보험문의','차량 보험적용에대해\r\n상세정보를 원합니다',NULL,4,0,0,'2','2020-07-23 10:59:41',''),(5,'psy','1234','문의사항','문의합니다',NULL,5,0,0,'1','2020-07-23 11:00:00',''),(6,'admin','1234','[답글] 답글입니다','답글입니다',NULL,5,1,1,'0','2020-07-23 11:01:06',''),(7,'admin','1234','[답글] 답글입니다','답글입니다',NULL,4,1,1,'0','2020-07-23 11:03:31',''),(8,'psm211','1234','문의합니다','문의합니다',NULL,8,0,0,'2','2020-07-23 11:08:32',''),(9,'psm211','1234','렌트차량변경','차량변경가능한지 문의합니다',NULL,9,0,0,'2','2020-07-23 11:08:51',''),(10,'psy','1234','문의','문의합니다',NULL,10,0,0,'4','2020-07-23 11:11:06',''),(11,'admin','1234','[답글] 답글입니다','답글입니다',NULL,10,1,1,'1','2020-07-23 11:13:47',''),(12,'admin','1234','[답글]답변드립니다','답변드립니다',NULL,9,1,1,'0','2020-07-23 11:14:28',''),(13,'admin','1234','[답글] 답글입니다','답글입니다',NULL,8,1,1,'0','2020-07-23 11:16:10',''),(14,'psm211','1234','렌트후기입니다','렌트후기입니다',NULL,14,0,0,'1','2020-07-23 11:27:05',''),(15,'admin','1234','[답글]후기감사합니다','',NULL,14,1,1,'3','2020-07-23 11:28:19',''),(16,'admin','1234','[답글]문의완료','문의완료입니다',NULL,1,2,2,'1','2020-07-23 11:31:01',''),(17,'admin','1234','[답글]문의완료','문의완료입니다',NULL,10,2,2,'0','2020-07-23 11:31:56','');
/*!40000 ALTER TABLE `board2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carlist`
--

DROP TABLE IF EXISTS `carlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carlist` (
  `carno` int(11) NOT NULL,
  `carname` varchar(50) NOT NULL,
  `carcompany` varchar(50) NOT NULL,
  `carprice` int(11) NOT NULL,
  `carusepeople` int(11) NOT NULL,
  `carinfo` varchar(500) NOT NULL,
  `carimg` varchar(50) NOT NULL,
  `carcategory` varchar(10) NOT NULL,
  PRIMARY KEY (`carno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carlist`
--

LOCK TABLES `carlist` WRITE;
/*!40000 ALTER TABLE `carlist` DISABLE KEYS */;
INSERT INTO `carlist` VALUES (1,'아반테','현대',80000,4,'이차량은 소형차량으로 잘나갑니다.','avante.jpg','Small'),(2,'카렌스','현대',90000,4,'이차량은 소형차량으로 잘나갑니다.','carens.jpg','Small'),(3,'카니발','기아',100000,9,'이차량은 소형차량으로 잘나갑니다.','canival.jpg','Small'),(4,'코란도','쌍용',110000,4,'이차량은 소형차량으로 잘나갑니다.','co.jpg','Small'),(101,'에쿠스','현대',120000,5,'이차량은 중형차량으로 잘나갑니다.','equus.jpg','Mid'),(102,'그렌져','현대',130000,5,'이차량은 중형차량으로 잘나갑니다.','grandeur.jpg','Mid'),(103,'k3','기아',140000,4,'이차량은 중형차량으로 잘나갑니다.','k3.jpg','Mid'),(104,'k5','기아',150000,4,'이차량은 중형차량으로 잘나갑니다.','k5.jpg','Mid'),(201,'k7','기아',160000,4,'이차량은 대형차량으로 잘나갑니다.','k7.jpg','Big'),(202,'k9','기아',170000,4,'이차량은 대형차량으로 잘나갑니다.','k9.jpg','Big'),(203,'말리부','GM',180000,4,'이차량은 대형차량으로 잘나갑니다.','malibu.jpg','Big'),(204,'bmw528i','bmw',190000,5,'이차량은 대형차량으로 잘나갑니다.','bmw.jpg','Big');
/*!40000 ALTER TABLE `carlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carorder`
--

DROP TABLE IF EXISTS `carorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carorder` (
  `orderid` int(30) NOT NULL,
  `carno` int(30) DEFAULT NULL,
  `carqty` int(30) DEFAULT NULL,
  `carreserveday` int(30) DEFAULT NULL,
  `carbegindate` varchar(50) DEFAULT NULL,
  `carins` int(30) DEFAULT NULL,
  `carwifi` int(30) DEFAULT NULL,
  `carnave` int(30) DEFAULT NULL,
  `carbabyseat` int(30) DEFAULT NULL,
  `memberphone` varchar(40) DEFAULT NULL,
  `memberpass` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carorder`
--

LOCK TABLES `carorder` WRITE;
/*!40000 ALTER TABLE `carorder` DISABLE KEYS */;
INSERT INTO `carorder` VALUES (1,1,2,1,'2020-08-01',1,1,1,1,'1234','1234'),(2,201,1,1,'2020-08-08',1,1,1,1,'1234','1234');
/*!40000 ALTER TABLE `carorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member2`
--

DROP TABLE IF EXISTS `member2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member2` (
  `id` varchar(12) NOT NULL,
  `pass` varchar(12) NOT NULL,
  `name` varchar(20) NOT NULL,
  `joinDate` date NOT NULL,
  `email` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member2`
--

LOCK TABLES `member2` WRITE;
/*!40000 ALTER TABLE `member2` DISABLE KEYS */;
INSERT INTO `member2` VALUES ('admin','1234','김상순','2020-07-23','kim@test.com','부산 강서구 가달2로 60','01012345678','01012345678'),('psm211','1234','박수민','2020-07-13','psm211@hanmail.net','부산 수영구 광안해변로 386','01012345678','01012345678'),('psy','1234','신유진','2020-07-23','abel@naver.com','서울 영등포구 국제금융로7길 27','01012345678','01012345678');
/*!40000 ALTER TABLE `member2` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-23 11:34:54
