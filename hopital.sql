USE hopital;

SET FOREIGN_KEY_CHECKS = 0;
SET GLOBAL event_scheduler = ON;

DROP TABLE IF EXISTS medecin;
CREATE TABLE medecin
(
    medecin_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nom        varchar(50)                    NOT NULL,
    prenom     varchar(50)                    NOT NULL,
    salaire    DOUBLE,
    id_chef    INT,
    service_id INT
);

DROP TABLE IF EXISTS malade;
CREATE TABLE malade
(
    malade_id     INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nom           varchar(50)                    NOT NULL,
    prenom        varchar(50)                    NOT NULL,
    rue           varchar(200),
    numero_de_rue INT,
    ville         varchar(50)
);
DROP TABLE IF EXISTS participation;
CREATE TABLE participation
(
    participation_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_medecin       INT,
    id_equipe        INT,
    fonction         varchar(50)
);

DROP TABLE IF EXISTS service;
CREATE TABLE service
(
    service_id   INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nom          varchar(50)                    NOT NULL,
    localisation varchar(200)                   not NULL,
    id_chef      INT
);

DROP TABLE IF EXISTS equipe;
CREATE TABLE equipe
(
    equipe_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nom       varchar(20)                    NOT NULL
);

ALTER TABLE participation
    ADD FOREIGN KEY (id_equipe) REFERENCES equipe (equipe_id),
    ADD FOREIGN KEY (id_medecin) REFERENCES medecin (medecin_id);

ALTER TABLE service
    ADD FOREIGN KEY (id_chef) REFERENCES medecin (medecin_id);

ALTER TABLE medecin
    ADD FOREIGN KEY (service_id) REFERENCES service (service_id);

SET FOREIGN_KEY_CHECKS = 1;
