package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.spot.entry.SpotEntryOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourPackageSpotEntryOptionRepository extends JpaRepository<SpotEntryOptionEntity, Long> {
}