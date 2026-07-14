package com.eliseo.service;

import com.eliseo.dto.PostoStatoDto;
import com.eliseo.dto.ReplicaDto;
import com.eliseo.model.Prenotazione;
import com.eliseo.repository.PostoRepository;
import com.eliseo.repository.PrenotazioneRepository;
import com.eliseo.repository.ReplicaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReplicaService {

    private final ReplicaRepository replicaRepo;
    private final PostoRepository postoRepo;
    private final PrenotazioneRepository prenotazioneRepo;

    public ReplicaService(ReplicaRepository replicaRepo,
                          PostoRepository postoRepo,
                          PrenotazioneRepository prenotazioneRepo) {
        this.replicaRepo = replicaRepo;
        this.postoRepo = postoRepo;
        this.prenotazioneRepo = prenotazioneRepo;
    }

    @Transactional(readOnly = true)
    public List<ReplicaDto> list(Long spettacoloId) {
        var repliche = (spettacoloId != null)
                ? replicaRepo.findBySpettacoloId(spettacoloId)
                : replicaRepo.findByDataOraAfterOrderByDataOraAsc(LocalDateTime.now());
        return repliche.stream().map(ReplicaDto::from).toList();
    }

    @Transactional(readOnly = true)
    public ReplicaDto get(Long id) {
        return ReplicaDto.from(replicaRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Replica non trovata: " + id)));
    }

    @Transactional(readOnly = true)
    public List<PostoStatoDto> piantaPosti(Long replicaId) {
        var replica = replicaRepo.findById(replicaId)
                .orElseThrow(() -> new NoSuchElementException("Replica non trovata: " + replicaId));

        Set<Long> occupati = prenotazioneRepo
                .findByReplicaIdAndStato(replicaId, Prenotazione.Stato.ATTIVA)
                .stream()
                .map(p -> p.getPosto().getId())
                .collect(Collectors.toSet());

        return postoRepo.findBySalaId(replica.getSala().getId()).stream()
                .map(p -> new PostoStatoDto(
                        p.getId(),
                        p.getFila(),
                        p.getNumero(),
                        p.getSettore(),
                        occupati.contains(p.getId())))
                .toList();
    }
}
