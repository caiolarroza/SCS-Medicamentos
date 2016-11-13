/*cria o banco de dados farmacia*/
create database farmacia;
/*utiliza o banco de dados criado*/
use farmacia;

/*cria a tabela de endereco*/
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

/*cria a tabela de clientes*/
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
	constraint fkEndCli foreign key(codEndereco) references endereco(codEndereco)
);

/*cria a tabela de fornecedores*/
create table fornecedor(
	codFornecedor int(5) primary key,
	nome varchar(40),
	telefone char(10),
	celular char(11),
	cnpj varchar(14),
	codEndereco int(5),
	constraint fkEndFor foreign key(codEndereco) references endereco(codEndereco)
);

/*cria a tabela de medicamento*/
create table medicamento(
	codMedicamento int(5) primary key,
	nome varchar(30),
	codFornecedor int(5),
	dataValidade Date,
	preco decimal(7,2),
	qtdEstoque int(5),
	lote int(5),
	constraint fkForMed foreign key(codFornecedor) references fornecedor(codFornecedor)
);

/*cria a tabela de usuarios do sistema*/
create table usuario(
	codUsuario int(5) primary key,
	login varchar(25),
	senha varchar(25),
	tipoUsuario varchar(15)
);

/*cria a tabela de moedas*/
create table moedas(
	codMoedas int(5) primary key,
	qtd5 int(5),
	qtd10 int(5),
	qtd25 int(5),
	qtd50 int(5),
	qtd1Real int(5)
);

/*cria a tabela de notas*/
create table notas(
	codNotas int(5) primary key,
	qtd2 int(5),
	qtd5 int(5),
	qtd10 int(5),
	qtd20 int(5),
	qtd50 int(5),
	qtd100 int(5)
);

/*cria a tabela de caixa*/
create table caixa(
	codCaixa int(5) primary key,
	dataAbertura date,
	dataFechamento date,
	horaAbertura time,
	horaFechamento time,
	codNotas int(5),
	codMoedas int(5),
	usuarioAbriu int(5),
	usuarioFechou int(5),
	status boolean,
	constraint fkNotasC foreign key(codNotas) references notas(codNotas),
	constraint fkMoedasC foreign key(codMoedas) references moedas(codMoedas),
	constraint fkUsuarioAbriuC foreign key(usuarioAbriu) references usuario(codUsuario),
	constraint fkUsuarioFechouC foreign key(usuarioFechou) references usuario(codUsuario)
);

/*cria a tabela com o tipo do pagamento*/
create table tipoPagamento(
	codPagamento int(5) primary key,
	valorTotal decimal(7,2),
	codCaixa int(5),
	constraint fkCaixaTP foreign key(codCaixa) references caixa(codCaixa)
);

/*cria a tabela q armazena os dados do cartao de credito*/
create table cartaoCredito(
	codPagamento int(5) primary key,
	numero bigint(16),
	nome varchar(40),
	validade date,
	codSeguranca int(3),
	constraint fkTipoPagCC foreign key(codPagamento) references tipoPagamento(codPagamento)
);

/*cria a tabela de dinheiro*/
create table dinheiro(
	codPagamento int(5) primary key,
	codNotas int(5),
	codMoedas int(5),
	constraint fkNotasD foreign key(codNotas) references notas(codNotas),
	constraint fkMoedasD foreign key(codMoedas) references moedas(codMoedas),
	constraint fkTipoPagD foreign key(codPagamento) references tipoPagamento(codPagamento)
);

/*cria uma tabela de venda*/
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

/*cria uma tabela que armazena os dados da venda do medicamento*/
create table vendaMedicamento(
	codVendaMed int(5) primary key,
	codVenda int(5),
	codMedicamento int(5),
	quantidade int(5),
	constraint fkVendaVM foreign key(codVenda) references venda(codVenda),
	constraint fkMedicamentoVM foreign key(codMedicamento) references medicamento(codMedicamento)
);

/*view que mosta as compras realizadas*/
create view vCompra as
	select c.nome as Cliente, v.codVenda as CodigoVenda, 
	tP.valorTotal as ValorTotal,
	v.porcentagemDesconto as PorcentagemDesconto, 
	round(ValorTotal*(1-PorcentagemDesconto/100), 2) as ValorFinal,
	m.nome as Medicamento, vM.quantidade as Quantidade,
	m.lote as Lote, m.preco as PrecoUnitario from Venda v
	inner join tipoPagamento tP on v.codPagamento = tP.codPagamento
	inner join vendaMedicamento vM on v.codVenda = vM.codVenda
	inner join medicamento m on vM.codMedicamento = m.codMedicamento
	inner join cliente c on v.codCliente = c.codCliente;

/*mostra os clientes cadastrados no sistema*/
create view vCliente as
	select c.nome as Cliente, c.telefone as Telefone,
	c.celular as Celular, c.rg as RG, c.cpf as CPF,
	date_format(c.dataNascimento, '%d/%m/%Y') as DataNascimento,
	c.aposentado as Aposentado, e.logradouro as Logradouro,
	e.numero as Numero, e.complemento as Complemento,
	e.bairro as Bairro, e.cep as CEP, e.cidade as Cidade,
	e.estado as Estado from cliente c
	inner join endereco e on c.codEndereco = e.codEndereco; 

/*mostra todos os fornecedores cadastrados no sistema*/
create view vFornecedor as
	select f.nome as Fornecedor, f.telefone as Telefone,
	f.celular as Celular, f.cnpj as CNPJ, e.logradouro as Logradouro,
	e.numero as Numero, e.complemento as Complemento,
	e.bairro as Bairro, e.cep as CEP, e.cidade as Cidade,
	e.estado as Estado from fornecedor f
	inner join endereco e on f.codEndereco = e.codEndereco; 



/*insere valores na tabela de moedas*/
insert into moedas(codMoedas, qtd5, qtd10, qtd25, qtd50, qtd1Real)
		   values ("1", "1", "2","3","4","5");

insert into moedas(codMoedas, qtd5, qtd10, qtd25, qtd50, qtd1Real)
		   values ("2", "5", "4","3","2","1");

/*insere valores na tabela de notas*/
insert into notas(codNotas, qtd2, qtd5, qtd10, qtd20, qtd50, qtd100)
		  values("1", "1", "2","3","4","5","6");

insert into notas(codNotas, qtd2, qtd5, qtd10, qtd20, qtd50, qtd100)
		  values ("2", "6", "5", "4","3","2","1");

/*insere valores na tabela usuario*/
insert into usuario(codUsuario, login, senha, tipoUsuario) values ("1", "usuario1","123", "Funcionario");
insert into usuario(codUsuario, login, senha, tipoUsuario) values ("2", "usuario2","321", "Funcionario");

/*insere valores na tabela endereco*/
insert into endereco(codEndereco, logradouro, numero, complemento, bairro, cep, cidade, estado) 
		    values ("1", "Rua Joao Procopio Filho","51","","Pq Erasmo Assuncao","09271244","Santo Andre","SP");

insert into endereco(codEndereco, logradouro, numero, complemento, bairro, cep, cidade, estado) 
		    values ("2", "Rua Bell Aliance","225","","Jd Sao Caetano","09581420","Sao Caetano do Sul","SP");
			
insert into endereco(codEndereco, logradouro, numero, complemento, bairro, cep, cidade, estado) 
		    values ("3", "Rodovia Castelo Branco","3565","","Bairro do Itaqui","06696000","Itapevi","SP");
	
insert into endereco(codEndereco, logradouro, numero, complemento, bairro, cep, cidade, estado) 
			values ("4", "Rua Macedo Costa","55","","Jardim Santa Genebra","13080180","Campinas","SP");

/*insere valores na tabela cliente*/
insert into cliente(codCliente, nome, telefone, celular, rg, cpf, dataNascimento, aposentado, codEndereco)
						values ("1", "Caio Larroza", "1144445555", "11944445555", "665844571", "55511124855", "1996-03-15", "0", "1");

insert into cliente(codCliente, nome, telefone, celular, rg, cpf, dataNascimento, aposentado, codEndereco)
					values ("2", "Matheus dos Santos", "1155554444", "11955554444", "775514847", "22214548441", "1997-11-06", "1", "2");

/*insere valores na tabela fornecedor*/
insert into fornecedor(codFornecedor, nome, telefone, celular, cnpj, codEndereco) 
				 values ("1", "Eurofarma Laboratorios S.A","1150908600","","61190096000869","3");
				 
insert into fornecedor(codFornecedor, nome, telefone, celular, cnpj, codEndereco) 
		     values ("2", "Medlye Industria Farmaceutica Ltda.","1921178222","","50929710000179","4");
					
/*insere valores na tabela medicamento*/
insert into medicamento(codMedicamento,nome,codFornecedor,dataValidade,preco,qtdEstoque,lote)
						values ("1", "Betatrinta 1x1ml", "1", "20180315", "15.59", "100", "1");
						
insert into medicamento(codMedicamento,nome,codFornecedor,dataValidade,preco,qtdEstoque,lote)
						values ("2", "Fluxocor 20MG 30 comp", "2", "20170723", "25.10", "110", "1");						
					
/*insere valores na tabela de caixa*/
insert into caixa(codCaixa, dataAbertura, dataFechamento, horaAbertura, horaFechamento, codNotas, codMoedas, usuarioAbriu, usuarioFechou, status)
		  values ("1", "20161109", "20161110","09:10","10:10","1","1","1","1","0");
		  
insert into caixa(codCaixa, dataAbertura, dataFechamento, horaAbertura, horaFechamento, codNotas, codMoedas, usuarioAbriu, usuarioFechou, status)
		  values ("2", "20161009", "20161009","07:15","13:27","2","2","2","2","0");		 

/*insere valores na tabela de tipopagamento*/
insert into tipopagamento(codPagamento, valorTotal, codCaixa) values ("1","1000", "1");
insert into tipopagamento(codPagamento, valorTotal, codCaixa) values ("2","5000.56", "2");
		  
/*insere valores na tabela de dinheiro*/
insert into dinheiro(CodPagamento, codNotas, codMoedas) values ("1","1","1");

/*insere valores na tabela de cartaoCredito*/
insert into cartaocredito(codPagamento, numero, nome, validade, codSeguranca)
						  values("2", "1122334455667788", "Caio Larroza de Oliveira", "20170529", "225");					  

/*insere valores na tabela de venda*/
insert into venda(codVenda, porcentagemDesconto, codCliente, codPagamento, codCaixa)
				  values ("1", "5", "1", "1", "1");
insert into venda(codVenda, porcentagemDesconto, codCliente, codPagamento, codCaixa)
				  values ("2", "20", "2", "2", "2");				  
				  
/*insere valores na tabela de vendamedicamento*/
insert into vendamedicamento(codVendaMed, codVenda, codMedicamento, quantidade) values ("1", "1", "1", "20");
insert into vendamedicamento(codVendaMed, codVenda, codMedicamento, quantidade) values ("2", "2", "2", "50");				  

/*good to go*/