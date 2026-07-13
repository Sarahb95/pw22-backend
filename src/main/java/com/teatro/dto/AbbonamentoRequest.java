package com.teatro.dto;

import com.teatro.model.Abbonamento;
import jakarta.validation.constraints.NotNull;

public record AbbonamentoRequest(
        @NotNull Long utenteId,
        @NotNull Abbonamento.Tipo tipo
) {}
