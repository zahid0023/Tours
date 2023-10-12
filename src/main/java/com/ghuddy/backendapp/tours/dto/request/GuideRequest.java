package com.ghuddy.backendapp.tours.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GuideRequest {
    @Schema(description = "The price of the guide per day", required = true, example = "700")
    @JsonProperty("tour_guide_price_per_day")
    private BigDecimal perDayGuidePrice;
    @Schema(description = "The number of days the guide service will be provided", required = true, example = "2")
    @JsonProperty("tour_guide_provided_in_days")
    private Integer numberOfDaysGuideProvided;
}
