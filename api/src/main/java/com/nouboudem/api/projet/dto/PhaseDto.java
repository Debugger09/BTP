package com.nouboudem.api.projet.dto;

import lombok.Data;
import java.util.List;

@Data
public class PhaseDto {
    private Long id;
    private String titre;
    private Integer ordre;
    private Long projetId;
    private List<PaiementDto> paiements;
} 