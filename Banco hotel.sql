CREATE DATABASE  IF NOT EXISTS `hotel` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `hotel`;
-- MySQL dump 10.16  Distrib 10.1.31-MariaDB, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: hotel
-- ------------------------------------------------------
-- Server version	10.1.31-MariaDB

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
-- Table structure for table `apartamento`
--

DROP TABLE IF EXISTS `apartamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apartamento` (
  `idApartamento` int(11) NOT NULL AUTO_INCREMENT,
  `valor` double DEFAULT NULL,
  `disponibilidade` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idApartamento`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartamento`
--

LOCK TABLES `apartamento` WRITE;
/*!40000 ALTER TABLE `apartamento` DISABLE KEYS */;
INSERT INTO `apartamento` VALUES (1,50,'Indisponivel'),(2,50,'Indisponivel'),(3,50,'Indisponivel'),(4,50,'Indisponivel'),(5,50,'Indisponivel'),(6,50,'Indisponivel'),(7,50,'Indisponivel'),(8,50,'Indisponivel'),(9,50,'Indisponivel'),(10,50,'Indisponivel'),(11,50,'Indisponivel'),(12,50,'Indisponivel'),(13,50,'Indisponivel'),(14,50,'Indisponivel'),(15,70,'Indisponivel'),(16,70,'Indisponivel'),(17,70,'Indisponivel'),(18,70,'Indisponivel'),(19,70,'Indisponivel'),(20,70,'Indisponivel'),(21,70,'Indisponivel'),(22,70,'Indisponivel'),(23,70,'Indisponivel'),(24,70,'Indisponivel'),(25,70,'Indisponivel'),(26,70,'Indisponivel'),(27,70,'Indisponivel'),(28,70,'Indisponivel'),(29,70,'Indisponivel'),(30,70,'Indisponivel'),(31,70,'Indisponivel'),(32,70,'Indisponivel'),(33,70,'Indisponivel'),(34,70,'Indisponivel'),(35,70,'Indisponivel'),(36,70,'Disponivel'),(37,70,'Disponivel'),(38,70,'Disponivel'),(39,70,'Disponivel'),(40,70,'Disponivel'),(41,70,'Disponivel'),(42,70,'Disponivel');
/*!40000 ALTER TABLE `apartamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hospedagem`
--

DROP TABLE IF EXISTS `hospedagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hospedagem` (
  `idHospedagem` int(11) NOT NULL AUTO_INCREMENT,
  `dataEntrada` date DEFAULT NULL,
  `dataSaida` date DEFAULT NULL,
  `valorHospedagem` double DEFAULT NULL,
  `numeroPessoa` int(11) DEFAULT NULL,
  `hospede_idHospede` int(11) DEFAULT NULL,
  `apartamento_idApartamento` int(11) DEFAULT NULL,
  `spg` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idHospedagem`),
  KEY `fk_hospedagem_hospede_idx` (`hospede_idHospede`),
  KEY `fk_hospedagem_apartamento1_idx` (`apartamento_idApartamento`),
  CONSTRAINT `fk_hospedagem_apartamento1` FOREIGN KEY (`apartamento_idApartamento`) REFERENCES `apartamento` (`idApartamento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hospedagem_hospede` FOREIGN KEY (`hospede_idHospede`) REFERENCES `hospede` (`idHospede`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospedagem`
--

LOCK TABLES `hospedagem` WRITE;
/*!40000 ALTER TABLE `hospedagem` DISABLE KEYS */;
INSERT INTO `hospedagem` VALUES (1,'2019-10-08','2019-10-09',50,1,1,1,'Não pago'),(2,'2019-10-08','2019-10-08',50,1,1,2,'Não pago'),(3,'2019-10-12','2019-10-13',1,1,1,3,'Não pago'),(4,'2019-10-13','2019-10-14',1,1,1,4,'Não pago'),(5,'2019-10-19','2019-10-19',30,1,33,7,'Não pago'),(6,'2019-10-19','2019-10-19',30,1,33,5,'Não pago'),(7,'2019-10-19','2019-10-19',30,1,1,6,'Não pago'),(25,'2019-10-21','2019-10-22',30,1,1,20,'Não pago'),(26,'2019-10-21','2019-10-22',30,1,1,21,'Não pago'),(27,'2019-10-21','2019-10-22',30,1,1,22,'Não pago'),(28,'2019-10-21','2019-10-22',30,1,1,23,'Não pago'),(29,'2019-10-21','2019-10-22',30,1,1,25,'Não pago'),(30,'2019-10-21','2019-10-22',30,1,1,24,'Não pago'),(31,'2019-11-01','2019-11-02',30,1,1,30,'Não pago'),(32,'2019-11-29','2019-11-30',30,1,1,31,'Não pago'),(33,'2019-12-01','2019-12-02',30,1,1,32,'Não pago'),(34,'2019-12-06','2019-12-07',30,1,1,33,'Não pago'),(35,'2019-12-06','2019-12-07',30,1,35,34,'Não pago'),(36,'2019-12-06','2019-12-07',55,1,39,35,'Pago');
/*!40000 ALTER TABLE `hospedagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hospede`
--

DROP TABLE IF EXISTS `hospede`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hospede` (
  `idHospede` int(11) NOT NULL AUTO_INCREMENT,
  `nomeHospede` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idHospede`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospede`
--

LOCK TABLES `hospede` WRITE;
/*!40000 ALTER TABLE `hospede` DISABLE KEYS */;
INSERT INTO `hospede` VALUES (1,'Dionatan pontes'),(2,'2as'),(3,'a2'),(4,'a2'),(5,'aa222'),(6,'sd3'),(7,'22222'),(8,'22'),(9,'2222'),(10,'a33'),(11,'1a'),(12,'adsadsad'),(13,'adsadsad111'),(14,'add3'),(15,'dgfd4'),(16,'aa2'),(17,'fsdf4'),(18,'aaaaa'),(19,'aaaaa222'),(20,'cvce22'),(21,'dsfdsaf2'),(22,'dsfdsaf233333333333'),(23,'ewrrwe222'),(24,'dione'),(25,'dione23'),(26,'dione'),(27,'dione23'),(28,'ddsd4'),(29,''),(30,'fds4354354'),(31,'dione'),(32,'dione'),(33,'joao'),(34,'Teste'),(35,'Juliana'),(36,'dione'),(37,'dione'),(38,'dfsdsd'),(39,'Ana');
/*!40000 ALTER TABLE `hospede` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-06 16:00:04
