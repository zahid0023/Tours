package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.accommodation.*;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourAccommodationListResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourAccommodationTypeListResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourRoomCategoryListResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourRoomTypeListResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.entities.accommodation.*;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AccommodationService {

    // room type
    InsertAcknowledgeResponse addRoomType(RoomTypeAddRequest roomTypeAddRequest);

    InsertAcknowledgeListResponse addRoomTypes(RoomTypeListAddRequest roomTypeListAddRequest);

    Map<Long, TourRoomTypeEntity> getTourRoomTypeEntitiesByIDs(Set<Long> roomTypeIDs);

    TourRoomTypeListResponse getAllTourRoomTypes(String requestId) throws EmptyListException;

    TourRoomTypeListResponse getAllTourRoomTypesPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException;


    // room category

    InsertAcknowledgeResponse addRoomCategory(RoomCategoryAddRequest roomCategoryAddRequest);

    InsertAcknowledgeListResponse addRoomCategories(RoomCategoryListAddRequest roomCategoryListAddRequest);

    Map<Long, TourRoomCategoryEntity> getTourRoomCategoryEntitiesByIDs(Set<Long> roomCategoryIDs);

    TourRoomCategoryListResponse getAllTourRoomCategories(String requestId) throws EmptyListException;

    TourRoomCategoryListResponse getAllTourRoomCategoriesPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException;

    // accommodation type

    InsertAcknowledgeResponse addAccommodationType(AccommodationTypeAddRequest accommodationTypeAddRequest);

    InsertAcknowledgeListResponse addAccommodationTypes(AccommodationTypeListAddRequest accommodationTypeListAddRequest);

    TourAccommodationTypeEntity getAccommodationTypeByID(Long accommodationTypeID);

    Map<Long, TourAccommodationTypeEntity> getAccommodationTypeEntitiesByIDs(Set<Long> accommodationTypeIDs);

    TourAccommodationTypeListResponse getAllTourAccommodationTypes(String requestId) throws EmptyListException;

    TourAccommodationTypeListResponse getAllTourAccommodationTypesPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException;

    // accommodation

    InsertAcknowledgeResponse addAccommodation(AccommodationAddRequest accommodationAddRequest);

    InsertAcknowledgeListResponse addAccommodations(AccommodationListAddRequest accommodationListAddRequest);

    Map<Long, TourAccommodationEntity> getAccommodationEntitiesByIDs(Set<Long> accommodationIDs);

    TourAccommodationListResponse getAllTourAccommodations(String requestId) throws EmptyListException;

    TourAccommodationListResponse getAllTourAccommodationsPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException;

    // tour package accommodation
    InsertAcknowledgeResponse addTourPackageAccommodation(TourPackageEntity tourPackageEntity, AccommodationOptionRequest accommodationOptionRequest, String requestId);

    InsertAcknowledgeListResponse addTourPackageAccommodations(TourPackageEntity tourPackageEntity, List<AccommodationOptionRequest> accommodationOptionRequestList, String requestId);

    List<AccommodationOptionEntity> setTourPackageAccommodations(TourPackageEntity tourPackageEntity, List<AccommodationOptionRequest> accommodationOptions);

    Map<Long, AccommodationPackageEntity> getAccommodationPackageEntitiesById(Set<Long> accommodationPackageIds);
}
