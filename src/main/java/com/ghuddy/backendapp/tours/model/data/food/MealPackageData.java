package com.ghuddy.backendapp.tours.model.data.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.MealPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MealPackageData {
    @Schema(description = "The name of the meal package name")
    @JsonProperty("meal_package_name")
    private String mealPackageName;
    @Schema(description = "The name of the meal package type")
    @JsonProperty("meal_type_name")
    private String mealTypeName;
    @Schema(description = "The food items belonging to this meal package")
    @JsonProperty("food_items")
    private List<String> foodItems;

    @Schema(description = "The price of this meal package", required = true, example = "120")
    @JsonProperty("meal_package_unit_price")
    private BigDecimal unitPrice;
    @Schema(description = "The number of this meal package provided during the tour", required = true, example = "4")
    @JsonProperty("meal_package_quantity")
    private Integer quantity;
    @Schema(description = "The total/final price of this meal package", required = true, example = "120")
    @JsonProperty("meal_package_total_price")
    private BigDecimal totalMealPackagePrice;

    public MealPackageData(MealPackageEntity mealPackageEntity) {
        this.mealPackageName = mealPackageEntity.getMealPackageName();
        this.mealTypeName = mealPackageEntity.getMealTypeEntity().getMealTypeName();
        this.foodItems = mealPackageEntity.getFoodItemEntities().stream()
                .map(foodItemEntity -> foodItemEntity.getFoodItemName())
                .collect(Collectors.toList());
        this.unitPrice = mealPackageEntity.getPerMealPrice();
        this.quantity = mealPackageEntity.getPerPersonNumberOfMeals();
        this.totalMealPackagePrice = mealPackageEntity.getPerPersonTotalMealPrice();
    }
}
