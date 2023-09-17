package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.entities.TourLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourLocationRepository extends JpaRepository<TourLocationEntity, Long> {
}