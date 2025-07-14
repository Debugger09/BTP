package com.nouboudem.api.formation.repository;

import com.nouboudem.api.formation.entity.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
} 