package com.ghuddy.backendapp.tours.dto.request.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.enums.TripType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TransferPackageRequest {

    @Schema(description = "The route of the transfer", example = "nilachol - nilgiri - shoilo propat")
    @JsonProperty("transfer_route")
    private String transferRoute;
    @Schema(description = "The mode id of the transportation", required = true, example = "1")
    @JsonProperty("tour_transfer_mode_id")
    private Long transferModeId;

    @Schema(description = "The id of the transfer provider", required = true, example = "1")
    @JsonProperty("tour_transfer_provider_id")
    private Long transferProviderId;

    @Schema(description = "The trip type",required = true,example = "ONE_WAY")
    @JsonProperty("trip_type")
    private TripType tripType;

    @Schema(description = "Whether AC is provided", required = true, example = "true")
    @JsonProperty("is_ac")
    private Boolean isAc;
    @Schema(description = "The price of the transfer per day", required = true, example = "3500")
    @JsonProperty("tour_transfer_unit_price")
    private BigDecimal transferUnitPrice;

    @Schema(description = "The number of travellers can travel at the same time in this vehicle", required = true, example = "14")
    @JsonProperty("suitable_for_persons")
    private Integer suitableForPersons;
    @Schema(description = "The number of vehicles provided for this tour package", required = true, example = "1")
    @JsonProperty("number_of_vehicles")
    private Integer numberOfVehicles;

}
