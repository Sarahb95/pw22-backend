package com.eliseo.repository;

import com.eliseo.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByUtenteId(Long utenteId);
    boolean existsByReplicaIdAndPostoIdAndStato(Long replicaId, Long postoId, Prenotazione.Stato stato);
    List<Prenotazione> findByReplicaIdAndStato(Long replicaId, Prenotazione.Stato stato);

    @Query("SELECT COUNT(DISTINCT p.replica.id) FROM Prenotazione p " +
           "WHERE p.utente.id = :utenteId " +
           "AND p.stato = :stato " +
           "AND p.dataPrenotazione >= :from")
    int countDistinctReplicaByUtenteIdAndStato(
            @Param("utenteId") Long utenteId,
            @Param("stato") Prenotazione.Stato stato,
            @Param("from") LocalDateTime from);
}
