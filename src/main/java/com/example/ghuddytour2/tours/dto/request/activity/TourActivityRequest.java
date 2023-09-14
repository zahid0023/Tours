package com.example.ghuddytour2.tours.dto.request.activity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalTime;

@Data
public class TourActivityRequest {
    @Schema(description = "The unique identifier of the activity related to the tour.", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long activityID;

    @Schema(description = "The day number of the activity related to the tour.", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer dayNumber;

    @Schema(description = "The start time of the activity related to the tour.", example = "09:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalTime startTime;

    @Schema(description = "The end time of the activity related to the tour.", example = "17:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalTime endTime;
}
