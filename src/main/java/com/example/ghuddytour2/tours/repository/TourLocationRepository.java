package com.example.ghuddytour2.tours.repository;

import com.example.ghuddytour2.tours.entities.TourLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourLocationRepository extends JpaRepository<TourLocationEntity, Long> {
}