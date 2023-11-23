package com.ghuddy.backendapp.tours.es.model.entities;

import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailabilityGeneratedAccommodationOptionEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ESAccommodationOptionDocument {
    @Field(name = "tour_package_available_accommodation_option_id", type = FieldType.Long)
    private Long availableAccommodationOptionId;
    @Field(name = "tour_package_available_accommodation_packages", type = FieldType.Nested, includeInParent = true)
    private List<ESAccommodationPackageDocument> accommodationPackageList;

    public ESAccommodationOptionDocument(AvailabilityGeneratedAccommodationOptionEntity availabilityGeneratedAccommodationOptionEntity) {
        this.availableAccommodationOptionId = availabilityGeneratedAccommodationOptionEntity.getId();
        this.accommodationPackageList = availabilityGeneratedAccommodationOptionEntity.getAvailabilityGeneratedAccommodationPackageEntities().stream()
                .map(accommodationPackageEntity -> new ESAccommodationPackageDocument(accommodationPackageEntity))
                .collect(Collectors.toList());
    }
}
