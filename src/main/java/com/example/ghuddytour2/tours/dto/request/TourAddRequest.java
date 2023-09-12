package com.example.ghuddytour2.tours.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourAddRequest {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            description = "The ID of the Location",
            example = "1L")
    private Long locationID;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Specify the the number of days of the tour",
            example = "2")
    private Integer numberOfDays;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Specify the the number of nights of the tour",
            example = "1")
    private Integer numberOfNights;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Short address of the tour",
            example = "Bandarban, Chittagong")
    private String shortAddress;
}
