package com.nouboudem.api.projet.dto;

import com.nouboudem.api.media.entity.Media;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PaiementDto {
    private Long id;
    private Long phaseId;
    private BigDecimal montant;
    private LocalDateTime datePaiement;
    private String modePaiement;
    private List<Media> medias;
} 