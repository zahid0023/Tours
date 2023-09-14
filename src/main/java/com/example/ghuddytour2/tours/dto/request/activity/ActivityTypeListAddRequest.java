package com.example.ghuddytour2.tours.dto.request.activity;

import lombok.Data;

import java.util.List;

@Data
public class ActivityTypeListAddRequest {
    private List<ActivityTypeRequest> activityTypes;
}
