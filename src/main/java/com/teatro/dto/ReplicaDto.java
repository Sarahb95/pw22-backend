package com.teatro.dto;

import com.teatro.model.Replica;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ReplicaDto(
        Long id,
        Long spettacoloId,
        String spettacoloTitolo,
        Long salaId,
        String salaNome,
        LocalDateTime dataOra,
        BigDecimal prezzoBase
) {
    public static ReplicaDto from(Replica r) {
        return new ReplicaDto(
                r.getId(),
                r.getSpettacolo().getId(),
                r.getSpettacolo().getTitolo(),
                r.getSala().getId(),
                r.getSala().getNome(),
                r.getDataOra(),
                r.getPrezzoBase()
        );
    }
}
