package com.ghuddy.backendapp.tours.dto.request.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.OptionRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class FoodOptionRequest extends OptionRequest {
    @Schema(description = "The meal packages belonging to this option", required = true)
    @JsonProperty("tour_package_meal_packages")
    private List<MealPackageRequest> mealPackageRequestList;

    @Schema(description = "The day in which this option is available")
    @JsonProperty("day_number")
    private Integer dayNumber;
}
