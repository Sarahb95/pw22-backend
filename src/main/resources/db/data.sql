-- =====================================================================
-- Seed per H2 — Nuovo Cinema Teatro Eliseo, Poggiomarino
-- Programmazione curata: teatro / danza / cinema / eventi
-- =====================================================================

-- ----- UTENTI ---------------------------------------------------------
-- Le password mostrate sono hash BCrypt di stringhe note:
--   marco@example.it  -> password: marco123
--   giulia@example.it -> password: giulia123
INSERT INTO utente (email, password_hash, nome, cognome, data_nascita) VALUES
    ('marco@example.it',  '$2a$10$PlaceHolderPlaceHolderPlaceHolderPlaceHolderPlaceHolde', 'Marco',  'Ferraro',  DATE '1988-09-14'),
    ('giulia@example.it', '$2a$10$PlaceHolderPlaceHolderPlaceHolderPlaceHolderPlaceHolde', 'Giulia', 'De Rosa',  DATE '2004-02-20');

-- ----- SALE -----------------------------------------------------------
INSERT INTO sala (nome, capienza, descrizione) VALUES
    ('Sala Grande', 60, 'Sala storica del cinema, palco e schermo, 6 file da 10 posti.'),
    ('Sala Studio', 30, 'Spazio raccolto per danza contemporanea, prove aperte e salotti filosofici.'),
    ('Sala Astra',  45, 'Sala cinematografica con schermo panoramico, 5 file da 9 posti.');

-- ----- POSTI ----------------------------------------------------------
-- Sala Grande: 6 file (A-F) x 10 posti
INSERT INTO posto (sala_id, fila, numero, settore) VALUES
    (1,'A',1,'PLATEA'),(1,'A',2,'PLATEA'),(1,'A',3,'PLATEA'),(1,'A',4,'PLATEA'),(1,'A',5,'PLATEA'),
    (1,'A',6,'PLATEA'),(1,'A',7,'PLATEA'),(1,'A',8,'PLATEA'),(1,'A',9,'PLATEA'),(1,'A',10,'PLATEA'),
    (1,'B',1,'PLATEA'),(1,'B',2,'PLATEA'),(1,'B',3,'PLATEA'),(1,'B',4,'PLATEA'),(1,'B',5,'PLATEA'),
    (1,'B',6,'PLATEA'),(1,'B',7,'PLATEA'),(1,'B',8,'PLATEA'),(1,'B',9,'PLATEA'),(1,'B',10,'PLATEA'),
    (1,'C',1,'PLATEA'),(1,'C',2,'PLATEA'),(1,'C',3,'PLATEA'),(1,'C',4,'PLATEA'),(1,'C',5,'PLATEA'),
    (1,'C',6,'PLATEA'),(1,'C',7,'PLATEA'),(1,'C',8,'PLATEA'),(1,'C',9,'PLATEA'),(1,'C',10,'PLATEA'),
    (1,'D',1,'PLATEA'),(1,'D',2,'PLATEA'),(1,'D',3,'PLATEA'),(1,'D',4,'PLATEA'),(1,'D',5,'PLATEA'),
    (1,'D',6,'PLATEA'),(1,'D',7,'PLATEA'),(1,'D',8,'PLATEA'),(1,'D',9,'PLATEA'),(1,'D',10,'PLATEA'),
    (1,'E',1,'PLATEA'),(1,'E',2,'PLATEA'),(1,'E',3,'PLATEA'),(1,'E',4,'PLATEA'),(1,'E',5,'PLATEA'),
    (1,'E',6,'PLATEA'),(1,'E',7,'PLATEA'),(1,'E',8,'PLATEA'),(1,'E',9,'PLATEA'),(1,'E',10,'PLATEA'),
    (1,'F',1,'PLATEA'),(1,'F',2,'PLATEA'),(1,'F',3,'PLATEA'),(1,'F',4,'PLATEA'),(1,'F',5,'PLATEA'),
    (1,'F',6,'PLATEA'),(1,'F',7,'PLATEA'),(1,'F',8,'PLATEA'),(1,'F',9,'PLATEA'),(1,'F',10,'PLATEA');

-- Sala Studio: 3 file (A-C) x 10 posti  →  ID 61–90
INSERT INTO posto (sala_id, fila, numero, settore) VALUES
    (2,'A',1,'PLATEA'),(2,'A',2,'PLATEA'),(2,'A',3,'PLATEA'),(2,'A',4,'PLATEA'),(2,'A',5,'PLATEA'),
    (2,'A',6,'PLATEA'),(2,'A',7,'PLATEA'),(2,'A',8,'PLATEA'),(2,'A',9,'PLATEA'),(2,'A',10,'PLATEA'),
    (2,'B',1,'PLATEA'),(2,'B',2,'PLATEA'),(2,'B',3,'PLATEA'),(2,'B',4,'PLATEA'),(2,'B',5,'PLATEA'),
    (2,'B',6,'PLATEA'),(2,'B',7,'PLATEA'),(2,'B',8,'PLATEA'),(2,'B',9,'PLATEA'),(2,'B',10,'PLATEA'),
    (2,'C',1,'PLATEA'),(2,'C',2,'PLATEA'),(2,'C',3,'PLATEA'),(2,'C',4,'PLATEA'),(2,'C',5,'PLATEA'),
    (2,'C',6,'PLATEA'),(2,'C',7,'PLATEA'),(2,'C',8,'PLATEA'),(2,'C',9,'PLATEA'),(2,'C',10,'PLATEA');

-- Sala Astra: 5 file (A-E) x 9 posti  →  ID 91–135
INSERT INTO posto (sala_id, fila, numero, settore) VALUES
    (3,'A',1,'PLATEA'),(3,'A',2,'PLATEA'),(3,'A',3,'PLATEA'),(3,'A',4,'PLATEA'),(3,'A',5,'PLATEA'),
    (3,'A',6,'PLATEA'),(3,'A',7,'PLATEA'),(3,'A',8,'PLATEA'),(3,'A',9,'PLATEA'),
    (3,'B',1,'PLATEA'),(3,'B',2,'PLATEA'),(3,'B',3,'PLATEA'),(3,'B',4,'PLATEA'),(3,'B',5,'PLATEA'),
    (3,'B',6,'PLATEA'),(3,'B',7,'PLATEA'),(3,'B',8,'PLATEA'),(3,'B',9,'PLATEA'),
    (3,'C',1,'PLATEA'),(3,'C',2,'PLATEA'),(3,'C',3,'PLATEA'),(3,'C',4,'PLATEA'),(3,'C',5,'PLATEA'),
    (3,'C',6,'PLATEA'),(3,'C',7,'PLATEA'),(3,'C',8,'PLATEA'),(3,'C',9,'PLATEA'),
    (3,'D',1,'PLATEA'),(3,'D',2,'PLATEA'),(3,'D',3,'PLATEA'),(3,'D',4,'PLATEA'),(3,'D',5,'PLATEA'),
    (3,'D',6,'PLATEA'),(3,'D',7,'PLATEA'),(3,'D',8,'PLATEA'),(3,'D',9,'PLATEA'),
    (3,'E',1,'PLATEA'),(3,'E',2,'PLATEA'),(3,'E',3,'PLATEA'),(3,'E',4,'PLATEA'),(3,'E',5,'PLATEA'),
    (3,'E',6,'PLATEA'),(3,'E',7,'PLATEA'),(3,'E',8,'PLATEA'),(3,'E',9,'PLATEA');

-- ----- SPETTACOLI -----------------------------------------------------
-- TEATRO  (ID 1-2)
INSERT INTO spettacolo (titolo, categoria, regista, cast_principale, descrizione, trama, genere, durata_min) VALUES
    ('Aspettando Godot', 'TEATRO', 'Theodoros Terzopoulos',
     CONCAT('Vladimiro: Pippo Delbono', CHAR(10), 'Estragone: Lino Musella', CHAR(10), 'Pozzo: Roberto Latini', CHAR(10), 'Lucky: Giovanni Anzaldo'),
     'Il capolavoro di Beckett in una nuova versione: due uomini, un albero, l''attesa.',
     'Due vagabondi, Vladimiro ed Estragone, attendono in una pianura desolata l''arrivo di Godot. Mentre aspettano si interrogano sul senso del tempo, della parola, della libertà. Compaiono Pozzo e Lucky, padrone e schiavo, e poi un ragazzo che annuncia: "Godot oggi non viene, forse domani." Il dramma dell''attesa diventa specchio della condizione umana, sospesa tra noia, speranza e disperazione.',
     'Dramma', 110);

INSERT INTO spettacolo (titolo, categoria, regista, cast_principale, descrizione, trama, genere, durata_min) VALUES
    ('Romeo e Giulietta', 'TEATRO', 'Antonio Latella',
     CONCAT('Romeo: Andrea Germani', CHAR(10), 'Giulietta: Ludovica Apollonj Ghetti', CHAR(10), 'Mercuzio: Pierluigi Corallo', CHAR(10), 'Balia: Barbara Altissimo', CHAR(10), 'Prod. Stabile di Napoli / ERT'),
     'La tragedia di Shakespeare nella visione di Latella: due famiglie, una città, un amore che brucia più della vita.',
     'A Verona, da tempo immemorabile, i Montecchi e i Capuleti si odiano. Romeo Montecchi incontra Giulietta Capuleti a un ballo in maschera e se ne innamora all''istante. Si sposano in segreto, ma il destino — nella forma di una rissa, un esilio e un equivoco fatale — li travolge. Shakespeare scrisse questa storia nel 1595 e da allora non ha smesso di essere il racconto definitivo sull''amore che brucia più della vita. La lettura di Latella sposta il peso dalla fatalità alla responsabilità: i due ragazzi muoiono di odio altrui, non di stella avversa.',
     'Tragedia', 120);

-- DANZA  (ID 3-5)
INSERT INTO spettacolo (titolo, categoria, regista, cast_principale, descrizione, trama, genere, durata_min) VALUES
    ('La Bayadère', 'DANZA', 'Coreografia di Marius Petipa (ripresa di Natalia Makarova)',
     CONCAT('Corps de ballet e solisti del Teatro dell''Opera di Roma', CHAR(10), 'Nikiya: Eleonora Abbagnato', CHAR(10), 'Solor: Friedemann Vogel', CHAR(10), 'Gamzatti: Giulia Lunanova'),
     'Il grande balletto romantico del Rajasthan: la devadasi Nikiya, il guerriero Solor e il celebre regno delle Ombre.',
     'Nikiya, devadasi del tempio, ama il guerriero Solor. Questi però promette di sposare Gamzatti, figlia del Gran Brahmino. Nikiya muore per il morso di un serpente nascosto tra i fiori. Solor, oppresso dal dolore e dall''oppio, vede in sogno il regno delle Ombre: le anime delle bayadère in bianche tuniche scendono uno a uno lungo una rampa in diagonale in quello che è considerato uno dei più lunghi e ipnotici entré del balletto classico. Petipa compose La Bayadère nel 1877 a San Pietroburgo; la terza scena, il regno delle Ombre, è sopravvissuta a tutto.',
     'Balletto classico', 130);

INSERT INTO spettacolo (titolo, categoria, regista, cast_principale, descrizione, trama, genere, durata_min) VALUES
    ('La Carmen', 'DANZA', 'Coreografia di Roland Petit (ripresa di Luigi Bonino)',
     CONCAT('Carmen: Eleonora Abbagnato', CHAR(10), 'José: Roberto Bolle', CHAR(10), 'Escamillo: Massimo Murru', CHAR(10), 'Compagnia residente dell''Eliseo'),
     'Carmen seduce, brucia, sfugge — Roland Petit traduce in danza la tragedia di Bizet e Mérimée.',
     'Carmen è una sigaraia di Siviglia che vive fuori da ogni regola. Incontra il caporale José, lo trascina nel contrabbando, lo seduce e poi lo abbandona per il torero Escamillo. José, travolto dalla gelosia, la uccide all''uscita dall''arena. Roland Petit firma nel 1949 una Carmen di soli quarantacinque minuti — scabra, sensuale, irriducibile. Ogni gesto porta la logica del desiderio e della libertà assoluta, che non ammette sopravvivenza.',
     'Balletto contemporaneo', 60);

INSERT INTO spettacolo (titolo, categoria, regista, cast_principale, descrizione, trama, genere, durata_min) VALUES
    ('Don Chisciotte', 'DANZA', 'Coreografia di Marius Petipa e Alexander Gorsky (ripresa di José Carlos Martínez)',
     CONCAT('Kitri: Antonella Albano', CHAR(10), 'Basilio: Claudio Coviello', CHAR(10), 'Don Chisciotte: Alessandro Grillo', CHAR(10), 'Sancho Panza: Massimo Garon', CHAR(10), 'Compagnia del Teatro alla Scala'),
     'Il balletto più solare del repertorio classico: Kitri e Basilio si amano, Don Chisciotte li aiuta per caso.',
     'In una vivace piazza spagnola, Kitri, la figlia dell''oste, ama il barbiere Basilio. Il padre la vuole però sposare al ricco e goffo Gamache. In città arriva Don Chisciotte, convinto che Kitri sia Dulcinea, la sua amata immaginaria: il suo intervento scompiglia i piani e, tra fughe, sogni e un matrimonio finto che diventa vero, i due giovani riescono a unirsi. Petipa crea il balletto a Mosca nel 1869, Gorsky lo riprende nel 1900 portandolo alla sua forma definitiva. Celebre per le variazioni solistiche di Kitri e per il finale di bolero andaluso, Don Chisciotte è la festa pura della tecnica classica.',
     'Balletto classico', 140);

-- CINEMA  (ID 6-10)
INSERT INTO spettacolo (titolo, categoria, regista, cast_principale, descrizione, trama, genere, durata_min) VALUES
    ('Parthenope', 'CINEMA', 'Paolo Sorrentino',
     'Celeste Dalla Porta, Stefania Sandrelli, Gary Oldman, Isabella Ferrari, Luisa Ranieri',
     'Sorrentino ritorna a Napoli: Parthenope nasce dalle acque del Golfo nel 1950 e attraversa mezzo secolo insieme alla sua città.',
     'Parthenope nasce nel 1950 dalle acque del Golfo di Napoli in una famiglia borghese. Cresce in una città che cambia — il Dopoguerra, il boom, gli anni di piombo, il terremoto dell''80 — mentre lei attraversa amori, disillusioni, una laurea in antropologia, un incontro con un vescovo santo e uno con uno scrittore americano in declino. Sorrentino filma Napoli con ammirazione e distanza, come si guarda ciò che si ama troppo per capirlo. Il film più solare e malinconico del regista napoletano.',
     'Drammatico', 136);

INSERT INTO spettacolo (titolo, categoria, regista, cast_principale, descrizione, trama, genere, durata_min) VALUES
    ('È stata la mano di Dio', 'CINEMA', 'Paolo Sorrentino',
     'Filippo Scotti, Toni Servillo, Teresa Saponangelo, Luisa Ranieri, Renato Carpentieri',
     'Napoli, anni ''80. Il giovane Fabietto cresce tra famiglia, Maradona e una perdita che lo cambia per sempre. Oscar al miglior film internazionale 2022.',
     'Fabietto Schisa ha sedici anni e vive in una famiglia allargata, caotica e calorosa nel Napoli dei primi anni ''80. L''arrivo di Diego Armando Maradona in città è un evento cosmico. Poi accade qualcosa di irreparabile. Fabietto sopravvive per caso — «è stata la mano di Dio», dirà — e in quel caso trova la sua vocazione al cinema. Sorrentino torna all''autobiografia con la sua storia più vera: la fine dell''infanzia, il lutto impossibile, il desiderio di fare film come unica risposta al dolore.',
     'Drammatico', 130);

INSERT INTO spettacolo (titolo, categoria, regista, cast_principale, descrizione, trama, genere, durata_min) VALUES
    ('Oppenheimer', 'CINEMA', 'Christopher Nolan',
     'Cillian Murphy, Emily Blunt, Matt Damon, Robert Downey Jr., Florence Pugh, Josh Hartnett',
     'La storia di J. Robert Oppenheimer, il fisico che guidò il Progetto Manhattan. Sette premi Oscar, incluso miglior film 2024.',
     'J. Robert Oppenheimer è un fisico teorico con una carriera brillante e un passato comunista che lo renderà vulnerabile. Il generale Groves lo sceglie per guidare il Progetto Manhattan, lo sforzo segreto degli Alleati per costruire la bomba atomica prima della Germania. Nel luglio del 1945, nel deserto del New Mexico, Oppenheimer assiste alla prima esplosione atomica della storia e capisce di aver cambiato il mondo per sempre. Nolan alterna passato e presente in un processo drammatico che interroga la responsabilità dello scienziato davanti alla Storia.',
     'Storico / Thriller', 181);

INSERT INTO spettacolo (titolo, categoria, regista, cast_principale, descrizione, trama, genere, durata_min) VALUES
    ('Pulp Fiction', 'CINEMA', 'Quentin Tarantino',
     'John Travolta, Uma Thurman, Samuel L. Jackson, Bruce Willis, Harvey Keitel, Tim Roth',
     'Los Angeles. Tre storie intrecciate, una valigetta misteriosa, dialoghi immortali. Il film che ha cambiato il cinema degli anni ''90.',
     'Vincent Vega e Jules Winnfield sono sicari alle dipendenze del boss Marsellus Wallace. Vincent trascorre una serata con Mia Wallace, moglie di Marsellus. Butch Coolidge è un pugile che deve perdere un incontro — e non lo fa. Un avvocato ritira una valigetta dorata per conto di qualcuno che non si vede mai. Tarantino mescola le storie in un gioco di flashback e flashforward che riscrive le regole del tempo cinematografico. Dialoghi come musica, violenza come balletto, ironia come morale. Palma d''Oro a Cannes 1994.',
     'Crime / Noir', 154);

INSERT INTO spettacolo (titolo, categoria, regista, cast_principale, descrizione, trama, genere, durata_min) VALUES
    ('Kill Bill', 'CINEMA', 'Quentin Tarantino',
     'Uma Thurman, Lucy Liu, Vivica A. Fox, Daryl Hannah, David Carradine, Michael Madsen',
     'La Sposa si sveglia da quattro anni di coma. La lista è breve: cinque nomi. Un''epopea di vendetta tra kung fu, anime e spaghetti western.',
     'La Sposa era un''assassina del Gruppo Internazionale di Vigliaccheria Assassinistica. Il giorno del suo matrimonio Bill — il suo capo e amante — ordina il massacro degli invitati. Lei sopravvive dopo quattro anni di coma. Adesso ha una lista. Tarantino costruisce un''epopea pulp in due volumi, citando il cinema di genere asiatico, i western di Leone, i fumetti giapponesi e i film di kung fu degli anni ''70. Uma Thurman in tuta gialla è il centro di gravità di tutto.',
     'Action / Thriller', 247);

-- EVENTI / SALOTTI  (ID 11-13)
INSERT INTO spettacolo (titolo, categoria, regista, cast_principale, descrizione, trama, genere, durata_min) VALUES
    ('Salotto filosofico: il corpo che pensa', 'EVENTO', 'a cura di Sarah Boccia',
     'Con la partecipazione di danzatori della compagnia residente e studenti di filosofia.',
     'Conversazione aperta tra filosofia e danza, nella Sala Studio.',
     'Una serata di parole e movimento. Si parte dalla domanda: il corpo pensa? Tra Merleau-Ponty e la pratica della danza contemporanea, attraverso letture, dimostrazioni in studio e dialogo con il pubblico. Format informale, ingresso libero su prenotazione.',
     'Talk', 90);

INSERT INTO spettacolo (titolo, categoria, regista, cast_principale, descrizione, trama, genere, durata_min) VALUES
    ('Cinema e silenzio: Bergman, Tarkovskij e la domanda di senso', 'EVENTO', 'a cura di Sarah Boccia',
     'Relatori invitati dell''Università di Salerno e del Centro Studi Tarkovskij.',
     'Conferenza-proiezione, ingresso libero.',
     'Una serata dedicata al cinema come esperienza filosofica. Frammenti dal Settimo sigillo e dal Sacrificio, intervallati da brevi interventi su cosa significa "cercare senso" attraverso un''immagine. Pensata come ponte tra il pubblico del cinema e quello della filosofia.',
     'Talk', 75);

INSERT INTO spettacolo (titolo, categoria, regista, cast_principale, descrizione, trama, genere, durata_min) VALUES
    ('Omaggio a Hannah Arendt: la banalità del male', 'EVENTO', 'a cura di Marco Esposito',
     CONCAT('Mediatore: prof.ssa Maria Donnarumma, filosofia teoretica — Università Federico II di Napoli', CHAR(10), 'Con interventi di docenti del Dipartimento di Studi Umanistici della Federico II.'),
     'Una serata attorno alla formula che ha riaperto la riflessione sul male nel Novecento.',
     CONCAT('«La triste verità è che la maggior parte del male è fatto da persone che non hanno mai deciso di essere buone o cattive.» — Hannah Arendt, Alcune questioni di filosofia morale (1965–66).', CHAR(10), CHAR(10), 'Una serata costruita attorno alla riflessione che Arendt sviluppa a partire dall''osservazione di Adolf Eichmann nella sua gabbia di vetro a Gerusalemme: l''idea che il male possa abitare i gesti più ordinari, e nascere semplicemente dall''aver smesso di pensare.', CHAR(10), CHAR(10), 'LIBRI in dialogo:', CHAR(10), '· La banalità del male. Eichmann a Gerusalemme (1963)', CHAR(10), '· Alcune questioni di filosofia morale, in Responsabilità e giudizio (Einaudi)', CHAR(10), '· Le origini del totalitarismo (1951)', CHAR(10), CHAR(10), 'FILM proiettati per frammenti:', CHAR(10), '· Hannah Arendt, di Margarethe von Trotta (2012)', CHAR(10), '· Un Spécialiste, portrait d''un criminel moderne, di Eyal Sivan (1999)', CHAR(10), '· Conspiracy, di Frank Pierson (2001)', CHAR(10), CHAR(10), 'Seconda parte aperta alle domande del pubblico. Ingresso libero su prenotazione.'),
     'Talk', 120);

-- ----- REPLICHE -------------------------------------------------------
-- ID spettacolo: 1=Godot 2=Romeo 3=Bayadère 4=Carmen 5=DonChisciotte
--                6=Parthenope 7=ManoDiDio 8=Oppenheimer 9=PulpFiction 10=KillBill
--                11=SalottoCorpo 12=CinemaESilenzio 13=Arendt
-- Sala 1 = Grande (teatro/cinema), Sala 2 = Studio (danza/salotti)
INSERT INTO replica (spettacolo_id, sala_id, data_ora, prezzo_base) VALUES
-- Luglio 9-11: apertura stagione — Aspettando Godot
    (1,  1, TIMESTAMP '2026-07-09 20:30:00', 12.00),  -- replica 1
    (1,  1, TIMESTAMP '2026-07-10 20:30:00', 12.00),  -- replica 2
    (1,  1, TIMESTAMP '2026-07-11 20:30:00', 12.00),  -- replica 3
-- Luglio 13: salotto filosofico
    (11, 2, TIMESTAMP '2026-07-13 19:00:00',  0.00),  -- replica 4
-- Luglio 14-15: Parthenope — Sala Astra (due turni il 14, uno il 15)
    (6,  3, TIMESTAMP '2026-07-14 17:00:00',  5.00),  -- replica 5
    (6,  3, TIMESTAMP '2026-07-14 20:00:00',  5.00),  -- replica 6
    (6,  3, TIMESTAMP '2026-07-15 20:00:00',  5.00),  -- replica 7
-- Luglio 16-17: La Bayadère
    (3,  2, TIMESTAMP '2026-07-16 20:30:00', 12.00),  -- replica 8
    (3,  2, TIMESTAMP '2026-07-17 20:30:00', 12.00),  -- replica 9
-- Luglio 18-19: La Carmen
    (4,  2, TIMESTAMP '2026-07-18 20:30:00', 12.00),  -- replica 10
    (4,  2, TIMESTAMP '2026-07-19 18:00:00', 12.00),  -- replica 11
-- Luglio 20: Cinema e silenzio (conferenza)
    (12, 1, TIMESTAMP '2026-07-20 19:30:00',  0.00),  -- replica 12
-- Luglio 21-22: È stata la mano di Dio — Sala Astra (due turni il 21, uno il 22)
    (7,  3, TIMESTAMP '2026-07-21 17:00:00',  5.00),  -- replica 13
    (7,  3, TIMESTAMP '2026-07-21 20:00:00',  5.00),  -- replica 14
    (7,  3, TIMESTAMP '2026-07-22 20:00:00',  5.00),  -- replica 15
-- Luglio 23-25: Romeo e Giulietta
    (2,  1, TIMESTAMP '2026-07-23 20:30:00', 12.00),  -- replica 16
    (2,  1, TIMESTAMP '2026-07-24 20:30:00', 12.00),  -- replica 17
    (2,  1, TIMESTAMP '2026-07-25 18:00:00', 12.00),  -- replica 18
-- Luglio 27: Omaggio a Hannah Arendt
    (13, 1, TIMESTAMP '2026-07-27 19:30:00',  0.00),  -- replica 19
-- Luglio 28: Oppenheimer — Sala Astra (due turni)
    (8,  3, TIMESTAMP '2026-07-28 17:00:00',  5.00),  -- replica 20
    (8,  3, TIMESTAMP '2026-07-28 20:30:00',  5.00),  -- replica 21
-- Luglio 30-31: Don Chisciotte
    (5,  2, TIMESTAMP '2026-07-30 20:30:00', 12.00),  -- replica 22
    (5,  2, TIMESTAMP '2026-07-31 20:30:00', 12.00),  -- replica 23
-- Agosto 1: È stata la mano di Dio — Sala Astra (replica supplementare)
    (7,  3, TIMESTAMP '2026-08-01 20:00:00',  5.00),  -- replica 24
-- Agosto 2: Kill Bill — Sala Astra (due turni)
    (10, 3, TIMESTAMP '2026-08-02 17:00:00',  5.00),  -- replica 25
    (10, 3, TIMESTAMP '2026-08-02 20:30:00',  5.00),  -- replica 26
-- Agosto 4: Pulp Fiction — Sala Astra (due turni)
    (9,  3, TIMESTAMP '2026-08-04 17:00:00',  5.00),  -- replica 27
    (9,  3, TIMESTAMP '2026-08-04 20:30:00',  5.00),  -- replica 28
-- Agosto 6: Aspettando Godot (replica bis)
    (1,  1, TIMESTAMP '2026-08-06 20:30:00', 12.00),  -- replica 29
-- Agosto 8: Kill Bill — Sala Astra (replica bis)
    (10, 3, TIMESTAMP '2026-08-08 20:30:00',  5.00),  -- replica 30
-- Agosto 9: Pulp Fiction — Sala Astra (replica bis)
    (9,  3, TIMESTAMP '2026-08-09 18:00:00',  5.00);  -- replica 31

-- Prenotazioni pre-esistenti
-- replica 1 = Godot 9/7 (sala 1): posti 15, 16, 25, 26 ✓
-- replica 4 = Salotto 13/7 (sala 2): posto 65 (fila A, num 5, sala 2) ✓
INSERT INTO prenotazione (utente_id, replica_id, posto_id) VALUES
    (1, 1, 15), (1, 1, 16),
    (2, 1, 25), (2, 1, 26),
    (1, 4, 65);

-- ----- LOCANDINE ------------------------------------------------------
UPDATE spettacolo SET locandina_url = 'assets/godot.jpg'            WHERE titolo = 'Aspettando Godot';
UPDATE spettacolo SET locandina_url = 'assets/romeoegiulietta.jpg'  WHERE titolo = 'Romeo e Giulietta';
UPDATE spettacolo SET locandina_url = 'assets/bayadere.jpg'         WHERE titolo = 'La Bayadère';
UPDATE spettacolo SET locandina_url = 'assets/carmen.jpg'           WHERE titolo = 'La Carmen';
UPDATE spettacolo SET locandina_url = 'assets/donquixote.jpg'       WHERE titolo = 'Don Chisciotte';
UPDATE spettacolo SET locandina_url = 'assets/parthenope.jpg'       WHERE titolo = 'Parthenope';
UPDATE spettacolo SET locandina_url = 'assets/mano-di-dio.jpg'      WHERE titolo = 'È stata la mano di Dio';
UPDATE spettacolo SET locandina_url = 'assets/oppenheimer.jpg'      WHERE titolo = 'Oppenheimer';
UPDATE spettacolo SET locandina_url = 'assets/pulp-fiction.jpg'     WHERE titolo = 'Pulp Fiction';
UPDATE spettacolo SET locandina_url = 'assets/kill-bill.jpg'        WHERE titolo = 'Kill Bill';
UPDATE spettacolo SET locandina_url = 'assets/corpo-che-pensa.jpg'  WHERE titolo = 'Salotto filosofico: il corpo che pensa';
UPDATE spettacolo SET locandina_url = 'assets/domanda-di-senso.jpg' WHERE titolo LIKE 'Cinema e silenzio%';
UPDATE spettacolo SET locandina_url = 'assets/arendt.jpg'           WHERE titolo LIKE 'Omaggio a Hannah Arendt%';

-- ----- ABBONAMENTI ---------------------------------------------------
INSERT INTO abbonamento (utente_id, tipo, data_inizio, data_fine, prezzo) VALUES
    (1, 'STAGIONALE', DATE '2026-01-01', DATE '2027-06-30', 90.00);
