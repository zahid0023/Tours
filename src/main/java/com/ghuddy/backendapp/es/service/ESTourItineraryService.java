package com.ghuddy.backendapp.es.service;

import com.ghuddy.backendapp.es.dto.data.ESSubscribedTourItineraryData;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.entities.SubscribedTourEntity;

import java.util.List;

public interface ESTourItineraryService {
    List<ESSubscribedTourItineraryData> getESTourItineraryBySubscribedTourEntity(SubscribedTourEntity subscribedTourEntity, String requestId) throws EmptyListException;
}
