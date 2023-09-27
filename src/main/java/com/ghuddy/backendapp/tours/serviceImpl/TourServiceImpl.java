package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dto.request.tour.TourCreateRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.data.tour.CreatedTourData;
import com.ghuddy.backendapp.tours.model.entities.TourEntity;
import com.ghuddy.backendapp.tours.model.entities.TourItineraryEntity;
import com.ghuddy.backendapp.tours.model.entities.TourLocationEntity;
import com.ghuddy.backendapp.tours.model.entities.TourSpecialityEntity;
import com.ghuddy.backendapp.tours.repository.TourRepository;
import com.ghuddy.backendapp.tours.service.SpecialityService;
import com.ghuddy.backendapp.tours.service.TourItineraryService;
import com.ghuddy.backendapp.tours.service.TourLocationService;
import com.ghuddy.backendapp.tours.service.TourService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class TourServiceImpl implements TourService {
    private final SpecialityService specialityService;
    private final TourItineraryService tourItineraryService;
    private final TourRepository tourRepository;
    private final TourLocationService tourLocationService;

    public TourServiceImpl(SpecialityService specialityService,
                           TourItineraryService tourItineraryService,
                           TourRepository tourRepository,
                           TourLocationService tourLocationService) {
        this.specialityService = specialityService;
        this.tourItineraryService = tourItineraryService;
        this.tourRepository = tourRepository;
        this.tourLocationService = tourLocationService;
    }

    // created tour
    @Transactional
    @Override
    public InsertAcknowledgeResponse<CreatedTourData> createTour(TourCreateRequest tourCreateRequest) throws TourNotFoundException {
        TourLocationEntity tourLocationEntity = tourLocationService.getAddedTourEntityById(tourCreateRequest.getAddedTourID());

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
        return new InsertAcknowledgeResponse<>(new CreatedTourData(tourEntity), tourCreateRequest.getRequestId());
    }

    /**
     * @param createdTourId the of the created tour entity
     * @return TourEntity
     * @throws TourNotFoundException when the created tour entity by this id is not found
     */
    @Override
    public TourEntity getCreatedTourEntityById(Long createdTourId) throws TourNotFoundException {
        return tourRepository.findById(createdTourId).orElseThrow(() -> new TourNotFoundException(ErrorCode.TOUR_NOT_FOUND));
    }

    /**
     * @param createdTourEntityId the id of the created tour
     * @return CreatedTourData
     * @throws TourNotFoundException when the created tour by this id is not found
     */
    @Override
    public CreatedTourData getCreatedTourByCreatedTourId(Long createdTourEntityId) throws TourNotFoundException {
        return new CreatedTourData(getCreatedTourEntityById(createdTourEntityId));
    }
}
