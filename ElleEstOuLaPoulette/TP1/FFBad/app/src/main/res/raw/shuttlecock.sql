DROP INDEX I_PK_FABRICANT ON FABRICANT;

DROP INDEX I_PK_COMMANDE ON COMMANDE;

DROP INDEX I_FK_COMMANDE_CLIENT ON COMMANDE;

DROP INDEX I_PK_VOLANT ON VOLANT;

DROP INDEX I_FK_VOLANT_FABRICANT ON VOLANT;

DROP INDEX I_PK_CLIENT ON CLIENT;

DROP INDEX I_PK_LIGNE_COMMANDE ON LIGNE_COMMANDE;

DROP INDEX I_FK_LIGNE_COMMANDE_COMMANDE ON LIGNE_COMMANDE;

DROP INDEX I_FK_LIGNE_COMMANDE_TUBE ON LIGNE_COMMANDE;

DROP INDEX I_PK_TUBE ON TUBE;

DROP INDEX I_FK_TUBE_VOLANT ON TUBE;

DROP INDEX I_PK_VALIDITE ON VALIDITE;

# -----------------------------------------------------------------------------
#       TABLE : FABRICANT
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS IF NOT EXISTS FABRICANT
 (
   NOM VARCHAR(128) NOT NULL  ,
   ADDRESSE VARCHAR(255) NOT NULL  ,
   CONTACT VARCHAR(128) NOT NULL  ,
   TELEPHONE VARCHAR(128) NOT NULL  ,
   EMAIL VARCHAR(128) NOT NULL  
 );

# -----------------------------------------------------------------------------
#       TABLE : COMMANDE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS IF NOT EXISTS COMMANDE
 (
   REF_DE_COMMANDE VARCHAR(128) NOT NULL  ,
   NOM CHAR(32) NOT NULL  ,
   ESTPAYE BOOL NOT NULL  
 ); 

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE COMMANDE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_COMMANDE_CLIENT ON COMMANDE (NOM ASC);

# -----------------------------------------------------------------------------
#       TABLE : VOLANT
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS IF NOT EXISTS VOLANT
 (
   REF VARCHAR(128) NOT NULL  ,
   NOM VARCHAR(128) NOT NULL  ,
   MARQUE VARCHAR(128) NOT NULL  ,
   CLASSEMENT SMALLINT(1) NOT NULL  
 ); 

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE VOLANT
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_VOLANT_FABRICANT ON VOLANT (NOM ASC);

# -----------------------------------------------------------------------------
#       TABLE : CLIENT
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS IF NOT EXISTS CLIENT
 (
   NOM CHAR(32) NOT NULL  ,
   ADRESSE VARCHAR(255) NOT NULL  
 ); 


# -----------------------------------------------------------------------------
#       TABLE : LIGNE_COMMANDE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS IF NOT EXISTS LIGNE_COMMANDE
 (
   REF VARCHAR(128) NOT NULL  ,
   NOMBRE_DE_VOLANTS INTEGER(2) NOT NULL  ,
   REF_DE_COMMANDE VARCHAR(128) NOT NULL  ,
   NUMERO CHAR(32) NOT NULL  ,
   PRIX_TOTAL CHAR(32) NULL  ,
   NOMBRE CHAR(32) NULL  
 ); 

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE LIGNE_COMMANDE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_LIGNE_COMMANDE_COMMANDE ON LIGNE_COMMANDE (REF_DE_COMMANDE ASC);

CREATE  INDEX I_FK_LIGNE_COMMANDE_TUBE ON LIGNE_COMMANDE (REF ASC,NOMBRE_DE_VOLANTS ASC);

# -----------------------------------------------------------------------------
#       TABLE : TUBE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS IF NOT EXISTS TUBE
 (
   REF VARCHAR(128) NOT NULL  ,
   NOMBRE_DE_VOLANTS INTEGER(2) NOT NULL  ,
   PRIX INTEGER(2) NULL  ,
   NOMBRE_EN_STOCK CHAR(32) NOT NULL  
 ); 

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE TUBE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_TUBE_VOLANT ON TUBE (REF ASC);

# -----------------------------------------------------------------------------
#       TABLE : VALIDITE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS IF NOT EXISTS VALIDITE
 (
   REF VARCHAR(128) NOT NULL  ,
   ANNEEDEBUT BIGINT(4) NOT NULL  ,
   ANNEEFIN BIGINT(4) NOT NULL  
 );

# -----------------------------------------------------------------------------
#       CREATION DES REFERENCES DE TABLE
# -----------------------------------------------------------------------------


ALTER TABLE COMMANDE 
  ADD FOREIGN KEY FK_COMMANDE_CLIENT (NOM)
      REFERENCES CLIENT (NOM) ;


ALTER TABLE VOLANT 
  ADD FOREIGN KEY FK_VOLANT_FABRICANT (NOM)
      REFERENCES FABRICANT (NOM) ;


ALTER TABLE LIGNE_COMMANDE 
  ADD FOREIGN KEY FK_LIGNE_COMMANDE_COMMANDE (REF_DE_COMMANDE)
      REFERENCES COMMANDE (REF_DE_COMMANDE) ;


ALTER TABLE LIGNE_COMMANDE 
  ADD FOREIGN KEY FK_LIGNE_COMMANDE_TUBE (REF,NOMBRE_DE_VOLANTS)
      REFERENCES TUBE (REF,NOMBRE_DE_VOLANTS) ;


ALTER TABLE TUBE 
  ADD FOREIGN KEY FK_TUBE_VOLANT (REF)
      REFERENCES VOLANT (REF) ;


ALTER TABLE VALIDITE 
  ADD FOREIGN KEY FK_VALIDITE_VOLANT (REF)
      REFERENCES VOLANT (REF) ;