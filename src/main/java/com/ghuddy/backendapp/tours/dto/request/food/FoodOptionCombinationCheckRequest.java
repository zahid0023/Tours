package com.ghuddy.backendapp.tours.dto.request.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class FoodOptionCombinationCheckRequest extends BaseRequest {
    @Schema(description = "The meal packages for this tour package")
    @JsonProperty("tour_package_meal_packages")
    private List<MealPackageRequest> mealPackageRequestList;
    @Schema(description = "In which the which meal will be provided during the tour")
    @JsonProperty("meals_provided_in_days")
    private HashMap<Integer, List<Long>> mealsProvidedInDays;

    @Override
    public void validate() throws AbstractException {

    }
}
