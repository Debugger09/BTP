package com.nouboudem.api.projet.repository;

import com.nouboudem.api.projet.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
} 