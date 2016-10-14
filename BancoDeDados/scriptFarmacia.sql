create database farmacia;
use farmacia;

create table Endereco(
	codEndereco int(5) primary key,
	logradouro varchar(50),
	numero varchar(5),
	complemento varchar(50),
	bairro varchar(20),
	cep char(8),
	cidade varchar(30),
	estado char(2)
);

create table Cliente(
	codCliente int(5) primary key,
	nome varchar(40),
	telefone char(10),
	celular char(11),
	rg char(15),
	cpf char(15),
	dataNascimento Date,
	codEndereco int(5)
);

alter table Cliente add constraint fkEnd foreign key(codEndereco) references Endereco(codEndereco);

create table Medicamento(
	codMedicamento int(5) primary key,
	nome varchar(30),
	fabricante varchar(30),
	dataValidade Date,
	preco decimal(6,2),
	qtdEstoque int(5)
);

create table Usuario(
	codUsuario int(5) primary key,
	senha varchar(25),
	tipoUsuario varchar(15)
);

/*good to go*/