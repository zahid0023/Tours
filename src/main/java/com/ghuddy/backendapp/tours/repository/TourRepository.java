package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.entities.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<TourEntity, Long> {
}