package com.nouboudem.api.service.repository;

import com.nouboudem.api.service.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    boolean existsByNom(String nom);
} 