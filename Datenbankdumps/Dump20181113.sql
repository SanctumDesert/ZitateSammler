-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 13. Nov 2018 um 18:13
-- Server-Version: 10.1.36-MariaDB
-- PHP-Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `zitatedb`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `lehrer_kurs_map`
--

CREATE TABLE `lehrer_kurs_map` (
  `ID` int(11) NOT NULL,
  `LehrerID` int(11) NOT NULL COMMENT 'ForeignKey von Tabelle Lehrer',
  `KursID` int(11) NOT NULL COMMENT 'ForeignKey von Tabelle Kurs'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `lehrer_kurs_map`
--

INSERT INTO `lehrer_kurs_map` (`ID`, `LehrerID`, `KursID`) VALUES
(1, 1, 1),
(2, 5, 2),
(3, 1, 3),
(4, 1, 5);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tblaenderungen`
--

CREATE TABLE `tblaenderungen` (
  `ID` int(11) NOT NULL,
  `UserID` varchar(45) NOT NULL,
  `ZitatID` varchar(45) NOT NULL,
  `Datum` bigint(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tblklassen`
--

CREATE TABLE `tblklassen` (
  `ID` int(11) NOT NULL,
  `Klasse` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `tblklassen`
--

INSERT INTO `tblklassen` (`ID`, `Klasse`) VALUES
(1, '12FIAE'),
(2, '12FISI');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tblkurs`
--

CREATE TABLE `tblkurs` (
  `ID` int(11) NOT NULL,
  `Kurs` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `tblkurs`
--

INSERT INTO `tblkurs` (`ID`, `Kurs`) VALUES
(5, '10FIAE16/17-LF6'),
(1, '10FIAE17/18-LF6'),
(2, '11FISI17/18-LF5'),
(3, '12FIAE17/18-Sport');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tbllehrer`
--

CREATE TABLE `tbllehrer` (
  `id` int(11) NOT NULL,
  `name` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `tbllehrer`
--

INSERT INTO `tbllehrer` (`id`, `name`) VALUES
(1, 'Gruening'),
(3, 'Blam'),
(4, 'Gram'),
(5, 'Schuster');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tbluser`
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
-- Daten für Tabelle `tbluser`
--

INSERT INTO `tbluser` (`ID`, `Deleted`, `Nutzername`, `Vorname`, `Nachname`, `Passwort`, `Mail`, `Admin`) VALUES
(1, 0, 'Kuro', 'Fabian', 'Minx', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', 'test', 1),
(2, 0, 'Sanctum', 'Lukas', 'Manus', '60303ae22b998861bce3b28f33eec1be758a213c86c93c076dbe9f558c11c752', 'test2', 1),
(4, 0, 'Tester', 'testVorname', 'testNachname', 'testPW', 'testMail', 0),
(5, 0, 'manus12', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas.manus@t-online.de', 1),
(6, 1, 'manus123', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas.anus@t-online.de', 0),
(8, 0, 'manus1234', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas.nus@t-online.de', 0),
(9, 0, 'manus12345', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas.us@t-online.de', 0),
(10, 0, 'manus123456', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas.s@t-online.de', 0),
(12, 0, 'manus1234567', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas@t-online.de', 0),
(14, 0, 'lukas11', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas@manus.de', 0),
(15, 0, 'lukas123', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas.manuss@t-online.de', 0),
(16, 0, 'test123243', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukas.manusss@t-online.de', 0),
(17, 0, 'test1234345', 'lukas', 'manus', '08253867d3319bd3da77a3a3a28822e6e503d5467a630571cead2c5dd70ec6cd', 'lukasss.manus@t-online.de', 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tblzitate`
--

CREATE TABLE `tblzitate` (
  `ID` int(11) NOT NULL,
  `deleted` tinyint(1) NOT NULL,
  `UrheberID` varchar(45) NOT NULL,
  `SprecherID` varchar(45) NOT NULL,
  `KursID` varchar(45) NOT NULL,
  `Datum` bigint(30) NOT NULL,
  `klasseID` varchar(45) NOT NULL,
  `lehrerID` varchar(45) NOT NULL,
  `zitat` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `tblzitate`
--

INSERT INTO `tblzitate` (`ID`, `deleted`, `UrheberID`, `SprecherID`, `KursID`, `Datum`, `klasseID`, `lehrerID`, `zitat`) VALUES
(1, 0, '1', '1', '1', 20092018, '0', '', ''),
(3, 0, '5', '1', '1', 1540553997010, '1', '1', 'Test1'),
(4, 0, '5', '5', '-1', 1540554380373, '1', '1', 'Test2'),
(5, 0, '5', '5', '-1', 1540554654597, '1', '1', 'Test123'),
(6, 0, '2', '5', '1', 1540554929211, '2', '1', 'Test123'),
(7, 0, '5', '5', '1', 1541149002671, '1', '1', 'test'),
(9, 0, '2', '5', '2', 1540554380373, '2', '5', 'Test123423544545356');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user_kurs_map`
--

CREATE TABLE `user_kurs_map` (
  `id` int(11) NOT NULL,
  `kursid` int(11) NOT NULL COMMENT 'ForeignID from tblKurs',
  `userid` int(11) NOT NULL COMMENT 'ForeignID from tblUser'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `user_kurs_map`
--

INSERT INTO `user_kurs_map` (`id`, `kursid`, `userid`) VALUES
(1, 1, 5),
(2, 2, 5);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `lehrer_kurs_map`
--
ALTER TABLE `lehrer_kurs_map`
  ADD PRIMARY KEY (`ID`);

--
-- Indizes für die Tabelle `tblaenderungen`
--
ALTER TABLE `tblaenderungen`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID_UNIQUE` (`ID`);

--
-- Indizes für die Tabelle `tblklassen`
--
ALTER TABLE `tblklassen`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID_UNIQUE` (`ID`),
  ADD UNIQUE KEY `Klasse_UNIQUE` (`Klasse`);

--
-- Indizes für die Tabelle `tblkurs`
--
ALTER TABLE `tblkurs`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Kurs_UNIQUE` (`Kurs`),
  ADD UNIQUE KEY `ID_UNIQUE` (`ID`);

--
-- Indizes für die Tabelle `tbllehrer`
--
ALTER TABLE `tbllehrer`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `tbluser`
--
ALTER TABLE `tbluser`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Nutzername_UNIQUE` (`Nutzername`),
  ADD UNIQUE KEY `idx_tbluser_Mail` (`Mail`);

--
-- Indizes für die Tabelle `tblzitate`
--
ALTER TABLE `tblzitate`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID_UNIQUE` (`ID`);

--
-- Indizes für die Tabelle `user_kurs_map`
--
ALTER TABLE `user_kurs_map`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `lehrer_kurs_map`
--
ALTER TABLE `lehrer_kurs_map`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT für Tabelle `tblaenderungen`
--
ALTER TABLE `tblaenderungen`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `tblklassen`
--
ALTER TABLE `tblklassen`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT für Tabelle `tblkurs`
--
ALTER TABLE `tblkurs`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT für Tabelle `tbllehrer`
--
ALTER TABLE `tbllehrer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT für Tabelle `tbluser`
--
ALTER TABLE `tbluser`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT für Tabelle `tblzitate`
--
ALTER TABLE `tblzitate`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT für Tabelle `user_kurs_map`
--
ALTER TABLE `user_kurs_map`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
