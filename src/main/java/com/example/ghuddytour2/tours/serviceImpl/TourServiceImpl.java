package com.example.ghuddytour2.tours.serviceImpl;

import com.example.ghuddytour2.tours.dto.request.TourAddRequest;
import com.example.ghuddytour2.tours.dto.response.TourAddResponse;
import com.example.ghuddytour2.tours.entities.LocationEntity;
import com.example.ghuddytour2.tours.entities.TourEntity;
import com.example.ghuddytour2.tours.exception.LocationNotFoundException;
import com.example.ghuddytour2.tours.repository.TourRepository;
import com.example.ghuddytour2.tours.service.LocationService;
import com.example.ghuddytour2.tours.service.TourService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;
    private final LocationService locationService;

    public TourServiceImpl(TourRepository tourRepository, LocationService locationService) {
        this.tourRepository = tourRepository;
        this.locationService = locationService;
    }

    @Override
    public TourAddResponse addTour(TourAddRequest tourAddRequest) throws LocationNotFoundException, DataIntegrityViolationException {
        LocationEntity locationEntity = locationService.findLocationEntityByID(tourAddRequest.getLocationID());
        TourEntity tourEntity = new TourEntity(tourAddRequest);
        tourEntity.setDestinationLocation(locationEntity);
        tourRepository.save(tourEntity);
        return new TourAddResponse("Success", "00000");
    }

    @Override
    public TourEntity getTourEntityByID() {
        return null;
    }
}
