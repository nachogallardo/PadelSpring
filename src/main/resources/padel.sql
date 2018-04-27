-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: padel
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
-- Table structure for table `clasificacion`
--

DROP TABLE IF EXISTS `clasificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clasificacion` (
  `idClasificacion` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) DEFAULT NULL,
  `idTorneo` int(11) DEFAULT NULL,
  `puntos` int(11) DEFAULT NULL,
  `partJugados` int(11) DEFAULT NULL,
  PRIMARY KEY (`idClasificacion`),
  KEY `idUsu_idx` (`idUsuario`),
  KEY `idTorneo_idx` (`idTorneo`),
  CONSTRAINT `idTorneo` FOREIGN KEY (`idTorneo`) REFERENCES `torneo` (`idTorneo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idUsu` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clasificacion`
--

LOCK TABLES `clasificacion` WRITE;
/*!40000 ALTER TABLE `clasificacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `clasificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagos`
--

DROP TABLE IF EXISTS `pagos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagos` (
  `idPago` int(11) NOT NULL AUTO_INCREMENT,
  `ultimoPago` date DEFAULT NULL,
  `idJugador` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPago`),
  KEY `idJugador_idx` (`idJugador`),
  CONSTRAINT `idJugador` FOREIGN KEY (`idJugador`) REFERENCES `usuarios` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos`
--

LOCK TABLES `pagos` WRITE;
/*!40000 ALTER TABLE `pagos` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partidos`
--

DROP TABLE IF EXISTS `partidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partidos` (
  `idPartido` int(11) NOT NULL AUTO_INCREMENT,
  `idJugador1` int(11) DEFAULT NULL,
  `idJugador2` int(11) DEFAULT NULL,
  `idJugador3` int(11) DEFAULT NULL,
  `idJugador4` int(11) DEFAULT NULL,
  `idGanador1` int(11) DEFAULT NULL,
  `idGanador2` int(11) DEFAULT NULL,
  `fechaPartido` datetime DEFAULT NULL,
  `numJornada` int(11) DEFAULT NULL,
  `idPista` int(11) DEFAULT NULL,
  `idTorneo1` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPartido`),
  KEY `idJug1_idx` (`idJugador1`),
  KEY `idJug2_idx` (`idJugador2`),
  KEY `idJug3_idx` (`idJugador3`),
  KEY `idJug4_idx` (`idJugador4`),
  KEY `idPista_idx` (`idPista`),
  KEY `idTorneo_idx` (`idTorneo1`),
  KEY `idTorne_idx` (`idTorneo1`),
  CONSTRAINT `idJug1` FOREIGN KEY (`idJugador1`) REFERENCES `usuarios` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idJug2` FOREIGN KEY (`idJugador2`) REFERENCES `usuarios` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idJug3` FOREIGN KEY (`idJugador3`) REFERENCES `usuarios` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idJug4` FOREIGN KEY (`idJugador4`) REFERENCES `usuarios` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idPista` FOREIGN KEY (`idPista`) REFERENCES `pistas` (`idPista`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idTorneo1` FOREIGN KEY (`idTorneo1`) REFERENCES `torneo` (`idTorneo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partidos`
--

LOCK TABLES `partidos` WRITE;
/*!40000 ALTER TABLE `partidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `partidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pistas`
--

DROP TABLE IF EXISTS `pistas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pistas` (
  `idPista` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPista`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pistas`
--

LOCK TABLES `pistas` WRITE;
/*!40000 ALTER TABLE `pistas` DISABLE KEYS */;
INSERT INTO `pistas` VALUES (7,'altair'),(8,'alcala'),(9,'sevilla'),(10,'madrid');
/*!40000 ALTER TABLE `pistas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `torneo`
--

DROP TABLE IF EXISTS `torneo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `torneo` (
  `idTorneo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idTorneo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `torneo`
--

LOCK TABLES `torneo` WRITE;
/*!40000 ALTER TABLE `torneo` DISABLE KEYS */;
/*!40000 ALTER TABLE `torneo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `tipoUsuario` int(11) DEFAULT NULL,
  `contrasenia` varchar(45) CHARACTER SET keybcs2 DEFAULT NULL,
  `telefono` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (4,'admin','admin@admin.com',1,'εó°Ó`č\"UzU│═tŤh',678987657),(5,'prueba','prueba@gmail.es',2,'εó°Ó`č\"UzU│═tŤh',678876567),(6,'otroUsuario','otro@otro.com',2,'εó°Ó`č\"UzU│═tŤh',678987876);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-27 16:48:44
