package com.ghuddy.backendapp.tours.model.data.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.accommodation.AvailabilityGeneratedAccommodationOptionData;
import com.ghuddy.backendapp.tours.model.data.activity.SubscribedTourActivityData;
import com.ghuddy.backendapp.tours.model.data.food.AvailabilityGeneratedFoodOptionData;
import com.ghuddy.backendapp.tours.model.data.guide.AvailabilityGeneratedGuideOptionData;
import com.ghuddy.backendapp.tours.model.data.spot.entry.AvailabilityGeneratedSpotEntryOptionData;
import com.ghuddy.backendapp.tours.model.data.transfer.AvailabilityGeneratedTransferOptionData;
import com.ghuddy.backendapp.tours.model.data.transportation.AvailabilityGeneratedTransportationPackageData;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.AvailabilityGeneratedTourPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class TourPackageDetailsPageData { // granularity: for a tour, for a specific schedule, by a merchant
    @Schema(description = "The id of the subscribed tour")
    @JsonProperty("subscribed_tour_id")
    private Long subscribedTourId; // for merchant reference

    @Schema(description = "The available tour package id")
    @JsonProperty("available_tour_package_id")
    private Long availableTourPackageId;
    @Schema(description = "The tour package type id")
    @JsonProperty("tour_package_type_id")
    private Long tourPackageTypeId;
    @Schema(description = "When this tour package will be available")
    @JsonProperty("tour_package_start_date")
    private LocalDate tourStartDate;
    @Schema(description = "The tour reporting time on the first day", example = "08:30:00")
    @JsonProperty("subscribed_tour_reporting_time")
    private LocalTime tourReportingTime;
    @Schema(description = "The tour reporting place on the first day", example = "bandarban bus terminal, bandarban")
    @JsonProperty("subscribed_tour_reporting_place")
    private String tourReportingPlace;
    @Schema(description = "Total seats in this tour package")
    @JsonProperty("tour_package_total_seats")
    private Integer totalSeats;
    @Schema(description = "Total bookable seats")
    @JsonProperty("tour_package_bookable_seats")
    private Integer bookableSeats;
    @Schema(description = "Whether accommodation is included")
    @JsonProperty("tour_package_is_accommodation_included")
    private Boolean isAccommodationInclusive;
    @Schema(description = "Whether food is included")
    @JsonProperty("tour_package_is_food_included")
    private Boolean isFoodInclusive;
    @Schema(description = "Whether transfer is included")
    @JsonProperty("tour_package_is_transfer_included")
    private Boolean isTransferInclusive;
    @Schema(description = "Whether guide is included")
    @JsonProperty("tour_package_is_guide_included")
    private Boolean isGuideInclusive;
    @Schema(description = "Whether spot entry is included")
    @JsonProperty("tour_package_is_spot_entry_included")
    private Boolean isSpotEntryInclusive;

    @Schema(description = "The list of available accommodation options")
    @JsonProperty("tour_package_available_accommodation_options")
    private List<AvailabilityGeneratedAccommodationOptionData> accommodationOptionDataList;
    @Schema(description = "The list of available food options")
    @JsonProperty("tour_package_available_food_options")
    private List<AvailabilityGeneratedFoodOptionData> foodOptionDataList;
    @Schema(description = "The list of available transfer options")
    @JsonProperty("tour_package_available_transfer_options")
    private List<AvailabilityGeneratedTransferOptionData> transferOptionDataList;
    @Schema(description = "The list of available transportation packages")
    @JsonProperty("tour_package_available_transportation_options")
    private List<AvailabilityGeneratedTransportationPackageData> transportationPackageDataList;
    @Schema(description = "The list of available guide options")
    @JsonProperty("tour_package_available_guide_options")
    private List<AvailabilityGeneratedGuideOptionData> guideOptionDataList;
    @Schema(description = "The list of spot entry options")
    @JsonProperty("tour_package_available_spot_entry_options")
    private List<AvailabilityGeneratedSpotEntryOptionData> spotEntryOptionDataList;
    @Schema(description = "The tour activities of the tour that merchant must turn into itinerary by providing day number, start time, and end time for each activity")
    @JsonProperty("tour_itinerary")
    private List<SubscribedTourActivityData> subscribedTourActivityList;

    public TourPackageDetailsPageData(SubscribedTourEntity subscribedTourEntity, AvailabilityGeneratedTourPackageEntity availabilityGeneratedTourPackageEntity) {
        this.subscribedTourId = subscribedTourEntity.getId();
        this.availableTourPackageId = availabilityGeneratedTourPackageEntity.getId();
        this.tourPackageTypeId = availabilityGeneratedTourPackageEntity.getTourPackageEntity().getTourPackageType().getId();
        this.tourStartDate = availabilityGeneratedTourPackageEntity.getTourStartDate();
        this.totalSeats = availabilityGeneratedTourPackageEntity.getTotalSeats();
        this.bookableSeats = availabilityGeneratedTourPackageEntity.getBookableSeats();
        this.isAccommodationInclusive = availabilityGeneratedTourPackageEntity.getIsAccommodationInclusive();
        this.isFoodInclusive = availabilityGeneratedTourPackageEntity.getIsFoodInclusive();
        this.isTransferInclusive = availabilityGeneratedTourPackageEntity.getIsTransferInclusive();
        this.isGuideInclusive = availabilityGeneratedTourPackageEntity.getIsGuideInclusive();
        this.isSpotEntryInclusive = availabilityGeneratedTourPackageEntity.getIsSpotEntryInclusive();

        this.accommodationOptionDataList = availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedAccommodationOptionEntities().stream()
                .map(availabilityGeneratedAccommodationOptionEntity -> new AvailabilityGeneratedAccommodationOptionData(availabilityGeneratedAccommodationOptionEntity))
                .toList();

        //this.foodOptionDataList = availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedFoodOptionEntities().stream()
                //.map(availabilityGeneratedFoodOptionEntity -> new AvailabilityGeneratedFoodOptionData(availabilityGeneratedFoodOptionEntity))
                //.toList();

        this.transferOptionDataList = availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedTransferOptionEntities().stream()
                .map(availabilityGeneratedTransferOptionEntity -> new AvailabilityGeneratedTransferOptionData(availabilityGeneratedTransferOptionEntity))
                .toList();

        this.transportationPackageDataList = availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedTransportationPackageEntities().stream()
                .map(availabilityGeneratedTransportationPackageEntity -> new AvailabilityGeneratedTransportationPackageData(availabilityGeneratedTransportationPackageEntity))
                .toList();
        this.guideOptionDataList = availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedGuideOptionEntities().stream()
                .map(availabilityGeneratedGuideOptionEntity -> new AvailabilityGeneratedGuideOptionData(availabilityGeneratedGuideOptionEntity))
                .toList();
        this.spotEntryOptionDataList = availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedSpotEntryOptionEntities().stream()
                .map(availabilityGeneratedSpotEntryOptionEntity -> new AvailabilityGeneratedSpotEntryOptionData(availabilityGeneratedSpotEntryOptionEntity))
                .toList();
        this.subscribedTourActivityList = subscribedTourEntity.getSubscribedTourItineraryEntities().stream()
                .map(subscribedTourItineraryEntity -> new SubscribedTourActivityData(subscribedTourItineraryEntity))
                .toList();
    }
}
