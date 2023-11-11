package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dao.AccommodationDao;
import com.ghuddy.backendapp.tours.dto.request.accommodation.*;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourAccommodationListResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourAccommodationTypeListResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourRoomCategoryListResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourRoomTypeListResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.accommodation.*;
import com.ghuddy.backendapp.tours.model.entities.accommodation.*;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import com.ghuddy.backendapp.tours.repository.*;
import com.ghuddy.backendapp.tours.service.AccommodationService;
import com.ghuddy.backendapp.tours.service.TourPackagePriceService;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final TourRoomTypeRepository tourRoomTypeRepository;
    private final TourRoomCategoryRepository tourRoomCategoryRepository;
    private final TourAccommodationTypeRepository tourAccommodationTypeRepository;
    private final TourAccommodationRepository tourAccommodationRepository;
    private final AccommodationPackageRepository accommodationPackageRepository;
    private final AccommodationDao accommodationDao;
    private final TourPackagePriceService tourPackagePriceService;
    private final AccommodationOptionRepository accommodationOptionRepository;

    public AccommodationServiceImpl(TourRoomTypeRepository tourRoomTypeRepository,
                                    TourRoomCategoryRepository tourRoomCategoryRepository,
                                    TourAccommodationTypeRepository tourAccommodationTypeRepository,
                                    TourAccommodationRepository tourAccommodationRepository,
                                    AccommodationPackageRepository accommodationPackageRepository,
                                    AccommodationDao accommodationDao,
                                    TourPackagePriceService tourPackagePriceService,
                                    AccommodationOptionRepository accommodationOptionRepository) {
        this.tourRoomTypeRepository = tourRoomTypeRepository;
        this.tourRoomCategoryRepository = tourRoomCategoryRepository;
        this.tourAccommodationTypeRepository = tourAccommodationTypeRepository;
        this.tourAccommodationRepository = tourAccommodationRepository;
        this.accommodationPackageRepository = accommodationPackageRepository;
        this.accommodationDao = accommodationDao;
        this.tourPackagePriceService = tourPackagePriceService;
        this.accommodationOptionRepository = accommodationOptionRepository;
    }

    // room type
    @Override
    public InsertAcknowledgeResponse<TourPackageRoomTypeData> addRoomType(RoomTypeAddRequest roomTypeAddRequest) {
        TourPackageRoomTypeData tourPackageRoomTypeData = addRoomTypes(List.of(roomTypeAddRequest.getRoomType())).get(0);
        return new InsertAcknowledgeResponse<>(tourPackageRoomTypeData, roomTypeAddRequest.getRequestId());
    }

    @Override
    public InsertAcknowledgeListResponse addRoomTypes(RoomTypeListAddRequest roomTypeListAddRequest) {
        List<TourPackageRoomTypeData> tourPackageRoomTypeDataList = addRoomTypes(roomTypeListAddRequest.getRoomTypes());
        return new InsertAcknowledgeListResponse<>(tourPackageRoomTypeDataList, roomTypeListAddRequest.getRequestId());
    }

    @Transactional
    public List<TourPackageRoomTypeData> addRoomTypes(List<RoomTypeRequest> roomTypes) {
        List<TourRoomTypeEntity> tourRoomTypeEntities = roomTypes.stream()
                .map(roomTypeRequest -> {
                    TourRoomTypeEntity tourRoomTypeEntity = new TourRoomTypeEntity();
                    tourRoomTypeEntity.setRoomTypeName(roomTypeRequest.getRoomTypeName());
                    tourRoomTypeEntity.setDescription(roomTypeRequest.getDescription());
                    return tourRoomTypeEntity;
                })
                .collect(Collectors.toList());

        List<TourRoomTypeEntity> tourRoomTypeEntityList = tourRoomTypeRepository.saveAll(tourRoomTypeEntities);

        List<TourPackageRoomTypeData> tourPackageRoomTypeDataList = tourRoomTypeEntityList.stream()
                .map(tourRoomTypeEntity -> {
                    TourPackageRoomTypeData tourPackageRoomTypeData = new TourPackageRoomTypeData(tourRoomTypeEntity);
                    return tourPackageRoomTypeData;
                })
                .collect(Collectors.toList());
        return tourPackageRoomTypeDataList;
    }

    @Override
    public TourRoomTypeListResponse getAllTourRoomTypes(String requestId) throws EmptyListException {
        List<TourPackageRoomTypeData> tourPackageRoomTypeDataList = accommodationDao.getTourRoomTypes(0, 0);
        if (tourPackageRoomTypeDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourRoomTypeListResponse(tourPackageRoomTypeDataList, requestId);
    }

    @Override
    public TourRoomTypeListResponse getAllTourRoomTypesPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException {
        List<TourPackageRoomTypeData> tourPackageRoomTypeDataList = accommodationDao.getTourRoomTypes(pageSize, pageNumber);
        if (tourPackageRoomTypeDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourRoomTypeListResponse(tourPackageRoomTypeDataList, requestId);
    }

    // room category
    @Override
    public InsertAcknowledgeResponse addRoomCategory(RoomCategoryAddRequest roomCategoryAddRequest) {
        TourPackageRoomCategoryData tourPackageRoomCategoryData = addRoomCategories(List.of(roomCategoryAddRequest.getRoomCategoryRequest())).get(0);
        return new InsertAcknowledgeResponse(tourPackageRoomCategoryData, roomCategoryAddRequest.getRequestId());
    }

    @Override
    public InsertAcknowledgeListResponse addRoomCategories(RoomCategoryListAddRequest roomCategoryListAddRequest) {
        List<TourPackageRoomCategoryData> tourPackageRoomCategoryDataList = addRoomCategories(roomCategoryListAddRequest.getRoomCategoryRequestList());
        return new InsertAcknowledgeListResponse(tourPackageRoomCategoryDataList, roomCategoryListAddRequest.getRequestId());
    }

    @Transactional
    public List<TourPackageRoomCategoryData> addRoomCategories(List<RoomCategoryRequest> roomCategories) {
        List<TourRoomCategoryEntity> tourRoomCategoryEntities = roomCategories.stream()
                .map(roomCategoryRequest -> {
                    TourRoomCategoryEntity tourRoomCategoryEntity = new TourRoomCategoryEntity();
                    tourRoomCategoryEntity.setRoomCategoryName(roomCategoryRequest.getRoomCategoryName());
                    tourRoomCategoryEntity.setDescription(roomCategoryRequest.getDescription());
                    return tourRoomCategoryEntity;
                })
                .collect(Collectors.toList());
        List<TourRoomCategoryEntity> tourRoomCategoryEntityList = tourRoomCategoryRepository.saveAll(tourRoomCategoryEntities);
        List<TourPackageRoomCategoryData> tourPackageRoomCategoryDataList = tourRoomCategoryEntityList.stream()
                .map(tourRoomCategoryEntity -> new TourPackageRoomCategoryData(tourRoomCategoryEntity))
                .collect(Collectors.toList());
        return tourPackageRoomCategoryDataList;
    }

    @Override
    public TourRoomCategoryListResponse getAllTourRoomCategories(String requestId) throws EmptyListException {
        List<TourPackageRoomCategoryData> tourPackageRoomCategoryDataList = accommodationDao.getTourRoomCategories(0, 0);
        if (tourPackageRoomCategoryDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourRoomCategoryListResponse(tourPackageRoomCategoryDataList, requestId);
    }

    @Override
    public TourRoomCategoryListResponse getAllTourRoomCategoriesPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException {
        List<TourPackageRoomCategoryData> tourPackageRoomCategoryDataList = accommodationDao.getTourRoomCategories(pageSize, pageNumber);
        if (tourPackageRoomCategoryDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourRoomCategoryListResponse(tourPackageRoomCategoryDataList, requestId);
    }

    // accommodation type
    @Override
    public InsertAcknowledgeResponse addAccommodationType(AccommodationTypeAddRequest accommodationTypeAddRequest) {
        AccommodationTypeData accommodationTypeData = addAccommodationTypes(List.of(accommodationTypeAddRequest.getAccommodationTypeRequest())).get(0);
        return new InsertAcknowledgeResponse(accommodationTypeData, accommodationTypeAddRequest.getRequestId());
    }

    @Override
    public InsertAcknowledgeListResponse addAccommodationTypes(AccommodationTypeListAddRequest accommodationTypeListAddRequest) {
        List<AccommodationTypeData> accommodationTypeDataList = addAccommodationTypes(accommodationTypeListAddRequest.getAccommodationTypeRequestList());
        return new InsertAcknowledgeListResponse(accommodationTypeDataList, accommodationTypeListAddRequest.getRequestId());
    }

    @Transactional
    public List<AccommodationTypeData> addAccommodationTypes(List<AccommodationTypeRequest> accommodations) {
        List<TourAccommodationTypeEntity> tourAccommodationEntities = accommodations.stream()
                .map(accommodationTypeRequest -> {
                    TourAccommodationTypeEntity tourAccommodationTypeEntity = new TourAccommodationTypeEntity();
                    tourAccommodationTypeEntity.setAccommodationTypeName(accommodationTypeRequest.getAccommodationTypeName());
                    return tourAccommodationTypeEntity;
                })
                .collect(Collectors.toList());

        return tourAccommodationTypeRepository.saveAll(tourAccommodationEntities)
                .stream()
                .map(tourAccommodationTypeEntity -> new AccommodationTypeData(tourAccommodationTypeEntity))
                .collect(Collectors.toList());
    }

    @Override
    public TourAccommodationTypeListResponse getAllTourAccommodationTypes(String requestId) throws EmptyListException {
        List<AccommodationTypeData> accommodationTypeDataList = accommodationDao.getTourAccommodationTypes(0, 0);
        if (accommodationTypeDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourAccommodationTypeListResponse(accommodationTypeDataList, requestId);
    }

    @Override
    public TourAccommodationTypeListResponse getAllTourAccommodationTypesPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException {
        List<AccommodationTypeData> accommodationTypeDataList = accommodationDao.getTourAccommodationTypes(pageSize, pageNumber);
        if (accommodationTypeDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourAccommodationTypeListResponse(accommodationTypeDataList, requestId);
    }

    // accommodation
    @Override
    public InsertAcknowledgeResponse addAccommodation(AccommodationAddRequest accommodationAddRequest) {
        AccommodationData accommodationData = addAccommodations(List.of(accommodationAddRequest.getAccommodationRequest())).get(0);
        return new InsertAcknowledgeResponse(accommodationData, accommodationAddRequest.getRequestId());
    }

    @Override
    public InsertAcknowledgeListResponse addAccommodations(AccommodationListAddRequest accommodationListAddRequest) {
        List<AccommodationData> accommodationDataList = addAccommodations(accommodationListAddRequest.getAccommodationList());
        return new InsertAcknowledgeListResponse(accommodationDataList, accommodationListAddRequest.getRequestId());
    }

    @Transactional
    public List<AccommodationData> addAccommodations(List<AccommodationRequest> accommodations) {
        Set<Long> accommodationTypeIDs = accommodations.stream()
                .map(AccommodationRequest::getAccommodationTypeID)
                .collect(Collectors.toSet());
        Map<Long, TourAccommodationTypeEntity> tourAccommodationTypeEntityMap = getAccommodationTypeEntitiesByIDs(accommodationTypeIDs);

        List<TourAccommodationEntity> tourAccommodationEntities = accommodations.stream()
                .map(accommodationRequest -> {
                    TourAccommodationEntity tourAccommodationEntity = new TourAccommodationEntity();
                    tourAccommodationEntity.setTourAccommodationTypeEntity(tourAccommodationTypeEntityMap.get(accommodationRequest.getAccommodationTypeID()));
                    tourAccommodationEntity.setAccommodationName(accommodationRequest.getAccommodationName());
                    tourAccommodationEntity.setAccommodationAddress(accommodationRequest.getAccommodationAddress());
                    return tourAccommodationEntity;
                })
                .collect(Collectors.toList());
        return tourAccommodationRepository.saveAll(tourAccommodationEntities).stream()
                .map(tourAccommodationEntity -> new AccommodationData(tourAccommodationEntity))
                .collect(Collectors.toList());
    }

    @Override
    public TourAccommodationListResponse getAllTourAccommodations(String requestId) throws EmptyListException {
        List<AccommodationData> accommodationDataList = accommodationDao.getTourAccommodations(0, 0);
        if (accommodationDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourAccommodationListResponse(accommodationDataList, requestId);
    }

    @Override
    public TourAccommodationListResponse getAllTourAccommodationsPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException {
        List<AccommodationData> accommodationDataList = accommodationDao.getTourAccommodations(0, 0);
        if (accommodationDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourAccommodationListResponse(accommodationDataList, requestId);
    }

    // tour package accommodation
    @Override
    public InsertAcknowledgeResponse addTourPackageAccommodation(TourPackageEntity tourPackageEntity, AccommodationOptionRequest accommodationOptionRequest, String requestId) {
        AccommodationOptionEntity accommodationOptionEntity = setTourPackageAccommodations(tourPackageEntity, List.of(accommodationOptionRequest)).get(0);
        accommodationOptionEntity = accommodationOptionRepository.save(accommodationOptionEntity);
        AccommodationOptionData accommodationOptionData = new AccommodationOptionData(accommodationOptionEntity, true, false);
        return new InsertAcknowledgeResponse(accommodationOptionData, requestId);
    }

    @Override
    public InsertAcknowledgeListResponse addTourPackageAccommodations(TourPackageEntity tourPackageEntity, List<AccommodationOptionRequest> accommodationOptionRequestList, String requestId) {
        List<AccommodationOptionEntity> tourPackageAccommodationEntities = setTourPackageAccommodations(tourPackageEntity, accommodationOptionRequestList);
        System.out.println("ok");
        List<AccommodationOptionData> accommodationOptionDataList = accommodationOptionRepository.saveAll(tourPackageAccommodationEntities).stream()
                .map(accommodationOptionEntity -> new AccommodationOptionData(accommodationOptionEntity, true, false))
                .toList();
        return new InsertAcknowledgeListResponse(accommodationOptionDataList, requestId);
    }

    @Override
    public List<AccommodationOptionEntity> setTourPackageAccommodations(TourPackageEntity tourPackageEntity, List<AccommodationOptionRequest> accommodationOptions) {

        Map<String, Set<Long>> idMaps = new HashMap<>();
        accommodationOptions.forEach(accommodationOptionRequest -> {
            accommodationOptionRequest.getTourPackageAccommodationRequestList().forEach(accommodationPackageRequest -> {
                idMaps.computeIfAbsent("Accommodation", key -> new HashSet<>())
                        .add(accommodationPackageRequest.getAccommodationID());
                idMaps.computeIfAbsent("RoomCategory", key -> new HashSet<>())
                        .add(accommodationPackageRequest.getRoomCategoryID());
                idMaps.computeIfAbsent("RoomType", key -> new HashSet<>())
                        .add(accommodationPackageRequest.getRoomTypeID());
            });
        });

        Map<Long, TourAccommodationEntity> accommodationEntityMap = getAccommodationEntitiesByIDs(idMaps.get("Accommodation"));
        Map<Long, TourRoomCategoryEntity> roomCategoryEntityMap = getTourRoomCategoryEntitiesByIDs(idMaps.get("RoomCategory"));
        Map<Long, TourRoomTypeEntity> roomTypeEntityMap = getTourRoomTypeEntitiesByIDs(idMaps.get("RoomType"));


        List<AccommodationOptionEntity> accommodationOptionEntities = accommodationOptions.stream()
                .map(accommodationOptionRequest -> {
                    AccommodationOptionEntity accommodationOptionEntity = new AccommodationOptionEntity();
                    List<AccommodationPackageEntity> accommodationPackageEntities = accommodationOptionRequest.getTourPackageAccommodationRequestList().stream()
                            .map(accommodationPackageRequest -> {
                                AccommodationPackageEntity accommodationPackageEntity = new AccommodationPackageEntity();
                                accommodationPackageEntity.setAccommodationOptionEntity(accommodationOptionEntity);
                                accommodationPackageEntity.setTourAccommodationEntity(accommodationEntityMap.get(accommodationPackageRequest.getAccommodationID()));
                                accommodationPackageEntity.setTourRoomCategoryEntity(roomCategoryEntityMap.get(accommodationPackageRequest.getRoomCategoryID()));
                                accommodationPackageEntity.setTourRoomTypeEntity(roomTypeEntityMap.get(accommodationPackageRequest.getRoomTypeID()));
                                accommodationPackageEntity.setBedCount(accommodationPackageRequest.getBedCount());
                                accommodationPackageEntity.setBedConfiguration(accommodationPackageRequest.getBedConfiguration());
                                accommodationPackageEntity.setIsShareable(accommodationPackageRequest.getIsShareable());
                                accommodationPackageEntity.setSuitableForPersons(accommodationPackageRequest.getForPersons());
                                accommodationPackageEntity.setPerNightRoomPrice(accommodationPackageRequest.getPerNightRoomPrice());
                                accommodationPackageEntity.setNightNumber(accommodationPackageRequest.getNightNumber());
                                return accommodationPackageEntity;
                            }).toList();
                    accommodationOptionEntity.setAccommodationPackageEntities(accommodationPackageEntities);
                    //accommodationOptionEntity.setIsDefault(accommodationOptionRequest.getIsDefault());
                    accommodationOptionEntity.setTotalOptionPricePerPerson(tourPackagePriceService.perPersonAccommodationOptionPrice(accommodationOptionRequest));
                    return accommodationOptionEntity;
                })
                .toList();
        return accommodationOptionEntities;
    }

    @Override
    public TourAccommodationTypeEntity getAccommodationTypeByID(Long accommodationTypeID) {
        return tourAccommodationTypeRepository.findById(accommodationTypeID).orElseThrow(() -> new EntityNotFoundException("AccommodationTypeEntity Not Found"));
    }

    @Override
    public Map<Long, TourRoomCategoryEntity> getTourRoomCategoryEntitiesByIDs(Set<Long> roomCategoryIDs) {
        return EntityUtil.findEntitiesByIds(roomCategoryIDs, tourRoomCategoryRepository, TourRoomCategoryEntity::getId, "TourRoomCategoryEntity");
    }

    @Override
    public Map<Long, TourAccommodationTypeEntity> getAccommodationTypeEntitiesByIDs(Set<Long> accommodationTypeIDs) {
        return EntityUtil.findEntitiesByIds(accommodationTypeIDs, tourAccommodationTypeRepository, TourAccommodationTypeEntity::getId, "TourAccommodationTypeEntity");

    }

    @Override
    public Map<Long, TourRoomTypeEntity> getTourRoomTypeEntitiesByIDs(Set<Long> roomTypeIDs) {
        return EntityUtil.findEntitiesByIds(roomTypeIDs, tourRoomTypeRepository, TourRoomTypeEntity::getId, "TourRoomTypeEntity");
    }

    @Override
    public Map<Long, TourAccommodationEntity> getAccommodationEntitiesByIDs(Set<Long> accommodationIDs) {
        return EntityUtil.findEntitiesByIds(accommodationIDs, tourAccommodationRepository, TourAccommodationEntity::getId, "TourAccommodationEntity");
    }

    /**
     * @param accommodationPackageIds
     * @return
     * @throws EmptyListException
     */
    @Override
    public Map<Long, AccommodationPackageEntity> getAccommodationPackageEntitiesById(Set<Long> accommodationPackageIds) {
       return EntityUtil.findEntitiesByIds(accommodationPackageIds,accommodationPackageRepository,AccommodationPackageEntity::getId,"AccommodationPackageEntity");
    }
}
