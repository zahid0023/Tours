package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.activity.TourActivityRequest;
import com.ghuddy.backendapp.tours.model.entities.TourEntity;
import com.ghuddy.backendapp.tours.model.entities.TourItineraryEntity;

import java.util.List;
import java.util.Set;

public interface TourItineraryService {
    List<TourItineraryEntity> setTourActivities(TourEntity tourEntity, Set<Long> tourActivityIds);
}
