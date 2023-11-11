package com.ghuddy.backendapp.tours.es.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationPackageEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@NoArgsConstructor
public class ESAccommodationPackageData {
    @JsonProperty("tour_package_accommodation_name")
    @Field(name = "tour_package_accommodation_name")
    private String tourPackageAccommodationName;
    @JsonProperty("tour_package_room_category_name")
    @Field(name = "tour_package_room_category_name")
    private String tourPackageRoomCategoryName;
    @JsonProperty("tour_package_room_type_name")
    @Field(name = "tour_package_room_type_name")
    private String tourPackageRoomTypeName;
    @JsonProperty("is_room_shareable")
    @Field(name = "is_room_shareable")
    private boolean isShareable;
    @JsonProperty("suitable_for_persons")
    @Field(name = "suitable_for_persons")
    private Integer suitableForPersons;
    @JsonProperty("bed_count")
    @Field(name = "bed_count")
    private Integer bedCount;
    @JsonProperty("bed_configuration")
    @Field(name = "bed_configuration")
    private String bedConfiguration;
    @JsonProperty("night_number")
    @Field(name = "night_number")
    private Integer nightNumber;

    public ESAccommodationPackageData(AccommodationPackageEntity accommodationPackageEntity) {
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
