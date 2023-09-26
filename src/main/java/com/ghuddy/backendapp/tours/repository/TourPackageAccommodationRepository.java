package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.TourPackageAccommodationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourPackageAccommodationRepository extends JpaRepository<TourPackageAccommodationEntity, Long> {
}