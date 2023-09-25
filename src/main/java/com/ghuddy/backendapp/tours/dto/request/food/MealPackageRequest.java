package com.ghuddy.backendapp.tours.dto.request.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class MealPackageRequest {
    @Schema(description = "The name of the meal package", required = true, example = "Package 1")
    @JsonProperty("meal_package_name")
    private String mealPackageName;
    @Schema(description = "The ID of the mealType", required = true, example = "1")
    @JsonProperty("meal_type_id")
    private Long mealTypeID; // Lunch
    @Schema(description = "The list of IDs of all the food items belonging to this package", required = true)
    @JsonProperty("food_item_ids")
    private List<Long> foodItemIDs;
}
