-- Attention, penser à activer le support des foreign keys
--
-- PRAGMA foreign_keys=ON;
--

-- Définition des tables

PRAGMA foreign_keys=ON;

create table marque (
	marque_id integer primary key,
	nom text unique
);

create table tube (
	tube_id integer primary key,
	reference text unique,
	marque integer references marque(marque_id),
	prix numeric,
	stock integer
);

create table vente (
	tube integer references tube(tube_id),
	date_achat date,
	nom_acheteur text,
	prix numeric,
	quantite integer
);

-- Insertions

insert into marque values(1, 'Yonex');
insert into marque values(2, 'RSL');

insert into tube values(null, 'AS30', 1, 27, 500);
insert into tube values(null, 'Grade 3', 2, 16.70, 5000);
insert into tube values(null, 'Grade A9', 2, 13.70, 10000);
insert into tube values(null, 'Grade A1', 2, 21, 6000);
