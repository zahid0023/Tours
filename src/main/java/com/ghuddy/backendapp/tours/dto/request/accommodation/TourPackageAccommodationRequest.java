package com.ghuddy.backendapp.tours.dto.request.accommodation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourPackageAccommodationRequest {
    @Schema(description = "The ID of the accommodation", required = true, example = "1")
    private Long accommodationID;
    @Schema(description = "The ID of the room category", required = true, example = "1")
    private Long roomCategoryID;
    @Schema(description = "The ID of the room type", required = true, example = "1")
    private Long roomTypeID;
    @Schema(description = "Whether the traveler may share this room with another fellow traveller", required = true, example = "true")
    private Boolean isShareable;
    @Schema(description = "Number of beds in the room", required = true, example = "1")
    private Integer bedCount;
    @Schema(description = "The type of bed provided in this room", required = false, example = "Queen")
    private String bedConfiguration;

    @Schema(description = "Number of person that will stay in this room", required = true, example = "2")
    private Integer forPersons;
}
