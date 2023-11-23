package com.ghuddy.backendapp.tours.es.service;

import com.ghuddy.backendapp.tours.es.dto.response.ESSubscribedTourResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;

public interface ESTourService {
    ESSubscribedTourResponse getAllAvailableTourPackages(Long subscribedTourId, String requestId) throws TourNotFoundException, EmptyListException;

    Boolean indexTourByTourId(Long createdTourId, String requestId) throws TourNotFoundException;


}
