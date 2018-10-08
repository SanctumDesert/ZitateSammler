CREATE DATABASE  IF NOT EXISTS `zitatedb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `zitatedb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: zitatedb
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.35-MariaDB

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
-- Table structure for table `tblaenderungen`
--

DROP TABLE IF EXISTS `tblaenderungen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblaenderungen` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` varchar(45) NOT NULL,
  `ZitatID` varchar(45) NOT NULL,
  `Datum` bigint(30) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblaenderungen`
--

LOCK TABLES `tblaenderungen` WRITE;
/*!40000 ALTER TABLE `tblaenderungen` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblaenderungen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblklassen`
--

DROP TABLE IF EXISTS `tblklassen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblklassen` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Klasse` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `Klasse_UNIQUE` (`Klasse`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblklassen`
--

LOCK TABLES `tblklassen` WRITE;
/*!40000 ALTER TABLE `tblklassen` DISABLE KEYS */;
INSERT INTO `tblklassen` VALUES (1,'12FIAE'),(2,'12FISI');
/*!40000 ALTER TABLE `tblklassen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblkurs`
--

DROP TABLE IF EXISTS `tblkurs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblkurs` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Lehrer` varchar(45) NOT NULL,
  `Kurs` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Kurs_UNIQUE` (`Kurs`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblkurs`
--

LOCK TABLES `tblkurs` WRITE;
/*!40000 ALTER TABLE `tblkurs` DISABLE KEYS */;
INSERT INTO `tblkurs` VALUES (1,'Gruening','LF6'),(2,'Schuster','LF5'),(3,'Gruening','Sport');
/*!40000 ALTER TABLE `tblkurs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbluser`
--

DROP TABLE IF EXISTS `tbluser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbluser` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nutzername` varchar(45) NOT NULL,
  `Vorname` varchar(45) NOT NULL,
  `Nachname` varchar(45) NOT NULL,
  `Passwort` varchar(70) NOT NULL,
  `Mail` varchar(45) NOT NULL,
  `Admin` tinyint(4) NOT NULL,
  `KlasseID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Nutzername_UNIQUE` (`Nutzername`),
  UNIQUE KEY `idx_tbluser_Mail` (`Mail`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbluser`
--

LOCK TABLES `tbluser` WRITE;
/*!40000 ALTER TABLE `tbluser` DISABLE KEYS */;
INSERT INTO `tbluser` VALUES (1,'Kuro','Fabian','Minx','9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08','test',1,1),(2,'Sanctum','Lukas','Manus','60303ae22b998861bce3b28f33eec1be758a213c86c93c076dbe9f558c11c752','test2',1,1),(4,'Tester','testVorname','testNachname','testPW','testMail',0,1);
/*!40000 ALTER TABLE `tbluser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblzitate`
--

DROP TABLE IF EXISTS `tblzitate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblzitate` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UrheberID` varchar(45) NOT NULL,
  `SprecherID` varchar(45) NOT NULL,
  `KursID` varchar(45) NOT NULL,
  `Datum` bigint(30) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblzitate`
--

LOCK TABLES `tblzitate` WRITE;
/*!40000 ALTER TABLE `tblzitate` DISABLE KEYS */;
INSERT INTO `tblzitate` VALUES (1,'1','1','1',20092018);
/*!40000 ALTER TABLE `tblzitate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'zitatedb'
--

--
-- Dumping routines for database 'zitatedb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-08 13:57:26
