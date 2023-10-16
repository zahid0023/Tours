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
import com.ghuddy.backendapp.tours.model.data.food.FoodOptionData;
import com.ghuddy.backendapp.tours.model.data.food.MealPackageData;
import com.ghuddy.backendapp.tours.model.data.food.MealTypeData;
import com.ghuddy.backendapp.tours.model.entities.*;
import com.ghuddy.backendapp.tours.repository.FoodItemRepository;
import com.ghuddy.backendapp.tours.repository.FoodOptioRepository;
import com.ghuddy.backendapp.tours.repository.MealPackageRepository;
import com.ghuddy.backendapp.tours.repository.MealTypeRepository;
import com.ghuddy.backendapp.tours.service.FoodService;
import com.ghuddy.backendapp.tours.service.TourPackagePriceService;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import com.ghuddy.backendapp.tours.utils.StringUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {
    private final FoodItemRepository foodItemRepository;
    private final MealTypeRepository mealTypeRepository;
    private final MealPackageRepository mealPackageRepository;
    private final FoodDao foodDao;
    private final JdbcTemplate jdbcTemplate;
    private final TourPackagePriceService tourPackagePriceService;
    private final FoodOptioRepository foodOptionRepository;

    public FoodServiceImpl(FoodItemRepository foodItemRepository,
                           MealTypeRepository mealTypeRepository,
                           MealPackageRepository mealPackageRepository,
                           FoodDao foodDao,
                           JdbcTemplate jdbcTemplate,
                           TourPackagePriceService tourPackagePriceService,
                           FoodOptioRepository foodOptionRepository) {
        this.foodItemRepository = foodItemRepository;
        this.mealTypeRepository = mealTypeRepository;
        this.mealPackageRepository = mealPackageRepository;
        this.foodDao = foodDao;
        this.jdbcTemplate = jdbcTemplate;
        this.tourPackagePriceService = tourPackagePriceService;
        this.foodOptionRepository = foodOptionRepository;
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
    public InsertAcknowledgeResponse addTourPackageFoodOption(TourPackageEntity tourPackageEntity, FoodOptionRequest foodOptionRequest, String requestId) {
        FoodOptionEntity foodOptionEntity = setTourPackageFoodOptions(tourPackageEntity, List.of(foodOptionRequest)).get(0);
        foodOptionEntity = foodOptionRepository.save(foodOptionEntity);
        return new InsertAcknowledgeResponse(new FoodOptionData(foodOptionEntity), requestId);

    }

    @Transactional
    @Override
    public InsertAcknowledgeListResponse addTourPackageFoodOptions(TourPackageEntity tourPackageEntity, List<FoodOptionRequest> foodOptions, String requestId) {
        List<FoodOptionEntity> foodOptionEntities = setTourPackageFoodOptions(tourPackageEntity, foodOptions);
        foodOptionEntities = foodOptionRepository.saveAll(foodOptionEntities);
        List<FoodOptionData> foodOptionDataList = foodOptionEntities.stream()
                .map(foodOptionEntity -> new FoodOptionData(foodOptionEntity))
                .collect(Collectors.toList());
        return new InsertAcknowledgeListResponse(foodOptionDataList, requestId);
    }

    @Override
    public List<FoodOptionEntity> setTourPackageFoodOptions(TourPackageEntity tourPackageEntity, List<FoodOptionRequest> foodOptions) {

        Map<String, Set<Long>> idMaps = new HashMap<>();
        foodOptions.forEach(foodOptionRequest -> {
            foodOptionRequest.getMealPackageRequestList().forEach(mealPackageRequest -> {
                idMaps.computeIfAbsent("mealType", key -> new HashSet<>())
                        .add(mealPackageRequest.getMealTypeID());
                idMaps.computeIfAbsent("foodItem", key -> new HashSet<>())
                        .addAll(mealPackageRequest.getFoodItemIDs());
            });
        });

        Map<Long, MealTypeEntity> mealTypeEntityMap = getMealTypeEntitiesByIDs(idMaps.get("mealType"));
        Map<Long, FoodItemEntity> foodItemEntityMap = getFoodItemEntitiesByIDs(idMaps.get("foodItem"));
        List<FoodOptionEntity> foodOptionEntityList = foodOptions.stream()
                .map(foodOptionRequest -> {
                    FoodOptionEntity foodOptionEntity = new FoodOptionEntity();
                    foodOptionEntity.setTourPackageEntity(tourPackageEntity);
                    List<MealPackageEntity> mealPackageEntityList = foodOptionRequest.getMealPackageRequestList().stream()
                            .map(mealPackageRequest -> {
                                MealPackageEntity mealPackageEntity = new MealPackageEntity();
                                mealPackageEntity.setFoodOptionEntity(foodOptionEntity);
                                mealPackageEntity.setMealTypeEntity(mealTypeEntityMap.get(mealPackageRequest.getMealTypeID()));

                                List<FoodItemEntity> foodItemEntityList = mealPackageRequest.getFoodItemIDs().stream()
                                        .map(id -> foodItemEntityMap.get(id))
                                        .toList();
                                mealPackageEntity.setFoodItemEntities(foodItemEntityList);
                                mealPackageEntity.setPerMealPrice(mealPackageRequest.getPerMealPackagePrice());
                                mealPackageEntity.setPerPersonNumberOfMeals(mealPackageRequest.getNumberOfMeals());
                                mealPackageEntity.setPerPersonTotalMealPrice(new BigDecimal(7));
                                Integer count = StringUtil.mealPackageCount(tourPackageEntity,mealPackageEntity,jdbcTemplate);
                                mealPackageEntity.setMealPackageName(StringUtil.mealPackageName(tourPackageEntity, mealPackageEntity, count));
                                return mealPackageEntity;
                            })
                            .collect(Collectors.toList());
                    foodOptionEntity.setMealPackageEntities(mealPackageEntityList);
                    foodOptionEntity.setIsDefault(foodOptionRequest.getIsDefault());
                    return foodOptionEntity;
                }).collect(Collectors.toList());

        return foodOptionEntityList;
    }


    @Override
    public MealTypeEntity getMealTypeEntityByID(Long mealTypeID) {
        return mealTypeRepository.findById(mealTypeID).orElseThrow(() -> new EntityNotFoundException("MealTypeEntity Not Found"));
    }

    @Override
    public FoodItemEntity getFoodItemEntityByID(Long foodItemID) {
        return foodItemRepository.findById(foodItemID).orElseThrow(() -> new EntityNotFoundException("FoodItemEntity Not Found"));
    }


    /**
     * @param mealTypeIDs
     * @return
     */
    @Override
    public Map<Long, MealTypeEntity> getMealTypeEntitiesByIDs(Set<Long> mealTypeIDs) {
        return EntityUtil.findEntitiesByIds(mealTypeIDs, mealTypeRepository, MealTypeEntity::getId, "MealTypeEntity");
    }

    /**
     * @param foodItemIDs
     * @return
     */
    @Override
    public Map<Long, FoodItemEntity> getFoodItemEntitiesByIDs(Set<Long> foodItemIDs) {
        return EntityUtil.findEntitiesByIds(foodItemIDs, foodItemRepository, FoodItemEntity::getId, "FoodItemEntity");
    }
}
