package com.ghuddy.backendapp.tours.es.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailabilityGeneratedAccommodationOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ESAccommodationOptionData  {
    @Schema(description = "The available accommodation option id that traveller can choose from")
    @JsonProperty("tour_package_available_accommodation_option_id")
    private Long availableAccommodationOptionId;
    @JsonProperty("accommodation_packages")
    @Field(name = "accommodation_packages")
    private List<ESAccommodationPackageData> accommodationPackageList;

    public ESAccommodationOptionData(AvailabilityGeneratedAccommodationOptionEntity availabilityGeneratedAccommodationOptionEntity) {
        this.availableAccommodationOptionId = availabilityGeneratedAccommodationOptionEntity.getId();
        this.accommodationPackageList = availabilityGeneratedAccommodationOptionEntity.getAvailabilityGeneratedAccommodationPackageEntities().stream()
                .map(accommodationPackageEntity -> new ESAccommodationPackageData(accommodationPackageEntity))
                .collect(Collectors.toList());
    }
}
