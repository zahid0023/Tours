package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageAvailabilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourPackageAvailabilityRepository extends JpaRepository<TourPackageAvailabilityEntity, Long> {
}