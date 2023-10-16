package com.ghuddy.backendapp.tours.dto.request.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class FoodOptionRequest {
    @Schema(description = "The meal packages combination for this option", required = true)
    @JsonProperty("tour_package_meal_packages")
    private List<MealPackageRequest> mealPackageRequestList;

    @Schema(description = "Whether this is the default food option for the tour package", required = true, example = "false")
    @JsonProperty("tour_package_default_food_option")
    private Boolean isDefault;
}
