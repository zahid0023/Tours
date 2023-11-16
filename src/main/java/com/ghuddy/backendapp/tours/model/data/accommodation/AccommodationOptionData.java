package com.ghuddy.backendapp.tours.model.data.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.OptionData;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class AccommodationOptionData extends OptionData {

    @Schema(description = "The accommodation option")
    @JsonProperty("accommodation_option_id")
    private Long accommodationOptionId;
    @Schema(description = "The accommodation packages belonging to this tour package")
    @JsonProperty("tour_package_accommodation_packages")
    private List<AccommodationPackageData> accommodationPackageDataList;

    public AccommodationOptionData(AccommodationOptionEntity accommodationOptionEntity, Boolean isActive) {
        super(isActive, accommodationOptionEntity.getTotalOptionPricePerPerson());
        this.accommodationOptionId = accommodationOptionEntity.getId();
        this.accommodationPackageDataList = accommodationOptionEntity.getAccommodationPackageEntities().stream()
                .map(accommodationPackageEntity -> new AccommodationPackageData(accommodationPackageEntity))
                .collect(Collectors.toList());
    }
}
