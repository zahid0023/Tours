package com.ghuddy.backendapp.tours.dto.request.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class MealPackageListAddRequest extends BaseRequest {
    @Schema(description = "The tour package id", required = true, example = "1")
    @JsonProperty("tour_package_id")
    private Long tourPackageId;
    @Schema(description = "The list of meal packages to add",required = true)
    @JsonProperty("tour_package_meal_packages")
    private List<MealPackageRequest> mealPackageRequestList;

    /**
     * @throws AbstractException
     */
    @Override
    public void validate() throws AbstractException {

    }
}
