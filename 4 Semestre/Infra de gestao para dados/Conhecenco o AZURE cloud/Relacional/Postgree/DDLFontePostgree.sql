
CREATE TABLE Acidentes
(
	idAcidente	Numeric(5) Not Null,
	
	Constraint pk_acidente Primary Key(idAcidente)
);



CREATE TABLE Tipos
(	
	idTipo			Numeric(5) Not Null,
	idAcidente		Numeric(5) Not Null,
	atropelamento	Numeric(3),
	capotagem		Numeric(3),
	choqueObjFixo	Numeric(3),
	colisao			Numeric(3),
	colisaoLat		Numeric(3),
	naoInf			Numeric(3),
	outro			Numeric(3),
	tombamento		Numeric(3),
	
	Constraint pk_tipo Primary Key(idTipo)
);



CREATE TABLE Locais
(
	idLocal			Numeric(5) Not Null,
	idAcidente		Numeric(5) Not Null,
	municipio		VARCHAR(50),
	
	Constraint pk_local Primary Key(idLocal)
);

CREATE TABLE Participantes
(
	idPart			Numeric(5) Not Null,
	idAcidente		Numeric(5) Not Null,
	caronaMoto		Numeric(2),
	carroceiro		Numeric(2),
	ciclista		Numeric(2),
	condutor		Numeric(2),
	motociclista	Numeric(2),
	naoInf			Numeric(2),
	outros			Numeric(2),
	passageiro		Numeric(2),
	pedestre		Numeric(2),
	
	Constraint pk_participantes Primary Key(idPart)
);



ALTER TABLE Tipos
ADD CONSTRAINT fk_tipos_acidentes FOREIGN KEY(idAcidente) REFERENCES acidentes(idAcidente);

ALTER TABLE Locais
ADD CONSTRAINT fk_locais_acidentes FOREIGN KEY(idAcidente) REFERENCES acidentes(idAcidente);

ALTER TABLE Participantes
ADD CONSTRAINT fk_partic_acidentes FOREIGN KEY(idAcidente) REFERENCES acidentes(idAcidente);
