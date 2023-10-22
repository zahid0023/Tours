package com.ghuddy.backendapp.tours.model.data.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.FoodOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class FoodOptionData {

    @Schema(description = "The meal packages belonging to this food option")
    @JsonProperty("meal_packages")
    private List<MealPackageData> mealPackageDataList;
    @Schema(description = "Whether this is a default food option")
    @JsonProperty("tour_package_default_meal_option")
    private Boolean isDefault;

    @Schema(description = "The day in which this meal package belongs")
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
    @Schema(description = "The total option price for the day")
    @JsonProperty("total_option_price")
    private BigDecimal totalOptionPrice;

    public FoodOptionData(FoodOptionEntity foodOptionEntity) {
        this.mealPackageDataList = foodOptionEntity.getMealPackageEntities().stream()
                .map(mealPackageEntity -> new MealPackageData(mealPackageEntity))
                .collect(Collectors.toList());
        this.numberOfMeals = foodOptionEntity.getNumberOfMeals();
        this.numberOfBreakFast = foodOptionEntity.getNumberOfBreakfast();
        this.numberOfLunch = foodOptionEntity.getNumberOfLunch();
        this.numberOfDinner = foodOptionEntity.getNumberOfDinner();
        this.totalOptionPrice = foodOptionEntity.getTotalOptionPrice();
        this.isDefault = foodOptionEntity.getIsDefault();
        this.dayNumber = foodOptionEntity.getDayNumber();
    }
}
