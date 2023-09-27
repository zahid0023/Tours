package com.ghuddy.backendapp.tours.dto.request.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalTime;

@Data
public class TourActivityRequest{
    @Schema(description = "The unique identifier of the activity related to the tour.", example = "1", required = true)
    @JsonProperty("activity_id")
    private Long activityID;

    @Schema(description = "The day number of the activity related to the tour.", example = "1", required = true)
    @JsonProperty("day_number")
    private Integer dayNumber;

    @Schema(description = "The start time of the activity related to the tour.", example = "09:00", required = true, format = "time")
    @JsonProperty("start_time")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @Schema(description = "The end time of the activity related to the tour.", example = "17:00", required = true, format = "time")
    @JsonProperty("end_time")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;

}
