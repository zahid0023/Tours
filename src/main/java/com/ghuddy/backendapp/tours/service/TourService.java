package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.tour.TourCreateRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tour.TourDataResponseList;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.data.tour.TourData;
import com.ghuddy.backendapp.tours.model.entities.TourEntity;

public interface TourService {
    InsertAcknowledgeResponse<TourData> createTour(TourCreateRequest tourCreateRequest) throws TourNotFoundException;

    TourEntity getCreatedTourEntityById(Long createdTourId) throws TourNotFoundException;

    TourData getCreatedTourByCreatedTourId(Long createdTourEntityId) throws TourNotFoundException;

    TourDataResponseList getAllCreatedTours(String requestId) throws EmptyListException;

    TourDataResponseList getAllCreatedToursPaginated(Integer pageSize, Integer pageNumber,String requestId) throws EmptyListException;

}
