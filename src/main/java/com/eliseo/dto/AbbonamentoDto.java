package com.eliseo.dto;

import com.eliseo.model.Abbonamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AbbonamentoDto(
        Long id,
        String tipo,
        LocalDate dataInizio,
        LocalDate dataFine,
        BigDecimal prezzo
) {
    public static AbbonamentoDto from(Abbonamento a) {
        return new AbbonamentoDto(
                a.getId(),
                a.getTipo().name(),
                a.getDataInizio(),
                a.getDataFine(),
                a.getPrezzo()
        );
    }
}
