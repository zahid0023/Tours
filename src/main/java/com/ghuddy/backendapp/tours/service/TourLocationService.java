package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.entities.TourLocationEntity;

public interface TourLocationService {
    TourLocationEntity getAddedTourByID(Long addedTourID);
}
