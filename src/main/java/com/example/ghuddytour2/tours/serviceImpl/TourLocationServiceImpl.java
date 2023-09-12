package com.example.ghuddytour2.tours.serviceImpl;

import com.example.ghuddytour2.tours.entities.TourLocationEntity;
import com.example.ghuddytour2.tours.repository.TourLocationRepository;
import com.example.ghuddytour2.tours.service.TourLocationService;
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
