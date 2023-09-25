package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.entities.TransportationBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportationBrandRepository extends JpaRepository<TransportationBrandEntity, Long> {
}