-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: atams
-- ------------------------------------------------------
-- Server version	5.7.17-log

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

CREATE DATABASE IF NOT EXISTS atams;
USE atams;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES ('admin01','123'),('admin02','456'),('admin03','111111'),('admin04','11111'),('admin05','6666'),('admin06','56789'),('admin07','888888'),('admin08','1231231231'),('admin09','6665555'),('admin10','87879878'),('admin11','5555555'),('admin12','675271'),('盟主儿子','23213213');
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('c900002112','离散数学'),('c900002123','使用JavaEE开发web服务'),('c900002221','马克思哲学'),('c900003212','高级程序设计语言'),('c900004325','高等数学'),('c900005422','adroid应用开发'),('c900005431','java程序设计'),('c900005442','大学英语'),('c900005554',' JavaEE应用集成JSF'),('c900008783','HTML网页设计');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evaluation` (
  `student_id` varchar(20) NOT NULL,
  `course_id` varchar(20) NOT NULL,
  `score` varchar(20) DEFAULT NULL,
  `term` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`student_id`,`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluation`
--

LOCK TABLES `evaluation` WRITE;
/*!40000 ALTER TABLE `evaluation` DISABLE KEYS */;
INSERT INTO `evaluation` VALUES ('20140087','c900002112','优','2014春'),('20140087','c900002123','优','2014春'),('20140087','c900002221','良','2014春'),('20140543','c900002123','良','2014秋'),('20140544','c900002221','差','2016春'),('20145626','c900003212','良','2015秋'),('20145643','c900004325','良','2015春'),('20145776','c900005422','良','2017春'),('20145776','c900005431','良','2016春'),('20147869','c900005442','优','2015春'),('20148765','c900005442','优','2014春'),('20149879','c900005554','良','2017春');
/*!40000 ALTER TABLE `evaluation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `url` text,
  `parameter` text,
  `time` datetime DEFAULT NULL,
  `length` int(11) DEFAULT NULL,
  `module` varchar(20) DEFAULT NULL,
  `method` varchar(20) DEFAULT NULL,
  `result` varchar(20) DEFAULT NULL,
  `message` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4799 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `score` (
  `student_id` varchar(20) NOT NULL,
  `course_id` varchar(20) NOT NULL,
  `score` int(11) DEFAULT NULL,
  `term` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`course_id`,`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES ('20140087','c900002112',80,'2014春'),('20140087','c900002123',89,'2014春'),('20140543','c900002123',92,'2014秋'),('20140087','c900002221',80,'2014春'),('20140544','c900002221',70,'2016春'),('20145626','c900003212',86,'2015秋'),('20145643','c900004325',93,'2015春'),('20145776','c900005422',68,'2017春'),('20145776','c900005431',77,'2016春'),('20147869','c900005442',98,'2015春'),('20148765','c900005442',90,'2014春'),('20149879','c900005554',60,'2017春');
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `clazz` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('20140087','123456','晓东','java6'),('20140543','857432','马小','java5'),('20140544','444345','冉晴','java10'),('20145626','2112121','小马','java3'),('20145643','222221121','小张','java1'),('20145776','4334352','小候','java6'),('20147869','2121121','小马','java2'),('20148765','2121112','小徐','java3'),('20148954','66644zzzz','小伍','java9'),('20149879','26754','小潘','java7');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_course`
--

DROP TABLE IF EXISTS `student_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(20) DEFAULT NULL,
  `course_id` varchar(20) DEFAULT NULL,
  `term` varchar(20) DEFAULT NULL,
  `information` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_course`
--

LOCK TABLES `student_course` WRITE;
/*!40000 ALTER TABLE `student_course` DISABLE KEYS */;
INSERT INTO `student_course` VALUES (8,'20140087','c900002112','2014春','1-18周星期一1.2节、星期三3.4节'),(9,'20140543','c900002123','2014秋','1-9周星期三1-4节     10-18周星期二1.2节、星期五1.2节'),(10,'20140544','c900002221','2016春','1-18周星期三3.4节、星期四1.2节'),(11,'20145626','c900003212','2015秋','1-9周星期一1-4节 10-18周星期二5.6节  1-18周单周星期三1.2节'),(12,'20145643','c900004325','2015春','1-18周星期一7.8节、星期二9.10节'),(13,'20145776','c900005422','2017春','1-18周星期四1-4节、星期五7.8节'),(14,'20145776','c900005431','2016春','单周1-18周星期三5.6节 双周星期二1.2节'),(15,'20147869','c900005442','2015春','1-18周星期三3.4节、星期四7.8节'),(16,'20148765','c900005442','2014春','1-18周星期一1.2节、星期二3.4节'),(17,'20149879','c900005554','2017春','1-9周星期一3.4节、10-18周星期四7.8节'),(18,'20140087','c900003212','2014春','1-18周星期五5-8节'),(19,'20140087','c900005422','2014春','单周1-9周星期二1.2节 1-18周 星期三1-4节');
/*!40000 ALTER TABLE `student_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `id` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('109611','543343','徐瑞杰'),('109612','12121121','小潘'),('109621','2222222','伍翔'),('109622','123212','少东'),('109625','123421','东东'),('109633','34342','Jesse'),('109644','211211','子男'),('109653','55343','张三'),('109668','433434','少帅'),('109673','554321','盟主'),('109678','1212112','Anna'),('109688','433432','透尖'),('109698','1212112','刘二'),('109699','5343242','马五');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_course`
--

DROP TABLE IF EXISTS `teacher_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_id` varchar(20) DEFAULT NULL,
  `course_id` varchar(20) DEFAULT NULL,
  `term` varchar(20) DEFAULT NULL,
  `information` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_course`
--

LOCK TABLES `teacher_course` WRITE;
/*!40000 ALTER TABLE `teacher_course` DISABLE KEYS */;
INSERT INTO `teacher_course` VALUES (7,'109611','c900002123','2015秋','1-9周星期一3.4节、星期二3.4节、星期五1.2节  10-18周星期三7.8节'),(8,'109612','c900003212','2016春','1-18周星期三1.2节、星期五5.6节'),(9,'109621','c900002221','2015秋','1-18周星期二1.2节、星期四1.2、3.4节'),(10,'109622','c900005442','2016春','1-18周星期一5.6节、星期二3.4节、单周星期三7.8节'),(11,'109633','c900008783','2016秋','1-9周星期一1.2节、星期二5.6节、10-18周星期三7.8节'),(12,'109644','c900005431','2017春','1-18周星期一7.8节、星期五1-4节'),(13,'109653','c900002112','2014秋','1-18周星期三5.6节、星期四5.6节'),(14,'109668','c900005422','2014春','1-18周星期二5.6节、星期四9.10节'),(15,'109625','c900005554','2016秋','1-16周星期四1-4节、星期五7.8节、17-18周星期一1-4节、星期二5-8节'),(16,'109673','c900002123','2017秋','1-18周星期五1-8节'),(17,'109611','c900002123','2014春','1-18周星期四3.4节、7.8节'),(18,'109611','c900005422','2015春','1-18周星期一3.4节、星期三3.4节   双周星期二1.2节');
/*!40000 ALTER TABLE `teacher_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'atams'
--

--
-- Dumping routines for database 'atams'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-04 14:52:17
