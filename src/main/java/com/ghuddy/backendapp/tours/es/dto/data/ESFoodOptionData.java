package com.ghuddy.backendapp.tours.es.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.FoodOptionEntity;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

@Data
public class ESFoodOptionData extends ESOptionData {
    @JsonProperty("meal_packages")
    @Field(name = "meal_packages")
    private List<ESMealPackageData> mealPackageList;
    @JsonProperty("tour_package_meal_option_day")
    @Field(name = "tour_package_meal_option_day")
    private Integer dayNumber;
    @JsonProperty("number_of_meals")
    @Field(name = "number_of_meals")
    private Integer numberOfMeals;
    @JsonProperty("number_of_breakfast")
    @Field(name = "number_of_breakfast")
    private Integer numberOfBreakFast;
    @JsonProperty("number_of_lunch")
    @Field(name = "number_of_lunch")
    private Integer numberOfLunch;
    @JsonProperty("number_of_dinner")
    @Field(name = "number_of_dinner")
    private Integer numberOfDinner;

    public ESFoodOptionData(FoodOptionEntity foodOptionEntity) {
        super(foodOptionEntity.getActive(), foodOptionEntity.getIsDefault(), foodOptionEntity.getTotalOptionPricePerPerson());
        this.mealPackageList = foodOptionEntity.getMealPackageEntities().stream()
                .map(mealPackageEntity -> new ESMealPackageData(mealPackageEntity))
                .toList();
        this.dayNumber = foodOptionEntity.getDayNumber();
        this.numberOfMeals = foodOptionEntity.getNumberOfMeals();
        this.numberOfBreakFast = foodOptionEntity.getNumberOfBreakfast();
        this.numberOfLunch = foodOptionEntity.getNumberOfLunch();
        this.numberOfDinner = foodOptionEntity.getNumberOfDinner();
    }
}
