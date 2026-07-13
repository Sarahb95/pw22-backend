package com.teatro.dto;

import com.teatro.model.Spettacolo;
import jakarta.validation.constraints.NotBlank;

public record SpettacoloRequest(
        @NotBlank String titolo,
        Spettacolo.Categoria categoria,
        String regista,
        String castPrincipale,
        String descrizione,
        String trama,
        String genere,
        Integer durataMin,
        String locandinaUrl
) {}
