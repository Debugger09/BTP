package com.nouboudem.api.formation.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class InscriptionDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String portable;
    private LocalDateTime dateInscription;
    private Long formationId;
} 