package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.guide.GuideOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuideOptionRepository extends JpaRepository<GuideOptionEntity, Long> {
}