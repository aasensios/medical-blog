SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE IF NOT EXISTS `advancedmedical` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `advancedmedical`;

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'books'),
(2, 'news'),
(3, 'webs');

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `password` varchar(150) COLLATE utf8_spanish2_ci NOT NULL,
  `role` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `address` varchar(150) COLLATE utf8_spanish2_ci NOT NULL,
  `dni` varchar(9) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

INSERT INTO `users` (`id`, `name`, `password`, `role`, `email`, `address`, `dni`) VALUES
(1, 'anas', 'anas', 'admin', 'anas@gmail.com', 'Carrer 23', '12881846Y'),
(2, 'asd', 'asd', 'basic', 'asd@gmail.com ', 'Carrer 23 3ª-1ª', '27763506E'),
(3, 'isaac', 'isaac', 'admin', 'isaac@gmail.com', 'Calle 12 5-2', '46482942B'),
(4, 'alumne', 'alumne', 'basic', 'alumne@gmail.com', 'Calle 123', '12345678Z');

CREATE TABLE `webs` (
  `code` varchar(6) COLLATE utf8_spanish2_ci NOT NULL,
  `publication_date` date NOT NULL,
  `title` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `url` varchar(200) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

INSERT INTO `webs` (`code`, `publication_date`, `title`, `url`) VALUES
('P-0001', '2004-01-01', 'Facebook', 'https://www.facebook.com'),
('P-0002', '2005-02-14', 'Youtube', 'https://www.youtube.com'),
('P-0003', '2001-01-05', 'Wikipedia', 'http://www.wikipedia.com');


ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `dni` (`dni`);

ALTER TABLE `webs`
  ADD PRIMARY KEY (`code`);


ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
