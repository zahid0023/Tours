package com.ghuddy.backendapp.tours.dto.request.guide;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.OptionRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GuidePackageRequest extends OptionRequest {
    @Schema(description = "The price of the guide per day", required = true, example = "700")
    @JsonProperty("tour_guide_price_per_day")
    private BigDecimal perDayGuidePrice;

    @Schema(description = "The number of guide provided for this guide package")
    @JsonProperty("number_of_guides")
    private Integer numberOfGuides;
    @Schema(description = "The number of days the guide service will be provided", required = true, example = "2")
    @JsonProperty("tour_guide_provided_in_days")
    private List<Integer> guideProvidedInDays;
}