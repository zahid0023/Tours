package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationOptionRepository extends JpaRepository<AccommodationOptionEntity, Long> {
}