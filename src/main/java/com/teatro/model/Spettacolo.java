package com.teatro.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "spettacolo")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Spettacolo {

    public enum Categoria { TEATRO, DANZA, CINEMA, EVENTO }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String titolo;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Categoria categoria = Categoria.TEATRO;

    @Column(length = 150)
    private String regista;

    @Lob
    @Column(name = "cast_principale")
    private String castPrincipale;

    @Lob
    @Column
    private String descrizione;

    @Lob
    @Column
    private String trama;

    @Column(length = 50)
    private String genere;

    @Column(name = "durata_min")
    private Integer durataMin;

    @Column(name = "locandina_url", length = 500)
    private String locandinaUrl;
}
