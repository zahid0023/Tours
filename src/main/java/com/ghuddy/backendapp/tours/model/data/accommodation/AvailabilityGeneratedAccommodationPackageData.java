package com.ghuddy.backendapp.tours.model.data.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailabilityGeneratedAccommodationPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AvailabilityGeneratedAccommodationPackageData {
    @Schema(description = "The tour package/ tour package availability id")
    @JsonProperty("tour_package_accommodation_package_id")
    private Long tourPackageAccommodationPackageId;
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
    @JsonProperty("per_night_room_price")
    private BigDecimal unitPrice;

    @Schema(description = "The night in which this accommodation package will be available", example = "1")
    @JsonProperty("night_number")
    private Integer nightNumber;

    public AvailabilityGeneratedAccommodationPackageData(AccommodationPackageData accommodationPackageData) {
        this.tourPackageAccommodationPackageId = accommodationPackageData.getAccommodationPackageId();
        this.setTourPackageAccommodationName(accommodationPackageData.getTourPackageAccommodationName());
        this.setTourPackageRoomCategoryName(accommodationPackageData.getTourPackageRoomCategoryName());
        this.setTourPackageRoomTypeName(accommodationPackageData.getTourPackageRoomTypeName());
        this.setShareable(accommodationPackageData.isShareable());
        this.setSuitableForPersons(accommodationPackageData.getSuitableForPersons());
        this.setBedCount(accommodationPackageData.getBedCount());
        this.setBedConfiguration(accommodationPackageData.getBedConfiguration());
        this.setNightNumber(accommodationPackageData.getNightNumber());
    }

    public AvailabilityGeneratedAccommodationPackageData(AvailabilityGeneratedAccommodationPackageEntity availabilityGeneratedAccommodationPackageEntity) {
        this(new AccommodationPackageData(availabilityGeneratedAccommodationPackageEntity.getAccommodationPackageEntity()));
        this.setUnitPrice(availabilityGeneratedAccommodationPackageEntity.getAccommodationPackagePrice());
    }

    public AvailabilityGeneratedAccommodationPackageData(AccommodationPackageEntity accommodationPackageEntity) {
        this(new AccommodationPackageData(accommodationPackageEntity));
        this.setUnitPrice(accommodationPackageEntity.getPerNightRoomPrice());
    }
}
