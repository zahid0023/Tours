package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.activity.ActivityAddRequest;
import com.ghuddy.backendapp.tours.dto.request.activity.ActivityListAddRequest;
import com.ghuddy.backendapp.tours.dto.request.activity.ActivityTypeAddRequest;
import com.ghuddy.backendapp.tours.dto.request.activity.ActivityTypeListAddRequest;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.entities.ActivityEntity;
import com.ghuddy.backendapp.tours.entities.ActivityTypeEntity;

import java.util.Map;
import java.util.Set;

public interface ActivityService {

    // Activity Type
    AcknowledgeResponse addActivityType(ActivityTypeAddRequest activityTypeAddRequest);

    AcknowledgeResponse addActivityTypes(ActivityTypeListAddRequest activityTypeListAddRequest);

    ActivityTypeEntity getActivityType(Long activityTypeID);

    // Activity
    AcknowledgeResponse addActivity(ActivityAddRequest activityAddRequest);

    AcknowledgeResponse addActivities(ActivityListAddRequest activityListAddRequest);

    ActivityEntity getActivity(Long ActivityID);

    Map<Long, ActivityEntity> getActivityEntityMapByIDs(Set<Long> activityIDs);

}
