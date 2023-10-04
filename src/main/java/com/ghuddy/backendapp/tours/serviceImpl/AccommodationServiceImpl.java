package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dao.AccommodationDao;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourPackageAccommodationData;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourPackageAccommodationTypeData;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourPackageRoomCategoryData;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourPackageRoomTypeData;
import com.ghuddy.backendapp.tours.dto.request.accommodation.*;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourAccommodationListResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourAccommodationTypeListResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourRoomCategoryListResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourRoomTypeListResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.entities.*;
import com.ghuddy.backendapp.tours.repository.*;
import com.ghuddy.backendapp.tours.service.AccommodationService;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final TourRoomTypeRepository tourRoomTypeRepository;
    private final TourRoomCategoryRepository tourRoomCategoryRepository;
    private final TourAccommodationTypeRepository tourAccommodationTypeRepository;
    private final TourAccommodationRepository tourAccommodationRepository;
    private final AccommodationPackageRepository accommodationPackageRepository;
    private final AccommodationDao accommodationDao;

    public AccommodationServiceImpl(TourRoomTypeRepository tourRoomTypeRepository,
                                    TourRoomCategoryRepository tourRoomCategoryRepository,
                                    TourAccommodationTypeRepository tourAccommodationTypeRepository,
                                    TourAccommodationRepository tourAccommodationRepository,
                                    AccommodationPackageRepository accommodationPackageRepository,
                                    AccommodationDao accommodationDao) {
        this.tourRoomTypeRepository = tourRoomTypeRepository;
        this.tourRoomCategoryRepository = tourRoomCategoryRepository;
        this.tourAccommodationTypeRepository = tourAccommodationTypeRepository;
        this.tourAccommodationRepository = tourAccommodationRepository;
        this.accommodationPackageRepository = accommodationPackageRepository;
        this.accommodationDao = accommodationDao;
    }

    // room type
    @Override
    public InsertAcknowledgeResponse addRoomType(RoomTypeAddRequest roomTypeAddRequest) {
        TourPackageRoomTypeData tourPackageRoomTypeData = addRoomTypes(List.of(roomTypeAddRequest.getRoomType())).get(0);
        return new InsertAcknowledgeResponse(tourPackageRoomTypeData, roomTypeAddRequest.getRequestId());
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
        TourPackageAccommodationTypeData tourPackageAccommodationTypeData = addAccommodationTypes(List.of(accommodationTypeAddRequest.getAccommodationTypeRequest())).get(0);
        return new InsertAcknowledgeResponse(tourPackageAccommodationTypeData, accommodationTypeAddRequest.getRequestId());
    }

    @Override
    public InsertAcknowledgeListResponse addAccommodationTypes(AccommodationTypeListAddRequest accommodationTypeListAddRequest) {
        List<TourPackageAccommodationTypeData> tourPackageAccommodationTypeDataList = addAccommodationTypes(accommodationTypeListAddRequest.getAccommodationTypeRequestList());
        return new InsertAcknowledgeListResponse(tourPackageAccommodationTypeDataList, accommodationTypeListAddRequest.getRequestId());
    }

    @Transactional
    public List<TourPackageAccommodationTypeData> addAccommodationTypes(List<AccommodationTypeRequest> accommodations) {
        List<TourAccommodationTypeEntity> tourAccommodationEntities = accommodations.stream()
                .map(accommodationTypeRequest -> {
                    TourAccommodationTypeEntity tourAccommodationTypeEntity = new TourAccommodationTypeEntity();
                    tourAccommodationTypeEntity.setAccommodationTypeName(accommodationTypeRequest.getAccommodationTypeName());
                    return tourAccommodationTypeEntity;
                })
                .collect(Collectors.toList());

        return tourAccommodationTypeRepository.saveAll(tourAccommodationEntities)
                .stream()
                .map(tourAccommodationTypeEntity -> new TourPackageAccommodationTypeData(tourAccommodationTypeEntity))
                .collect(Collectors.toList());
    }

    @Override
    public TourAccommodationTypeListResponse getAllTourAccommodationTypes(String requestId) throws EmptyListException {
        List<TourPackageAccommodationTypeData> tourPackageAccommodationTypeDataList = accommodationDao.getTourAccommodationTypes(0, 0);
        if (tourPackageAccommodationTypeDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourAccommodationTypeListResponse(tourPackageAccommodationTypeDataList, requestId);
    }

    @Override
    public TourAccommodationTypeListResponse getAllTourAccommodationTypesPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException {
        List<TourPackageAccommodationTypeData> tourPackageAccommodationTypeDataList = accommodationDao.getTourAccommodationTypes(pageSize, pageNumber);
        if (tourPackageAccommodationTypeDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourAccommodationTypeListResponse(tourPackageAccommodationTypeDataList, requestId);
    }

    // accommodation
    @Override
    public InsertAcknowledgeResponse addAccommodation(AccommodationAddRequest accommodationAddRequest) {
        TourPackageAccommodationData tourPackageAccommodationData = addAccommodations(List.of(accommodationAddRequest.getAccommodationRequest())).get(0);
        return new InsertAcknowledgeResponse(tourPackageAccommodationData, accommodationAddRequest.getRequestId());
    }

    @Override
    public InsertAcknowledgeListResponse addAccommodations(AccommodationListAddRequest accommodationListAddRequest) {
        List<TourPackageAccommodationData> tourPackageAccommodationDataList = addAccommodations(accommodationListAddRequest.getAccommodationList());
        return new InsertAcknowledgeListResponse(tourPackageAccommodationDataList, accommodationListAddRequest.getRequestId());
    }

    @Transactional
    public List<TourPackageAccommodationData> addAccommodations(List<AccommodationRequest> accommodations) {
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
                .map(tourAccommodationEntity -> new TourPackageAccommodationData(tourAccommodationEntity))
                .collect(Collectors.toList());
    }

    @Override
    public TourAccommodationListResponse getAllTourAccommodations(String requestId) throws EmptyListException {
        List<TourPackageAccommodationData> tourPackageAccommodationDataList = accommodationDao.getTourAccommodations(0, 0);
        if (tourPackageAccommodationDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourAccommodationListResponse(tourPackageAccommodationDataList, requestId);
    }

    @Override
    public TourAccommodationListResponse getAllTourAccommodationsPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException {
        List<TourPackageAccommodationData> tourPackageAccommodationDataList = accommodationDao.getTourAccommodations(0, 0);
        if (tourPackageAccommodationDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourAccommodationListResponse(tourPackageAccommodationDataList, requestId);
    }

    // tour package accommodation
    @Override
    public AcknowledgeResponse addTourPackageAccommodation(TourPackageEntity tourPackageEntity, AccommodationPackageRequest accommodation) {
        List<AccommodationPackageEntity> tourPackageAccommodationEntities = setTourPackageAccommodations(tourPackageEntity, List.of(accommodation));
        accommodationPackageRepository.saveAll(tourPackageAccommodationEntities);
        return new AcknowledgeResponse();
    }

    @Override
    public AcknowledgeResponse addTourPackageAccommodations(TourPackageEntity tourPackageEntity, List<AccommodationPackageRequest> accommodations) {
        List<AccommodationPackageEntity> tourPackageAccommodationEntities = setTourPackageAccommodations(tourPackageEntity, accommodations);
        accommodationPackageRepository.saveAll(tourPackageAccommodationEntities);
        return new AcknowledgeResponse();
    }

    @Override
    public List<AccommodationPackageEntity> setTourPackageAccommodations(TourPackageEntity tourPackageEntity, List<AccommodationPackageRequest> accommodations) {

        Map<String, Set<Long>> idMaps = new HashMap<>();
        accommodations.forEach(tourPackageAccommodationRequest -> {
            idMaps.computeIfAbsent("Accommodation", key -> new HashSet<>())
                    .add(tourPackageAccommodationRequest.getAccommodationID());
            idMaps.computeIfAbsent("RoomCategory", key -> new HashSet<>())
                    .add(tourPackageAccommodationRequest.getRoomCategoryID());
            idMaps.computeIfAbsent("RoomType", key -> new HashSet<>())
                    .add(tourPackageAccommodationRequest.getRoomTypeID());
        });
        Map<Long, TourAccommodationEntity> accommodationEntityMap = getAccommodationEntitiesByIDs(idMaps.get("Accommodation"));
        Map<Long, TourRoomCategoryEntity> roomCategoryEntityMap = getTourRoomCategoryEntitiesByIDs(idMaps.get("RoomCategory"));
        Map<Long, TourRoomTypeEntity> roomTypeEntityMap = getTourRoomTypeEntitiesByIDs(idMaps.get("RoomType"));

        return accommodations.stream()
                .map(tourPackageAccommodationRequest -> {
                    AccommodationPackageEntity accommodationPackageEntity = new AccommodationPackageEntity();
                    accommodationPackageEntity.setTourPackageEntity(tourPackageEntity);
                    accommodationPackageEntity.setTourAccommodationEntity(accommodationEntityMap.get(tourPackageAccommodationRequest.getAccommodationID()));
                    accommodationPackageEntity.setTourRoomCategoryEntity(roomCategoryEntityMap.get(tourPackageAccommodationRequest.getRoomCategoryID()));
                    accommodationPackageEntity.setTourRoomTypeEntity(roomTypeEntityMap.get(tourPackageAccommodationRequest.getRoomTypeID()));
                    accommodationPackageEntity.setBedCount(tourPackageAccommodationRequest.getBedCount());
                    accommodationPackageEntity.setBedConfiguration(tourPackageAccommodationRequest.getBedConfiguration());
                    accommodationPackageEntity.setIsShareable(tourPackageAccommodationRequest.getIsShareable());
                    accommodationPackageEntity.setSuitableForPersons(tourPackageAccommodationRequest.getForPersons());
                    accommodationPackageEntity.setUnitPrice(tourPackageAccommodationRequest.getUnitPrice());
                    accommodationPackageEntity.setQuantity(tourPackageAccommodationRequest.getQuantity());
                    accommodationPackageEntity.setNetPrice(tourPackageAccommodationRequest.getNetPrice());
                    accommodationPackageEntity.setAddedPrice(tourPackageAccommodationRequest.getAddedPrice());
                    accommodationPackageEntity.setTotalAccommodationPackagePrice(tourPackageAccommodationRequest.getTotalAccommodationPackagePrice());
                    accommodationPackageEntity.setIsIncluded(tourPackageAccommodationRequest.getIsDefault());
                    return accommodationPackageEntity;
                })
                .collect(Collectors.toList());
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
}
