package com.ghuddy.backendapp.tours.model.data.accommodation;

import com.ghuddy.backendapp.tours.model.data.OptionData;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailabilityGeneratedAccommodationOptionEntity;
import lombok.Data;

import java.util.List;

@Data
public class AvailabilityGeneratedAccommodationOptionData extends OptionData {
    private Long accommodationOptionId;
    private List<AvailabilityGeneratedAccommodationPackageData> availabilityGeneratedAccommodationPackageDataList;

    private AvailabilityGeneratedAccommodationOptionData(AccommodationOptionData accommodationOptionData) {
        this.accommodationOptionId = accommodationOptionData.getAccommodationOptionId();
    }

    public AvailabilityGeneratedAccommodationOptionData(AvailabilityGeneratedAccommodationOptionEntity availabilityGeneratedAccommodationOptionEntity) {
        this(new AccommodationOptionData(availabilityGeneratedAccommodationOptionEntity.getAccommodationOptionEntity(), availabilityGeneratedAccommodationOptionEntity.getIsActive()));
        this.availabilityGeneratedAccommodationPackageDataList = availabilityGeneratedAccommodationOptionEntity.getAvailabilityGeneratedAccommodationPackageEntities().stream()
                .map(availabilityGeneratedAccommodationPackageEntity -> new AvailabilityGeneratedAccommodationPackageData(availabilityGeneratedAccommodationPackageEntity))
                .toList();
        this.setTotalOptionPricePerPerson(availabilityGeneratedAccommodationOptionEntity.getTotalOptionPricePerPerson());
    }

    public AvailabilityGeneratedAccommodationOptionData(AccommodationOptionEntity accommodationOptionEntity) {
        this(new AccommodationOptionData(accommodationOptionEntity, accommodationOptionEntity.getIsActive()));
        this.availabilityGeneratedAccommodationPackageDataList = accommodationOptionEntity.getAccommodationPackageEntities().stream()
                .map(accommodationPackageEntity -> new AvailabilityGeneratedAccommodationPackageData(accommodationPackageEntity))
                .toList();
        this.setTotalOptionPricePerPerson(accommodationOptionEntity.getTotalOptionPricePerPerson());
    }
}
