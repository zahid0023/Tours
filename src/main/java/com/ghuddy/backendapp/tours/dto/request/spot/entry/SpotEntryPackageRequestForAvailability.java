package com.ghuddy.backendapp.tours.dto.request.spot.entry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.OptionRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpotEntryPackageRequestForAvailability extends OptionRequest {
    @Schema(description = "The spot entry id", required = true, example = "1")
    @JsonProperty("spot_entry_id")
    private Long spotEntryId;
    @Schema(description = "The spot entry price", required = true, example = "140")
    @JsonProperty("spot_entry_price")
    private BigDecimal spotEntryPrice;
}
