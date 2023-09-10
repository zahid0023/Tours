package com.example.ghuddytour2.tours.service;

import com.example.ghuddytour2.tours.dto.request.ActivityAddRequest;
import com.example.ghuddytour2.tours.dto.request.ActivityTypeAddRequest;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.dto.response.ActivityResponseList;
import com.example.ghuddytour2.tours.dto.response.ActivityTypeResponseList;
import com.example.ghuddytour2.tours.entities.ActivityEntity;
import com.example.ghuddytour2.tours.entities.ActivityTypeEntity;
import com.example.ghuddytour2.tours.exception.ActivityNotFoundException;
import com.example.ghuddytour2.tours.exception.ActivityTypeNotFoundException;
import com.example.ghuddytour2.tours.exception.EmptyListException;
import org.springframework.dao.DataIntegrityViolationException;

public interface ActivityService {
    AcknowledgeResponse addActivityType(ActivityTypeAddRequest activityTypeAddRequest) throws DataIntegrityViolationException;

    ActivityTypeEntity getActivityTypeEntityByID(Long activityTypeID) throws ActivityTypeNotFoundException;

    ActivityTypeResponseList getAllActivityTypes() throws EmptyListException;

    AcknowledgeResponse addActivity(ActivityAddRequest activityAddRequest) throws DataIntegrityViolationException, ActivityTypeNotFoundException;

    ActivityResponseList getAllActivities() throws EmptyListException;

    ActivityEntity getActivityEntityByID(Long activityID) throws ActivityNotFoundException;


}
