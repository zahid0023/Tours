package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dao.TourPackageDao;
import com.ghuddy.backendapp.tours.dto.data.ComponentCombinationData;
import com.ghuddy.backendapp.tours.dto.data.DefaultCombinationData;
import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.food.FoodOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.*;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.transporation.TransportationPackageRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.ComponentCombinationResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageDetailResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageSummaryListResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageTypeListResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.TourPackageNotFoundException;
import com.ghuddy.backendapp.tours.model.data.accommodation.AccommodationOptionData;
import com.ghuddy.backendapp.tours.model.data.food.FoodOptionData;
import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageData;
import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageSummaryData;
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

        List<TourPackageEntity> tourPackageEntityList = tourPackages.stream()
                .map(tourPackageRequest -> {
                    TourPackageEntity tourPackageEntity = new TourPackageEntity();
                    tourPackageEntity.setSubscribedTourEntity(subscribedTourEntity);
                    TourPackageTypeEntity tourPackageTypeEntity = tourPackageTypeEntityMap.get(tourPackageRequest.getTourPackageTypeID());
                    tourPackageEntity.setTourPackageType(tourPackageTypeEntity);
                    tourPackageEntity.setDescription(tourPackageRequest.getTourPackageDescription());
                    tourPackageEntity.setTourPackageName(StringUtil.tourPackageName(subscribedTourEntity.getTourEntity().getAddedTourEntity().getTourName(), tourPackageEntity.getTourPackageType().getPackageTypeName()));
                    Optional<List<TourPackageOptionEntity>> tourPackageOptionEntityListExists = setTourPackageOptions(tourPackageEntity, new TourPackageOptionCheckRequest(tourPackageRequest, tourPackageEntity.getTourPackageType().getSuitableFor()));
                    if (tourPackageOptionEntityListExists.isPresent()) {
                        tourPackageEntity.setTourPackageOptionEntities(tourPackageOptionEntityListExists.get());
                        List<TransportationPackageEntity> transportationPackageEntities = tourPackageEntity.getTourPackageOptionEntities()
                                .stream()
                                .map(TourPackageOptionEntity::getTransportationPackageEntity)
                                .distinct() // Ensures uniqueness
                                .collect(Collectors.toList());
                        tourPackageEntity.setTransportationPackageEntities(transportationPackageEntities.stream().toList());
                    }
                    return tourPackageEntity;
                })
                .toList();

        return tourPackageEntityList;
    }

    @Override
    public TourPackageEntity getTourPackageEntityByPackageID(Long tourPackageID) throws TourPackageNotFoundException {
        return tourPackageRepository.findById(tourPackageID).orElseThrow(() -> new TourPackageNotFoundException(ErrorCode.TOUR_PACKAGE_NOT_FOUND));
    }

    /**
     * @param tourPackageEntity
     * @param tourPackageOptionCheckRequest
     * @return
     */
    @Override
    public Optional<List<TourPackageOptionEntity>> setTourPackageOptions(TourPackageEntity tourPackageEntity, TourPackageOptionCheckRequest tourPackageOptionCheckRequest) {
        List<List<?>> tourPackageCombinationToCheck = new LinkedList<>();

        if (tourPackageOptionCheckRequest.getAccommodationOptionRequestList() != null && !tourPackageOptionCheckRequest.getAccommodationOptionRequestList().isEmpty())
            tourPackageCombinationToCheck.add(tourPackageOptionCheckRequest.getAccommodationOptionRequestList());
        if (tourPackageOptionCheckRequest.getFoodOptionRequestList() != null && !tourPackageOptionCheckRequest.getFoodOptionRequestList().isEmpty())
            tourPackageCombinationToCheck.add(tourPackageOptionCheckRequest.getFoodOptionRequestList());
        if (tourPackageOptionCheckRequest.getTransferOptionRequestList() != null && !tourPackageOptionCheckRequest.getTransferOptionRequestList().isEmpty())
            tourPackageCombinationToCheck.add(tourPackageOptionCheckRequest.getTransferOptionRequestList());
        if (tourPackageOptionCheckRequest.getTransportationPackages() != null && !tourPackageOptionCheckRequest.getTransportationPackages().isEmpty())
            tourPackageCombinationToCheck.add(tourPackageOptionCheckRequest.getTransportationPackages());

        List<List<?>> combinations = CombinationGenerator.generateCombinations(tourPackageCombinationToCheck);

        List<TourPackageOptionEntity> tourPackageOptionEntityList = combinations.stream()
                .map(option -> {
                    TourPackageOptionEntity tourPackageOptionEntity = new TourPackageOptionEntity();
                    tourPackageEntity.setPackagePricePerPerson(BigDecimal.ZERO);
                    option.forEach(component -> {
                        if (component.getClass().isAssignableFrom(AccommodationOptionRequest.class)) {
                            AccommodationOptionRequest accommodationOptionRequest = (AccommodationOptionRequest) component;
                            AccommodationOptionEntity accommodationOptionEntity = accommodationService.setTourPackageAccommodations(tourPackageEntity, List.of(accommodationOptionRequest)).get(0);
                            tourPackageOptionEntity.setAccommodationOptionEntity(accommodationOptionEntity);
                            if (accommodationOptionEntity.getIsDefault()) {
                                tourPackageEntity.setDefaultAccommodationOptionPrice(accommodationOptionEntity.getTotalOptionPricePerPerson());
                                tourPackageEntity.setPackagePricePerPerson(tourPackageEntity.getPackagePricePerPerson().add(accommodationOptionEntity.getTotalOptionPricePerPerson()));
                            }
                        } else if (component.getClass().isAssignableFrom(FoodOptionRequest.class)) {
                            FoodOptionRequest foodOptionRequest = (FoodOptionRequest) component;
                            FoodOptionEntity foodOptionEntity = foodService.setTourPackageFoodOptions(tourPackageEntity, List.of(foodOptionRequest)).get(0);
                            tourPackageOptionEntity.setFoodOptionEntity(foodOptionEntity);
                            if (foodOptionEntity.getIsDefault()) {
                                tourPackageEntity.setDefaultFoodOptionPrice(foodOptionEntity.getTotalOptionPricePerPerson());
                                tourPackageEntity.setPackagePricePerPerson(tourPackageEntity.getPackagePricePerPerson().add(foodOptionEntity.getTotalOptionPricePerPerson()));
                            }
                        } else if (component.getClass().isAssignableFrom(TransferOptionRequest.class)) {
                            TransferOptionRequest transferOptionRequest = (TransferOptionRequest) component;
                            TransferOptionEntity transferOptionEntity = transferService.setTourPackageTransferOptions(tourPackageEntity, List.of(transferOptionRequest)).get(0);
                            tourPackageOptionEntity.setTransferOptionEntity(transferOptionEntity);
                            if (transferOptionEntity.getIsDefault()) {
                                tourPackageEntity.setDefaultTransferOptionPrice(transferOptionEntity.getPerPersonTransferOptionPrice());
                                tourPackageEntity.setTotalPackagePrice(tourPackageEntity.getPackagePricePerPerson().add(transferOptionEntity.getPerPersonTransferOptionPrice()));
                            }
                        } else if (component.getClass().isAssignableFrom(TransportationPackageRequest.class)) {
                            TransportationPackageRequest transportationPackageRequest = (TransportationPackageRequest) component;
                            tourPackageOptionEntity.setTransportationPackageEntity(transportationService.setTourPackageTransportations(tourPackageEntity, List.of(transportationPackageRequest)).get(0));
                        }
                    });
                    tourPackageOptionEntity.setTourPackageEntity(tourPackageEntity);
                    return tourPackageOptionEntity;
                })
                .toList();
        return Optional.of(tourPackageOptionEntityList);
    }

    /**
     * @param tourPackageOptionCheckRequest
     */
    @Override
    public ComponentCombinationResponse checkTourPackageOptionCombination(TourPackageEntity tourPackageEntity, TourPackageOptionCheckRequest tourPackageOptionCheckRequest) throws EmptyListException {
        DefaultCombinationData defaultCombinationData = new DefaultCombinationData();

        Optional<List<TourPackageOptionEntity>> tourPackageOptionEntityListExists = setTourPackageOptions(tourPackageEntity, tourPackageOptionCheckRequest);
        List<TourPackageOptionEntity> tourPackageOptionEntityList;
        if (tourPackageOptionEntityListExists.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        else tourPackageOptionEntityList = tourPackageOptionEntityListExists.get();


        List<ComponentCombinationData> componentCombinationDataList = tourPackageOptionEntityList.stream()
                .map(tourPackageOptionEntity -> {
                    ComponentCombinationData componentCombinationData = new ComponentCombinationData();
                    componentCombinationData.setTotalOptionPricePerPerson(BigDecimal.ZERO);

                    AccommodationOptionEntity accommodationOptionEntity = tourPackageOptionEntity.getAccommodationOptionEntity();
                    FoodOptionEntity foodOptionEntity = tourPackageOptionEntity.getFoodOptionEntity();
                    TransferOptionEntity transferOptionEntity = tourPackageOptionEntity.getTransferOptionEntity();
                    TransportationPackageEntity transportationPackageEntity = tourPackageOptionEntity.getTransportationPackageEntity();

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

                    if (transportationPackageEntity != null) {
                        TransportationPackageData transportationPackageData = new TransportationPackageData(transportationPackageEntity);
                        componentCombinationData.setTransportationPackageData(transportationPackageData);
                        componentCombinationData.setTotalOptionPricePerPerson(componentCombinationData.getTotalOptionPricePerPerson().add(transportationPackageData.getUnitPrice()));
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

    /**
     * @param tourPackageId
     * @return
     * @throws TourPackageNotFoundException
     */
    @Override
    public TourPackageDetailResponse getTourPackageDetailByTourPackageId(Long tourPackageId, String requestId) throws TourPackageNotFoundException {
        TourPackageEntity tourPackageEntity = getTourPackageEntityByPackageID(tourPackageId);
        return new TourPackageDetailResponse(new TourPackageData(tourPackageEntity), requestId);
    }

    /**
     * @return
     * @throws EmptyListException
     */
    @Override
    public TourPackageSummaryListResponse getTourPackageSummaryBySubscribedTourId(SubscribedTourEntity subscribedTourEntity, String requestId) throws EmptyListException {
        List<TourPackageSummaryData> tourPackageSummaryDataList = subscribedTourEntity.getTourPackageEntities().stream()
                .map(tourPackageEntity -> new TourPackageSummaryData(tourPackageEntity))
                .toList();
        return new TourPackageSummaryListResponse(tourPackageSummaryDataList, requestId);
    }
}
