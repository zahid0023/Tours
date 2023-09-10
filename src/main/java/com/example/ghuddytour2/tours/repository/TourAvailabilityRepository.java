package com.example.ghuddytour2.tours.repository;

import com.example.ghuddytour2.tours.entities.TourAvailabilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourAvailabilityRepository extends JpaRepository<TourAvailabilityEntity, Long> {
}