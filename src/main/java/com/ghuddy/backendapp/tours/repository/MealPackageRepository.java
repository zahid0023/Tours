package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.entities.MealPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealPackageRepository extends JpaRepository<MealPackageEntity, Long> {
}