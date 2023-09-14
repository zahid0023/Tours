package com.example.ghuddytour2.tours.dto.request.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourAddRequest {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            description = "The ID of the Location",
            example = "1L")
    @JsonProperty("destination_location_id")
    private Long locationID;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Specify the the number of days of the tour",
            example = "2")
    @JsonProperty("number_of_days")
    private Integer numberOfDays;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Specify the the number of nights of the tour",
            example = "1")
    @JsonProperty("number_of_nights")
    private Integer numberOfNights;

    @JsonProperty("short_address")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Short address of the tour",
            example = "Bandarban, Chittagong")
    private String shortAddress;
}
