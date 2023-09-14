package com.example.ghuddytour2.tours.dto.request.activity;

import com.example.ghuddytour2.tours.dto.request.activity.ActivityAddRequest;
import lombok.Data;

import java.util.List;

@Data
public class ActivityListAddRequest {
    List<ActivityRequest> activities;
}
