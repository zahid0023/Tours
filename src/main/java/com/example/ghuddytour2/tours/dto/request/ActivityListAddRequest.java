package com.example.ghuddytour2.tours.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ActivityListAddRequest {
    List<ActivityAddRequest> activityAddRequests;
}
