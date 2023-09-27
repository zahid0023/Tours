package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.tour.TourCreateRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.data.tour.CreatedTourData;
import com.ghuddy.backendapp.tours.model.entities.TourEntity;

public interface TourService {
    InsertAcknowledgeResponse<CreatedTourData> createTour(TourCreateRequest tourCreateRequest) throws TourNotFoundException;

    TourEntity getCreatedTourEntityById(Long createdTourId) throws TourNotFoundException;

    CreatedTourData getCreatedTourByCreatedTourId(Long createdTourEntityId) throws TourNotFoundException;

}
