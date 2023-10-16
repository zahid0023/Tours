package com.ghuddy.backendapp.tours.model.data.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.FoodOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class FoodOptionData {
    @Schema(description = "The meal packages belonging to this food option")
    @JsonProperty("meal_packages")
    private List<MealPackageData> mealPackageDataList;

    public FoodOptionData(FoodOptionEntity foodOptionEntity) {
        this.mealPackageDataList = foodOptionEntity.getMealPackageEntities().stream()
                .map(mealPackageEntity -> new MealPackageData(mealPackageEntity))
                .collect(Collectors.toList());
    }
}
