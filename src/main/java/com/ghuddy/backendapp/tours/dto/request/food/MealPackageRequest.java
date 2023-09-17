package com.ghuddy.backendapp.tours.dto.request.food;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class MealPackageRequest {
    @Schema(description = "The name of the meal package", required = true, example = "Package 1")
    private String mealPackageName;
    @Schema(description = "The ID of the mealType", required = true, example = "1")
    private Long mealTypeID; // Lunch
    @Schema(description = "The list of IDs of all the food items belonging to this package", required = true)
    private List<Long> foodItemIDs;
}
