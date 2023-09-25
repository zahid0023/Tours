package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.entities.TransportationRouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportationRouteRepository extends JpaRepository<TransportationRouteEntity, Long> {
}