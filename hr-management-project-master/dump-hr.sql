-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: localhost    Database: HR_MANAGEMENT
-- ------------------------------------------------------
-- Server version	5.7.24-0ubuntu0.18.04.1

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
-- Table structure for table `Appraisal`
--

DROP TABLE IF EXISTS `Appraisal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Appraisal` (
  `EID` varchar(10) NOT NULL,
  `Head_Of_Department` varchar(100) DEFAULT NULL,
  `Faculty_Mentor` varchar(100) DEFAULT NULL,
  KEY `EID` (`EID`),
  CONSTRAINT `Appraisal_ibfk_1` FOREIGN KEY (`EID`) REFERENCES `Employee` (`EID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Appraisal`
--

LOCK TABLES `Appraisal` WRITE;
/*!40000 ALTER TABLE `Appraisal` DISABLE KEYS */;
/*!40000 ALTER TABLE `Appraisal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Conference`
--

DROP TABLE IF EXISTS `Conference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Conference` (
  `EID` varchar(10) NOT NULL,
  `Conference_Topic` varchar(255) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Attended_OR_Conducted` varchar(100) DEFAULT NULL,
  `Start_Date` date NOT NULL,
  `End_Date` date NOT NULL,
  `Whether_Speaker` tinyint(1) NOT NULL,
  KEY `EID` (`EID`),
  CONSTRAINT `Conference_ibfk_1` FOREIGN KEY (`EID`) REFERENCES `Employee` (`EID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Conference`
--

LOCK TABLES `Conference` WRITE;
/*!40000 ALTER TABLE `Conference` DISABLE KEYS */;
/*!40000 ALTER TABLE `Conference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Employee` (
  `EID` varchar(10) NOT NULL,
  `Title` varchar(64) NOT NULL,
  `Name` varchar(64) NOT NULL,
  `Gender` varchar(5) NOT NULL,
  `DOB` date NOT NULL,
  `Date_Of_Joining` date NOT NULL,
  `Address` varchar(255) NOT NULL,
  `Type_of_employee` varchar(64) NOT NULL,
  `Highest_Degree` varchar(64) NOT NULL,
  `Designation` varchar(64) NOT NULL,
  `Phone_No` varchar(64) NOT NULL,
  `Contact_Info` varchar(64) NOT NULL,
  PRIMARY KEY (`EID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES ('11TS003','Dr.','Rohan Sharma','M','1970-05-21','2011-08-03','15, Vaishali Nagar, Jaipur, Rajasthan','Teaching Staff','Ph.D','Professor','9637254922','rohansharma@gmail.com'),('12NTS008','Mr.','Deendayal','M','1985-01-19','2012-03-16','The LNMIIT, Jaipur','Non Teaching Staff ','M.Com.','Accountant','6391208765','deendayal@gmail.com'),('13NTS003','Mr.','Manav Singh','M','1975-03-29','2013-11-24','3, Malviya Nagar, Jaipur, Rajasthan','Non Teaching Staff','B.Tech.','System Engineer','6497659209','manavsinghgmail.com'),('14TS009','Prof.','Dherraj Sharma','M','1979-06-11','2014-08-01','The LNMIIT, Jaipur','Teaching Staff','M.Tech.','Asst. Professor','9630969648','dheerajsharma@gmail.com'),('15NTS010','Mr.','Ashish Sharma','M','1980-04-23','2015-09-13','The LNMIIT, Jaipur','Non Teaching Staff ','B.Tech.','Jr. Office Superintendent','6786983833','ashishsharma@gmail.com'),('16TS001','Dr.','Stephen Strange','M','1975-08-10','2016-10-06','The Sanctum Sanctorum, Jaipur','Teaching Staff','Ph.D.','Professor','9988776655','drstrange@marvel.com'),('16TS112','Dr.','Raj Kumar','M','1980-05-21','2016-08-03','1234 Good Lane, Jaipur, Rajasthan','Teaching Staff','Ph.D','Assistant Professor','9875431984','rajkumar@gmail.com');
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LeaveRecord`
--

DROP TABLE IF EXISTS `LeaveRecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LeaveRecord` (
  `EID` varchar(10) NOT NULL,
  `CL` varchar(10) NOT NULL,
  `PL` varchar(10) NOT NULL,
  `CCL` varchar(10) NOT NULL,
  `ODL` varchar(10) NOT NULL,
  `OL` varchar(10) NOT NULL,
  PRIMARY KEY (`EID`),
  CONSTRAINT `LeaveRecord_ibfk_1` FOREIGN KEY (`EID`) REFERENCES `Employee` (`EID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LeaveRecord`
--

LOCK TABLES `LeaveRecord` WRITE;
/*!40000 ALTER TABLE `LeaveRecord` DISABLE KEYS */;
INSERT INTO `LeaveRecord` VALUES ('11TS003','2/4','5/16','4','3','4'),('12NTS008','0/4','0/16','0','0','0'),('13NTS003','1/4','5/16','2','4','9'),('14TS009','0/4','0/16','0','0','0'),('15NTS010','1/4','3/16','1','0','2'),('16TS001','0/4','0/16','0','0','0'),('16TS112','0/4','6/16','2','1','3');
/*!40000 ALTER TABLE `LeaveRecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Leave_Applications`
--

DROP TABLE IF EXISTS `Leave_Applications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Leave_Applications` (
  `AID` int(11) NOT NULL AUTO_INCREMENT,
  `EID` varchar(10) NOT NULL,
  `Application_Date` date NOT NULL,
  `Type_Of_Leave` varchar(64) NOT NULL,
  `Reason_For_Leave` varchar(255) NOT NULL,
  `Start_Date` date NOT NULL,
  `End_Date` date NOT NULL,
  `HOD_Status` tinyint(1) DEFAULT NULL,
  `Registrar_Status` tinyint(1) DEFAULT NULL,
  `DOFA_Status` tinyint(1) DEFAULT NULL,
  `Director_Status` tinyint(1) DEFAULT NULL,
  `Application_Status` tinyint(1) NOT NULL DEFAULT '2',
  `Urgent` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`AID`),
  KEY `EID` (`EID`),
  CONSTRAINT `Leave_Applications_ibfk_1` FOREIGN KEY (`EID`) REFERENCES `Employee` (`EID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Leave_Applications`
--

LOCK TABLES `Leave_Applications` WRITE;
/*!40000 ALTER TABLE `Leave_Applications` DISABLE KEYS */;
INSERT INTO `Leave_Applications` VALUES (2,'16TS112','2018-12-12','PL','Have to go to a family function.','2018-12-19','2018-12-21',1,3,0,1,1,0),(3,'13NTS003','2018-12-12','OL','I wanna go home!','2018-12-12','2018-12-15',3,1,3,1,1,0),(4,'13NTS003','2018-12-12','PL','Have to go to my child\'s school.','2018-12-12','2018-12-14',3,0,3,2,2,0),(5,'16TS112','2018-12-12','PL','Wedding','2018-12-19','2018-12-19',1,3,2,2,2,0),(6,'16TS112','2018-12-12','PL','Wedding','2018-12-19','2018-12-21',1,3,2,2,2,0);
/*!40000 ALTER TABLE `Leave_Applications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Non_Teaching_staff`
--

DROP TABLE IF EXISTS `Non_Teaching_staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Non_Teaching_staff` (
  `EID` varchar(10) NOT NULL,
  `Office` varchar(64) NOT NULL,
  KEY `EID` (`EID`),
  CONSTRAINT `Non_Teaching_staff_ibfk_1` FOREIGN KEY (`EID`) REFERENCES `Employee` (`EID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Non_Teaching_staff`
--

LOCK TABLES `Non_Teaching_staff` WRITE;
/*!40000 ALTER TABLE `Non_Teaching_staff` DISABLE KEYS */;
INSERT INTO `Non_Teaching_staff` VALUES ('13NTS003','IT Department'),('12NTS008','Accounts'),('15NTS010','Registrar\'s Office');
/*!40000 ALTER TABLE `Non_Teaching_staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Paper`
--

DROP TABLE IF EXISTS `Paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Paper` (
  `EID` varchar(10) NOT NULL,
  `Paper_Title` varchar(255) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `publicationDate` date NOT NULL,
  `Journals` varchar(255) DEFAULT NULL,
  `Whether_First_Author` tinyint(1) NOT NULL,
  KEY `EID` (`EID`),
  CONSTRAINT `Paper_ibfk_1` FOREIGN KEY (`EID`) REFERENCES `Employee` (`EID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Paper`
--

LOCK TABLES `Paper` WRITE;
/*!40000 ALTER TABLE `Paper` DISABLE KEYS */;
/*!40000 ALTER TABLE `Paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Teaching_Staff`
--

DROP TABLE IF EXISTS `Teaching_Staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Teaching_Staff` (
  `EID` varchar(10) NOT NULL,
  `Department` varchar(64) NOT NULL,
  `Research_Topics` varchar(255) DEFAULT NULL,
  `Committees` varchar(255) DEFAULT NULL,
  KEY `EID` (`EID`),
  CONSTRAINT `Teaching_Staff_ibfk_1` FOREIGN KEY (`EID`) REFERENCES `Employee` (`EID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Teaching_Staff`
--

LOCK TABLES `Teaching_Staff` WRITE;
/*!40000 ALTER TABLE `Teaching_Staff` DISABLE KEYS */;
INSERT INTO `Teaching_Staff` VALUES ('16TS112','CSE','Cloud Computing','AC-SAC'),('11TS003','ECE','VLSI','AC-AAC'),('16TS001','ME','Fluids','N/A'),('14TS009','ME','Mechanical Systems','AC');
/*!40000 ALTER TABLE `Teaching_Staff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-04 17:46:41
