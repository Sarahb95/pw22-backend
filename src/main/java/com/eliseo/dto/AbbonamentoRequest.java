package com.eliseo.dto;

import com.eliseo.model.Abbonamento;
import jakarta.validation.constraints.NotNull;

public record AbbonamentoRequest(
        @NotNull Long utenteId,
        @NotNull Abbonamento.Tipo tipo
) {}
