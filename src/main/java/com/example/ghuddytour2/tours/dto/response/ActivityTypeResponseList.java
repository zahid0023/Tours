package com.example.ghuddytour2.tours.dto.response;

import com.example.ghuddytour2.tours.dto.data.ActivityTypeData;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class ActivityTypeResponseList {
    private List<ActivityTypeData> activityTypes = new LinkedList<>();

    public ActivityTypeResponseList(List<ActivityTypeData> activityTypes) {
        this.activityTypes = activityTypes;
    }
}
