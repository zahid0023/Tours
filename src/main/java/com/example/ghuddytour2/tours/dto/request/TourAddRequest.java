package com.example.ghuddytour2.tours.dto.request;

import com.example.ghuddytour2.tours.dto.data.TourDescriptionData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourAddRequest {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            description = "The ID of the Location",
            example = "1L")
    private Long locationID;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            description = "The name of the tour",
            example = "Bandarban Tour")
    private String tourName;
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
    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            description = "Please provide an thumbnail image URL for the tour, " +
                    "It will be displayed alongside the tour details.")
    private String thumbImageUrl;
    private TourDescriptionData tourDescription;
}
