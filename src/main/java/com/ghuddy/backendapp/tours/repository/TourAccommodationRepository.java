package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.TourAccommodationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourAccommodationRepository extends JpaRepository<TourAccommodationEntity, Long> {
}