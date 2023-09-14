package com.example.ghuddytour2.tours.dto.request.tour;

import com.example.ghuddytour2.tours.dto.request.activity.TourActivityRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;


@Data
public class TourCreateRequest {
    @Schema(description = "The unique identifier of the tour.", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("tour_id")
    private Long tourID; // basically tourLocationID

    @Schema(description = "A title that goes with the tour", example = "Relaxing Tour For All age in Bandarban", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("tour_title")
    private String title;
    @Schema(description = "A description of the tour", example = "", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("tour_description")
    private String description;
    @JsonProperty("thumb_image_url")
    private String thumbImageURL;

    @Schema(description = "The list of activities associated with the tour.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("tour_activities")
    private List<TourActivityRequest> tourActivities;

    @Schema(description = "The list of specialities associated with the tour.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("tour_specialities")
    private List<TourSpecialityRequest> tourSpecialities;


}




