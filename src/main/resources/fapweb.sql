-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 18-Jan-2020 às 14:53
-- Versão do servidor: 10.1.40-MariaDB
-- versão do PHP: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fapweb`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `consultas`
--

CREATE TABLE `consultas` (
  `id_consulta` bigint(20) NOT NULL,
  `altura_uterina` double NOT NULL,
  `apres_fetal` varchar(255) DEFAULT NULL,
  `bcf` varchar(255) DEFAULT NULL,
  `created_at` date NOT NULL,
  `data_consulta` datetime NOT NULL,
  `edema` varchar(255) DEFAULT NULL,
  `idade_gestacional` varchar(255) DEFAULT NULL,
  `obs` varchar(255) DEFAULT NULL,
  `peso` double NOT NULL,
  `pres_arterial` varchar(255) DEFAULT NULL,
  `toque` varchar(255) DEFAULT NULL,
  `updated_at` date NOT NULL,
  `paciente_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `consultas`
--

INSERT INTO `consultas` (`id_consulta`, `altura_uterina`, `apres_fetal`, `bcf`, `created_at`, `data_consulta`, `edema`, `idade_gestacional`, `obs`, `peso`, `pres_arterial`, `toque`, `updated_at`, `paciente_id`) VALUES
(4, 26.6, 'Cefálica', '150', '2020-01-18', '2020-01-10 13:24:44', 'Não', '12', 'Passado medicamento para aliviar as dores da lombar', 67.9, '120x80', 'nao', '2020-01-18', 654897654132154);

-- --------------------------------------------------------

--
-- Estrutura da tabela `enderecos`
--

CREATE TABLE `enderecos` (
  `id_endereco` bigint(20) NOT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(255) NOT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `numero` int(11) NOT NULL,
  `rua` varchar(255) DEFAULT NULL,
  `uf` varchar(255) DEFAULT NULL,
  `paciente_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `enderecos`
--

INSERT INTO `enderecos` (`id_endereco`, `bairro`, `cep`, `cidade`, `numero`, `rua`, `uf`, `paciente_id`) VALUES
(6, 'Vila Militar', '11.241-302', 'Barueri', 2021, 'Estr. de Jandira', 'SP', 654897654132154);

-- --------------------------------------------------------

--
-- Estrutura da tabela `exames`
--

CREATE TABLE `exames` (
  `id_exames` bigint(20) NOT NULL,
  `abo_rh` varchar(255) DEFAULT NULL,
  `cit_oncotica` varchar(255) DEFAULT NULL,
  `citomegalovirus` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `dt_abo_rh` datetime DEFAULT NULL,
  `dt_cit_oncotica` datetime DEFAULT NULL,
  `dt_citomega` datetime DEFAULT NULL,
  `dt_glicemia` datetime DEFAULT NULL,
  `dt_hemoglobina` datetime DEFAULT NULL,
  `dt_hepatite` datetime DEFAULT NULL,
  `dt_sifilis` datetime DEFAULT NULL,
  `dt_toxopla` datetime DEFAULT NULL,
  `dt_urina_eas` datetime DEFAULT NULL,
  `dt_vdrl_hiv` datetime DEFAULT NULL,
  `glicemia` int(11) NOT NULL,
  `hemoglobina` varchar(255) DEFAULT NULL,
  `hepatite` varchar(255) DEFAULT NULL,
  `sifilis` varchar(255) DEFAULT NULL,
  `toxoplasmose` varchar(255) DEFAULT NULL,
  `updated_at` datetime NOT NULL,
  `urina_eas` int(11) NOT NULL,
  `vdrl_hiv` varchar(255) DEFAULT NULL,
  `paciente_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `exames`
--

INSERT INTO `exames` (`id_exames`, `abo_rh`, `cit_oncotica`, `citomegalovirus`, `created_at`, `dt_abo_rh`, `dt_cit_oncotica`, `dt_citomega`, `dt_glicemia`, `dt_hemoglobina`, `dt_hepatite`, `dt_sifilis`, `dt_toxopla`, `dt_urina_eas`, `dt_vdrl_hiv`, `glicemia`, `hemoglobina`, `hepatite`, `sifilis`, `toxoplasmose`, `updated_at`, `urina_eas`, `vdrl_hiv`, `paciente_id`) VALUES
(3, 'AB+', '', 'Não Reagente', '2020-01-18 13:49:04', '2020-02-06 13:24:44', NULL, '2020-01-03 13:24:44', '2020-01-03 13:24:44', '2020-01-03 13:24:44', '2020-01-03 13:24:44', '2020-01-03 13:24:44', '2020-01-03 13:24:44', '2020-01-03 13:24:44', '2020-01-03 13:24:44', 656, '12.6', 'B:positivo; C:negativo', 'Não Reagente', 'Não Reagente', '2020-01-18 13:49:04', 1001, 'Não Reagente', 654897654132154);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pacientes`
--

CREATE TABLE `pacientes` (
  `id_paciente` bigint(20) NOT NULL,
  `altura` double DEFAULT NULL,
  `cpf` varchar(255) NOT NULL,
  `created_at` date NOT NULL,
  `data_nasc` datetime NOT NULL,
  `dpp` datetime NOT NULL,
  `dum` datetime NOT NULL,
  `estado_civil` varchar(255) DEFAULT NULL,
  `idade` int(11) NOT NULL,
  `identidade` bigint(20) NOT NULL,
  `imc` double DEFAULT NULL,
  `nome_mae` varchar(255) DEFAULT NULL,
  `nome_paciente` varchar(255) DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `telefone` varchar(255) NOT NULL,
  `updated_at` date NOT NULL,
  `usuario_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pacientes`
--

INSERT INTO `pacientes` (`id_paciente`, `altura`, `cpf`, `created_at`, `data_nasc`, `dpp`, `dum`, `estado_civil`, `idade`, `identidade`, `imc`, `nome_mae`, `nome_paciente`, `peso`, `telefone`, `updated_at`, `usuario_id`) VALUES
(654897654132154, 170, '014.522.478-99', '2020-01-18', '1997-02-06 02:00:00', '2020-07-10 13:24:44', '2020-10-20 13:24:44', 'divorciada', 23, 987498498, 22.76, 'Lynne Spears', 'Britney Spears', 65.8, '84 87564-6513', '2020-01-18', 9);

-- --------------------------------------------------------

--
-- Estrutura da tabela `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `name` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(2, 'ROLE_ADMIN'),
(1, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Estrutura da tabela `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `users`
--

INSERT INTO `users` (`id`, `created_at`, `updated_at`, `email`, `name`, `password`, `username`) VALUES
(9, '2020-01-18 13:30:34', '2020-01-18 13:30:34', 'admin@email.com', 'admin', '$2a$10$X/.EhXGKjDVMsY/cusqxC.h/Cwy3pGBa2CHJ4v3Gw6l9ZTqpciJEu', 'admin');

-- --------------------------------------------------------

--
-- Estrutura da tabela `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(9, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `consultas`
--
ALTER TABLE `consultas`
  ADD PRIMARY KEY (`id_consulta`),
  ADD KEY `FKe93cdauso5jd1y4bdpox45n4t` (`paciente_id`);

--
-- Indexes for table `enderecos`
--
ALTER TABLE `enderecos`
  ADD PRIMARY KEY (`id_endereco`),
  ADD KEY `FK1scy3177hxeml4ticphhq9qj7` (`paciente_id`);

--
-- Indexes for table `exames`
--
ALTER TABLE `exames`
  ADD PRIMARY KEY (`id_exames`),
  ADD KEY `FK41l34d1r99qne459h1m2tf7wl` (`paciente_id`);

--
-- Indexes for table `pacientes`
--
ALTER TABLE `pacientes`
  ADD PRIMARY KEY (`id_paciente`),
  ADD KEY `FKfffcq49fcmqpbllaou4de7t02` (`usuario_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_nb4h0p6txrmfc0xbrd1kglp9t` (`name`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `consultas`
--
ALTER TABLE `consultas`
  MODIFY `id_consulta` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `enderecos`
--
ALTER TABLE `enderecos`
  MODIFY `id_endereco` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `exames`
--
ALTER TABLE `exames`
  MODIFY `id_exames` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `consultas`
--
ALTER TABLE `consultas`
  ADD CONSTRAINT `FKe93cdauso5jd1y4bdpox45n4t` FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`id_paciente`);

--
-- Limitadores para a tabela `enderecos`
--
ALTER TABLE `enderecos`
  ADD CONSTRAINT `FK1scy3177hxeml4ticphhq9qj7` FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`id_paciente`);

--
-- Limitadores para a tabela `exames`
--
ALTER TABLE `exames`
  ADD CONSTRAINT `FK41l34d1r99qne459h1m2tf7wl` FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`id_paciente`);

--
-- Limitadores para a tabela `pacientes`
--
ALTER TABLE `pacientes`
  ADD CONSTRAINT `FKfffcq49fcmqpbllaou4de7t02` FOREIGN KEY (`usuario_id`) REFERENCES `users` (`id`);

--
-- Limitadores para a tabela `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
