package com.example.ghuddytour2.tours.service;

import com.example.ghuddytour2.tours.dto.data.TourActivityData;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.TourEntity;

import java.util.List;

public interface TourItineraryService {
    AcknowledgeResponse setTourActivities(TourEntity tourEntity, List<TourActivityData> tourActivities);
}
