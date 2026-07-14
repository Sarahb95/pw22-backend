package com.eliseo.dto;

public record PostoStatoDto(
        Long id,
        String fila,
        Integer numero,
        String settore,
        boolean occupato
) {}
