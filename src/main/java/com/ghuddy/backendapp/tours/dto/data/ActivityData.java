package com.ghuddy.backendapp.tours.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ActivityData {
    @JsonProperty("activity_id")
    private Long activityId;
    @JsonProperty("activity_name")
    private String activityName;
    @JsonProperty("short_location")
    private String shortLocation;
    @JsonProperty("activity_type")
    private String activityType;
}
