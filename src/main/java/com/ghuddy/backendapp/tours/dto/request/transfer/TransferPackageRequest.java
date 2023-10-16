package com.ghuddy.backendapp.tours.dto.request.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Schema(description = "Whether AC is provided", required = true, example = "true")
    @JsonProperty("is_ac")
    private Boolean isAc;
    @Schema(description = "The price of the transfer per day", required = true, example = "3500")
    @JsonProperty("tour_transfer_price_per_day")
    private BigDecimal transferPricePerDay;
    @Schema(description = "The days of the tour in which the transfer service provided", required = true, example = "[1,2]")
    @JsonProperty("tour_transfer_provided_in_days")
    private Integer[] dayNumbers;

    @Schema(description = "The number of travellers can travel at the same time in this vehicle", required = true, example = "14")
    @JsonProperty("maximum_number_of_travellers")
    private Integer maxTransferCapacity;
    @Schema(description = "The number of vehicles provided for this tour package", required = true, example = "1")
    @JsonProperty("number_of_vehicles")
    private Integer numberOfVehicles;

}
