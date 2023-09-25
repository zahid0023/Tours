package com.ghuddy.backendapp.tours.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourRoomTypeData {
    @Schema(description = "The id of the tour room type", example = "1")
    @JsonProperty("tour_room_type_id")
    private Long tourRoomTypeId;
    @Schema(description = "The name of the tour room type", example = "Couple Room")
    @JsonProperty("tour_room_type_name")
    private String tourRoomTypeName;
    @Schema(description = "The description of the tour room type", example = "A description of tour room type")
    @JsonProperty("tour_room_type_description")
    private String tourRoomTypeDescription;
}
