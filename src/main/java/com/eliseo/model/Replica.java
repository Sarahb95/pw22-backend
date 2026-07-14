package com.eliseo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "replica")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Replica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "spettacolo_id")
    private Spettacolo spettacolo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sala_id")
    private Sala sala;

    @Column(name = "data_ora", nullable = false)
    private LocalDateTime dataOra;

    @Column(name = "prezzo_base", nullable = false, precision = 8, scale = 2)
    private BigDecimal prezzoBase;
}
