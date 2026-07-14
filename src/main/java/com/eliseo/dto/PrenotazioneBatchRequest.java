package com.eliseo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PrenotazioneBatchRequest(
        @NotNull Long utenteId,
        @NotNull Long replicaId,
        @NotEmpty @Size(max = 10, message = "Massimo 10 posti per prenotazione")
        List<@NotNull Long> postoIds
) {}
