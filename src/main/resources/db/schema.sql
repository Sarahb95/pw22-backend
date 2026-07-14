-- =====================================================================
-- Schema DDL — variante per H2 in modalità MySQL.
-- Per il deploy reale su MySQL/MariaDB usare database/schema.sql.
-- =====================================================================

DROP TABLE IF EXISTS abbonamento;
DROP TABLE IF EXISTS prenotazione;
DROP TABLE IF EXISTS replica;
DROP TABLE IF EXISTS spettacolo;
DROP TABLE IF EXISTS posto;
DROP TABLE IF EXISTS sala;
DROP TABLE IF EXISTS utente;

CREATE TABLE utente (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    email           VARCHAR(150) NOT NULL UNIQUE,
    password_hash   VARCHAR(255) NOT NULL,
    nome            VARCHAR(80)  NOT NULL,
    cognome         VARCHAR(80)  NOT NULL,
    data_nascita    DATE,
    data_iscrizione TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE sala (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome        VARCHAR(100) NOT NULL,
    capienza    INT          NOT NULL CHECK (capienza > 0),
    descrizione CLOB
);

CREATE TABLE posto (
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    sala_id  BIGINT      NOT NULL,
    fila     VARCHAR(5)  NOT NULL,
    numero   INT         NOT NULL,
    settore  VARCHAR(30) NOT NULL DEFAULT 'PLATEA',
    CONSTRAINT fk_posto_sala FOREIGN KEY (sala_id) REFERENCES sala(id) ON DELETE CASCADE,
    CONSTRAINT uq_posto_sala UNIQUE (sala_id, fila, numero)
);

CREATE TABLE spettacolo (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    titolo          VARCHAR(200) NOT NULL,
    categoria       VARCHAR(20)  NOT NULL DEFAULT 'TEATRO',
    regista         VARCHAR(150),
    cast_principale CLOB,
    descrizione     CLOB,
    trama           CLOB,
    genere          VARCHAR(50),
    durata_min      INT,
    locandina_url   VARCHAR(500),
    CONSTRAINT chk_spettacolo_categoria CHECK (categoria IN ('TEATRO','DANZA','CINEMA','EVENTO'))
);

CREATE TABLE replica (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    spettacolo_id BIGINT       NOT NULL,
    sala_id       BIGINT       NOT NULL,
    data_ora      TIMESTAMP    NOT NULL,
    prezzo_base   DECIMAL(8,2) NOT NULL CHECK (prezzo_base >= 0),
    CONSTRAINT fk_replica_spettacolo FOREIGN KEY (spettacolo_id) REFERENCES spettacolo(id) ON DELETE CASCADE,
    CONSTRAINT fk_replica_sala       FOREIGN KEY (sala_id)       REFERENCES sala(id)
);

CREATE INDEX idx_replica_data ON replica(data_ora);

CREATE TABLE prenotazione (
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    utente_id         BIGINT    NOT NULL,
    replica_id        BIGINT    NOT NULL,
    posto_id          BIGINT    NOT NULL,
    data_prenotazione TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    stato             VARCHAR(20) NOT NULL DEFAULT 'ATTIVA',
    CONSTRAINT fk_prenotazione_utente  FOREIGN KEY (utente_id)  REFERENCES utente(id),
    CONSTRAINT fk_prenotazione_replica FOREIGN KEY (replica_id) REFERENCES replica(id) ON DELETE CASCADE,
    CONSTRAINT fk_prenotazione_posto   FOREIGN KEY (posto_id)   REFERENCES posto(id),
    CONSTRAINT uq_posto_per_replica    UNIQUE (replica_id, posto_id),
    CONSTRAINT chk_prenotazione_stato  CHECK (stato IN ('ATTIVA','ANNULLATA'))
);

CREATE TABLE abbonamento (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    utente_id    BIGINT       NOT NULL,
    tipo         VARCHAR(20)  NOT NULL,
    data_inizio  DATE         NOT NULL,
    data_fine    DATE         NOT NULL,
    prezzo       DECIMAL(8,2) NOT NULL,
    CONSTRAINT fk_abbonamento_utente FOREIGN KEY (utente_id) REFERENCES utente(id),
    CONSTRAINT chk_abbonamento_tipo  CHECK (tipo IN ('STAGIONALE','OPEN10','UNDER25')),
    CONSTRAINT chk_abbonamento_date  CHECK (data_fine >= data_inizio)
);

