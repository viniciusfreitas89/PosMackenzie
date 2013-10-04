-- phpMyAdmin SQL Dump
-- version 4.0.5
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: 04/10/2013 às 11:32
-- Versão do servidor: 5.1.70-cll
-- Versão do PHP: 5.3.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de dados: `mpcb_mackenzie`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `categorias`
--

CREATE TABLE IF NOT EXISTS `categorias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `icone` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Fazendo dump de dados para tabela `categorias`
--

INSERT INTO `categorias` (`id`, `nome`, `icone`) VALUES
(1, 'Restaurantes', ''),
(2, 'Bares', ''),
(3, 'Baladas', '');

-- --------------------------------------------------------

--
-- Estrutura para tabela `checkin`
--

CREATE TABLE IF NOT EXISTS `checkin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `id_local` int(11) NOT NULL,
  `valor_gasto` float(10,2) NOT NULL,
  `data_registro` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Fazendo dump de dados para tabela `checkin`
--

INSERT INTO `checkin` (`id`, `id_usuario`, `id_local`, `valor_gasto`, `data_registro`) VALUES
(1, 1, 14, 35.00, '2013-09-30 15:59:28'),
(2, 2, 9, 50.00, '2013-09-30 16:35:34'),
(3, 3, 15, 10.00, '2013-09-30 17:11:29'),
(4, 1, 16, 11.00, '2013-09-30 17:53:20'),
(5, 1, 17, 150.00, '2013-09-30 18:36:07'),
(6, 6, 18, 13.00, '2013-09-30 19:03:00'),
(7, 6, 9, 55.00, '2013-09-30 19:06:11'),
(8, 2, 15, 99.99, '2013-09-30 19:06:39'),
(9, 7, 18, 15.00, '2013-09-30 19:12:38'),
(10, 7, 14, 15.00, '2013-09-30 19:13:20'),
(11, 7, 7, 15.00, '2013-09-30 19:13:55'),
(12, 1, 19, 14.00, '2013-09-30 22:53:45'),
(13, 2, 3, 35.00, '2013-10-01 08:25:35'),
(14, 2, 12, 22.00, '2013-10-01 08:28:44'),
(15, 1, 9, 50.00, '2013-10-01 08:43:36');

-- --------------------------------------------------------

--
-- Estrutura para tabela `locais`
--

CREATE TABLE IF NOT EXISTS `locais` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `num_checkins` int(11) NOT NULL DEFAULT '0',
  `data_registro` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Fazendo dump de dados para tabela `locais`
--

INSERT INTO `locais` (`id`, `nome`, `latitude`, `longitude`, `id_categoria`, `num_checkins`, `data_registro`) VALUES
(1, 'Jasmim Rosa', 89998, 65434, 1, 11, '2013-09-15 00:00:00'),
(2, 'MC Donalds - Ipiranga', -23.55014, -46.639924, 1, 1, '2013-09-15 18:27:03'),
(3, 'Black Dog - Saúde', -23.55084, -46.639924, 1, 1, '2013-09-15 21:14:39'),
(4, 'Belga Lancheteria', -23.55084, -46.639924, 1, 1, '2013-09-15 21:16:20'),
(5, '189 Burguers', -23.55014, -46.639924, 1, 3, '2013-09-15 22:16:03'),
(6, 'Bar Rock', -23.55014, -46.639924, 1, 1, '2013-09-16 17:40:22'),
(7, 'Toloucos', -23.55014, -46.639924, 1, 4, '2013-09-16 18:01:56'),
(8, 'Tatuapé Bar', -23.55014, -46.639924, 1, 1, '2013-09-22 22:48:16'),
(9, 'Outback Moema', -23.55014, -46.639924, 2, 5, '2013-09-22 22:49:44'),
(10, 'Tia Zilma Dog', -23.55014, -46.639924, 2, 2, '2013-09-22 22:57:24'),
(11, 'Esperanza', -23.55014, -46.639924, 2, 1, '2013-09-22 22:58:40'),
(12, 'Mackenzie', -23.55014, -46.639924, 2, 4, '2013-09-23 19:12:02'),
(13, 'Bella Paulista', -23.55014, -46.639924, 3, 2, '2013-09-29 20:07:22'),
(14, 'Pizza Pan', -23.55014, -46.639924, 1, 4, '2013-09-29 21:52:25'),
(15, 'MackFil', -23.55014, -46.639924, 2, 2, '2013-09-30 17:11:28'),
(16, 'Miracatu Lanches', -23.55014, -46.639924, 2, 1, '2013-09-30 17:53:19'),
(17, 'Vila Carioca', -23.55014, -46.639924, 3, 1, '2013-09-30 18:36:06'),
(18, 'mackenzie lanches', -23.55014, -46.639924, 2, 2, '2013-09-30 19:03:00'),
(19, 'Bar do Ceara', -23.55014, -46.639924, 2, 1, '2013-09-30 22:53:45');

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `data_registro` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Fazendo dump de dados para tabela `usuario`
--

INSERT INTO `usuario` (`id`, `nome`, `email`, `senha`, `data_registro`) VALUES
(1, 'Marcelo Pacheco', 'marcelo@teste.com.br', 'f5bb0c8de146c67b44babbf4e6584cc0', '2013-09-30 15:48:53'),
(2, 'Antônio', 'antonio@teste.com.br', 'f5bb0c8de146c67b44babbf4e6584cc0', '2013-09-30 00:00:00'),
(3, 'Vinicius', 'vinicius@teste.com.br', 'f5bb0c8de146c67b44babbf4e6584cc0', '2013-09-30 00:00:00'),
(4, 'Derik', 'derik@teste.com.br', 'f5bb0c8de146c67b44babbf4e6584cc0', '2013-09-30 00:00:00'),
(5, 'Juliana Ferreira', 'juliana@teste.com.br', 'F5BB0C8DE146C67B44BABBF4E6584CC0', '2013-09-30 17:15:42'),
(6, 'Vagner Santana', 'vagner@teste.com.br', 'F5BB0C8DE146C67B44BABBF4E6584CC0', '2013-09-30 19:00:08'),
(7, 'admim', 'admin@myplaces.com', 'D8578EDF8458CE06FBC5BB76A58C5CA4', '2013-09-30 19:10:28');

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario_amigos`
--

CREATE TABLE IF NOT EXISTS `usuario_amigos` (
  `id_usuario` int(11) NOT NULL,
  `id_amigo` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `usuario_amigos`
--

INSERT INTO `usuario_amigos` (`id_usuario`, `id_amigo`) VALUES
(3, 1),
(3, 2),
(2, 1),
(6, 2);

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario_solicitacao`
--

CREATE TABLE IF NOT EXISTS `usuario_solicitacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `id_amigo` int(11) NOT NULL,
  `status` enum('aceito','n_aceito') DEFAULT NULL,
  `data_solicitacao` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Fazendo dump de dados para tabela `usuario_solicitacao`
--

INSERT INTO `usuario_solicitacao` (`id`, `id_usuario`, `id_amigo`, `status`, `data_solicitacao`) VALUES
(1, 3, 1, 'aceito', '2013-09-30 17:11:54'),
(2, 3, 2, 'aceito', '2013-09-30 17:49:55'),
(3, 2, 1, 'aceito', '2013-09-30 18:05:43'),
(4, 6, 2, 'aceito', '2013-09-30 19:03:42'),
(5, 2, 4, NULL, '2013-10-01 08:27:11');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
         