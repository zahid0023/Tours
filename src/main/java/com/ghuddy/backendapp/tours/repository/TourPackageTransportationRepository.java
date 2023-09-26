package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.TourPackageTransportationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourPackageTransportationRepository extends JpaRepository<TourPackageTransportationEntity, Long> {
}