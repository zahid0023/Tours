package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dao.AccommodationDao;
import com.ghuddy.backendapp.tours.dto.data.TourAccommodationData;
import com.ghuddy.backendapp.tours.dto.data.TourAccommodationTypeData;
import com.ghuddy.backendapp.tours.dto.data.TourRoomCategoryData;
import com.ghuddy.backendapp.tours.dto.data.TourRoomTypeData;
import com.ghuddy.backendapp.tours.dto.request.accommodation.*;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourAccommodationListResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourAccommodationTypeListResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourRoomCategoryListResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourRoomTypeListResponse;
import com.ghuddy.backendapp.tours.entities.*;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
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
    private final TourPackageAccommodationRepository tourPackageAccommodationRepository;
    private final AccommodationDao accommodationDao;

    public AccommodationServiceImpl(TourRoomTypeRepository tourRoomTypeRepository,
                                    TourRoomCategoryRepository tourRoomCategoryRepository,
                                    TourAccommodationTypeRepository tourAccommodationTypeRepository,
                                    TourAccommodationRepository tourAccommodationRepository,
                                    TourPackageAccommodationRepository tourPackageAccommodationRepository,
                                    AccommodationDao accommodationDao) {
        this.tourRoomTypeRepository = tourRoomTypeRepository;
        this.tourRoomCategoryRepository = tourRoomCategoryRepository;
        this.tourAccommodationTypeRepository = tourAccommodationTypeRepository;
        this.tourAccommodationRepository = tourAccommodationRepository;
        this.tourPackageAccommodationRepository = tourPackageAccommodationRepository;
        this.accommodationDao = accommodationDao;
    }

    // room type
    @Override
    public AcknowledgeResponse addRoomType(RoomTypeAddRequest roomTypeAddRequest) {
        return addRoomTypes(List.of(roomTypeAddRequest.getRoomType()));
    }

    @Override
    public AcknowledgeResponse addRoomTypes(RoomTypeListAddRequest roomTypeListAddRequest) {
        return addRoomTypes(roomTypeListAddRequest.getRoomTypes());
    }

    @Transactional
    public AcknowledgeResponse addRoomTypes(List<RoomTypeRequest> roomTypes) {
        List<TourRoomTypeEntity> tourRoomTypeEntities = roomTypes.stream()
                .map(roomTypeRequest -> {
                    TourRoomTypeEntity tourRoomTypeEntity = new TourRoomTypeEntity();
                    tourRoomTypeEntity.setRoomTypeName(roomTypeRequest.getRoomTypeName());
                    tourRoomTypeEntity.setDescription(roomTypeRequest.getDescription());
                    return tourRoomTypeEntity;
                })
                .collect(Collectors.toList());
        tourRoomTypeRepository.saveAll(tourRoomTypeEntities);
        return new AcknowledgeResponse();
    }

    @Override
    public TourRoomTypeListResponse getAllTourRoomTypes() throws EmptyListException {
        List<TourRoomTypeData> tourRoomTypeDataList = accommodationDao.getTourRoomTypes(0, 0);
        if (tourRoomTypeDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourRoomTypeListResponse(tourRoomTypeDataList);
    }

    @Override
    public TourRoomTypeListResponse getAllTourRoomTypesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException {
        List<TourRoomTypeData> tourRoomTypeDataList = accommodationDao.getTourRoomTypes(pageSize, pageNumber);
        if (tourRoomTypeDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourRoomTypeListResponse(tourRoomTypeDataList);
    }

    // room category
    @Override
    public AcknowledgeResponse addRoomCategory(RoomCategoryAddRequest roomCategoryAddRequest) {
        return addRoomCategories(List.of(roomCategoryAddRequest.getRoomCategoryRequest()));
    }

    @Override
    public AcknowledgeResponse addRoomCategories(RoomCategoryListAddRequest roomCategoryListAddRequest) {
        return addRoomCategories(roomCategoryListAddRequest.getRoomCategoryRequestList());
    }

    @Transactional
    public AcknowledgeResponse addRoomCategories(List<RoomCategoryRequest> roomCategories) {
        List<TourRoomCategoryEntity> tourRoomCategoryEntities = roomCategories.stream()
                .map(roomCategoryRequest -> {
                    TourRoomCategoryEntity tourRoomCategoryEntity = new TourRoomCategoryEntity();
                    tourRoomCategoryEntity.setRoomCategoryName(roomCategoryRequest.getRoomCategoryName());
                    tourRoomCategoryEntity.setDescription(roomCategoryRequest.getDescription());
                    return tourRoomCategoryEntity;
                })
                .collect(Collectors.toList());
        tourRoomCategoryRepository.saveAll(tourRoomCategoryEntities);
        return new AcknowledgeResponse();
    }

    @Override
    public TourRoomCategoryListResponse getAllTourRoomCategories() throws EmptyListException {
        List<TourRoomCategoryData> tourRoomCategoryDataList = accommodationDao.getTourRoomCategories(0, 0);
        if (tourRoomCategoryDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourRoomCategoryListResponse(tourRoomCategoryDataList);
    }

    @Override
    public TourRoomCategoryListResponse getAllTourRoomCategoriesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException {
        List<TourRoomCategoryData> tourRoomCategoryDataList = accommodationDao.getTourRoomCategories(pageSize, pageNumber);
        if (tourRoomCategoryDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourRoomCategoryListResponse(tourRoomCategoryDataList);
    }

    // accommodation type
    @Override
    public AcknowledgeResponse addAccommodationType(AccommodationTypeAddRequest accommodationTypeAddRequest) {
        return addAccommodationTypes(List.of(accommodationTypeAddRequest.getAccommodationTypeRequest()));
    }

    @Override
    public AcknowledgeResponse addAccommodationTypes(AccommodationTypeListAddRequest accommodationTypeListAddRequest) {
        return addAccommodationTypes(accommodationTypeListAddRequest.getAccommodationTypeRequestList());
    }

    @Transactional
    public AcknowledgeResponse addAccommodationTypes(List<AccommodationTypeRequest> accommodations) {
        List<TourAccommodationTypeEntity> tourAccommodationEntities = accommodations.stream()
                .map(accommodationTypeRequest -> {
                    TourAccommodationTypeEntity tourAccommodationTypeEntity = new TourAccommodationTypeEntity();
                    tourAccommodationTypeEntity.setAccommodationTypeName(accommodationTypeRequest.getAccommodationTypeName());
                    return tourAccommodationTypeEntity;
                })
                .collect(Collectors.toList());
        tourAccommodationTypeRepository.saveAll(tourAccommodationEntities);
        return new AcknowledgeResponse();
    }


    // accommodation
    @Override
    public AcknowledgeResponse addAccommodation(AccommodationAddRequest accommodationAddRequest) {
        return addAccommodations(List.of(accommodationAddRequest.getAccommodationRequest()));
    }

    @Override
    public AcknowledgeResponse addAccommodations(AccommodationListAddRequest accommodationListAddRequest) {
        return addAccommodations(accommodationListAddRequest.getAccommodationRequestList());
    }

    @Override
    public TourAccommodationTypeListResponse getAllTourAccommodationTypes() throws EmptyListException {
        List<TourAccommodationTypeData> tourAccommodationTypeDataList = accommodationDao.getTourAccommodationTypes(0, 0);
        if (tourAccommodationTypeDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourAccommodationTypeListResponse(tourAccommodationTypeDataList);
    }

    @Override
    public TourAccommodationTypeListResponse getAllTourAccommodationTypesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException {
        List<TourAccommodationTypeData> tourAccommodationTypeDataList = accommodationDao.getTourAccommodationTypes(pageSize, pageNumber);
        if (tourAccommodationTypeDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourAccommodationTypeListResponse(tourAccommodationTypeDataList);
    }

    @Transactional
    public AcknowledgeResponse addAccommodations(List<AccommodationRequest> accommodations) {
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
        tourAccommodationRepository.saveAll(tourAccommodationEntities);
        return new AcknowledgeResponse();
    }

    @Override
    public TourAccommodationListResponse getAllTourAccommodations() throws EmptyListException {
        List<TourAccommodationData> tourAccommodationDataList = accommodationDao.getTourAccommodations(0, 0);
        if (tourAccommodationDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourAccommodationListResponse(tourAccommodationDataList);
    }

    @Override
    public TourAccommodationListResponse getAllTourAccommodationsPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException {
        List<TourAccommodationData> tourAccommodationDataList = accommodationDao.getTourAccommodations(0, 0);
        if (tourAccommodationDataList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        return new TourAccommodationListResponse(tourAccommodationDataList);
    }

    @Override
    public AcknowledgeResponse addTourPackageAccommodation(TourPackageEntity tourPackageEntity, TourPackageAccommodationRequest accommodation) {
        List<TourPackageAccommodationEntity> tourPackageAccommodationEntities = setTourPackageAccommodations(tourPackageEntity, List.of(accommodation));
        tourPackageAccommodationRepository.saveAll(tourPackageAccommodationEntities);
        return new AcknowledgeResponse();
    }

    @Override
    public AcknowledgeResponse addTourPackageAccommodations(TourPackageEntity tourPackageEntity, List<TourPackageAccommodationRequest> accommodations) {
        List<TourPackageAccommodationEntity> tourPackageAccommodationEntities = setTourPackageAccommodations(tourPackageEntity, accommodations);
        tourPackageAccommodationRepository.saveAll(tourPackageAccommodationEntities);
        return new AcknowledgeResponse();
    }

    @Override
    public List<TourPackageAccommodationEntity> setTourPackageAccommodations(TourPackageEntity tourPackageEntity, List<TourPackageAccommodationRequest> accommodations) {

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
                    TourPackageAccommodationEntity tourPackageAccommodationEntity = new TourPackageAccommodationEntity();
                    tourPackageAccommodationEntity.setTourPackageEntity(tourPackageEntity);
                    tourPackageAccommodationEntity.setTourAccommodationEntity(accommodationEntityMap.get(tourPackageAccommodationRequest.getAccommodationID()));
                    tourPackageAccommodationEntity.setTourRoomCategoryEntity(roomCategoryEntityMap.get(tourPackageAccommodationRequest.getRoomCategoryID()));
                    tourPackageAccommodationEntity.setTourRoomTypeEntity(roomTypeEntityMap.get(tourPackageAccommodationRequest.getRoomTypeID()));
                    tourPackageAccommodationEntity.setBedCount(tourPackageAccommodationRequest.getBedCount());
                    tourPackageAccommodationEntity.setBedConfiguration(tourPackageAccommodationRequest.getBedConfiguration());
                    tourPackageAccommodationEntity.setIsShareable(tourPackageAccommodationRequest.getIsShareable());
                    tourPackageAccommodationEntity.setSuitableForPersons(tourPackageAccommodationRequest.getForPersons());
                    return tourPackageAccommodationEntity;
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
