package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dao.FoodDao;
import com.ghuddy.backendapp.tours.dto.request.food.FoodOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.food.*;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.food.*;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.food.FoodItemData;
import com.ghuddy.backendapp.tours.model.data.food.FoodOptionData;
import com.ghuddy.backendapp.tours.model.data.food.FoodOptionListAddResponse;
import com.ghuddy.backendapp.tours.model.data.food.MealTypeData;
import com.ghuddy.backendapp.tours.model.entities.*;
import com.ghuddy.backendapp.tours.repository.FoodItemRepository;
import com.ghuddy.backendapp.tours.repository.FoodOptioRepository;
import com.ghuddy.backendapp.tours.repository.MealPackageRepository;
import com.ghuddy.backendapp.tours.repository.MealTypeRepository;
import com.ghuddy.backendapp.tours.service.FoodService;
import com.ghuddy.backendapp.tours.service.TourPackagePriceService;
import com.ghuddy.backendapp.tours.utils.CombinationGenerator;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import com.ghuddy.backendapp.tours.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
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


    /**
     * @param foodOptionCombinationCheckRequest
     * @return
     */
    @Override
    public FoodOptionCombinationCheckResponse getAllMealsCombination(FoodOptionCombinationCheckRequest foodOptionCombinationCheckRequest) throws EmptyListException, RuntimeException {
        List<MealPackageRequest> mealPackageRequestList = foodOptionCombinationCheckRequest.getMealPackageRequestList();
        HashMap<Integer, List<Long>> mealsProvidedInDays = foodOptionCombinationCheckRequest.getMealsProvidedInDays();

        HashMap<String, Long> idMealTypeMap = new HashMap<>();

        List<MealTypeData> mealTypeDataList = foodDao.getAllMealTypes(0, 0);
        mealTypeDataList.forEach(mealTypeData -> idMealTypeMap.put(mealTypeData.getMealTypeName().toLowerCase(), mealTypeData.getMealTypeId()));

        Long breakfastId = idMealTypeMap.getOrDefault("breakfast", Long.valueOf(0));
        Long lunchId = idMealTypeMap.getOrDefault("lunch", Long.valueOf(0));
        Long dinnerId = idMealTypeMap.getOrDefault("dinner", Long.valueOf(0));


        // Use stream to group by mealTypeID
        Map<Long, List<MealPackageRequest>> groupedByMealType = mealPackageRequestList.stream()
                .collect(Collectors.groupingBy(MealPackageRequest::getMealTypeID));

        // Extract lists for each meal type
        List<MealPackageRequest> breakfastPackages = groupedByMealType.getOrDefault(breakfastId, List.of());
        List<MealPackageRequest> lunchPackages = groupedByMealType.getOrDefault(lunchId, List.of());
        List<MealPackageRequest> dinnerPackages = groupedByMealType.getOrDefault(dinnerId, List.of());

        List<FoodOptionResponse> foodOptionResponseList = new LinkedList<>();

        mealsProvidedInDays.entrySet().forEach(integerListEntry -> {
            List<Long> mealTypeIDs = integerListEntry.getValue();

            List<List<MealPackageRequest>> foodPackages = new LinkedList<>();

            if (mealTypeIDs.contains(breakfastId) && breakfastPackages.isEmpty())
                throw new RuntimeException("Please add at least one breakfast package");
            else foodPackages.add(breakfastPackages);

            if (mealTypeIDs.contains(lunchId) && lunchPackages.isEmpty())
                throw new RuntimeException("Please add at least one lunch package");
            else foodPackages.add(lunchPackages);

            if (mealTypeIDs.contains(dinnerId) && dinnerPackages.isEmpty())
                throw new RuntimeException("Please add at least one dinner package");
            else foodPackages.add(dinnerPackages);

            List<List<MealPackageRequest>> options = CombinationGenerator.generateMealPackagesCombination(foodPackages);

            options.forEach(option -> {
                FoodOptionResponse foodOptionResponse = new FoodOptionResponse(option, integerListEntry.getKey());
                foodOptionResponseList.add(foodOptionResponse);
            });

        });

        return new FoodOptionCombinationCheckResponse(foodOptionResponseList, idMealTypeMap);
    }

    /**
     * @param tourPackageEntity
     * @param foodOptionRequest
     * @return
     */
    @Override
    public InsertAcknowledgeResponse addTourPackageFoodOption(TourPackageEntity tourPackageEntity, FoodOptionRequest foodOptionRequest, String requestId) throws EmptyListException {
        FoodOptionEntity foodOptionEntity = setTourPackageFoodOptions(tourPackageEntity,List.of(foodOptionRequest)).get(0);
        foodOptionEntity = foodOptionRepository.save(foodOptionEntity);
        FoodOptionData foodOptionData = new FoodOptionData(foodOptionEntity);
        return new InsertAcknowledgeResponse(foodOptionData,requestId);
    }

    @Transactional
    @Override
    public InsertAcknowledgeListResponse addTourPackageFoodOptions(TourPackageEntity tourPackageEntity, List<FoodOptionRequest> foodOptions, String requestId) throws EmptyListException {
        List<FoodOptionEntity> foodOptionEntities = setTourPackageFoodOptions(tourPackageEntity, foodOptions);
        foodOptionEntities = foodOptionRepository.saveAll(foodOptionEntities);

        // Create a map where day number is the key and FoodOptionEntity is the value
        List<FoodOptionData> dayNumberToFoodOptionMap = foodOptionEntities.stream()
                .filter(foodOptionEntity -> {
                    log.info(foodOptionEntity.toString());
                    return foodOptionEntity.getIsDefault();
                })
                .map(foodOptionEntity -> new FoodOptionData(foodOptionEntity))
                .toList();

        //return new InsertAcknowledgeResponse(new DefaultFoodOptionData(tourPackageEntity.getId(), dayNumberToFoodOptionMap), requestId);
        return new FoodOptionListAddResponse(tourPackageEntity.getId(), dayNumberToFoodOptionMap, requestId);
    }

    @Override
    public List<FoodOptionEntity> setTourPackageFoodOptions(TourPackageEntity tourPackageEntity, List<FoodOptionRequest> foodOptions) throws EmptyListException {

        Map<String, Set<Long>> idMaps = new HashMap<>();
        foodOptions.forEach(foodOptionRequest -> {
            foodOptionRequest.getMealPackageRequestList().forEach(mealPackageRequest -> {
                idMaps.computeIfAbsent("mealType", key -> new HashSet<>())
                        .add(mealPackageRequest.getMealTypeID());
                idMaps.computeIfAbsent("foodItem", key -> new HashSet<>())
                        .addAll(mealPackageRequest.getFoodItemIDs());
            });
        });

        HashMap<String, Long> idMealTypeMap = new HashMap<>();

        List<MealTypeData> mealTypeDataList = foodDao.getAllMealTypes(0, 0);
        mealTypeDataList.forEach(mealTypeData -> idMealTypeMap.put(mealTypeData.getMealTypeName().toLowerCase(), mealTypeData.getMealTypeId()));

        Map<Long, MealTypeEntity> mealTypeEntityMap = getMealTypeEntitiesByIDs(idMaps.get("mealType"));
        Map<Long, FoodItemEntity> foodItemEntityMap = getFoodItemEntitiesByIDs(idMaps.get("foodItem"));
        List<FoodOptionEntity> foodOptionEntityList = foodOptions.stream()
                .map(foodOptionRequest -> {
                    FoodOptionEntity foodOptionEntity = new FoodOptionEntity();
                    foodOptionEntity.setTourPackageEntity(tourPackageEntity);
                    foodOptionEntity.setTotalOptionPrice(BigDecimal.ZERO);
                    List<MealPackageEntity> mealPackageEntityList = foodOptionRequest.getMealPackageRequestList().stream()
                            .map(mealPackageRequest -> {
                                log.info(mealPackageRequest.toString());
                                MealPackageEntity mealPackageEntity = new MealPackageEntity();
                                mealPackageEntity.setFoodOptionEntity(foodOptionEntity);
                                mealPackageEntity.setMealTypeEntity(mealTypeEntityMap.get(mealPackageRequest.getMealTypeID()));
                                mealPackageEntity.setPerMealPrice(mealPackageRequest.getPerMealPackagePrice());
                                String mealTypeName = mealTypeEntityMap.get(mealPackageRequest.getMealTypeID()).getMealTypeName().toLowerCase();

                                if (mealTypeName.equals("breakfast")) {
                                    foodOptionEntity.setNumberOfBreakfast(1);
                                }
                                if (mealTypeName.equals("lunch")) {
                                    foodOptionEntity.setNumberOfLunch(1);
                                }
                                if (mealTypeName.equals("dinner")) {
                                    foodOptionEntity.setNumberOfDinner(1);
                                }

                                List<FoodItemEntity> foodItemEntityList = mealPackageRequest.getFoodItemIDs().stream()
                                        .map(id -> foodItemEntityMap.get(id))
                                        .toList();
                                mealPackageEntity.setFoodItemEntities(foodItemEntityList);
                                Integer count = StringUtil.mealPackageCount(tourPackageEntity, mealPackageEntity, jdbcTemplate);
                                mealPackageEntity.setMealPackageName(StringUtil.mealPackageName(tourPackageEntity, mealPackageEntity, count));
                                return mealPackageEntity;
                            })
                            .collect(Collectors.toList());
                    foodOptionEntity.setMealPackageEntities(mealPackageEntityList);
                    foodOptionEntity.setNumberOfMeals(foodOptionEntity.getNumberOfBreakfast() + foodOptionEntity.getNumberOfLunch() + foodOptionEntity.getNumberOfDinner());
                    foodOptionEntity.setTotalOptionPrice(tourPackagePriceService.perPersonFoodOptionPrice(foodOptionRequest));
                    foodOptionEntity.setDayNumber(foodOptionRequest.getDayNumber());
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
