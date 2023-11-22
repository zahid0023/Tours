package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dao.TourPackageDao;
import com.ghuddy.backendapp.tours.dto.data.TourPackageAllComponentData;
import com.ghuddy.backendapp.tours.dto.data.DefaultCombinationData;
import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.food.FoodOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.food.MealPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.*;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.transporation.TransportationPackageRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.*;
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
import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.FoodOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.MealPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.guide.GuideOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.SpotEntryPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.SpotEntryOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageTypeEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.TransferOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.TransportationPackageEntity;
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
    private final GuideService guideService;
    private final SpotEntryService spotEntryService;

    public TourPackageServiceImpl(TourPackageTypeRepository tourPackageTypeRepository,
                                  TourPackageRepository tourPackageRepository,
                                  FoodService foodService,
                                  AccommodationService accommodationService,
                                  TransportationService transportationService,
                                  TourPackageDao tourPackageDao,
                                  TourPackagePriceService tourPackagePriceService,
                                  TransferService transferService,
                                  GuideService guideService,
                                  SpotEntryService spotEntryService) {
        this.tourPackageTypeRepository = tourPackageTypeRepository;
        this.tourPackageRepository = tourPackageRepository;
        this.foodService = foodService;
        this.accommodationService = accommodationService;
        this.transportationService = transportationService;
        this.tourPackageDao = tourPackageDao;
        this.tourPackagePriceService = tourPackagePriceService;
        this.transferService = transferService;
        this.guideService = guideService;
        this.spotEntryService = spotEntryService;
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

                    if (tourPackageRequest.getAccommodationOptionRequestList() != null && !tourPackageRequest.getAccommodationOptionRequestList().isEmpty()) {
                        List<AccommodationOptionEntity> accommodationOptionEntityList = accommodationService.setTourPackageAccommodations(tourPackageEntity, tourPackageRequest.getAccommodationOptionRequestList());
                        tourPackageEntity.setAccommodationOptionEntities(accommodationOptionEntityList);
                    }

                    if (tourPackageRequest.getMealPackageRequestList()!=null&&!tourPackageRequest.getMealPackageRequestList().isEmpty()){
                        List<MealPackageEntity> mealPackageRequestList = foodService.setTourPackageMealPackages(tourPackageEntity,tourPackageRequest.getMealPackageRequestList());
                        tourPackageEntity.setMealPackageEntities(mealPackageRequestList);
                    }

                    if (tourPackageRequest.getTransferOptionRequestList() != null && !tourPackageRequest.getTransferOptionRequestList().isEmpty()) {
                        List<TransferOptionEntity> transferOptionEntityList = transferService.setTourPackageTransferOptions(tourPackageEntity, tourPackageRequest.getTransferOptionRequestList());
                        tourPackageEntity.setTransferOptionEntities(transferOptionEntityList);
                    }

                    if (tourPackageRequest.getTransportationPackages() != null && !tourPackageRequest.getTransportationPackages().isEmpty()) {
                        List<TransportationPackageEntity> transportationPackageEntityList = transportationService.setTourPackageTransportations(tourPackageEntity, tourPackageRequest.getTransportationPackages());
                        tourPackageEntity.setTransportationPackageEntities(transportationPackageEntityList);
                    }

                    if (tourPackageRequest.getGuideOptionRequestList() != null && !tourPackageRequest.getGuideOptionRequestList().isEmpty()) {
                        List<GuideOptionEntity> guideOptionEntityList = guideService.setTourPackageGuideOptions(tourPackageEntity, tourPackageRequest.getGuideOptionRequestList());
                        tourPackageEntity.setGuideOptionEntityList(guideOptionEntityList);
                    }

                    if (tourPackageRequest.getSpotEntryRequestList() != null && !tourPackageRequest.getSpotEntryRequestList().isEmpty()) {
                        SpotEntryOptionEntity spotEntryOptionEntity = new SpotEntryOptionEntity();
                        spotEntryOptionEntity.setTourPackageEntity(tourPackageEntity);
                        List<SpotEntryPackageEntity> spotEntryPackageEntityList = spotEntryService.setTourPackageSpotEntries(spotEntryOptionEntity, tourPackageRequest.getSpotEntryRequestList());
                        spotEntryOptionEntity.setSpotEntryPackageEntities(spotEntryPackageEntityList);
                        spotEntryOptionEntity.setTotalOptionPricePerPerson(spotEntryOptionEntity.getSpotEntryPackageEntities().stream()
                                .map(spotEntryPackageEntity -> spotEntryPackageEntity.getPricePerPerson())
                                .reduce(BigDecimal.ZERO, BigDecimal::add));
                        tourPackageEntity.setTourPackageSpotEntryOptionEntities(List.of(spotEntryOptionEntity));
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
        log.info(combinations.toString());


        List<TourPackageOptionEntity> tourPackageOptionEntityList = combinations.stream()
                .map(option -> {
                    TourPackageOptionEntity tourPackageOptionEntity = new TourPackageOptionEntity();
                    option.forEach(component -> {
                        if (component.getClass().isAssignableFrom(AccommodationOptionRequest.class)) {
                            AccommodationOptionRequest accommodationOptionRequest = (AccommodationOptionRequest) component;
                            AccommodationOptionEntity accommodationOptionEntity = accommodationService.setTourPackageAccommodations(tourPackageEntity, List.of(accommodationOptionRequest)).get(0);
                            accommodationOptionEntity.setTourPackageEntity(tourPackageEntity);
                            tourPackageOptionEntity.setAccommodationOptionEntity(accommodationOptionEntity);
                        } else if (component.getClass().isAssignableFrom(TransferOptionRequest.class)) {
                            TransferOptionRequest transferOptionRequest = (TransferOptionRequest) component;
                            TransferOptionEntity transferOptionEntity = transferService.setTourPackageTransferOptions(tourPackageEntity, List.of(transferOptionRequest)).get(0);
                            tourPackageOptionEntity.setTransferOptionEntity(transferOptionEntity);
                        } else if (component.getClass().isAssignableFrom(TransportationPackageRequest.class)) {
                            TransportationPackageRequest transportationPackageRequest = (TransportationPackageRequest) component;
                            tourPackageOptionEntity.setTransportationPackageEntity(transportationService.setTourPackageTransportations(tourPackageEntity, List.of(transportationPackageRequest)).get(0));
                        }
                    });
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


        List<TourPackageAllComponentData> tourPackageAllComponentCombinationDataList = tourPackageOptionEntityList.stream()
                .map(tourPackageOptionEntity -> {
                    TourPackageAllComponentData tourPackageAllComponentCombinationData = new TourPackageAllComponentData();
                    tourPackageAllComponentCombinationData.setTotalOptionPricePerPerson(BigDecimal.ZERO);

                    AccommodationOptionEntity accommodationOptionEntity = tourPackageOptionEntity.getAccommodationOptionEntity();
                    FoodOptionEntity foodOptionEntity = tourPackageOptionEntity.getFoodOptionEntity();
                    TransferOptionEntity transferOptionEntity = tourPackageOptionEntity.getTransferOptionEntity();
                    TransportationPackageEntity transportationPackageEntity = tourPackageOptionEntity.getTransportationPackageEntity();

                    if (accommodationOptionEntity != null) {
                        AccommodationOptionData accommodationOptionData = new AccommodationOptionData(accommodationOptionEntity, accommodationOptionEntity.getIsActive());

                        tourPackageAllComponentCombinationData.setAccommodationOptionData(accommodationOptionData);
                        tourPackageAllComponentCombinationData.setTotalOptionPricePerPerson(tourPackageAllComponentCombinationData.getTotalOptionPricePerPerson().add(accommodationOptionEntity.getTotalOptionPricePerPerson()));
                    }

                    if (foodOptionEntity != null) {
                        FoodOptionData foodOptionData = new FoodOptionData(foodOptionEntity, foodOptionEntity.getIsActive());

                        tourPackageAllComponentCombinationData.setFoodOptionData(foodOptionData);
                        tourPackageAllComponentCombinationData.setTotalOptionPricePerPerson(tourPackageAllComponentCombinationData.getTotalOptionPricePerPerson().add(foodOptionData.getTotalOptionPricePerPerson()));
                    }

                    if (transferOptionEntity != null) {
                        TransferOptionData transferOptionData = new TransferOptionData(transferOptionEntity, transferOptionEntity.getIsActive());

                        tourPackageAllComponentCombinationData.setTransferOptionData(transferOptionData);
                        tourPackageAllComponentCombinationData.setTotalOptionPricePerPerson(tourPackageAllComponentCombinationData.getTotalOptionPricePerPerson().add(transferOptionData.getTotalOptionPricePerPerson()));
                    }

                    if (transportationPackageEntity != null) {
                        TransportationPackageData transportationPackageData = new TransportationPackageData(transportationPackageEntity, true);

                        tourPackageAllComponentCombinationData.setTransportationPackageData(transportationPackageData);
                        tourPackageAllComponentCombinationData.setTotalOptionPricePerPerson(tourPackageAllComponentCombinationData.getTotalOptionPricePerPerson().add(transportationPackageData.getUnitPrice()));
                    }
                    return tourPackageAllComponentCombinationData;

                })
                .toList();
        return new ComponentCombinationResponse(tourPackageAllComponentCombinationDataList, defaultCombinationData);
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

    /**
     * @param tourPackageId
     * @param requestId
     * @return
     * @throws TourPackageNotFoundException
     */
    @Override
    public TourPackageOptionListResponse getTourPackageCoreOptionsByTourPackageId(Long tourPackageId, String requestId) throws TourPackageNotFoundException {
        TourPackageEntity tourPackageEntity = getTourPackageEntityByPackageID(tourPackageId);
        /*List<TourPackageCoreComponentData> tourPackageCoreComponentDataList = tourPackageEntity.getTourPackageOptionEntities().stream()
                .map(tourPackageOptionEntity -> new TourPackageCoreComponentData(tourPackageOptionEntity))
                .toList();
        return new TourPackageOptionListResponse(tourPackageCoreComponentDataList, requestId);*/
        return null;
    }
}
