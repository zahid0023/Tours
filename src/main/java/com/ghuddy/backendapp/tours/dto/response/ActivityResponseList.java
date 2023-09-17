package com.ghuddy.backendapp.tours.dto.response;

import com.ghuddy.backendapp.tours.dto.data.ActivityData;
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
