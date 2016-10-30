create database farmacia;
use farmacia;

create table endereco(
	codEndereco int(5) primary key,
	logradouro varchar(50),
	numero varchar(5),
	complemento varchar(50),
	bairro varchar(20),
	cep char(8),
	cidade varchar(30),
	estado char(2)
);

create table cliente(
	codCliente int(5) primary key,
	nome varchar(40),
	telefone char(10),
	celular char(11),
	rg char(15),
	cpf char(15),
	dataNascimento Date,
	aposentado boolean,
	codEndereco int(5),
	constraint fkEndCL foreign key(codEndereco) references Endereco(codEndereco)
);

create table medicamento(
	codMedicamento int(5) primary key,
	nome varchar(30),
	fabricante varchar(30),
	dataValidade Date,
	preco decimal(7,2),
	qtdEstoque int(5)
);

create table usuario(
	codUsuario int(5) primary key,
	login varchar(25),
	senha varchar(25),
	tipoUsuario varchar(15)
);

create table moedas(
	codMoedas int(5) primary key,
	qtd5 int(5),
	qtd10 int(5),
	qtd25 int(5),
	qtd50 int(5),
	qtd1Real int(5)
);

create table notas(
	codNotas int(5) primary key,
	qtd2 int(5),
	qtd5 int(5),
	qtd10 int(5),
	qtd20 int(5),
	qtd50 int(5),
	qtd100 int(5)
);

create table caixa(
	codCaixa int(5) primary key,
	senhaAbertura varchar(25),
	senhaFechamento varchar(25),
	dataAbertura date,
	dataFechamento date,
	horaAbertura time,
	horaFechamento time,
	codNotas int(5),
	codMoedas int(5),
	usuarioAbriu int(5),
	usuarioFechou int(5),
	constraint fkNotasC foreign key(codNotas) references notas(codNotas),
	constraint fkMoedasC foreign key(codMoedas) references moedas(codMoedas),
	constraint fkUsuarioAbriuC foreign key(usuarioAbriu) references usuario(codUsuario),
	constraint fkUsuarioFechouC foreign key(usuarioFechou) references usuario(codUsuario)
);

create table tipoPagamento(
	codPagamento int(5) primary key,
	valor decimal(7,2),
	codCaixa int(5),
	constraint fkCaixaTP foreign key(codCaixa) references caixa(codCaixa)
);

create table cartaoCredito(
	codPagamento int(5) primary key,
	numero bigint(16),
	nome varchar(40),
	validade date,
	codSeguranca int(3),
	constraint fkTipoPagCC foreign key(codPagamento) references tipoPagamento(codPagamento)
);

create table dinheiro(
	codPagamento int(5) primary key,
	codNotas int(5),
	codMoedas int(5),
	constraint fkNotasD foreign key(codNotas) references notas(codNotas),
	constraint fkMoedasD foreign key(codMoedas) references moedas(codMoedas),
	constraint fkTipoPagD foreign key(codPagamento) references tipoPagamento(codPagamento)
);

create table venda(
	codVenda int(5) primary key,
	porcentagemDesconto int(2),
	codCliente int(5),
	codPagamento int(5),
	codCaixa int(5),
	constraint fkClienteV foreign key(codCliente) references cliente(codCliente),
	constraint fkPagamentoV foreign key(codPagamento) references tipoPagamento(codPagamento),
	constraint fkCaixaV foreign key(codCaixa) references caixa(codCaixa)
);

create table vendaMedicamento(
	codVendaMed int(5) primary key,
	codVenda int(5),
	codMedicamento int(5),
	quantidade int(5),
	constraint fkVendaVM foreign key(codVenda) references venda(codVenda),
	constraint fkMedicamentoVM foreign key(codMedicamento) references medicamento(codMedicamento)
);
/*good to go*/