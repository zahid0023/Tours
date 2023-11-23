package com.ghuddy.backendapp.tours.es.model.entities;

import com.ghuddy.backendapp.tours.es.dto.data.*;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailabilityGeneratedAccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourItineraryEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.AvailabilityGeneratedTourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailabilityGeneratedTransferOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.AvailabilityGeneratedTransportationPackageEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@Document(indexName = "available_tour_packages")
public class ESTourPackageDocument {
    @Id
    @Field(name = "available_tour_package_id", type = FieldType.Long)
    private Long availableTourPackageId;
    @Field(name = "subscribed_tour_id", type = FieldType.Long)
    private Long subscribedTourId; // for merchant reference
    @Field(name = "tour_id", type = FieldType.Long) // for tour reference
    private Long tourId;
    @Field(name = "tour_package_available_type_id", type = FieldType.Long)
    private Long tourPackageTypeId;
    @Field(name = "tour_package_name", type = FieldType.Keyword)
    private String tourPackageName;
    @Field(name = "tour_package_available_start_date", type = FieldType.Date)
    private LocalDate tourStartDate;
    @Field(name = "tour_package_available_reporting_time", type = FieldType.Date, format = DateFormat.custom, pattern = "HH:mm:ss")
    private String tourReportingTime;
    @Field(name = "tour_package_available_reporting_place", type = FieldType.Text)
    private String tourReportingPlace;
    @Field(name = "tour_package_available_total_seats", type = FieldType.Integer)
    private Integer totalSeats;
    @Field(name = "tour_package_available_bookable_seats", type = FieldType.Integer)
    private Integer bookableSeats;
    @Field(name = "tour_package_available_is_accommodation_included", type = FieldType.Boolean)
    private Boolean isAccommodationInclusive;
    @Field(name = "tour_package_available_is_food_included", type = FieldType.Boolean)
    private Boolean isFoodInclusive;
    @Field(name = "tour_package_available_is_transfer_included", type = FieldType.Boolean)
    private Boolean isTransferInclusive;
    @Field(name = "tour_package_available_is_guide_included", type = FieldType.Boolean)
    private Boolean isGuideInclusive;
    @Field(name = "tour_package_available_is_spot_entry_included", type = FieldType.Boolean)
    private Boolean isSpotEntryInclusive;
    @Field(name = "tour_package_available_accommodation_options", type = FieldType.Nested, includeInParent = true)
    private List<ESAccommodationOptionDocument> esAccommodationOptionDocumentList;
    @Field(name = "tour_package_available_food_options", type = FieldType.Nested, includeInParent = true)
    private List<ESFoodOptionData> esFoodOptionDataList;
    @Field(name = "tour_package_available_transfer_options", type = FieldType.Nested, includeInParent = true)
    private List<ESTransferOptionDocument> esTransferOptionDocumentList;
    @Field(name = "tour_package_available_transportation_options", type = FieldType.Nested, includeInParent = true)
    private List<ESTransportationPackageDocument> esTransportationPackageDocumentList;

    @Field(name = "tour_package_available_itinerary", type = FieldType.Nested, includeInParent = true)
    private List<ESSubscribedTourItineraryDocument> esSubscribedTourItineraryDocumentList;

    public ESTourPackageDocument(AvailabilityGeneratedTourPackageEntity availabilityGeneratedTourPackageEntity) {
        this.availableTourPackageId = availabilityGeneratedTourPackageEntity.getId();
        SubscribedTourEntity subscribedTourEntity = availabilityGeneratedTourPackageEntity.getTourPackageEntity().getSubscribedTourEntity();
        this.tourId = subscribedTourEntity.getTourEntity().getId();
        this.subscribedTourId = subscribedTourEntity.getId();
        this.tourPackageTypeId = availabilityGeneratedTourPackageEntity.getTourPackageEntity().getTourPackageType().getId();
        this.tourPackageName = availabilityGeneratedTourPackageEntity.getTourPackageEntity().getTourPackageName();
        this.tourStartDate = availabilityGeneratedTourPackageEntity.getTourStartDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.tourReportingTime = subscribedTourEntity.getTourReportingTime().format(formatter);
        this.tourReportingPlace = subscribedTourEntity.getTourReportingPlace();
        this.totalSeats = availabilityGeneratedTourPackageEntity.getTotalSeats();
        this.bookableSeats = availabilityGeneratedTourPackageEntity.getBookableSeats();
        this.isAccommodationInclusive = availabilityGeneratedTourPackageEntity.getIsAccommodationInclusive();
        this.isFoodInclusive = availabilityGeneratedTourPackageEntity.getIsFoodInclusive();
        this.isTransferInclusive = availabilityGeneratedTourPackageEntity.getIsTransferInclusive();
        this.isGuideInclusive = availabilityGeneratedTourPackageEntity.getIsGuideInclusive();
        this.isSpotEntryInclusive = availabilityGeneratedTourPackageEntity.getIsSpotEntryInclusive();

        this.esAccommodationOptionDocumentList = getAvailableAccommodationOptions(availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedAccommodationOptionEntities());
        //this.esFoodOptionDataList = getEsFoodOptionDataList(availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedFoodOptionEntities());
        this.esTransferOptionDocumentList = getAvailableTransferOptions(availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedTransferOptionEntities());
        this.esTransportationPackageDocumentList = getAvailableTransportationPackages(availabilityGeneratedTourPackageEntity.getAvailabilityGeneratedTransportationPackageEntities());
        this.esSubscribedTourItineraryDocumentList = getTourItinerary(subscribedTourEntity.getSubscribedTourItineraryEntities());

    }

    private List<ESAccommodationOptionDocument> getAvailableAccommodationOptions(List<AvailabilityGeneratedAccommodationOptionEntity> availabilityGeneratedAccommodationOptionEntityList) {
        return availabilityGeneratedAccommodationOptionEntityList.stream()
                .map(availabilityGeneratedAccommodationOptionEntity -> new ESAccommodationOptionDocument(availabilityGeneratedAccommodationOptionEntity))
                .toList();
    }

    //private List<ESFoodOptionData> getAvailableFoodOptions(List<AvailabilityGeneratedFoodOptionEntity> availabilityGeneratedFoodOptionEntityList){
    //  return availabilityGeneratedFoodOptionEntityList.stream()
    //.map(availabilityGeneratedFoodOptionEntity -> new ESFoodOptionData(availabilityGeneratedFoodOptionEntity))
    //        .toList();
    //}

    private List<ESTransferOptionDocument> getAvailableTransferOptions(List<AvailabilityGeneratedTransferOptionEntity> availabilityGeneratedTransferOptionEntityList) {
        return availabilityGeneratedTransferOptionEntityList.stream()
                .map(availabilityGeneratedTransferOptionEntity -> new ESTransferOptionDocument(availabilityGeneratedTransferOptionEntity))
                .toList();
    }

    private List<ESTransportationPackageDocument> getAvailableTransportationPackages(List<AvailabilityGeneratedTransportationPackageEntity> availabilityGeneratedTransportationPackageEntityList) {
        return availabilityGeneratedTransportationPackageEntityList.stream()
                .map(availabilityGeneratedTransportationPackageEntity -> new ESTransportationPackageDocument(availabilityGeneratedTransportationPackageEntity))
                .toList();
    }

    private List<ESSubscribedTourItineraryDocument> getTourItinerary(List<SubscribedTourItineraryEntity> subscribedTourItineraryEntityList) {
        return subscribedTourItineraryEntityList.stream()
                .map(subscribedTourItineraryEntity -> new ESSubscribedTourItineraryDocument(subscribedTourItineraryEntity))
                .toList();
    }

}
