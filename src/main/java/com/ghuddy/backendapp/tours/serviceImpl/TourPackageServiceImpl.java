package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dao.TourPackageDao;
import com.ghuddy.backendapp.tours.dto.data.ComponentCombinationData;
import com.ghuddy.backendapp.tours.dto.data.DefaultCombinationData;
import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.food.FoodOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.*;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.transporation.TransportationPackageRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.ComponentCombinationResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageTypeListResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.accommodation.AccommodationOptionData;
import com.ghuddy.backendapp.tours.model.data.food.FoodOptionData;
import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageData;
import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageTypeData;
import com.ghuddy.backendapp.tours.model.data.transfer.TransferOptionData;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationPackageData;
import com.ghuddy.backendapp.tours.model.entities.*;
import com.ghuddy.backendapp.tours.repository.TourPackageRepository;
import com.ghuddy.backendapp.tours.repository.TourPackageTypeRepository;
import com.ghuddy.backendapp.tours.service.*;
import com.ghuddy.backendapp.tours.utils.CombinationGenerator;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import com.ghuddy.backendapp.tours.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
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
    public InsertAcknowledgeResponse addTourPackage(SubscribedTourEntity subscribedTourEntity, TourPackageRequest tourPackageRequest, String requestId) throws EmptyListException {

        TourPackageEntity tourPackageEntity = setTourPackages(subscribedTourEntity, List.of(tourPackageRequest)).get(0);
        TourPackageData tourPackageData = new TourPackageData(tourPackageRepository.save(tourPackageEntity));

        return new InsertAcknowledgeResponse(tourPackageData, requestId);
    }

    @Override
    public InsertAcknowledgeListResponse addTourPackages(SubscribedTourEntity subscribedTourEntity, List<TourPackageRequest> tourPackageRequestList, String requestId) throws EmptyListException {

        List<TourPackageEntity> tourPackageEntities = setTourPackages(subscribedTourEntity, tourPackageRequestList);
        List<TourPackageData> tourPackageData = tourPackageRepository.saveAll(tourPackageEntities).stream()
                .map(tourPackageEntity -> new TourPackageData(tourPackageEntity))
                .collect(Collectors.toList());

        return new InsertAcknowledgeListResponse(tourPackageData, requestId);
    }

    @Override
    public List<TourPackageEntity> setTourPackages(SubscribedTourEntity subscribedTourEntity, List<TourPackageRequest> tourPackages) {
        log.info(tourPackages.toString());
        Set<Long> tourPackageTypeIDs = tourPackages.stream()
                .map(TourPackageRequest::getTourPackageTypeID)
                .collect(Collectors.toSet());

        Map<Long, TourPackageTypeEntity> tourPackageTypeEntityMap = getTourPackageTypeEntitiesByPackageTypeIDs(tourPackageTypeIDs);

        return null;
    }

    @Override
    public TourPackageEntity getTourPackageEntityByPackageID(Long tourPackageID) {
        return tourPackageRepository.findById(tourPackageID).orElseThrow(() -> new EntityNotFoundException("TourPackageEntity Not Found"));
    }

    /**
     * @param tourPackageEntity
     * @param tourPackageOptionCheckRequest
     * @return
     */
    @Override
    public List<TourPackageOptionEntity> setTourPackageOptions(TourPackageEntity tourPackageEntity, TourPackageOptionCheckRequest tourPackageOptionCheckRequest) {
        List<List<?>> tourPackageCombinationToCheck = new LinkedList<>();

        if (tourPackageOptionCheckRequest.getAccommodationOptionRequestList() != null && !tourPackageOptionCheckRequest.getAccommodationOptionRequestList().isEmpty())
            tourPackageCombinationToCheck.add(tourPackageOptionCheckRequest.getAccommodationOptionRequestList());
        if (tourPackageOptionCheckRequest.getFoodOptionRequestList() != null && !tourPackageOptionCheckRequest.getFoodOptionRequestList().isEmpty())
            tourPackageCombinationToCheck.add(tourPackageOptionCheckRequest.getFoodOptionRequestList());
        if (tourPackageOptionCheckRequest.getTransferOptionRequestList() != null && !tourPackageOptionCheckRequest.getTransferOptionRequestList().isEmpty())
            tourPackageCombinationToCheck.add(tourPackageOptionCheckRequest.getTransferOptionRequestList());

        List<List<?>> combinations = CombinationGenerator.generateCombinations(tourPackageCombinationToCheck);

        List<TourPackageOptionEntity> tourPackageOptionEntityList = combinations.stream()
                .map(option -> {
                    TourPackageOptionEntity tourPackageOptionEntity = new TourPackageOptionEntity();
                    option.forEach(component -> {
                        if (component.getClass().isAssignableFrom(AccommodationOptionRequest.class)) {
                            AccommodationOptionRequest accommodationOptionRequest = (AccommodationOptionRequest) component;
                            tourPackageOptionEntity.setAccommodationOptionEntity(accommodationService.setTourPackageAccommodations(tourPackageEntity, List.of(accommodationOptionRequest)).get(0));
                        } else if (component.getClass().isAssignableFrom(FoodOptionRequest.class)) {
                            FoodOptionRequest foodOptionRequest = (FoodOptionRequest) component;
                            tourPackageOptionEntity.setFoodOptionEntity(foodService.setTourPackageFoodOptions(tourPackageEntity, List.of(foodOptionRequest)).get(0));
                        } else if (component.getClass().isAssignableFrom(TransferOptionRequest.class)) {
                            TransferOptionRequest transferOptionRequest = (TransferOptionRequest) component;
                            tourPackageOptionEntity.setTransferOptionEntity(transferService.setTourPackageTransferOptions(tourPackageEntity, List.of(transferOptionRequest)).get(0));
                        }
                    });
                    return tourPackageOptionEntity;
                })
                .toList();
        return tourPackageOptionEntityList;
    }

    /**
     * @param tourPackageOptionCheckRequest
     */
    @Override
    public ComponentCombinationResponse checkTourPackageOptionCombination(TourPackageEntity tourPackageEntity, TourPackageOptionCheckRequest tourPackageOptionCheckRequest) {
        DefaultCombinationData defaultCombinationData = new DefaultCombinationData();

        List<ComponentCombinationData> componentCombinationDataList = setTourPackageOptions(tourPackageEntity, tourPackageOptionCheckRequest).stream()
                .map(tourPackageOptionEntity -> {
                    ComponentCombinationData componentCombinationData = new ComponentCombinationData();
                    componentCombinationData.setTotalOptionPricePerPerson(BigDecimal.ZERO);
                    AccommodationOptionEntity accommodationOptionEntity = tourPackageOptionEntity.getAccommodationOptionEntity();
                    FoodOptionEntity foodOptionEntity = tourPackageOptionEntity.getFoodOptionEntity();
                    TransferOptionEntity transferOptionEntity = tourPackageOptionEntity.getTransferOptionEntity();
                    if (accommodationOptionEntity != null) {
                        AccommodationOptionData accommodationOptionData = new AccommodationOptionData(accommodationOptionEntity);
                        componentCombinationData.setAccommodationOptionData(accommodationOptionData);
                        componentCombinationData.setTotalOptionPricePerPerson(componentCombinationData.getTotalOptionPricePerPerson().add(accommodationOptionEntity.getTotalOptionPricePerPerson()));
                        if (accommodationOptionEntity.getIsDefault())
                            defaultCombinationData.setAccommodationOptionData(accommodationOptionData);
                    }

                    if (foodOptionEntity != null) {
                        FoodOptionData foodOptionData = new FoodOptionData(foodOptionEntity);
                        componentCombinationData.setFoodOptionData(foodOptionData);
                        componentCombinationData.setTotalOptionPricePerPerson(componentCombinationData.getTotalOptionPricePerPerson().add(foodOptionData.getTotalOptionPricePerPerson()));
                        if (foodOptionEntity.getIsDefault()) defaultCombinationData.setFoodOptionData(foodOptionData);
                    }

                    if (transferOptionEntity != null) {
                        TransferOptionData transferOptionData = new TransferOptionData(transferOptionEntity);
                        componentCombinationData.setTransferOptionData(transferOptionData);
                        componentCombinationData.setTotalOptionPricePerPerson(componentCombinationData.getTotalOptionPricePerPerson().add(transferOptionData.getTotalOptionPricePerPerson()));
                        if (transferOptionEntity.getIsDefault())
                            defaultCombinationData.setTransferOptionData(transferOptionData);
                    }
                    return componentCombinationData;
                })
                .toList();

        BigDecimal defaultFoodOptionPrice = BigDecimal.ZERO;
        BigDecimal defaultAccommodationOptionPrice = BigDecimal.ZERO;
        BigDecimal defaultTransferOptionPrice = BigDecimal.ZERO;

        if (defaultCombinationData.getFoodOptionData() != null)
            defaultFoodOptionPrice = defaultCombinationData.getFoodOptionData().getTotalOptionPricePerPerson();
        if (defaultCombinationData.getAccommodationOptionData() != null)
            defaultAccommodationOptionPrice = defaultCombinationData.getAccommodationOptionData().getTotalOptionPricePerPerson();
        if (defaultCombinationData.getTransferOptionData() != null)
            defaultTransferOptionPrice = defaultCombinationData.getTransferOptionData().getTotalOptionPricePerPerson();

        defaultCombinationData.setDefaultOptionPricePerPerson(defaultFoodOptionPrice.add(defaultAccommodationOptionPrice).add(defaultTransferOptionPrice));

        return new ComponentCombinationResponse(componentCombinationDataList, defaultCombinationData);
    }
}
