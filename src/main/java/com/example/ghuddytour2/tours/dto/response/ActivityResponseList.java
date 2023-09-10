package com.example.ghuddytour2.tours.dto.response;

import com.example.ghuddytour2.tours.dto.data.ActivityData;
import com.example.ghuddytour2.tours.dto.data.ActivityTypeData;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class ActivityResponseList {
    private List<ActivityData> activities = new LinkedList<>();

    public ActivityResponseList(List<ActivityData> activities) {
        this.activities = activities;
    }
}
