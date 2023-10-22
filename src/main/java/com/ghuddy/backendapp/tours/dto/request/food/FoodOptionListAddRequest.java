package com.ghuddy.backendapp.tours.dto.request.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Data
public class FoodOptionListAddRequest extends BaseRequest {
    @Schema(description = "The id of the tour package", required = true, example = "1")
    @JsonProperty("tour_package_id")
    private Long tourPackageID;
    @Schema(description = "The food options package associated with the tour package", required = true)
    @JsonProperty("tour_package_food_options")
    private List<FoodOptionRequest> foodOptionRequestList;

    @Override
    public void validate() throws AbstractException {

    }
}
