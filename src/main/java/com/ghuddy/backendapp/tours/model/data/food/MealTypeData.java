package com.ghuddy.backendapp.tours.model.data.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MealTypeData {
    @Schema(description = "The id of the meal type", required = true, example = "1")
    @JsonProperty("meal_type_id")
    private Long mealTypeId;
    @Schema(description = "The name of the meal type", required = true, example = "Lunch")
    @JsonProperty("meal_type_name")
    private String mealTypeName;
}
