package com.example.ghuddytour2.tours.serviceImpl;

import com.example.ghuddytour2.tours.dto.request.TourAddRequest;
import com.example.ghuddytour2.tours.dto.request.TourCreateRequest;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.*;
import com.example.ghuddytour2.tours.exception.LocationNotFoundException;
import com.example.ghuddytour2.tours.repository.TourLocationRepository;
import com.example.ghuddytour2.tours.repository.TourRepository;
import com.example.ghuddytour2.tours.service.*;
import com.example.ghuddytour2.tours.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class TourServiceImpl implements TourService {

    private final TourLocationRepository tourLocationRepository;
    private final TourLocationService tourLocationService;
    private final SpecialityService specialityService;
    private final TourItineraryService tourItineraryService;


    public TourServiceImpl(TourLocationRepository tourLocationRepository,
                           TourLocationService tourLocationService,
                           SpecialityService specialityService,
                           LocationService locationService,
                           TourRepository tourRepository,
                           TourItineraryService tourItineraryService) {
        this.tourLocationRepository = tourLocationRepository;
        this.tourLocationService = tourLocationService;
        this.specialityService = specialityService;
        this.locationService = locationService;
        this.tourRepository = tourRepository;
        this.tourItineraryService = tourItineraryService;
    }


    private final LocationService locationService;
    private final TourRepository tourRepository;

    @Override
    public AcknowledgeResponse addTour(TourAddRequest tourAddRequest) throws LocationNotFoundException, DataIntegrityViolationException {
        LocationEntity locationEntity = locationService.findLocationEntityByID(tourAddRequest.getLocationID());
        TourLocationEntity tourLocationEntity = new TourLocationEntity();
        tourLocationEntity.setTourName(locationEntity.getName() + " " + "Tour");
        tourLocationEntity.setDestinationLocationEntity(locationEntity);
        tourLocationEntity.setNumberOfDays(tourAddRequest.getNumberOfDays());
        tourLocationEntity.setNumberOfNights(tourAddRequest.getNumberOfNights());
        tourLocationEntity.setShortAddress(tourAddRequest.getShortAddress());
        tourLocationEntity.setTourTag(StringUtil.tagify(locationEntity.getName(), tourAddRequest.getShortAddress()));
        tourLocationRepository.save(tourLocationEntity); // tour is added!!
        return new AcknowledgeResponse();
    }


    @Transactional
    @Override
    public AcknowledgeResponse createTour(TourCreateRequest tourCreateRequest) {
        TourLocationEntity tourLocationEntity = tourLocationService.getAddedTourByID(tourCreateRequest.getTourID());

        TourEntity tourEntity = new TourEntity();
        tourEntity.setTitle(tourCreateRequest.getTitle());
        tourEntity.setDescription(tourCreateRequest.getDescription());
        tourEntity.setThumbImageUrl(tourCreateRequest.getThumbImageURL());
        tourEntity.setTourLocation(tourLocationEntity);
        tourEntity = tourRepository.save(tourEntity);

        tourItineraryService.setTourActivities(tourEntity, tourCreateRequest.getTourActivities());
        specialityService.setTourSpecialities(tourEntity, tourCreateRequest.getTourSpecialities());

        return new AcknowledgeResponse();
    }

}
