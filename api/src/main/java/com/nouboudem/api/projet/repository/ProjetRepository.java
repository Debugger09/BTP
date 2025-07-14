package com.nouboudem.api.projet.repository;

import com.nouboudem.api.projet.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface ProjetRepository extends JpaRepository<Projet, Long> {
} 