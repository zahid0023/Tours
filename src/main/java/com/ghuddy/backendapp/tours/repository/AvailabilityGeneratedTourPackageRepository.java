package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.tourpackage.AvailabilityGeneratedTourPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailabilityGeneratedTourPackageRepository extends JpaRepository<AvailabilityGeneratedTourPackageEntity, Long> {
}