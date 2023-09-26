package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.TourAccommodationTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourAccommodationTypeRepository extends JpaRepository<TourAccommodationTypeEntity, Long> {
}