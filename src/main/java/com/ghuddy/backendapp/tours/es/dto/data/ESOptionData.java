package com.ghuddy.backendapp.tours.es.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ESOptionData {
    @JsonProperty("tour_package_option_is_active")
    @Field(name = "tour_package_option_is_active")
    private Boolean isActive;
    @JsonProperty("tour_package_option_is_default")
    @Field(name = "tour_package_option_is_default")
    private boolean isDefault;
    @JsonProperty("tour_package_option_price_per_person")
    @Field(name = "tour_package_option_price_per_person")
    private BigDecimal totalOptionPricePerPerson;

    public ESOptionData(Boolean isActive, Boolean isDefault, BigDecimal totalOptionPricePerPerson) {
        this.isActive = isActive;
        this.isDefault = isDefault;
        this.totalOptionPricePerPerson = totalOptionPricePerPerson;
    }
}