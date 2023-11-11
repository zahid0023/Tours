package com.ghuddy.backendapp.tours.dto.request.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccommodationPackageRequestForAvailability {
    @Schema(description = "The accommodation package id", required = true, example = "1")
    @JsonProperty("accommodation_package_id")
    private Long accommodationPackageId;
    @Schema(description = "The price for this accommodation package", required = true, example = "1000")
    @JsonProperty("per_night_room_price")
    private BigDecimal perNightRoomPrice;
}
