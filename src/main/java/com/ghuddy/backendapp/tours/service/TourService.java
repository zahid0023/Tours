package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.tour.TourAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tour.TourCreateRequest;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tour.TourResponseList;
import com.ghuddy.backendapp.tours.model.entities.TourEntity;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.LocationNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;

public interface TourService {
    InsertAcknowledgeResponse addTour(TourAddRequest tourAddRequest) throws LocationNotFoundException;

    InsertAcknowledgeResponse createTour(TourCreateRequest tourCreateRequest);

    TourEntity getTourByTourID(Long tourID);

    TourResponseList getAllTours() throws EmptyListException;

    TourResponseList getAllToursPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;
}
