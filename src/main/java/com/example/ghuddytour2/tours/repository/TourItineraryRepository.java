package com.example.ghuddytour2.tours.repository;

import com.example.ghuddytour2.tours.entities.TourItineraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourItineraryRepository extends JpaRepository<TourItineraryEntity, Long> {
}