package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.tour.TourAddRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tour.AddedTourResponseList;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.LocationNotFoundException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.data.tour.AddedTourDataOptimized;
import com.ghuddy.backendapp.tours.model.entities.TourLocationEntity;

public interface TourLocationService {
    InsertAcknowledgeResponse<AddedTourDataOptimized> addTour(TourAddRequest tourAddRequest) throws LocationNotFoundException;

    TourLocationEntity getAddedTourEntityById(Long addedTourEntityId) throws TourNotFoundException;

    AddedTourDataOptimized getAddedTourByAddedTourId(Long addedTourId) throws TourNotFoundException;

    AddedTourResponseList getAllAddedTours(String requestId) throws EmptyListException;

    AddedTourResponseList getAllAddedToursPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException;
}
