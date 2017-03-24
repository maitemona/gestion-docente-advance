-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-03-2017 a las 13:37:06
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
CREATE DATABASE IF NOT EXISTS `gestiondocente` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `gestiondocente`;

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `alumnoCreate`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `alumnoCreate` (IN `papellidos` VARCHAR(250), IN `pcodigopostal` INT(5), IN `pdireccion` VARCHAR(250), IN `pdni` VARCHAR(9), IN `pemail` VARCHAR(150), IN `pfNacimiento` DATE, IN `pnHermanos` INT(2), IN `pnombre` VARCHAR(50), IN `ppoblacion` VARCHAR(150), IN `ptelefono` INT(9), OUT `pcodigo` INT)  BEGIN



INSERT	INTO alumno(nombre,apellidos,dni,email,direccion,codigopostal,poblacion,fNacimiento,telefono,nHermanos)

VALUES(LOWER(pnombre),LOWER(papellidos),LOWER(pdni),LOWER(pemail),LOWER(pdireccion),pcodigopostal,LOWER(ppoblacion),pfNacimiento,ptelefono,pnHermanos);

SET pcodigo = LAST_INSERT_ID();



END$$

DROP PROCEDURE IF EXISTS `alumnoDelete`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `alumnoDelete` (IN `pcodigo` INT)  NO SQL
BEGIN



DELETE FROM alumno WHERE codigo = pcodigo;



END$$

DROP PROCEDURE IF EXISTS `alumnogetAll`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `alumnogetAll` ()  NO SQL
BEGIN

	SELECT `codigo`, `nombre`, `apellidos`, `fNacimiento`, `direccion`, `poblacion`, `codigopostal`, `telefono`, `email`, `dni`, `nHermanos`, `activo` 

	FROM `alumno`;

END$$

DROP PROCEDURE IF EXISTS `alumnogetByCurso`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `alumnogetByCurso` (IN `pcursocodigo` INT)  BEGIN
    SELECT a.codigo as codigo, a.nombre, a.apellidos, a.fNacimiento, a.direccion, a.poblacion, a.codigopostal, a.telefono, a.email, a.dni, a.nHermanos, a.activo
    FROM `alumno` as a 
		inner join imparticion as i ON i.alumno_codigo = a.codigo
        inner join curso as c ON i.curso_codigo = c.codigo
	WHERE c.codigo = pcursocodigo
    group by a.codigo;
        
        
END$$

DROP PROCEDURE IF EXISTS `alumnogetByDni`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `alumnogetByDni` (IN `pdni` VARCHAR(9))  BEGIN
    SELECT `codigo` as codigo, `nombre`, `apellidos`, `fNacimiento`, `direccion`, `poblacion`, `codigopostal`, `telefono`, `email`, `dni`, `nHermanos`, `activo` 
    FROM `alumno`
	WHERE dni = pdni;
END$$

DROP PROCEDURE IF EXISTS `alumnogetById`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `alumnogetById` (IN `pcodigo` INT)  NO SQL
BEGIN



    SELECT `codigo` as codigo, `nombre`, `apellidos`, `fNacimiento`, `direccion`, `poblacion`, `codigopostal`, `telefono`, `email`, `dni`, `nHermanos`, `activo` 

    FROM `alumno`

	WHERE codigo = pcodigo;



END$$

DROP PROCEDURE IF EXISTS `alumnoInforme`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `alumnoInforme` (IN `pcodigo` INT)  BEGIN
	
	SELECT a.codigo as codigo, a.nombre, a.apellidos, a.fNacimiento, a.direccion, a.poblacion, a.codigopostal,
    a.telefono, a.email, a.dni, a.nHermanos, a.activo,
	 cu.codigo as codigocurso,cu.nombre as nombrecurso,cu.identificador as idenficadorcurso,
            cu.fInicio,cu.fFin,cu.nhoras,cu.precio
            
    FROM alumno as a
        LEFT JOIN asistente as asi ON asi.alumno_codigo = a.codigo
        LEFT JOIN imparticion as i ON i.codigo = asi.imparticion_codigo
        LEFT JOIN curso_detalle as cd ON cd.codigo = i.curso_detalle_codigo
        LEFT JOIN curso as cu ON cu.codigo = cd.curso_codigo
    WHERE a.codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `alumnoUpdate`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `alumnoUpdate` (IN `papellidos` VARCHAR(250), IN `pcodigo` INT, IN `pcodigopostal` INT(5), IN `pdireccion` VARCHAR(250), IN `pdni` VARCHAR(9), IN `pemail` VARCHAR(150), IN `pfNacimiento` DATE, IN `pnHermanos` INT(2), IN `pnombre` VARCHAR(150), IN `ppoblacion` VARCHAR(150), IN `ptelefono` INT(9))  NO SQL
BEGIN



UPDATE alumno 

SET nombre = LOWER(pnombre),apellidos = LOWER(papellidos), dni = LOWER(pdni),email = LOWER(pemail),direccion=LOWER(pdireccion),codigopostal=pcodigopostal,poblacion=LOWER(ppoblacion),fNacimiento=pfNacimiento,telefono=ptelefono,nHermanos=pnHermanos

WHERE codigo = pcodigo;





END$$

DROP PROCEDURE IF EXISTS `clienteCreate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clienteCreate` (IN `pnombre` TEXT, IN `pemail` VARCHAR(150), IN `ptelefono` INT, IN `pdireccion` VARCHAR(150), IN `ppoblacion` VARCHAR(150), IN `pcodigopostal` INT(5), IN `pidentificador` INT, OUT `pcodigo` INT)  BEGIN
INSERT INTO cliente(`nombre`, `email`, `telefono`, `direccion`, `poblacion`, `codigopostal`, `identificador`) 
VALUES(LOWER(pnombre),
       LOWER(pemail),
       ptelefono,
       LOWER(pdireccion),
       LOWER(poblacion),
       codigopostal,
       LOWER(pidentificador));
	SET pcodigo = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `clientegetAll`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `clientegetAll` ()  NO SQL
BEGIN

	SELECT`codigo`, `nombre`, `email`, `telefono`, `direccion`, `poblacion`, `codigopostal`,`identificador`,activo

	FROM `cliente`;

END$$

DROP PROCEDURE IF EXISTS `clientegetById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clientegetById` (IN `pcodigo` INT)  BEGIN
	SELECT`codigo`, `nombre`, `email`, `telefono`, `direccion`, `poblacion`, `codigopostal`,`identificador`, activo
	FROM `cliente`
    WHERE codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `clientegetByIdentificador`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clientegetByIdentificador` (IN `pidentificador` VARCHAR(15))  BEGIN
	SELECT`codigo`, `nombre`, `email`, `telefono`, `direccion`, `poblacion`, `codigopostal`,`identificador`, activo
	FROM `cliente`
    WHERE identificaro = pidentificador;
END$$

DROP PROCEDURE IF EXISTS `clienteInforme`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clienteInforme` (IN `pcodigo` INT)  BEGIN
    SELECT c.codigo, c.nombre, c.email, c.telefono, c.direccion, 
            c.poblacion, c.codigopostal, c.identificador,c.activo,
            cu.codigo as codigocurso,cu.nombre as nombrecurso,cu.identificador as idenficadorcurso,
            cu.fInicio,cu.fFin,cu.nhoras,cu.precio
            
    FROM cliente as c
        LEFT JOIN curso as cu ON cu.cliente_codigo = c.codigo
    WHERE c.codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `clienteUpdate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clienteUpdate` (IN `pcodigo` INT, IN `pnombre` VARCHAR(150), IN `pemail` VARCHAR(150), IN `ptelefono` INT(9), IN `pdirecion` VARCHAR(250), IN `ppoblacion` VARCHAR(150), IN `pcodigopostal` INT(5), IN `pidentificador` VARCHAR(15))  BEGIN
UPDATE `cliente` 
SET `codigo`=pcodigo,
	`nombre`=pnombre,
    `email`=pemail,
    `telefono`=ptelefono,
    `direccion`=pdirecion,
    `poblacion`=ppoblacion,
    `codigopostal`=pcodigopostal,
    `identificador`=pidentificador;
END$$

DROP PROCEDURE IF EXISTS `profesorByDni`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `profesorByDni` (IN `dni` VARCHAR(9))  BEGIN
	SELECT `codigo`, `NSS`, `nombre`, `apellidos`, `fNacimiento`, `DNI`, `direccion`, `poblacion`, `codigopostal`, `telefono`, `email`,activo
	FROM `profesor`
    WHERE dni = pdni;
END$$

DROP PROCEDURE IF EXISTS `profesorCreate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `profesorCreate` (IN `pnss` VARCHAR(150), IN `pnombre` VARCHAR(150), IN `papellidos` VARCHAR(250), IN `pfNacimiento` DATE, IN `pdni` VARCHAR(9), IN `pdireccion` VARCHAR(150), IN `ppoblacion` VARCHAR(150), IN `pcodigopostal` INT(5), IN `ptelefono` INT(9), IN `pemail` VARCHAR(150), OUT `pcodigo` INT)  BEGIN
	INSERT INTO `profesor` ( 
        `nombre`, 
        `apellidos`, 
        `fNacimiento`, 
        `DNI`, 
        `direccion`, 
        `poblacion`, 
        `codigopostal`, 
        `telefono`, 
        `email`) 
        VALUES (
        LOWER(pnombre),
        LOWER(papellidos),
		pfNacimiento,
        LOWER(pdni),
        LOWER(pdireccion),
        LOWER(poblacion),
		pcodigopostal,
		ptelefono,
        LOWER(pemail)
        );
        SET pcodigo = LAST_INSERT_ID();
        if pnss != '' THEN
			update profesor set nss = pnss where codigo = pcodigo;
        END IF;
END$$

DROP PROCEDURE IF EXISTS `profesorgetAll`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `profesorgetAll` ()  NO SQL
BEGIN



	SELECT `codigo`, `NSS`, `nombre`, `apellidos`, `fNacimiento`, `DNI`, `direccion`, `poblacion`, `codigopostal`, `telefono`, `email`,activo

	FROM `profesor`;



END$$

DROP PROCEDURE IF EXISTS `profesorgetByDni`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `profesorgetByDni` (IN `pdni` VARCHAR(9))  BEGIN
	SELECT `codigo`, `NSS`, `nombre`, `apellidos`, `fNacimiento`, `DNI`, `direccion`, `poblacion`, `codigopostal`, `telefono`, `email`,activo
	FROM `profesor`
    WHERE dni = pdni;
END$$

DROP PROCEDURE IF EXISTS `profesorgetById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `profesorgetById` (IN `pcodigo` INT)  BEGIN
	SELECT `codigo`, `NSS`, `nombre`, `apellidos`, `fNacimiento`, `DNI`, `direccion`, `poblacion`, `codigopostal`, `telefono`, `email`,activo
	FROM `profesor`
    WHERE codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `profesorgetByNss`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `profesorgetByNss` (IN `pnss` VARCHAR(12))  BEGIN
	SELECT `codigo`, `NSS`, `nombre`, `apellidos`, `fNacimiento`, `DNI`, `direccion`, `poblacion`, `codigopostal`, `telefono`, `email`,activo
	FROM `profesor`
    WHERE nss = pnss;
END$$

DROP PROCEDURE IF EXISTS `profesorUpdate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `profesorUpdate` (IN `pnss` VARCHAR(12), IN `pnombre` VARCHAR(150), IN `papellidos` VARCHAR(250), IN `pfnacimiento` DATE, IN `pdni` VARCHAR(9), IN `pdireccion` VARCHAR(250), IN `ppoblacion` VARCHAR(150), IN `pcodigopostal` INT(5), IN `ptelefono` INT(9), IN `pemail` VARCHAR(150))  BEGIN

UPDATE `profesor` 
SET `NSS`=pnss,
    `nombre`=pnombre,
    `apellidos`=papellidos,
    `fNacimiento`=pfnacimiento,
    `DNI`=pdni,
    `direccion`=pdireccion,
    `poblacion`=ppoblacion,
    `codigopostal`=pcodigopostal,
    `telefono`=ptelefono,
    `email`=pemail
WHERE codigo = pcodigo;


END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

DROP TABLE IF EXISTS `alumno`;
CREATE TABLE IF NOT EXISTS `alumno` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT COMMENT 'el campo clave de la tabla. Es auto generado.',
  `nombre` varchar(50) COLLATE utf8_bin NOT NULL,
  `apellidos` varchar(250) COLLATE utf8_bin NOT NULL,
  `fNacimiento` date DEFAULT NULL,
  `direccion` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `poblacion` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `codigopostal` int(5) UNSIGNED ZEROFILL DEFAULT NULL,
  `telefono` int(9) NOT NULL,
  `email` varchar(150) COLLATE utf8_bin NOT NULL,
  `dni` varchar(9) COLLATE utf8_bin NOT NULL,
  `nHermanos` int(2) DEFAULT '0',
  `activo` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `dni_UNIQUE` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`codigo`, `nombre`, `apellidos`, `fNacimiento`, `direccion`, `poblacion`, `codigopostal`, `telefono`, `email`, `dni`, `nHermanos`, `activo`) VALUES
(0, 'alumno', 'sin asignar', NULL, NULL, NULL, 00000, 0, 'aaaaaaa@aaaaa.com', '0000000x', 0, 1),
(1, 'sergio', 'aparicio vargas', '1977-12-01', '', '', 00000, 944110293, 'aaaa@aaaa.com', '44974398z', 0, 1),
(2, 'maite', 'monasterio', '1986-11-11', '', '', 48007, 944110293, 'mmonasterio@gmail.com', '16071559x', 0, 1),
(4, 'enrique javier', 'ruiz jimenez', '2017-02-14', '', '', 00048, 944110239, 'enrique@gmail.com', '45677362y', 0, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` text COLLATE utf8_bin NOT NULL,
  `email` varchar(150) COLLATE utf8_bin NOT NULL,
  `telefono` int(9) NOT NULL,
  `direccion` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `poblacion` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `codigopostal` int(5) UNSIGNED ZEROFILL DEFAULT NULL,
  `identificador` varchar(15) COLLATE utf8_bin NOT NULL,
  `activo` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`codigo`, `nombre`, `email`, `telefono`, `direccion`, `poblacion`, `codigopostal`, `identificador`, `activo`) VALUES
(1, 'SERIKAT CONSULTORÍA E INFORMÁTICA, S.A.', 'info@serikat.es', 944250100, 'c/ Ercilla 19', 'Bilbao', 48009, 'A-48476006', 1),
(2, 'lanbide - servicio vasco de empleo', 'info@lanbide.net', 945160601, 'Jose Atxotegi 1', 'Vitoria-Gazteiz', 01009, 'Q0100571l', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso`
--

DROP TABLE IF EXISTS `curso`;
CREATE TABLE IF NOT EXISTS `curso` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8_bin NOT NULL,
  `identificador` varchar(12) COLLATE utf8_bin NOT NULL,
  `fInicio` date DEFAULT NULL,
  `fFin` date DEFAULT NULL,
  `nHoras` int(4) NOT NULL,
  `temario` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `activo` tinyint(4) DEFAULT '1',
  `cliente_codigo` int(11) DEFAULT NULL,
  `precio` double(8,2) DEFAULT '0.00',
  `profesor_codigo` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_curso_cliente_codigo_idx` (`cliente_codigo`),
  KEY `fk_curso_profesor_codigo_idx` (`profesor_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `curso`
--

INSERT INTO `curso` (`codigo`, `nombre`, `identificador`, `fInicio`, `fFin`, `nHoras`, `temario`, `activo`, `cliente_codigo`, `precio`, `profesor_codigo`) VALUES
(1, 'Desarrrollo de Aplicaciones con Tecnologias Web', '18482673', '2017-01-09', '2017-06-13', 510, NULL, 1, 1, 300000.00, NULL),
(2, 'Desarrollo de Bases de Datos y Programacion orientada a Objetos', '18488229', '2017-02-20', '2017-09-29', 630, NULL, 1, 1, 400000.00, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `imparticion`
--

DROP TABLE IF EXISTS `imparticion`;
CREATE TABLE IF NOT EXISTS `imparticion` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `curso_codigo` int(11) NOT NULL,
  `alumno_codigo` int(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_imparticion_alumno_codigo_idx` (`alumno_codigo`),
  KEY `fk_imparticion_curso_codigo_idx` (`curso_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `imparticion`
--

INSERT INTO `imparticion` (`codigo`, `curso_codigo`, `alumno_codigo`) VALUES
(1, 1, 1),
(2, 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor`
--

DROP TABLE IF EXISTS `profesor`;
CREATE TABLE IF NOT EXISTS `profesor` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `NSS` bigint(12) DEFAULT NULL,
  `nombre` varchar(50) COLLATE utf8_bin NOT NULL,
  `apellidos` varchar(250) COLLATE utf8_bin NOT NULL,
  `fNacimiento` date DEFAULT NULL,
  `DNI` varchar(9) COLLATE utf8_bin NOT NULL,
  `direccion` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `poblacion` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `codigopostal` int(5) UNSIGNED ZEROFILL DEFAULT NULL,
  `telefono` int(9) NOT NULL,
  `email` varchar(150) COLLATE utf8_bin NOT NULL,
  `activo` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `profesor`
--

INSERT INTO `profesor` (`codigo`, `NSS`, `nombre`, `apellidos`, `fNacimiento`, `DNI`, `direccion`, `poblacion`, `codigopostal`, `telefono`, `email`, `activo`) VALUES
(0, 0, 'profesor', 'sin asignar', NULL, '00000000z', NULL, NULL, NULL, 0, 'aaaaaaaa@aaaaa.aaa', 1),
(1, 481234567840, 'Urko', 'Villanueva Alvarez', '1976-11-24', '30693142x', 'Av. Mazustegi 9', 'Bilbao', 48009, 944110293, 'uvillanueva@ipartek.com', 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `curso`
--
ALTER TABLE `curso`
  ADD CONSTRAINT `fk_curso_cliente_codigo` FOREIGN KEY (`cliente_codigo`) REFERENCES `cliente` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_curso_profesor_codigo` FOREIGN KEY (`profesor_codigo`) REFERENCES `profesor` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `imparticion`
--
ALTER TABLE `imparticion`
  ADD CONSTRAINT `fk_imparticion_alumno_codigo` FOREIGN KEY (`alumno_codigo`) REFERENCES `alumno` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_imparticion_curso_codigo` FOREIGN KEY (`curso_codigo`) REFERENCES `curso` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
