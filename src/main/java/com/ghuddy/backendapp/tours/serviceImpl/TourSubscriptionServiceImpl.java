package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.model.UserEntity;
import com.ghuddy.backendapp.repository.MerchantRepository;
import com.ghuddy.backendapp.tours.dto.request.activity.SubscribedTourActivityRequest;
import com.ghuddy.backendapp.tours.dto.request.tour.TourSubscriptionRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tour.TourSubscriptionResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.data.tour.SubscribedTourData;
import com.ghuddy.backendapp.tours.model.entities.*;
import com.ghuddy.backendapp.tours.repository.SubscribedTourRepository;
import com.ghuddy.backendapp.tours.service.ActivityService;
import com.ghuddy.backendapp.tours.service.TourPackageService;
import com.ghuddy.backendapp.tours.service.TourService;
import com.ghuddy.backendapp.tours.service.TourSubscriptionService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TourSubscriptionServiceImpl implements TourSubscriptionService {
    private final TourService tourService;
    private final MerchantRepository merchantRepository;
    private final ActivityService activityService;
    private final SubscribedTourRepository subscribedTourRepository;
    private final TourPackageService tourPackageService;

    public TourSubscriptionServiceImpl(TourService tourService,
                                       MerchantRepository merchantRepository,
                                       ActivityService activityService,
                                       SubscribedTourRepository subscribedTourRepository,
                                       TourPackageService tourPackageService) {
        this.tourService = tourService;
        this.merchantRepository = merchantRepository;
        this.activityService = activityService;
        this.subscribedTourRepository = subscribedTourRepository;
        this.tourPackageService = tourPackageService;
    }

    /**
     * @param tourSubscriptionRequest the request of a merchant who wants to provide  a tour
     * @return SubscribedTourData
     */
    @Override
    public TourSubscriptionResponse subscribeTour(TourSubscriptionRequest tourSubscriptionRequest, String requestId) throws TourNotFoundException {
        SubscribedTourData subscribedTourData = saveSubscribedTour(tourSubscriptionRequest);
        return new TourSubscriptionResponse(subscribedTourData, requestId);
    }

    @Transactional
    public SubscribedTourData saveSubscribedTour(TourSubscriptionRequest tourSubscriptionRequest) throws TourNotFoundException {
        // Retrieve tour, merchant, and activity information
        TourEntity tourEntity = tourService.getCreatedTourEntityById(tourSubscriptionRequest.getTourId());
        UserEntity merchantEntity = merchantRepository.findById(tourSubscriptionRequest.getMerchantId())
                .orElseThrow(() -> new EntityNotFoundException("MerchantEntity Not Found"));

        Set<Long> activityIds = tourSubscriptionRequest.getSubscribedTourActivityList().stream()
                .map(SubscribedTourActivityRequest::getActivityId)
                .collect(Collectors.toSet());

        Map<Long, ActivityEntity> activityEntityMap = activityService.getActivityEntityMapByIDs(activityIds);

        // Create SubscribedTourEntity
        SubscribedTourEntity subscribedTourEntity = new SubscribedTourEntity();

        // Set properties of SubscribedTourEntity
        subscribedTourEntity.setTourEntity(tourEntity);
        subscribedTourEntity.setMerchantEntity(merchantEntity);
        subscribedTourEntity.setSubscribedTourItineraryEntities(setSubscribedTourItinerary(subscribedTourEntity, tourSubscriptionRequest.getSubscribedTourActivityList(), activityEntityMap));
        subscribedTourEntity.setTourStartDate(tourSubscriptionRequest.getTourStartDate());
        subscribedTourEntity.setTourEndDate(tourSubscriptionRequest.getTourEndDate());
        subscribedTourEntity.setTourReportingTime(tourSubscriptionRequest.getTourReportingTime());
        subscribedTourEntity.setTourReportingPlace(tourSubscriptionRequest.getTourReportingPlace());
        subscribedTourEntity.setTourPackageEntities(setSubscribedTourPackages(subscribedTourEntity, tourSubscriptionRequest.getTourPackageRequestList()));

        // Save SubscribedTourEntity
        subscribedTourEntity = subscribedTourRepository.save(subscribedTourEntity);

        // Return SubscribedTourData
        return new SubscribedTourData(subscribedTourEntity);
    }

    private List<SubscribedTourItineraryEntity> setSubscribedTourItinerary(SubscribedTourEntity subscribedTourEntity,
                                                                           List<SubscribedTourActivityRequest> subscribedTourActivityDataList,
                                                                           Map<Long, ActivityEntity> activityEntityMap) {

        // Create SubscribedTourItineraryEntities
        List<SubscribedTourItineraryEntity> subscribedTourItineraryEntities = subscribedTourActivityDataList.stream()
                .map(subscribedTourActivityRequest -> {
                    SubscribedTourItineraryEntity subscribedTourItineraryEntity = new SubscribedTourItineraryEntity();

                    subscribedTourItineraryEntity.setSubscribedTourEntity(subscribedTourEntity);
                    subscribedTourItineraryEntity.setActivityEntity(activityEntityMap.get(subscribedTourActivityRequest.getActivityId()));
                    subscribedTourItineraryEntity.setActivityDayNumber(subscribedTourActivityRequest.getDayNumber());
                    subscribedTourItineraryEntity.setActivityStartTime(subscribedTourActivityRequest.getStartTime());
                    subscribedTourItineraryEntity.setActivityEndTime(subscribedTourActivityRequest.getEndTime());
                    subscribedTourItineraryEntity.setIsActive(true);

                    return subscribedTourItineraryEntity;
                })
                .collect(Collectors.toList());

        return subscribedTourItineraryEntities;
    }

    /**
     * @param id
     * @return
     * @throws TourNotFoundException
     */
    @Override
    public SubscribedTourEntity getSubscribedTourEntityById(Long id) throws TourNotFoundException {
        return subscribedTourRepository.findById(id).orElseThrow(() -> new TourNotFoundException(ErrorCode.TOUR_NOT_FOUND));
    }

    private List<TourPackageEntity> setSubscribedTourPackages(SubscribedTourEntity subscribedTourEntity, List<TourPackageRequest> tourPackageRequestList) {
        // create TourPackage entities
        List<TourPackageEntity> tourPackageEntities = tourPackageService.prepareTourPackages(subscribedTourEntity, tourPackageRequestList);
        return tourPackageEntities;
    }
}
