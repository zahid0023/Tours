package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.entities.ActivityImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityImageRepository extends JpaRepository<ActivityImageEntity, Long> {
}