package com.eliseo.dto;

import com.eliseo.model.Spettacolo;

public record SpettacoloDto(
        Long id,
        String titolo,
        String categoria,
        String regista,
        String castPrincipale,
        String descrizione,
        String trama,
        String genere,
        Integer durataMin,
        String locandinaUrl
) {
    public static SpettacoloDto from(Spettacolo s) {
        return new SpettacoloDto(
                s.getId(),
                s.getTitolo(),
                s.getCategoria() != null ? s.getCategoria().name() : null,
                s.getRegista(),
                s.getCastPrincipale(),
                s.getDescrizione(),
                s.getTrama(),
                s.getGenere(),
                s.getDurataMin(),
                s.getLocandinaUrl()
        );
    }
}
