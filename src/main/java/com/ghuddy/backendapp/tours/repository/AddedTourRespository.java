package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.AddedTourEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddedTourRespository extends JpaRepository<AddedTourEntity, Long> {
}