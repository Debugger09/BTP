package com.nouboudem.api.service.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCreateDto {
    private String nom;
    private String description;
} 