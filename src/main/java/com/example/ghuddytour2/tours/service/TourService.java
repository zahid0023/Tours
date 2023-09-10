package com.example.ghuddytour2.tours.service;

import com.example.ghuddytour2.tours.dto.data.ImageData;
import com.example.ghuddytour2.tours.dto.request.TourAddRequest;
import com.example.ghuddytour2.tours.dto.request.TourAvailabilityRequest;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.dto.response.TourResponseList;
import com.example.ghuddytour2.tours.entities.TourEntity;
import com.example.ghuddytour2.tours.exception.ActivityNotFoundException;
import com.example.ghuddytour2.tours.exception.EmptyListException;
import com.example.ghuddytour2.tours.exception.LocationNotFoundException;
import com.example.ghuddytour2.tours.exception.TourNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.EntityNotFoundException;

public interface TourService {
    AcknowledgeResponse addTour(TourAddRequest tourAddRequest) throws LocationNotFoundException, DataIntegrityViolationException;

    TourEntity getTourEntityByID(Long tourID) throws EntityNotFoundException, TourNotFoundException;

    TourResponseList getAllTours() throws EmptyListException;

    AcknowledgeResponse generateTourAvailability(TourAvailabilityRequest tourAvailabilityRequest) throws ActivityNotFoundException, TourNotFoundException;

}
