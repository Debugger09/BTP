package com.nouboudem.api.temoignage.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemoignageDto {
    private Long id;
    private Long userId; // null si anonyme
    private String contenu;
    private Boolean publier;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 