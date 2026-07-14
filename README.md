# pw22-backend

REST API del **Nuovo Cinema Teatro Eliseo di Poggiomarino** — elaborato finale PW 22, CdS Informatica per le Aziende Digitali (L-31).

## Stack

- Java 17 · Spring Boot 3.3.4 · Maven
- H2 in-memory (sviluppo) · MySQL 8 (produzione)
- Hibernate 6 · Spring Data JPA
- JavaMailSender (SMTP Gmail)

## Avvio locale

```bash
MAIL_USERNAME="cte.eliseo.demo@gmail.com" \
MAIL_PASSWORD="<app-password>" \
mvn spring-boot:run
```

Il server parte sulla porta `8080`. La console H2 è disponibile su `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:eliseo_db`).

## Struttura

```
src/main/java/com/eliseo/
├── controller/   # 5 controller REST + ApiExceptionHandler
├── service/      # logica di business e invio email
├── repository/   # 6 repository Spring Data JPA
├── model/        # 7 entità JPA
├── dto/          # 11 record DTO (output API)
└── config/       # WebConfig (CORS)
src/main/resources/
├── db/schema.sql # DDL — fonte di verità dello schema
└── db/data.sql   # dati di esempio
```

## Endpoint principali

| Metodo | Path | Descrizione |
|--------|------|-------------|
| GET | `/api/spettacoli` | Catalogo completo |
| GET | `/api/spettacoli/{id}` | Dettaglio spettacolo |
| GET | `/api/repliche/{id}/posti` | Disponibilità posti |
| POST | `/api/prenotazioni` | Nuova prenotazione |
| POST | `/api/abbonamenti` | Nuovo abbonamento |
| POST | `/api/auth/registrazione` | Registrazione utente |
| POST | `/api/auth/login` | Login |

## Variabili d'ambiente

| Variabile | Descrizione |
|-----------|-------------|
| `MAIL_USERNAME` | Indirizzo Gmail mittente |
| `MAIL_PASSWORD` | App password Gmail (16 caratteri) |
| `PORT` | Porta server (default: `8080`) |

## Build

```bash
mvn clean package -DskipTests
java -jar target/eliseo-backend-0.1.0.jar
```

## Test

```bash
mvn test
```

Il test `EliseoApplicationTests#contextLoads` verifica il corretto avvio del contesto Spring.
