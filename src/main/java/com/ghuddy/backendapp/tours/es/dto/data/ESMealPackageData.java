package com.ghuddy.backendapp.tours.es.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.food.FoodItemEntity;
import com.ghuddy.backendapp.tours.model.entities.food.MealPackageEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class ESMealPackageData {
    @JsonProperty("meal_type_name")
    @Field(name = "meal_type_name")
    private String mealTypeName;
    @JsonProperty("food_items")
    @Field(name = "food_items")
    private List<String> foodItems;
    @JsonProperty("per_meal_package_price")
    @Field(name = "per_meal_package_price")
    private BigDecimal unitPrice;

    public ESMealPackageData(MealPackageEntity mealPackageEntity) {
        this.mealTypeName = mealPackageEntity.getMealTypeEntity().getMealTypeName();
        this.foodItems = mealPackageEntity.getFoodItemEntities().stream()
                .map(FoodItemEntity::getFoodItemName)
                .toList();
        this.unitPrice = mealPackageEntity.getPerMealPrice();
    }
}
