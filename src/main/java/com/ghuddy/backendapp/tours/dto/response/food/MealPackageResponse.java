package com.ghuddy.backendapp.tours.dto.response.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MealPackageResponse {
    @Schema(description = "The ID of the mealType", example = "1")
    @JsonProperty("meal_type_id")
    private Long mealTypeID; // Lunch

    @Schema(description = "The name of the meal package", example = "LUNCH")
    @JsonProperty("meal_package_name")
    private String mealPackageName;
    @Schema(description = "The list of IDs of all the food items belonging to this package")
    @JsonProperty("food_item_ids")
    private List<Long> foodItemIDs;
    @Schema(description = "The price of this meal package", example = "120")
    @JsonProperty("meal_package_unit_price")// per meal package price
    private BigDecimal perMealPackagePrice;
}
