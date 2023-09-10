package com.example.ghuddytour2.tours.serviceImpl;

import com.example.ghuddytour2.enums.ErrorCode;
import com.example.ghuddytour2.tours.dao.ActivityDAO;
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
import com.example.ghuddytour2.tours.repository.ActivityRepository;
import com.example.ghuddytour2.tours.repository.ActivityTypeRepository;
import com.example.ghuddytour2.tours.service.ActivityService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityTypeRepository activityTypeRepository;
    private final ActivityDAO activityDAO;
    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityTypeRepository activityTypeRepository, ActivityDAO activityDAO,
                               ActivityRepository activityRepository) {
        this.activityTypeRepository = activityTypeRepository;
        this.activityDAO = activityDAO;
        this.activityRepository = activityRepository;
    }

    @Override
    public AcknowledgeResponse addActivityType(ActivityTypeAddRequest activityTypeAddRequest) throws DataIntegrityViolationException {
        ActivityTypeEntity activityTypeEntity = new ActivityTypeEntity();
        activityTypeEntity.setActivityTypeName(activityTypeAddRequest.getActivityTypeName());
        activityTypeEntity.setDescription(activityTypeAddRequest.getDescription());
        activityTypeRepository.save(activityTypeEntity);
        return new AcknowledgeResponse();
    }

    @Override
    public ActivityTypeEntity getActivityTypeEntityByID(Long activityTypeID) throws ActivityTypeNotFoundException {
        ActivityTypeEntity activityTypeEntity = activityTypeRepository.findById(activityTypeID).orElseThrow(() -> new ActivityTypeNotFoundException(ErrorCode.ACTIVITY_TYPE_NOT_FOUND));
        return activityTypeEntity;
    }

    @Override
    public ActivityTypeResponseList getAllActivityTypes() throws EmptyListException {
        return activityDAO.getAllActivityTypes();
    }

    @Override
    public AcknowledgeResponse addActivity(ActivityAddRequest activityAddRequest) throws DataIntegrityViolationException, ActivityTypeNotFoundException {

        // check if ActivityTypeExists or not
        ActivityTypeEntity activityTypeEntity = getActivityTypeEntityByID(activityAddRequest.getActivityTypeID());

        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setActivityName(activityAddRequest.getActivityName());
        activityEntity.setActivityType(activityTypeEntity);
        activityEntity.setShortLocation(activityAddRequest.getShortLocation());
        activityRepository.save(activityEntity);
        return new AcknowledgeResponse();
    }

    @Override
    public ActivityResponseList getAllActivities() throws EmptyListException {
        return activityDAO.getAllActivities();
    }

    @Override
    public ActivityEntity getActivityEntityByID(Long activityID) throws ActivityNotFoundException {
        return activityRepository.findById(activityID).orElseThrow(() -> new ActivityNotFoundException(ErrorCode.ACTIVITY_NOT_FOUND));
    }
}
