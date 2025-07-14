package com.nouboudem.api.devis.repository;

import com.nouboudem.api.devis.entity.Devis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevisRepository extends JpaRepository<Devis, Long> {
} 