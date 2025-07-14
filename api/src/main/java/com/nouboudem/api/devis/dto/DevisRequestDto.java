package com.nouboudem.api.devis.dto;

import lombok.Data;

@Data
public class DevisRequestDto {
    private String type; // 'PROJET' ou 'PUBLIC'
    private boolean estPublic;
    private Long projetId; // null si public
    private Long clientId; // null si public
    private String objet;
    private Double totalHt;
    private Double tva;
    private Double totalTtc;
} 