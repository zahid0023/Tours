package com.example.ghuddytour2.tours.service;

import com.example.ghuddytour2.tours.dto.request.activity.TourActivityRequest;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.TourEntity;
import com.example.ghuddytour2.tours.entities.TourItineraryEntity;

import java.util.List;

public interface TourItineraryService {
    List<TourItineraryEntity> setTourActivities(TourEntity tourEntity, List<TourActivityRequest> tourActivities);
}
