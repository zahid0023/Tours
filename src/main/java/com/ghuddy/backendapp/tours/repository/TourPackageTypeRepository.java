package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.TourPackageTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourPackageTypeRepository extends JpaRepository<TourPackageTypeEntity, Long> {
}