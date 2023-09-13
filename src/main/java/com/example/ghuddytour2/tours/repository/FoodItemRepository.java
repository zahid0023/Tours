package com.example.ghuddytour2.tours.repository;

import com.example.ghuddytour2.tours.entities.FoodItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItemEntity, Long> {
}