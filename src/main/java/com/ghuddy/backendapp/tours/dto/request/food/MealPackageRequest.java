package com.ghuddy.backendapp.tours.dto.request.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MealPackageRequest {
    @Schema(description = "The ID of the mealType", required = true, example = "1")
    @JsonProperty("meal_type_id")
    private Long mealTypeID; // Lunch
    @Schema(description = "The list of IDs of all the food items belonging to this package", required = true)
    @JsonProperty("food_item_ids")
    private List<Long> foodItemIDs;
    @Schema(description = "The price of this meal package", required = true, example = "120")
    @JsonProperty("meal_package_unit_price")// per meal package price
    private BigDecimal perMealPrice;
    @Schema(description = "The number of this meal package provided during the tour", required = true, example = "4")
    @JsonProperty("meal_package_quantity")
    private Integer numberOfMeals;
    @Schema(description = "The total/final price of this meal package", required = true, example = "120")
    @JsonProperty("meal_package_total_price")
    private BigDecimal totalMealPackagePrice;

    @Schema(description = "Whether this is meal package comes with the tour package or optional, i.e. the user can choose this for this the price will vary", required = true, example = "true")
    @JsonProperty("meal_package_is_default")
    private Boolean isDefault;
}
