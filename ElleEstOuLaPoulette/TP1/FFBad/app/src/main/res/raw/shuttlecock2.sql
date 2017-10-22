CREATE TABLE IF NOT EXISTS "CLIENT"(
  "NOM" CHAR(32) NOT NULL,
  "ADRESSE" VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS "COMMANDE"(
  "REF_DE_COMMANDE" VARCHAR(128) NOT NULL,
  "NOM" CHAR(32) NOT NULL,
  "ESTPAYE" INTEGER NOT NULL,
  CONSTRAINT "FK_COMMANDE_CLIENT"
    FOREIGN KEY("NOM")
    REFERENCES "CLIENT"("NOM")
);
CREATE INDEX IF NOT EXISTS "COMMANDE.I_FK_COMMANDE_CLIENT" ON "COMMANDE" ("NOM");
CREATE TABLE IF NOT EXISTS "FABRICANT"(
  "NOM" VARCHAR(128) NOT NULL,
  "ADDRESSE" VARCHAR(255) NOT NULL,
  "CONTACT" VARCHAR(128) NOT NULL,
  "TELEPHONE" VARCHAR(128) NOT NULL,
  "EMAIL" VARCHAR(128) NOT NULL
);
CREATE TABLE IF NOT EXISTS "VOLANT"(
  "REF" VARCHAR(128) NOT NULL,
  "NOM" VARCHAR(128) NOT NULL,
  "MARQUE" VARCHAR(128) NOT NULL,
  "CLASSEMENT" INTEGER NOT NULL,
  CONSTRAINT "FK_VOLANT_FABRICANT"
    FOREIGN KEY("NOM")
    REFERENCES "FABRICANT"("NOM")
);
CREATE INDEX IF NOT EXISTS "VOLANT.I_FK_VOLANT_FABRICANT" ON "VOLANT" ("NOM");
CREATE TABLE IF NOT EXISTS "TUBE"(
  "REF" VARCHAR(128) NOT NULL,
  "NOMBRE_DE_VOLANTS" INTEGER NOT NULL,
  "PRIX" INTEGER DEFAULT NULL,
  "NOMBRE_EN_STOCK" CHAR(32) NOT NULL,
  CONSTRAINT "FK_TUBE_VOLANT"
    FOREIGN KEY("REF")
    REFERENCES "VOLANT"("REF")
);
CREATE INDEX IF NOT EXISTS "TUBE.I_FK_TUBE_VOLANT" ON "TUBE" ("REF");
CREATE TABLE IF NOT EXISTS "LIGNE_COMMANDE"(
  "REF" VARCHAR(128) NOT NULL,
  "NOMBRE_DE_VOLANTS" INTEGER NOT NULL,
  "REF_DE_COMMANDE" VARCHAR(128) NOT NULL,
  "NUMERO" CHAR(32) NOT NULL,
  "PRIX_TOTAL" CHAR(32) DEFAULT NULL,
  "NOMBRE" CHAR(32) DEFAULT NULL,
  CONSTRAINT "FK_LIGNE_COMMANDE_COMMANDE"
    FOREIGN KEY("REF_DE_COMMANDE")
    REFERENCES "COMMANDE"("REF_DE_COMMANDE"),
  CONSTRAINT "FK_LIGNE_COMMANDE_TUBE"
    FOREIGN KEY("REF","NOMBRE_DE_VOLANTS")
    REFERENCES "TUBE"("REF","NOMBRE_DE_VOLANTS")
);
CREATE INDEX IF NOT EXISTS "LIGNE_COMMANDE.I_FK_LIGNE_COMMANDE_COMMANDE" ON "LIGNE_COMMANDE" ("REF_DE_COMMANDE");
CREATE INDEX IF NOT EXISTS "LIGNE_COMMANDE.I_FK_LIGNE_COMMANDE_TUBE" ON "LIGNE_COMMANDE" ("REF");
CREATE INDEX IF NOT EXISTS "LIGNE_COMMANDE.FK_LIGNE_COMMANDE_TUBE" ON "LIGNE_COMMANDE" ("REF","NOMBRE_DE_VOLANTS");
CREATE TABLE IF NOT EXISTS "VALIDITE"(
  "REF" VARCHAR(128) NOT NULL,
  "ANNEEDEBUT" INTEGER NOT NULL,
  "ANNEEFIN" INTEGER NOT NULL,
  CONSTRAINT "FK_VALIDITE_VOLANT"
    FOREIGN KEY("REF")
    REFERENCES "VOLANT"("REF")
);
CREATE INDEX IF NOT EXISTS "VALIDITE.FK_VALIDITE_VOLANT" ON "VALIDITE" ("REF");