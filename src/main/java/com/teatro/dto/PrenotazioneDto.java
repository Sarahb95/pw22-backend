package com.teatro.dto;

import com.teatro.model.Prenotazione;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PrenotazioneDto(
        Long id,
        Long replicaId,
        Long spettacoloId,
        String spettacoloTitolo,
        String categoria,
        String salaNome,
        LocalDateTime dataOra,
        BigDecimal prezzo,
        String fila,
        Integer numero,
        LocalDateTime dataPrenotazione,
        String stato
) {
    public static PrenotazioneDto from(Prenotazione p) {
        var r = p.getReplica();
        var s = r.getSpettacolo();
        var po = p.getPosto();
        return new PrenotazioneDto(
                p.getId(),
                r.getId(),
                s.getId(),
                s.getTitolo(),
                s.getCategoria() != null ? s.getCategoria().name() : null,
                r.getSala().getNome(),
                r.getDataOra(),
                r.getPrezzoBase(),
                po.getFila(),
                po.getNumero(),
                p.getDataPrenotazione(),
                p.getStato().name()
        );
    }
}
