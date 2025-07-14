package com.nouboudem.api.formation.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String objectifs;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String debouches;

    @Column(nullable = false)
    private String duree;

    @Column(nullable = false)
    private BigDecimal fraisInscription;

    @Column(nullable = false)
    private BigDecimal fraisFormation;
} 