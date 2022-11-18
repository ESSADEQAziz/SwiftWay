-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : sam. 12 nov. 2022 à 21:35
-- Version du serveur : 8.0.31
-- Version de PHP : 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `swiftway`
--

-- --------------------------------------------------------

--
-- Structure de la table `Admin`
--

CREATE TABLE `Admin` (
  `nomUtilisateur` varchar(30) NOT NULL,
  `motDepasse` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Autocar`
--

CREATE TABLE `Autocar` (
  `idAutocar` double NOT NULL,
  `numeroAutocar` int DEFAULT NULL,
  `placesTotal` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Companie`
--

CREATE TABLE `Companie` (
  `nomDeSociete` varchar(30) NOT NULL,
  `totalVehicules` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Offre`
--

CREATE TABLE `Offre` (
  `idOffre` double NOT NULL,
  `nbrDePlacesDisponible` int DEFAULT NULL,
  `prix` double DEFAULT NULL,
  `nomDeSociete` varchar(30) DEFAULT NULL,
  `villeDepart` varchar(20) DEFAULT NULL,
  `villeDarrivee` varchar(20) DEFAULT NULL,
  `idAutocar` double DEFAULT NULL,
  `CIN` varchar(20) DEFAULT NULL,
  `nomUtilisateur` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Paiment`
--

CREATE TABLE `Paiment` (
  `idPaiment` double NOT NULL,
  `email` varchar(40) DEFAULT NULL,
  `codeCarte` double DEFAULT NULL,
  `CCV` int DEFAULT NULL,
  `dateDexpiration` date DEFAULT NULL,
  `idReservation` double DEFAULT NULL,
  `idTicket` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Reservation`
--

CREATE TABLE `Reservation` (
  `idReservation` double NOT NULL,
  `nbrDePassagers` int DEFAULT NULL,
  `CINvoyageur` varchar(10) DEFAULT NULL,
  `idPaiment` double DEFAULT NULL,
  `idTicket` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Ticket`
--

CREATE TABLE `Ticket` (
  `idTicket` double NOT NULL,
  `numeroDePlace` int DEFAULT NULL,
  `prixTTC` double DEFAULT NULL,
  `counterDePlace` int DEFAULT NULL,
  `NumeroAutocar` int DEFAULT NULL,
  `idReservation` double DEFAULT NULL,
  `idPaiment` double DEFAULT NULL,
  `CIN` varchar(10) DEFAULT NULL,
  `idOffre` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Ville`
--

CREATE TABLE `Ville` (
  `nomDeVille` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Voyageur`
--

CREATE TABLE `Voyageur` (
  `CIN` varchar(10) NOT NULL,
  `nom` varchar(20) DEFAULT NULL,
  `prenom` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Admin`
--
ALTER TABLE `Admin`
  ADD PRIMARY KEY (`nomUtilisateur`,`motDepasse`);

--
-- Index pour la table `Autocar`
--
ALTER TABLE `Autocar`
  ADD PRIMARY KEY (`idAutocar`);

--
-- Index pour la table `Companie`
--
ALTER TABLE `Companie`
  ADD PRIMARY KEY (`nomDeSociete`);

--
-- Index pour la table `Offre`
--
ALTER TABLE `Offre`
  ADD PRIMARY KEY (`idOffre`),
  ADD KEY `OffreAvecAutocar` (`idAutocar`),
  ADD KEY `OffreAvecVoyageur` (`CIN`),
  ADD KEY `OffreAvecVilleD` (`villeDepart`),
  ADD KEY `OffreAvecVilleA` (`villeDarrivee`),
  ADD KEY `OffreAvecAdmin` (`nomUtilisateur`),
  ADD KEY `OffreAvecSociete` (`nomDeSociete`);

--
-- Index pour la table `Paiment`
--
ALTER TABLE `Paiment`
  ADD PRIMARY KEY (`idPaiment`),
  ADD KEY `AvecReservation` (`idReservation`),
  ADD KEY `idTicket` (`idTicket`);

--
-- Index pour la table `Reservation`
--
ALTER TABLE `Reservation`
  ADD PRIMARY KEY (`idReservation`),
  ADD KEY `AvecVoyageur` (`CINvoyageur`),
  ADD KEY `AvecPaiment` (`idPaiment`),
  ADD KEY `idTicket` (`idTicket`);

--
-- Index pour la table `Ticket`
--
ALTER TABLE `Ticket`
  ADD PRIMARY KEY (`idTicket`),
  ADD KEY `TicketAvecReservation` (`idReservation`),
  ADD KEY `TicketAvecPaiment` (`idPaiment`),
  ADD KEY `TicketAvecVoyageur` (`CIN`),
  ADD KEY `TicketAvecOffre` (`idOffre`);

--
-- Index pour la table `Ville`
--
ALTER TABLE `Ville`
  ADD PRIMARY KEY (`nomDeVille`);

--
-- Index pour la table `Voyageur`
--
ALTER TABLE `Voyageur`
  ADD PRIMARY KEY (`CIN`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Offre`
--
ALTER TABLE `Offre`
  MODIFY `idOffre` double NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `Paiment`
--
ALTER TABLE `Paiment`
  MODIFY `idPaiment` double NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `Ticket`
--
ALTER TABLE `Ticket`
  MODIFY `idTicket` double NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Offre`
--
ALTER TABLE `Offre`
  ADD CONSTRAINT `OffreAvecAdmin` FOREIGN KEY (`nomUtilisateur`) REFERENCES `Admin` (`nomUtilisateur`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `OffreAvecAutocar` FOREIGN KEY (`idAutocar`) REFERENCES `Autocar` (`idAutocar`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `OffreAvecSociete` FOREIGN KEY (`nomDeSociete`) REFERENCES `Companie` (`nomDeSociete`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `OffreAvecVilleA` FOREIGN KEY (`villeDarrivee`) REFERENCES `Ville` (`nomDeVille`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `OffreAvecVilleD` FOREIGN KEY (`villeDepart`) REFERENCES `Ville` (`nomDeVille`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `OffreAvecVoyageur` FOREIGN KEY (`CIN`) REFERENCES `Voyageur` (`CIN`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Contraintes pour la table `Paiment`
--
ALTER TABLE `Paiment`
  ADD CONSTRAINT `AvecReservation` FOREIGN KEY (`idReservation`) REFERENCES `Reservation` (`idReservation`),
  ADD CONSTRAINT `Paiment_ibfk_1` FOREIGN KEY (`idTicket`) REFERENCES `Ticket` (`idTicket`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Contraintes pour la table `Reservation`
--
ALTER TABLE `Reservation`
  ADD CONSTRAINT `AvecPaiment` FOREIGN KEY (`idPaiment`) REFERENCES `Paiment` (`idPaiment`),
  ADD CONSTRAINT `AvecVoyageur` FOREIGN KEY (`CINvoyageur`) REFERENCES `Voyageur` (`CIN`),
  ADD CONSTRAINT `Reservation_ibfk_1` FOREIGN KEY (`idTicket`) REFERENCES `Ticket` (`idTicket`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Contraintes pour la table `Ticket`
--
ALTER TABLE `Ticket`
  ADD CONSTRAINT `TicketAvecOffre` FOREIGN KEY (`idOffre`) REFERENCES `Offre` (`idOffre`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `TicketAvecPaiment` FOREIGN KEY (`idPaiment`) REFERENCES `Paiment` (`idPaiment`),
  ADD CONSTRAINT `TicketAvecReservation` FOREIGN KEY (`idReservation`) REFERENCES `Reservation` (`idReservation`),
  ADD CONSTRAINT `TicketAvecVoyageur` FOREIGN KEY (`CIN`) REFERENCES `Voyageur` (`CIN`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
