package com.ghuddy.backendapp.tours.model.data.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.OptionData;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailabilityGeneratedAccommodationOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AvailabilityGeneratedAccommodationOptionData extends OptionData {
    @Schema(description = "The accommodation option id. Both For tour package and availability generated tour package")
    @JsonProperty("tour_package_accommodation_option_id")
    private Long accommodationOptionId;
    @Schema(description = "The accommodation packages")
    @JsonProperty("tour_package_accommodation_packages")
    private List<AvailabilityGeneratedAccommodationPackageData> availabilityGeneratedAccommodationPackageDataList;

    private AvailabilityGeneratedAccommodationOptionData(AccommodationOptionData accommodationOptionData) {
        this.accommodationOptionId = accommodationOptionData.getAccommodationOptionId();
    }

    public AvailabilityGeneratedAccommodationOptionData(AccommodationOptionEntity accommodationOptionEntity) {
        this(new AccommodationOptionData(accommodationOptionEntity, accommodationOptionEntity.getIsActive()));
        this.availabilityGeneratedAccommodationPackageDataList = accommodationOptionEntity.getAccommodationPackageEntities().stream()
                .map(accommodationPackageEntity -> new AvailabilityGeneratedAccommodationPackageData(accommodationPackageEntity))
                .toList();
        this.setTotalOptionPricePerPerson(accommodationOptionEntity.getTotalOptionPricePerPerson());
        this.setIsActive(accommodationOptionEntity.getIsActive());
    }

    public AvailabilityGeneratedAccommodationOptionData(AvailabilityGeneratedAccommodationOptionEntity availabilityGeneratedAccommodationOptionEntity) {
        this.accommodationOptionId = availabilityGeneratedAccommodationOptionEntity.getId();
        this.availabilityGeneratedAccommodationPackageDataList = availabilityGeneratedAccommodationOptionEntity.getAvailabilityGeneratedAccommodationPackageEntities().stream()
                .map(availabilityGeneratedAccommodationPackageEntity -> new AvailabilityGeneratedAccommodationPackageData(availabilityGeneratedAccommodationPackageEntity))
                .toList();
    }
}
