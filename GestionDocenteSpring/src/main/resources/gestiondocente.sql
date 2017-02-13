-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-02-2017 a las 13:33:54
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

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `alumnoDelete` (IN `pcodigo` INT)  NO SQL
BEGIN
DELETE FROM alumno where codigo = pcodigo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `alumnogetAll` ()  NO SQL
BEGIN 
	SELECT a.codigo as codigo, a.nombre as nombre, a.apellidos as 					apellido
    FROM alumno as a;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `alumnogetById` (IN `pcodigo` INT)  NO SQL
BEGIN 
	SELECT a.codigo as codigo, a.nombre as nombre, a.apellidos as 					apellido
    FROM alumno as a WHERE codigo = pcodigo;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE `alumno` (
  `codigo` int(11) NOT NULL,
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
  `activo` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`codigo`, `nombre`, `apellidos`, `fNacimiento`, `direccion`, `poblacion`, `codigopostal`, `telefono`, `email`, `dni`, `nHermanos`, `activo`) VALUES
(1, 'Maite', 'Monasterio Herrero', '1978-02-01', 'Kasune 12 c', 'Getxo', 48891, 654789123, 'maitemonasterio2gmail.com', '16071448X', 2, 1),
(3, 'Rosa', 'Lopez Gomez', '1978-05-05', 'la predreita', 'la rioja', 48997, 654789123, 'rosa@gmail.com', '45698712N', 0, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `direccion` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `telefono` int(9) NOT NULL,
  `email` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `identificador` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`codigo`, `nombre`, `direccion`, `telefono`, `email`, `identificador`) VALUES
(1, 'Ipartek', 'palado 12', 654789213, 'maitemonasterioo@gmail.com', '16047889X');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor`
--

CREATE TABLE `profesor` (
  `codigo` int(11) NOT NULL,
  `nSS` bigint(12) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `apellidos` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `fNacimiento` date DEFAULT NULL,
  `dni` varchar(9) COLLATE utf8_unicode_ci NOT NULL,
  `direccion` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `poblacion` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `codigopostal` int(5) DEFAULT NULL,
  `telefono` int(9) NOT NULL,
  `email` varchar(150) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `profesor`
--

INSERT INTO `profesor` (`codigo`, `nSS`, `nombre`, `apellidos`, `fNacimiento`, `dni`, `direccion`, `poblacion`, `codigopostal`, `telefono`, `email`) VALUES
(1, 481245678, 'Urko', 'Villanueva Romero', '1975-05-10', '16047889X', 'Palado nº 12', 'Munguia', 48100, 94456213, 'urkovillanueva@ipartek.com');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `profesor`
--
ALTER TABLE `profesor`
  ADD PRIMARY KEY (`codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumno`
--
ALTER TABLE `alumno`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `profesor`
--
ALTER TABLE `profesor`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
