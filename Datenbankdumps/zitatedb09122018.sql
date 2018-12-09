-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2018 at 06:48 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `zitatedb`
--

-- --------------------------------------------------------

--
-- Table structure for table `lehrer_kurs_map`
--

CREATE TABLE `lehrer_kurs_map` (
  `ID` int(11) NOT NULL,
  `LehrerID` int(11) NOT NULL COMMENT 'ForeignKey von Tabelle Lehrer',
  `KursID` int(11) NOT NULL COMMENT 'ForeignKey von Tabelle Kurs'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lehrer_kurs_map`
--

INSERT INTO `lehrer_kurs_map` (`ID`, `LehrerID`, `KursID`) VALUES
(1, 1, 1),
(2, 5, 2),
(3, 1, 3),
(4, 1, 5),
(5, 6, 6);

-- --------------------------------------------------------

--
-- Table structure for table `tblklassen`
--

CREATE TABLE `tblklassen` (
  `ID` int(11) NOT NULL,
  `Klasse` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblklassen`
--

INSERT INTO `tblklassen` (`ID`, `Klasse`) VALUES
(1, '12FIAE'),
(2, '12FISI');

-- --------------------------------------------------------

--
-- Table structure for table `tblkurs`
--

CREATE TABLE `tblkurs` (
  `ID` int(11) NOT NULL,
  `Kurs` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblkurs`
--

INSERT INTO `tblkurs` (`ID`, `Kurs`) VALUES
(5, '10FIAE16/17-LF6'),
(1, '10FIAE17/18-LF6'),
(2, '11FISI17/18-LF5'),
(6, '12FIAE17/18-LF11'),
(3, '12FIAE17/18-Sport');

-- --------------------------------------------------------

--
-- Table structure for table `tbllehrer`
--

CREATE TABLE `tbllehrer` (
  `id` int(11) NOT NULL,
  `name` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbllehrer`
--

INSERT INTO `tbllehrer` (`id`, `name`) VALUES
(1, 'Gruening'),
(3, 'Blam'),
(4, 'Gram'),
(5, 'Schuster'),
(6, 'Lahm');

-- --------------------------------------------------------

--
-- Table structure for table `tbluser`
--

CREATE TABLE `tbluser` (
  `ID` int(11) NOT NULL,
  `Deleted` tinyint(1) NOT NULL DEFAULT '0',
  `Nutzername` varchar(45) NOT NULL,
  `Vorname` varchar(45) NOT NULL,
  `Nachname` varchar(45) NOT NULL,
  `Passwort` varchar(70) NOT NULL,
  `Mail` varchar(45) NOT NULL,
  `Admin` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbluser`
--

INSERT INTO `tbluser` (`ID`, `Deleted`, `Nutzername`, `Vorname`, `Nachname`, `Passwort`, `Mail`, `Admin`) VALUES
(5, 0, 'manus12', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas.manus@t-online.de', 1),
(6, 1, 'manus123', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas.anus@t-online.de', 0),
(8, 0, 'manus1234', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas.nus@t-online.de', 0),
(9, 0, 'manus12345', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas.us@t-online.de', 0),
(10, 0, 'manus123456', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas.s@t-online.de', 0),
(12, 0, 'manus1234567', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas@t-online.de', 0),
(14, 0, 'lukas11', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas@manus.de', 0),
(15, 0, 'lukas123', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas.manuss@t-online.de', 0),
(16, 0, 'test123243', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas.manusss@t-online.de', 0),
(17, 0, 'test1234345', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukasss.manus@t-online.de', 0),
(18, 0, 'irene', 'Irene', 'Schuster', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'irene.schuster@gmx.de', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tblzitate`
--

CREATE TABLE `tblzitate` (
  `ID` int(11) NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `UrheberID` varchar(45) NOT NULL,
  `SprecherID` varchar(45) NOT NULL,
  `KursID` varchar(45) NOT NULL,
  `Datum` bigint(30) NOT NULL,
  `klasseID` varchar(45) NOT NULL,
  `lehrerID` varchar(45) NOT NULL,
  `zitat` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblzitate`
--

INSERT INTO `tblzitate` (`ID`, `deleted`, `UrheberID`, `SprecherID`, `KursID`, `Datum`, `klasseID`, `lehrerID`, `zitat`) VALUES
(14, 0, '5', '14', '1', 1542564909646, '1', '1', 'Ich bin ein Testzitat.'),
(15, 0, '5', '14', '2', 1542564932815, '1', '5', 'Ich bin ein anderes Zitat.'),
(16, 0, '5', '5', '2', 1542564968613, '1', '5', 'Das ist ein weiteres Testzitat.'),
(17, 0, '5', '5', '2', 1544376819076, '2', '5', 'Robin, du bist ein richtiger Kevin.'),
(18, 1, '5', '18', '2', 1544377080327, '1', '5', '2,4,6,8,10...Ach das wird mir zu kompliziert.');

-- --------------------------------------------------------

--
-- Table structure for table `user_kurs_map`
--

CREATE TABLE `user_kurs_map` (
  `id` int(11) NOT NULL,
  `kursid` int(11) NOT NULL COMMENT 'ForeignID from tblKurs',
  `userid` int(11) NOT NULL COMMENT 'ForeignID from tblUser'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_kurs_map`
--

INSERT INTO `user_kurs_map` (`id`, `kursid`, `userid`) VALUES
(1, 1, 5),
(2, 2, 5),
(3, 6, 5),
(4, 2, 18);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `lehrer_kurs_map`
--
ALTER TABLE `lehrer_kurs_map`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tblklassen`
--
ALTER TABLE `tblklassen`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID_UNIQUE` (`ID`),
  ADD UNIQUE KEY `Klasse_UNIQUE` (`Klasse`);

--
-- Indexes for table `tblkurs`
--
ALTER TABLE `tblkurs`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Kurs_UNIQUE` (`Kurs`),
  ADD UNIQUE KEY `ID_UNIQUE` (`ID`);

--
-- Indexes for table `tbllehrer`
--
ALTER TABLE `tbllehrer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbluser`
--
ALTER TABLE `tbluser`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Nutzername_UNIQUE` (`Nutzername`),
  ADD UNIQUE KEY `idx_tbluser_Mail` (`Mail`);

--
-- Indexes for table `tblzitate`
--
ALTER TABLE `tblzitate`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID_UNIQUE` (`ID`);

--
-- Indexes for table `user_kurs_map`
--
ALTER TABLE `user_kurs_map`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `lehrer_kurs_map`
--
ALTER TABLE `lehrer_kurs_map`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tblklassen`
--
ALTER TABLE `tblklassen`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tblkurs`
--
ALTER TABLE `tblkurs`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tbllehrer`
--
ALTER TABLE `tbllehrer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tbluser`
--
ALTER TABLE `tbluser`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `tblzitate`
--
ALTER TABLE `tblzitate`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `user_kurs_map`
--
ALTER TABLE `user_kurs_map`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
