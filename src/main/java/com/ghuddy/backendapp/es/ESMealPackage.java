package com.ghuddy.backendapp.es;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.FoodItemEntity;
import com.ghuddy.backendapp.tours.model.entities.MealPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class ESMealPackage {
    @JsonProperty("meal_package_name")
    private String mealPackageName;
    @JsonProperty("meal_type_name")
    private String mealTypeName;
    @JsonProperty("food_items")
    private List<String> foodItems;
    @JsonProperty("per_meal_package_price")
    private BigDecimal unitPrice;

    public ESMealPackage(MealPackageEntity mealPackageEntity) {
        this.mealPackageName = mealPackageEntity.getMealPackageName();
        this.mealTypeName = mealPackageEntity.getMealTypeEntity().getMealTypeName();
        this.foodItems = mealPackageEntity.getFoodItemEntities().stream()
                .map(FoodItemEntity::getFoodItemName)
                .toList();
        this.unitPrice = mealPackageEntity.getPerMealPrice();
    }
}
