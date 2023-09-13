package com.example.ghuddytour2.tours.service;

import com.example.ghuddytour2.tours.dto.request.TourAddRequest;
import com.example.ghuddytour2.tours.dto.request.TourCreateRequest;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.TourEntity;
import com.example.ghuddytour2.tours.exception.LocationNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;

public interface TourService {
    AcknowledgeResponse addTour(TourAddRequest tourAddRequest) throws LocationNotFoundException, DataIntegrityViolationException;

    AcknowledgeResponse createTour(TourCreateRequest tourCreateRequest);

    TourEntity getTourByTourID(Long tourID);
}
