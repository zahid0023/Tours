package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.entities.DestinationLocationEntity;
import com.ghuddy.backendapp.tours.exception.LocationNotFoundException;

public interface LocationService {
    DestinationLocationEntity findLocationEntityByID(Long locationID) throws LocationNotFoundException;
}
