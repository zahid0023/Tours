package com.ghuddy.backendapp.tours.dto.request.transporation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.enums.TripType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TourPackageTransportationRequest {
    @Schema(description = "The id of the transportation route", required = true, example = "1")
    @JsonProperty("transportation_route_id")
    private Long transportationRouteID;
    @Schema(description = "The id of the transportation mode", required = true, example = "1")
    @JsonProperty("transportation_mode_id")
    private Long transportationModeID;
    @Schema(description = "The id of the transportation brand", required = true, example = "1")
    @JsonProperty("transportation_brand_id")
    private Long transportationBrandID;
    @Schema(description = "The id of the transportation provider", required = true, example = "1")
    @JsonProperty("transportation_provider_id")
    private Long transportationProviderID;
    @Schema(description = "The type of the tour, can be one of the following: ONE_WAY/ROUND_TRIP", required = true, example = "ONE_WAY")
    @JsonProperty("trip_type")
    private TripType tripType;
    @Schema(description = "Where the transportation has ac or not", example = "true")
    @JsonProperty("is_ac")
    private Boolean isAC;
    @Schema(description = "The price of this transportation package", required = true, example = "120")
    @JsonProperty("transportation_package_unit_price")
    private BigDecimal unitPrice;
    @Schema(description = "The number of transportation packages provided for this tour package", required = true, example = "1")
    @JsonProperty("transportation_package_quantity")
    private Integer quantity;
    @Schema(description = "The net price of this transportation package", required = true, example = "120")
    @JsonProperty("transportation_package_net_price")
    private BigDecimal netPrice;
    @Schema(description = "The added/subtracted price of this transportation package", required = true, example = "120")
    @JsonProperty("transportation_package_added_price")
    private BigDecimal addedPrice;
    @Schema(description = "The total/final price of this transportation package", required = true, example = "120")
    @JsonProperty("transportation_package_total_price")
    private BigDecimal totalTransportationPackagePrice;
    @Schema(description = "Whether this is transportation package comes with the tour package or optional, i.e. the user can choose this for this the price will vary", required = true, example = "true")
    @JsonProperty("transportation_package_is_default")
    private Boolean isDefault;
}
