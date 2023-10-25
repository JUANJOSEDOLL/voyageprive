-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-10-2023 a las 00:20:25
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `voyageprive`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hotel`
--

CREATE TABLE `hotel` (
  `ID_Hotel` int(11) NOT NULL,
  `Nombre_hotel` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `PrecioEstancia` float(6,2) NOT NULL,
  `Duracion` int(3) NOT NULL,
  `Ubicacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `Descripcion` varchar(500) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `Fecha_Entrada` varchar(10) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `URL_Imagen` varchar(200) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `Activo` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `hotel`
--

INSERT INTO `hotel` (`ID_Hotel`, `Nombre_hotel`, `PrecioEstancia`, `Duracion`, `Ubicacion`, `Descripcion`, `Fecha_Entrada`, `URL_Imagen`, `Activo`) VALUES
(104, 'The Moon Boutique Hotel', 558.00, 5, 'Sofia', 'Empápese del cálido ambiente de este confortable establecimiento, antigua residencia que data de 1916, hoy transformada en un hotel que fusiona armoniosamente pasado y presente. ', '2023-12-10', '1694084734295.jpg', 1),
(109, 'HOTEL AC', 999.99, 10, 'Estambul', 'Elige pasar tus vacaciones en una de sus modernas y amplias habitaciones y regálate momentos privilegiados en los baños turcos. ¡La forma ideal de sumergirse por completo en las tradiciones del país!  Un pequeño refugio en el corazón de la ciudad, estarás encantado de relajarte después de tus días llenos de visitas.', '2023-12-12', '1694177056037.jpg', 1),
(112, 'Maison Bistro ', 899.15, 10, 'Budapest', 'Te alojarás en un encantador edificio lleno de historia y convertido ya en todo un ícono para los residentes de Budapest. Construido en el siglo XV, auí encontrarás una decoración típica húngara, confortables zonas comunes, una preciosa terraza exterior y un mágico patio con jardín. ', '2023-11-30', '1694555956423.jpg', 0),
(113, 'Roma Imperator', 299.00, 2, 'Roma', 'Bienvenido a uno de los barrios más de moda de Roma. En el Hotel Buenos Aires, encontrarás un establecimiento renovado con gusto y con un ambiente cálido y acogedor. Gracias a su ubicación ideal, a 800 metros de la Galleria Borghese y a 5 minutos de Via Veneto, podrás llegar fácilmente al centro de la ciudad.', '2024-02-05', '1696369543372.jpg', 0),
(126, 'UNAWAY Empire Roma', 1234.95, 12, 'Roma', 'Bienvenido al UNAWAY Empire Roma 4*, un elegante hotel ubicado en el corazón de Roma, a pocos pasos de la famosa Via Veneto.     Diseñado en 1870 por la noble familia Mocenigo como su residencia, hoy recibe a sus huéspedes en habitaciones y suites clásicas, y los envuelve en un ambiente enriquecido por hermosos elementos arquitectónicos y mármoles preciosos.', '2024-02-16', '1697624129132.jpg', 0),
(127, 'Hotel Radison 4*', 399.95, 3, 'Milano', 'Situado en una zona tranquila, cerca del estadio de San Siro, el elegante Arco de la Paz y de los modernos distritos de Porta Nuova y City Life, Radisson Blu Milano 4* es la base ideal para descubrir la capital de la moda.', '2023-11-01', '1698007501257.jpg', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `ID_Reserva` int(11) NOT NULL,
  `ID_Hotel` int(11) DEFAULT NULL,
  `ID_Usuario` int(11) DEFAULT NULL,
  `Fecha_Reserva` text CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `NombreyApellidosHuesped` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `PrecioEstancia` float(6,2) DEFAULT NULL,
  `Duracion` int(100) DEFAULT NULL,
  `NumeroTarjeta` bigint(20) DEFAULT NULL,
  `CVV` int(4) DEFAULT NULL,
  `Email` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `Nombre_hotel` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `Fecha_Entrada` varchar(10) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `Ubicacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `TitularTarjeta` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `VencimientoTarjeta` varchar(10) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`ID_Reserva`, `ID_Hotel`, `ID_Usuario`, `Fecha_Reserva`, `NombreyApellidosHuesped`, `PrecioEstancia`, `Duracion`, `NumeroTarjeta`, `CVV`, `Email`, `Nombre_hotel`, `Fecha_Entrada`, `Ubicacion`, `TitularTarjeta`, `VencimientoTarjeta`) VALUES
(109, 104, 22, '29/9/2023', 'Joan Doll Roche', 558.00, 10, 11111, 333, 'joanjdoll@gmail.com', 'hostal1', '2023-10-10', 'Santiago de Compostela', 'jjjjjjjjjjjjj', '2023-09-30'),
(129, 104, 1, '10/10/2023', 'Joan Doll Roche', 558.00, 10, 7777777, 777, 'joanjdoll@gmail.com', 'hostal1', '2023-10-10', 'Santiago de Compostela', 'yyyyyyyyyy', '2023-10-28'),
(131, 104, 23, '10/10/2023', 'Joan Doll Roche', 558.00, 10, 333333333, 333, 'joanjdoll@gmail.com', 'hostal1', '2023-10-10', 'Santiago de Compostela', 'ssssssssssssssssss', '2023-11-04'),
(132, 113, 1, '10/10/2023', 'Joan Doll Roche', 2001.00, 1, 55555555, 111, 'joanjdoll@gmail.com', 'hotel1', '2023-10-25', 'Zaragoza', 'ssssssssssssssssss', '2023-10-18'),
(135, 109, 20, '13/10/2023', 'jjdr', 1000.99, 10, 123456789, 111, 'jjdr@gmail.com', 'hotel12', '2023-12-12', 'A Coruña', 'jjdr', '2023-10-24'),
(139, 109, 1, '19/10/2023', 'Joan Doll Roche', 999.99, 10, 1234567890, 111, 'joanjdoll@gmail.com', 'HOTEL AC', '2023-12-12', 'Estambul', 'juanjo', '2023-12-10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `email` varchar(40) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `rol` varchar(10) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL DEFAULT 'user'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `email`, `password`, `rol`) VALUES
(1, 'juanjo@gmail.com', '1234', 'user'),
(20, 'jjdr@gmail.com', '1234', 'user'),
(22, 'jjj@gmail.com', '1234', 'user'),
(23, 'jdr@gmail.com', '1234', 'user'),
(66, 'admin@admin.com', '1234', 'admin');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`ID_Hotel`),
  ADD KEY `ID_Hotel` (`ID_Hotel`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`ID_Reserva`),
  ADD UNIQUE KEY `ID_Hotel` (`ID_Hotel`,`ID_Usuario`) USING BTREE,
  ADD KEY `FK_USUARIO` (`ID_Usuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `hotel`
--
ALTER TABLE `hotel`
  MODIFY `ID_Hotel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=128;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `ID_Reserva` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=140;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `FK_USUARIO` FOREIGN KEY (`ID_Usuario`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`ID_Hotel`) REFERENCES `hotel` (`ID_Hotel`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
