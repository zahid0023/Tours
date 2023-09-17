package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.accommodation.*;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.entities.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AccommodationService {
    AcknowledgeResponse addRoomType(RoomTypeAddRequest roomTypeAddRequest);

    AcknowledgeResponse addRoomTypes(RoomTypeListAddRequest roomTypeListAddRequest);

    AcknowledgeResponse addRoomCategory(RoomCategoryAddRequest roomCategoryAddRequest);

    AcknowledgeResponse addRoomCategories(RoomCategoryListAddRequest roomCategoryListAddRequest);

    AcknowledgeResponse addAccommodationType(AccommodationTypeAddRequest accommodationTypeAddRequest);

    TourAccommodationTypeEntity getAccommodationTypeByID(Long accommodationTypeID);

    AcknowledgeResponse addAccommodationTypes(AccommodationTypeListAddRequest accommodationTypeListAddRequest);

    AcknowledgeResponse addAccommodation(AccommodationAddRequest accommodationAddRequest);

    AcknowledgeResponse addAccommodations(AccommodationListAddRequest accommodationListAddRequest);

    Map<Long, TourAccommodationEntity> getAccommodationEntitiesByIDs(Set<Long> accommodationIDs);

    Map<Long, TourAccommodationTypeEntity> getAccommodationTypeEntitiesByIDs(Set<Long> accommodationTypeIDs);

    Map<Long, TourRoomCategoryEntity> getTourRoomCategoryEntitiesByIDs(Set<Long> roomCategoryIDs);

    Map<Long, TourRoomTypeEntity> getTourRoomTypeEntitiesByIDs(Set<Long> roomTypeIDs);

    AcknowledgeResponse addTourPackageAccommodation(TourPackageEntity tourPackageEntity, TourPackageAccommodationRequest accommodation);

    AcknowledgeResponse addTourPackageAccommodations(TourPackageEntity tourPackageEntity, List<TourPackageAccommodationRequest> tourPackages);

    List<TourPackageAccommodationEntity> setTourPackageAccommodations(TourPackageEntity tourPackageEntity, List<TourPackageAccommodationRequest> accommodations);
}
