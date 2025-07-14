package com.nouboudem.api.devis.entity;

import com.nouboudem.api.projet.entity.Projet;
import com.nouboudem.api.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Devis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // 'PROJET' ou 'PUBLIC'

    @Column(nullable = false)
    private boolean estPublic;

    @Column(nullable = true)
    private String objet;

    @Column(nullable = true)
    private Double totalHt;

    @Column(nullable = true)
    private Double tva;

    @Column(nullable = true)
    private Double totalTtc;

    @ManyToOne
    private Projet projet; // null si type = 'PUBLIC'

    @ManyToOne
    private User client; // null si type = 'PUBLIC'

    @OneToMany(mappedBy = "devis", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<LotTravaux> lots = new ArrayList<>();

    @Column(updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
} 