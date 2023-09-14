package com.example.ghuddytour2.tours.dto.response;

import com.example.ghuddytour2.tours.dto.request.activity.ActivityTypeRequest;
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
