-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 04 jan. 2023 à 19:51
-- Version du serveur : 10.4.25-MariaDB
-- Version de PHP : 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bus`
--

-- --------------------------------------------------------

--
-- Structure de la table `book`
--

CREATE TABLE `book` (
  `from` varchar(50) NOT NULL,
  `to` varchar(45) DEFAULT NULL,
  `idBook` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `book`
--

INSERT INTO `book` (`from`, `to`, `idBook`) VALUES
('Agadir', 'Rabat', 1),
('Boujdour', 'Agadir', 2),
('CasaBlanca', 'Rabat', 3),
('Tanger', 'Agadir', 4),
('Taroudant', 'Tata', 5),
('Tata', 'Agadir', 6);

-- --------------------------------------------------------

--
-- Structure de la table `search`
--

CREATE TABLE `search` (
  `service` varchar(50) NOT NULL,
  `source` varchar(45) DEFAULT NULL,
  `dest` varchar(50) DEFAULT NULL,
  `fare` int(11) DEFAULT NULL,
  `dtime` time DEFAULT NULL,
  `atime` time DEFAULT NULL,
  `seat` int(11) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `search`
--

INSERT INTO `search` (`service`, `source`, `dest`, `fare`, `dtime`, `atime`, `seat`, `date`) VALUES
('CTM', 'Agadir', 'Rabat', 300, '12:30:00', '20:00:00', 41, '2023-01-15'),
('ITRAN', 'Boujdour', 'Agadir', 450, '08:30:00', '19:00:00', 41, '2022-01-10'),
('SAT', 'Boujdour', 'Agadir', 500, '20:30:00', '06:00:00', 41, '2023-01-10');

-- --------------------------------------------------------

--
-- Structure de la table `seats`
--

CREATE TABLE `seats` (
  `seatname` varchar(50) NOT NULL,
  `uname` varchar(50) NOT NULL,
  `service` varchar(50) NOT NULL,
  `count` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `seats`
--

INSERT INTO `seats` (`seatname`, `uname`, `service`, `count`, `id`) VALUES
('A1', 'Hatim', 'SAT', 1, 3),
('A2', 'Hatim', 'SAT', 1, 4),
('A3', 'Aziz', 'CTM', 1, 5),
('A4', 'Hatim', 'CTM', 1, 6),
('B1', 'Aziz', 'CTM', 1, 7),
('B2', 'Hatim', 'CTM', 0, 8),
('B3', 'Hatim', 'CTM', 0, 9),
('B4', 'Aziz', 'CTM', 1, 10),
('C1', 'Hatim', 'CTM', 1, 11),
('C2', 'Hatim', 'CTM', 1, 12),
('C3', 'Hatim', 'CTM', 1, 13),
('C4', 'Hatim', 'CTM', 1, 14),
('D1', 'Aziz', 'CTM', 0, 15),
('D2', 'Hatim', 'CTM', 0, 16),
('D3', 'Hatim', 'CTM', 0, 17),
('D4', 'Hatim', 'CTM', 0, 18),
('A3', 'Hatim', 'SAT', 0, 19),
('A4', 'Hatim', 'SAT', 0, 20),
('B1', 'Hatim', 'SAT', 1, 21),
('B2', 'Hatim', 'SAT', 0, 22),
('C2', 'Hatim', 'SAT', 0, 26),
('C4', 'Hatim', 'SAT', 0, 28),
('D1', 'Hatim', 'SAT', 0, 29),
('D2', 'Hatim', 'SAT', 0, 30),
('D3', 'Hatim', 'SAT', 0, 31),
('D4', 'Hatim', 'SAT', 0, 32),
('A1', 'Hatim', 'ITRAN', 0, 33),
('A2', 'Hatim', 'ITRAN', 0, 34),
('A3', 'Hatim', 'ITRAN', 0, 35),
('A4', 'Hatim', 'ITRAN', 0, 36),
('B1', 'Hatim', 'ITRAN', 0, 37),
('B2', 'Hatim', 'ITRAN', 0, 38),
('B3', 'Hatim', 'ITRAN', 0, 39),
('B4', 'Hatim', 'ITRAN', 0, 40),
('C1', 'Hatim', 'ITRAN', 0, 41),
('C2', 'Hatim', 'ITRAN', 0, 42),
('C3', 'Hatim', 'ITRAN', 0, 50),
('C4', 'Hatim', 'ITRAN', 0, 51),
('D1', 'Hatim', 'ITRAN', 0, 54),
('D2', 'Hatim', 'ITRAN', 0, 55),
('D3', 'Hatim', 'ITRAN', 0, 56),
('D4', 'Hatim', 'ITRAN', 0, 57);

-- --------------------------------------------------------

--
-- Structure de la table `seat_names`
--

CREATE TABLE `seat_names` (
  `seatname` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `seat_names`
--

INSERT INTO `seat_names` (`seatname`) VALUES
('A1'),
('A2'),
('A3'),
('A4'),
('B1'),
('B2'),
('B3'),
('B4'),
('C1'),
('C2'),
('C3'),
('C4'),
('D1'),
('D2'),
('D3'),
('D4'),
('E1');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `uname` varchar(50) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `fname` varchar(45) DEFAULT NULL,
  `lname` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `age` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `gender` varchar(23) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`uname`, `password`, `fname`, `lname`, `phone`, `age`, `state`, `city`, `gender`, `email`) VALUES
('Aziz', '1234', 'Aziz', 'Essadeq', '06060606', '23', 'Boujdour', 'Boujdour', 'Male', 'aziz@gmail.com'),
('Hatim', '1234', 'Hatim', 'Lakrouni', '06060606', '23', 'Agadir', 'Agadir', 'Male', 'hatim@gmail.com');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`idBook`),
  ADD UNIQUE KEY `idBook` (`idBook`),
  ADD UNIQUE KEY `idBook_2` (`idBook`),
  ADD UNIQUE KEY `idBook_3` (`idBook`);

--
-- Index pour la table `search`
--
ALTER TABLE `search`
  ADD PRIMARY KEY (`service`);

--
-- Index pour la table `seats`
--
ALTER TABLE `seats`
  ADD PRIMARY KEY (`id`),
  ADD KEY `seatname` (`seatname`),
  ADD KEY `uname` (`uname`),
  ADD KEY `service` (`service`);

--
-- Index pour la table `seat_names`
--
ALTER TABLE `seat_names`
  ADD PRIMARY KEY (`seatname`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`uname`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `book`
--
ALTER TABLE `book`
  MODIFY `idBook` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `seats`
--
ALTER TABLE `seats`
  ADD CONSTRAINT `seats_ibfk_1` FOREIGN KEY (`seatname`) REFERENCES `seat_names` (`seatname`) ON UPDATE CASCADE,
  ADD CONSTRAINT `seats_ibfk_2` FOREIGN KEY (`uname`) REFERENCES `user` (`uname`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `seats_ibfk_3` FOREIGN KEY (`service`) REFERENCES `search` (`service`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
