package com.ghuddy.backendapp.tours.dto.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "tour description data")
public class TourDescriptionData {
    @Schema(required = true,
            description = "The title of the tour",
            example = "Bandarban Adventure: Immersing in Nature's Majestic Beauty")
    private String title;
    @Schema(required = true,
            description = "Provide a description outlining the tour experience.")
    private String description;
}
