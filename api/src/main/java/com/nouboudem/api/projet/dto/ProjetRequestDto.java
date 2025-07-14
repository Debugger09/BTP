package com.nouboudem.api.projet.dto;

import lombok.Data;

@Data
public class ProjetRequestDto {
    private String nom;
    private String description;
    private Long clientId;
} 