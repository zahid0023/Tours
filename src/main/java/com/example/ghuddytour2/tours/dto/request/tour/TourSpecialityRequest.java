package com.example.ghuddytour2.tours.dto.request.tour;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class TourSpecialityRequest {
    @Schema(description = "The title of the tour speciality.", example = "Adventure")
    private String tourSpecialityTitle;

    @Schema(description = "The description of the tour speciality.", example = "Exciting adventures for thrill-seekers.")
    private String tourSpecialityDescription;

    @Schema(description = "The URL of the icon representing the tour speciality.", example = "https://example.com/icons/adventure.png")
    private String tourSpecialityIconURL;

}



