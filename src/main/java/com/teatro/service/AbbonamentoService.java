package com.teatro.service;

import com.teatro.dto.AbbonamentoCopertura;
import com.teatro.dto.AbbonamentoDto;
import com.teatro.dto.AbbonamentoRequest;
import com.teatro.model.Abbonamento;
import com.teatro.model.Prenotazione;
import com.teatro.repository.AbbonamentoRepository;
import com.teatro.repository.PrenotazioneRepository;
import com.teatro.repository.UtenteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class AbbonamentoService {

    private static final int OPEN10_MAX_INGRESSI = 10;
    private static final String ABB_PREFIX = "ABB";

    private static final Map<Abbonamento.Tipo, BigDecimal> PREZZI = Map.of(
            Abbonamento.Tipo.STAGIONALE, new BigDecimal("90.00"),
            Abbonamento.Tipo.OPEN10,     new BigDecimal("45.00"),
            Abbonamento.Tipo.UNDER25,    new BigDecimal("30.00")
    );

    private static final Map<Abbonamento.Tipo, Integer> MAX_POSTI = Map.of(
            Abbonamento.Tipo.STAGIONALE, 2,
            Abbonamento.Tipo.OPEN10,     2,
            Abbonamento.Tipo.UNDER25,    1
    );

    private static final Map<Abbonamento.Tipo, String> LABEL = Map.of(
            Abbonamento.Tipo.STAGIONALE, "Stagionale",
            Abbonamento.Tipo.OPEN10,     "Open 10",
            Abbonamento.Tipo.UNDER25,    "Under 25"
    );

    private static final LocalDate FINE_STAGIONE = LocalDate.of(2027, 6, 30);
    private static final DateTimeFormatter DATE_IT = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ITALIAN);

    private final AbbonamentoRepository abbonamentoRepo;
    private final PrenotazioneRepository prenotazioneRepo;
    private final UtenteRepository utenteRepo;
    private final EmailService emailService;

    public AbbonamentoService(AbbonamentoRepository abbonamentoRepo,
                               PrenotazioneRepository prenotazioneRepo,
                               UtenteRepository utenteRepo,
                               EmailService emailService) {
        this.abbonamentoRepo = abbonamentoRepo;
        this.prenotazioneRepo = prenotazioneRepo;
        this.utenteRepo = utenteRepo;
        this.emailService = emailService;
    }

    @Transactional
    public AbbonamentoDto acquista(AbbonamentoRequest req) {
        var utente = utenteRepo.findById(req.utenteId())
                .orElseThrow(() -> new NoSuchElementException("Utente non trovato"));

        if (req.tipo() == Abbonamento.Tipo.UNDER25) {
            if (utente.getDataNascita() == null) {
                throw new IllegalArgumentException(
                        "Data di nascita non registrata: impossibile acquistare l'abbonamento Under 25.");
            }
            int eta = Period.between(utente.getDataNascita(), LocalDate.now()).getYears();
            if (eta >= 25) {
                throw new IllegalArgumentException(
                        "L'abbonamento Under 25 è riservato a chi ha meno di 25 anni (età rilevata: " + eta + ").");
            }
        }

        var abb = Abbonamento.builder()
                .utente(utente)
                .tipo(req.tipo())
                .dataInizio(LocalDate.now())
                .dataFine(FINE_STAGIONE)
                .prezzo(PREZZI.get(req.tipo()))
                .build();

        var saved = abbonamentoRepo.save(abb);

        emailService.inviaConfermaAbbonamento(utente.getEmail(), Map.of(
                "nome",     utente.getNome(),
                "tipo",     LABEL.get(req.tipo()),
                "dataFine", FINE_STAGIONE.format(DATE_IT),
                "prezzo",   "€" + PREZZI.get(req.tipo()).toPlainString().replace(".", ","),
                "abbId",    String.format(ABB_PREFIX + "-%06d", saved.getId())
        ));

        return AbbonamentoDto.from(saved);
    }

    @Transactional(readOnly = true)
    public List<AbbonamentoDto> listByUtente(Long utenteId) {
        return abbonamentoRepo.findByUtenteId(utenteId).stream()
                .map(AbbonamentoDto::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public AbbonamentoCopertura verificaCopertura(Long utenteId) {
        List<Abbonamento> attivi = abbonamentoRepo
                .findByUtenteIdAndDataFineGreaterThanEqual(utenteId, LocalDate.now());

        if (attivi.isEmpty()) return AbbonamentoCopertura.notCovered();

        // Preferisce illimitati (STAGIONALE / UNDER25) rispetto a OPEN10
        for (Abbonamento abb : attivi) {
            if (abb.getTipo() == Abbonamento.Tipo.STAGIONALE
                    || abb.getTipo() == Abbonamento.Tipo.UNDER25) {
                return new AbbonamentoCopertura(
                        true, abb.getTipo().name(), -1, MAX_POSTI.get(abb.getTipo()));
            }
        }

        for (Abbonamento abb : attivi) {
            if (abb.getTipo() == Abbonamento.Tipo.OPEN10) {
                int used = prenotazioneRepo
                        .countDistinctReplicaByUtenteIdAndStato(
                                utenteId, Prenotazione.Stato.ATTIVA,
                                abb.getDataInizio().atStartOfDay());
                int remaining = OPEN10_MAX_INGRESSI - used;
                if (remaining > 0) {
                    return new AbbonamentoCopertura(
                            true, "OPEN10", remaining, MAX_POSTI.get(Abbonamento.Tipo.OPEN10));
                }
            }
        }

        return AbbonamentoCopertura.notCovered();
    }
}
