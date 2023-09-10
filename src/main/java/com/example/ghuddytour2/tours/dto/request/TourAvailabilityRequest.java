package com.example.ghuddytour2.tours.dto.request;

import com.example.ghuddytour2.tours.dto.data.ImageData;
import com.example.ghuddytour2.tours.dto.data.TourActivityData;
import com.example.ghuddytour2.tours.dto.data.TourSpecialityData;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;


@Data
public class TourAvailabilityRequest {
    @Schema(description = "The unique identifier of the tour.", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long tourID;

    @Schema(description = "The list of activities associated with the tour.", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<TourActivityData> tourActivities;

    @Schema(description = "The list of specialities associated with the tour.")
    private List<TourSpecialityData> tourSpecialities;

    @Schema(description = "The list of images associated with the tour.")
    private List<ImageData> images;
}




