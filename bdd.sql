-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-08-2023 a las 08:39:08
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
-- Base de datos: `bdsys`
--
CREATE DATABASE IF NOT EXISTS `bdsys` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci;
USE `bdsys`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cargo`
--

CREATE TABLE `cargo` (
  `IDCARGO` int(11) NOT NULL,
  `NOMBRECARGO` varchar(20) DEFAULT NULL,
  `ESTADO` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `cargo`
--

INSERT INTO `cargo` (`IDCARGO`, `NOMBRECARGO`, `ESTADO`) VALUES
(1, 'ADMINISTRADOR', b'1'),
(2, 'VENDEDOR', b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `IDEMPLEADO` int(11) NOT NULL,
  `NOMBRE` varchar(50) DEFAULT NULL,
  `APELLIDOS` varchar(50) DEFAULT NULL,
  `SEXO` char(1) DEFAULT NULL,
  `TELEFONO` char(9) DEFAULT NULL,
  `FECHANACIMIENTO` date DEFAULT NULL,
  `TIPODOCUMENTO` char(1) DEFAULT NULL,
  `NUMERODOCUMENTO` char(11) DEFAULT NULL,
  `IDUSUARIO` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `IDUSUARIO` int(11) NOT NULL,
  `NOMBREUSUARIO` varchar(20) DEFAULT NULL,
  `CLAVE` varchar(10) DEFAULT NULL,
  `ESTADO` bit(1) DEFAULT NULL,
  `IDCARGO` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`IDUSUARIO`, `NOMBREUSUARIO`, `CLAVE`, `ESTADO`, `IDCARGO`) VALUES
(1, 'ALEXANDER', '123', b'1', 1),
(2, 'STEPHANO', '456', b'1', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cargo`
--
ALTER TABLE `cargo`
  ADD PRIMARY KEY (`IDCARGO`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`IDEMPLEADO`),
  ADD KEY `FK_EMPLEADO_USU` (`IDUSUARIO`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`IDUSUARIO`),
  ADD KEY `FK_USUARIO_CARGO` (`IDCARGO`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cargo`
--
ALTER TABLE `cargo`
  MODIFY `IDCARGO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `IDEMPLEADO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `IDUSUARIO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `FK_EMPLEADO_USU` FOREIGN KEY (`IDUSUARIO`) REFERENCES `usuario` (`IDUSUARIO`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FK_USUARIO_CARGO` FOREIGN KEY (`IDCARGO`) REFERENCES `cargo` (`IDCARGO`);
--
-- Base de datos: `phpmyadmin`
--
CREATE DATABASE IF NOT EXISTS `phpmyadmin` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `phpmyadmin`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__bookmark`
--

CREATE TABLE `pma__bookmark` (
  `id` int(10) UNSIGNED NOT NULL,
  `dbase` varchar(255) NOT NULL DEFAULT '',
  `user` varchar(255) NOT NULL DEFAULT '',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `query` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Bookmarks';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__central_columns`
--

CREATE TABLE `pma__central_columns` (
  `db_name` varchar(64) NOT NULL,
  `col_name` varchar(64) NOT NULL,
  `col_type` varchar(64) NOT NULL,
  `col_length` text DEFAULT NULL,
  `col_collation` varchar(64) NOT NULL,
  `col_isNull` tinyint(1) NOT NULL,
  `col_extra` varchar(255) DEFAULT '',
  `col_default` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Central list of columns';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__column_info`
--

CREATE TABLE `pma__column_info` (
  `id` int(5) UNSIGNED NOT NULL,
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `table_name` varchar(64) NOT NULL DEFAULT '',
  `column_name` varchar(64) NOT NULL DEFAULT '',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `mimetype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `transformation` varchar(255) NOT NULL DEFAULT '',
  `transformation_options` varchar(255) NOT NULL DEFAULT '',
  `input_transformation` varchar(255) NOT NULL DEFAULT '',
  `input_transformation_options` varchar(255) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Column information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__designer_settings`
--

CREATE TABLE `pma__designer_settings` (
  `username` varchar(64) NOT NULL,
  `settings_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Settings related to Designer';

--
-- Volcado de datos para la tabla `pma__designer_settings`
--

INSERT INTO `pma__designer_settings` (`username`, `settings_data`) VALUES
('root', '{\"snap_to_grid\":\"off\",\"relation_lines\":\"true\",\"angular_direct\":\"direct\"}');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__export_templates`
--

CREATE TABLE `pma__export_templates` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) NOT NULL,
  `export_type` varchar(10) NOT NULL,
  `template_name` varchar(64) NOT NULL,
  `template_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved export templates';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__favorite`
--

CREATE TABLE `pma__favorite` (
  `username` varchar(64) NOT NULL,
  `tables` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Favorite tables';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__history`
--

CREATE TABLE `pma__history` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `username` varchar(64) NOT NULL DEFAULT '',
  `db` varchar(64) NOT NULL DEFAULT '',
  `table` varchar(64) NOT NULL DEFAULT '',
  `timevalue` timestamp NOT NULL DEFAULT current_timestamp(),
  `sqlquery` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='SQL history for phpMyAdmin';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__navigationhiding`
--

CREATE TABLE `pma__navigationhiding` (
  `username` varchar(64) NOT NULL,
  `item_name` varchar(64) NOT NULL,
  `item_type` varchar(64) NOT NULL,
  `db_name` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Hidden items of navigation tree';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__pdf_pages`
--

CREATE TABLE `pma__pdf_pages` (
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `page_nr` int(10) UNSIGNED NOT NULL,
  `page_descr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PDF relation pages for phpMyAdmin';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__recent`
--

CREATE TABLE `pma__recent` (
  `username` varchar(64) NOT NULL,
  `tables` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Recently accessed tables';

--
-- Volcado de datos para la tabla `pma__recent`
--

INSERT INTO `pma__recent` (`username`, `tables`) VALUES
('root', '[{\"db\":\"voyageprive\",\"table\":\"hotel\"},{\"db\":\"voyageprive\",\"table\":\"reservar\"},{\"db\":\"voyageprive\",\"table\":\"usuario\"},{\"db\":\"bdsys\",\"table\":\"empleado\"},{\"db\":\"rakutentv\",\"table\":\"usuario\"},{\"db\":\"rakutentv\",\"table\":\"pelicula\"},{\"db\":\"rakutentv\",\"table\":\"actor\"}]');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__relation`
--

CREATE TABLE `pma__relation` (
  `master_db` varchar(64) NOT NULL DEFAULT '',
  `master_table` varchar(64) NOT NULL DEFAULT '',
  `master_field` varchar(64) NOT NULL DEFAULT '',
  `foreign_db` varchar(64) NOT NULL DEFAULT '',
  `foreign_table` varchar(64) NOT NULL DEFAULT '',
  `foreign_field` varchar(64) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Relation table';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__savedsearches`
--

CREATE TABLE `pma__savedsearches` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) NOT NULL DEFAULT '',
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `search_name` varchar(64) NOT NULL DEFAULT '',
  `search_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved searches';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__table_coords`
--

CREATE TABLE `pma__table_coords` (
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `table_name` varchar(64) NOT NULL DEFAULT '',
  `pdf_page_number` int(11) NOT NULL DEFAULT 0,
  `x` float UNSIGNED NOT NULL DEFAULT 0,
  `y` float UNSIGNED NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for phpMyAdmin PDF output';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__table_info`
--

CREATE TABLE `pma__table_info` (
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `table_name` varchar(64) NOT NULL DEFAULT '',
  `display_field` varchar(64) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__table_uiprefs`
--

CREATE TABLE `pma__table_uiprefs` (
  `username` varchar(64) NOT NULL,
  `db_name` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL,
  `prefs` text NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Tables'' UI preferences';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__tracking`
--

CREATE TABLE `pma__tracking` (
  `db_name` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL,
  `version` int(10) UNSIGNED NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime NOT NULL,
  `schema_snapshot` text NOT NULL,
  `schema_sql` text DEFAULT NULL,
  `data_sql` longtext DEFAULT NULL,
  `tracking` set('UPDATE','REPLACE','INSERT','DELETE','TRUNCATE','CREATE DATABASE','ALTER DATABASE','DROP DATABASE','CREATE TABLE','ALTER TABLE','RENAME TABLE','DROP TABLE','CREATE INDEX','DROP INDEX','CREATE VIEW','ALTER VIEW','DROP VIEW') DEFAULT NULL,
  `tracking_active` int(1) UNSIGNED NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Database changes tracking for phpMyAdmin';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__userconfig`
--

CREATE TABLE `pma__userconfig` (
  `username` varchar(64) NOT NULL,
  `timevalue` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `config_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User preferences storage for phpMyAdmin';

--
-- Volcado de datos para la tabla `pma__userconfig`
--

INSERT INTO `pma__userconfig` (`username`, `timevalue`, `config_data`) VALUES
('root', '2023-07-19 15:09:40', '{\"Console\\/Mode\":\"collapse\",\"lang\":\"es\"}');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__usergroups`
--

CREATE TABLE `pma__usergroups` (
  `usergroup` varchar(64) NOT NULL,
  `tab` varchar(64) NOT NULL,
  `allowed` enum('Y','N') NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User groups with configured menu items';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__users`
--

CREATE TABLE `pma__users` (
  `username` varchar(64) NOT NULL,
  `usergroup` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users and their assignments to user groups';

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pma__central_columns`
--
ALTER TABLE `pma__central_columns`
  ADD PRIMARY KEY (`db_name`,`col_name`);

--
-- Indices de la tabla `pma__column_info`
--
ALTER TABLE `pma__column_info`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `db_name` (`db_name`,`table_name`,`column_name`);

--
-- Indices de la tabla `pma__designer_settings`
--
ALTER TABLE `pma__designer_settings`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_user_type_template` (`username`,`export_type`,`template_name`);

--
-- Indices de la tabla `pma__favorite`
--
ALTER TABLE `pma__favorite`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `pma__history`
--
ALTER TABLE `pma__history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `username` (`username`,`db`,`table`,`timevalue`);

--
-- Indices de la tabla `pma__navigationhiding`
--
ALTER TABLE `pma__navigationhiding`
  ADD PRIMARY KEY (`username`,`item_name`,`item_type`,`db_name`,`table_name`);

--
-- Indices de la tabla `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  ADD PRIMARY KEY (`page_nr`),
  ADD KEY `db_name` (`db_name`);

--
-- Indices de la tabla `pma__recent`
--
ALTER TABLE `pma__recent`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `pma__relation`
--
ALTER TABLE `pma__relation`
  ADD PRIMARY KEY (`master_db`,`master_table`,`master_field`),
  ADD KEY `foreign_field` (`foreign_db`,`foreign_table`);

--
-- Indices de la tabla `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_savedsearches_username_dbname` (`username`,`db_name`,`search_name`);

--
-- Indices de la tabla `pma__table_coords`
--
ALTER TABLE `pma__table_coords`
  ADD PRIMARY KEY (`db_name`,`table_name`,`pdf_page_number`);

--
-- Indices de la tabla `pma__table_info`
--
ALTER TABLE `pma__table_info`
  ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Indices de la tabla `pma__table_uiprefs`
--
ALTER TABLE `pma__table_uiprefs`
  ADD PRIMARY KEY (`username`,`db_name`,`table_name`);

--
-- Indices de la tabla `pma__tracking`
--
ALTER TABLE `pma__tracking`
  ADD PRIMARY KEY (`db_name`,`table_name`,`version`);

--
-- Indices de la tabla `pma__userconfig`
--
ALTER TABLE `pma__userconfig`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `pma__usergroups`
--
ALTER TABLE `pma__usergroups`
  ADD PRIMARY KEY (`usergroup`,`tab`,`allowed`);

--
-- Indices de la tabla `pma__users`
--
ALTER TABLE `pma__users`
  ADD PRIMARY KEY (`username`,`usergroup`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pma__column_info`
--
ALTER TABLE `pma__column_info`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pma__history`
--
ALTER TABLE `pma__history`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  MODIFY `page_nr` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- Base de datos: `rakutentv`
--
CREATE DATABASE IF NOT EXISTS `rakutentv` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci;
USE `rakutentv`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actor`
--

CREATE TABLE `actor` (
  `ID_Actor` int(11) NOT NULL,
  `Nombre` varchar(20) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `Apellido` varchar(20) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `F_Nacimiento` varchar(10) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `Pais` varchar(20) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `Biografia` text CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `actor`
--

INSERT INTO `actor` (`ID_Actor`, `Nombre`, `Apellido`, `F_Nacimiento`, `Pais`, `Biografia`) VALUES
(1, 'Tom', 'Cruise', '03/07/1962', 'Estados Unidos', 'Thomas Cruise Mapother IV, m?s conocido como Tom Cruise, es un actor estadounidense. Ha protagonizado pel?culas como Risky Business, Top Gun, El color del dinero, La guerra de los mundos, Days of Thunder, Rain Man, Eyes Wide Shut, Minority Report, El ?ltimo samur?i, Jack Reacher, Al filo del ma?ana y la saga de Misi?n imposible entre otras. '),
(2, 'Leonardo', 'DiCaprio', '11/11/1974', 'Estados Unidos', 'Leonardo Wilhelm DiCaprio es un actor y productor de cine estadounidense. Ha recibido numerosos premios por su actuaci?n, entre los que se destacan un ?scar al mejor actor y un premio BAFTA como Mejor Actor por su actuaci?n en El renacido de 2016, dos Globos de Oro al mejor actor en drama por su actuaci?n en El aviador en el a?o 2005, en El renacido (2016) y un Globo de Oro al mejor actor en comedia o musical por El lobo de Wall Street, en 2014. Adicionalmente, ha ganado el premio Oso de Plata y un Chlotrudis.'),
(3, 'Matthew', 'McConaughey', '04/11/1969', 'Estados Unidos', 'Matthew David McConaughey es un actor y productor de cine y televisi?n estadounidense. Es ganador de un premio ?scar, un Globo de Oro y un SAG.'),
(4, 'Sophia', 'Lillis', '13/02/2002', 'Estados Unidos', 'Sophia Lillis es una actriz estadounidense, conocida por interpretar a Beverly Marsh en la pel?cula de terror de 2017 It.'),
(5, 'Bill ', 'Skarsgard', '09/08/1990', 'Suecia', 'Bill Istvan G?nther Skarsg?rd es un sueco. Es hijo del actor Stellan Skarsg?rd. Es conocido por interpretar a Pennywise, el payaso en la pel?cula de terror It.'),
(6, 'Sally', 'Hawkins', '27/04/1976', 'Reino Unido', 'Sally Cecilia Hawkins es una actriz brit?nica. En 2002 hizo su debut cinematogr?fico en All or Nothing de Mike Leigh.'),
(7, 'Al ', 'Pacino', '25/04/2940', 'Estados Unidos', 'Alfredo James Pacino, conocido como Al Pacino es un actor, guionista y director estadounidense de cine y teatro. Es uno de los actores de teatro y cine de fines del siglo XX que m?s reconocimientos recibi?.? Su carrera ha abarcado cincuenta a?os durante los cuales ha obtenido numerosos premios y honores, incluyendo los premios ?scar, Emmy, Globo de Oro, SAG, BAFTA, Tony, un reconocimiento a su carrera por parte del American Film Institute, el Premio Cecil B. DeMille y la Medalla Nacional de las Artes. Es adem?s uno de los pocos actores que ha obtenido los premios ?scar, Emmy y Tony como actor, conjunto conocido como la ?triple corona de la actuaci?n?. '),
(8, 'Marlon', 'Brando', '03/04/1924', 'Estados Unidos', 'Marlon Brando, Jr. fue un actor estadounidense de cine y teatro. Su formaci?n e instrucci?n teatral fue llevada a cabo por Stella Adler, una de las m?s prestigiosas profesoras que desarroll? el trabajo de Stanislavski en Nueva York.'),
(9, 'Peter', 'Dinklage', '11/06/1969', 'Estados Unidos', 'Peter Hayden Dinklage? es un actor de cine, televisi?n y teatro estadounidense, su papel m?s conocido es el de Tyrion Lannister en la serie Juego de tronos, el cual le ha valido tres Emmy y un Globo de Oro.'),
(10, 'Frances', 'McDormand', '23/06/1957', 'Estados Unidos', 'Frances Louise McDormand es una actriz estadounidense. McDormand se encuentra entre el grupo de actores que ha conseguido la Triple Corona de la Actuaci?n: ?scar a la mejor actriz por Fargo y por Three Billboards Outside Ebbing, Missouri; Premio Tony a la mejor actriz principal en una obra de teatro por la producci?n original de Broadway Good People (2011) y Primetime Emmy a la mejor actriz en una Miniserie o telefilme por Olive Kitteridge (2014).'),
(11, 'Felicity', 'Jones', '17/10/1983', 'Reino Unido', 'Felicity Rose Hadley Jones? es una actriz brit?nica nominada a los Premios Globo de Oro, los Premios de Cine de la Academia Brit?nica BAFTA, los Premios del Sindicato de Actores y los Premios ?scar.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actuar`
--

CREATE TABLE `actuar` (
  `ID_Actuacion` int(11) NOT NULL,
  `ID_Actor` int(11) NOT NULL,
  `ID_Pelicula` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `actuar`
--

INSERT INTO `actuar` (`ID_Actuacion`, `ID_Actor`, `ID_Pelicula`) VALUES
(2, 1, 2),
(1, 2, 1),
(3, 3, 3),
(9, 4, 7),
(10, 5, 7),
(6, 6, 5),
(4, 7, 4),
(5, 8, 4),
(7, 9, 6),
(8, 10, 6),
(11, 11, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clasificar`
--

CREATE TABLE `clasificar` (
  `ID_Clasificacion` int(11) NOT NULL,
  `ID_Pelicula` int(11) NOT NULL,
  `ID_Genero` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `clasificar`
--

INSERT INTO `clasificar` (`ID_Clasificacion`, `ID_Pelicula`, `ID_Genero`) VALUES
(5, 1, 4),
(6, 1, 10),
(8, 2, 1),
(7, 2, 2),
(9, 3, 7),
(10, 3, 9),
(11, 4, 4),
(12, 4, 9),
(14, 5, 4),
(13, 5, 10),
(16, 6, 4),
(15, 6, 9),
(18, 7, 2),
(17, 7, 5),
(19, 8, 2),
(20, 8, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `desear`
--

CREATE TABLE `desear` (
  `ID_Deseado` int(11) NOT NULL,
  `ID_Pelicula` int(11) NOT NULL,
  `ID_Usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `desear`
--

INSERT INTO `desear` (`ID_Deseado`, `ID_Pelicula`, `ID_Usuario`) VALUES
(14, 1, 1),
(23, 1, 2),
(34, 1, 3),
(29, 1, 4),
(10, 1, 5),
(11, 1, 6),
(4, 2, 2),
(1, 3, 1),
(40, 3, 2),
(5, 3, 3),
(26, 3, 4),
(44, 3, 6),
(2, 4, 1),
(6, 4, 3),
(30, 4, 4),
(17, 5, 1),
(20, 5, 2),
(35, 5, 3),
(8, 5, 4),
(42, 6, 1),
(21, 6, 2),
(41, 6, 3),
(7, 6, 4),
(15, 7, 1),
(25, 7, 2),
(28, 7, 4),
(46, 7, 6),
(48, 8, 1),
(3, 8, 2),
(38, 8, 3),
(32, 8, 4),
(9, 8, 5),
(13, 8, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

CREATE TABLE `genero` (
  `ID_Genero` int(11) NOT NULL,
  `Genero` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `genero`
--

INSERT INTO `genero` (`ID_Genero`, `Genero`) VALUES
(1, 'Accion'),
(2, 'Aventuras'),
(8, 'Belica'),
(3, 'Comedia'),
(4, 'Drama'),
(7, 'Ficcion'),
(6, 'Musical'),
(10, 'Romantica'),
(9, 'Suspense'),
(5, 'Terror');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pelicula`
--

CREATE TABLE `pelicula` (
  `ID_Pelicula` int(11) NOT NULL,
  `Titulo` varchar(50) NOT NULL,
  `Precio` double(4,2) NOT NULL,
  `Duracion` int(3) NOT NULL,
  `Trailer` varchar(100) DEFAULT NULL,
  `Sinopsis` text CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `N_Votos` int(9) NOT NULL,
  `S_Puntuacion` int(11) NOT NULL,
  `Fecha_Estreno` varchar(10) NOT NULL,
  `URL` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `pelicula`
--

INSERT INTO `pelicula` (`ID_Pelicula`, `Titulo`, `Precio`, `Duracion`, `Trailer`, `Sinopsis`, `N_Votos`, `S_Puntuacion`, `Fecha_Estreno`, `URL`) VALUES
(1, 'Titanic', 5.99, 295, 'https://www.youtube.com/watch?v=jnFxtSuZRpU', 'Una joven (Kate Winslet) de sociedad abandona a su arrogante pretendiente (Billy Zane) por un artista (Leonardo DiCaprio) humilde en el transatlántico que se hundió durante su viaje inaugural.', 200, 900, '1997', 'titanic'),
(2, 'Mision imposible: Fallout', 0.99, 148, 'https://www.youtube.com/embed/vDX_r9MH5Z0', 'Un traficante de armas y un grupo de terroristas pretenden realizar un triple ataque nuclear con tres artefactos de plutonio, los cuales se pierden. Ethan Hunt y su equipo tendrán que recuperarlos antes de que caigan en las manos equivocadas.', 100, 300, '2018', 'mision_imposible'),
(3, 'Interstellar', 9.99, 169, 'https://www.youtube.com/watch?v=NqniWGlg5kU', 'Un grupo de exploradores hacen uso de un orificio recién descubierto para superar las limitaciones de los viajes espaciales humanos y conquistar las vastas distancias relacionadas con los viajes interestelares.', 500, 2200, '2014', 'interstellar'),
(4, 'El Padrino', 7.99, 178, 'https://www.youtube.com/watch?v=gCVj1LeYnsc', 'Una adaptación ganadora del Premio de la Academia, de la novela de Mario Puzo acerca de la familia Corleone.', 500, 2400, '1972', 'padrino'),
(5, 'La forma del agua', 3.99, 183, 'https://www.youtube.com/watch?v=FMNTFFhR__g', 'Elisa es una joven muda que se enamora de un hombre anfibio que está recluido en un acuario en un laboratorio secreto, propiedad del Gobierno, en el que ella trabaja limpiando. Llevada por el amor, Elisa trama un plan para liberar al mutante.', 100, 400, '2017', 'agua'),
(6, 'Tres anuncios en las afueras', 4.99, 175, 'https://www.youtube.com/watch?v=uLr4jog9EX8', 'Una mujer cuya hija fue asesinada se enfrenta a la policía usando los carteles publicitarios para denunciar que han pasado meses desde que se cometió el crimen y no solo no han resuelto nada, sino que parecen no tener interés en hacerlo.', 50, 165, '2017', 'anuncios'),
(7, 'It', 7.99, 195, 'https://www.youtube.com/watch?v=xKJmEC5ieOk', 'Varios niños de una pequeña ciudad del estado de Maine se alían para combatir a una entidad diabólica que adopta la forma de un payaso y desde hace mucho tiempo emerge cada 27 años para saciarse de sangre infantil.', 250, 1100, '2017', 'it'),
(8, 'Un monstruo viene a verme', 4.99, 108, 'https://www.youtube.com/watch?v=1-fubC9JN50', 'Un niño de 12 anos evoca un monstruo en su imaginación y este ayuda al niño a lidiar con su miserable vida y le enseña a tener coraje.', 100, 360, '2016', 'monstruo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `ID_Usuario` int(11) NOT NULL,
  `Nombre` varchar(20) NOT NULL,
  `Apellido` varchar(20) NOT NULL,
  `Email` varchar(40) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Fecha_registro` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`ID_Usuario`, `Nombre`, `Apellido`, `Email`, `Password`, `Fecha_registro`) VALUES
(1, 'Diego', 'Abad', 'diego@gmail.com', '12345', '2018-11-21'),
(2, 'Samuel', 'Til', 'samuel@gmail.com', '1234', '2018-11-21'),
(3, 'Pablo', 'Cuesta', 'pablo@gmail.com', '123', '2018-11-21'),
(4, 'Nacho', 'Arbues', 'nacho@gmail.com', '12345', '2018-11-21'),
(5, 'Daniel', 'Nieto', 'daniel@gmail.com', '1234', '2018-11-21'),
(6, 'Alberto', 'Borraz', 'alberto@gmail.com', '12345', '2018-11-21'),
(9, 'PIPO', 'POPO', 'prueba@gmail.com', '12345', '2018-11-29');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actor`
--
ALTER TABLE `actor`
  ADD PRIMARY KEY (`ID_Actor`),
  ADD KEY `ID_Actor` (`ID_Actor`);

--
-- Indices de la tabla `actuar`
--
ALTER TABLE `actuar`
  ADD PRIMARY KEY (`ID_Actuacion`),
  ADD UNIQUE KEY `ID_Actor` (`ID_Actor`,`ID_Pelicula`) USING BTREE,
  ADD KEY `FK_PELICULA` (`ID_Pelicula`);

--
-- Indices de la tabla `clasificar`
--
ALTER TABLE `clasificar`
  ADD PRIMARY KEY (`ID_Clasificacion`),
  ADD UNIQUE KEY `ID_Pelicula` (`ID_Pelicula`,`ID_Genero`) USING BTREE,
  ADD KEY `FK_GENERO` (`ID_Genero`);

--
-- Indices de la tabla `desear`
--
ALTER TABLE `desear`
  ADD PRIMARY KEY (`ID_Deseado`),
  ADD UNIQUE KEY `ID_Pelicula` (`ID_Pelicula`,`ID_Usuario`) USING BTREE,
  ADD KEY `FK_USUARIO` (`ID_Usuario`);

--
-- Indices de la tabla `genero`
--
ALTER TABLE `genero`
  ADD PRIMARY KEY (`ID_Genero`),
  ADD UNIQUE KEY `Genero` (`Genero`),
  ADD KEY `ID_Genero` (`ID_Genero`);

--
-- Indices de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  ADD PRIMARY KEY (`ID_Pelicula`),
  ADD KEY `ID_Pelicula` (`ID_Pelicula`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`ID_Usuario`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `actor`
--
ALTER TABLE `actor`
  MODIFY `ID_Actor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `actuar`
--
ALTER TABLE `actuar`
  MODIFY `ID_Actuacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `clasificar`
--
ALTER TABLE `clasificar`
  MODIFY `ID_Clasificacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `desear`
--
ALTER TABLE `desear`
  MODIFY `ID_Deseado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT de la tabla `genero`
--
ALTER TABLE `genero`
  MODIFY `ID_Genero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  MODIFY `ID_Pelicula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `ID_Usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `actuar`
--
ALTER TABLE `actuar`
  ADD CONSTRAINT `FK_ACTOR` FOREIGN KEY (`ID_Actor`) REFERENCES `actor` (`ID_Actor`),
  ADD CONSTRAINT `FK_PELICULA` FOREIGN KEY (`ID_Pelicula`) REFERENCES `pelicula` (`ID_Pelicula`);

--
-- Filtros para la tabla `clasificar`
--
ALTER TABLE `clasificar`
  ADD CONSTRAINT `FK_GENERO` FOREIGN KEY (`ID_Genero`) REFERENCES `genero` (`ID_Genero`),
  ADD CONSTRAINT `FK_PELICULA_CLA` FOREIGN KEY (`ID_Pelicula`) REFERENCES `pelicula` (`ID_Pelicula`);

--
-- Filtros para la tabla `desear`
--
ALTER TABLE `desear`
  ADD CONSTRAINT `FK_PELICULA_DESEAR` FOREIGN KEY (`ID_Pelicula`) REFERENCES `pelicula` (`ID_Pelicula`),
  ADD CONSTRAINT `FK_USUARIO` FOREIGN KEY (`ID_Usuario`) REFERENCES `usuario` (`ID_Usuario`);
--
-- Base de datos: `test`
--
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `test`;
--
-- Base de datos: `voyageprive`
--
CREATE DATABASE IF NOT EXISTS `voyageprive` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci;
USE `voyageprive`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hotel`
--

CREATE TABLE `hotel` (
  `ID_Hotel` int(11) NOT NULL,
  `Nombre_hotel` varchar(50) NOT NULL,
  `PrecioEstancia` float(6,2) NOT NULL,
  `Duracion` int(3) NOT NULL,
  `Ubicacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `Descripcion` text CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `Fecha_Entrada` varchar(10) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `URL_Imagen` varchar(200) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `hotel`
--

INSERT INTO `hotel` (`ID_Hotel`, `Nombre_hotel`, `PrecioEstancia`, `Duracion`, `Ubicacion`, `Descripcion`, `Fecha_Entrada`, `URL_Imagen`) VALUES
(24, 'nuevo hotel', 50.99, 130, 'Santiago de Compostela', 'av marques del botafumeiro', '2023-08-15', 'img3.jpg'),
(35, 'hotel12', 2000.00, 10, 'hotel12 wertyuio asdfghj cvbnm', 'Barcelona', '16-12-2023', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservar`
--

CREATE TABLE `reservar` (
  `ID_Reservado` int(11) NOT NULL,
  `ID_Hotel` int(11) NOT NULL,
  `ID_Usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `email`, `password`) VALUES
(1, 'juanjo@gmail.com', '1234'),
(2, 'jroche@gmail.com', '1234'),
(17, 'prueba@gmail.com', '123456'),
(18, 'prueba2@gmail.com', '4321'),
(19, 'juanjodoll@gmail.com', '1234'),
(20, 'jjdr@gmail.com', '1234'),
(21, 'prueba3@gmail.com', '159'),
(22, 'jjd4r@gmail.com', '1234'),
(23, 'jjdddr@gmail.com', '1234'),
(24, 'jjddd3r@gmail.com', '123433');

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
-- Indices de la tabla `reservar`
--
ALTER TABLE `reservar`
  ADD PRIMARY KEY (`ID_Reservado`),
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
  MODIFY `ID_Hotel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT de la tabla `reservar`
--
ALTER TABLE `reservar`
  MODIFY `ID_Reservado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `reservar`
--
ALTER TABLE `reservar`
  ADD CONSTRAINT `FK_HOTEL_RESERVAR` FOREIGN KEY (`ID_Hotel`) REFERENCES `hotel` (`ID_Hotel`),
  ADD CONSTRAINT `FK_USUARIO` FOREIGN KEY (`ID_Usuario`) REFERENCES `usuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
