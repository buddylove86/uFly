CREATE DATABASE  IF NOT EXISTS `ufly` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ufly`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ufly
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking` (
  `BookingNumber` int(11) NOT NULL AUTO_INCREMENT,
  `FlightNumber` varchar(10) NOT NULL,
  `Price` decimal(10,2) NOT NULL,
  `CabinClass` varchar(20) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Insurance` bit(1) NOT NULL,
  PRIMARY KEY (`BookingNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,'NZ250',144.80,'Prestige',2,'');
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight` (
  `FlightNumber` varchar(10) NOT NULL,
  `DepartureAirport` varchar(5) NOT NULL,
  `DestinationAirport` varchar(5) NOT NULL,
  `Price` decimal(10,2) NOT NULL,
  `DateTime` datetime NOT NULL,
  `Plane` varchar(20) NOT NULL,
  `SeatsTaken` int(11) NOT NULL,
  PRIMARY KEY (`FlightNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES ('NZ110','AKL','WLG',50.00,'2017-05-19 00:00:00','BOEING737',205),('NZ120','AKL','WLG',50.00,'2017-05-01 00:00:00','AIRBUSA350',267),('NZ230','CHC','ZQN',52.00,'2017-05-10 00:00:00','AIRBUSA350',269),('NZ250','ZQN','DUD',52.00,'2017-05-17 00:00:00','BOEING747',455),('NZ310','DUD','ROT',76.00,'2017-05-12 00:00:00','AIRBUSA350',261),('NZ330','ROT','AKL',50.00,'2017-05-23 00:00:00','BOEING737',210),('NZ450','CHC','ZQN',52.00,'2017-05-25 00:00:00','BOEING747',458),('NZ500','DUD','AKL',76.00,'2017-05-25 00:00:00','BOEING747',452);
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `City` varchar(45) NOT NULL,
  `AirportCode` varchar(5) NOT NULL,
  PRIMARY KEY (`AirportCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES ('Auckland','AKL'),('Christchurch','CHC'),('Dunedin','DUD'),('Rotorua','ROT'),('Wellington','WLG'),('Queenstown','ZQN');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-15  9:03:27
