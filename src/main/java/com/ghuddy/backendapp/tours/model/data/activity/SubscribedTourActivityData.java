package com.ghuddy.backendapp.tours.model.data.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.SubscribedTourItineraryEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class SubscribedTourActivityData {
    @Schema(description = "The id of the activity", example = "1")
    @JsonProperty("activity_id")
    private Long activityId;
    @Schema(description = "The name of the activity", example = "Hiking in Keokradang")
    @JsonProperty("activity_name")
    private String activityName;

    @Schema(description = "The short address of the activity")
    @JsonProperty("short_address")
    private String shortAddress;
    @Schema(description = "The day number on which the activity will be provided", example = "1")
    @JsonProperty("activity_day_number")
    private Integer dayNumber;
    @Schema(description = "The start time of the activity on the day", example = "09:00:00")
    @JsonProperty("activity_start_time")
    private LocalTime startTime;
    @Schema(description = "the end time of the activity on the day", example = "12:00:00")
    @JsonProperty("activity_end_time")
    private LocalTime endTime;

    public SubscribedTourActivityData(SubscribedTourItineraryEntity subscribedTourItineraryEntity) {
        this.activityId = subscribedTourItineraryEntity.getActivityEntity().getId();
        this.activityName = subscribedTourItineraryEntity.getActivityEntity().getActivityName();
        this.shortAddress = subscribedTourItineraryEntity.getActivityEntity().getShortLocation();
        this.dayNumber = subscribedTourItineraryEntity.getActivityDayNumber();
        this.startTime = subscribedTourItineraryEntity.getActivityStartTime();
        this.endTime = subscribedTourItineraryEntity.getActivityEndTime();
    }
}
