package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dao.FoodDao;
import com.ghuddy.backendapp.tours.dto.request.food.*;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.food.FoodItemListResponse;
import com.ghuddy.backendapp.tours.dto.response.food.MealTypeListResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.food.FoodItemData;
import com.ghuddy.backendapp.tours.model.data.food.MealPackageData;
import com.ghuddy.backendapp.tours.model.data.food.MealTypeData;
import com.ghuddy.backendapp.tours.model.entities.FoodItemEntity;
import com.ghuddy.backendapp.tours.model.entities.MealPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.MealTypeEntity;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
import com.ghuddy.backendapp.tours.repository.FoodItemRepository;
import com.ghuddy.backendapp.tours.repository.MealPackageRepository;
import com.ghuddy.backendapp.tours.repository.MealTypeRepository;
import com.ghuddy.backendapp.tours.service.FoodService;
import com.ghuddy.backendapp.tours.service.TourPackagePriceService;
import com.ghuddy.backendapp.tours.utils.StringUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {
    private final FoodItemRepository foodItemRepository;
    private final MealTypeRepository mealTypeRepository;
    private final MealPackageRepository mealPackageRepository;
    private final FoodDao foodDao;
    private final JdbcTemplate jdbcTemplate;
    private final TourPackagePriceService tourPackagePriceService;

    public FoodServiceImpl(FoodItemRepository foodItemRepository,
                           MealTypeRepository mealTypeRepository,
                           MealPackageRepository mealPackageRepository,
                           FoodDao foodDao,
                           JdbcTemplate jdbcTemplate,
                           TourPackagePriceService tourPackagePriceService) {
        this.foodItemRepository = foodItemRepository;
        this.mealTypeRepository = mealTypeRepository;
        this.mealPackageRepository = mealPackageRepository;
        this.foodDao = foodDao;
        this.jdbcTemplate = jdbcTemplate;
        this.tourPackagePriceService = tourPackagePriceService;
    }

    // food item
    @Override
    public InsertAcknowledgeResponse addFoodItem(FoodItemAddRequest foodItemAddRequest) {
        FoodItemData foodItemData = addFoodItems(List.of(foodItemAddRequest.getFoodItem())).get(0);
        return new InsertAcknowledgeResponse(foodItemData, foodItemAddRequest.getRequestId());
    }

    @Override
    public InsertAcknowledgeListResponse addFoodItems(FoodItemListAddRequest foodItemListAddRequest) {
        List<FoodItemData> foodItemDataList = addFoodItems(foodItemListAddRequest.getFoodItems());
        return new InsertAcknowledgeListResponse(foodItemDataList, foodItemListAddRequest.getRequestId());
    }

    private List<FoodItemData> addFoodItems(List<FoodItemRequest> foodItems) {
        List<FoodItemEntity> foodItemEntities = foodItems.stream()
                .map(foodItemRequest -> {
                    FoodItemEntity foodItemEntity = new FoodItemEntity();
                    foodItemEntity.setFoodItemName(foodItemRequest.getFoodItemName());
                    return foodItemEntity;
                })
                .collect(Collectors.toList());
        return foodItemRepository.saveAll(foodItemEntities).stream()
                .map(foodItemEntity -> new FoodItemData(foodItemEntity))
                .collect(Collectors.toList());
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
    public InsertAcknowledgeResponse addMealType(MealTypeAddRequest mealTypeAddRequest) {
        MealTypeData mealTypeData = addMealTypes(List.of(mealTypeAddRequest.getMealType())).get(0);
        return new InsertAcknowledgeResponse(mealTypeData, mealTypeAddRequest.getRequestId());
    }

    @Override
    public InsertAcknowledgeListResponse addMealTypes(MealTypeListAddRequest mealTypeListAddRequest) {
        List<MealTypeData> mealTypeDataList = addMealTypes(mealTypeListAddRequest.getMealTypes());
        return new InsertAcknowledgeListResponse<>(mealTypeDataList, mealTypeListAddRequest.getRequestId());
    }

    private List<MealTypeData> addMealTypes(List<MealTypeRequest> mealTypes) {
        List<MealTypeEntity> mealTypeEntities = mealTypes.stream()
                .map(mealTypeRequest -> {
                    MealTypeEntity mealTypeEntity = new MealTypeEntity();
                    mealTypeEntity.setMealTypeName(mealTypeRequest.getMealTypeName());
                    return mealTypeEntity;
                })
                .collect(Collectors.toList());
        return mealTypeRepository.saveAll(mealTypeEntities).stream()
                .map(mealTypeEntity -> new MealTypeData(mealTypeEntity))
                .collect(Collectors.toList());
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

    // tour package meal package
    @Transactional
    @Override
    public InsertAcknowledgeResponse addTourPackageMealPackage(TourPackageEntity tourPackageEntity, MealPackageRequest mealPackageRequest, String requestId) {
        MealPackageEntity mealPackageEntity = setTourPackageMealPackages(tourPackageEntity, List.of(mealPackageRequest)).get(0);
        mealPackageEntity = mealPackageRepository.save(mealPackageEntity);
        return new InsertAcknowledgeResponse(new MealPackageData(mealPackageEntity), requestId);

    }

    @Transactional
    @Override
    public InsertAcknowledgeListResponse addTourPackageMealPackages(TourPackageEntity tourPackageEntity, List<MealPackageRequest> mealPackages, String requestId) {
        List<MealPackageEntity> mealPackageEntities = setTourPackageMealPackages(tourPackageEntity, mealPackages);
        mealPackageEntities = mealPackageRepository.saveAll(mealPackageEntities);
        List<MealPackageData> mealPackageDataList = mealPackageEntities.stream()
                .map(mealPackageEntity -> new MealPackageData(mealPackageEntity))
                .collect(Collectors.toList());
        return new InsertAcknowledgeListResponse(mealPackageDataList, requestId);
    }

    @Override
    public List<MealPackageEntity> setTourPackageMealPackages(TourPackageEntity tourPackageEntity, List<MealPackageRequest> mealPackages) {
        Set<Long> mealTypeIDs = mealPackages.stream()
                .map(MealPackageRequest::getMealTypeID) // Extract mealTypeID from each MealPackageRequest
                .collect(Collectors.toSet()); // Collect into a List<Long>
        Map<Long, MealTypeEntity> mealTypeEntityMap = getMealTypeEntitiesByIds(mealTypeIDs);
        List<MealPackageEntity> mealPackageEntities = mealPackages.stream()
                .map(mealPackageRequest -> {
                    MealTypeEntity mealTypeEntity = mealTypeEntityMap.get(mealPackageRequest.getMealTypeID());
                    MealPackageEntity mealPackageEntity = new MealPackageEntity();
                    mealPackageEntity.setTourPackageEntity(tourPackageEntity);
                    mealPackageEntity.setMealTypeEntity(mealTypeEntity);
                    List<FoodItemEntity> foodItemEntities = mealPackageRequest.getFoodItemIDs().stream()
                            .map(this::getFoodItemEntityByID)
                            .collect(Collectors.toList());
                    mealPackageEntity.setFoodItemEntities(foodItemEntities);
                    mealPackageEntity.setPerMealPrice(mealPackageRequest.getPerMealPrice());
                    mealPackageEntity.setPerPersonNumberOfMeals(mealPackageRequest.getNumberOfMeals());
                    mealPackageEntity.setPerPersonTotalMealPrice(tourPackagePriceService.perPersonPerMealPackageTotalPrice(mealPackageRequest));
                    mealPackageEntity.setIsIncluded(mealPackageRequest.getIsDefault());
                    mealPackageEntity.setMealPackageName(StringUtil.mealPackageName(tourPackageEntity, mealPackageEntity, jdbcTemplate));
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

    private Map<Long, MealTypeEntity> getMealTypeEntitiesByIds(Set<Long> ids) {
        return mealTypeRepository.findMealTypeEntitiesByIds(ids);
    }
}
