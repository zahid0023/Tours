package com.ghuddy.backendapp.es.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.accommodation.AccommodationPackageData;
import com.ghuddy.backendapp.tours.model.entities.AccommodationOptionEntity;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ESAccommodationOptionData extends ESOptionData {
    @JsonProperty("accommodation_packages")
    private List<ESAccommodationPackageData> accommodationPackageList;

    public ESAccommodationOptionData(AccommodationOptionEntity accommodationOptionEntity) {
        this.accommodationPackageList = accommodationOptionEntity.getAccommodationPackageEntities().stream()
                .map(accommodationPackageEntity -> new ESAccommodationPackageData(accommodationPackageEntity))
                .collect(Collectors.toList());
        this.setIsActive(accommodationOptionEntity.getActive());
        this.setDefault(accommodationOptionEntity.getIsDefault());
        this.setTotalOptionPricePerPerson(accommodationOptionEntity.getTotalOptionPricePerPerson());
    }
}
