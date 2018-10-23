-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 23, 2018 at 11:54 AM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `questions`
--

-- --------------------------------------------------------

--
-- Table structure for table `quest`
--

CREATE TABLE `quest` (
  `Question` varchar(100) DEFAULT NULL,
  `A` varchar(30) DEFAULT NULL,
  `B` varchar(30) DEFAULT NULL,
  `C` varchar(30) DEFAULT NULL,
  `D` varchar(30) DEFAULT NULL,
  `correct` varchar(30) DEFAULT NULL,
  `id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `quest`
--

INSERT INTO `quest` (`Question`, `A`, `B`, `C`, `D`, `correct`, `id`) VALUES
('Which city is the capital of Canda ?', 'Toronto', 'Montreal', 'Ottawa', 'Vancouver', 'C', 1),
('Which city is the capital of Saudi Arabia ?', 'Riyadh', 'Mecca', 'Medina', 'Jeddah', 'A', 2),
('Which city is NOT the capital of South Africa', 'Cape Town', 'Pretoria', 'Bloemfontein', 'Durban', 'D', 3),
('Which city is the capital of UK?', 'London', 'Leeds', 'York', 'Kent', 'A', 4);

-- --------------------------------------------------------

--
-- Table structure for table `ur`
--

CREATE TABLE `ur` (
  `UserResponse` varchar(100) DEFAULT 'Not_Answered',
  `correct` varchar(100) DEFAULT NULL,
  `id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ur`
--

INSERT INTO `ur` (`UserResponse`, `correct`, `id`) VALUES
('C', 'C', 1),
('A', 'A', 2),
('D', 'D', 3),
('A', 'A', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `quest`
--
ALTER TABLE `quest`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ur`
--
ALTER TABLE `ur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `quest`
--
ALTER TABLE `quest`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `ur`
--
ALTER TABLE `ur`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
