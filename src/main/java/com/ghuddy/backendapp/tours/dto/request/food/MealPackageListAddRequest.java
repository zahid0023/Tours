package com.ghuddy.backendapp.tours.dto.request.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class MealPackageListAddRequest extends BaseRequest {
    @Schema(description = "The id of the tour package", required = true)
    @JsonProperty("tour_package_id")
    private Long tourPackageID;
    @Schema(description = "The list of the meal packages associated with this tour package", required = true)
    @JsonProperty("meal_packages")
    private List<MealPackageRequest> mealPackages;

    @Override
    public void validate() throws AbstractException {

    }
}
