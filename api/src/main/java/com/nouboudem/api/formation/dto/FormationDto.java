package com.nouboudem.api.formation.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
import com.nouboudem.api.media.entity.Media;

@Data
public class FormationDto {
    private Long id;
    private String titre;
    private String objectifs;
    private String debouches;
    private String duree;
    private BigDecimal fraisInscription;
    private BigDecimal fraisFormation;
    private List<Media> medias;
} 