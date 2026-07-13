package com.teatro.dto;

import com.teatro.model.Utente;

import java.time.LocalDate;

public record UtenteDto(
        Long id,
        String email,
        String nome,
        String cognome,
        String ruolo,
        LocalDate dataNascita
) {
    public static UtenteDto from(Utente u) {
        return new UtenteDto(
                u.getId(),
                u.getEmail(),
                u.getNome(),
                u.getCognome(),
                u.getRuolo() != null ? u.getRuolo().name() : null,
                u.getDataNascita()
        );
    }
}
