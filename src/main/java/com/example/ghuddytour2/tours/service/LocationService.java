package com.example.ghuddytour2.tours.service;

import com.example.ghuddytour2.tours.entities.LocationEntity;
import com.example.ghuddytour2.tours.exception.LocationNotFoundException;

public interface LocationService {
    LocationEntity findLocationEntityByID(Long locationID) throws LocationNotFoundException;
}
