package com.ghuddy.backendapp.tours.dto.response;

import com.ghuddy.backendapp.tours.dto.request.activity.ActivityTypeRequest;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class ActivityTypeResponseList {
    private List<ActivityTypeRequest> activityTypes = new LinkedList<>();

    public ActivityTypeResponseList(List<ActivityTypeRequest> activityTypes) {
        this.activityTypes = activityTypes;
    }
}
