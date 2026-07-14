package com.eliseo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "utente")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(nullable = false, length = 80)
    private String nome;

    @Column(nullable = false, length = 80)
    private String cognome;

    @Column(name = "data_nascita")
    private LocalDate dataNascita;

    @Builder.Default
    @Column(name = "data_iscrizione", nullable = false, updatable = false)
    private LocalDateTime dataIscrizione = LocalDateTime.now();
}
