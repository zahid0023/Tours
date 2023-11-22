package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dto.data.OptionPriceData;
import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.food.FoodOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.spot.entry.SpotEntryOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageAvailabilitySetRequest;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.transporation.TransportationPackageRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageAllComponentListResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageAvailabilityResponse;
import com.ghuddy.backendapp.tours.exception.TourPackageNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.AvailabilityGeneratedTourPackageAllOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.AvailabilityGeneratedTourPackageInclusiveOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailabilityGeneratedAccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailabilityGeneratedAccommodationPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.food.AvailabilityGeneratedFoodOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.AvailabilityGeneratedMealPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.food.MealPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.AvailabilityGeneratedSpotEntryOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.AvailabilityGeneratedSpotEntryPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.SpotEntryPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.AvailabilityGeneratedTourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailabilityGeneratedTransferOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailabilityGeneratedTransferPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.TransferPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.AvailabilityGeneratedTransportationPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.TransportationPackageEntity;
import com.ghuddy.backendapp.tours.repository.AvailabilityGeneratedTourPackageRepository;
import com.ghuddy.backendapp.tours.service.*;
import com.ghuddy.backendapp.tours.utils.CombinationGenerator;
import com.ghuddy.backendapp.tours.utils.OptionPriceCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TourPackageAvailabilityServiceImpl implements TourPackageAvailabilityService {
    private final AvailabilityGeneratedTourPackageRepository availabilityGeneratedTourPackageRepository;
    private final TourPackageService tourPackageService;
    private final AccommodationService accommodationService;
    private final FoodService foodService;
    private final TransferService transferService;
    private final TransportationService transportationService;
    private final SpotEntryService spotEntryService;

    public TourPackageAvailabilityServiceImpl(AvailabilityGeneratedTourPackageRepository availabilityGeneratedTourPackageRepository,
                                              AccommodationService accommodationService,
                                              FoodService foodService,
                                              TransferService transferService,
                                              TransportationService transportationService,
                                              TourPackageService tourPackageService,
                                              SpotEntryService spotEntryService) {
        this.availabilityGeneratedTourPackageRepository = availabilityGeneratedTourPackageRepository;
        this.accommodationService = accommodationService;
        this.foodService = foodService;
        this.transferService = transferService;
        this.transportationService = transportationService;
        this.tourPackageService = tourPackageService;
        this.spotEntryService = spotEntryService;
    }


    /**
     * @param tourPackageEntity
     * @param requestId
     * @return
     */
    @Override
    public TourPackageAllComponentListResponse getAllComponentOptionsByTourPackage(TourPackageEntity tourPackageEntity, String requestId) {
        TourPackageAllComponentListResponse tourPackageAllComponentListResponse = new TourPackageAllComponentListResponse(tourPackageEntity, requestId);
        return tourPackageAllComponentListResponse;
    }

    /**
     * @param tourPackageAvailabilitySetRequest
     * @return
     */
    @Override
    @Transactional
    public TourPackageAvailabilityResponse generateTourPackageAvailabilityOptions(TourPackageAvailabilitySetRequest tourPackageAvailabilitySetRequest) throws TourPackageNotFoundException {
        AvailabilityGeneratedTourPackageEntity availabilityGeneratedTourPackageEntity = prepareTourPackageAvailabilityEntity(tourPackageAvailabilitySetRequest);

        // Set Inclusive Components Option Combinations
        availabilityGeneratedTourPackageEntity.setAvailabilityGeneratedTourPackageInclusiveOptionEntities(generateAvailableTourPackageOptionsCombinationsForAllInclusiveOptions(availabilityGeneratedTourPackageEntity));
        availabilityGeneratedTourPackageEntity.setAvailabilityGeneratedTourPackageAllOptionEntities(generateAvailableTourPackageOptionsCombinationsForAllOptions(availabilityGeneratedTourPackageEntity));

        availabilityGeneratedTourPackageRepository.save(availabilityGeneratedTourPackageEntity);
        return null;
    }

    private AvailabilityGeneratedTourPackageEntity prepareTourPackageAvailabilityEntity(TourPackageAvailabilitySetRequest tourPackageAvailabilitySetRequest) throws TourPackageNotFoundException {
        AvailabilityGeneratedTourPackageEntity availabilityGeneratedTourPackageEntity = new AvailabilityGeneratedTourPackageEntity();

        TourPackageEntity tourPackageEntity = tourPackageService.getTourPackageEntityByPackageID(tourPackageAvailabilitySetRequest.getTourPackageId());
        availabilityGeneratedTourPackageEntity.setTourPackageEntity(tourPackageEntity);
        availabilityGeneratedTourPackageEntity.setTourStartDate(tourPackageAvailabilitySetRequest.getTourStartDate());
        availabilityGeneratedTourPackageEntity.setTotalSeats(tourPackageAvailabilitySetRequest.getTotalSeats());
        availabilityGeneratedTourPackageEntity.setBookableSeats(tourPackageAvailabilitySetRequest.getBookableSeats());

        // Set Accommodations
        if (tourPackageAvailabilitySetRequest.getTourPackageAccommodationIsInclusive())
            availabilityGeneratedTourPackageEntity.setIsAccommodationInclusive(true);
        List<AccommodationOptionRequestForAvailability> accommodationOptions = tourPackageAvailabilitySetRequest.getAccommodationOptionRequestForAvailabilityList();
        if (accommodationOptions != null && !accommodationOptions.isEmpty()) {
            availabilityGeneratedTourPackageEntity.setAvailabilityGeneratedAccommodationOptionEntities(setAccommodations(availabilityGeneratedTourPackageEntity, accommodationOptions));
        }

        // Set Foods
        if (tourPackageAvailabilitySetRequest.getTourPackageFoodIsInclusive())
            availabilityGeneratedTourPackageEntity.setIsFoodInclusive(true);
        List<FoodOptionRequestForAvailability> foodOptions = tourPackageAvailabilitySetRequest.getFoodOptionRequestForAvailabilityList();
        if (foodOptions != null && !foodOptions.isEmpty()) {
            availabilityGeneratedTourPackageEntity.setAvailabilityGeneratedFoodOptionEntities(setFoods(availabilityGeneratedTourPackageEntity, foodOptions));
        }

        // Set Transfers
        if (tourPackageAvailabilitySetRequest.getTourPackageTransferIsInclusive())
            availabilityGeneratedTourPackageEntity.setIsTransferInclusive(true);
        List<TransferOptionRequestForAvailability> transferOptions = tourPackageAvailabilitySetRequest.getTransferOptionRequestForAvailabilityList();
        if (transferOptions != null && !transferOptions.isEmpty()) {
            availabilityGeneratedTourPackageEntity.setAvailabilityGeneratedTransferOptionEntities(setTransfers(availabilityGeneratedTourPackageEntity, transferOptions));
        }

        // Set Transportation
        List<TransportationPackageRequestForAvailability> transportationOptions = tourPackageAvailabilitySetRequest.getTransportationPackageRequestForAvailabilityList();
        if (transportationOptions != null && !transportationOptions.isEmpty()) {
            availabilityGeneratedTourPackageEntity.setAvailabilityGeneratedTransportationPackageEntities(setTransportations(availabilityGeneratedTourPackageEntity, transportationOptions));
        }

        // Set Guide Package
        if (tourPackageAvailabilitySetRequest.getTourPackageGuideIsInclusive() != null && tourPackageAvailabilitySetRequest.getTourPackageGuideIsInclusive())
            availabilityGeneratedTourPackageEntity.setIsGuideInclusive(true);

        // Set Spot Entry
        if (tourPackageAvailabilitySetRequest.getTourPackageSpotEntryIsInclusive() != null && tourPackageAvailabilitySetRequest.getTourPackageSpotEntryIsInclusive()) {
            availabilityGeneratedTourPackageEntity.setIsSpotEntryInclusive(true);
        }
        List<SpotEntryOptionRequestForAvailability> spotEntryOptions = tourPackageAvailabilitySetRequest.getSpotEntryOptionRequestForAvailabilityList();
        log.info(spotEntryOptions.toString());
        if (spotEntryOptions != null && !spotEntryOptions.isEmpty()) {
            availabilityGeneratedTourPackageEntity.setAvailabilityGeneratedSpotEntryOptionEntities(setSpotEntries(availabilityGeneratedTourPackageEntity, spotEntryOptions));
        }
        return availabilityGeneratedTourPackageEntity;
    }

    private List<AvailabilityGeneratedAccommodationOptionEntity> setAccommodations(AvailabilityGeneratedTourPackageEntity availabilityGeneratedTourPackageEntity, List<AccommodationOptionRequestForAvailability> accommodationOptionRequestForAvailabilityList) {
        Set<Long> accommodationPackageIds = accommodationOptionRequestForAvailabilityList.stream()
                .flatMap(accommodationOptionRequestForAvailability ->
                        accommodationOptionRequestForAvailability.getAccommodationPackageRequestForAvailabilityList().stream()
                                .map(accommodationPackageRequestForAvailability -> accommodationPackageRequestForAvailability.getAccommodationPackageId())
                )
                .collect(Collectors.toSet());
        log.info(accommodationOptionRequestForAvailabilityList.toString());
        log.info(accommodationPackageIds.toString());
        Map<Long, AccommodationPackageEntity> accommodationPackageEntityMap = accommodationService.getAccommodationPackageEntitiesById(accommodationPackageIds);

        return accommodationOptionRequestForAvailabilityList.stream()
                .map(accommodationOptionRequestForAvailability -> {
                    AvailabilityGeneratedAccommodationOptionEntity availabilityGeneratedAccommodationOptionEntity = new AvailabilityGeneratedAccommodationOptionEntity();
                    availabilityGeneratedAccommodationOptionEntity.setAvailabilityGeneratedTourPackageEntity(availabilityGeneratedTourPackageEntity);
                    availabilityGeneratedAccommodationOptionEntity.setTotalOptionPricePerPerson(BigDecimal.ZERO);
                    List<AvailabilityGeneratedAccommodationPackageEntity> availabilityGeneratedAccommodationPackageEntityList = accommodationOptionRequestForAvailability.getAccommodationPackageRequestForAvailabilityList().stream()
                            .map(accommodationPackageRequestForAvailability -> {
                                AvailabilityGeneratedAccommodationPackageEntity availabilityGeneratedAccommodationPackageEntity = new AvailabilityGeneratedAccommodationPackageEntity();
                                availabilityGeneratedAccommodationPackageEntity.setAvailabilityGeneratedAccommodationOptionEntity(availabilityGeneratedAccommodationOptionEntity);
                                AccommodationPackageEntity accommodationPackageEntity = accommodationPackageEntityMap.get(accommodationPackageRequestForAvailability.getAccommodationPackageId());
                                availabilityGeneratedAccommodationPackageEntity.setAccommodationPackageEntity(accommodationPackageEntityMap.get(accommodationPackageRequestForAvailability.getAccommodationPackageId()));
                                BigDecimal perPersonAccommodationPackagePrice = accommodationPackageRequestForAvailability.getPerNightRoomPrice().divideToIntegralValue(BigDecimal.valueOf(accommodationPackageEntity.getSuitableForPersons()));
                                availabilityGeneratedAccommodationPackageEntity.setAccommodationPackagePrice(accommodationPackageRequestForAvailability.getPerNightRoomPrice());
                                availabilityGeneratedAccommodationOptionEntity.setTotalOptionPricePerPerson(availabilityGeneratedAccommodationOptionEntity.getTotalOptionPricePerPerson().add(perPersonAccommodationPackagePrice));
                                return availabilityGeneratedAccommodationPackageEntity;
                            })
                            .toList();
                    availabilityGeneratedAccommodationOptionEntity.setAvailabilityGeneratedAccommodationPackageEntities(availabilityGeneratedAccommodationPackageEntityList);
                    return availabilityGeneratedAccommodationOptionEntity;
                })
                .collect(Collectors.toList());
    }

    private List<AvailabilityGeneratedFoodOptionEntity> setFoods(AvailabilityGeneratedTourPackageEntity availabilityGeneratedTourPackageEntity, List<FoodOptionRequestForAvailability> foodOptions) {
        Set<Long> mealPackageIds = foodOptions.stream()
                .flatMap(foodOptionRequest ->
                        foodOptionRequest.getMealTypeWiseMealPackages().values().stream()
                                .flatMap(List::stream))
                .collect(Collectors.toSet());

        Map<Long, MealPackageEntity> mealPackageEntityMap = foodService.getMealPackageEntitiesByIds(mealPackageIds);
        List<AvailabilityGeneratedFoodOptionEntity> availabilityGeneratedFoodOptionEntityList = new LinkedList<>();

        foodOptions.stream()
                .forEach(foodOptionRequestForAvailability -> {
                    List<List<?>> lists = new LinkedList<>();
                    foodOptionRequestForAvailability.getMealTypeWiseMealPackages().entrySet().stream()
                            .forEach(integerListEntry -> {
                                List<AvailabilityGeneratedMealPackageEntity> availabilityGeneratedMealPackageEntityList = integerListEntry.getValue().stream()
                                        .map(mealPackageId -> {
                                            AvailabilityGeneratedMealPackageEntity availabilityGeneratedMealPackageEntity = new AvailabilityGeneratedMealPackageEntity();
                                            availabilityGeneratedMealPackageEntity.setMealPackageEntity(mealPackageEntityMap.get(mealPackageId));
                                            availabilityGeneratedMealPackageEntity.setMealPackageAvailableInDay(foodOptionRequestForAvailability.getDayNumber());
                                            return availabilityGeneratedMealPackageEntity;
                                        })
                                        .toList();
                                lists.add(availabilityGeneratedMealPackageEntityList);
                            });
                    List<List<?>> combinations = CombinationGenerator.generateCombinations(lists);
                    combinations.forEach(combination -> {
                        AvailabilityGeneratedFoodOptionEntity availabilityGeneratedFoodOptionEntity = new AvailabilityGeneratedFoodOptionEntity();
                        List<AvailabilityGeneratedMealPackageEntity> availabilityGeneratedMealPackageEntityList = (List<AvailabilityGeneratedMealPackageEntity>) combination;
                        availabilityGeneratedMealPackageEntityList = availabilityGeneratedMealPackageEntityList.stream().peek(availabilityGeneratedMealPackageEntity -> availabilityGeneratedMealPackageEntity.setAvailabilityGeneratedFoodOptionEntity(availabilityGeneratedFoodOptionEntity)).toList();
                        availabilityGeneratedFoodOptionEntity.setAvailabilityGeneratedMealPackageEntities(availabilityGeneratedMealPackageEntityList);
                        availabilityGeneratedFoodOptionEntity.setAvailabilityGeneratedTourPackageEntity(availabilityGeneratedTourPackageEntity);
                        availabilityGeneratedFoodOptionEntityList.add(availabilityGeneratedFoodOptionEntity);
                    });
                });
        return availabilityGeneratedFoodOptionEntityList;
    }

    private List<AvailabilityGeneratedTransferOptionEntity> setTransfers(AvailabilityGeneratedTourPackageEntity availabilityGeneratedTourPackageEntity, List<TransferOptionRequestForAvailability> transferOptionRequestForAvailabilityList) {
        Set<Long> transferPackageIds = transferOptionRequestForAvailabilityList.stream()
                .flatMap(transferOptionRequestForAvailability ->
                        transferOptionRequestForAvailability.getTransferPackageRequestForAvailabilityList().stream()
                                .map(transferPackageRequestForAvailability -> transferPackageRequestForAvailability.getTransferPackageId())
                )
                .collect(Collectors.toSet());
        Map<Long, TransferPackageEntity> transferPackageEntityMap = transferService.getTransferPackageEntitiesById(transferPackageIds);

        return transferOptionRequestForAvailabilityList.stream()
                .map(transferOptionRequestForAvailability -> {
                    AvailabilityGeneratedTransferOptionEntity availabilityGeneratedTransferOptionEntity = new AvailabilityGeneratedTransferOptionEntity();
                    availabilityGeneratedTransferOptionEntity.setAvailabilityGeneratedTourPackageEntity(availabilityGeneratedTourPackageEntity);
                    availabilityGeneratedTransferOptionEntity.setTotalOptionPricePerPerson(BigDecimal.ZERO);
                    List<AvailabilityGeneratedTransferPackageEntity> availabilityGeneratedTransferPackageEntityList = transferOptionRequestForAvailability.getTransferPackageRequestForAvailabilityList().stream()
                            .map(transferPackageRequestForAvailability -> {
                                AvailabilityGeneratedTransferPackageEntity availabilityGeneratedTransferPackageEntity = new AvailabilityGeneratedTransferPackageEntity();
                                availabilityGeneratedTransferPackageEntity.setAvailabilityGeneratedTransferOptionEntity(availabilityGeneratedTransferOptionEntity);
                                TransferPackageEntity transferPackageEntity = transferPackageEntityMap.get(transferPackageRequestForAvailability.getTransferPackageId());
                                availabilityGeneratedTransferPackageEntity.setTransferPackageEntity(transferPackageEntityMap.get(transferPackageRequestForAvailability.getTransferPackageId()));
                                int numberOfTravellers = availabilityGeneratedTourPackageEntity.getTourPackageEntity().getTourPackageType().getSuitableFor();
                                int suitableForPersons = transferPackageEntity.getSuitableForPersons();
                                BigDecimal perVehiclePerTripPrice = transferPackageRequestForAvailability.getPerVehiclePerTripPrice();
                                BigDecimal perPersonTransferPackagePrice = perVehiclePerTripPrice.divideToIntegralValue(BigDecimal.valueOf(suitableForPersons > numberOfTravellers ? numberOfTravellers : suitableForPersons));
                                availabilityGeneratedTransferPackageEntity.setPerVehiclePerTripPrice(perVehiclePerTripPrice);
                                availabilityGeneratedTransferOptionEntity.setTotalOptionPricePerPerson(availabilityGeneratedTransferOptionEntity.getTotalOptionPricePerPerson().add(perPersonTransferPackagePrice));
                                return availabilityGeneratedTransferPackageEntity;
                            })
                            .toList();
                    availabilityGeneratedTransferOptionEntity.setAvailabilityGeneratedTransferPackageEntities(availabilityGeneratedTransferPackageEntityList);
                    return availabilityGeneratedTransferOptionEntity;
                })
                .collect(Collectors.toList());
    }

    private List<AvailabilityGeneratedTransportationPackageEntity> setTransportations(AvailabilityGeneratedTourPackageEntity availabilityGeneratedTourPackageEntity, List<TransportationPackageRequestForAvailability> transportationPackageRequestForAvailabilityList) {
        Set<Long> transportationPackageIds = transportationPackageRequestForAvailabilityList.stream()
                .map(transportationPackageRequestForAvailability -> transportationPackageRequestForAvailability.getTransportationPackageId())
                .collect(Collectors.toSet());
        Map<Long, TransportationPackageEntity> transportationPackageEntityMap = transportationService.getTransferPackageEntitiesById(transportationPackageIds);
        return transportationPackageRequestForAvailabilityList.stream()
                .map(transportationPackageRequestForAvailability -> {
                    AvailabilityGeneratedTransportationPackageEntity availabilityGeneratedTransportationPackageEntity = new AvailabilityGeneratedTransportationPackageEntity();
                    availabilityGeneratedTransportationPackageEntity.setAvailabilityGeneratedTourPackageEntity(availabilityGeneratedTourPackageEntity);
                    TransportationPackageEntity transportationPackageEntity = transportationPackageEntityMap.get(transportationPackageRequestForAvailability.getTransportationPackageId());
                    availabilityGeneratedTransportationPackageEntity.setTransportationPackageEntity(transportationPackageEntity);
                    BigDecimal perPersonTransportationPackagePrice = transportationPackageRequestForAvailability.getTransportationPackagePrice();
                    availabilityGeneratedTransportationPackageEntity.setTransportationPackagePrice(perPersonTransportationPackagePrice);
                    return availabilityGeneratedTransportationPackageEntity;
                })
                .toList();
    }

    private List<AvailabilityGeneratedSpotEntryOptionEntity> setSpotEntries(AvailabilityGeneratedTourPackageEntity availabilityGeneratedTourPackageEntity, List<SpotEntryOptionRequestForAvailability> spotEntryOptionRequestForAvailabilityList) {
        Set<Long> spotEntryIds = spotEntryOptionRequestForAvailabilityList.stream()
                .flatMap(spotEntryOptionRequestForAvailability ->
                        spotEntryOptionRequestForAvailability.getSpotEntryPackageRequestForAvailabilityList().stream()
                                .map(spotEntryPackageRequestForAvailability -> spotEntryPackageRequestForAvailability.getSpotEntryId())
                )
                .collect(Collectors.toSet());
        Map<Long, SpotEntryPackageEntity> spotEntryPackageEntityMap = spotEntryService.getSpotEntryPackageEntitiesById(spotEntryIds);

        return spotEntryOptionRequestForAvailabilityList.stream()
                .map(spotEntryOptionRequestForAvailability -> {
                    AvailabilityGeneratedSpotEntryOptionEntity availabilityGeneratedSpotEntryOptionEntity = new AvailabilityGeneratedSpotEntryOptionEntity();
                    availabilityGeneratedSpotEntryOptionEntity.setAvailabilityGeneratedTourPackageEntity(availabilityGeneratedTourPackageEntity);
                    availabilityGeneratedSpotEntryOptionEntity.setTotalOptionPricePerPerson(BigDecimal.ZERO);
                    List<AvailabilityGeneratedSpotEntryPackageEntity> availabilityGeneratedTransferPackageEntityList = spotEntryOptionRequestForAvailability.getSpotEntryPackageRequestForAvailabilityList().stream()
                            .map(spotEntryPackageRequestForAvailability -> {
                                AvailabilityGeneratedSpotEntryPackageEntity availabilityGeneratedSpotEntryPackageEntity = new AvailabilityGeneratedSpotEntryPackageEntity();
                                availabilityGeneratedSpotEntryPackageEntity.setAvailabilityGeneratedSpotEntryOptionEntity(availabilityGeneratedSpotEntryOptionEntity);
                                SpotEntryPackageEntity spotEntryPackageEntity = spotEntryPackageEntityMap.get(spotEntryPackageRequestForAvailability.getSpotEntryId());
                                availabilityGeneratedSpotEntryPackageEntity.setSpotEntryPackageEntity(spotEntryPackageEntity);
                                BigDecimal perPersonSpotEntryPackagePrice = spotEntryPackageRequestForAvailability.getSpotEntryPrice();
                                availabilityGeneratedSpotEntryPackageEntity.setSpotEntryPricePerPerson(perPersonSpotEntryPackagePrice);
                                availabilityGeneratedSpotEntryOptionEntity.setTotalOptionPricePerPerson(availabilityGeneratedSpotEntryOptionEntity.getTotalOptionPricePerPerson().add(perPersonSpotEntryPackagePrice));
                                return availabilityGeneratedSpotEntryPackageEntity;
                            })
                            .toList();
                    availabilityGeneratedSpotEntryOptionEntity.setAvailabilityGeneratedSpotEntryPackageEntities(availabilityGeneratedTransferPackageEntityList);
                    return availabilityGeneratedSpotEntryOptionEntity;
                })
                .collect(Collectors.toList());
    }


    private List<AvailabilityGeneratedTourPackageInclusiveOptionEntity> generateAvailableTourPackageOptionsCombinationsForAllInclusiveOptions(AvailabilityGeneratedTourPackageEntity availabilityGeneratedTourPackageEntity) throws TourPackageNotFoundException {
        List<List<?>> tourPackageCombinationToCheck = combinationToCheckForAllInclusiveOptions(availabilityGeneratedTourPackageEntity);
        List<List<?>> combinations = CombinationGenerator.generateCombinations(tourPackageCombinationToCheck);

        List<AvailabilityGeneratedTourPackageInclusiveOptionEntity> availabilityGeneratedTourPackageInclusiveOptionEntityList = combinations.stream()
                .map(option -> {
                    AvailabilityGeneratedTourPackageInclusiveOptionEntity inclusiveOptionsCombinationEntity = new AvailabilityGeneratedTourPackageInclusiveOptionEntity();
                    inclusiveOptionsCombinationEntity.setGhuddyPlatformCalculatedRate(BigDecimal.ZERO);
                    option.forEach(component -> {
                        if (component.getClass().isAssignableFrom(AvailabilityGeneratedAccommodationOptionEntity.class)) {
                            AvailabilityGeneratedAccommodationOptionEntity accommodationOptionEntity = (AvailabilityGeneratedAccommodationOptionEntity) component;
                            inclusiveOptionsCombinationEntity.setAvailabilityGeneratedAccommodationOptionEntity(accommodationOptionEntity);
                            inclusiveOptionsCombinationEntity.setGhuddyPlatformCalculatedRate(inclusiveOptionsCombinationEntity.getGhuddyPlatformCalculatedRate().add(accommodationOptionEntity.getTotalOptionPricePerPerson()));

                        } else if (component.getClass().isAssignableFrom(AvailabilityGeneratedFoodOptionEntity.class)) {
                            AvailabilityGeneratedFoodOptionEntity foodOptionEntity = (AvailabilityGeneratedFoodOptionEntity) component;
                            inclusiveOptionsCombinationEntity.setAvailabilityGeneratedFoodOptionEntity(foodOptionEntity);
                            inclusiveOptionsCombinationEntity.setGhuddyPlatformCalculatedRate(inclusiveOptionsCombinationEntity.getGhuddyPlatformCalculatedRate().add(foodOptionEntity.getTotalOptionPricePerPerson()));

                        } else if (component.getClass().isAssignableFrom(AvailabilityGeneratedTransferOptionEntity.class)) {
                            AvailabilityGeneratedTransferOptionEntity transferOptionEntity = (AvailabilityGeneratedTransferOptionEntity) component;
                            inclusiveOptionsCombinationEntity.setAvailabilityGeneratedTransferOptionEntity(transferOptionEntity);
                            inclusiveOptionsCombinationEntity.setGhuddyPlatformCalculatedRate(inclusiveOptionsCombinationEntity.getGhuddyPlatformCalculatedRate().add(transferOptionEntity.getTotalOptionPricePerPerson()));

                        } else if (component.getClass().isAssignableFrom(AvailabilityGeneratedSpotEntryOptionEntity.class)) {
                            AvailabilityGeneratedSpotEntryOptionEntity availabilityGeneratedSpotEntryOptionEntity = (AvailabilityGeneratedSpotEntryOptionEntity) component;
                            inclusiveOptionsCombinationEntity.setGhuddyPlatformCalculatedRate(inclusiveOptionsCombinationEntity.getGhuddyPlatformCalculatedRate().add(availabilityGeneratedSpotEntryOptionEntity.getTotalOptionPricePerPerson()));
                        }
                    });
                    inclusiveOptionsCombinationEntity.setTourPackageAvailabilityEntity(availabilityGeneratedTourPackageEntity);
                    OptionPriceData optionPriceData = OptionPriceCalculator.getBlackPrice(inclusiveOptionsCombinationEntity.getGhuddyPlatformCalculatedRate(), BigDecimal.ZERO);
                    optionPriceData = OptionPriceCalculator.getRedPrice(optionPriceData.getNetOptionPriceAfterGhuddyCommission(), BigDecimal.ZERO, optionPriceData);
                    inclusiveOptionsCombinationEntity.setGhuddyPlatformCalculatedRate(optionPriceData.getGhuddyPlatformCalculatedOptionPrice());
                    inclusiveOptionsCombinationEntity.setMerchantSubsidyAmount(optionPriceData.getMerchantSubsidyAmount());
                    inclusiveOptionsCombinationEntity.setNetOptionPriceAfterMerchantSubsidy(optionPriceData.getNetOptionPriceAfterMerchantSubsidy());
                    inclusiveOptionsCombinationEntity.setGhuddyPlatformCommissionAmount(optionPriceData.getGhuddyPlatformCommissionAmount());
                    inclusiveOptionsCombinationEntity.setNetOptionPriceAfterGhuddyCommission(optionPriceData.getNetOptionPriceAfterGhuddyCommission());
                    inclusiveOptionsCombinationEntity.setGhuddyWebsiteBlackPrice(optionPriceData.getGhuddyWebsiteBlackPrice());
                    inclusiveOptionsCombinationEntity.setGhuddySubsidyAmount(optionPriceData.getGhuddySubsidyAmount());
                    inclusiveOptionsCombinationEntity.setNetOptionPriceAfterGhuddySubsidy(optionPriceData.getNetOptionPriceAfterGhuddySubsidy());
                    inclusiveOptionsCombinationEntity.setGhuddyWebsiteRedPrice(optionPriceData.getGhuddyWebsiteRedPrice());
                    inclusiveOptionsCombinationEntity.setPaymentGatewayAmount(optionPriceData.getPaymentGateWayAmount());
                    return inclusiveOptionsCombinationEntity;
                })
                .toList();
        return availabilityGeneratedTourPackageInclusiveOptionEntityList;
    }

    private List<List<?>> combinationToCheckForAllInclusiveOptions(AvailabilityGeneratedTourPackageEntity availabilityGeneratedTourPackageEntity) {
        List<List<?>> tourPackageCombinationToCheck = new LinkedList<>();

        if (availabilityGeneratedTourPackageEntity.getIsAccommodationInclusive() && availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedAccommodationOptionEntities() != null &&
                !availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedAccommodationOptionEntities().isEmpty()) {
            tourPackageCombinationToCheck.add(availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedAccommodationOptionEntities());
        }

        if (availabilityGeneratedTourPackageEntity.getIsFoodInclusive() && availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedFoodOptionEntities() != null &&
                !availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedFoodOptionEntities().isEmpty()) {
            tourPackageCombinationToCheck.add(availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedFoodOptionEntities());
        }

        if (availabilityGeneratedTourPackageEntity.getIsTransferInclusive() && availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedTransferOptionEntities() != null &&
                !availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedTransferOptionEntities().isEmpty()) {
            tourPackageCombinationToCheck.add(availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedTransferOptionEntities());
        }

        if (availabilityGeneratedTourPackageEntity.getIsGuideInclusive() && availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedGuideOptionEntities() != null &&
                !availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedGuideOptionEntities().isEmpty()) {
            tourPackageCombinationToCheck.add(availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedGuideOptionEntities());
        }

        if (availabilityGeneratedTourPackageEntity.getIsSpotEntryInclusive() && availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedSpotEntryOptionEntities() != null &&
                !availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedSpotEntryOptionEntities().isEmpty()) {
            tourPackageCombinationToCheck.add(availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedSpotEntryOptionEntities());
        }

        return tourPackageCombinationToCheck;
    }

    private List<AvailabilityGeneratedTourPackageAllOptionEntity> generateAvailableTourPackageOptionsCombinationsForAllOptions(AvailabilityGeneratedTourPackageEntity availabilityGeneratedTourPackageEntity) {
        List<List<?>> tourPackageCombinationToCheck = combinationToCheckForAllOptions(availabilityGeneratedTourPackageEntity);
        List<List<?>> combinations = CombinationGenerator.generateCombinations(tourPackageCombinationToCheck);

        List<AvailabilityGeneratedTourPackageAllOptionEntity> availabilityGeneratedTourPackageInclusiveOptionEntityList = combinations.stream()
                .map(option -> {
                    AvailabilityGeneratedTourPackageAllOptionEntity allOptionsCombinationEntity = new AvailabilityGeneratedTourPackageAllOptionEntity();
                    allOptionsCombinationEntity.setGhuddyPlatformCalculatedOptionPrice(BigDecimal.ZERO);
                    option.forEach(component -> {
                        if (component.getClass().isAssignableFrom(AvailabilityGeneratedAccommodationOptionEntity.class)) {
                            AvailabilityGeneratedAccommodationOptionEntity accommodationOptionEntity = (AvailabilityGeneratedAccommodationOptionEntity) component;
                            allOptionsCombinationEntity.setAvailabilityGeneratedAccommodationOptionEntity(accommodationOptionEntity);
                            allOptionsCombinationEntity.setGhuddyPlatformCalculatedOptionPrice(allOptionsCombinationEntity.getGhuddyPlatformCalculatedOptionPrice().add(accommodationOptionEntity.getTotalOptionPricePerPerson()));

                        } else if (component.getClass().isAssignableFrom(AvailabilityGeneratedFoodOptionEntity.class)) {
                            AvailabilityGeneratedFoodOptionEntity foodOptionEntity = (AvailabilityGeneratedFoodOptionEntity) component;
                            allOptionsCombinationEntity.setAvailabilityGeneratedFoodOptionEntity(foodOptionEntity);
                            allOptionsCombinationEntity.setGhuddyPlatformCalculatedOptionPrice(allOptionsCombinationEntity.getGhuddyPlatformCalculatedOptionPrice().add(BigDecimal.ZERO));

                        } else if (component.getClass().isAssignableFrom(AvailabilityGeneratedTransferOptionEntity.class)) {
                            AvailabilityGeneratedTransferOptionEntity transferOptionEntity = (AvailabilityGeneratedTransferOptionEntity) component;
                            allOptionsCombinationEntity.setAvailabilityGeneratedTransferOptionEntity(transferOptionEntity);
                            allOptionsCombinationEntity.setGhuddyPlatformCalculatedOptionPrice(allOptionsCombinationEntity.getGhuddyPlatformCalculatedOptionPrice().add(transferOptionEntity.getTotalOptionPricePerPerson()));
                        } else if (component.getClass().isAssignableFrom(AvailabilityGeneratedTransportationPackageEntity.class)) {
                            AvailabilityGeneratedTransportationPackageEntity transportationPackageEntity = (AvailabilityGeneratedTransportationPackageEntity) component;
                            allOptionsCombinationEntity.setAvailabilityGeneratedTransportationPackageEntity(transportationPackageEntity);
                            allOptionsCombinationEntity.setGhuddyPlatformCalculatedOptionPrice(allOptionsCombinationEntity.getGhuddyPlatformCalculatedOptionPrice().add(transportationPackageEntity.getTransportationPackagePrice()));
                        } else if (component.getClass().isAssignableFrom(AvailabilityGeneratedSpotEntryOptionEntity.class)) {
                            AvailabilityGeneratedSpotEntryOptionEntity spotEntryOptionEntity = (AvailabilityGeneratedSpotEntryOptionEntity) component;
                            allOptionsCombinationEntity.setAvailabilityGeneratedSpotEntryOptionEntity(spotEntryOptionEntity);
                            allOptionsCombinationEntity.setGhuddyPlatformCalculatedOptionPrice(allOptionsCombinationEntity.getGhuddyPlatformCalculatedOptionPrice().add(spotEntryOptionEntity.getTotalOptionPricePerPerson()));
                        }
                    });
                    allOptionsCombinationEntity.setAvailabilityGeneratedTourPackageEntity(availabilityGeneratedTourPackageEntity);
                    OptionPriceData optionPriceData = OptionPriceCalculator.getBlackPrice(allOptionsCombinationEntity.getGhuddyPlatformCalculatedOptionPrice(), BigDecimal.ZERO);
                    optionPriceData = OptionPriceCalculator.getRedPrice(optionPriceData.getNetOptionPriceAfterGhuddyCommission(), BigDecimal.ZERO, optionPriceData);
                    allOptionsCombinationEntity.setGhuddyPlatformCalculatedOptionPrice(optionPriceData.getGhuddyPlatformCalculatedOptionPrice());
                    allOptionsCombinationEntity.setMerchantSubsidyAmount(optionPriceData.getMerchantSubsidyAmount());
                    allOptionsCombinationEntity.setNetOptionPriceAfterMerchantSubsidy(optionPriceData.getNetOptionPriceAfterMerchantSubsidy());
                    allOptionsCombinationEntity.setGhuddyPlatformCommissionAmount(optionPriceData.getGhuddyPlatformCommissionAmount());
                    allOptionsCombinationEntity.setNetOptionPriceAfterGhuddyCommission(optionPriceData.getNetOptionPriceAfterGhuddyCommission());
                    allOptionsCombinationEntity.setGhuddyWebsiteBlackPrice(optionPriceData.getGhuddyWebsiteBlackPrice());
                    allOptionsCombinationEntity.setGhuddySubsidyAmount(optionPriceData.getGhuddySubsidyAmount());
                    allOptionsCombinationEntity.setNetOptionPriceAfterGhuddySubsidy(optionPriceData.getNetOptionPriceAfterGhuddySubsidy());
                    allOptionsCombinationEntity.setGhuddyWebsiteRedPrice(optionPriceData.getGhuddyWebsiteRedPrice());
                    allOptionsCombinationEntity.setPaymentGatewayAmount(optionPriceData.getPaymentGateWayAmount());
                    return allOptionsCombinationEntity;
                })
                .toList();
        return availabilityGeneratedTourPackageInclusiveOptionEntityList;
    }

    private List<List<?>> combinationToCheckForAllOptions(AvailabilityGeneratedTourPackageEntity availabilityGeneratedTourPackageEntity) {
        List<List<?>> tourPackageCombinationToCheck = new LinkedList<>();

        if (availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedAccommodationOptionEntities() != null &&
                !availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedAccommodationOptionEntities().isEmpty()) {
            tourPackageCombinationToCheck.add(availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedAccommodationOptionEntities());
        }

        if (availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedFoodOptionEntities() != null &&
                !availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedFoodOptionEntities().isEmpty()) {
            tourPackageCombinationToCheck.add(availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedFoodOptionEntities());
        }

        if (availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedTransferOptionEntities() != null &&
                !availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedTransferOptionEntities().isEmpty()) {
            tourPackageCombinationToCheck.add(availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedTransferOptionEntities());
        }
        if (availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedTransportationPackageEntities() != null &&
                !availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedTransportationPackageEntities().isEmpty()) {
            tourPackageCombinationToCheck.add(availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedTransportationPackageEntities());
        }

        if (availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedGuideOptionEntities() != null &&
                !availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedGuideOptionEntities().isEmpty()) {
            tourPackageCombinationToCheck.add(availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedGuideOptionEntities());
        }

        if (availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedSpotEntryOptionEntities() != null &&
                !availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedSpotEntryOptionEntities().isEmpty()) {
            tourPackageCombinationToCheck.add(availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedSpotEntryOptionEntities());
        }

        return tourPackageCombinationToCheck;
    }
}
