package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dao.FoodDao;
import com.ghuddy.backendapp.tours.dto.data.FoodItemData;
import com.ghuddy.backendapp.tours.dto.data.MealTypeData;
import com.ghuddy.backendapp.tours.dto.request.food.*;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.food.FoodItemListResponse;
import com.ghuddy.backendapp.tours.dto.response.food.MealTypeListResponse;
import com.ghuddy.backendapp.tours.entities.FoodItemEntity;
import com.ghuddy.backendapp.tours.entities.MealPackageEntity;
import com.ghuddy.backendapp.tours.entities.MealTypeEntity;
import com.ghuddy.backendapp.tours.entities.TourPackageEntity;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.repository.FoodItemRepository;
import com.ghuddy.backendapp.tours.repository.MealPackageRepository;
import com.ghuddy.backendapp.tours.repository.MealTypeRepository;
import com.ghuddy.backendapp.tours.service.FoodService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {
    private final FoodItemRepository foodItemRepository;
    private final MealTypeRepository mealTypeRepository;
    private final MealPackageRepository mealPackageRepository;
    private final FoodDao foodDao;

    public FoodServiceImpl(FoodItemRepository foodItemRepository,
                           MealTypeRepository mealTypeRepository,
                           MealPackageRepository mealPackageRepository,
                           FoodDao foodDao) {
        this.foodItemRepository = foodItemRepository;
        this.mealTypeRepository = mealTypeRepository;
        this.mealPackageRepository = mealPackageRepository;
        this.foodDao = foodDao;
    }

    // food items
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
    public FoodItemListResponse getAllFoodItems() throws EmptyListException {
        List<FoodItemData> foodItemDataList = foodDao.getAllFoodItems(0, 0);
        if (foodItemDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new FoodItemListResponse(foodItemDataList);
    }

    @Override
    public FoodItemListResponse getAllFoodItemsPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException {
        List<FoodItemData> foodItemDataList = foodDao.getAllFoodItems(pageSize, pageNumber);
        if (foodItemDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new FoodItemListResponse(foodItemDataList);
    }


    // meal type
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
    public MealTypeListResponse getAllMealTypes() throws EmptyListException {
        List<MealTypeData> mealTypeDataList = foodDao.getAllMealTypes(0, 0);
        if (mealTypeDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new MealTypeListResponse(mealTypeDataList);
    }

    @Override
    public MealTypeListResponse getAllMealTypesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException {
        List<MealTypeData> mealTypeDataList = foodDao.getAllMealTypes(pageSize, pageNumber);
        if (mealTypeDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new MealTypeListResponse(mealTypeDataList);
    }

    @Transactional
    @Override
    public AcknowledgeResponse addTourPackageMealPackage(TourPackageEntity tourPackageEntity, MealPackageRequest mealPackageRequest) {
        List<MealPackageEntity> mealPackageEntities = setTourPackageMealPackages(tourPackageEntity, List.of(mealPackageRequest));
        mealPackageRepository.saveAll(mealPackageEntities);
        return new AcknowledgeResponse();
    }

    @Transactional
    @Override
    public AcknowledgeResponse addTourPackageMealPackages(TourPackageEntity tourPackageEntity, List<MealPackageRequest> mealPackages) {
        List<MealPackageEntity> mealPackageEntities = setTourPackageMealPackages(tourPackageEntity, mealPackages);
        mealPackageRepository.saveAll(mealPackageEntities);
        return new AcknowledgeResponse();
    }

    @Override
    public List<MealPackageEntity> setTourPackageMealPackages(TourPackageEntity tourPackageEntity, List<MealPackageRequest> mealPackages) {
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
        return mealPackageEntities;
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
