package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.entities.DestinationLocationEntity;
import com.ghuddy.backendapp.tours.exception.LocationNotFoundException;
import com.ghuddy.backendapp.tours.repository.LocationRepository;
import com.ghuddy.backendapp.tours.service.LocationService;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public DestinationLocationEntity findLocationEntityByID(Long locationID) throws LocationNotFoundException {
        DestinationLocationEntity destinationLocationEntity = locationRepository.findLocationEntityById(locationID)
                .orElseThrow(() -> new LocationNotFoundException(ErrorCode.LOCATION_NOT_FOUND));
        return destinationLocationEntity;
    }
}
