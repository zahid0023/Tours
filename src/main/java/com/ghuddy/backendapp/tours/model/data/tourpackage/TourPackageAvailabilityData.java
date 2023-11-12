package com.ghuddy.backendapp.tours.model.data.tourpackage;

import com.ghuddy.backendapp.tours.model.data.accommodation.AvailabilityGeneratedAccommodationOptionData;
import com.ghuddy.backendapp.tours.model.data.food.AvailabilityGeneratedFoodOptionData;
import com.ghuddy.backendapp.tours.model.data.guide.AvailabilityGeneratedGuideOptionData;
import com.ghuddy.backendapp.tours.model.data.spot.entry.AvailabilityGeneratedSpotEntryData;
import com.ghuddy.backendapp.tours.model.data.transfer.AvailabilityGeneratedTransferOptionData;
import com.ghuddy.backendapp.tours.model.data.transportation.AvailabilityGeneratedTransportationPackageData;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageAvailabilityEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TourPackageAvailabilityData {
    private Long tourPackageTypeId;
    private Long tourPackageId;
    private Long tourPackageAvailabilityId;
    private LocalDate tourStartDate;
    private Integer totalSeats;
    private Integer bookableSeats;
    private List<AvailabilityGeneratedAccommodationOptionData> availabilityGeneratedAccommodationOptionDataList;
    private List<AvailabilityGeneratedFoodOptionData> availabilityGeneratedFoodOptionDataList;
    private List<AvailabilityGeneratedTransferOptionData> availabilityGeneratedTransferOptionData;
    private List<AvailabilityGeneratedTransportationPackageData> availabilityGeneratedTransportationPackageDataList;
    private List<AvailabilityGeneratedGuideOptionData> availabilityGeneratedGuideOptionDataList;
    private List<AvailabilityGeneratedSpotEntryData> availabilityGeneratedSpotEntryDataList;

    public TourPackageAvailabilityData(TourPackageAvailabilityEntity tourPackageAvailabilityEntity){
        this.tourPackageTypeId = tourPackageAvailabilityEntity.getTourPackageEntity().getTourPackageType().getId();
        this.tourPackageId = tourPackageAvailabilityEntity.getTourPackageEntity().getId();
        this.tourPackageAvailabilityId = tourPackageAvailabilityEntity.getId();
        this.tourStartDate = tourPackageAvailabilityEntity.getTourStartDate();
        this.totalSeats = tourPackageAvailabilityEntity.getTotalSeats();
        this.bookableSeats = tourPackageAvailabilityEntity.getBookableSeats();
    }
}
