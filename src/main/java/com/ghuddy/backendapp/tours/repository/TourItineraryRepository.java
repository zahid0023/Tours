package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.TourItineraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourItineraryRepository extends JpaRepository<TourItineraryEntity, Long> {
}