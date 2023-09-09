package com.example.ghuddytour2.tours.serviceImpl;

import com.example.ghuddytour2.enums.ErrorCode;
import com.example.ghuddytour2.tours.entities.LocationEntity;
import com.example.ghuddytour2.tours.exception.LocationNotFoundException;
import com.example.ghuddytour2.tours.repository.LocationRepository;
import com.example.ghuddytour2.tours.service.LocationService;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public LocationEntity findLocationEntityByID(Long locationID) throws LocationNotFoundException {
        LocationEntity locationEntity = locationRepository.findLocationEntityById(locationID)
                .orElseThrow(() -> new LocationNotFoundException(ErrorCode.LOCATION_NOT_FOUND));
        return locationEntity;
    }
}
