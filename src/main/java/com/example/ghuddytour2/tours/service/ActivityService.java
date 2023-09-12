package com.example.ghuddytour2.tours.service;

import com.example.ghuddytour2.tours.dto.request.ActivityAddRequest;
import com.example.ghuddytour2.tours.dto.request.ActivityListAddRequest;
import com.example.ghuddytour2.tours.dto.request.ActivityTypeAddRequest;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.ActivityEntity;
import com.example.ghuddytour2.tours.entities.ActivityTypeEntity;

public interface ActivityService {

    // Activity Type
    AcknowledgeResponse addActivityTypes(ActivityTypeAddRequest activityTypeAddRequest);

    ActivityTypeEntity getActivityType(Long activityTypeID);

    // Activity
    AcknowledgeResponse addActivity(ActivityAddRequest activityAddRequest);

    ActivityEntity getActivity(Long ActivityID);

    AcknowledgeResponse addActivities(ActivityListAddRequest activityListAddRequest);

}
