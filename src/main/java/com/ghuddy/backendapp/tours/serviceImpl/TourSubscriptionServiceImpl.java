package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.model.UserEntity;
import com.ghuddy.backendapp.repository.UserRepository;
import com.ghuddy.backendapp.tours.dao.TourDAO;
import com.ghuddy.backendapp.tours.dto.request.activity.SubscribedTourActivityRequest;
import com.ghuddy.backendapp.tours.dto.request.tour.TourSubscriptionRequest;
import com.ghuddy.backendapp.tours.dto.response.AddressResponse;
import com.ghuddy.backendapp.tours.dto.response.tour.SubscribedTourCardDataListResponse;
import com.ghuddy.backendapp.tours.dto.response.tour.SubscribedTourListResponse;
import com.ghuddy.backendapp.tours.dto.response.tour.TourSubscriptionResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.enums.SubscribedTourFilterCriteria;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.MerchantNotFoundException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.data.tour.SubscribedTourData;
import com.ghuddy.backendapp.tours.model.entities.activity.ActivityEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.TourEntity;
import com.ghuddy.backendapp.tours.repository.SubscribedTourRepository;
import com.ghuddy.backendapp.tours.service.ActivityService;
import com.ghuddy.backendapp.tours.service.TourService;
import com.ghuddy.backendapp.tours.service.TourSubscriptionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TourSubscriptionServiceImpl implements TourSubscriptionService {
    private final TourService tourService;
    private final UserRepository merchantRepository;
    private final ActivityService activityService;
    private final SubscribedTourRepository subscribedTourRepository;
    private final TourDAO tourDAO;

    public TourSubscriptionServiceImpl(TourService tourService,
                                       UserRepository merchantRepository,
                                       ActivityService activityService,
                                       SubscribedTourRepository subscribedTourRepository,
                                       TourDAO tourDAO) {
        this.tourService = tourService;
        this.merchantRepository = merchantRepository;
        this.activityService = activityService;
        this.subscribedTourRepository = subscribedTourRepository;
        this.tourDAO = tourDAO;
    }


    /**
     * @param tourSubscriptionRequest the DTO for tour subscription
     * @param requestId               to keep track of the request served by this service
     * @return TourSubscriptionResponse
     * @throws TourNotFoundException when the tour for this id is not found to be subscribed
     */
    @Override
    public TourSubscriptionResponse subscribeTour(TourSubscriptionRequest tourSubscriptionRequest, String requestId) throws TourNotFoundException, EntityNotFoundException {
        TourEntity tourEntity = tourService.getCreatedTourEntityById(tourSubscriptionRequest.getTourId());
        UserEntity userEntity = merchantRepository.findById(tourSubscriptionRequest.getMerchantId()).orElseThrow(() -> new EntityNotFoundException("MerchantEntity Not Found"));
        SubscribedTourData subscribedTourData = saveSubscribedTours(tourEntity, userEntity, tourSubscriptionRequest);
        return new TourSubscriptionResponse(subscribedTourData, requestId);
    }

    @Transactional
    public SubscribedTourData saveSubscribedTours(TourEntity tourEntity,
                                                  UserEntity merchantEntity,
                                                  TourSubscriptionRequest tourSubscriptionRequest) {

        SubscribedTourEntity subscribedTourEntity = prepareSubscribedTourEntity(tourEntity, merchantEntity, tourSubscriptionRequest);
        subscribedTourEntity = subscribedTourRepository.save(subscribedTourEntity);

        return new SubscribedTourData(subscribedTourEntity);
    }

    private SubscribedTourEntity prepareSubscribedTourEntity(TourEntity tourEntity,
                                                             UserEntity merchantEntity,
                                                             TourSubscriptionRequest tourSubscriptionRequest) {

        Set<Long> activityIds = tourSubscriptionRequest.getSubscribedTourActivityList().stream()
                .map(SubscribedTourActivityRequest::getActivityId)
                .collect(Collectors.toSet());

        Map<Long, ActivityEntity> activityEntityMap = activityService.getActivityEntityMapByIDs(activityIds);

        // Create SubscribedTourEntity
        SubscribedTourEntity subscribedTourEntity = new SubscribedTourEntity();

        // Set properties of SubscribedTourEntity
        subscribedTourEntity.setTourEntity(tourEntity);
        subscribedTourEntity.setMerchantEntity(merchantEntity);
        subscribedTourEntity.setSubscribedTourItineraryEntities(activityService.setSubscribedTourItinerary(subscribedTourEntity, tourSubscriptionRequest.getSubscribedTourActivityList(), activityEntityMap));
        subscribedTourEntity.setTourReportingTime(tourSubscriptionRequest.getTourReportingTime());
        subscribedTourEntity.setTourReportingPlace(tourSubscriptionRequest.getTourReportingPlace());

        return subscribedTourEntity;
    }

    /**
     * @param id the id of the subscribed tour
     * @return SubscribedTourEntity
     * @throws TourNotFoundException when the subscribed tour for this id is not found
     */
    @Override
    public SubscribedTourEntity getSubscribedTourEntityById(Long id) throws TourNotFoundException {
        return subscribedTourRepository.findById(id).orElseThrow(() -> new TourNotFoundException(ErrorCode.TOUR_NOT_FOUND));
    }

    /**
     * @param merchantId the id of the merchant/user
     * @return SubscribedTourListResponse
     */
    @Override
    public SubscribedTourListResponse getAllSubscribedToursByMerchantId(Long merchantId, String requestId) throws EmptyListException {
        List<SubscribedTourData> subscribedTourDataList = tourDAO.getAllSubscribedToursForMerchant(0, 0, merchantId);
        if (subscribedTourDataList == null || subscribedTourDataList.isEmpty())
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new SubscribedTourListResponse(subscribedTourDataList, requestId);
    }

    /**
     * @param pageSize   the size of the page
     * @param pageNumber the number of the page
     * @param merchantId the id of the merchant
     * @return SubscribedTourListResponse
     */
    @Override
    public SubscribedTourListResponse getAllSubscribedToursPaginatedByMerchantId(Integer pageSize, Integer pageNumber, Long merchantId, String requestId) throws EmptyListException {
        List<SubscribedTourData> subscribedTourDataList = tourDAO.getAllSubscribedToursForMerchant(pageSize, pageNumber, merchantId);
        if (subscribedTourDataList == null || subscribedTourDataList.isEmpty())
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new SubscribedTourListResponse(subscribedTourDataList, requestId);
    }

    /**
     * @param merchantId
     * @param subscribedTourId
     * @return
     */
    @Override
    public AddressResponse getSubscribedTourRelatedAddress(Long merchantId, Long subscribedTourId, String requestId) throws TourNotFoundException {
        SubscribedTourEntity subscribedTourEntity = getSubscribedTourEntityById(subscribedTourId);
        List<String> addresses = new LinkedList<>();
        addresses.add(subscribedTourEntity.getTourEntity().getAddedTourEntity().getShortAddress());
        subscribedTourEntity.getSubscribedTourItineraryEntities().stream()
                .forEach(subscribedTourItineraryEntity -> addresses.add(subscribedTourItineraryEntity.getActivityEntity().getShortLocation()));
        addresses.add(subscribedTourEntity.getTourReportingPlace());
        return new AddressResponse(addresses, requestId);
    }

    /**
     * @param merchantId
     * @param requestId
     * @return
     * @throws EmptyListException
     */
    @Override
    public SubscribedTourCardDataListResponse getAllSubscribedTourCardDataByMerchantId(Long merchantId, SubscribedTourFilterCriteria subscribedTourFilterCriteria, String requestId) throws EmptyListException, MerchantNotFoundException {
        UserEntity merchantEntity = merchantRepository.findById(merchantId).orElseThrow(() -> new MerchantNotFoundException(ErrorCode.MERCHANT_NOT_FOUND));
        List<SubscribedTourEntity> subscribedTourEntityList = subscribedTourRepository.getSubscribedTourEntitiesByMerchantEntityAndDeleted(merchantEntity, false);

        if (subscribedTourEntityList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);

        List<SubscribedTourData> subscribedTourDataList = getSubscribedTourDataList(subscribedTourFilterCriteria, subscribedTourEntityList);

        return new SubscribedTourCardDataListResponse(subscribedTourDataList, requestId, 1, Long.valueOf(subscribedTourDataList.size()));
    }

    /**
     * @param merchantId
     * @param pageNumber
     * @param pageSize
     * @param requestId
     * @return
     * @throws EmptyListException
     */
    @Override
    public SubscribedTourCardDataListResponse getAllSubscribedTourCardDataPaginatedByMerchantId(Long merchantId, SubscribedTourFilterCriteria subscribedTourFilterCriteria, Integer pageNumber, Integer pageSize, String requestId) throws EmptyListException, MerchantNotFoundException {
        UserEntity merchantEntity = merchantRepository.findById(merchantId).orElseThrow(() -> new MerchantNotFoundException(ErrorCode.MERCHANT_NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);

        Page<SubscribedTourEntity> page = subscribedTourRepository.getSubscribedTourEntitiesByMerchantEntityAndDeleted(merchantEntity, false, pageRequest);
        List<SubscribedTourEntity> subscribedTourEntityList = page.getContent();

        if (subscribedTourEntityList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        List<SubscribedTourData> subscribedTourDataList = getSubscribedTourDataList(subscribedTourFilterCriteria, subscribedTourEntityList);
        return new SubscribedTourCardDataListResponse(subscribedTourDataList, requestId, page.getTotalPages(), (long) page.getNumberOfElements());
    }


    private List<SubscribedTourData> getSubscribedTourDataList(SubscribedTourFilterCriteria subscribedTourFilterCriteria, List<SubscribedTourEntity> subscribedTourEntityList) throws EmptyListException {
        List<SubscribedTourData> subscribedTourDataList = null;

        if (subscribedTourFilterCriteria.equals(SubscribedTourFilterCriteria.SUBSCRIBED_TOUR_ALL))
            subscribedTourDataList = getSubscribedTourCardDataList(subscribedTourEntityList);
        else if (subscribedTourFilterCriteria.equals(SubscribedTourFilterCriteria.SUBSCRIBED_TOUR_WITHOUT_ANY_PACKAGE))
            subscribedTourDataList = getSubscribedTourCardDataListWithoutAnyPackage(subscribedTourEntityList);
        else if (subscribedTourFilterCriteria.equals(SubscribedTourFilterCriteria.SUBSCRIBED_TOUR_WITH_PACKAGE))
            subscribedTourDataList = getSubscribedTourCardDataListWithPackage(subscribedTourEntityList);
        if (subscribedTourDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return subscribedTourDataList;
    }

    private List<SubscribedTourData> getSubscribedTourCardDataList(List<SubscribedTourEntity> subscribedTourEntityList) {
        return subscribedTourEntityList.stream()
                .map(subscribedTourEntity -> new SubscribedTourData(subscribedTourEntity))
                .toList();
    }

    private List<SubscribedTourData> getSubscribedTourCardDataListWithoutAnyPackage(List<SubscribedTourEntity> subscribedTourEntityList) {
        return subscribedTourEntityList.stream()
                .filter(subscribedTourEntity -> subscribedTourEntity.getTourPackageEntities().isEmpty())
                .map(subscribedTourEntity -> new SubscribedTourData(subscribedTourEntity))
                .toList();
    }

    private List<SubscribedTourData> getSubscribedTourCardDataListWithPackage(List<SubscribedTourEntity> subscribedTourEntityList) {
        return subscribedTourEntityList.stream()
                .filter(subscribedTourEntity -> !subscribedTourEntity.getTourPackageEntities().isEmpty())
                .map(subscribedTourEntity -> new SubscribedTourData(subscribedTourEntity))
                .toList();
    }
}
