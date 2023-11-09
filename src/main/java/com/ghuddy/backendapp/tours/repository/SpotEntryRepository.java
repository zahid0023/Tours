package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.SpotEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotEntryRepository extends JpaRepository<SpotEntryEntity, Long> {
}