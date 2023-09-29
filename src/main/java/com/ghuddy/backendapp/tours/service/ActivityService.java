package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.activity.ActivityAddRequest;
import com.ghuddy.backendapp.tours.dto.request.activity.ActivityListAddRequest;
import com.ghuddy.backendapp.tours.dto.request.activity.ActivityTypeAddRequest;
import com.ghuddy.backendapp.tours.dto.request.activity.ActivityTypeListAddRequest;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.activity.ActivityListResponse;
import com.ghuddy.backendapp.tours.dto.response.activity.ActivityTypeListResponse;
import com.ghuddy.backendapp.tours.model.entities.ActivityEntity;
import com.ghuddy.backendapp.tours.model.entities.ActivityTypeEntity;
import com.ghuddy.backendapp.tours.exception.EmptyListException;

import java.util.Map;
import java.util.Set;

public interface ActivityService {

    // Activity Type
    InsertAcknowledgeResponse addActivityType(ActivityTypeAddRequest activityTypeAddRequest);

    InsertAcknowledgeListResponse addActivityTypes(ActivityTypeListAddRequest activityTypeListAddRequest);

    ActivityTypeEntity getActivityType(Long activityTypeID);

    ActivityTypeListResponse getAllActivityTypes(String requestId) throws EmptyListException;

    ActivityTypeListResponse getAllActivityTypesPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException;

    // Activity
    InsertAcknowledgeResponse addActivity(ActivityAddRequest activityAddRequest);

    InsertAcknowledgeListResponse addActivities(ActivityListAddRequest activityListAddRequest);

    ActivityEntity getActivity(Long ActivityID);

    Map<Long, ActivityEntity> getActivityEntityMapByIDs(Set<Long> activityIDs);

    ActivityListResponse getAllActivities(String requestId) throws EmptyListException;

    ActivityListResponse getAllActivitiesPaginated(int pageSize, int pageNumber, String requestId) throws EmptyListException;
}
