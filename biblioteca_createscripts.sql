-- -----------------------------------------------------
-- Table perfil
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS perfil (
    cod_perfil SERIAL NOT NULL,
    perfil VARCHAR(100) NOT NULL,
    prazo INT NOT NULL,
    limite INT NOT NULL,
    qtde_renovacoes INT NOT NULL,
    situacao VARCHAR(10) NOT NULL,
    CONSTRAINT pk_perfil PRIMARY KEY (cod_perfil)
);

-- -----------------------------------------------------
-- Table editora
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS editora (
    cod_editora SERIAL NOT NULL,
    nome VARCHAR(100) NOT NULL,
    situacao VARCHAR(10) NOT NULL,
    CONSTRAINT pk_editora PRIMARY KEY (cod_editora)
);

-- -----------------------------------------------------
-- Table funcionario
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS funcionario (
    cod_func SERIAL NOT NULL,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    usuario VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    salt VARCHAR(100) NOT NULL,
    situacao VARCHAR(10) NOT NULL,
    CONSTRAINT pk_funcionario PRIMARY KEY (cod_func)
);

-- -----------------------------------------------------
-- Table autor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS autor (
    cod_autor SERIAL NOT NULL,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    situacao VARCHAR(10) NOT NULL,
    CONSTRAINT pk_autor PRIMARY KEY (cod_autor)
);

-- -----------------------------------------------------
-- Table estado
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estado (
    cod_estado SERIAL NOT NULL,
    estado VARCHAR(100) NOT NULL,
    CONSTRAINT pk_estado PRIMARY KEY (cod_estado)
);

-- -----------------------------------------------------
-- Table tipolivro
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tipolivro (
    cod_tipolivro SERIAL NOT NULL,
    tipo VARCHAR(100) NOT NULL,
    CONSTRAINT pk_tipolivro PRIMARY KEY (cod_tipolivro)
);

-- -----------------------------------------------------
-- Table genero
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS genero (
    cod_genero SERIAL NOT NULL,
    genero VARCHAR(100) NOT NULL,
    CONSTRAINT pk_genero PRIMARY KEY (cod_genero)
);

-- -----------------------------------------------------
-- Table usuario
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS usuario (
    cod_usuario SERIAL NOT NULL,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    endereco VARCHAR(255) NULL,
    cpf VARCHAR(14) NOT NULL,
    email VARCHAR(100) NULL,
    data_nasc DATE NOT NULL,
    data_cadastro DATE NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    situacao VARCHAR(10) NOT NULL,
    cod_perfil INT NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (cod_usuario),
    CONSTRAINT fk_cod_perfil_usuario FOREIGN KEY (cod_perfil) REFERENCES perfil
);

-- -----------------------------------------------------
-- Table emprestimo
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS emprestimo (
    cod_emprestimo SERIAL NOT NULL,
    data_retirada DATE NOT NULL,
    data_devolucao DATE NOT NULL,
    data_devolvido DATE,
    renovacoes INT NOT NULL,
    devolvido BOOLEAN NOT NULL,
    cod_usuario INT NOT NULL,
    cod_func INT NOT NULL,
    CONSTRAINT pk_emprestimo PRIMARY KEY (cod_emprestimo),
    CONSTRAINT fk_cod_usuario_emprestimo FOREIGN KEY (cod_usuario) REFERENCES usuario,
    CONSTRAINT fk_cod_func_emprestimo FOREIGN KEY (cod_func) REFERENCES funcionario
);

-- -----------------------------------------------------
-- Table multa
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS multa (
    cod_multa SERIAL NOT NULL,
    valor DECIMAL(10,2) NULL,
    pago BOOLEAN NOT NULL,
    data_pgto DATE,
    cod_emprestimo INT NOT NULL,
    CONSTRAINT pk_multa PRIMARY KEY (cod_multa),
    CONSTRAINT fk_cod_emprestimo_multa FOREIGN KEY (cod_emprestimo) REFERENCES emprestimo
);

-- -----------------------------------------------------
-- Table livro
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS livro (
    cod_livro SERIAL NOT NULL,
    ISBN VARCHAR(45) NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    ano VARCHAR(4) NOT NULL,
    edicao VARCHAR(4) NOT NULL,
    volume VARCHAR(4) NOT NULL,
    exemplares INT NOT NULL,
    cod_tipolivro INT NOT NULL,
    cod_editora INT NOT NULL,
    CONSTRAINT pk_livro PRIMARY KEY (cod_livro),
    CONSTRAINT fk_cod_tipolivro_livro FOREIGN KEY (cod_tipolivro) REFERENCES tipolivro,
    CONSTRAINT fk_cod_editora_livro FOREIGN KEY (cod_editora) REFERENCES editora
);

-- -----------------------------------------------------
-- Table exemplar
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS exemplar (
    cod_exemplar SERIAL NOT NULL,
    cod_livro INT NOT NULL,
    cod_estado INT NOT NULL,
    CONSTRAINT pk_exemplar PRIMARY KEY (cod_exemplar),
    CONSTRAINT fk_cod_livro_exemplar FOREIGN KEY (cod_livro) REFERENCES livro,
    CONSTRAINT fk_cod_estado_exemplar FOREIGN KEY (cod_estado) REFERENCES estado
);  

-- -----------------------------------------------------
-- Table emprestimo_exemplar
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS emprestimo_exemplar (
    cod_emprestimo INT NOT NULL,
    cod_exemplar INT NOT NULL,
    CONSTRAINT pk_emprestimo_exemplar PRIMARY KEY (cod_emprestimo, cod_exemplar),
    CONSTRAINT fk_exemplar_emprestimo_exemplar FOREIGN KEY (cod_exemplar) REFERENCES exemplar,
    CONSTRAINT fk_emprestimo_emprestimo_exemplar FOREIGN KEY (cod_emprestimo) REFERENCES emprestimo
);

-- -----------------------------------------------------
-- Table livro_autor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS livro_autor (
    cod_autor INT NOT NULL,
    cod_livro INT NOT NULL,
    CONSTRAINT pk_livro_autor PRIMARY KEY (cod_autor, cod_livro),
    CONSTRAINT fk_cod_autor_livro_autor FOREIGN KEY (cod_autor) REFERENCES autor,
    CONSTRAINT fk_cod_livro_livro_autor FOREIGN KEY (cod_livro) REFERENCES livro
);

-- -----------------------------------------------------
-- Table livro_genero
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS livro_genero (
    cod_genero INT NOT NULL,
    cod_livro INT NOT NULL,
    CONSTRAINT pk_livro_genero PRIMARY KEY (cod_genero, cod_livro),
    CONSTRAINT fk_cod_livro_livro_genero FOREIGN KEY (cod_livro) REFERENCES livro,
    CONSTRAINT fk_cod_genero_livro_genero FOREIGN KEY (cod_genero) REFERENCES genero
);

-- -----------------------------------------------------
-- Insert autores
-- -----------------------------------------------------
INSERT INTO autor
(nome, sobrenome, situacao) VALUES 
    ('Jeca', 'Bala', 'ativo'),
    ('Machado', 'de Assis', 'ativo'),
    ('Clarice', 'Lispector', 'ativo'),
    ('Dan', 'Brown', 'ativo'),
    ('George', 'R. R. Martin', 'ativo'),
    ('Jojo', 'Moyes', 'ativo'),
    ('Svetlana', 'Alexievich', 'ativo'),
    ('Stephen', 'King', 'ativo'),
    ('José', 'Saramago', 'ativo'),
    ('Chimamanda', 'Adichie', 'ativo');

-- -----------------------------------------------------
-- Insert editoras
-- -----------------------------------------------------
INSERT INTO editora
(nome, situacao) VALUES
    ('JecaBala Livros', 'ativo'),
    ('Passarinho PiuPiu', 'ativo'),
    ('EDITORA DE LIVROS', 'ativo'),
    ('Torrent', 'ativo'),
    ('PDF MEU DEUS', 'ativo'),
    ('pedra PAPEL tesoura', 'ativo'),
    ('kpop é mto ruim', 'ativo');

-- -----------------------------------------------------
-- Insert perfis
-- -----------------------------------------------------
INSERT INTO perfil
(perfil, prazo, limite, qtde_renovacoes, situacao) VALUES
    ('Professor', 21, 15, 5, 'ativo'),
    ('Estudante', 14, 10, 3, 'ativo'),
    ('Comunitário', 7, 5, 1, 'ativo');


-- -----------------------------------------------------
-- Insert funcionario admin
-- -----------------------------------------------------
INSERT INTO funcionario VALUES (
    default,
    'admin',
    'admin',
    'admin@admin.com',
    'admin',
    'qn3LbvLtDx0lKtpH7nQKX9gnJJlzv4L7iCpo7brxAu9tTIhjvZmAycC0Ul6/umSre4hiUTBjfdAL2lMFaKm0yg==',
    'mCJ9ImTxkDFKeg==',
    'ativo'
);

-- -----------------------------------------------------
-- Insert estados
-- -----------------------------------------------------
INSERT INTO estado VALUES
    (default, 'disponível'),
    (default, 'emprestado'),
    (default, 'danificado'),
    (default, 'indisponível');

-- -----------------------------------------------------
-- Insert tipolivro
-- -----------------------------------------------------
INSERT INTO tipolivro VALUES
    (default, 'livro'),
    (default, 'enciclopédia'),
    (default, 'revista'),
    (default, 'dicionário');

-- -----------------------------------------------------
-- Insert generos
-- -----------------------------------------------------
INSERT INTO genero (genero) VALUES
    ('Ciências Políticas'),
    ('História'),
    ('Ficção Científica'),
    ('Biografia'),
    ('Contos'),
    ('Humor'),
    ('Poesia'),
    ('Ciências Exatas'),
    ('Tecnologia');

-- -----------------------------------------------------
-- Insert usuários
-- -----------------------------------------------------
INSERT INTO usuario (nome, sobrenome, endereco, cpf, email, data_nasc, data_cadastro, telefone, situacao, cod_perfil) VALUES
    ('Juca', 'Bala', 'Rua do JucaBala', '928.239.660-64', 'juca@bala.com', '01/02/2000', CURRENT_DATE, '(51) 99876-5432', 'ativo', 1),
    ('Paulo Pedro', 'Jerso', 'Rua do PauloPedro', '175.170.670-28', 'paulo@pedro.com', '08/09/1980', CURRENT_DATE, '(51) 99876-5432', 'ativo', 2),
    ('Maria', 'Sharapova', 'Rua da MariaSharapova', '194.772.860-10', 'maria@sharapova.com', '10/03/1992', CURRENT_DATE, '(51) 99876-5432', 'ativo', 3),
    ('Junia', 'Mosqueta', 'Rua da JuniaMosqueta', '956.726.740-54', 'junia@mosqueta.com', '02/10/1962', CURRENT_DATE, '(51) 99876-5432', 'ativo', 3),
    ('PeLanza', 'Coloride', 'Rua do PeLanza MEO', '137.264.500-41', 'pe@lanza.com', '01/01/2002', CURRENT_DATE, '(51) 99876-5432', 'ativo', 2),
    ('Ana', 'Banana', 'Rua da AnaBanana', '456.021.470-04', 'ana@banana.com', '12/12/1999', CURRENT_DATE, '(51) 99876-5432', 'ativo', 1);

-- -----------------------------------------------------
-- Insert empréstimos
-- -----------------------------------------------------
INSERT INTO emprestimo
(data_retirada, data_devolucao, data_devolvido, renovacoes, devolvido, cod_usuario, cod_func) VALUES 
    ('10/10/2020', '10/17/2020', '10/17/2020', 0 ,true, 4, 1),
    ('01/11/2020', '01/18/2020', '02/21/2021', 0 ,true, 2, 1),
    ('03/15/2021', '03/29/2021', null, 0 ,false, 2, 1),
    ('12/22/2020', '01/12/2020', '01/23/2021', 0 ,true, 1, 1),
    ('10/10/2019', '10/17/2019', null, 0 ,false, 3, 1),
    ('01/01/2019', '01/15/2019', null, 0 ,false, 5, 1),
    ('02/28/2021', '03/14/2021', '03/10/2021', 0 ,true, 2, 1),
    ('10/10/2020', '10/17/2020', '10/17/2020', 0 ,true, 3, 1),
    ('01/11/2020', '01/18/2020', '02/21/2021', 0 ,true, 4, 1),
    ('03/15/2021', '05/05/2021', null, 0 ,false, 1, 1),
    ('12/22/2020', '01/05/2020', '01/23/2021', 0 ,true, 5, 1),
    ('10/10/2019', '10/31/2019', null, 0 ,false, 6, 1),
    ('01/01/2019', '01/15/2019', '06/01/2019', 0 ,true, 2, 1),
    ('02/28/2021', '03/07/2021', '03/10/2021', 0 ,true, 4, 1);

-- -----------------------------------------------------
-- Insert multas
-- -----------------------------------------------------
INSERT INTO multa (valor, pago, data_pgto, cod_emprestimo) VALUES
    (101.75, true, '02/21/2021', 2),
    (8.0, true, '01/23/2021', 4),
    (0.0, false, null, 8),
    (0.0, false, null, 11),
    (37.75, true, '06/01/2019', 13),
    (0.0, false, null, 14);

-- -----------------------------------------------------
-- Insert livros
-- -----------------------------------------------------
INSERT INTO livro (isbn, titulo, ano, edicao, volume, exemplares, cod_tipolivro, cod_editora) VALUES 
    ('978-6-9362-3745-1', 'As Aventuras do Jeca Bala', '1950', 1, 1, 5, 1, 2),
    ('978-9-3633-1905-9', 'Chapeuzinho Vermelho', '1980', 3, 1, 2, 1, 3),
    ('978-9-7404-2176-4', 'Pequenos incêndios por toda parte', '1999', 1, 1, 3, 1, 6),
    ('978-9-7482-7125-5', 'Inferno Somos Nós', '1949', 1, 1, 1, 1, 7),
    ('978-5-6340-2688-6', 'A Insustentável Leveza do Ser', '2001', 1, 1, 1, 1, 2),
    ('978-2-0665-4272-4', 'As Veias Abertas da América Latina', '2010', 1, 1, 6, 1, 3),
    ('978-9-0387-9474-7', 'As Coisas Que Você Só Vê Quando Desacelera', '1890', 1, 1, 2, 1, 1),
    ('978-7-0984-5045-0', 'Águas Que Me Dançam', '2014', 1, 1, 3, 1, 5),
    ('978-2-3154-4561-5', 'O Amor nos Tempos do Cólera', '2018', 1, 1, 1, 1, 4),
    ('978-4-1900-1856-4', 'Era uma vez uma mulher que tentou matar o bebê da vizinha', '2001', 1, 1, 1, 1, 4),
    ('978-0-3878-2236-5', 'O Estranho Caso do Cachorro Morto', '2005', 1, 1, 2, 1, 7),
    ('978-8-1216-7205-4', 'Anatomia de um Instante', '2011', 1, 1, 6, 1, 6),
    ('978-6-8292-4749-9', 'As Coisas Que Você Só Vê Quando Desacelera', '1999', 1, 1, 3, 1, 5),
    ('978-6-3630-3352-7', 'Os Homens Que Não Amavam as Mulheres', '1969', 1, 1, 4, 1, 1);

-- -----------------------------------------------------
-- Insert exemplares
-- -----------------------------------------------------    
INSERT INTO exemplar (cod_livro, cod_estado) VALUES
    (1, 1), (1, 1), (1, 2), (1, 1), (1, 2),
    (2, 2), (2, 1),
    (3, 1), (3, 1), (3, 2),
    (4, 1),
    (5, 2),
    (6, 1), (6, 1), (6, 1), (6, 1), (6, 1), (6, 1),
    (7, 1), (7, 1),
    (8, 1), (8, 1), (8, 1),
    (9, 1),
    (10, 1),
    (11, 1), (11, 1),
    (12, 1), (12, 1), (12, 1), (12, 1), (12, 1), (12, 1), 
    (13, 1), (13, 1), (13, 1), 
    (14, 1), (14, 1), (14, 1), (14, 1);

-- -----------------------------------------------------
-- Insert emprestimo_exemplar
-- -----------------------------------------------------
INSERT INTO emprestimo_exemplar (cod_emprestimo, cod_exemplar) VALUES
    (1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7),
    (8, 8), (9, 9), (10, 10), (11, 11), (12, 12), (13, 13), (14, 14); 

-- -----------------------------------------------------
-- Insert livro_autor
-- -----------------------------------------------------
INSERT INTO livro_autor (cod_autor, cod_livro) VALUES
    (1, 1), (2, 1),
    (4, 2),
    (6, 3), (1, 3), (9, 3),
    (3, 4), (5, 4), 
    (7, 5),
    (8, 6),
    (9, 7), 
    (2, 8), 
    (10, 9), 
    (4, 10), (3, 10), (7, 10), 
    (1, 11), 
    (2, 12), 
    (3, 13), 
    (4, 14);

-- -----------------------------------------------------
-- Insert livro_genero
-- -----------------------------------------------------
INSERT INTO livro_genero (cod_livro, cod_genero) VALUES
    (1, 1), (1, 4), 
    (2, 3), 
    (3, 2), (3, 6), (3, 9), 
    (4, 4), 
    (5, 3), 
    (6, 6), (6, 2), (6, 1), (6, 3), 
    (7, 7), 
    (8, 9), 
    (9, 6), (9, 9), 
    (10, 3), 
    (11, 2), (11, 6), 
    (12, 1), 
    (13, 3),  (13, 2), (13, 5), 
    (14, 5); 



--SELECT PRA CONCATENAR STRING COM NOME DE AUTORES E GENEROS DE UM LIVRO
-- select l.*, livaut.autores, livgen.generos
-- from livro l 
-- join (
--  		select string_agg(aut2.nome_sobrenome, ', ') as autores, aut2.cod_livro
-- 		from (
-- 				select concat(isel.nome, ' ', isel.sobrenome) as nome_sobrenome, isel.cod_livro
-- 				from (
-- 						SELECT a.cod_autor, a.nome, a.sobrenome, la.cod_livro as cod_livro
--  						FROM public.autor a
--  						join livro_autor la on la.cod_autor = a.cod_autor
--  				) as isel
--  			) as aut2
--  			group by aut2.cod_livro
-- ) as livaut on l.cod_livro = livaut.cod_livro
-- join (
--   		select string_agg(aut.genero, ', ') as generos, aut.cod_livro
--  		from (
--  				SELECT g.cod_genero, g.genero, lg.cod_livro
--  				FROM public.genero g
--  				join livro_genero lg on lg.cod_genero = g.cod_genero
--  		) as aut
--  		group by aut.cod_livro
-- ) as livgen on l.cod_livro  = livgen.cod_livro
-- order by cod_livro;

--SELECT PRA CONCATENAR STRING COM NOME DE AUTORES DE UM LIVRO
-- select string_agg(aut.nome_sobrenome, ', ') as autores
-- from (
-- 	select *, concat(isel.nome, ' ', isel.sobrenome) as nome_sobrenome
-- 	from (
-- 		SELECT a.cod_autor, a.nome, a.sobrenome, la.cod_livro as cod_livro
-- 		FROM public.autor a
-- 		join livro_autor la on la.cod_autor = a.cod_autor
-- 	) as isel
-- 	where isel.cod_livro = 1
-- ) as aut;

--SELECT PRA CONCATENAR STRING COM NOME DE GENEROS DE UM LIVRO
-- select string_agg(aut.genero, ', ') as generos
-- from (
-- 		SELECT g.cod_genero, g.genero, lg.cod_livro
-- 		FROM public.genero g
-- 		join livro_genero lg on lg.cod_genero = g.cod_genero
-- 		where lg.cod_livro = 2
-- ) as aut;


