package com.example.ghuddytour2.tours.service;

import com.example.ghuddytour2.tours.dto.request.activity.ActivityAddRequest;
import com.example.ghuddytour2.tours.dto.request.activity.ActivityListAddRequest;
import com.example.ghuddytour2.tours.dto.request.activity.ActivityTypeAddRequest;
import com.example.ghuddytour2.tours.dto.request.activity.ActivityTypeListAddRequest;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.ActivityEntity;
import com.example.ghuddytour2.tours.entities.ActivityTypeEntity;

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
