package com.nouboudem.api.temoignage.repository;

import com.nouboudem.api.temoignage.entity.Temoignage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TemoignageRepository extends JpaRepository<Temoignage, Long> {
    List<Temoignage> findByPublierTrue();
    List<Temoignage> findByUserId(Long userId);
} 