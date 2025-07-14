package com.nouboudem.api.temoignage.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemoignageCreateDto {
    private Long userId; // null si anonyme
    private String contenu;
} 