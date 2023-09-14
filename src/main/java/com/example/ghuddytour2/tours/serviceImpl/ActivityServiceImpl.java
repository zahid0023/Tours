package com.example.ghuddytour2.tours.serviceImpl;

import com.example.ghuddytour2.tours.dto.request.activity.*;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.ActivityEntity;
import com.example.ghuddytour2.tours.entities.ActivityImageEntity;
import com.example.ghuddytour2.tours.entities.ActivityTypeEntity;
import com.example.ghuddytour2.tours.repository.ActivityRepository;
import com.example.ghuddytour2.tours.repository.ActivityTypeRepository;
import com.example.ghuddytour2.tours.service.ActivityService;
import com.example.ghuddytour2.tours.service.ImageService;
import com.example.ghuddytour2.tours.utils.EntityUtil;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    public AcknowledgeResponse addActivityType(ActivityTypeAddRequest activityTypeAddRequest) {
        return addActivityTypes(List.of(activityTypeAddRequest.getActivityType()));
    }

    @Override
    public AcknowledgeResponse addActivityTypes(ActivityTypeListAddRequest activityTypeListAddRequest) {
        return addActivityTypes(activityTypeListAddRequest.getActivityTypes());
    }

    private AcknowledgeResponse addActivityTypes(List<ActivityTypeRequest> activities) {
        List<ActivityTypeEntity> activityTypeEntities = activities.stream()
                .map(activityTypeRequest -> {
                    ActivityTypeEntity activityTypeEntity = new ActivityTypeEntity();
                    activityTypeEntity.setActivityTypeName(activityTypeRequest.getActivityTypeName());
                    activityTypeEntity.setDescription(activityTypeRequest.getDescription());
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

    @Override
    public AcknowledgeResponse addActivity(ActivityAddRequest activityAddRequest) {
        return addActivities(List.of(activityAddRequest.getActivity()));
    }

    @Override
    public AcknowledgeResponse addActivities(ActivityListAddRequest activityListAddRequest) {
        return addActivities(activityListAddRequest.getActivities());
    }

    private AcknowledgeResponse addActivities(List<ActivityRequest> activities) {
        Set<Long> activityTypeIDs = activities.stream()
                .map(ActivityRequest::getActivityTypeID)
                .collect(Collectors.toSet());
        Map<Long, ActivityTypeEntity> activityTypeEntityMap = EntityUtil.findEntitiesByIds(activityTypeIDs, activityTypeRepository, ActivityTypeEntity::getId, "ActivityTypeEntity");
        List<ActivityEntity> activityEntities = activities.stream()
                .map(activityRequest -> {
                    ActivityEntity activityEntity = new ActivityEntity();
                    List<ActivityImageEntity> activityImageEntities = activityRequest.getActivityImages().stream()
                            .map(imageRequest -> {
                                ActivityImageEntity activityImageEntity = new ActivityImageEntity();
                                activityImageEntity.setActivityEntity(activityEntity);
                                activityImageEntity.setFileName(imageRequest.getFileName());
                                activityImageEntity.setImageUrl(imageRequest.getImageURL());
                                activityImageEntity.setImageUrl(imageRequest.getImageURL());
                                activityImageEntity.setCaption(imageRequest.getImageCaption());
                                return activityImageEntity;
                            })
                            .collect(Collectors.toList());
                    activityEntity.setActivityTypeEntity(activityTypeEntityMap.get(activityRequest.getActivityTypeID()));
                    activityEntity.setActivityName(activityRequest.getActivityName());
                    activityEntity.setShortLocation(activityRequest.getShortLocation());
                    activityEntity.setActivityImageEntities(activityImageEntities);
                    return activityEntity;
                })
                .collect(Collectors.toList());
        activityRepository.saveAll(activityEntities);
        return new AcknowledgeResponse();
    }

    @Override
    public ActivityEntity getActivity(Long activityID) {
        return activityRepository.findById(activityID).orElseThrow(() -> new EntityNotFoundException("ActivityEntity not found"));
    }

    @Override
    public Map<Long, ActivityEntity> getActivityEntityMapByIDs(Set<Long> activityIDs) {
        return EntityUtil.findEntitiesByIds(activityIDs, activityRepository, ActivityEntity::getId, "ActivityEntity");
    }
}
