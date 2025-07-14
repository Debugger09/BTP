package com.nouboudem.api.devis.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class LigneDevisDto {
    private Long id;
    private String description;
    private String unite;
    private BigDecimal quantite;
    private BigDecimal prixUnitaire;
    private BigDecimal montant;
    private Long lotTravauxId;
    private Long devisId;
} 