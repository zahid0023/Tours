package com.ghuddy.backendapp.tours.model.data.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.AccommodationPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AccommodationPackageData {
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

    @Schema(description = "The price of this accommodation package", required = true, example = "1200")
    @JsonProperty("accommodation_package_unit_price")
    private BigDecimal unitPrice;
    @Schema(description = "The total number of rooms provided for this tour package", required = true, example = "1")
    @JsonProperty("accommodation_package_quantity")
    private Integer quantity;
    @Schema(description = "The total/final price of this accommodation package", required = true, example = "120")
    @JsonProperty("accommodation_package_total_price")
    private BigDecimal totalAccommodationPackagePrice;
    @Schema(description = "Whether this is accommodation package comes with the tour package or optional, i.e. the user can choose this, for this the price will vary", required = true, example = "true")
    @JsonProperty("accommodation_package_is_default")
    private Boolean isDefault;
    private int[] nightNumbers;

    public AccommodationPackageData(AccommodationPackageEntity accommodationPackageEntity) {
        this.tourPackageAccommodationName = accommodationPackageEntity.getTourAccommodationEntity().getAccommodationName();
        this.tourPackageRoomCategoryName = accommodationPackageEntity.getTourRoomCategoryEntity().getRoomCategoryName();
        this.tourPackageRoomTypeName = accommodationPackageEntity.getTourRoomTypeEntity().getRoomTypeName();
        this.isShareable = accommodationPackageEntity.getIsShareable();
        this.suitableForPersons = accommodationPackageEntity.getSuitableForPersons();
        this.bedCount = accommodationPackageEntity.getBedCount();
        this.bedConfiguration = accommodationPackageEntity.getBedConfiguration();
        this.unitPrice = accommodationPackageEntity.getPerNightRoomPrice();
        this.nightNumbers = accommodationPackageEntity.getNightNumbers();
        this.totalAccommodationPackagePrice = accommodationPackageEntity.getPerPersonAccommodationPackagePrice();
        //this.isDefault = accommodationPackageEntity.getIsIncluded();
    }
}
