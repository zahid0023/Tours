package com.ghuddy.backendapp.tours.dto.request.accommodation;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class TourPackageAccommodationRequest extends AccommodationPackageRequest {

    private List<Integer> nightNumber;

    public TourPackageAccommodationRequest(AccommodationPackageRequest accommodationPackageRequest, List<Integer> nightNumbers) {
        this.setAccommodationID(accommodationPackageRequest.getAccommodationID());
        this.setRoomCategoryID(accommodationPackageRequest.getRoomCategoryID());
        this.setRoomTypeID(accommodationPackageRequest.getRoomTypeID());
        this.setPerNightRoomPrice(accommodationPackageRequest.getPerNightRoomPrice());
        this.setForPersons(accommodationPackageRequest.getForPersons());
        this.setIsShareable(accommodationPackageRequest.getIsShareable());
        this.setBedCount(accommodationPackageRequest.getBedCount());
        this.setBedConfiguration(accommodationPackageRequest.getBedConfiguration());
        this.nightNumber = nightNumbers;
    }
}
