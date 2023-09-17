package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.entities.TourLocationEntity;
import com.ghuddy.backendapp.tours.repository.TourLocationRepository;
import com.ghuddy.backendapp.tours.service.TourLocationService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class TourLocationServiceImpl implements TourLocationService {
    private final TourLocationRepository tourLocationRepository;

    public TourLocationServiceImpl(TourLocationRepository tourLocationRepository) {
        this.tourLocationRepository = tourLocationRepository;
    }

    @Override
    public TourLocationEntity getAddedTourByID(Long addedTourID) {
        return tourLocationRepository.findById(addedTourID).orElseThrow(() -> new EntityNotFoundException("TourLocationEntity Not Found!!"));
    }
}
