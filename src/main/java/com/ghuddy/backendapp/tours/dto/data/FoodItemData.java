package com.ghuddy.backendapp.tours.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FoodItemData {
    @Schema(description = "The id of the food item", required = true, example = "1")
    @JsonProperty("food_item_id")
    private Long foodItemId;
    @Schema(description = "The name of the food item", required = true, example = "Rice")
    @JsonProperty("food_item_name")
    private String foodItemName;
}