package com.ghuddy.backendapp.tours.dto.response.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.food.FoodOptionData;
import com.ghuddy.backendapp.tours.model.entities.FoodOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class DefaultFoodOptionData {
    @Schema(description = "The default food option for each day")
    @JsonProperty("day_number_wise_default_options")
    private Map<Integer, FoodOptionData> dayNumberToFoodOptionMap;

    @Schema(description = "Total per person food price for this tour package")
    @JsonProperty("tour_package_default_food_price")
    private BigDecimal totalFoodPrice = BigDecimal.ZERO;



    public DefaultFoodOptionData(Map<Integer, FoodOptionData> dayNumberToFoodOptionMap) {
        this.dayNumberToFoodOptionMap = dayNumberToFoodOptionMap;
    }
}
