package com.example.ghuddytour2.tours.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class MealPackageRequest {
    private String mealPackageName; // Couple Package - Lunch
    private Long mealTypeID; // Lunch
    private List<Long> foodItemIDs;
}
