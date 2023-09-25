package com.ghuddy.backendapp.tours.dto.request.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MealPackageAddRequest extends BaseRequest {
    @Schema(description = "The id of the tour package",required = true,example = "1")
    @JsonProperty("tour_package_id")
    private Long tourPackageID;
    @Schema(description = "The meal package associated with the tour package",required = true)
    @JsonProperty("meal_package")
    private MealPackageRequest mealPackage;

    @Override
    public void validate() throws AbstractException {

    }
}
