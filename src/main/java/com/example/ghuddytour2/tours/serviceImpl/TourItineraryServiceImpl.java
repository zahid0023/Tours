package com.example.ghuddytour2.tours.serviceImpl;

import com.example.ghuddytour2.tours.dto.data.TourActivityData;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.TourEntity;
import com.example.ghuddytour2.tours.entities.TourItineraryEntity;
import com.example.ghuddytour2.tours.repository.TourItineraryRepository;
import com.example.ghuddytour2.tours.service.ActivityService;
import com.example.ghuddytour2.tours.service.TourItineraryService;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public AcknowledgeResponse setTourActivities(TourEntity tourEntity, List<TourActivityData> tourActivities) {
        List<TourItineraryEntity> tourItineraryEntities = tourActivities.stream()
                .map(tourActivityData -> {
                    TourItineraryEntity tourItineraryEntity = new TourItineraryEntity();
                    tourItineraryEntity.setTourEntity(tourEntity);
                    tourItineraryEntity.setActivity(activityService.getActivity(tourActivityData.getActivityID()));
                    tourItineraryEntity.setDayNumber(tourActivityData.getDayNumber());
                    tourItineraryEntity.setStartTime(tourActivityData.getStartTime());
                    tourItineraryEntity.setEndTime(tourActivityData.getEndTime());
                    return tourItineraryEntity;
                })
                .collect(Collectors.toList());
        tourItineraryRepository.saveAll(tourItineraryEntities);

        return new AcknowledgeResponse();
    }
}
