package com.ghuddy.backendapp.tours.dto.request.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.food.MealPackageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FoodOptionRequest {
    @Schema(description = "The meal packages belonging to this option", required = true)
    @JsonProperty("tour_package_meal_packages")
    private List<MealPackageRequest> mealPackageRequestList;

    @Schema(description = "The day in which this option is available")
    @JsonProperty("day_number")
    private Integer dayNumber;
    @Schema(description = "Whether this is the default food option for the tour package", example = "false")
    @JsonProperty("tour_package_default_food_option")
    private Boolean isDefault;

    @Schema(description = "The meal price for the day")
    @JsonProperty("tour_package_food_option_price")
    private BigDecimal foodOptionTotalPrice;
}
