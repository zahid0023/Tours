package com.ghuddy.backendapp.tours.model.data.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.TourPackageAccommodationEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TourPackageAccommodationPackageData {
    @Schema(description = "The name of the accommodation associated with this tour package accommodation package")
    @JsonProperty("tour_package_accommodation_name")
    private String tourPackageAccommodationName;
    @Schema(description = "The name of the room category associated with this tour package accommodation package")
    @JsonProperty("tour_package_room_category_name")
    private String tourPackageRoomCategoryName;
    @Schema(description = "The name of the room type associated with this tour package accommodation package")
    @JsonProperty("tour_package_room_type_name")
    private String tourPackageRoomTypeName;
    @Schema(description = "Whether this room is shareable or not")
    @JsonProperty("is_room_shareable")
    private boolean isShareable;
    @Schema(description = "How many people can stay in this room")
    @JsonProperty("suitable_for_persons")
    private Integer suitableForPersons;
    @Schema(description = "Total number of beds in this room")
    @JsonProperty("bed_count")
    private Integer bedCount;
    @Schema(description = "The type of bed provided with this room")
    @JsonProperty("bed_configuration")
    private String bedConfiguration;

    public TourPackageAccommodationPackageData(TourPackageAccommodationEntity tourPackageAccommodationEntity) {
        this.tourPackageAccommodationName = tourPackageAccommodationEntity.getTourAccommodationEntity().getAccommodationName();
        this.tourPackageRoomCategoryName = tourPackageAccommodationEntity.getTourRoomCategoryEntity().getRoomCategoryName();
        this.tourPackageRoomTypeName = tourPackageAccommodationEntity.getTourRoomTypeEntity().getRoomTypeName();
        this.isShareable = tourPackageAccommodationEntity.getIsShareable();
        this.suitableForPersons = tourPackageAccommodationEntity.getSuitableForPersons();
        this.bedCount = tourPackageAccommodationEntity.getBedCount();
        this.bedConfiguration = tourPackageAccommodationEntity.getBedConfiguration();
    }
}
