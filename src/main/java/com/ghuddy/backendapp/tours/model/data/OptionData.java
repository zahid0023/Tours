package com.ghuddy.backendapp.tours.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OptionData {
    @Schema(description = "Whether this option is active or not", required = true, example = "false")
    @JsonProperty("tour_package_option_is_active")
    private Boolean isActive;

    @Schema(description = "Whether this is the default option for this tour package", required = true, example = "false")
    @JsonProperty("tour_package_option_is_default")
    private boolean isDefault;

    @Schema(description = "The total option price per person")
    @JsonProperty("tour_package_option_price_per_person")
    private BigDecimal totalOptionPricePerPerson;

    public OptionData(Boolean isActive, boolean isDefault, BigDecimal totalOptionPricePerPerson) {
        this.isActive = isActive;
        this.isDefault = isDefault;
        this.totalOptionPricePerPerson = totalOptionPricePerPerson;
    }
}
