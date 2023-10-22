package com.ghuddy.backendapp.tours.model.data.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.OptionData;
import com.ghuddy.backendapp.tours.model.entities.AccommodationOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AccommodationOptionData extends OptionData {
    @Schema(description = "The accommodation packages belonging to this tour package")
    @JsonProperty("tour_package_accommodation_packages")
    private List<AccommodationPackageData> accommodationPackageDataList;

    public AccommodationOptionData(AccommodationOptionEntity accommodationOptionEntity) {
        this.accommodationPackageDataList = accommodationOptionEntity.getAccommodationPackageEntities().stream()
                .map(accommodationPackageEntity -> new AccommodationPackageData(accommodationPackageEntity))
                .collect(Collectors.toList());
        this.setIsActive(accommodationOptionEntity.getActive());
        this.setDefault(accommodationOptionEntity.getIsDefault());
        this.setTotalOptionPricePerPerson(accommodationOptionEntity.getTotalOptionPricePerPerson());
    }
}
