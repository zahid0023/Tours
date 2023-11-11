package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.food.FoodOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageAvailabilitySetRequest;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.transporation.TransportationPackageRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageAvailabilityResponse;
import com.ghuddy.backendapp.tours.exception.TourPackageNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.AvailabilityGeneratedTourPackageOptionsWoTransportationEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailabilityGeneratedAccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailabilityGeneratedAccommodationPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.food.AvailabilityGeneratedFoodOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.AvailabilityGeneratedMealPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.food.MealPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageAvailabilityEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailabilityGeneratedTransferOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailabilityGeneratedTransferPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.TransferPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.AvailabilityGeneratedTransportationPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.TransportationPackageEntity;
import com.ghuddy.backendapp.tours.repository.TourPackageAvailabilityRepository;
import com.ghuddy.backendapp.tours.service.*;
import com.ghuddy.backendapp.tours.utils.CombinationGenerator;
import com.ghuddy.backendapp.tours.utils.OptionPriceCalculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TourPackageAvailabilityServiceImpl implements TourPackageAvailabilityService {
    private final TourPackageAvailabilityRepository tourPackageAvailabilityRepository;
    private final TourPackageService tourPackageService;
    private final AccommodationService accommodationService;
    private final FoodService foodService;
    private final TransferService transferService;
    private final TransportationService transportationService;

    public TourPackageAvailabilityServiceImpl(TourPackageAvailabilityRepository tourPackageAvailabilityRepository,
                                              AccommodationService accommodationService,
                                              FoodService foodService,
                                              TransferService transferService,
                                              TransportationService transportationService,
                                              TourPackageService tourPackageService) {
        this.tourPackageAvailabilityRepository = tourPackageAvailabilityRepository;
        this.accommodationService = accommodationService;
        this.foodService = foodService;
        this.transferService = transferService;
        this.transportationService = transportationService;
        this.tourPackageService = tourPackageService;
    }

    /**
     * @param tourPackageAvailabilitySetRequest
     * @return
     */
    @Override
    @Transactional
    public TourPackageAvailabilityResponse generateTourPackageAvailabilityOptions(TourPackageAvailabilitySetRequest tourPackageAvailabilitySetRequest) throws TourPackageNotFoundException {
        TourPackageAvailabilityEntity tourPackageAvailabilityEntity = prepareTourPackageAvailabilityEntity(tourPackageAvailabilitySetRequest);
        List<AvailabilityGeneratedTourPackageOptionsWoTransportationEntity> optionsWoTransportationEntities = generateAvailableTourPackageOptionsWithoutTransportationsPackages(tourPackageAvailabilityEntity);
        tourPackageAvailabilityEntity.setAvailabilityGeneratedTourPackageOptionsWoTransportationEntities(optionsWoTransportationEntities);
        tourPackageAvailabilityRepository.save(tourPackageAvailabilityEntity);
        return null;
    }

    private TourPackageAvailabilityEntity prepareTourPackageAvailabilityEntity(TourPackageAvailabilitySetRequest tourPackageAvailabilitySetRequest) throws TourPackageNotFoundException {
        TourPackageAvailabilityEntity tourPackageAvailabilityEntity = new TourPackageAvailabilityEntity();

        TourPackageEntity tourPackageEntity = tourPackageService.getTourPackageEntityByPackageID(tourPackageAvailabilitySetRequest.getTourPackageId());
        tourPackageAvailabilityEntity.setTourPackageEntity(tourPackageEntity);
        tourPackageAvailabilityEntity.setTourStartDate(tourPackageAvailabilitySetRequest.getTourStartDate());
        tourPackageAvailabilityEntity.setTotalSeats(tourPackageAvailabilitySetRequest.getTotalSeats());
        tourPackageAvailabilityEntity.setBookableSeats(tourPackageAvailabilitySetRequest.getBookableSeats());

        // Set Accommodations
        List<AccommodationOptionRequestForAvailability> accommodationOptions = tourPackageAvailabilitySetRequest.getAccommodationOptionRequestForAvailabilityList();
        if (accommodationOptions != null && !accommodationOptions.isEmpty()) {
            tourPackageAvailabilityEntity.setAvailabilityGeneratedAccommodationOptionEntities(setAccommodations(tourPackageAvailabilityEntity, accommodationOptions));
        }

        // Set Foods
        List<FoodOptionRequestForAvailability> foodOptions = tourPackageAvailabilitySetRequest.getFoodOptionRequestForAvailabilityList();
        if (foodOptions != null && !foodOptions.isEmpty()) {
            tourPackageAvailabilityEntity.setAvailabilityGeneratedFoodOptionEntities(setFoods(tourPackageAvailabilityEntity, foodOptions));
        }

        // Set Transfers
        List<TransferOptionRequestForAvailability> transferOptions = tourPackageAvailabilitySetRequest.getTransferOptionRequestForAvailabilityList();
        if (transferOptions != null && !transferOptions.isEmpty()) {
            tourPackageAvailabilityEntity.setAvailabilityGeneratedTransferOptionEntities(setTransfers(tourPackageAvailabilityEntity, transferOptions));
        }

        // Set Transportation
        List<TransportationPackageRequestForAvailability> transportationOptions = tourPackageAvailabilitySetRequest.getTransportationPackageRequestForAvailabilityList();
        if (transportationOptions != null && !transportationOptions.isEmpty()) {
            tourPackageAvailabilityEntity.setAvailabilityGeneratedTransportationPackageEntities(setTransportations(tourPackageAvailabilityEntity, transportationOptions));
        }
        return tourPackageAvailabilityEntity;
    }

    private List<AvailabilityGeneratedAccommodationOptionEntity> setAccommodations(TourPackageAvailabilityEntity tourPackageAvailabilityEntity, List<AccommodationOptionRequestForAvailability> accommodationOptionRequestForAvailabilityList) {
        Set<Long> accommodationPackageIds = accommodationOptionRequestForAvailabilityList.stream()
                .flatMap(accommodationOptionRequestForAvailability ->
                        accommodationOptionRequestForAvailability.getAccommodationPackageRequestForAvailabilityList().stream()
                                .map(accommodationPackageRequestForAvailability -> accommodationPackageRequestForAvailability.getAccommodationPackageId())
                )
                .collect(Collectors.toSet());
        Map<Long, AccommodationPackageEntity> accommodationPackageEntityMap = accommodationService.getAccommodationPackageEntitiesById(accommodationPackageIds);

        return accommodationOptionRequestForAvailabilityList.stream()
                .map(accommodationOptionRequestForAvailability -> {
                    AvailabilityGeneratedAccommodationOptionEntity availabilityGeneratedAccommodationOptionEntity = new AvailabilityGeneratedAccommodationOptionEntity();
                    availabilityGeneratedAccommodationOptionEntity.setTourPackageAvailabilityEntity(tourPackageAvailabilityEntity);
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
                    availabilityGeneratedAccommodationOptionEntity.setIsDefault(accommodationOptionRequestForAvailability.getIsDefault());
                    return availabilityGeneratedAccommodationOptionEntity;
                })
                .collect(Collectors.toList());
    }

    private List<AvailabilityGeneratedFoodOptionEntity> setFoods(TourPackageAvailabilityEntity tourPackageAvailabilityEntity, List<FoodOptionRequestForAvailability> foodOptionRequestForAvailabilityList) {
        Set<Long> mealPackageIds = foodOptionRequestForAvailabilityList.stream()
                .flatMap(foodOptionRequestForAvailability ->
                        foodOptionRequestForAvailability.getMealPackageRequestForAvailabilityList().stream()
                                .map(mealPackageRequestForAvailability -> mealPackageRequestForAvailability.getMealPackageId())
                )
                .collect(Collectors.toSet());
        Map<Long, MealPackageEntity> mealPackageEntityMap = foodService.getMealPackageEntitiesByIds(mealPackageIds);

        return foodOptionRequestForAvailabilityList.stream()
                .map(foodOptionRequestForAvailability -> {
                    AvailabilityGeneratedFoodOptionEntity availabilityGeneratedFoodOptionEntity = new AvailabilityGeneratedFoodOptionEntity();
                    availabilityGeneratedFoodOptionEntity.setTourPackageAvailabilityEntity(tourPackageAvailabilityEntity);
                    availabilityGeneratedFoodOptionEntity.setTotalOptionPricePerPerson(BigDecimal.ZERO);
                    List<AvailabilityGeneratedMealPackageEntity> availabilityGeneratedMealPackageEntityList = foodOptionRequestForAvailability.getMealPackageRequestForAvailabilityList().stream()
                            .map(mealPackageRequestForAvailability -> {
                                AvailabilityGeneratedMealPackageEntity availabilityGeneratedMealPackageEntity = new AvailabilityGeneratedMealPackageEntity();
                                availabilityGeneratedMealPackageEntity.setAvailabilityGeneratedFoodOptionEntity(availabilityGeneratedFoodOptionEntity);
                                MealPackageEntity mealPackageEntity = mealPackageEntityMap.get(mealPackageRequestForAvailability.getMealPackageId());
                                availabilityGeneratedMealPackageEntity.setMealPackageEntity(mealPackageEntity);
                                BigDecimal perPersonMealPackagePrice = mealPackageRequestForAvailability.getMealPackagePrice();
                                availabilityGeneratedMealPackageEntity.setMealPackagePrice(perPersonMealPackagePrice);
                                availabilityGeneratedFoodOptionEntity.setTotalOptionPricePerPerson(availabilityGeneratedFoodOptionEntity.getTotalOptionPricePerPerson().add(perPersonMealPackagePrice));
                                return availabilityGeneratedMealPackageEntity;
                            })
                            .toList();
                    availabilityGeneratedFoodOptionEntity.setAvailabilityGeneratedMealPackageEntities(availabilityGeneratedMealPackageEntityList);
                    availabilityGeneratedFoodOptionEntity.setIsDefault(foodOptionRequestForAvailability.getIsDefault());
                    return availabilityGeneratedFoodOptionEntity;
                })
                .collect(Collectors.toList());
    }

    private List<AvailabilityGeneratedTransferOptionEntity> setTransfers(TourPackageAvailabilityEntity tourPackageAvailabilityEntity, List<TransferOptionRequestForAvailability> transferOptionRequestForAvailabilityList) {
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
                    availabilityGeneratedTransferOptionEntity.setTourPackageAvailabilityEntity(tourPackageAvailabilityEntity);
                    availabilityGeneratedTransferOptionEntity.setTotalOptionPricePerPerson(BigDecimal.ZERO);
                    List<AvailabilityGeneratedTransferPackageEntity> availabilityGeneratedTransferPackageEntityList = transferOptionRequestForAvailability.getTransferPackageRequestForAvailabilityList().stream()
                            .map(transferPackageRequestForAvailability -> {
                                AvailabilityGeneratedTransferPackageEntity availabilityGeneratedTransferPackageEntity = new AvailabilityGeneratedTransferPackageEntity();
                                availabilityGeneratedTransferPackageEntity.setAvailabilityGeneratedTransferOptionEntity(availabilityGeneratedTransferOptionEntity);
                                TransferPackageEntity transferPackageEntity = transferPackageEntityMap.get(transferPackageRequestForAvailability.getTransferPackageId());
                                availabilityGeneratedTransferPackageEntity.setTransferPackageEntity(transferPackageEntityMap.get(transferPackageRequestForAvailability.getTransferPackageId()));
                                int numberOfTravellers = tourPackageAvailabilityEntity.getTourPackageEntity().getTourPackageType().getSuitableFor();
                                int suitableForPersons = transferPackageEntity.getSuitableForPersons();
                                BigDecimal perVehiclePerTripPrice = transferPackageRequestForAvailability.getPerVehiclePerTripPrice();
                                BigDecimal perPersonTransferPackagePrice = perVehiclePerTripPrice.divideToIntegralValue(BigDecimal.valueOf(suitableForPersons > numberOfTravellers ? numberOfTravellers : suitableForPersons));
                                availabilityGeneratedTransferPackageEntity.setPerVehiclePerTripPrice(perVehiclePerTripPrice);
                                availabilityGeneratedTransferOptionEntity.setTotalOptionPricePerPerson(availabilityGeneratedTransferOptionEntity.getTotalOptionPricePerPerson().add(perPersonTransferPackagePrice));
                                return availabilityGeneratedTransferPackageEntity;
                            })
                            .toList();
                    availabilityGeneratedTransferOptionEntity.setAvailabilityGeneratedTransferPackageEntities(availabilityGeneratedTransferPackageEntityList);
                    availabilityGeneratedTransferOptionEntity.setIsDefault(transferOptionRequestForAvailability.getIsDefault());
                    return availabilityGeneratedTransferOptionEntity;
                })
                .collect(Collectors.toList());
    }

    private List<AvailabilityGeneratedTransportationPackageEntity> setTransportations(TourPackageAvailabilityEntity tourPackageAvailabilityEntity, List<TransportationPackageRequestForAvailability> transportationPackageRequestForAvailabilityList) {
        Set<Long> transportationPackageIds = transportationPackageRequestForAvailabilityList.stream()
                .map(transportationPackageRequestForAvailability -> transportationPackageRequestForAvailability.getTransportationPackageId())
                .collect(Collectors.toSet());
        Map<Long, TransportationPackageEntity> transportationPackageEntityMap = transportationService.getTransferPackageEntitiesById(transportationPackageIds);
        return transportationPackageRequestForAvailabilityList.stream()
                .map(transportationPackageRequestForAvailability -> {
                    AvailabilityGeneratedTransportationPackageEntity availabilityGeneratedTransportationPackageEntity = new AvailabilityGeneratedTransportationPackageEntity();
                    availabilityGeneratedTransportationPackageEntity.setTourPackageAvailabilityEntity(tourPackageAvailabilityEntity);
                    TransportationPackageEntity transportationPackageEntity = transportationPackageEntityMap.get(transportationPackageRequestForAvailability.getTransportationPackageId());
                    availabilityGeneratedTransportationPackageEntity.setTransportationPackageEntity(transportationPackageEntity);
                    BigDecimal perPersonTransportationPackagePrice = transportationPackageRequestForAvailability.getTransportationPackagePrice();
                    availabilityGeneratedTransportationPackageEntity.setTransportationPackagePrice(perPersonTransportationPackagePrice);
                    return availabilityGeneratedTransportationPackageEntity;
                })
                .toList();
    }

    private List<List<?>> tourPackageCombinationsToCheckWithoutTransportationPackages(TourPackageAvailabilityEntity tourPackageAvailabilityEntity) {
        List<List<?>> tourPackageCombinationToCheck = new LinkedList<>();

        if (tourPackageAvailabilityEntity.getAvailabilityGeneratedAccommodationOptionEntities() != null &&
                !tourPackageAvailabilityEntity.getAvailabilityGeneratedAccommodationOptionEntities().isEmpty()) {
            tourPackageCombinationToCheck.add(tourPackageAvailabilityEntity.getAvailabilityGeneratedAccommodationOptionEntities());
        }

        if (tourPackageAvailabilityEntity.getAvailabilityGeneratedFoodOptionEntities() != null &&
                !tourPackageAvailabilityEntity.getAvailabilityGeneratedFoodOptionEntities().isEmpty()) {
            tourPackageCombinationToCheck.add(tourPackageAvailabilityEntity.getAvailabilityGeneratedFoodOptionEntities());
        }

        if (tourPackageAvailabilityEntity.getAvailabilityGeneratedTransferOptionEntities() != null &&
                !tourPackageAvailabilityEntity.getAvailabilityGeneratedTransferOptionEntities().isEmpty()) {
            tourPackageCombinationToCheck.add(tourPackageAvailabilityEntity.getAvailabilityGeneratedTransferOptionEntities());
        }

        if (tourPackageAvailabilityEntity.getAvailabilityGeneratedGuideOptionEntities() != null &&
                !tourPackageAvailabilityEntity.getAvailabilityGeneratedGuideOptionEntities().isEmpty()) {
            tourPackageCombinationToCheck.add(tourPackageAvailabilityEntity.getAvailabilityGeneratedGuideOptionEntities());
        }

        if (tourPackageAvailabilityEntity.getAvailabilityGeneratedSpotEntries() != null &&
                !tourPackageAvailabilityEntity.getAvailabilityGeneratedSpotEntries().isEmpty()) {
            tourPackageCombinationToCheck.add(tourPackageAvailabilityEntity.getAvailabilityGeneratedSpotEntries());
        }
        return tourPackageCombinationToCheck;
    }

    private List<List<?>> tourPackageCombinationToCheckWithTransportationPackages(TourPackageAvailabilityEntity tourPackageAvailabilityEntity) {
        List<List<?>> tourPackageCombinationToCheck = tourPackageCombinationsToCheckWithoutTransportationPackages(tourPackageAvailabilityEntity);
        if (tourPackageAvailabilityEntity.getAvailabilityGeneratedTransportationPackageEntities() != null && !tourPackageAvailabilityEntity.getAvailabilityGeneratedTransportationPackageEntities().isEmpty())
            tourPackageCombinationToCheck.add(tourPackageAvailabilityEntity.getAvailabilityGeneratedTransportationPackageEntities());
        return tourPackageCombinationToCheck;
    }

    private List<AvailabilityGeneratedTourPackageOptionsWoTransportationEntity> generateAvailableTourPackageOptionsWithoutTransportationsPackages(TourPackageAvailabilityEntity tourPackageAvailabilityEntity) throws TourPackageNotFoundException {
        List<List<?>> tourPackageCombinationToCheck = tourPackageCombinationsToCheckWithoutTransportationPackages(tourPackageAvailabilityEntity);
        List<List<?>> combinations = CombinationGenerator.generateCombinations(tourPackageCombinationToCheck);

        List<AvailabilityGeneratedTourPackageOptionsWoTransportationEntity> availabilityGeneratedTourPackageOptionsWoTransportationEntityList = combinations.stream()
                .map(option -> {
                    AvailabilityGeneratedTourPackageOptionsWoTransportationEntity coreComponentsOptionsCombinationEntity = new AvailabilityGeneratedTourPackageOptionsWoTransportationEntity();
                    coreComponentsOptionsCombinationEntity.setGhuddyPlatformCalculatedRate(BigDecimal.ZERO);
                    option.forEach(component -> {
                        if (component.getClass().isAssignableFrom(AvailabilityGeneratedAccommodationOptionEntity.class)) {
                            AvailabilityGeneratedAccommodationOptionEntity accommodationOptionEntity = (AvailabilityGeneratedAccommodationOptionEntity) component;
                            coreComponentsOptionsCombinationEntity.setAvailabilityGeneratedAccommodationOption(accommodationOptionEntity);
                            coreComponentsOptionsCombinationEntity.setGhuddyPlatformCalculatedRate(coreComponentsOptionsCombinationEntity.getGhuddyPlatformCalculatedRate().add(accommodationOptionEntity.getTotalOptionPricePerPerson()));
                            if (accommodationOptionEntity.getIsDefault()) {
                                tourPackageAvailabilityEntity.setDefaultAccommodationOption(accommodationOptionEntity);
                            }
                        } else if (component.getClass().isAssignableFrom(AvailabilityGeneratedFoodOptionEntity.class)) {
                            AvailabilityGeneratedFoodOptionEntity foodOptionEntity = (AvailabilityGeneratedFoodOptionEntity) component;
                            coreComponentsOptionsCombinationEntity.setAvailabilityGeneratedFoodOption(foodOptionEntity);
                            coreComponentsOptionsCombinationEntity.setGhuddyPlatformCalculatedRate(coreComponentsOptionsCombinationEntity.getGhuddyPlatformCalculatedRate().add(foodOptionEntity.getTotalOptionPricePerPerson()));
                            if (foodOptionEntity.getIsDefault()) {
                                tourPackageAvailabilityEntity.setDefaultFoodOption(foodOptionEntity);
                            }
                        } else if (component.getClass().isAssignableFrom(AvailabilityGeneratedTransferOptionEntity.class)) {
                            AvailabilityGeneratedTransferOptionEntity transferOptionEntity = (AvailabilityGeneratedTransferOptionEntity) component;
                            coreComponentsOptionsCombinationEntity.setAvailabilityGeneratedTransferOption(transferOptionEntity);
                            coreComponentsOptionsCombinationEntity.setGhuddyPlatformCalculatedRate(coreComponentsOptionsCombinationEntity.getGhuddyPlatformCalculatedRate().add(transferOptionEntity.getTotalOptionPricePerPerson()));
                            if (transferOptionEntity.getIsDefault()) {
                                tourPackageAvailabilityEntity.setDefaultTransferOption(transferOptionEntity);
                            }
                        }
                    });
                    coreComponentsOptionsCombinationEntity.setTourPackageAvailabilityEntity(tourPackageAvailabilityEntity);
                    coreComponentsOptionsCombinationEntity.setGhuddyWebsiteBlackPrice(OptionPriceCalculator.getBlackPrice(coreComponentsOptionsCombinationEntity.getGhuddyPlatformCalculatedRate(), BigDecimal.ZERO));
                    return coreComponentsOptionsCombinationEntity;
                })
                .toList();
        return availabilityGeneratedTourPackageOptionsWoTransportationEntityList;
    }

    private void generateTourPackageComponentOptionWithTransportationPackages(TourPackageAvailabilityEntity tourPackageAvailabilityEntity) {
    }
}
