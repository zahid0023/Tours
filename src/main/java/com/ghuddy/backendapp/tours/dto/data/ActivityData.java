package com.ghuddy.backendapp.tours.dto.data;

import lombok.Data;

@Data
public class ActivityData {
    private Long activityID;
    private String activityName;
    private String shortLocation;
    private String activityType;
}
