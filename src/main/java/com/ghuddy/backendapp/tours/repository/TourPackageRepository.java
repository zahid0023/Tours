package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.entities.TourPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourPackageRepository extends JpaRepository<TourPackageEntity, Long> {
}