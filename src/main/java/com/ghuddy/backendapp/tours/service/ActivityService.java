package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.activity.ActivityAddRequest;
import com.ghuddy.backendapp.tours.dto.request.activity.ActivityListAddRequest;
import com.ghuddy.backendapp.tours.dto.request.activity.ActivityTypeAddRequest;
import com.ghuddy.backendapp.tours.dto.request.activity.ActivityTypeListAddRequest;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.activity.ActivityListResponse;
import com.ghuddy.backendapp.tours.dto.response.activity.ActivityTypeListResponse;
import com.ghuddy.backendapp.tours.model.entities.ActivityEntity;
import com.ghuddy.backendapp.tours.model.entities.ActivityTypeEntity;
import com.ghuddy.backendapp.tours.exception.EmptyListException;

import java.util.Map;
import java.util.Set;

public interface ActivityService {

    // Activity Type
    AcknowledgeResponse addActivityType(ActivityTypeAddRequest activityTypeAddRequest);

    AcknowledgeResponse addActivityTypes(ActivityTypeListAddRequest activityTypeListAddRequest);

    ActivityTypeEntity getActivityType(Long activityTypeID);

    ActivityTypeListResponse getAllActivityTypes() throws EmptyListException;

    ActivityTypeListResponse getAllActivityTypesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    // Activity
    AcknowledgeResponse addActivity(ActivityAddRequest activityAddRequest);

    AcknowledgeResponse addActivities(ActivityListAddRequest activityListAddRequest);

    ActivityEntity getActivity(Long ActivityID);

    Map<Long, ActivityEntity> getActivityEntityMapByIDs(Set<Long> activityIDs);

    ActivityListResponse getAllActivities() throws EmptyListException;

    ActivityListResponse getAllActivitiesPaginated(int pageSize, int pageNumber) throws EmptyListException;
}
