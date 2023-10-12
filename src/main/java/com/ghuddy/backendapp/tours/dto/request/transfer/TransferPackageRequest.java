package com.ghuddy.backendapp.tours.dto.request.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferPackageRequest {
    @Schema(description = "The mode id of the transportation", required = true, example = "1")
    @JsonProperty("tour_transfer_mode_id")
    private Long transferModeId;

    @Schema(description = "The id of the transfer provider", required = true, example = "1")
    @JsonProperty("tour_transfer_provider_id")
    private Long transferProvideId;
    @Schema(description = "The price of the transfer per day", required = true, example = "3500")
    @JsonProperty("tour_transfer_price_per_day")
    private BigDecimal transferPricePerDay;
    @Schema(description = "The number of days the transfer service provided in the tour", required = true, example = "2")
    @JsonProperty("tour_transfer_provided_in_days")
    private BigDecimal numberOfDays;

    @Schema(description = "The number of travellers can travel at the same time in this vehicle", required = true, example = "14")
    @JsonProperty("maximum_number_of_travellers")
    private Integer maxTransferCapacity;
    @Schema(description = "The number of vehicles provided for this tour package", required = true, example = "1")
    @JsonProperty("number_of_vehicles")
    private Integer numberOfVehicles;

    @Schema(description = "Whether this is the default transfer package of this tour package",required = true,example = "true")
    @JsonProperty("is_default")
    private Boolean isDefault;
}
