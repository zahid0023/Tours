package com.ghuddy.backendapp.tours.dto.request.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import com.ghuddy.backendapp.tours.dto.request.activity.TourActivityRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;


@Data
public class TourCreateRequest extends BaseRequest {
    @Schema(description = "The unique identifier of the tour.", example = "1", required = true)
    @JsonProperty("tour_id")
    private Long tourID; // basically tourLocationID

    @Schema(description = "A title that goes with the tour", example = "Relaxing Tour For All age in Bandarban", required = true)
    @JsonProperty("tour_title")
    private String title;
    @Schema(description = "A description of the tour", example = "", required = true)
    @JsonProperty("tour_description")
    private String description;

    @Schema(description = "The url of the thumb image")
    @JsonProperty("thumb_image_url")
    private String thumbImageURL;

    @Schema(description = "The list of activities associated with the tour.", required = true)
    @JsonProperty("tour_activities")
    private List<TourActivityRequest> tourActivities;

    @Schema(description = "The list of specialities associated with the tour.", required = true)
    @JsonProperty("tour_specialities")
    private List<TourSpecialityRequest> tourSpecialities;

    @Override
    public void validate() throws AbstractException {

    }
}




