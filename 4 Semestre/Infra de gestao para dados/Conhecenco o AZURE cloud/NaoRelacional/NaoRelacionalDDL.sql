CREATE TABLE Acidentes
(
	id_Acidente				NUMERIC(5) NOT NULL,
	causa_acidente			VARCHAR2(60),
	tipo_acidente			VARCHAR2(40),
	classificacao_acidente	VARCHAR2(20),
	dataHora				TIMESTAMP,
	dia_semana				VARCHAR2(15),

	CONSTRAINT pk_acidentes PRIMARY KEY(id_Acidente)
);


CREATE TABLE Locais
(
	id_Local		NUMERIC(5) NOT NULL,
	id_Acidente		NUMERIC(5) NOT NULL,
	municipio		VARCHAR2(40),
	uf				VARCHAR2(2),
	br				NUMERIC(3),
	km_br			NUMERIC(4,1),
	
	CONSTRAINT pk_locais	PRIMARY KEY(id_Local)
);



CREATE TABLE Participantes
(
	id_Participantes	NUMERIC(5)	NOT NULL,
	id_Acidente			NUMERIC(5)	NOT NULL,
	pessoas				NUMERIC(2),
	mortos				NUMERIC(2),
	feridos_leves		NUMERIC(2),
	feridos_graves		NUMERIC(2),
	ilesos				NUMERIC(2),
	ignorados			NUMERIC(2),
	feridos_totais		NUMERIC(2),
	veiculos			NUMERIC(2),
	
	CONSTRAINT pk_participantes PRIMARY KEY(id_Participantes)
);




CREATE TABLE Condicoes
(
	id_Condicoes		NUMERIC(5)	NOT NULL,
	id_Acidente			NUMERIC(5)	NOT NULL,
	cond_metereologica	VARCHAR2(20),
	sentido_via			VARCHAR2(20),
	tipo_pista			VARCHAR2(10),
	tracado_via			VARCHAR(25),
	fase_dia			VARCHAR2(15),
	
	CONSTRAINT pk_condicoes PRIMARY KEY(id_Condicoes)
);



ALTER TABLE Locais
ADD CONSTRAINT fk_local_acidente FOREIGN KEY(id_Acidente) REFERENCES Acidentes(id_Acidente);

ALTER TABLE Participantes
ADD CONSTRAINT fk_participantes_acidente FOREIGN KEY(id_Acidente) REFERENCES Acidentes(id_Acidente); 

ALTER TABLE Condicoes
ADD CONSTRAINT fk_condicoes_acidente FOREIGN KEY (id_Acidente) REFERENCES Acidentes(id_Acidente); 































