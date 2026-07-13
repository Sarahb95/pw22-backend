package com.teatro.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "posto", uniqueConstraints = @UniqueConstraint(columnNames = {"sala_id", "fila", "numero"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Posto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sala_id")
    private Sala sala;

    @Column(nullable = false, length = 5)
    private String fila;

    @Column(nullable = false)
    private Integer numero;

    @Builder.Default
    @Column(nullable = false, length = 30)
    private String settore = "PLATEA";
}
