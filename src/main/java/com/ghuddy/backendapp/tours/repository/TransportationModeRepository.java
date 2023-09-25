package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.entities.TransportationModeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportationModeRepository extends JpaRepository<TransportationModeEntity, Long> {
}