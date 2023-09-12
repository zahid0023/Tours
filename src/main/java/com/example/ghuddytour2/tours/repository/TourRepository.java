package com.example.ghuddytour2.tours.repository;

import com.example.ghuddytour2.tours.entities.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<TourEntity, Long> {
}