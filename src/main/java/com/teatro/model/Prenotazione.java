package com.teatro.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "prenotazione",
       uniqueConstraints = @UniqueConstraint(columnNames = {"replica_id", "posto_id"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Prenotazione {

    public enum Stato { ATTIVA, ANNULLATA }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "replica_id")
    private Replica replica;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "posto_id")
    private Posto posto;

    @Builder.Default
    @Column(name = "data_prenotazione", nullable = false, updatable = false)
    private LocalDateTime dataPrenotazione = LocalDateTime.now();

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Stato stato = Stato.ATTIVA;
}
