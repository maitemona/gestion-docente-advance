-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-02-2017 a las 10:24:30
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

DROP PROCEDURE IF EXISTS `alumnogetByDni`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `alumnogetByDni` (IN `pdni` VARCHAR(9))  NO SQL
BEGIN 
		SELECT a.codigo as codigo, a.nombre as nombre, a.apellidos as 					apellidos, a.activo as activo, a.codigopostal as codigopostal,a.direccion as direccion, a.dni as dni, a.email as email, a.fNacimiento as fnacimiento,a.nHermanos as nhermanos, a.poblacion as poblacion, a.telefono as telefono
    FROM alumno as a WHERE dni = pdni;
END$$

DROP PROCEDURE IF EXISTS `alumnogetById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `alumnogetById` (IN `pcodigo` INT)  NO SQL
BEGIN 
		SELECT a.codigo as codigo, a.nombre as nombre, a.apellidos as 					apellidos, a.activo as activo, a.codigopostal as codigopostal,a.direccion as direccion, a.dni as dni, a.email as email, a.fNacimiento as fnacimiento,a.nHermanos as nhermanos, a.poblacion as poblacion, a.telefono as telefono
    FROM alumno as a WHERE codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `alumnoInforme`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `alumnoInforme` (IN `pcodigo` INT)  NO SQL
BEGIN
SELECT 
a.codigo, a.nombre, a.apellidos,a.fNacimiento,a.direccion,a.poblacion,a.poblacion,a.codigopostal,a.telefono,a.email,a.dni,a.nHermanos,a.activo,

c.codigo as codigocurso,c.nombre as nombrecurso,
c.identificador as idenficadorcurso,
c.fInicio,c.fFin,c.nhoras,c.precio
FROM alumno as a
LEFT JOIN asistente as asi ON asi.alumno_codigo= a.codigo 
LEFT Join imparticion as i ON i.codigo = asi.imparticion_codigo  
LEFT JOIN curso_detalle as cd ON cd.codigo = i.curso_detalle_codigo
LEFT join curso as c on c.codigo= cd.curso_codigo
WHERE a.codigo=pcodigo;
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

DROP PROCEDURE IF EXISTS `clientegetByDni`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clientegetByDni` (IN `pidentificador` VARCHAR(200))  NO SQL
BEGIN
SELECT c.codigo as codigo, c.nombre as nombre,c.activo as activo,c.direccion as direccion, c.email as email , c.telefono as telefono, c.identificador as identificador
FROM cliente as c WHERE lower(c.identificador) = lower(pidentificador);
END$$

DROP PROCEDURE IF EXISTS `clientegetById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clientegetById` (IN `pcodigo` INT)  NO SQL
BEGIN
SELECT c.codigo as codigo, c.nombre as nombre,c.activo as activo,c.direccion as direccion, c.email as email , c.telefono as telefono, c.identificador as identificador
FROM cliente as c WHERE c.codigo = pcodigo;

END$$

DROP PROCEDURE IF EXISTS `clienteInforme`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clienteInforme` (IN `pcodigo` INT)  NO SQL
BEGIN
 SELECT c.codigo, c.nombre, c.email, c.telefono, c.direccion, 
            c.poblacion, c.codigopostal, c.identificador,c.activo,
            cu.codigo as codigocurso,cu.nombre as nombrecurso,cu.identificador as idenficadorcurso,
            cu.fInicio,cu.fFin,cu.nhoras,cu.precio
    FROM cliente as c
        LEFT JOIN curso as cu ON cu.cliente_codigo = c.codigo
        INNER JOIN curso_detalle as cd ON cd.curso_codigo = cu.codigo
    
    WHERE c.codigo = pcodigo;

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

DROP PROCEDURE IF EXISTS `profesorgetByDni`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `profesorgetByDni` (IN `pdni` VARCHAR(9))  NO SQL
BEGIN
    SELECT p.codigo as codigo, p.nombre as nombre, p.apellidos as 					apellidos, p.activo as activo, p.codigopostal as codigopostal,p.direccion as direccion, p.dni as dni, p.email as email, p.fNacimiento as fnacimiento,p.nSS as nss, p.poblacion as poblacion, p.telefono as telefono
FROM profesor as p WHERE LOWER(p.dni) = LOWER( pdni);
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
  `codigopostal` int(5) UNSIGNED ZEROFILL DEFAULT NULL,
  `telefono` int(9) NOT NULL,
  `email` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `dni` varchar(9) COLLATE utf8_unicode_ci NOT NULL COMMENT 'clave unica',
  `nHermanos` int(2) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `dni_UNIQUE` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`codigo`, `nombre`, `apellidos`, `fNacimiento`, `direccion`, `poblacion`, `codigopostal`, `telefono`, `email`, `dni`, `nHermanos`, `activo`) VALUES
(0, 'alumno', 'oooooooooo', '2017-02-08', 'oooooooooo', 'ooooooo', 00000, 0, 'm@g.com', '000000000', 0, 1),
(1, 'Maite', 'monasterio herrero', '1979-08-12', 'kasune 12 c', 'getxo', 48891, 654789123, 'maitemonasterio@gmail.com', '16071559x', 2, 1),
(2, 'Paco', 'Perez lopez', '1979-08-12', 'kasune 12 c', 'getxo', 48891, 654789123, 'pacoperez@gmail.com', '16071558d', 4, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asistente`
--

DROP TABLE IF EXISTS `asistente`;
CREATE TABLE IF NOT EXISTS `asistente` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `alumno_codigo` int(11) NOT NULL,
  `imparticion_codigo` int(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_asistente_alumno_codigo` (`alumno_codigo`),
  KEY `fk_asistente_imparticion_codigo` (`imparticion_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `asistente`
--

INSERT INTO `asistente` (`codigo`, `alumno_codigo`, `imparticion_codigo`) VALUES
(4, 1, 2),
(5, 2, 3),
(6, 1, 1),
(7, 2, 3);

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
  `poblacion` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `codigopostal` int(10) UNSIGNED ZEROFILL DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`codigo`, `nombre`, `direccion`, `telefono`, `email`, `identificador`, `activo`, `poblacion`, `codigopostal`) VALUES
(0, 'oooooo', 'oooooo', 0, 'o@gmail.com', '00000000000', 1, 'ooooooooo', 0000000000),
(1, 'ipartek-css-ii', 'bilbao', 944661303, 'maitemonasterio@gmail.com', '16047889x', 1, NULL, NULL),
(2, 'maria', 'bilbao', 944661303, 'm@gmail.com', '4852613', 1, NULL, NULL),
(3, 'castaño', 'bilbao', 944641306, 'ccc@gmail.com', '123456', 1, NULL, NULL),
(4, 'fffff', '', 944661303, 'ffff', 'fffffffffff', 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso`
--

DROP TABLE IF EXISTS `curso`;
CREATE TABLE IF NOT EXISTS `curso` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `identificador` varchar(12) COLLATE utf8_unicode_ci NOT NULL,
  `fFin` date DEFAULT NULL,
  `fInicio` date DEFAULT NULL,
  `nHoras` int(4) NOT NULL,
  `temario` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cliente_codigo` int(11) DEFAULT NULL,
  `precio` double(8,2) DEFAULT '0.00',
  PRIMARY KEY (`codigo`),
  KEY `fk_curso_cliente_codigo` (`cliente_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `curso`
--

INSERT INTO `curso` (`codigo`, `nombre`, `identificador`, `fFin`, `fInicio`, `nHoras`, `temario`, `cliente_codigo`, `precio`) VALUES
(1, 'Cuso de java', '456123', '2017-02-15', '2017-02-05', 30, NULL, 1, 800.20),
(2, 'Cuso de spring', '456123', '2017-02-15', '2017-02-05', 30, NULL, 1, 800.20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso_detalle`
--

DROP TABLE IF EXISTS `curso_detalle`;
CREATE TABLE IF NOT EXISTS `curso_detalle` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `curso_codigo` int(11) DEFAULT NULL,
  `modulo_codigo` int(11) DEFAULT NULL,
  `fFin` date NOT NULL,
  `fInicio` date NOT NULL,
  `precio` double(7,2) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_curso_modulo_curso_codigo_idx` (`curso_codigo`),
  KEY `FK_cursomodulo_modulo_codigo_idx` (`modulo_codigo`),
  KEY `uq_curso_codigo_modulo_codigo` (`curso_codigo`,`modulo_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

--
-- Volcado de datos para la tabla `curso_detalle`
--

INSERT INTO `curso_detalle` (`codigo`, `curso_codigo`, `modulo_codigo`, `fFin`, `fInicio`, `precio`) VALUES
(1, 1, 1, '2017-02-15', '2017-02-05', 800.20),
(3, 2, 2, '2017-02-15', '2017-02-05', 800.20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evaluacion`
--

DROP TABLE IF EXISTS `evaluacion`;
CREATE TABLE IF NOT EXISTS `evaluacion` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `imparticion_codigo` int(11) NOT NULL,
  `alumno_codigo` int(11) NOT NULL,
  `fExamen` date NOT NULL,
  `nota` int(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_evaluacion_modulo_idx` (`imparticion_codigo`),
  KEY `fk_evaluacion_alumno_idx` (`alumno_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `imparticion`
--

DROP TABLE IF EXISTS `imparticion`;
CREATE TABLE IF NOT EXISTS `imparticion` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `curso_detalle_codigo` int(11) DEFAULT NULL,
  `profesor_codigo` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_imparticion_profesor_codigo_idx` (`profesor_codigo`),
  KEY `fk_imparticion_curso_detalle_codigo` (`curso_detalle_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `imparticion`
--

INSERT INTO `imparticion` (`codigo`, `curso_detalle_codigo`, `profesor_codigo`) VALUES
(1, 1, 1),
(2, 3, 2),
(3, 1, 1),
(4, 3, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modulo`
--

DROP TABLE IF EXISTS `modulo`;
CREATE TABLE IF NOT EXISTS `modulo` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `nHoras` int(3) NOT NULL,
  `descripcion` text COLLATE utf8_unicode_ci,
  `precio` double(7,2) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `modulo`
--

INSERT INTO `modulo` (`codigo`, `nombre`, `nHoras`, `descripcion`, `precio`) VALUES
(1, 'JEE2', 30, 'desarrollo de aplicacion de web con java', 800.20),
(2, 'Spring', 30, 'desarrollo de aplicacion de web con java', 800.20);

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
  `codigopostal` int(5) UNSIGNED ZEROFILL DEFAULT NULL,
  `telefono` int(9) NOT NULL,
  `email` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `activo` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `profesor`
--

INSERT INTO `profesor` (`codigo`, `nSS`, `nombre`, `apellidos`, `fNacimiento`, `dni`, `direccion`, `poblacion`, `codigopostal`, `telefono`, `email`, `activo`) VALUES
(0, 0, 'ooooooooo', 'oooooooo', '2017-02-20', '111111111', 'oooooooooo', 'oooooooo', 00000, 0, 'm@gmail.com', 1),
(1, 481245678, 'Urko', 'Villanueva Romero', '1975-05-10', '16071559x', 'Palado nº 12', 'Munguia', 48100, 94456213, 'urkovillanueva@ipartek.com', 1),
(2, 48012547, 'rosa blab', 'perez lopez', '1978-12-20', '16071558z', 'gran via', 'bilbao', 48991, 94466130, 'rosa-lopez@gmail.com', 1),
(3, 48555, 'prueba', 'prueba y prueba', '1978-12-20', '16071558d', 'prueba', 'prueba', 48991, 94466130, 'm@gmail.com', 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asistente`
--
ALTER TABLE `asistente`
  ADD CONSTRAINT `fk_asistente_alumno_codigo` FOREIGN KEY (`alumno_codigo`) REFERENCES `alumno` (`codigo`),
  ADD CONSTRAINT `fk_asistente_imparticion_codigo` FOREIGN KEY (`imparticion_codigo`) REFERENCES `imparticion` (`codigo`);

--
-- Filtros para la tabla `curso`
--
ALTER TABLE `curso`
  ADD CONSTRAINT `fk_curso_cliente_codigo` FOREIGN KEY (`cliente_codigo`) REFERENCES `cliente` (`codigo`);

--
-- Filtros para la tabla `curso_detalle`
--
ALTER TABLE `curso_detalle`
  ADD CONSTRAINT `fk_curso_detalle_curso_codigo` FOREIGN KEY (`curso_codigo`) REFERENCES `curso` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_curso_detalle_modulo_codigo` FOREIGN KEY (`modulo_codigo`) REFERENCES `modulo` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `evaluacion`
--
ALTER TABLE `evaluacion`
  ADD CONSTRAINT `fk_evaluacion_alumno_codigo` FOREIGN KEY (`alumno_codigo`) REFERENCES `alumno` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_evaluacion_imparticion_codigo` FOREIGN KEY (`imparticion_codigo`) REFERENCES `imparticion` (`codigo`);

--
-- Filtros para la tabla `imparticion`
--
ALTER TABLE `imparticion`
  ADD CONSTRAINT `fk_imparticion_curso_detalle_codigo` FOREIGN KEY (`curso_detalle_codigo`) REFERENCES `curso_detalle` (`codigo`),
  ADD CONSTRAINT `fk_imparticion_profesor_codigo` FOREIGN KEY (`profesor_codigo`) REFERENCES `profesor` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
