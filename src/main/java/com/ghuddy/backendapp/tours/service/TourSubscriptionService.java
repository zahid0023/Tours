package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.tour.TourSubscriptionRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tour.TourSubscriptionResponse;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.data.tour.SubscribedTourData;
import com.ghuddy.backendapp.tours.model.entities.SubscribedTourEntity;

import java.util.List;

public interface TourSubscriptionService {
    TourSubscriptionResponse subscribeTour(TourSubscriptionRequest tourSubscriptionRequest, String requestId) throws TourNotFoundException;

    SubscribedTourEntity getSubscribedTourEntityById(Long id) throws TourNotFoundException;
}
