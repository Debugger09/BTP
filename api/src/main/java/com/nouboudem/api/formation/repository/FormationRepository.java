package com.nouboudem.api.formation.repository;

import com.nouboudem.api.formation.entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface FormationRepository extends JpaRepository<Formation, Long> {
} 