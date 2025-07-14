package com.nouboudem.api.devis.dto;

import com.nouboudem.api.media.entity.Media;
import lombok.Data;
import java.util.List;

@Data
public class DevisResponseDto {
    private Long id;
    private String type;
    private boolean estPublic;
    private Long projetId;
    private Long clientId;
    private String objet;
    private Double totalHt;
    private Double tva;
    private Double totalTtc;
    private List<LotTravauxDto> lots;
    private List<LigneDevisDto> lignes;
    private List<Media> medias;
} 