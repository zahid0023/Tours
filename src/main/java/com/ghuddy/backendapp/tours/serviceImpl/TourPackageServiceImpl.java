package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dao.TourPackageDao;
import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.food.FoodOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.food.MealPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageTypeAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageTypeListAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageTypeRequest;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferPackageRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageTypeListResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageData;
import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageTypeData;
import com.ghuddy.backendapp.tours.model.entities.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.TourPackageTypeEntity;
import com.ghuddy.backendapp.tours.repository.TourPackageRepository;
import com.ghuddy.backendapp.tours.repository.TourPackageTypeRepository;
import com.ghuddy.backendapp.tours.service.*;
import com.ghuddy.backendapp.tours.utils.CombinationGenerator;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import com.ghuddy.backendapp.tours.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TourPackageServiceImpl implements TourPackageService {
    private final TourPackageTypeRepository tourPackageTypeRepository;
    private final TourPackageRepository tourPackageRepository;
    private final FoodService foodService;
    private final AccommodationService accommodationService;
    private final TransportationService transportationService;
    private final TourPackageDao tourPackageDao;
    private final TourPackagePriceService tourPackagePriceService;
    private final TransferService transferService;

    public TourPackageServiceImpl(TourPackageTypeRepository tourPackageTypeRepository,
                                  TourPackageRepository tourPackageRepository,
                                  FoodService foodService,
                                  AccommodationService accommodationService,
                                  TransportationService transportationService,
                                  TourPackageDao tourPackageDao,
                                  TourPackagePriceService tourPackagePriceService,
                                  TransferService transferService) {
        this.tourPackageTypeRepository = tourPackageTypeRepository;
        this.tourPackageRepository = tourPackageRepository;
        this.foodService = foodService;
        this.accommodationService = accommodationService;
        this.transportationService = transportationService;
        this.tourPackageDao = tourPackageDao;
        this.tourPackagePriceService = tourPackagePriceService;
        this.transferService = transferService;
    }

    // tour package type
    @Override
    public InsertAcknowledgeResponse addTourPackageType(TourPackageTypeAddRequest tourPackageTypeAddRequest) {
        TourPackageTypeData tourPackageTypeData = addTourPackageTypes(List.of(tourPackageTypeAddRequest.getTourPackageTypeRequest())).get(0);
        return new InsertAcknowledgeResponse(tourPackageTypeData, tourPackageTypeAddRequest.getRequestId());
    }

    @Override
    public InsertAcknowledgeListResponse addTourPackageTypes(TourPackageTypeListAddRequest tourPackageTypeListAddRequest) {
        List<TourPackageTypeData> tourPackageTypeDataList = addTourPackageTypes(tourPackageTypeListAddRequest.getTourPackageTypes());
        return new InsertAcknowledgeListResponse(tourPackageTypeDataList, tourPackageTypeListAddRequest.getRequestId());
    }

    private List<TourPackageTypeData> addTourPackageTypes(List<TourPackageTypeRequest> tourPackages) {
        List<TourPackageTypeEntity> tourPackageTypeEntities = tourPackages.stream()
                .map(tourPackageTypeAddRequest -> {
                    TourPackageTypeEntity tourPackageTypeEntity = new TourPackageTypeEntity();
                    tourPackageTypeEntity.setPackageTypeName(tourPackageTypeAddRequest.getTourPackageTypeName());
                    tourPackageTypeEntity.setDescription(tourPackageTypeAddRequest.getTourPackageTypeDescription());
                    tourPackageTypeEntity.setSuitableFor(tourPackageTypeAddRequest.getSuitableForPersons());
                    return tourPackageTypeEntity;
                })
                .collect(Collectors.toList());
        return tourPackageTypeRepository.saveAll(tourPackageTypeEntities).stream()
                .map(tourPackageTypeEntity -> new TourPackageTypeData(tourPackageTypeEntity))
                .collect(Collectors.toList());
    }

    @Override
    public TourPackageTypeListResponse getAllTourPackageTypes() throws EmptyListException {
        List<TourPackageTypeData> tourPackageTypeDataList = tourPackageDao.getTourPackageTypes(0, 0);
        if (tourPackageTypeDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourPackageTypeListResponse(tourPackageTypeDataList);
    }

    @Override
    public TourPackageTypeListResponse getAllTourPackageTypesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException {
        List<TourPackageTypeData> tourPackageTypeDataList = tourPackageDao.getTourPackageTypes(pageSize, pageNumber);
        if (tourPackageTypeDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourPackageTypeListResponse(tourPackageTypeDataList);
    }

    @Override
    public TourPackageTypeEntity getTourPackageTypeEntityByPackageTypeID(Long tourPackageTypeID) {
        return tourPackageTypeRepository.findById(tourPackageTypeID).orElseThrow(() -> new EntityNotFoundException("TourPackageTypeEntity Not Found"));
    }

    @Override
    public Map<Long, TourPackageTypeEntity> getTourPackageTypeEntitiesByPackageTypeIDs(Set<Long> tourPackageTypeIDs) {
        return EntityUtil.findEntitiesByIds(tourPackageTypeIDs, tourPackageTypeRepository, TourPackageTypeEntity::getId, "TourPackageTypeEntity");
    }

    // tour package
    @Override
    public InsertAcknowledgeResponse addTourPackage(SubscribedTourEntity subscribedTourEntity, TourPackageRequest tourPackageRequest, String requestId) {

        TourPackageEntity tourPackageEntity = setTourPackages(subscribedTourEntity, List.of(tourPackageRequest)).get(0);
        TourPackageData tourPackageData = new TourPackageData(tourPackageRepository.save(tourPackageEntity));

        return new InsertAcknowledgeResponse(tourPackageData, requestId);
    }

    @Override
    public InsertAcknowledgeListResponse addTourPackages(SubscribedTourEntity subscribedTourEntity, List<TourPackageRequest> tourPackageRequestList, String requestId) {

        List<TourPackageEntity> tourPackageEntities = setTourPackages(subscribedTourEntity, tourPackageRequestList);
        List<TourPackageData> tourPackageData = tourPackageRepository.saveAll(tourPackageEntities).stream()
                .map(tourPackageEntity -> new TourPackageData(tourPackageEntity))
                .collect(Collectors.toList());

        return new InsertAcknowledgeListResponse(tourPackageData, requestId);
    }

    @Override
    public List<TourPackageEntity> setTourPackages(SubscribedTourEntity subscribedTourEntity, List<TourPackageRequest> tourPackages) {
        Set<Long> tourPackageTypeIDs = tourPackages.stream()
                .map(TourPackageRequest::getTourPackageTypeID)
                .collect(Collectors.toSet());

        Map<Long, TourPackageTypeEntity> tourPackageTypeEntityMap = getTourPackageTypeEntitiesByPackageTypeIDs(tourPackageTypeIDs);

        List<TourPackageEntity> tourPackageEntities = tourPackages.stream()
                .map(tourPackageRequest -> {
                    TourPackageEntity tourPackageEntity = new TourPackageEntity();
                    tourPackageEntity.setSubscribedTourEntity(subscribedTourEntity);
                    TourPackageTypeEntity tourPackageTypeEntity = tourPackageTypeEntityMap.get(tourPackageRequest.getTourPackageTypeID());
                    tourPackageEntity.setTourPackageType(tourPackageTypeEntity);
                    tourPackageEntity.setTourPackageName(StringUtil.tourPackageName(subscribedTourEntity.getTourEntity().getAddedTourEntity().getTourName(), tourPackageTypeEntity.getPackageTypeName()));
                    tourPackageEntity.setDescription(tourPackageRequest.getTourPackageDescription());

                    boolean isFoodIncluded = false;
                    boolean isAccommodationIncluded = false;
                    boolean isTransportationIncluded = false;
                    boolean isTransferIncluded = false;

                    if (tourPackageRequest.getFoodOptionRequestList() != null && !tourPackageRequest.getFoodOptionRequestList().isEmpty())
                        tourPackageEntity.setFoodOptionEntities(foodService.setTourPackageFoodOptions(tourPackageEntity, tourPackageRequest.getFoodOptionRequestList()));
                    if (tourPackageRequest.getAccommodationOptionRequestList() != null && !tourPackageRequest.getAccommodationOptionRequestList().isEmpty())
                        tourPackageEntity.setAccommodationOptionEntities(accommodationService.setTourPackageAccommodations(tourPackageEntity, tourPackageRequest.getAccommodationOptionRequestList()));
                    if (tourPackageRequest.getTransferOptionRequestList() != null && !tourPackageRequest.getTransferOptionRequestList().isEmpty())
                        tourPackageEntity.setTourTransferOptionEntities(transferService.setTourPackageTransferOptions(tourPackageEntity, tourPackageRequest.getTransferOptionRequestList()));
                    if (tourPackageRequest.getTransportationPackages() != null && !tourPackageRequest.getTransportationPackages().isEmpty())
                        tourPackageEntity.setTransportationPackageEntities(transportationService.setTourPackageTransportations(tourPackageEntity, tourPackageRequest.getTransportationPackages()));
                    tourPackageEntity.setIsFoodIncluded(isFoodIncluded);
                    tourPackageEntity.setIsAccommodationIncluded(isAccommodationIncluded);
                    tourPackageEntity.setIsTransportationIncluded(isTransportationIncluded);
                    tourPackageEntity.setIsTransferIncluded(isTransferIncluded);

                    tourPackageEntity.setPackagePricePerPerson(tourPackagePriceService.perPersonDefaultPackagePrice(tourPackageRequest));
                    tourPackageEntity.setTotalPackagePrice(tourPackageRequest.getTotalPackagePrice());

                    return tourPackageEntity;

                })
                .toList();


        return tourPackageEntities;
    }

    @Override
    public TourPackageEntity getTourPackageEntityByPackageID(Long tourPackageID) {
        return tourPackageRepository.findById(tourPackageID).orElseThrow(() -> new EntityNotFoundException("TourPackageEntity Not Found"));
    }


}
