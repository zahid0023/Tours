package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.SubscribedTourEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribedTourRepository extends JpaRepository<SubscribedTourEntity, Long> {
}