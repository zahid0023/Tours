package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.food.FoodOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOptioRepository extends JpaRepository<FoodOptionEntity, Long> {
}