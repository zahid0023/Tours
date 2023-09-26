package com.ghuddy.backendapp.tours.model.data.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourRoomCategoryData {
    @Schema(description = "The id of room category related to a tour accommodation", required = true, example = "1")
    @JsonProperty("tour_room_category_id")
    private Long tourRoomCategoryId;
    @JsonProperty("tour_room_category_name")
    @Schema(description = "The name of the tour category", required = true, example = "Standard")
    private String tourRoomCategoryName;
    @Schema(description = "The description of the room description", required = true, example = "A short description")
    @JsonProperty("tour_room_category_description")
    private String tourRoomCategoryDescription;
}
