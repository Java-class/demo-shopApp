-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 12, 2020 at 10:39 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.2.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `demo`
--
CREATE DATABASE IF NOT EXISTS `demo` DEFAULT CHARACTER SET utf8 COLLATE utf8_persian_ci;
USE `demo`;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `add_date` timestamp NULL DEFAULT current_timestamp(),
  `description` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `price` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `add_date`, `description`, `name`, `price`) VALUES
(15, '2020-07-12 20:29:51', NULL, 'Milk', 5500),
(16, '2020-07-12 20:29:51', NULL, 'Ketchup', 8800),
(17, '2020-07-12 20:29:51', NULL, 'Coffee', 45000),
(18, '2020-07-12 20:29:51', NULL, 'Rice', 25000),
(19, '2020-07-12 20:29:51', NULL, 'Apple Juice', 12500),
(20, '2020-07-12 20:29:51', NULL, 'Sugar', 7500),
(21, '2020-07-12 20:29:51', NULL, 'Cake', 3500),
(22, '2020-07-12 20:29:51', NULL, 'Beans', 8700),
(23, '2020-07-12 20:29:51', NULL, 'Fish Tuna', 13800),
(24, '2020-07-12 20:29:51', NULL, 'Toffee', 38000),
(25, '2020-07-12 20:32:44', NULL, 'Milk', 5500),
(26, '2020-07-12 20:32:44', NULL, 'Ketchup', 8800),
(27, '2020-07-12 20:32:44', NULL, 'Coffee', 45000),
(28, '2020-07-12 20:32:44', NULL, 'Rice', 25000),
(29, '2020-07-12 20:32:44', NULL, 'Apple Juice', 12500),
(30, '2020-07-12 20:32:44', NULL, 'Sugar', 7500),
(31, '2020-07-12 20:32:44', NULL, 'Cake', 3500),
(32, '2020-07-12 20:32:44', NULL, 'Beans', 8700),
(33, '2020-07-12 20:32:44', NULL, 'Fish Tuna', 13800),
(34, '2020-07-12 20:32:44', NULL, 'Toffee', 38000);

-- --------------------------------------------------------

--
-- Table structure for table `purchase`
--

CREATE TABLE `purchase` (
  `id` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `product_id` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `price` double NOT NULL,
  `shop_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `add_date` timestamp NULL DEFAULT current_timestamp(),
  `balance` double NOT NULL,
  `family` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `add_date`, `balance`, `family`, `name`, `password`, `phone`) VALUES
('0912xxxxxxx', NULL, 0, 'admin', 'Admin', 'admin_password', NULL),
('0919yyyyyyy', NULL, 0, 'user', 'User', 'user_password', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `purchase`
--
ALTER TABLE `purchase`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `purchase`
--
ALTER TABLE `purchase`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
