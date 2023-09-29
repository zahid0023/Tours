package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dao.TourPackageDao;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
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
import com.ghuddy.backendapp.tours.service.AccommodationService;
import com.ghuddy.backendapp.tours.service.FoodService;
import com.ghuddy.backendapp.tours.service.TourPackageService;
import com.ghuddy.backendapp.tours.service.TourService;
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
    private final TourPackageDao tourPackageDao;

    public TourPackageServiceImpl(TourPackageTypeRepository tourPackageTypeRepository,
                                  TourPackageRepository tourPackageRepository,
                                  TourService tourService,
                                  FoodService foodService,
                                  AccommodationService accommodationService,
                                  TourPackageDao tourPackageDao) {
        this.tourPackageTypeRepository = tourPackageTypeRepository;
        this.tourPackageRepository = tourPackageRepository;
        this.tourService = tourService;
        this.foodService = foodService;
        this.accommodationService = accommodationService;
        this.tourPackageDao = tourPackageDao;
    }

    // tour package type
    @Override
    public AcknowledgeResponse addTourPackageType(TourPackageTypeAddRequest tourPackageTypeAddRequest) {
        return addTourPackageTypes(List.of(tourPackageTypeAddRequest.getTourPackageTypeRequest()));
    }

    @Override
    public AcknowledgeResponse addTourPackageTypes(TourPackageTypeListAddRequest tourPackageTypeListAddRequest) {
        return addTourPackageTypes(tourPackageTypeListAddRequest.getTourPackageTypes());
    }

    private AcknowledgeResponse addTourPackageTypes(List<TourPackageTypeRequest> tourPackages) {
        List<TourPackageTypeEntity> tourPackageTypeEntities = tourPackages.stream()
                .map(tourPackageTypeAddRequest -> {
                    TourPackageTypeEntity tourPackageTypeEntity = new TourPackageTypeEntity();
                    tourPackageTypeEntity.setPackageTypeName(tourPackageTypeAddRequest.getTourPackageTypeName());
                    tourPackageTypeEntity.setDescription(tourPackageTypeAddRequest.getTourPackageTypeDescription());
                    tourPackageTypeEntity.setSuitableFor(tourPackageTypeAddRequest.getSuitableForPersons());
                    return tourPackageTypeEntity;
                })
                .collect(Collectors.toList());
        tourPackageTypeRepository.saveAll(tourPackageTypeEntities);
        return new AcknowledgeResponse();
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
    public AcknowledgeResponse addTourPackage(TourPackageAddRequest tourPackageAddRequest) throws TourNotFoundException {
        TourEntity tourEntity = tourService.getCreatedTourEntityById(tourPackageAddRequest.getTourID());
        return addTourPackages(tourEntity, List.of(tourPackageAddRequest.getTourPackageRequest()));
    }

    // tour package
    @Override
    public AcknowledgeResponse addTourPackages(TourPackageListAddRequest tourPackageListAddRequest) throws TourNotFoundException {
        TourEntity tourEntity = tourService.getCreatedTourEntityById(tourPackageListAddRequest.getTourID());
        return addTourPackages(tourEntity, tourPackageListAddRequest.getTourPackages());
    }

    private AcknowledgeResponse addTourPackages(TourEntity tourEntity, List<TourPackageRequest> tourPackages) {
        Set<Long> tourPackageTypeIDs = tourPackages.stream()
                .map(TourPackageRequest::getTourPackageTypeID)
                .collect(Collectors.toSet());
        Map<Long, TourPackageTypeEntity> tourPackageTypeEntityMap = getTourPackageTypeByPackageTypeIDs(tourPackageTypeIDs);
        List<TourPackageEntity> tourPackageEntities = tourPackages.stream()
                .map(tourPackageRequest -> {
                    TourPackageEntity tourPackageEntity = new TourPackageEntity();
                    tourPackageEntity.setTourEntity(tourEntity);
                    TourPackageTypeEntity tourPackageTypeEntity = tourPackageTypeEntityMap.get(tourPackageRequest.getTourPackageTypeID());
                    tourPackageEntity.setTourPackageType(tourPackageTypeEntity);
                    tourPackageEntity.setTourPackageName(StringUtil.tourPackageName(tourEntity.getAddedTourEntity().getTourName(), tourPackageTypeEntity.getPackageTypeName()));
                    tourPackageEntity.setDescription(tourPackageRequest.getDescription());
                    tourPackageEntity.setMealPackageEntities(foodService.setTourPackageMealPackages(tourPackageEntity, tourPackageRequest.getMealPackages()));
                    tourPackageEntity.setTourPackageAccommodationEntities(accommodationService.setTourPackageAccommodations(tourPackageEntity, tourPackageRequest.getAccommodations()));
                    return tourPackageEntity;
                })
                .collect(Collectors.toList());
        tourPackageRepository.saveAll(tourPackageEntities);
        return new AcknowledgeResponse();
    }

    @Override
    public TourPackageTypeEntity getTourPackageTypeByPackageTypeID(Long tourPackageTypeID) {
        return tourPackageTypeRepository.findById(tourPackageTypeID).orElseThrow(() -> new EntityNotFoundException("TourPackageTypeEntity Not Found"));
    }

    @Override
    public TourPackageEntity getTourPackageByPackageID(Long tourPackageID) {
        return tourPackageRepository.findById(tourPackageID).orElseThrow(() -> new EntityNotFoundException("TourPackageEntity Not Found"));
    }

    @Override
    public Map<Long, TourPackageTypeEntity> getTourPackageTypeByPackageTypeIDs(Set<Long> tourPackageTypeIDs) {
        return EntityUtil.findEntitiesByIds(tourPackageTypeIDs, tourPackageTypeRepository, TourPackageTypeEntity::getId, "TourPackageTypeEntity");
    }
}
