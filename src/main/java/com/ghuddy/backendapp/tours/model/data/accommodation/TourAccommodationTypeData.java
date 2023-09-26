package com.ghuddy.backendapp.tours.model.data.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourAccommodationTypeData {
    @Schema(description = "The id of the tour accommodation type", example = "1")
    @JsonProperty("tour_accommodation_type_id")
    private Long tourAccommodationTypeId;
    @Schema(description = "The name of the tour accommodation type", example = "Hotel")
    @JsonProperty("tour_accommodation_type_name")
    private String tourAccommodationTypeName;
}
