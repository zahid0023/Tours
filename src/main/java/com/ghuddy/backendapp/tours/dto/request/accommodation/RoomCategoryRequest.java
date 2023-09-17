package com.ghuddy.backendapp.tours.dto.request.accommodation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RoomCategoryRequest {
    @Schema(description = "The name of the room category", required = true, example = "Standard")
    private String roomCategoryName;
    @Schema(description = "A short description of the room category", example = "A short description")
    private String description;
}
