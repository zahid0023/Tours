package com.ghuddy.backendapp.tours.dto.request.activity;

import lombok.Data;

import java.util.List;

@Data
public class ActivityTypeListAddRequest {
    private List<ActivityTypeRequest> activityTypes;
}
