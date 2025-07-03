package com.nouboudem.user_service.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import com.nouboudem.user_service.enums.Role;

/**
 * Entité représentant un utilisateur de la plateforme.
 */
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique de l'utilisateur

    @NotBlank
    private String lastName; // Nom de famille de l'utilisateur

    @NotBlank
    private String firstName; // Prénom de l'utilisateur

    @Email
    @NotBlank
    private String email; // Adresse email (unique)

    @NotBlank
    private String password; // Mot de passe (hashé)

    @Enumerated(EnumType.STRING)
    private Role role; // Rôle de l'utilisateur (ADMIN, CLIENT)

    @NotBlank
    private String status; // Statut de l'utilisateur (ex : ACTIF, INACTIF)

    private LocalDateTime creationDate; // Date de création du compte

    private LocalDateTime updateDate; // Date de la dernière modification du compte

    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateDate = LocalDateTime.now();
    }
} 