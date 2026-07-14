package com.eliseo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sala")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private Integer capienza;

    @Lob
    private String descrizione;
}
