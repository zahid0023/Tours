package com.example.ghuddytour2.tours.dto.request.food;

import lombok.Data;

import java.util.List;

@Data
public class MealPackageListAddRequest {
    private Long tourPackageID;
    private List<MealPackageRequest> mealPackages;
}