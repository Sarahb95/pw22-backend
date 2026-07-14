package com.eliseo.service;

import com.eliseo.dto.PrenotazioneBatchRequest;
import com.eliseo.dto.PrenotazioneDto;
import com.eliseo.model.*;
import com.eliseo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PrenotazioneService {

    private static final DateTimeFormatter DATE_IT =
            DateTimeFormatter.ofPattern("d MMMM yyyy, HH:mm", Locale.ITALIAN);
    private static final String BOOKING_PREFIX = "PNZ";

    private final PrenotazioneRepository prenotazioneRepo;
    private final UtenteRepository utenteRepo;
    private final ReplicaRepository replicaRepo;
    private final PostoRepository postoRepo;
    private final EmailService emailService;
    private final AbbonamentoService abbonamentoService;

    public PrenotazioneService(PrenotazioneRepository prenotazioneRepo,
                               UtenteRepository utenteRepo,
                               ReplicaRepository replicaRepo,
                               PostoRepository postoRepo,
                               EmailService emailService,
                               AbbonamentoService abbonamentoService) {
        this.prenotazioneRepo = prenotazioneRepo;
        this.utenteRepo = utenteRepo;
        this.replicaRepo = replicaRepo;
        this.postoRepo = postoRepo;
        this.emailService = emailService;
        this.abbonamentoService = abbonamentoService;
    }

    @Transactional
    public List<PrenotazioneDto> prenotaBatch(PrenotazioneBatchRequest req) {
        var copertura = abbonamentoService.verificaCopertura(req.utenteId());
        if (copertura.coperta()) {
            int max = copertura.maxPostiPerEntrata();
            if (req.postoIds().size() > max) {
                throw new IllegalArgumentException(
                    "Con il tuo abbonamento puoi prenotare al massimo "
                    + max + (max == 1 ? " posto" : " posti") + " per evento.");
            }
        }

        List<Prenotazione> create = new ArrayList<>();
        for (Long postoId : req.postoIds()) {
            create.add(creaSingola(req.utenteId(), req.replicaId(), postoId));
        }
        inviaEmailPrenotazione(create);
        return create.stream().map(PrenotazioneDto::from).toList();
    }

    private Prenotazione creaSingola(Long utenteId, Long replicaId, Long postoId) {
        if (prenotazioneRepo.existsByReplicaIdAndPostoIdAndStato(
                replicaId, postoId, Prenotazione.Stato.ATTIVA)) {
            throw new IllegalStateException("Posto già prenotato per questa replica");
        }

        Utente utente = utenteRepo.findById(utenteId)
                .orElseThrow(() -> new NoSuchElementException("Utente non trovato"));
        Replica replica = replicaRepo.findById(replicaId)
                .orElseThrow(() -> new NoSuchElementException("Replica non trovata"));
        Posto posto = postoRepo.findById(postoId)
                .orElseThrow(() -> new NoSuchElementException("Posto non trovato"));

        if (!posto.getSala().getId().equals(replica.getSala().getId())) {
            throw new IllegalArgumentException("Il posto non appartiene alla sala della replica");
        }

        return prenotazioneRepo.save(Prenotazione.builder()
                .utente(utente)
                .replica(replica)
                .posto(posto)
                .stato(Prenotazione.Stato.ATTIVA)
                .build());
    }

    private void inviaEmailPrenotazione(List<Prenotazione> prenotazioni) {
        if (prenotazioni.isEmpty()) return;
        Prenotazione prima = prenotazioni.get(0);
        Utente utente = prima.getUtente();
        Replica replica = prima.getReplica();

        String posti = prenotazioni.stream()
                .map(p -> p.getPosto().getFila() + p.getPosto().getNumero())
                .collect(Collectors.joining(", "));

        BigDecimal totale = replica.getPrezzoBase()
                .multiply(BigDecimal.valueOf(prenotazioni.size()));
        String prezzo = "€" + String.format("%.2f", totale).replace(".", ",");

        String bookingId = String.format(BOOKING_PREFIX + "-%06d", prima.getId());

        emailService.inviaConfermaPrenotazione(utente.getEmail(), Map.of(
                "nome",       utente.getNome(),
                "spettacolo", replica.getSpettacolo().getTitolo(),
                "dataOra",    replica.getDataOra().format(DATE_IT),
                "sala",       replica.getSala().getNome(),
                "posti",      posti,
                "prezzo",     prezzo,
                "bookingId",  bookingId
        ));
    }

    @Transactional(readOnly = true)
    public List<PrenotazioneDto> listByUtente(Long utenteId) {
        return prenotazioneRepo.findByUtenteId(utenteId).stream()
                .sorted(Comparator.comparing((Prenotazione p) -> p.getReplica().getDataOra()).reversed())
                .map(PrenotazioneDto::from)
                .toList();
    }
}
