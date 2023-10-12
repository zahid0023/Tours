package com.ghuddy.backendapp.tours.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TourPeriod {
    @Schema(description = "The start date of the subscribed tour subscribed by the merchant. It means the merchant will provide the tour starting from this day", required = true, example = "2023-10-21")
    @JsonProperty("tour_start_date")
    private LocalDate tourStartDate;
    @Schema(description = "The end date of the subscribed tour subscribed by the merchant. It means the merchant will end the tour on this day.", required = true, example = "2023-10-23")
    @JsonProperty("tour_end_date")
    private LocalDate tourEndDate;
}
