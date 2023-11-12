package com.ghuddy.backendapp.tours.model.data.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.food.AvailabilityGeneratedMealPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.food.MealPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class AvailabilityGeneratedMealPackageData {
    @Schema(description = "The name of the meal package type")
    @JsonProperty("meal_type_name")
    private String mealTypeName;
    @Schema(description = "The food items belonging to this meal package")
    @JsonProperty("food_items")
    private Map<Long, String> foodItems;
    @Schema(description = "The price of this meal package", required = true, example = "120")
    @JsonProperty("per_meal_package_price")
    private BigDecimal unitPrice;

    private AvailabilityGeneratedMealPackageData(MealPackageData mealPackageData) {
        this.mealTypeName = mealPackageData.getMealTypeName();
        this.foodItems = mealPackageData.getFoodItems();
    }

    public AvailabilityGeneratedMealPackageData(AvailabilityGeneratedMealPackageEntity availabilityGeneratedMealPackageEntity) {
        this(new MealPackageData(availabilityGeneratedMealPackageEntity.getMealPackageEntity()));
        this.unitPrice = availabilityGeneratedMealPackageEntity.getMealPackagePrice();
    }

    public AvailabilityGeneratedMealPackageData(MealPackageEntity mealPackageEntity) {
        this(new MealPackageData(mealPackageEntity));
        this.unitPrice = mealPackageEntity.getPerMealPrice();
    }
}
