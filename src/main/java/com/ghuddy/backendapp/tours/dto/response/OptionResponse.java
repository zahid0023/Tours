package com.ghuddy.backendapp.tours.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OptionResponse {
    @Schema(description = "Whether this option is active or not")
    @JsonProperty("tour_package_option_is_active")
    private Boolean isActive;

    @Schema(description = "Whether this is the default option for this tour package")
    @JsonProperty("tour_package_option_is_default")
    private Boolean isDefault;

    @Schema(description = "The meal price for the day")
    @JsonProperty("tour_package_option_price")
    private BigDecimal optionPricePerPerson;
}
