package com.ghuddy.backendapp.es.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ESOptionData {
    @JsonProperty("tour_package_option_is_active")
    private Boolean isActive;
    @JsonProperty("tour_package_option_is_default")
    private boolean isDefault;
    @JsonProperty("tour_package_option_price_per_person")
    private BigDecimal totalOptionPricePerPerson;

    public ESOptionData(Boolean isActive, Boolean isDefault, BigDecimal totalOptionPricePerPerson) {
        this.isActive = isActive;
        this.isDefault = isDefault;
        this.totalOptionPricePerPerson = totalOptionPricePerPerson;
    }
}
