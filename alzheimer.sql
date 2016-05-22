-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 22, 2016 at 09:37 PM
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

--
-- Dumping data for table `memory`
--

INSERT INTO `memory` (`memory_id`) VALUES
(55);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `relationship`
--

INSERT INTO `relationship` (`relation_id`, `relative_id`, `patient_id`, `family_position_id`) VALUES
(1, 7, 3, 10),
(2, 7, 5, 39);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=40 ;

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`request_id`, `relative_id`, `patient_id`, `family_position_id`) VALUES
(29, 2, 3, 6),
(37, 4, 3, 10),
(38, 7, 3, 39),
(39, 8, 3, 6);

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
  `longitude` double DEFAULT NULL,
  `image_url` varchar(400) CHARACTER SET ascii DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `birthday`, `gender`, `email`, `phone_num`, `home_num`, `country`, `city`, `address`, `type`, `password`, `longitude`, `image_url`, `latitude`) VALUES
(2, 'mohamed', 'atef', '3893-02-15', 1, 'atef@yahoo.com', '0111111111111', NULL, 'egypt', 'P.S', 'el gam3a el adema', 0, 'QftkHtTYzIuF4Ed2wrtsGg==', NULL, 'IMG_5763.JPG', NULL),
(3, 'donia', 'mohamed', '1994-04-18', 0, 'donia@yahoo.com', '01111111', '11111', 'ksa', 'reyadh', '5amsa', 1, '123456789', NULL, 'donia.JPG', NULL),
(4, 'safaa', 'badr', '1993-03-13', 0, 'safaa@yahoo.com', '222222', '22222', 'egy', 'sweez', 'sab3a', 0, '456', NULL, 'safaa.jpg', NULL),
(5, 'nihal', 'hassan', '1993-09-14', 0, 'nihal@yahoo.com', '3333333', '333333', 'egypt', 'sweez', '3eshreeny', 1, '345', NULL, 'nihal.jpg', NULL),
(6, 'doaa', 'rashidy', '1993-01-18', 0, 'doaa@yahoo.com', '444444', '44444', 'egypt', 'cairo', '3eshreny', 1, '123', NULL, 'doaa.jpg', NULL),
(7, 'mohamed', 'ayad', '1993-02-11', 1, 'ayad@yahoo.com', '5555555', '555555', 'egypt', 'P.S', 'gam3a adema', 0, '123', NULL, 'ayad.jpg', NULL),
(8, 'nour', 'abd el rhim', '1994-01-12', 0, 'nour@yahoo.com', '66666', '6666', 'egypt', 'ismailia', 'dayry', 0, '123', NULL, 'nour.jpg', NULL),
(9, ' donia', 'hafez', '1994-04-18', 0, 'dodo@gmail.com', '+201002416766', '+20643222819', 'Egypt', 'Ismailia', 'Place: Ù?Ø§Ù?Ù? Ø³ØªØ§Ø±', 1, 'YNrf8QRI2bnuEhp6TF4wdA==', 32.266431, NULL, 30.603014);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `relationship`
--
ALTER TABLE `relationship`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`relative_id`) REFERENCES `users` (`user_id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk5` FOREIGN KEY (`family_position_id`) REFERENCES `familyposition` (`family_position_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk6` FOREIGN KEY (`patient_id`) REFERENCES `users` (`user_id`) ON UPDATE NO ACTION;

--
-- Constraints for table `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `fk2` FOREIGN KEY (`relative_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk3` FOREIGN KEY (`patient_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk4` FOREIGN KEY (`family_position_id`) REFERENCES `familyposition` (`family_position_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
