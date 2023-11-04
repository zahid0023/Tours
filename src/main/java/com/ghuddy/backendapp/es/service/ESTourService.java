package com.ghuddy.backendapp.es.service;

import com.ghuddy.backendapp.es.dto.ESSubscribedTourResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;

public interface ESTourService {
    ESSubscribedTourResponse getSubscribedTourById(Long subscribedTourId, String requestId) throws TourNotFoundException, EmptyListException;
}
