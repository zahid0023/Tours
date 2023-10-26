package com.ghuddy.backendapp.tours.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.accommodation.AccommodationOptionData;
import com.ghuddy.backendapp.tours.model.data.food.FoodOptionData;
import com.ghuddy.backendapp.tours.model.data.transfer.TransferOptionData;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationPackageData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ComponentCombinationData {
    @Schema(description = "The accommodation option")
    @JsonProperty("tour_package_accommodation_option")
    private AccommodationOptionData accommodationOptionData;
    @Schema(description = "The food option")
    @JsonProperty("tour_package_food_option")
    private FoodOptionData foodOptionData;
    @Schema(description = "The transfer option")
    @JsonProperty("tour_package_transfer_option")
    private TransferOptionData transferOptionData;

    @Schema(description = "The transportation package")
    @JsonProperty("tour_package_transportation_package")
    private TransportationPackageData transportationPackageData;

    @Schema(description = "The per person price for this tour package which is the sum of all the component price per person")
    @JsonProperty("tour_package_total_option_price_per_person")
    private BigDecimal totalOptionPricePerPerson;
}
