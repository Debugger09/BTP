package com.nouboudem.api.devis.dto;

import lombok.Data;
import java.util.List;

@Data
public class LotTravauxDto {
    private Long id;
    private String nom;
    private List<LigneDevisDto> lignes;
} 