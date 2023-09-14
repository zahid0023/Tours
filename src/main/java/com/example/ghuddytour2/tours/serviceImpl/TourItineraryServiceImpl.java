package com.example.ghuddytour2.tours.serviceImpl;

import com.example.ghuddytour2.tours.dto.request.activity.TourActivityRequest;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.ActivityEntity;
import com.example.ghuddytour2.tours.entities.TourEntity;
import com.example.ghuddytour2.tours.entities.TourItineraryEntity;
import com.example.ghuddytour2.tours.repository.TourItineraryRepository;
import com.example.ghuddytour2.tours.service.ActivityService;
import com.example.ghuddytour2.tours.service.TourItineraryService;
import com.example.ghuddytour2.tours.utils.EntityUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TourItineraryServiceImpl implements TourItineraryService {
    private final TourItineraryRepository tourItineraryRepository;
    private final ActivityService activityService;

    public TourItineraryServiceImpl(TourItineraryRepository tourItineraryRepository, ActivityService activityService) {
        this.tourItineraryRepository = tourItineraryRepository;
        this.activityService = activityService;
    }

    @Override
    public List<TourItineraryEntity> setTourActivities(TourEntity tourEntity, List<TourActivityRequest> tourActivities) {

        Set<Long> activityIDs = tourActivities.stream()
                .map(TourActivityRequest::getActivityID)
                .collect(Collectors.toSet());
        Map<Long, ActivityEntity> activityEntityMap = activityService.getActivityEntityMapByIDs(activityIDs);
        List<TourItineraryEntity> tourItineraryEntities = tourActivities.stream()
                .map(tourActivityRequest -> {
                    TourItineraryEntity tourItineraryEntity = new TourItineraryEntity();
                    tourItineraryEntity.setTourEntity(tourEntity);
                    tourItineraryEntity.setActivity(activityEntityMap.get(tourActivityRequest.getActivityID()));
                    tourItineraryEntity.setDayNumber(tourActivityRequest.getDayNumber());
                    tourItineraryEntity.setStartTime(tourActivityRequest.getStartTime());
                    tourItineraryEntity.setEndTime(tourActivityRequest.getEndTime());
                    return tourItineraryEntity;
                })
                .collect(Collectors.toList());

        return tourItineraryEntities;
    }
}
