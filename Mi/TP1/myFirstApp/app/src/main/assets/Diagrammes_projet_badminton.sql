CREATE TABLE Personne (
 id CHAR(10) NOT NULL,
 nom CHAR(10) NOT NULL,
 prénom CHAR(10) NOT NULL,
 sexe CHAR(10),
 telephone CHAR(10),
 email CHAR(10)
);

ALTER TABLE Personne ADD CONSTRAINT PK_Personne PRIMARY KEY (id);


CREATE TABLE Rapport (
 id_rapport CHAR(10) NOT NULL,
 nom_laboratoire CHAR(10),
 numero_rapport CHAR(10)
);

ALTER TABLE Rapport ADD CONSTRAINT PK_Rapport PRIMARY KEY (id_rapport);


CREATE TABLE Saison (
 id CHAR(10) NOT NULL,
 nom CHAR(10)
);

ALTER TABLE Saison ADD CONSTRAINT PK_Saison PRIMARY KEY (id);


CREATE TABLE Statut (
 id CHAR(10) NOT NULL,
 intitulé CHAR(10)
);

ALTER TABLE Statut ADD CONSTRAINT PK_Statut PRIMARY KEY (id);


CREATE TABLE Entité (
 id CHAR(10) NOT NULL,
 nom CHAR(10),
 adresse CHAR(10),
 id_contact CHAR(10) NOT NULL,
 id_statut CHAR(10) NOT NULL
);

ALTER TABLE Entité ADD CONSTRAINT PK_Entité PRIMARY KEY (id);


CREATE TABLE Volant (
 id CHAR(10) NOT NULL,
 nom CHAR(10),
 reference CHAR(10),
 marque CHAR(10),
 classement_federal CHAR(10),
 id_saison CHAR(10) NOT NULL,
 id_rapport CHAR(10) NOT NULL,
 id_constructeur CHAR(10)
);

ALTER TABLE Volant ADD CONSTRAINT PK_Volant PRIMARY KEY (id);


CREATE TABLE Commande (
 id CHAR(10) NOT NULL,
 payé CHAR(10),
 id_acheteur CHAR(10)
);

ALTER TABLE Commande ADD CONSTRAINT PK_Commande PRIMARY KEY (id);


CREATE TABLE Produit (
 id_volant CHAR(10) NOT NULL,
 id_distributeur CHAR(10) NOT NULL,
 prix CHAR(10),
 stock CHAR(10)
);

ALTER TABLE Produit ADD CONSTRAINT PK_Produit PRIMARY KEY (id_volant,id_distributeur);


CREATE TABLE Entrée (
 id_volant CHAR(10) NOT NULL,
 id_distributeur CHAR(10) NOT NULL,
 id_commande CHAR(10) NOT NULL,
 nombre CHAR(10)
);

ALTER TABLE Entrée ADD CONSTRAINT PK_Entrée PRIMARY KEY (id_volant,id_distributeur,id_commande);


ALTER TABLE Entité ADD CONSTRAINT FK_Entité_0 FOREIGN KEY (id_contact) REFERENCES Personne (id);
ALTER TABLE Entité ADD CONSTRAINT FK_Entité_1 FOREIGN KEY (id_statut) REFERENCES Statut (id);


ALTER TABLE Volant ADD CONSTRAINT FK_Volant_0 FOREIGN KEY (id_saison) REFERENCES Saison (id);
ALTER TABLE Volant ADD CONSTRAINT FK_Volant_1 FOREIGN KEY (id_rapport) REFERENCES Rapport (id_rapport);
ALTER TABLE Volant ADD CONSTRAINT FK_Volant_2 FOREIGN KEY (id_constructeur) REFERENCES Entité (id);


ALTER TABLE Commande ADD CONSTRAINT FK_Commande_0 FOREIGN KEY (id_acheteur) REFERENCES Entité (id);


ALTER TABLE Produit ADD CONSTRAINT FK_Produit_0 FOREIGN KEY (id_volant) REFERENCES Volant (id);
ALTER TABLE Produit ADD CONSTRAINT FK_Produit_1 FOREIGN KEY (id_distributeur) REFERENCES Entité (id);


ALTER TABLE Entrée ADD CONSTRAINT FK_Entrée_0 FOREIGN KEY (id_volant,id_distributeur) REFERENCES Produit (id_volant,id_distributeur);
ALTER TABLE Entrée ADD CONSTRAINT FK_Entrée_1 FOREIGN KEY (id_commande) REFERENCES Commande (id);


