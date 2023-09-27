package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.accommodation.*;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourAccommodationListResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourAccommodationTypeListResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourRoomCategoryListResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourRoomTypeListResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.entities.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AccommodationService {

    // room type
    InsertAcknowledgeResponse addRoomType(RoomTypeAddRequest roomTypeAddRequest);

    InsertAcknowledgeListResponse addRoomTypes(RoomTypeListAddRequest roomTypeListAddRequest);

    Map<Long, TourRoomTypeEntity> getTourRoomTypeEntitiesByIDs(Set<Long> roomTypeIDs);

    TourRoomTypeListResponse getAllTourRoomTypes() throws EmptyListException;

    TourRoomTypeListResponse getAllTourRoomTypesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;


    // room category

    InsertAcknowledgeResponse addRoomCategory(RoomCategoryAddRequest roomCategoryAddRequest);

    InsertAcknowledgeListResponse addRoomCategories(RoomCategoryListAddRequest roomCategoryListAddRequest);

    Map<Long, TourRoomCategoryEntity> getTourRoomCategoryEntitiesByIDs(Set<Long> roomCategoryIDs);

    TourRoomCategoryListResponse getAllTourRoomCategories() throws EmptyListException;

    TourRoomCategoryListResponse getAllTourRoomCategoriesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    // accommodation type

    InsertAcknowledgeResponse addAccommodationType(AccommodationTypeAddRequest accommodationTypeAddRequest);

    InsertAcknowledgeListResponse addAccommodationTypes(AccommodationTypeListAddRequest accommodationTypeListAddRequest);

    TourAccommodationTypeEntity getAccommodationTypeByID(Long accommodationTypeID);

    Map<Long, TourAccommodationTypeEntity> getAccommodationTypeEntitiesByIDs(Set<Long> accommodationTypeIDs);

    TourAccommodationTypeListResponse getAllTourAccommodationTypes() throws EmptyListException;

    TourAccommodationTypeListResponse getAllTourAccommodationTypesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    // accommodation

    InsertAcknowledgeResponse addAccommodation(AccommodationAddRequest accommodationAddRequest);

    InsertAcknowledgeListResponse addAccommodations(AccommodationListAddRequest accommodationListAddRequest);

    Map<Long, TourAccommodationEntity> getAccommodationEntitiesByIDs(Set<Long> accommodationIDs);

    TourAccommodationListResponse getAllTourAccommodations() throws EmptyListException;

    TourAccommodationListResponse getAllTourAccommodationsPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    // tour package accommodation
    AcknowledgeResponse addTourPackageAccommodation(TourPackageEntity tourPackageEntity, TourPackageAccommodationRequest accommodation);

    AcknowledgeResponse addTourPackageAccommodations(TourPackageEntity tourPackageEntity, List<TourPackageAccommodationRequest> tourPackages);

    List<TourPackageAccommodationEntity> setTourPackageAccommodations(TourPackageEntity tourPackageEntity, List<TourPackageAccommodationRequest> accommodations);
}
