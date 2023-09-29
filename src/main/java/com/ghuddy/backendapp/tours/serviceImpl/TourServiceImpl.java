package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dto.request.tour.TourCreateRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.data.tour.CreatedTourData;
import com.ghuddy.backendapp.tours.model.entities.TourEntity;
import com.ghuddy.backendapp.tours.model.entities.TourItineraryEntity;
import com.ghuddy.backendapp.tours.model.entities.AddedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.TourSpecialityEntity;
import com.ghuddy.backendapp.tours.repository.TourRepository;
import com.ghuddy.backendapp.tours.service.SpecialityService;
import com.ghuddy.backendapp.tours.service.TourItineraryService;
import com.ghuddy.backendapp.tours.service.TourLocationService;
import com.ghuddy.backendapp.tours.service.TourService;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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
    private final JdbcTemplate jdbcTemplate;

    public TourServiceImpl(SpecialityService specialityService,
                           TourItineraryService tourItineraryService,
                           TourRepository tourRepository,
                           TourLocationService tourLocationService,
                           JdbcTemplate jdbcTemplate) {
        this.specialityService = specialityService;
        this.tourItineraryService = tourItineraryService;
        this.tourRepository = tourRepository;
        this.tourLocationService = tourLocationService;
        this.jdbcTemplate = jdbcTemplate;
    }

    // created tour
    @Transactional
    @Override
    public InsertAcknowledgeResponse<CreatedTourData> createTour(TourCreateRequest tourCreateRequest) throws TourNotFoundException {
        AddedTourEntity addedTourEntity = tourLocationService.getAddedTourEntityById(tourCreateRequest.getAddedTourID());

        TourEntity tourEntity = new TourEntity();
        tourEntity.setTitle(tourCreateRequest.getTitle());
        tourEntity.setDescription(tourCreateRequest.getDescription());
        tourEntity.setThumbImageUrl(tourCreateRequest.getThumbImageURL());
        tourEntity.setAddedTourEntity(addedTourEntity);

        List<TourItineraryEntity> tourItineraryEntities = tourItineraryService.setTourActivities(tourEntity, tourCreateRequest.getTourActivities());
        tourEntity.setTourItineraryEntities(tourItineraryEntities);

        if (tourCreateRequest.getTourSpecialities() != null && !tourCreateRequest.getTourSpecialities().isEmpty()) {
            List<TourSpecialityEntity> tourSpecialityEntities = specialityService.setTourSpecialities(tourEntity, tourCreateRequest.getTourSpecialities());
            tourEntity.setTourSpecialityEntities(tourSpecialityEntities);
        } else {
            log.warn("No Speciality added!!");
        }

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

    /**
     * @param requestId
     * @return
     * @throws EmptyListException
     */
    @Override
    public List<CreatedTourData> getAllCreatedTours(String requestId) throws EmptyListException {
        String query = "";
        try {
            List<CreatedTourData> createdTourDataList = EntityUtil.getAllEntitiesPaginated(query, 0, 0, CreatedTourData.class, jdbcTemplate);
            return createdTourDataList;
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
    }

    /**
     * @param pageSize
     * @param pageNumber
     * @return
     * @throws EmptyListException
     */
    @Override
    public List<CreatedTourData> getAllCreatedToursPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException {
        return null;
    }
}
