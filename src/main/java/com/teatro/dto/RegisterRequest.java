package com.teatro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RegisterRequest(
        @Email @NotBlank String email,
        @NotBlank @Size(min = 6, message = "La password deve avere almeno 6 caratteri") String password,
        @NotBlank String nome,
        @NotBlank String cognome,
        @NotNull @Past LocalDate dataNascita
) {}
