package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dto.request.activity.TourActivityRequest;
import com.ghuddy.backendapp.tours.entities.ActivityEntity;
import com.ghuddy.backendapp.tours.entities.TourEntity;
import com.ghuddy.backendapp.tours.entities.TourItineraryEntity;
import com.ghuddy.backendapp.tours.repository.TourItineraryRepository;
import com.ghuddy.backendapp.tours.service.ActivityService;
import com.ghuddy.backendapp.tours.service.TourItineraryService;
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
