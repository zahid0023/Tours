package com.example.ghuddytour2.tours.serviceImpl;

import com.example.ghuddytour2.tours.dto.request.food.*;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.FoodItemEntity;
import com.example.ghuddytour2.tours.entities.MealPackageEntity;
import com.example.ghuddytour2.tours.entities.MealTypeEntity;
import com.example.ghuddytour2.tours.entities.TourPackageEntity;
import com.example.ghuddytour2.tours.repository.FoodItemRepository;
import com.example.ghuddytour2.tours.repository.MealPackageRepository;
import com.example.ghuddytour2.tours.repository.MealTypeRepository;
import com.example.ghuddytour2.tours.service.FoodService;
import com.example.ghuddytour2.tours.service.TourPackageService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {
    private final FoodItemRepository foodItemRepository;
    private final MealTypeRepository mealTypeRepository;
    private final TourPackageService tourPackageService;
    private final MealPackageRepository mealPackageRepository;

    public FoodServiceImpl(FoodItemRepository foodItemRepository,
                           MealTypeRepository mealTypeRepository,
                           TourPackageService tourPackageService,
                           MealPackageRepository mealPackageRepository) {
        this.foodItemRepository = foodItemRepository;
        this.mealTypeRepository = mealTypeRepository;
        this.tourPackageService = tourPackageService;
        this.mealPackageRepository = mealPackageRepository;
    }

    @Override
    public AcknowledgeResponse addFoodItem(FoodItemAddRequest foodItemAddRequest) {
        return addFoodItems(List.of(foodItemAddRequest.getFoodItem()));
    }

    @Override
    public AcknowledgeResponse addFoodItems(FoodItemListAddRequest foodItemListAddRequest) {
        return addFoodItems(foodItemListAddRequest.getFoodItems());
    }

    private AcknowledgeResponse addFoodItems(List<FoodItemRequest> foodItems) {
        List<FoodItemEntity> foodItemEntities = foodItems.stream()
                .map(foodItemRequest -> {
                    FoodItemEntity foodItemEntity = new FoodItemEntity();
                    foodItemEntity.setFoodItemName(foodItemRequest.getFoodItemName());
                    return foodItemEntity;
                })
                .collect(Collectors.toList());
        foodItemRepository.saveAll(foodItemEntities);
        return new AcknowledgeResponse();
    }

    @Override
    public AcknowledgeResponse addMealType(MealTypeAddRequest mealTypeAddRequest) {
        return addMealTypes(List.of(mealTypeAddRequest.getMealType()));
    }

    @Override
    public AcknowledgeResponse addMealTypes(MealTypeListAddRequest mealTypeListAddRequest) {
        return addMealTypes(mealTypeListAddRequest.getMealTypes());
    }

    private AcknowledgeResponse addMealTypes(List<MealTypeRequest> mealTypes) {
        List<MealTypeEntity> mealTypeEntities = mealTypes.stream()
                .map(mealTypeRequest -> {
                    MealTypeEntity mealTypeEntity = new MealTypeEntity();
                    mealTypeEntity.setMealTypeName(mealTypeRequest.getMealTypeName());
                    return mealTypeEntity;
                })
                .collect(Collectors.toList());
        mealTypeRepository.saveAll(mealTypeEntities);
        return new AcknowledgeResponse();
    }

    @Override
    public AcknowledgeResponse addTourPackageMealPackage(MealPackageAddRequest mealPackageAddRequest) {
        TourPackageEntity tourPackageEntity = tourPackageService.getTourPackageByPackageID(mealPackageAddRequest.getTourPackageID());
        return addTourPackageMealPackages(tourPackageEntity, List.of(mealPackageAddRequest.getMealPackage()));
    }

    @Override
    public AcknowledgeResponse addTourPackageMealPackages(MealPackageListAddRequest mealPackageListAddRequest) {
        TourPackageEntity tourPackageEntity = tourPackageService.getTourPackageByPackageID(mealPackageListAddRequest.getTourPackageID());
        return addTourPackageMealPackages(tourPackageEntity, mealPackageListAddRequest.getMealPackages());
    }

    private AcknowledgeResponse addTourPackageMealPackages(TourPackageEntity tourPackageEntity, List<MealPackageRequest> mealPackages) {
        List<Long> mealTypeIDs = mealPackages.stream()
                .map(MealPackageRequest::getMealTypeID) // Extract mealTypeID from each MealPackageRequest
                .collect(Collectors.toList()); // Collect into a List<Long>
        Map<Long, MealTypeEntity> mealTypeEntityMap = getMealTypeEntitiesByIds(mealTypeIDs);
        List<MealPackageEntity> mealPackageEntities = mealPackages.stream()
                .map(mealPackageRequest -> {
                    MealTypeEntity mealTypeEntity = mealTypeEntityMap.get(mealPackageRequest.getMealTypeID());
                    MealPackageEntity mealPackageEntity = new MealPackageEntity();
                    mealPackageEntity.setTourPackageEntity(tourPackageEntity);
                    mealPackageEntity.setMealTypeEntity(mealTypeEntity);
                    mealPackageEntity.setMealPackageName(mealPackageRequest.getMealPackageName());
                    List<FoodItemEntity> foodItemEntities = mealPackageRequest.getFoodItemIDs().stream()
                            .map(this::getFoodItemEntityByID)
                            .collect(Collectors.toList());
                    mealPackageEntity.setFoodItemEntities(foodItemEntities);
                    return mealPackageEntity;
                })
                .collect(Collectors.toList());
        mealPackageRepository.saveAll(mealPackageEntities);
        return new AcknowledgeResponse();
    }


    @Override
    public MealTypeEntity getMealTypeEntityByID(Long mealTypeID) {
        return mealTypeRepository.findById(mealTypeID).orElseThrow(() -> new EntityNotFoundException("MealTypeEntity Not Found"));
    }

    @Override
    public FoodItemEntity getFoodItemEntityByID(Long foodItemID) {
        return foodItemRepository.findById(foodItemID).orElseThrow(() -> new EntityNotFoundException("FoodItemEntity Not Found"));
    }

    private Map<Long, MealTypeEntity> getMealTypeEntitiesByIds(List<Long> ids) {
        return mealTypeRepository.findMealTypeEntitiesByIds(ids);
    }
}
