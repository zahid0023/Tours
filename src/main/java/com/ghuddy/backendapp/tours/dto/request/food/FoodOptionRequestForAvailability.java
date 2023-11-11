package com.ghuddy.backendapp.tours.dto.request.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.OptionRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FoodOptionRequestForAvailability extends OptionRequest {
    @Schema(description = "The list of meal packages", required = true)
    @JsonProperty("meal_packages")
    private List<MealPackageRequestForAvailability> mealPackageRequestForAvailabilityList;
}
