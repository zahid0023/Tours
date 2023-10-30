package com.ghuddy.backendapp.es;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.food.MealPackageData;
import com.ghuddy.backendapp.tours.model.entities.FoodOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class ESFoodOption extends ESOption {
    @JsonProperty("meal_packages")
    private List<ESMealPackage> mealPackageList;
    @JsonProperty("tour_package_meal_option_day")
    private Integer dayNumber;
    @JsonProperty("number_of_meals")
    private Integer numberOfMeals;
    @JsonProperty("number_of_breakfast")
    private Integer numberOfBreakFast;
    @JsonProperty("number_of_lunch")
    private Integer numberOfLunch;
    @JsonProperty("number_of_dinner")
    private Integer numberOfDinner;

    public ESFoodOption(FoodOptionEntity foodOptionEntity) {
        super(foodOptionEntity.getActive(), foodOptionEntity.getIsDefault(), foodOptionEntity.getTotalOptionPricePerPerson());
        this.mealPackageList = foodOptionEntity.getMealPackageEntities().stream()
                .map(mealPackageEntity -> new ESMealPackage(mealPackageEntity))
                .toList();
        this.dayNumber = foodOptionEntity.getDayNumber();
        this.numberOfMeals = foodOptionEntity.getNumberOfMeals();
    }
}
