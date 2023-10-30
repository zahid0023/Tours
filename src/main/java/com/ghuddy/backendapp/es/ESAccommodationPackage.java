package com.ghuddy.backendapp.es;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.AccommodationPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ESAccommodationPackage {
    @JsonProperty("tour_package_accommodation_name")
    private String tourPackageAccommodationName;
    @JsonProperty("tour_package_room_category_name")
    private String tourPackageRoomCategoryName;
    @JsonProperty("tour_package_room_type_name")
    private String tourPackageRoomTypeName;
    @JsonProperty("is_room_shareable")
    private boolean isShareable;
    @JsonProperty("suitable_for_persons")
    private Integer suitableForPersons;
    @JsonProperty("bed_count")
    private Integer bedCount;
    @JsonProperty("bed_configuration")
    private String bedConfiguration;
    @JsonProperty("night_number")
    private Integer nightNumber;

    public ESAccommodationPackage(AccommodationPackageEntity accommodationPackageEntity){
        this.tourPackageAccommodationName = accommodationPackageEntity.getTourAccommodationEntity().getAccommodationName();
        this.tourPackageRoomCategoryName = accommodationPackageEntity.getTourRoomCategoryEntity().getRoomCategoryName();
        this.tourPackageRoomTypeName = accommodationPackageEntity.getTourRoomTypeEntity().getRoomTypeName();
        this.isShareable = accommodationPackageEntity.getIsShareable();
        this.suitableForPersons = accommodationPackageEntity.getSuitableForPersons();
        this.bedCount = accommodationPackageEntity.getBedCount();
        this.bedConfiguration = accommodationPackageEntity.getBedConfiguration();
        this.nightNumber = accommodationPackageEntity.getNightNumber();
    }
}
