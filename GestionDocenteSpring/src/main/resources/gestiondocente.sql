-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-02-2017 a las 11:03:23
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestiondocente`
--
CREATE DATABASE IF NOT EXISTS `gestiondocente` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `gestiondocente`;

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `alumnoCreate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `alumnoCreate` (IN `papellidos` VARCHAR(250), IN `ppostal` INT(5), IN `pdireccion` VARCHAR(250), IN `pdni` VARCHAR(9), IN `pemail` VARCHAR(150), IN `pfnacimiento` DATE, IN `pnhermanos` INT(2), IN `pnombre` VARCHAR(50), IN `ppoblacion` VARCHAR(150), IN `ptelefono` INT(9), OUT `pcodigo` INT)  NO SQL
BEGIN
INSERT INTO alumno (nombre,apellidos,dni,email,direccion,codigopostal,poblacion,fNacimiento,telefono,nHermanos) 
VALUES
(LOWER(pnombre),LOWER(papellidos),LOWER(pdni),LOWER(pemail),LOWER(pdireccion),ppostal,LOWER(ppoblacion),pfnacimiento,ptelefono,pnhermanos);
SET pcodigo = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `alumnoDelete`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `alumnoDelete` (IN `pcodigo` INT)  NO SQL
BEGIN
DELETE FROM alumno where codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `alumnogetAll`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `alumnogetAll` ()  NO SQL
BEGIN 
	SELECT a.codigo as codigo, a.nombre as nombre, a.apellidos as 					apellidos, a.activo as activo, a.codigopostal as codigopostal,a.direccion as direccion, a.dni as dni, a.email as email, a.fNacimiento as fnacimiento,a.nHermanos as nhermanos, a.poblacion as poblacion, a.telefono as telefono
    FROM alumno as a;
END$$

DROP PROCEDURE IF EXISTS `alumnogetById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `alumnogetById` (IN `pcodigo` INT)  NO SQL
BEGIN 
		SELECT a.codigo as codigo, a.nombre as nombre, a.apellidos as 					apellidos, a.activo as activo, a.codigopostal as codigopostal,a.direccion as direccion, a.dni as dni, a.email as email, a.fNacimiento as fnacimiento,a.nHermanos as nhermanos, a.poblacion as poblacion, a.telefono as telefono
    FROM alumno as a WHERE codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `alumnoUpdate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `alumnoUpdate` (IN `papellidos` VARCHAR(250), IN `pcodigo` INT, IN `ppostal` INT(5), IN `pdireccion` VARCHAR(250), IN `pdni` VARCHAR(9), IN `pemail` VARCHAR(150), IN `pfnacimiento` DATE, IN `pnhermanos` INT(2), IN `pnombre` VARCHAR(50), IN `ppoblacion` VARCHAR(250), IN `ptelefono` INT(9))  NO SQL
BEGIN
UPDATE alumno SET 
nombre = LOWER(pnombre), apellidos= LOWER(papellidos), dni = LOWER(pdni),codigopostal= ppostal,  direccion=LOWER(pdireccion), email=LOWER(pemail), fNacimiento= pfnacimiento,  nHermanos = pnhermanos, poblacion = LOWER(ppoblacion), telefono = ptelefono
WHERE codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `clienteCreate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clienteCreate` (IN `pnombre` VARCHAR(50), IN `pdireccion` VARCHAR(250), IN `ptelefono` INT(9), IN `pemail` VARCHAR(150), IN `pidentificador` VARCHAR(200), OUT `pcodigo` INT)  NO SQL
BEGIN
INSERT INTO cliente (nombre,telefono,identificador,email,direccion)
VALUES
(LOWER(pnombre),ptelefono,LOWER(pidentificador),LOWER(pemail),LOWER(pdireccion));
SET pcodigo = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `clienteDelete`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clienteDelete` (IN `pcodigo` INT)  NO SQL
BEGIN
DELETE FROM cliente where codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `clientegetAll`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clientegetAll` ()  NO SQL
BEGIN 
	SELECT c.codigo as codigo, c.nombre as nombre, c.activo as activo,c.direccion as direccion, c.identificador as identificador, c.email as email,c.telefono as telefono
    FROM cliente as c;
END$$

DROP PROCEDURE IF EXISTS `clientegetById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clientegetById` (IN `pcodigo` INT)  NO SQL
BEGIN
SELECT c.codigo as codigo, c.nombre as nombre,c.activo as activo,c.direccion as direccion, c.email as email , c.telefono as telefono, c.identificador as identificador
FROM cliente as c WHERE c.codigo = pcodigo;

END$$

DROP PROCEDURE IF EXISTS `clienteUpdate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clienteUpdate` (IN `pnombre` VARCHAR(50), IN `pdireccion` VARCHAR(250), IN `ptelefono` INT(9), IN `pidentificador` VARCHAR(200), IN `pemail` VARCHAR(250), IN `pcodigo` INT)  NO SQL
BEGIN
UPDATE cliente SET 
nombre = LOWER(pnombre), identificador = LOWER(pidentificador),  direccion=LOWER(pdireccion), email=LOWER(pemail), telefono = ptelefono
WHERE codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `profesorCreate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `profesorCreate` (IN `pnombre` VARCHAR(50), IN `papellidos` VARCHAR(150), IN `pnss` BIGINT(12), IN `pfnacimiento` DATE, IN `pdni` VARCHAR(9), IN `pdireccion` VARCHAR(250), IN `ppoblacion` VARCHAR(150), IN `ppostal` INT(5), IN `ptelefono` INT(9), IN `pemail` VARCHAR(150), OUT `pcodigo` INT)  NO SQL
BEGIN
INSERT INTO profesor (nombre,apellidos,dni,email,direccion,codigopostal,poblacion,fNacimiento,telefono,nSS) 
VALUES
(LOWER(pnombre),LOWER(papellidos),LOWER(pdni),LOWER(pemail),LOWER(pdireccion),ppostal,LOWER(ppoblacion),pfnacimiento,ptelefono,pnss);
SET pcodigo = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `profesorDelete`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `profesorDelete` (IN `pcodigo` INT)  NO SQL
BEGIN
DELETE FROM profesor where codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `profesorgetAll`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `profesorgetAll` ()  NO SQL
BEGIN 
	SELECT p.codigo as codigo, p.nombre as nombre, p.apellidos as 					apellidos, p.activo as activo, p.codigopostal as codigopostal,p.direccion as direccion, p.dni as dni, p.email as email, p.fNacimiento as fnacimiento,p.nSS as nss, p.poblacion as poblacion, p.telefono as telefono
  FROM profesor as p;
END$$

DROP PROCEDURE IF EXISTS `profesorgetById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `profesorgetById` (IN `pcodigo` INT)  NO SQL
BEGIN 
		SELECT p.codigo as codigo, p.nombre as nombre, p.apellidos as 					apellidos, p.activo as activo, p.codigopostal as codigopostal,p.direccion as direccion, p.dni as dni, p.email as email, p.fNacimiento as fnacimiento,p.nSS as nss, p.poblacion as poblacion, p.telefono as telefono
FROM profesor as p WHERE p.codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `profesorUpdate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `profesorUpdate` (IN `pnombre` VARCHAR(50), IN `papellidos` VARCHAR(150), IN `pnss` BIGINT(12), IN `pfnacimiento` DATE, IN `pdni` VARCHAR(9), IN `pdireccion` VARCHAR(250), IN `ppostal` INT(5), IN `ptelefono` INT(9), IN `pemail` VARCHAR(150), IN `pcodigo` INT, IN `ppoblacion` VARCHAR(150))  NO SQL
BEGIN
UPDATE profesor SET 
nombre = LOWER(pnombre), apellidos= LOWER(papellidos), dni = LOWER(pdni),codigopostal= ppostal,  direccion=LOWER(pdireccion), email=LOWER(pemail), fNacimiento= pfnacimiento, nSS = pnss, poblacion = LOWER(ppoblacion), telefono = ptelefono
WHERE codigo = pcodigo;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

DROP TABLE IF EXISTS `alumno`;
CREATE TABLE IF NOT EXISTS `alumno` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `apellidos` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `fNacimiento` date DEFAULT NULL,
  `direccion` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `poblacion` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `codigopostal` int(5) DEFAULT NULL,
  `telefono` int(9) NOT NULL,
  `email` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `dni` varchar(9) COLLATE utf8_unicode_ci NOT NULL,
  `nHermanos` int(2) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`codigo`, `nombre`, `apellidos`, `fNacimiento`, `direccion`, `poblacion`, `codigopostal`, `telefono`, `email`, `dni`, `nHermanos`, `activo`) VALUES
(1, 'maite', 'monasterio herrero', '1987-10-11', 'kasune 12 c', 'getxo', 48891, 654789123, 'maitemonasterio2gmail.com', '16071559x', 2, 1),
(3, 'Rosa', 'Lopez Gomez', '1978-05-05', 'la predreita', 'la rioja', 48997, 654789123, 'rosa@gmail.com', '45698712N', 0, 1),
(4, 'paco', 'perez lopez', '1979-08-12', 'palado estupendo', 'getxo', 48991, 944568789, 'paco@gmail.com', '16071559x', 2, 1),
(5, 'jon', 'lopez perez', '1979-08-12', 'pardo del rey', 'getxo', 48990, 635745896, 'maitemonasterio@gmail.com', '16071559x', 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `direccion` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `telefono` int(9) NOT NULL,
  `email` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `identificador` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `activo` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`codigo`, `nombre`, `direccion`, `telefono`, `email`, `identificador`, `activo`) VALUES
(1, 'ipartek-css', 'bilbao', 944661303, 'maitemonasterio@gmail.com', '16047889x', 1),
(2, 'maria', 'bilbao', 944661303, 'm@gmail.com', '4852613', 1),
(3, 'castaño', 'bilbao', 944641306, 'ccc@gmail.com', '123456', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor`
--

DROP TABLE IF EXISTS `profesor`;
CREATE TABLE IF NOT EXISTS `profesor` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nSS` bigint(12) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `apellidos` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `fNacimiento` date DEFAULT NULL,
  `dni` varchar(9) COLLATE utf8_unicode_ci NOT NULL,
  `direccion` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `poblacion` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `codigopostal` int(5) DEFAULT NULL,
  `telefono` int(9) NOT NULL,
  `email` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `activo` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `profesor`
--

INSERT INTO `profesor` (`codigo`, `nSS`, `nombre`, `apellidos`, `fNacimiento`, `dni`, `direccion`, `poblacion`, `codigopostal`, `telefono`, `email`, `activo`) VALUES
(1, 481245678, 'Urko', 'Villanueva Romero', '1975-05-10', '16047889X', 'Palado nº 12', 'Munguia', 48100, 94456213, 'urkovillanueva@ipartek.com', 1),
(2, 48012547, 'rosa blab', 'perez lopez', '1978-12-20', '16071558z', 'gran via', 'bilbao', 48991, 94466130, 'rosa-lopez@gmail.com', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
