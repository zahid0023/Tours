package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dao.TourDAO;
import com.ghuddy.backendapp.tours.dto.request.tour.TourAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tour.TourCreateRequest;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.TourResponseList;
import com.ghuddy.backendapp.tours.entities.*;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.LocationNotFoundException;
import com.ghuddy.backendapp.tours.repository.TourLocationRepository;
import com.ghuddy.backendapp.tours.repository.TourRepository;
import com.ghuddy.backendapp.tours.service.*;
import com.ghuddy.backendapp.tours.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class TourServiceImpl implements TourService {

    private final TourLocationRepository tourLocationRepository;
    private final TourLocationService tourLocationService;
    private final SpecialityService specialityService;
    private final TourItineraryService tourItineraryService;
    private final TourDAO tourDAO;


    public TourServiceImpl(TourLocationRepository tourLocationRepository,
                           TourLocationService tourLocationService,
                           SpecialityService specialityService,
                           LocationService locationService,
                           TourRepository tourRepository,
                           TourItineraryService tourItineraryService,
                           TourDAO tourDAO) {
        this.tourLocationRepository = tourLocationRepository;
        this.tourLocationService = tourLocationService;
        this.specialityService = specialityService;
        this.locationService = locationService;
        this.tourRepository = tourRepository;
        this.tourItineraryService = tourItineraryService;
        this.tourDAO = tourDAO;
    }

    private final LocationService locationService;
    private final TourRepository tourRepository;

    @Override
    public AcknowledgeResponse addTour(TourAddRequest tourAddRequest) throws LocationNotFoundException, DataIntegrityViolationException {
        DestinationLocationEntity destinationLocationEntity = locationService.findLocationEntityByID(tourAddRequest.getLocationID());
        TourLocationEntity tourLocationEntity = new TourLocationEntity();
        tourLocationEntity.setTourName(destinationLocationEntity.getName() + " " + "Tour");
        tourLocationEntity.setDestinationLocationEntity(destinationLocationEntity);
        tourLocationEntity.setNumberOfDays(tourAddRequest.getNumberOfDays());
        tourLocationEntity.setNumberOfNights(tourAddRequest.getNumberOfNights());
        tourLocationEntity.setShortAddress(tourAddRequest.getShortAddress());
        tourLocationEntity.setTourTag(StringUtil.tagify(destinationLocationEntity.getName(), tourAddRequest.getShortAddress()));
        tourLocationRepository.save(tourLocationEntity); // tour is added!!
        return new AcknowledgeResponse();
    }


    @Transactional
    @Override
    public AcknowledgeResponse createTour(TourCreateRequest tourCreateRequest) {
        TourLocationEntity tourLocationEntity = tourLocationService.getAddedTourByID(tourCreateRequest.getTourID());

        TourEntity tourEntity = new TourEntity();
        tourEntity.setTourName("Bandarban Tour");
        tourEntity.setTitle(tourCreateRequest.getTitle());
        tourEntity.setDescription(tourCreateRequest.getDescription());
        tourEntity.setThumbImageUrl(tourCreateRequest.getThumbImageURL());
        tourEntity.setTourLocation(tourLocationEntity);

        List<TourItineraryEntity> tourItineraryEntities = tourItineraryService.setTourActivities(tourEntity, tourCreateRequest.getTourActivities());
        tourEntity.setTourItineraryEntities(tourItineraryEntities);
        List<TourSpecialityEntity> tourSpecialityEntities = specialityService.setTourSpecialities(tourEntity, tourCreateRequest.getTourSpecialities());
        tourEntity.setTourSpecialityEntities(tourSpecialityEntities);

        tourRepository.save(tourEntity);
        return new AcknowledgeResponse();
    }

    @Override
    public TourEntity getTourByTourID(Long tourID) {
        return tourRepository.findById(tourID).orElseThrow(() -> new EntityNotFoundException("TourEntity Not Found"));
    }

    @Override
    public TourResponseList getAllTours() throws EmptyListException {
        return tourDAO.getAllTours();
    }

    @Override
    public TourResponseList getAllToursPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException {
        return tourDAO.getAllToursPaginated(pageSize, pageNumber);
    }
}
