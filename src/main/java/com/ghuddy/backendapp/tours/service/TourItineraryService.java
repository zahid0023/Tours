package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.activity.TourActivityRequest;
import com.ghuddy.backendapp.tours.entities.TourEntity;
import com.ghuddy.backendapp.tours.entities.TourItineraryEntity;

import java.util.List;

public interface TourItineraryService {
    List<TourItineraryEntity> setTourActivities(TourEntity tourEntity, List<TourActivityRequest> tourActivities);
}
