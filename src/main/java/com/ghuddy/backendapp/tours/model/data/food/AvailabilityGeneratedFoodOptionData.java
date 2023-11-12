package com.ghuddy.backendapp.tours.model.data.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.OptionData;
import com.ghuddy.backendapp.tours.model.data.accommodation.AvailabilityGeneratedAccommodationPackageData;
import com.ghuddy.backendapp.tours.model.entities.food.AvailabilityGeneratedFoodOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.FoodOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AvailabilityGeneratedFoodOptionData extends OptionData {
    private Long foodOptionId;
    @Schema(description = "The day in which this meal option belongs")
    @JsonProperty("tour_package_meal_option_day")
    private Integer dayNumber;

    @Schema(description = "Number of total meals per day")
    @JsonProperty("number_of_meals")
    private Integer numberOfMeals;

    @Schema(description = "if breakfast is included its 1 otherwise 0")
    @JsonProperty("number_of_breakfast")
    private Integer numberOfBreakFast;
    @Schema(description = "if lunch is included its 1 otherwise 0")
    @JsonProperty("number_of_lunch")
    private Integer numberOfLunch;
    @Schema(description = "if dinner is included its 1 otherwise 0")
    @JsonProperty("number_of_dinner")
    private Integer numberOfDinner;
    private List<AvailabilityGeneratedMealPackageData> availabilityGeneratedMealPackageDataList;

    private AvailabilityGeneratedFoodOptionData(FoodOptionData foodOptionData){
        this.foodOptionId = foodOptionData.getFoodOptionId();
        this.dayNumber = foodOptionData.getDayNumber();
        this.numberOfMeals = foodOptionData.getNumberOfMeals();
        this.numberOfBreakFast = foodOptionData.getNumberOfBreakFast();
        this.numberOfLunch = foodOptionData.getNumberOfLunch();
        this.numberOfDinner = foodOptionData.getNumberOfDinner();
    }

    public AvailabilityGeneratedFoodOptionData(AvailabilityGeneratedFoodOptionEntity availabilityGeneratedFoodOptionEntity) {
        this(new FoodOptionData(availabilityGeneratedFoodOptionEntity.getFoodOptionEntity(),availabilityGeneratedFoodOptionEntity.getIsActive()));
        this.availabilityGeneratedMealPackageDataList = availabilityGeneratedFoodOptionEntity.getAvailabilityGeneratedMealPackageEntities().stream()
                .map(availabilityGeneratedMealPackageEntity -> new AvailabilityGeneratedMealPackageData(availabilityGeneratedMealPackageEntity))
                .toList();
        this.setTotalOptionPricePerPerson(availabilityGeneratedFoodOptionEntity.getTotalOptionPricePerPerson());
    }

    public AvailabilityGeneratedFoodOptionData(FoodOptionEntity foodOptionEntity){
        this(new FoodOptionData(foodOptionEntity,foodOptionEntity.getIsActive()));
        this.availabilityGeneratedMealPackageDataList = foodOptionEntity.getMealPackageEntities().stream()
                .map(mealPackageEntity-> new AvailabilityGeneratedMealPackageData(mealPackageEntity))
                .toList();
        this.setTotalOptionPricePerPerson(foodOptionEntity.getTotalOptionPricePerPerson());
    }
}
