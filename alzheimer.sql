-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 16, 2016 at 12:17 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `alzheimer`
--
CREATE DATABASE IF NOT EXISTS `alzheimer` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `alzheimer`;

-- --------------------------------------------------------

--
-- Table structure for table `familyposition`
--

CREATE TABLE IF NOT EXISTS `familyposition` (
  `family_position_id` int(11) NOT NULL,
  `family_position_value` varchar(50) NOT NULL,
  PRIMARY KEY (`family_position_id`),
  UNIQUE KEY `family_position_value` (`family_position_value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `familyposition`
--

INSERT INTO `familyposition` (`family_position_id`, `family_position_value`) VALUES
(20, 'Aunt (Father side)'),
(21, 'Aunt (Mother side)'),
(7, 'Brother'),
(39, 'Brother-in-law'),
(25, 'Cousin (female) - (Mother side)'),
(24, 'Cousin (female)- (Father side)'),
(22, 'Cousin (male) -(Father side)'),
(23, 'Cousin (male) -(Mother side)'),
(6, 'Daughter'),
(38, 'Daughter-in-law'),
(3, 'Father'),
(35, 'Father-in-law'),
(17, 'Grandchildren'),
(16, 'Granddaughter'),
(13, 'Grandfather'),
(14, 'Grandmother'),
(15, 'Grandson'),
(47, 'Half-brother'),
(48, 'Half-sister'),
(9, 'Husband'),
(4, 'Mother'),
(36, 'Mother-in-law'),
(31, 'Nephew (brother''s son)'),
(32, 'Nephew (sister''s son)'),
(33, 'Niece (brother''s daughter)'),
(34, 'Niece (sister''s daughter)'),
(8, 'Sister'),
(40, 'Siter-in-law'),
(5, 'Son'),
(37, 'Son-in-law'),
(46, 'Stepbrother'),
(44, 'Stepdaughter'),
(41, 'Stepfather'),
(42, 'Stepmother'),
(45, 'Stepsister'),
(43, 'Stepson'),
(18, 'Uncle (Father side)'),
(19, 'Uncle (Mother side)'),
(10, 'Wife');

-- --------------------------------------------------------

--
-- Table structure for table `memories`
--

CREATE TABLE IF NOT EXISTS `memories` (
  `memories_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) NOT NULL,
  `relative_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`memories_id`),
  KEY `patient_id` (`patient_id`),
  KEY `relative_id` (`relative_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `memory`
--

CREATE TABLE IF NOT EXISTS `memory` (
  `memory_id` int(11) NOT NULL,
  `memory_text` varchar(400) DEFAULT NULL,
  `image_url` varchar(200) DEFAULT NULL,
  `video_url` varchar(200) DEFAULT NULL,
  `date_time` varchar(45) NOT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `address` varchar(400) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `patient_id` int(11) NOT NULL,
  PRIMARY KEY (`memory_id`),
  KEY `memory_id` (`memory_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `relationship`
--

CREATE TABLE IF NOT EXISTS `relationship` (
  `relation_id` int(11) NOT NULL AUTO_INCREMENT,
  `relative_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `family_position_id` int(11) NOT NULL,
  PRIMARY KEY (`relative_id`,`patient_id`,`relation_id`),
  UNIQUE KEY `relation_id` (`relation_id`),
  KEY `relative_id` (`relative_id`),
  KEY `patient_id` (`patient_id`),
  KEY `family_position_id` (`family_position_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE IF NOT EXISTS `request` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `relative_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `family_position_id` int(11) NOT NULL,
  PRIMARY KEY (`relative_id`,`patient_id`,`request_id`),
  UNIQUE KEY `request_id` (`request_id`),
  KEY `request_id_2` (`request_id`),
  KEY `patient_id` (`patient_id`),
  KEY `family_position_id` (`family_position_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `birthday` date NOT NULL,
  `gender` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone_num` varchar(15) NOT NULL,
  `home_num` varchar(15) DEFAULT NULL,
  `country` varchar(30) NOT NULL,
  `city` varchar(30) NOT NULL,
  `address` varchar(300) NOT NULL,
  `type` int(11) NOT NULL,
  `password` varchar(50) NOT NULL,
  `longitude` double DEFAULT NULL,
  `image_url` varchar(500) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `mac_Address` varchar(60) DEFAULT NULL,
  `user_token` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `memories`
--
ALTER TABLE `memories`
  ADD CONSTRAINT `memories_ibfk_2` FOREIGN KEY (`relative_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `memories_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `memory`
--
ALTER TABLE `memory`
  ADD CONSTRAINT `memory_ibfk_1` FOREIGN KEY (`memory_id`) REFERENCES `memories` (`memories_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `relationship`
--
ALTER TABLE `relationship`
  ADD CONSTRAINT `relationship_ibfk_3` FOREIGN KEY (`family_position_id`) REFERENCES `familyposition` (`family_position_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `relationship_ibfk_1` FOREIGN KEY (`relative_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `relationship_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `request_ibfk_3` FOREIGN KEY (`family_position_id`) REFERENCES `familyposition` (`family_position_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `request_ibfk_1` FOREIGN KEY (`relative_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `request_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
