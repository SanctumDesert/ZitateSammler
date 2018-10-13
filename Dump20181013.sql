-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 13, 2018 at 03:36 PM
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
-- Table structure for table `tblaenderungen`
--

CREATE TABLE `tblaenderungen` (
  `ID` int(11) NOT NULL,
  `UserID` varchar(45) NOT NULL,
  `ZitatID` varchar(45) NOT NULL,
  `Datum` bigint(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `Lehrer` varchar(45) NOT NULL,
  `Kurs` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblkurs`
--

INSERT INTO `tblkurs` (`ID`, `Lehrer`, `Kurs`) VALUES
(1, 'Gruening', 'LF6'),
(2, 'Schuster', 'LF5'),
(3, 'Gruening', 'Sport');

-- --------------------------------------------------------

--
-- Table structure for table `tbluser`
--

CREATE TABLE `tbluser` (
  `ID` int(11) NOT NULL,
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

INSERT INTO `tbluser` (`ID`, `Nutzername`, `Vorname`, `Nachname`, `Passwort`, `Mail`, `Admin`) VALUES
(1, 'Kuro', 'Fabian', 'Minx', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', 'test', 1),
(2, 'Sanctum', 'Lukas', 'Manus', '60303ae22b998861bce3b28f33eec1be758a213c86c93c076dbe9f558c11c752', 'test2', 1),
(4, 'Tester', 'testVorname', 'testNachname', 'testPW', 'testMail', 0),
(5, 'manus12', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas.manus@t-online.de', 0),
(6, 'manus123', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas.anus@t-online.de', 0),
(8, 'manus1234', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas.nus@t-online.de', 0),
(9, 'manus12345', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas.us@t-online.de', 0),
(10, 'manus123456', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas.s@t-online.de', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tblzitate`
--

CREATE TABLE `tblzitate` (
  `ID` int(11) NOT NULL,
  `UrheberID` varchar(45) NOT NULL,
  `SprecherID` varchar(45) NOT NULL,
  `KursID` varchar(45) NOT NULL,
  `Datum` bigint(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblzitate`
--

INSERT INTO `tblzitate` (`ID`, `UrheberID`, `SprecherID`, `KursID`, `Datum`) VALUES
(1, '1', '1', '1', 20092018);

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
-- Indexes for dumped tables
--

--
-- Indexes for table `tblaenderungen`
--
ALTER TABLE `tblaenderungen`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID_UNIQUE` (`ID`);

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
-- AUTO_INCREMENT for table `tblaenderungen`
--
ALTER TABLE `tblaenderungen`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tblklassen`
--
ALTER TABLE `tblklassen`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tblkurs`
--
ALTER TABLE `tblkurs`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbluser`
--
ALTER TABLE `tbluser`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tblzitate`
--
ALTER TABLE `tblzitate`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user_kurs_map`
--
ALTER TABLE `user_kurs_map`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
