-- phpMyAdmin SQL Dump
-- version 4.5.4.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 20, 2020 at 01:53 PM
-- Server version: 5.7.11
-- PHP Version: 5.6.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projetsddb`
--

-- --------------------------------------------------------

--
-- Table structure for table `jeux`
--

CREATE TABLE `jeux` (
  `idJ` int(8) NOT NULL,
  `titre` varchar(40) COLLATE utf32_unicode_ci NOT NULL,
  `genre` varchar(40) COLLATE utf32_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

--
-- Dumping data for table `jeux`
--

INSERT INTO `jeux` (`idJ`, `titre`, `genre`) VALUES
(1, 'NieR : Automata', 'ARPG'),
(2, 'Golden Sun : Broken Seal', 'RPG'),
(3, 'Civilization VI', '4X'),
(4, 'Helltaker', 'Puzzle/Visual Novel'),
(5, 'Eternal Card Game', 'TCG'),
(9, 'Hades', 'Rogue-like'),
(10, 'Crypt of the Necrodancer', 'Rogue-like'),
(11, 'Overwatch', 'FPS'),
(12, 'Chess 2', 'StratÃ©gie');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `jeux`
--
ALTER TABLE `jeux`
  ADD PRIMARY KEY (`idJ`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
