-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 04-Dez-2016 às 18:24
-- Versão do servidor: 5.7.16
-- PHP Version: 5.6.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `farmacia`
--

DELIMITER $$
--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `tipo` (`cod` INT(5)) RETURNS VARCHAR(20) CHARSET latin1 begin
    declare procura integer;
    declare mensagem varchar(20);
    set procura = (select count(codPagamento) from dinheiro
      where codPagamento = cod);
    if procura = 0 then
      begin
        set mensagem = "Cartão de Credito";
      end;
    else
      set mensagem = "Dinheiro";
    end if;
    return mensagem;
  end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `caixa`
--

CREATE TABLE `caixa` (
  `codCaixa` int(5) NOT NULL,
  `dataAbertura` date DEFAULT NULL,
  `dataFechamento` date DEFAULT NULL,
  `horaAbertura` time DEFAULT NULL,
  `horaFechamento` time DEFAULT NULL,
  `codNotas` int(5) DEFAULT NULL,
  `codMoedas` int(5) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `caixa`
--

INSERT INTO `caixa` (`codCaixa`, `dataAbertura`, `dataFechamento`, `horaAbertura`, `horaFechamento`, `codNotas`, `codMoedas`, `status`) VALUES
(1, '2016-12-04', '2016-12-04', '16:17:03', '16:17:32', 1, 1, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cartaocredito`
--

CREATE TABLE `cartaocredito` (
  `codPagamento` int(5) NOT NULL,
  `numero` bigint(16) DEFAULT NULL,
  `nome` varchar(40) DEFAULT NULL,
  `validade` char(7) DEFAULT NULL,
  `codSeguranca` int(3) DEFAULT NULL,
  `parcelas` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cartaocredito`
--

INSERT INTO `cartaocredito` (`codPagamento`, `numero`, `nome`, `validade`, `codSeguranca`, `parcelas`) VALUES
(2, 1122334455667788, 'Matheus dos Santos Lopes', '05/2017', 225, 2),
(3, 123123123123, 'Matheus dos Santos', '12/2017', 123, 2),
(4, 123123123345, 'Matheus dos Santos', '12/2019', 123, 2),
(6, 1234567812341234, 'Matheus dos Santos', '12/2018', 112, 1),
(8, 33333333333333333, 'caio', '12/1998', 323, 7);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `codCliente` int(5) NOT NULL,
  `nome` varchar(40) DEFAULT NULL,
  `telefone` char(10) DEFAULT NULL,
  `celular` char(11) DEFAULT NULL,
  `rg` char(15) DEFAULT NULL,
  `cpf` char(15) DEFAULT NULL,
  `dataNascimento` date DEFAULT NULL,
  `aposentado` tinyint(1) DEFAULT NULL,
  `codEndereco` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`codCliente`, `nome`, `telefone`, `celular`, `rg`, `cpf`, `dataNascimento`, `aposentado`, `codEndereco`) VALUES
(1, 'Caio Larroza de Oliveira', '1144445555', '11944445555', '665844571', '44064969553', '1996-03-15', 0, 1),
(2, 'Matheus dos Santos', '1155554444', '11955554444', '775514847', '43800754860', '1997-11-06', 1, 2),
(3, 'Rita de Cassia', '1156893274', '11958693214', '386504985', '96985874712', '1966-12-12', 1, 6),
(4, 'Caio Larroza', '1144445555', '11944445555', '665844571', '1', '1996-03-15', 0, 7),
(5, 'Caio Larroza', '1144445555', '11944445555', '665844571', '3', '1996-03-15', 0, 8),
(6, 'Caio Larroza', '1144445555', '11944445555', '665844571', '123', '1996-03-15', 0, 9),
(7, 'asdsad', '1125356565', '11957893264', '579875632', '44589765236', '2000-12-12', 0, 10);

-- --------------------------------------------------------

--
-- Estrutura da tabela `dinheiro`
--

CREATE TABLE `dinheiro` (
  `codPagamento` int(5) NOT NULL,
  `codNotas` int(5) DEFAULT NULL,
  `codMoedas` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `dinheiro`
--

INSERT INTO `dinheiro` (`codPagamento`, `codNotas`, `codMoedas`) VALUES
(1, 2, 2),
(5, 4, 4),
(7, 5, 5),
(9, 6, 6),
(10, 7, 7);

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco`
--

CREATE TABLE `endereco` (
  `codEndereco` int(5) NOT NULL,
  `logradouro` varchar(50) DEFAULT NULL,
  `numero` varchar(5) DEFAULT NULL,
  `complemento` varchar(50) DEFAULT NULL,
  `bairro` varchar(20) DEFAULT NULL,
  `cep` char(8) DEFAULT NULL,
  `cidade` varchar(30) DEFAULT NULL,
  `estado` char(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `endereco`
--

INSERT INTO `endereco` (`codEndereco`, `logradouro`, `numero`, `complemento`, `bairro`, `cep`, `cidade`, `estado`) VALUES
(1, 'Rua Joao Procopio Filho', '51', '', 'Pq Erasmo Assuncao', '09271244', 'Santo Andre', 'SP'),
(2, 'Rua Bell Aliance', '225', 'Bloco C 18', 'Jd Sao Caetano', '09581420', 'Sao Caetano do Sul', 'SP'),
(3, 'Rodovia Castelo Branco', '3565', '', 'Bairro do Itaqui', '06696000', 'Itapevi', 'SP'),
(4, 'Rua Macedo Costa', '55', '', 'Jardim Santa Genebra', '13080180', 'Campinas', 'SP'),
(5, 'Rua Dias de Almeida', '55', '', 'Jardim Miriam', '04419000', 'São Paulo', 'SP'),
(6, 'Rua Dias de Almeida', '205', '', 'Jardins', '09958000', 'São Paulo', 'SP'),
(7, 'Rua Joao Procopio Filho', '51', '', 'Pq Erasmo Assuncao', '09271244', 'Diadema', 'SP'),
(8, 'Rua Joao Procopio Filho', '51', '', 'Pq Erasmo Assuncao', '09271244', 'Diadema', 'SP'),
(9, 'Rua Joao Procopio Filho', '51', '', 'Pq Erasmo Assuncao', '09271244', 'Diadema', 'SP'),
(10, 'Rua Bell Aliance', '111', '', 'Jardim São Caetano', '0941445', 'Sào Caetano', 'SP');

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedor`
--

CREATE TABLE `fornecedor` (
  `codFornecedor` int(5) NOT NULL,
  `nome` varchar(40) DEFAULT NULL,
  `telefone` char(10) DEFAULT NULL,
  `celular` char(11) DEFAULT NULL,
  `cnpj` varchar(14) DEFAULT NULL,
  `codEndereco` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `fornecedor`
--

INSERT INTO `fornecedor` (`codFornecedor`, `nome`, `telefone`, `celular`, `cnpj`, `codEndereco`) VALUES
(1, 'Eurofarma Laboratorios S.A', '1150908600', '', '61190096000869', 3),
(2, 'Medlye Industria Farmaceutica Ltda.', '1921178222', '', '50929710000179', 4),
(3, 'ACME LTDA.', '1921179222', '', '2', 5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `medicamento`
--

CREATE TABLE `medicamento` (
  `codMedicamento` int(5) NOT NULL,
  `nome` varchar(30) DEFAULT NULL,
  `codFornecedor` int(5) DEFAULT NULL,
  `dataValidade` date DEFAULT NULL,
  `preco` decimal(7,2) DEFAULT NULL,
  `qtdEstoque` int(5) DEFAULT NULL,
  `lote` int(5) DEFAULT NULL,
  `dataEntrada` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `medicamento`
--

INSERT INTO `medicamento` (`codMedicamento`, `nome`, `codFornecedor`, `dataValidade`, `preco`, `qtdEstoque`, `lote`, `dataEntrada`) VALUES
(1, 'Betatrinta 1x1ml', 1, '2018-03-15', '15.59', 800, 1, '2016-10-22'),
(2, 'Fluxocor 20MG 30 comp', 2, '2017-07-23', '25.10', 822, 1, '2016-10-30'),
(3, 'Dipirona', 3, '2019-12-12', '5.99', 50, 5, '2016-12-01');

-- --------------------------------------------------------

--
-- Estrutura da tabela `moedas`
--

CREATE TABLE `moedas` (
  `codMoedas` int(5) NOT NULL,
  `qtd5` int(5) DEFAULT NULL,
  `qtd10` int(5) DEFAULT NULL,
  `qtd25` int(5) DEFAULT NULL,
  `qtd50` int(5) DEFAULT NULL,
  `qtd1Real` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `moedas`
--

INSERT INTO `moedas` (`codMoedas`, `qtd5`, `qtd10`, `qtd25`, `qtd50`, `qtd1Real`) VALUES
(1, 10, 10, 10, 10, 10),
(2, 1, 2, 3, 4, 5),
(3, 0, 0, 0, 1, 1),
(4, 1, 1, 1, 1, 1),
(5, 1, 1, 1, 1, 0),
(6, 1, 0, 1, 1, 0),
(7, 0, 0, 1, 0, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `notas`
--

CREATE TABLE `notas` (
  `codNotas` int(5) NOT NULL,
  `qtd2` int(5) DEFAULT NULL,
  `qtd5` int(5) DEFAULT NULL,
  `qtd10` int(5) DEFAULT NULL,
  `qtd20` int(5) DEFAULT NULL,
  `qtd50` int(5) DEFAULT NULL,
  `qtd100` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `notas`
--

INSERT INTO `notas` (`codNotas`, `qtd2`, `qtd5`, `qtd10`, `qtd20`, `qtd50`, `qtd100`) VALUES
(1, 10, 10, 10, 10, 10, 10),
(2, 1, 2, 3, 4, 5, 6),
(3, 1, 0, 0, 0, 1, 0),
(4, 0, 1, 0, 1, 1, 4),
(5, 2, 0, 0, 1, 0, 0),
(6, 1, 0, 1, 0, 1, 5),
(7, 1, 0, 0, 2, 1, 11);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipopagamento`
--

CREATE TABLE `tipopagamento` (
  `codPagamento` int(5) NOT NULL,
  `valorTotal` decimal(7,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tipopagamento`
--

INSERT INTO `tipopagamento` (`codPagamento`, `valorTotal`) VALUES
(1, '979.00'),
(2, '1410.90'),
(3, '301.20'),
(4, '311.80'),
(5, '502.00'),
(6, '106.48'),
(7, '31.18'),
(8, '31.18'),
(9, '592.42'),
(10, '1255.00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `codUsuario` int(5) NOT NULL,
  `login` varchar(25) DEFAULT NULL,
  `senha` varchar(25) DEFAULT NULL,
  `tipoUsuario` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`codUsuario`, `login`, `senha`, `tipoUsuario`) VALUES
(1, 'usuario1', '123', 'Atendente'),
(2, 'usuario2', '321', 'Gerente');

-- --------------------------------------------------------

--
-- Stand-in structure for view `vcliente`
-- (See below for the actual view)
--
CREATE TABLE `vcliente` (
`Cliente` varchar(40)
,`Telefone` char(10)
,`Celular` char(11)
,`RG` char(15)
,`CPF` char(15)
,`DataNascimento` varchar(10)
,`Aposentado` tinyint(1)
,`Logradouro` varchar(50)
,`Numero` varchar(5)
,`Complemento` varchar(50)
,`Bairro` varchar(20)
,`CEP` char(8)
,`Cidade` varchar(30)
,`Estado` char(2)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vcompra`
-- (See below for the actual view)
--
CREATE TABLE `vcompra` (
`Cliente` varchar(40)
,`CodigoVenda` int(5)
,`ValorTotal` decimal(7,2)
,`PorcentagemDesconto` int(2)
,`ValorFinal` decimal(19,2)
,`Medicamento` varchar(30)
,`Quantidade` int(5)
,`Lote` int(5)
,`PrecoUnitario` decimal(7,2)
,`PrecoItem` decimal(17,2)
);

-- --------------------------------------------------------

--
-- Estrutura da tabela `venda`
--

CREATE TABLE `venda` (
  `codVenda` int(5) NOT NULL,
  `porcentagemDesconto` int(2) DEFAULT NULL,
  `codCliente` int(5) DEFAULT NULL,
  `codPagamento` int(5) DEFAULT NULL,
  `codCaixa` int(5) DEFAULT NULL,
  `data` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `venda`
--

INSERT INTO `venda` (`codVenda`, `porcentagemDesconto`, `codCliente`, `codPagamento`, `codCaixa`, `data`) VALUES
(1, 5, 1, 1, 1, '2016-11-24'),
(2, 20, 2, 2, 1, '2016-11-29'),
(3, 20, 2, 3, 1, '2016-11-30'),
(4, 20, 2, 4, 1, '2016-12-01'),
(5, 5, 1, 5, 1, '2016-12-01'),
(6, 0, 1, 6, 1, '2016-12-01'),
(7, 20, 2, 7, 1, '2016-12-01'),
(8, 0, 4, 8, 1, '2016-12-01'),
(9, 5, 4, 9, 1, '2016-12-01'),
(10, 5, 1, 10, 1, '2016-12-01');

-- --------------------------------------------------------

--
-- Estrutura da tabela `vendamedicamento`
--

CREATE TABLE `vendamedicamento` (
  `codVendaMed` int(5) NOT NULL,
  `codVenda` int(5) DEFAULT NULL,
  `codMedicamento` int(5) DEFAULT NULL,
  `quantidade` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `vendamedicamento`
--

INSERT INTO `vendamedicamento` (`codVendaMed`, `codVenda`, `codMedicamento`, `quantidade`) VALUES
(1, 1, 1, 20),
(2, 1, 2, 29),
(3, 2, 2, 50),
(4, 2, 1, 10),
(5, 3, 2, 12),
(6, 4, 1, 20),
(7, 5, 2, 20),
(8, 6, 1, 2),
(9, 6, 2, 3),
(10, 7, 1, 2),
(11, 8, 1, 2),
(12, 9, 1, 38),
(13, 10, 2, 50);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vfornecedor`
-- (See below for the actual view)
--
CREATE TABLE `vfornecedor` (
`Fornecedor` varchar(40)
,`Telefone` char(10)
,`Celular` char(11)
,`CNPJ` varchar(14)
,`Logradouro` varchar(50)
,`Numero` varchar(5)
,`Complemento` varchar(50)
,`Bairro` varchar(20)
,`CEP` char(8)
,`Cidade` varchar(30)
,`Estado` char(2)
);

-- --------------------------------------------------------

--
-- Structure for view `vcliente`
--
DROP TABLE IF EXISTS `vcliente`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vcliente`  AS  select `c`.`nome` AS `Cliente`,`c`.`telefone` AS `Telefone`,`c`.`celular` AS `Celular`,`c`.`rg` AS `RG`,`c`.`cpf` AS `CPF`,date_format(`c`.`dataNascimento`,'%d/%m/%Y') AS `DataNascimento`,`c`.`aposentado` AS `Aposentado`,`e`.`logradouro` AS `Logradouro`,`e`.`numero` AS `Numero`,`e`.`complemento` AS `Complemento`,`e`.`bairro` AS `Bairro`,`e`.`cep` AS `CEP`,`e`.`cidade` AS `Cidade`,`e`.`estado` AS `Estado` from (`cliente` `c` join `endereco` `e` on((`c`.`codEndereco` = `e`.`codEndereco`))) ;

-- --------------------------------------------------------

--
-- Structure for view `vcompra`
--
DROP TABLE IF EXISTS `vcompra`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vcompra`  AS  select `c`.`nome` AS `Cliente`,`v`.`codVenda` AS `CodigoVenda`,`tp`.`valorTotal` AS `ValorTotal`,`v`.`porcentagemDesconto` AS `PorcentagemDesconto`,round((`tp`.`valorTotal` * (1 - (`v`.`porcentagemDesconto` / 100))),2) AS `ValorFinal`,`m`.`nome` AS `Medicamento`,`vm`.`quantidade` AS `Quantidade`,`m`.`lote` AS `Lote`,`m`.`preco` AS `PrecoUnitario`,(`m`.`preco` * `vm`.`quantidade`) AS `PrecoItem` from ((((`venda` `v` join `tipopagamento` `tp` on((`v`.`codPagamento` = `tp`.`codPagamento`))) join `vendamedicamento` `vm` on((`v`.`codVenda` = `vm`.`codVenda`))) join `medicamento` `m` on((`vm`.`codMedicamento` = `m`.`codMedicamento`))) join `cliente` `c` on((`v`.`codCliente` = `c`.`codCliente`))) ;

-- --------------------------------------------------------

--
-- Structure for view `vfornecedor`
--
DROP TABLE IF EXISTS `vfornecedor`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vfornecedor`  AS  select `f`.`nome` AS `Fornecedor`,`f`.`telefone` AS `Telefone`,`f`.`celular` AS `Celular`,`f`.`cnpj` AS `CNPJ`,`e`.`logradouro` AS `Logradouro`,`e`.`numero` AS `Numero`,`e`.`complemento` AS `Complemento`,`e`.`bairro` AS `Bairro`,`e`.`cep` AS `CEP`,`e`.`cidade` AS `Cidade`,`e`.`estado` AS `Estado` from (`fornecedor` `f` join `endereco` `e` on((`f`.`codEndereco` = `e`.`codEndereco`))) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `caixa`
--
ALTER TABLE `caixa`
  ADD PRIMARY KEY (`codCaixa`),
  ADD KEY `fkNotasC` (`codNotas`),
  ADD KEY `fkMoedasC` (`codMoedas`);

--
-- Indexes for table `cartaocredito`
--
ALTER TABLE `cartaocredito`
  ADD PRIMARY KEY (`codPagamento`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`codCliente`),
  ADD KEY `fkEndCli` (`codEndereco`);

--
-- Indexes for table `dinheiro`
--
ALTER TABLE `dinheiro`
  ADD PRIMARY KEY (`codPagamento`),
  ADD KEY `fkNotasD` (`codNotas`),
  ADD KEY `fkMoedasD` (`codMoedas`);

--
-- Indexes for table `endereco`
--
ALTER TABLE `endereco`
  ADD PRIMARY KEY (`codEndereco`);

--
-- Indexes for table `fornecedor`
--
ALTER TABLE `fornecedor`
  ADD PRIMARY KEY (`codFornecedor`),
  ADD KEY `fkEndFor` (`codEndereco`);

--
-- Indexes for table `medicamento`
--
ALTER TABLE `medicamento`
  ADD PRIMARY KEY (`codMedicamento`),
  ADD KEY `fkForMed` (`codFornecedor`);

--
-- Indexes for table `moedas`
--
ALTER TABLE `moedas`
  ADD PRIMARY KEY (`codMoedas`);

--
-- Indexes for table `notas`
--
ALTER TABLE `notas`
  ADD PRIMARY KEY (`codNotas`);

--
-- Indexes for table `tipopagamento`
--
ALTER TABLE `tipopagamento`
  ADD PRIMARY KEY (`codPagamento`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`codUsuario`);

--
-- Indexes for table `venda`
--
ALTER TABLE `venda`
  ADD PRIMARY KEY (`codVenda`),
  ADD KEY `fkClienteV` (`codCliente`),
  ADD KEY `fkPagamentoV` (`codPagamento`),
  ADD KEY `fkCaixaV` (`codCaixa`);

--
-- Indexes for table `vendamedicamento`
--
ALTER TABLE `vendamedicamento`
  ADD PRIMARY KEY (`codVendaMed`),
  ADD KEY `fkVendaVM` (`codVenda`),
  ADD KEY `fkMedicamentoVM` (`codMedicamento`);

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `caixa`
--
ALTER TABLE `caixa`
  ADD CONSTRAINT `fkMoedasC` FOREIGN KEY (`codMoedas`) REFERENCES `moedas` (`codMoedas`),
  ADD CONSTRAINT `fkNotasC` FOREIGN KEY (`codNotas`) REFERENCES `notas` (`codNotas`);

--
-- Limitadores para a tabela `cartaocredito`
--
ALTER TABLE `cartaocredito`
  ADD CONSTRAINT `fkTipoPagCC` FOREIGN KEY (`codPagamento`) REFERENCES `tipopagamento` (`codPagamento`);

--
-- Limitadores para a tabela `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `fkEndCli` FOREIGN KEY (`codEndereco`) REFERENCES `endereco` (`codEndereco`);

--
-- Limitadores para a tabela `dinheiro`
--
ALTER TABLE `dinheiro`
  ADD CONSTRAINT `fkMoedasD` FOREIGN KEY (`codMoedas`) REFERENCES `moedas` (`codMoedas`),
  ADD CONSTRAINT `fkNotasD` FOREIGN KEY (`codNotas`) REFERENCES `notas` (`codNotas`),
  ADD CONSTRAINT `fkTipoPagD` FOREIGN KEY (`codPagamento`) REFERENCES `tipopagamento` (`codPagamento`);

--
-- Limitadores para a tabela `fornecedor`
--
ALTER TABLE `fornecedor`
  ADD CONSTRAINT `fkEndFor` FOREIGN KEY (`codEndereco`) REFERENCES `endereco` (`codEndereco`);

--
-- Limitadores para a tabela `medicamento`
--
ALTER TABLE `medicamento`
  ADD CONSTRAINT `fkForMed` FOREIGN KEY (`codFornecedor`) REFERENCES `fornecedor` (`codFornecedor`);

--
-- Limitadores para a tabela `venda`
--
ALTER TABLE `venda`
  ADD CONSTRAINT `fkCaixaV` FOREIGN KEY (`codCaixa`) REFERENCES `caixa` (`codCaixa`),
  ADD CONSTRAINT `fkClienteV` FOREIGN KEY (`codCliente`) REFERENCES `cliente` (`codCliente`),
  ADD CONSTRAINT `fkPagamentoV` FOREIGN KEY (`codPagamento`) REFERENCES `tipopagamento` (`codPagamento`);

--
-- Limitadores para a tabela `vendamedicamento`
--
ALTER TABLE `vendamedicamento`
  ADD CONSTRAINT `fkMedicamentoVM` FOREIGN KEY (`codMedicamento`) REFERENCES `medicamento` (`codMedicamento`),
  ADD CONSTRAINT `fkVendaVM` FOREIGN KEY (`codVenda`) REFERENCES `venda` (`codVenda`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
