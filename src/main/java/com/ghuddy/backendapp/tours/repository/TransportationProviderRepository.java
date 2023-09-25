package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.entities.TransportationProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportationProviderRepository extends JpaRepository<TransportationProviderEntity, Long> {
}