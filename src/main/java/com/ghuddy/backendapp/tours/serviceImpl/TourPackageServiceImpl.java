package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dao.TourPackageDao;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageData;
import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageTypeData;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.*;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageTypeListResponse;
import com.ghuddy.backendapp.tours.model.entities.TourEntity;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.TourPackageTypeEntity;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.repository.TourPackageRepository;
import com.ghuddy.backendapp.tours.repository.TourPackageTypeRepository;
import com.ghuddy.backendapp.tours.service.*;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import com.ghuddy.backendapp.tours.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TourPackageServiceImpl implements TourPackageService {
    private final TourPackageTypeRepository tourPackageTypeRepository;
    private final TourPackageRepository tourPackageRepository;
    private final TourService tourService;
    private final FoodService foodService;
    private final AccommodationService accommodationService;
    private final TransportationService transportationService;
    private final TourPackageDao tourPackageDao;

    public TourPackageServiceImpl(TourPackageTypeRepository tourPackageTypeRepository,
                                  TourPackageRepository tourPackageRepository,
                                  TourService tourService,
                                  FoodService foodService,
                                  AccommodationService accommodationService,
                                  TourPackageDao tourPackageDao,
                                  TransportationService transportationService) {
        this.tourPackageTypeRepository = tourPackageTypeRepository;
        this.tourPackageRepository = tourPackageRepository;
        this.tourService = tourService;
        this.foodService = foodService;
        this.accommodationService = accommodationService;
        this.tourPackageDao = tourPackageDao;
        this.transportationService = transportationService;
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
    public InsertAcknowledgeResponse addTourPackage(TourPackageAddRequest tourPackageAddRequest) throws TourNotFoundException {
        TourEntity tourEntity = tourService.getCreatedTourEntityById(tourPackageAddRequest.getTourID());
        TourPackageData tourPackageData = addTourPackages(tourEntity, List.of(tourPackageAddRequest.getTourPackageRequest())).get(0);
        return new InsertAcknowledgeResponse(tourPackageData, tourPackageAddRequest.getRequestId());
    }

    @Override
    public InsertAcknowledgeListResponse addTourPackages(TourPackageListAddRequest tourPackageListAddRequest) throws TourNotFoundException {
        TourEntity tourEntity = tourService.getCreatedTourEntityById(tourPackageListAddRequest.getTourID());
        List<TourPackageData> tourPackageDataList = addTourPackages(tourEntity, tourPackageListAddRequest.getTourPackages());
        return new InsertAcknowledgeListResponse(tourPackageDataList, tourPackageListAddRequest.getRequestId());
    }

    private List<TourPackageData> addTourPackages(TourEntity tourEntity, List<TourPackageRequest> tourPackages) {
        Set<Long> tourPackageTypeIDs = tourPackages.stream()
                .map(TourPackageRequest::getTourPackageTypeID)
                .collect(Collectors.toSet());
        Map<Long, TourPackageTypeEntity> tourPackageTypeEntityMap = getTourPackageTypeEntitiesByPackageTypeIDs(tourPackageTypeIDs);
        List<TourPackageEntity> tourPackageEntities = tourPackages.stream()
                .map(tourPackageRequest -> {
                    TourPackageEntity tourPackageEntity = new TourPackageEntity();
                    tourPackageEntity.setTourEntity(tourEntity);
                    TourPackageTypeEntity tourPackageTypeEntity = tourPackageTypeEntityMap.get(tourPackageRequest.getTourPackageTypeID());
                    tourPackageEntity.setTourPackageType(tourPackageTypeEntity);
                    tourPackageEntity.setTourPackageName(StringUtil.tourPackageName(tourEntity.getAddedTourEntity().getTourName(), tourPackageTypeEntity.getPackageTypeName()));
                    tourPackageEntity.setDescription(tourPackageRequest.getTourPackageDescription());
                    if (tourPackageRequest.getTourPackageMealPackages() != null && !tourPackageRequest.getTourPackageMealPackages().isEmpty())
                        tourPackageEntity.setMealPackageEntities(foodService.setTourPackageMealPackages(tourPackageEntity, tourPackageRequest.getTourPackageMealPackages()));
                    if (tourPackageRequest.getTourPackageAccommodations() != null && !tourPackageRequest.getTourPackageAccommodations().isEmpty())
                        tourPackageEntity.setTourPackageAccommodationEntities(accommodationService.setTourPackageAccommodations(tourPackageEntity, tourPackageRequest.getTourPackageAccommodations()));
                    if (tourPackageRequest.getTourPackageTransportations() != null && !tourPackageRequest.getTourPackageTransportations().isEmpty())
                        tourPackageEntity.setTourPackageTransportationEntities(transportationService.setTourPackageTransportations(tourPackageEntity, tourPackageRequest.getTourPackageTransportations()));
                    return tourPackageEntity;
                })
                .collect(Collectors.toList());
        return tourPackageRepository.saveAll(tourPackageEntities).stream()
                .map(tourPackageEntity -> new TourPackageData(tourPackageEntity))
                .collect(Collectors.toList());
    }

    @Override
    public TourPackageEntity getTourPackageEntityByPackageID(Long tourPackageID) {
        return tourPackageRepository.findById(tourPackageID).orElseThrow(() -> new EntityNotFoundException("TourPackageEntity Not Found"));
    }

}
