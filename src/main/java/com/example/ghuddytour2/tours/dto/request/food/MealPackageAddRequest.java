package com.example.ghuddytour2.tours.dto.request.food;

import lombok.Data;

@Data
public class MealPackageAddRequest {
    private Long tourPackageID;
    private MealPackageRequest mealPackage;
}
