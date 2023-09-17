package com.ghuddy.backendapp.tours.dto.request.accommodation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RoomTypeRequest {
    @Schema(description = "The name of the room type", required = true, example = "Couple")
    private String roomTypeName;
    @Schema(description = "A short description of the room type", required = false, example = "A short description")
    private String description;
}
