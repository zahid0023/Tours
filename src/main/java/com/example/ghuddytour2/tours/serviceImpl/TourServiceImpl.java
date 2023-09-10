package com.example.ghuddytour2.tours.serviceImpl;

import com.example.ghuddytour2.enums.ErrorCode;
import com.example.ghuddytour2.tours.dao.TourDAO;
import com.example.ghuddytour2.tours.dto.request.TourAddRequest;
import com.example.ghuddytour2.tours.dto.request.TourAvailabilityRequest;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.dto.response.TourResponseList;
import com.example.ghuddytour2.tours.entities.*;
import com.example.ghuddytour2.tours.exception.ActivityNotFoundException;
import com.example.ghuddytour2.tours.exception.EmptyListException;
import com.example.ghuddytour2.tours.exception.LocationNotFoundException;
import com.example.ghuddytour2.tours.exception.TourNotFoundException;
import com.example.ghuddytour2.tours.repository.TourAvailabilityRepository;
import com.example.ghuddytour2.tours.repository.TourImageRepository;
import com.example.ghuddytour2.tours.repository.TourRepository;
import com.example.ghuddytour2.tours.repository.TourSpecialityRepository;
import com.example.ghuddytour2.tours.service.ActivityService;
import com.example.ghuddytour2.tours.service.LocationService;
import com.example.ghuddytour2.tours.service.TourService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;
    private final LocationService locationService;
    private final ActivityService activityService;
    private final TourDAO tourDAO;
    private final TourSpecialityRepository tourSpecialityRepository;
    private final TourImageRepository tourImageRepository;
    private final TourAvailabilityRepository tourAvailabilityRepository;

    public TourServiceImpl(TourRepository tourRepository,
                           LocationService locationService,
                           TourDAO tourDAO,
                           ActivityService activityService,
                           TourSpecialityRepository tourSpecialityRepository,
                           TourImageRepository tourImageRepository,
                           TourAvailabilityRepository tourAvailabilityRepository) {
        this.tourRepository = tourRepository;
        this.locationService = locationService;
        this.tourDAO = tourDAO;
        this.activityService = activityService;
        this.tourSpecialityRepository = tourSpecialityRepository;
        this.tourImageRepository = tourImageRepository;
        this.tourAvailabilityRepository = tourAvailabilityRepository;
    }

    @Override
    public AcknowledgeResponse addTour(TourAddRequest tourAddRequest) throws LocationNotFoundException, DataIntegrityViolationException {
        LocationEntity locationEntity = locationService.findLocationEntityByID(tourAddRequest.getLocationID());
        TourEntity tourEntity = new TourEntity(tourAddRequest);
        tourEntity.setDestinationLocation(locationEntity);
        tourRepository.save(tourEntity);
        return new AcknowledgeResponse();
    }

    @Override
    public TourEntity getTourEntityByID(Long tourID) throws EntityNotFoundException, TourNotFoundException {
        return tourRepository.findById(tourID).orElseThrow(() -> new TourNotFoundException(ErrorCode.TOUR_NOT_FOUND));
    }

    @Override
    public TourResponseList getAllTours() throws EmptyListException {
        return tourDAO.getAllTours();
    }

    @Override
    public AcknowledgeResponse generateTourAvailability(TourAvailabilityRequest tourAvailabilityRequest)
            throws TourNotFoundException {
        // Retrieve the tour entity by ID
        TourEntity tourEntity = getTourEntityByID(tourAvailabilityRequest.getTourID());

        List<String> errorMessages = new LinkedList<>();

        // Use Stream to process TourActivityData objects
        List<TourAvailabilityEntity> tourAvailabilityEntities = tourAvailabilityRequest.getTourActivities()
                .stream()
                .map(tourActivityData -> {
                    try {
                        // Retrieve the activity entity by ID
                        ActivityEntity activityEntity = activityService.getActivityEntityByID(tourActivityData.getActivityID());

                        // Create and return a new TourAvailabilityEntity
                        TourAvailabilityEntity tourAvailabilityEntity = new TourAvailabilityEntity();
                        tourAvailabilityEntity.setTour(tourEntity);
                        tourAvailabilityEntity.setActivity(activityEntity);
                        tourAvailabilityEntity.setDayNumber(tourActivityData.getDayNumber());
                        tourAvailabilityEntity.setStartTime(tourActivityData.getStartTime());
                        tourAvailabilityEntity.setEndTime(tourActivityData.getEndTime());

                        return tourAvailabilityEntity;
                    } catch (ActivityNotFoundException ex) {
                        // Handle the exception and add an error message
                        String errorMessage = "Activity with ID " + tourActivityData.getActivityID() + " does not exist. Please add the activity first.";
                        errorMessages.add(errorMessage);
                        return null; // Return null for skipped activities
                    }
                })
                .filter(Objects::nonNull) // Filter out null entries (skipped activities)
                .collect(Collectors.toList()); // Collect the valid TourAvailabilityEntity objects

        List<TourImageEntity> tourImageEntities = tourAvailabilityRequest.getImages()
                .stream()
                .map((imageData) -> {
                    TourImageEntity tourImageEntity = new TourImageEntity();
                    tourImageEntity.setTour(tourEntity);
                    tourImageEntity.setFileName(imageData.getImageFileName());
                    tourImageEntity.setImageUrl(imageData.getImageURL());
                    return tourImageEntity;
                })
                .collect(Collectors.toList());

        List<TourSpecialityEntity> tourSpecialityEntities = tourAvailabilityRequest.getTourSpecialities()
                .stream()
                .map(tourSpecialityData -> {
                    TourSpecialityEntity tourSpecialityEntity = new TourSpecialityEntity();
                    tourSpecialityEntity.setTour(tourEntity);
                    tourSpecialityEntity.setTitle(tourSpecialityData.getTourSpecialityTitle());
                    tourSpecialityEntity.setDescription(tourSpecialityData.getTourSpecialityDescription());
                    tourSpecialityEntity.setIconUrl(tourSpecialityData.getTourSpecialityIconURL());
                    return tourSpecialityEntity;
                })
                .collect(Collectors.toList());

        try {
            tourSpecialityRepository.saveAll(tourSpecialityEntities);
            tourImageRepository.saveAll(tourImageEntities);
            tourAvailabilityRepository.saveAll(tourAvailabilityEntities);
        } catch (Exception ex) {
            // Handle the exception if any of the saves fail
            // Log the exception or take appropriate action

            ex.printStackTrace();
            // Rollback the transaction
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

            // Create an AcknowledgeResponse with an error message
            return new AcknowledgeResponse("Failed to save data. Rolling back transaction.", ErrorCode.DATA_SAVE_ERROR.getCode());
        }

        // Create an AcknowledgeResponse and include error messages if any
        if (!errorMessages.isEmpty()) {
            String status = errorMessages.stream()
                    .collect(Collectors.joining("\n"));
            return new AcknowledgeResponse(status, ErrorCode.ACTIVITY_NOT_FOUND.getCode());
        }

        return new AcknowledgeResponse();
    }


}
