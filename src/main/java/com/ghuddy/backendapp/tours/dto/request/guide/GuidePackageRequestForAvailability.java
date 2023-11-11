package com.ghuddy.backendapp.tours.dto.request.guide;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GuidePackageRequestForAvailability {
    @Schema(description = "The guide package id", required = true, example = "1")
    @JsonProperty("tour_package_guide_package_id")
    private Long guidePackageId;
    @Schema(description = "The guide package price", required = true, example = "1000")
    @JsonProperty("tour_package_guide_package_price")
    private BigDecimal guidePackagePrice;
}
