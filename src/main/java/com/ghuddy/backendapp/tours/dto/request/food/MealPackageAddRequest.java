package com.ghuddy.backendapp.tours.dto.request.food;

import lombok.Data;

@Data
public class MealPackageAddRequest {
    private Long tourPackageID;
    private MealPackageRequest mealPackage;
}
