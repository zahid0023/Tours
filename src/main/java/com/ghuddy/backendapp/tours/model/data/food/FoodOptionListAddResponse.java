package com.ghuddy.backendapp.tours.model.data.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FoodOptionListAddResponse extends BaseResponse {
    @Schema(description = "The tour package id this default food options belong to")
    @JsonProperty("tour_package_id")
    private Long tourPackageId;
    @Schema(description = "The default food option for each day")
    @JsonProperty("tour_package_default_food_options")
    private List<FoodOptionData> foodOptionDataList;

    @Schema(description = "Total per person food price for this tour package")
    @JsonProperty("tour_package_default_food_price")
    private BigDecimal totalFoodPrice;
    @Schema(description = "The number of meals belonging to this tour package")
    @JsonProperty("tour_package_total_meals")
    private Integer totalMeals;
    @Schema(description = "The number of breakfasts belonging to this tour package")
    @JsonProperty("tour_package_total_breakfasts")
    private Integer totalBreakfast;
    @Schema(description = "The number of lunches belonging to this tour package")
    @JsonProperty("tour_package_total_lunches")
    private Integer totalLunch;
    @Schema(description = "The number of dinners belonging to this tour package")
    @JsonProperty("tour_package_total_dinners")
    private Integer totalDinner;

    public FoodOptionListAddResponse(Long tourPackageId, List<FoodOptionData> foodOptionDataList, String requestId) {
        this.tourPackageId = tourPackageId;
        this.foodOptionDataList = foodOptionDataList;
        this.setRequestId(requestId);
        this.totalFoodPrice = BigDecimal.ZERO;
        this.totalBreakfast = 0;
        this.totalLunch = 0;
        this.totalDinner = 0;
        this.totalMeals = 0;
        // Iterate over the list and accumulate values
        for (FoodOptionData foodOptionData : foodOptionDataList) {
            totalFoodPrice = totalFoodPrice.add(foodOptionData.getTotalOptionPricePerPerson());
            totalBreakfast += foodOptionData.getNumberOfBreakFast();
            totalLunch += foodOptionData.getNumberOfLunch();
            totalDinner += foodOptionData.getNumberOfDinner();
            totalMeals += foodOptionData.getNumberOfMeals();
        }
    }
}
