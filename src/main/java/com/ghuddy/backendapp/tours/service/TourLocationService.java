package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.model.entities.TourLocationEntity;

public interface TourLocationService {
    TourLocationEntity getAddedTourByID(Long addedTourID);
}
