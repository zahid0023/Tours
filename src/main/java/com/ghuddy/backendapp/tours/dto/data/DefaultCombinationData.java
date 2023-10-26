package com.ghuddy.backendapp.tours.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.accommodation.AccommodationOptionData;
import com.ghuddy.backendapp.tours.model.data.food.FoodOptionData;
import com.ghuddy.backendapp.tours.model.data.transfer.TransferOptionData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DefaultCombinationData {
    @Schema(description = "The default accommodation option for this combination")
    @JsonProperty("tour_package_default_accommodation_option")
    private AccommodationOptionData accommodationOptionData;
    @Schema(description = "The default food option for this combination")
    @JsonProperty("tour_package_default_food_option")
    private FoodOptionData foodOptionData;
    @Schema(description = "The default transfer option for this combination")
    @JsonProperty("tour_package_default_transfer_option")
    private TransferOptionData transferOptionData;

    @Schema(description = "The price for default option for this tour package")
    @JsonProperty("tour_package_default_option_price_per_person")
    private BigDecimal defaultOptionPricePerPerson;
}
