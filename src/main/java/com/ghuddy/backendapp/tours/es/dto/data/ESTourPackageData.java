package com.ghuddy.backendapp.tours.es.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.accommodation.AvailabilityGeneratedAccommodationOptionData;
import com.ghuddy.backendapp.tours.model.data.activity.SubscribedTourActivityData;
import com.ghuddy.backendapp.tours.model.data.food.AvailabilityGeneratedFoodOptionData;
import com.ghuddy.backendapp.tours.model.data.guide.AvailabilityGeneratedGuideOptionData;
import com.ghuddy.backendapp.tours.model.data.spot.entry.AvailabilityGeneratedSpotEntryOptionData;
import com.ghuddy.backendapp.tours.model.data.transfer.AvailabilityGeneratedTransferOptionData;
import com.ghuddy.backendapp.tours.model.data.transportation.AvailabilityGeneratedTransportationPackageData;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailabilityGeneratedAccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.AvailabilityGeneratedFoodOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourItineraryEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.AvailabilityGeneratedTourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailabilityGeneratedTransferOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.AvailabilityGeneratedTransportationPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class ESTourPackageData {
    @JsonProperty("subscribed_tour_id")
    private Long subscribedTourId; // for merchant reference
    @JsonProperty("tour_package_available_tour_package_id")
    private Long availableTourPackageId;
    @JsonProperty("tour_package_available_type_id")
    private Long tourPackageTypeId;
    @JsonProperty("tour_package_available_start_date")
    private LocalDate tourStartDate;
    @JsonProperty("tour_package_available_reporting_time")
    private LocalTime tourReportingTime;
    @JsonProperty("tour_package_available_reporting_place")
    private String tourReportingPlace;
    @JsonProperty("tour_package_available_total_seats")
    private Integer totalSeats;
    @JsonProperty("tour_package_available_bookable_seats")
    private Integer bookableSeats;
    @JsonProperty("tour_package_available_is_accommodation_included")
    private Boolean isAccommodationInclusive;
    @JsonProperty("tour_package_available_is_food_included")
    private Boolean isFoodInclusive;
    @JsonProperty("tour_package_available_is_transfer_included")
    private Boolean isTransferInclusive;
    @JsonProperty("tour_package_available_is_guide_included")
    private Boolean isGuideInclusive;
    @JsonProperty("tour_package_available_is_spot_entry_included")
    private Boolean isSpotEntryInclusive;
    @JsonProperty("tour_package_available_accommodation_options")
    private List<ESAccommodationOptionData> esAccommodationOptionDataList;
    @JsonProperty("tour_package_available_food_options")
    private List<ESFoodOptionData> esFoodOptionDataList;
    @JsonProperty("tour_package_available_transfer_options")
    private List<ESTransferOptionData> esTransferOptionDataList;
    @JsonProperty("tour_package_available_transportation_options")
    private List<ESTransportationPackageData> esTransportationPackageDataList;
    @JsonProperty("tour_package_available_itinerary")
    private List<ESSubscribedTourItineraryData> esSubscribedTourItineraryDataList;

    public ESTourPackageData(AvailabilityGeneratedTourPackageEntity availabilityGeneratedTourPackageEntity) {
        SubscribedTourEntity subscribedTourEntity = availabilityGeneratedTourPackageEntity.getTourPackageEntity().getSubscribedTourEntity();
        this.subscribedTourId = subscribedTourEntity.getId();
        this.availableTourPackageId = availabilityGeneratedTourPackageEntity.getId();
        this.tourPackageTypeId = availabilityGeneratedTourPackageEntity.getTourPackageEntity().getTourPackageType().getId();
        this.tourStartDate = availabilityGeneratedTourPackageEntity.getTourStartDate();
        this.tourReportingTime = subscribedTourEntity.getTourReportingTime();
        this.tourReportingPlace = subscribedTourEntity.getTourReportingPlace();
        this.totalSeats = availabilityGeneratedTourPackageEntity.getTotalSeats();
        this.bookableSeats = availabilityGeneratedTourPackageEntity.getBookableSeats();
        this.isAccommodationInclusive = availabilityGeneratedTourPackageEntity.getIsAccommodationInclusive();
        this.isFoodInclusive = availabilityGeneratedTourPackageEntity.getIsFoodInclusive();
        this.isTransferInclusive = availabilityGeneratedTourPackageEntity.getIsTransferInclusive();
        this.isGuideInclusive = availabilityGeneratedTourPackageEntity.getIsGuideInclusive();
        this.isSpotEntryInclusive = availabilityGeneratedTourPackageEntity.getIsSpotEntryInclusive();

        this.esAccommodationOptionDataList = getAvailableAccommodationOptions(availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedAccommodationOptionEntities());
        //this.esFoodOptionDataList = getEsFoodOptionDataList(availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedFoodOptionEntities());
        this.esTransferOptionDataList = getAvailableTransferOptions(availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedTransferOptionEntities());
        this.esTransportationPackageDataList = getAvailableTransportationPackages(availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedTransportationPackageEntities());
        this.esSubscribedTourItineraryDataList = getTourItinerary(subscribedTourEntity.getSubscribedTourItineraryEntities());

    }

    private List<ESAccommodationOptionData> getAvailableAccommodationOptions(List<AvailabilityGeneratedAccommodationOptionEntity> availabilityGeneratedAccommodationOptionEntityList) {
        return availabilityGeneratedAccommodationOptionEntityList.stream()
                .map(availabilityGeneratedAccommodationOptionEntity -> new ESAccommodationOptionData(availabilityGeneratedAccommodationOptionEntity))
                .toList();
    }

    //private List<ESFoodOptionData> getAvailableFoodOptions(List<AvailabilityGeneratedFoodOptionEntity> availabilityGeneratedFoodOptionEntityList){
    //  return availabilityGeneratedFoodOptionEntityList.stream()
    //.map(availabilityGeneratedFoodOptionEntity -> new ESFoodOptionData(availabilityGeneratedFoodOptionEntity))
    //        .toList();
    //}

    private List<ESTransferOptionData> getAvailableTransferOptions(List<AvailabilityGeneratedTransferOptionEntity> availabilityGeneratedTransferOptionEntityList) {
        return availabilityGeneratedTransferOptionEntityList.stream()
                .map(availabilityGeneratedTransferOptionEntity -> new ESTransferOptionData(availabilityGeneratedTransferOptionEntity))
                .toList();
    }

    private List<ESTransportationPackageData> getAvailableTransportationPackages(List<AvailabilityGeneratedTransportationPackageEntity> availabilityGeneratedTransportationPackageEntityList) {
        return availabilityGeneratedTransportationPackageEntityList.stream()
                .map(availabilityGeneratedTransportationPackageEntity -> new ESTransportationPackageData(availabilityGeneratedTransportationPackageEntity))
                .toList();
    }

    private List<ESSubscribedTourItineraryData> getTourItinerary(List<SubscribedTourItineraryEntity> subscribedTourItineraryEntityList) {
        return subscribedTourItineraryEntityList.stream()
                .map(subscribedTourItineraryEntity -> new ESSubscribedTourItineraryData(subscribedTourItineraryEntity))
                .toList();
    }

}
