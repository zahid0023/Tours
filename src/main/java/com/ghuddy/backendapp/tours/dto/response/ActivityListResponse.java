package com.ghuddy.backendapp.tours.dto.response;

import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.dto.data.ActivityData;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class ActivityListResponse extends BaseResponse {
    private List<ActivityData> activities = new LinkedList<>();

    public ActivityListResponse(List<ActivityData> activities) {
        this.activities = activities;
    }
}
