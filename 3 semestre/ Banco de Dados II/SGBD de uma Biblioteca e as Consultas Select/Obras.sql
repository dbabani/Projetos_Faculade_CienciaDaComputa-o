CREATE TABLE TiposDeObras (
id_codigo INT PRIMARY KEY NOT NULL,
Descricao VARCHAR(50) NOT NULL
);

CREATE TABLE Obra (
id_codigo INT PRIMARY KEY NOT NULL,
Titulo VARCHAR(50) NOT NULL,
autor VARCHAR(50) NOT NULL,
nroPaginas INT NOT NULL,
id_tipo_de_Obra INT NOT NULL,
FOREIGN KEY (id_tipo_de_Obra) REFERENCES TiposDeObras(id_codigo)
);

CREATE TABLE Pessoa (
id_CPF INT PRIMARY KEY NOT NULL,
Nome VARCHAR(50) NOT NULL,
Email VARCHAR(40) NOT NULL,
Telefone INT  
);

CREATE TABLE Emprestimo (
id_data_de_emprestimo DATE NOT NULL,
id_data_de_devolucao DATE NOT NULL,
id_obra INT NOT NULL,
id_CPF INT NOT NULL,
FOREIGN KEY (id_obra) REFERENCES Obra(id_codigo),
PRIMARY KEY (id_data_de_emprestimo, id_data_de_devolucao, id_obra, id_CPF)
);

CREATE TABLE Professor (
id_CPF INT PRIMARY KEY NOT NULL,
data_de_contratacao DATE NOT NULL,
FOREIGN KEY (id_CPF) REFERENCES Pessoa(id_CPF)
);



CREATE TABLE Aluno (
id_CPF INT NOT NULL,
id_nro_de_Matricula INT PRIMARY KEY NOT NULL,
nro_de_creditos_concluidos INT NOT NULL,
FOREIGN KEY (id_CPF) REFERENCES Pessoa(id_CPF)
);


INSERT INTO TiposDeObras (id_codigo, Descricao) VALUES
(1, 'Livro');
INSERT INTO TiposDeObras (id_codigo, Descricao) VALUES
(2, 'Revista');
INSERT INTO TiposDeObras (id_codigo, Descricao) VALUES
(3, 'Artigo Científico');
INSERT INTO TiposDeObras (id_codigo, Descricao) VALUES
(4, 'Material especial');



-- Obras
INSERT INTO Obra (id_codigo, Titulo, autor, nroPaginas,id_tipo_de_Obra) VALUES
(1, 'A Arte da Guerra', 'Sun Tzu', 200, 1 );
INSERT INTO Obra (id_codigo, Titulo, autor, nroPaginas,id_tipo_de_Obra) VALUES
(2, 'O Processo', 'Franz Kafka', 400, 3);
INSERT INTO Obra (id_codigo, Titulo, autor, nroPaginas,id_tipo_de_Obra) VALUES
(3, 'Revista de História', 'Editora Abril', 50, 2);
INSERT INTO Obra (id_codigo, Titulo, autor, nroPaginas,id_tipo_de_Obra) VALUES
(4,'Banco de dados Oracle','Daniel Callegari',300, 4);


-- Pessoas
INSERT INTO Pessoa (id_CPF, Nome,Email,Telefone) VALUES
(70535036230, 'Dhruv Babani','d.babani001@edu.pucrs.br',92988330082);
INSERT INTO Pessoa (id_CPF, Nome,Email,Telefone) VALUES
(22222222222, 'Maria dos Santos','m.santos002@edu.pucrs.br',9297830082);
INSERT INTO Pessoa (id_CPF, Nome,Email,Telefone) VALUES
(33333333333, 'Carlos Eduardo','c.eduardo003@edu.pucrs.br',51988340082);
INSERT INTO Pessoa (id_CPF, Nome,Email,Telefone) VALUES
(44444444444, 'Luciana Oliveira','l.oliveira004@edu.pucrs.br',5189789088);
INSERT INTO Pessoa (id_CPF, Nome,Email,Telefone) VALUES
(55555555555, 'Pedro Henrique','p.henrique005@edu.pucrs.br',9296062733);
INSERT INTO Pessoa (id_CPF, Nome,Email,Telefone) VALUES
(66666666666, 'Luciano Farias','l.farias@edu.pucrs.br',92988330082);
INSERT INTO Pessoa (id_CPF, Nome,Email,Telefone) VALUES
(77777777777, 'Lorenzo Martins','l.martis@edu.pucrs.br',92898378838);
INSERT INTO Pessoa (id_CPF, Nome,Email,Telefone) VALUES
(88888888888, 'Eduardo Maia','e.maia@edu.pucrs.br',51988330082);



-- Emprestimos
INSERT INTO Emprestimo (id_data_de_emprestimo, id_data_de_devolucao, id_obra, id_CPF) VALUES
(TO_DATE('21/05/2001', 'dd/mm/yyyy'), TO_DATE('28/05/2001', 'dd/mm/yyyy'), 1, 70535036230);
INSERT INTO Emprestimo (id_data_de_emprestimo, id_data_de_devolucao, id_obra, id_CPF) VALUES
(TO_DATE('01/05/2001', 'dd/mm/yyyy'), TO_DATE('08/05/2001', 'dd/mm/yyyy'), 2, 22222222222);
INSERT INTO Emprestimo (id_data_de_emprestimo, id_data_de_devolucao, id_obra, id_CPF) VALUES
(TO_DATE('08/05/2001', 'dd/mm/yyyy'), TO_DATE('22/05/2001', 'dd/mm/yyyy'), 3, 33333333333);
INSERT INTO Emprestimo (id_data_de_emprestimo, id_data_de_devolucao, id_obra, id_CPF) VALUES
(TO_DATE('15/05/2001', 'dd/mm/yyyy'), TO_DATE('29/05/2001', 'dd/mm/yyyy'), 1, 44444444444);
INSERT INTO Emprestimo (id_data_de_emprestimo, id_data_de_devolucao, id_obra, id_CPF) VALUES
(TO_DATE('22/05/2001', 'dd/mm/yyyy'), TO_DATE('29/05/2001', 'dd/mm/yyyy'), 2, 55555555555);
INSERT INTO Emprestimo (id_data_de_emprestimo, id_data_de_devolucao, id_obra, id_CPF) VALUES
(TO_DATE('01/06/2001', 'dd/mm/yyyy'), TO_DATE('08/06/2001', 'dd/mm/yyyy'), 1, 22222222222);
INSERT INTO Emprestimo (id_data_de_emprestimo, id_data_de_devolucao, id_obra, id_CPF) VALUES
(TO_DATE('08/06/2001', 'dd/mm/yyyy'), TO_DATE('22/06/2001', 'dd/mm/yyyy'), 3, 33333333333);
INSERT INTO Emprestimo (id_data_de_emprestimo, id_data_de_devolucao, id_obra, id_CPF) VALUES
(TO_DATE('15/06/2001', 'dd/mm/yyyy'), TO_DATE('22/06/2001', 'dd/mm/yyyy'), 2, 77777777777);
INSERT INTO Emprestimo (id_data_de_emprestimo, id_data_de_devolucao, id_obra, id_CPF) VALUES
(TO_DATE('22/06/2001', 'dd/mm/yyyy'), TO_DATE('29/06/2001', 'dd/mm/yyyy'), 1, 55555555555);
INSERT INTO Emprestimo (id_data_de_emprestimo, id_data_de_devolucao, id_obra, id_CPF) VALUES
(TO_DATE('01/07/2001', 'dd/mm/yyyy'), TO_DATE('08/07/2001', 'dd/mm/yyyy'), 3, 70535036230);

INSERT INTO Professor (id_CPF, data_de_contratacao) VALUES
(44444444444, TO_DATE('21/04/2017', 'dd/mm/yyyy'));
INSERT INTO Professor (id_CPF, data_de_contratacao) VALUES
(33333333333, TO_DATE('21/05/2019', 'dd/mm/yyyy'));


-- Alunos
INSERT INTO Aluno (id_nro_de_Matricula,id_CPF, nro_de_creditos_concluidos) VALUES
(22110019, 70535036230, 56);
INSERT INTO Aluno (id_nro_de_Matricula,id_CPF, nro_de_creditos_concluidos) VALUES
(20220002,22222222222, 100);
INSERT INTO Aluno (id_nro_de_Matricula,id_CPF, nro_de_creditos_concluidos) VALUES
(20220003,88888888888, 60);
INSERT INTO Aluno (id_nro_de_Matricula,id_CPF, nro_de_creditos_concluidos) VALUES
(20220004,55555555555, 120);
INSERT INTO Aluno (id_nro_de_Matricula,id_CPF, nro_de_creditos_concluidos) VALUES
(20220005,66666666666, 40);
INSERT INTO Aluno (id_nro_de_Matricula,id_CPF, nro_de_creditos_concluidos) VALUES
(20229999,77777777777 ,30);


--
SELECT Titulo
FROM Obra 
WHERE nroPaginas > 100 AND id_tipo_de_Obra = 1;

SELECT AVG(id_data_de_devolucao - id_data_de_emprestimo) AS "Tempo Médio de Empréstimo" 
FROM Emprestimo 
INNER JOIN Obra ON Emprestimo.id_obra = Obra.id_codigo
WHERE Obra.id_tipo_de_Obra = 2;

SELECT COUNT(*) AS numero_livros_emprestados
FROM Emprestimo
JOIN Obra ON Emprestimo.id_obra = Obra.id_codigo
JOIN TiposDeObras ON Obra.id_tipo_de_Obra = TiposDeObras.id_codigo
WHERE TiposDeObras.Descricao = 'Livro';
