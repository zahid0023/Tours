package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.model.DestinationLocationEntity;
import com.ghuddy.backendapp.service.DestinationLocationService;
import com.ghuddy.backendapp.tours.dao.TourDAO;
import com.ghuddy.backendapp.tours.dto.request.tour.TourAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tour.TourCreateRequest;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tour.TourResponseList;
import com.ghuddy.backendapp.tours.model.data.tour.AddedTourDataOptimized;
import com.ghuddy.backendapp.tours.model.data.tour.CreatedTourData;
import com.ghuddy.backendapp.tours.model.entities.TourEntity;
import com.ghuddy.backendapp.tours.model.entities.TourItineraryEntity;
import com.ghuddy.backendapp.tours.model.entities.TourLocationEntity;
import com.ghuddy.backendapp.tours.model.entities.TourSpecialityEntity;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.LocationNotFoundException;
import com.ghuddy.backendapp.tours.repository.TourLocationRepository;
import com.ghuddy.backendapp.tours.repository.TourRepository;
import com.ghuddy.backendapp.tours.service.SpecialityService;
import com.ghuddy.backendapp.tours.service.TourItineraryService;
import com.ghuddy.backendapp.tours.service.TourLocationService;
import com.ghuddy.backendapp.tours.service.TourService;
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
    private final DestinationLocationService destinationLocationService;
    private final TourRepository tourRepository;

    public TourServiceImpl(TourLocationRepository tourLocationRepository,
                           TourLocationService tourLocationService,
                           SpecialityService specialityService,
                           DestinationLocationService destinationLocationService,
                           TourRepository tourRepository,
                           TourItineraryService tourItineraryService,
                           TourDAO tourDAO) {
        this.tourLocationRepository = tourLocationRepository;
        this.tourLocationService = tourLocationService;
        this.specialityService = specialityService;
        this.destinationLocationService = destinationLocationService;
        this.tourRepository = tourRepository;
        this.tourItineraryService = tourItineraryService;
        this.tourDAO = tourDAO;
    }

    @Override
    public InsertAcknowledgeResponse addTour(TourAddRequest tourAddRequest) throws LocationNotFoundException {
        DestinationLocationEntity destinationLocationEntity = destinationLocationService.getDestinationLocationEntityById(tourAddRequest.getLocationID());
        TourLocationEntity tourLocationEntity = new TourLocationEntity();
        tourLocationEntity.setTourName(StringUtil.tourName(destinationLocationEntity.getPlaceName(), tourAddRequest.getNumberOfDays(), tourAddRequest.getNumberOfNights()));
        tourLocationEntity.setDestinationLocationEntity(destinationLocationEntity);
        tourLocationEntity.setNumberOfDays(tourAddRequest.getNumberOfDays());
        tourLocationEntity.setNumberOfNights(tourAddRequest.getNumberOfNights());
        tourLocationEntity.setShortAddress(tourAddRequest.getShortAddress());
        tourLocationEntity.setTourTag(StringUtil.tagify(destinationLocationEntity.getPlaceName(), tourAddRequest.getShortAddress()));
        tourLocationEntity = tourLocationRepository.save(tourLocationEntity); // tour is added!!
        return new InsertAcknowledgeResponse(new AddedTourDataOptimized(tourLocationEntity), tourAddRequest.getRequestId());
    }

    @Transactional
    @Override
    public InsertAcknowledgeResponse createTour(TourCreateRequest tourCreateRequest) {
        TourLocationEntity tourLocationEntity = tourLocationService.getAddedTourByID(tourCreateRequest.getAddedTourID());

        TourEntity tourEntity = new TourEntity();
        tourEntity.setTitle(tourCreateRequest.getTitle());
        tourEntity.setDescription(tourCreateRequest.getDescription());
        tourEntity.setThumbImageUrl(tourCreateRequest.getThumbImageURL());
        tourEntity.setTourLocation(tourLocationEntity);

        List<TourItineraryEntity> tourItineraryEntities = tourItineraryService.setTourActivities(tourEntity, tourCreateRequest.getTourActivities());
        tourEntity.setTourItineraryEntities(tourItineraryEntities);

        List<TourSpecialityEntity> tourSpecialityEntities = specialityService.setTourSpecialities(tourEntity, tourCreateRequest.getTourSpecialities());
        tourEntity.setTourSpecialityEntities(tourSpecialityEntities);

        tourEntity = tourRepository.save(tourEntity);
        return new InsertAcknowledgeResponse(new CreatedTourData(tourEntity), tourCreateRequest.getRequestId());
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
