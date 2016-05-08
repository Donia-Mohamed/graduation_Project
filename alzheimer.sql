-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 07, 2016 at 03:44 PM
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

-- --------------------------------------------------------

--
-- Table structure for table `familyposition`
--

CREATE TABLE IF NOT EXISTS `familyposition` (
  `family_position_id` int(11) NOT NULL,
  `fimaly_position_value` varchar(50) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`family_position_id`),
  UNIQUE KEY `fimaly_position_value_UNIQUE` (`fimaly_position_value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `familyposition`
--

INSERT INTO `familyposition` (`family_position_id`, `fimaly_position_value`) VALUES
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
-- Table structure for table `memory`
--

CREATE TABLE IF NOT EXISTS `memory` (
  `memory_id` int(11) NOT NULL,
  PRIMARY KEY (`memory_id`)
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
  PRIMARY KEY (`patient_id`,`relative_id`),
  UNIQUE KEY `relation_id_UNIQUE` (`relation_id`),
  KEY `fk5_idx` (`family_position_id`),
  KEY `fk1_idx` (`relative_id`),
  KEY `fk6_idx` (`patient_id`)
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
  PRIMARY KEY (`relative_id`,`patient_id`),
  UNIQUE KEY `request_id_UNIQUE` (`request_id`),
  KEY `fk2_idx` (`relative_id`),
  KEY `fk3_idx` (`patient_id`),
  KEY `fk4_idx` (`family_position_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) CHARACTER SET latin1 NOT NULL,
  `last_name` varchar(20) CHARACTER SET latin1 NOT NULL,
  `birthday` date NOT NULL,
  `gender` int(11) NOT NULL,
  `email` varchar(100) CHARACTER SET latin1 NOT NULL,
  `phone_num` varchar(15) CHARACTER SET latin1 NOT NULL,
  `home_num` varchar(15) CHARACTER SET latin1 DEFAULT NULL,
  `country` varchar(30) CHARACTER SET latin1 NOT NULL,
  `city` varchar(30) CHARACTER SET latin1 NOT NULL,
  `address` varchar(70) CHARACTER SET latin1 NOT NULL,
  `type` int(11) NOT NULL,
  `password` varchar(50) CHARACTER SET latin1 NOT NULL,
  `longitude` varchar(50) DEFAULT NULL,
  `image_url` varchar(400) CHARACTER SET ascii NOT NULL,
  `latitude` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `relationship`
--
ALTER TABLE `relationship`
  ADD CONSTRAINT `fk6` FOREIGN KEY (`patient_id`) REFERENCES `users` (`user_id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk1` FOREIGN KEY (`relative_id`) REFERENCES `users` (`user_id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk5` FOREIGN KEY (`family_position_id`) REFERENCES `familyposition` (`family_position_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `fk4` FOREIGN KEY (`family_position_id`) REFERENCES `familyposition` (`family_position_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk2` FOREIGN KEY (`relative_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION,
  ADD CONSTRAINT `fk3` FOREIGN KEY (`patient_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
