package com.ghuddy.backendapp.tours.model.data.tourpackage;

import com.ghuddy.backendapp.tours.model.data.accommodation.AvailabilityGeneratedAccommodationOptionData;
import com.ghuddy.backendapp.tours.model.data.food.AvailabilityGeneratedFoodOptionData;
import com.ghuddy.backendapp.tours.model.data.guide.AvailabilityGeneratedGuideOptionData;
import com.ghuddy.backendapp.tours.model.data.spot.entry.AvailabilityGeneratedSpotEntryOptionData;
import com.ghuddy.backendapp.tours.model.data.transfer.AvailabilityGeneratedTransferOptionData;
import com.ghuddy.backendapp.tours.model.data.transportation.AvailabilityGeneratedTransportationPackageData;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.AvailabilityGeneratedTourPackageEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AvailabilityGeneratedTourPackageData {
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
    private List<AvailabilityGeneratedSpotEntryOptionData> availabilityGeneratedSpotEntryDataList;

    public AvailabilityGeneratedTourPackageData(AvailabilityGeneratedTourPackageEntity availabilityGeneratedTourPackageEntity) {
        this.tourPackageTypeId = availabilityGeneratedTourPackageEntity.getTourPackageEntity().getTourPackageType().getId();
        this.tourPackageId = availabilityGeneratedTourPackageEntity.getTourPackageEntity().getId();
        this.tourPackageAvailabilityId = availabilityGeneratedTourPackageEntity.getId();
        this.tourStartDate = availabilityGeneratedTourPackageEntity.getTourStartDate();
        this.totalSeats = availabilityGeneratedTourPackageEntity.getTotalSeats();
        this.bookableSeats = availabilityGeneratedTourPackageEntity.getBookableSeats();
    }
}
