package com.example.ghuddytour2.tours.serviceImpl;

import com.example.ghuddytour2.tours.dto.request.ActivityAddRequest;
import com.example.ghuddytour2.tours.dto.request.ActivityListAddRequest;
import com.example.ghuddytour2.tours.dto.request.ActivityTypeAddRequest;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.ActivityEntity;
import com.example.ghuddytour2.tours.entities.ActivityImageEntity;
import com.example.ghuddytour2.tours.entities.ActivityTypeEntity;
import com.example.ghuddytour2.tours.repository.ActivityRepository;
import com.example.ghuddytour2.tours.repository.ActivityTypeRepository;
import com.example.ghuddytour2.tours.service.ActivityService;
import com.example.ghuddytour2.tours.service.ImageService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityTypeRepository activityTypeRepository;
    private final ActivityRepository activityRepository;
    private final ImageService imageService;

    public ActivityServiceImpl(ActivityTypeRepository activityTypeRepository,
                               ActivityRepository activityRepository,
                               ImageService imageService) {
        this.activityTypeRepository = activityTypeRepository;
        this.activityRepository = activityRepository;
        this.imageService = imageService;
    }

    @Override
    public AcknowledgeResponse addActivityTypes(ActivityTypeAddRequest activityTypeAddRequest) {
        List<ActivityTypeEntity> activityTypeEntities = activityTypeAddRequest.getActivityTypes().stream()
                .map(activityTypeData -> {
                    ActivityTypeEntity activityTypeEntity = new ActivityTypeEntity();
                    activityTypeEntity.setActivityTypeName(activityTypeData.getActivityTypeName());
                    activityTypeEntity.setDescription(activityTypeData.getDescription());
                    return activityTypeEntity;
                })
                .collect(Collectors.toList());
        activityTypeRepository.saveAll(activityTypeEntities);
        return new AcknowledgeResponse();
    }

    @Override
    public ActivityTypeEntity getActivityType(Long activityTypeID) {
        return activityTypeRepository.findById(activityTypeID).orElseThrow(() -> new EntityNotFoundException("ActivityTypeEntity Not Found"));
    }

    @Transactional
    @Override
    public AcknowledgeResponse addActivity(ActivityAddRequest activityAddRequest) {
        ActivityTypeEntity activityTypeEntity = getActivityType(activityAddRequest.getActivityTypeID());
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setActivityName(activityAddRequest.getActivityName());
        activityEntity.setActivityTypeEntity(activityTypeEntity);
        activityEntity.setShortLocation(activityAddRequest.getShortLocation());

        activityEntity = activityRepository.save(activityEntity);
        imageService.addActivityImages(activityEntity, activityAddRequest.getActivityImages());

        return new AcknowledgeResponse();
    }

    @Transactional
    @Override
    public AcknowledgeResponse addActivities(ActivityListAddRequest activityListAddRequest) {
        List<ActivityEntity> activityEntities = activityListAddRequest.getActivityAddRequests().stream()
                .map(activityAddRequest -> {
                    ActivityTypeEntity activityTypeEntity = getActivityType(activityAddRequest.getActivityTypeID());
                    ActivityEntity activityEntity = new ActivityEntity();
                    activityEntity.setActivityName(activityAddRequest.getActivityName());
                    activityEntity.setActivityTypeEntity(activityTypeEntity);
                    activityEntity.setShortLocation(activityAddRequest.getShortLocation());
                    List<ActivityImageEntity> activityImageEntities = activityAddRequest.getActivityImages().stream()
                            .map(imageData -> {
                                ActivityImageEntity activityImageEntity = new ActivityImageEntity();
                                activityImageEntity.setFileName(imageData.getImageFileName());
                                activityImageEntity.setImageUrl(imageData.getImageURL());
                                activityImageEntity.setCaption(imageData.getImageCaption());
                                activityImageEntity.setActivityEntity(activityEntity); // Establish the relationship here
                                return activityImageEntity;
                            })
                            .collect(Collectors.toList());
                    activityEntity.setActivityImageEntities(activityImageEntities);
                    return activityEntity;
                })
                .collect(Collectors.toList());

        // Save the activities along with their images
        activityRepository.saveAll(activityEntities);

        return new AcknowledgeResponse();
    }


    @Override
    public ActivityEntity getActivity(Long activityID) {
        return activityRepository.findById(activityID).orElseThrow(() -> new EntityNotFoundException("ActivityEntity not found"));
    }
}
