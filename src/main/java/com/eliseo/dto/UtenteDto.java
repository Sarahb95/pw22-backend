package com.eliseo.dto;

import com.eliseo.model.Utente;

import java.time.LocalDate;

public record UtenteDto(
        Long id,
        String email,
        String nome,
        String cognome,
        LocalDate dataNascita
) {
    public static UtenteDto from(Utente u) {
        return new UtenteDto(
                u.getId(),
                u.getEmail(),
                u.getNome(),
                u.getCognome(),
                u.getDataNascita()
        );
    }
}
