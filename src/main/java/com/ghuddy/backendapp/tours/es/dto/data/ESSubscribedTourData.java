package com.ghuddy.backendapp.tours.es.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.SubscribedTourEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Data
@Document(indexName = "subscribed_tours")
public class ESSubscribedTourData {
    @Id
    @JsonProperty("tour_id")
    @Field(name = "tour_id")
    private Long tourId;
    @JsonProperty("tour_name")
    @Field(name = "tour_name")
    private String tourName;
    @JsonProperty("short_address")
    @Field(name = "short_address")
    private String shortAddress;
    @JsonProperty("number_of_reviews")
    @Field(name = "number_of_reviews")
    private Integer numberOfReviews;
    @JsonProperty("rating_in_stars")
    @Field(name = "rating_in_stars")
    private Double ratingInStars;
    @JsonProperty("tour_thumb_image_url")
    @Field(name = "tour_thumb_image_url")
    private String tourThumbImageUrl;
    @JsonProperty("tour_specialities")
    @Field(name = "tour_specialities", type = FieldType.Nested, includeInParent = true)
    private List<ESTourSpecialityData> esTourSpecialityDataList;
    @JsonProperty("tour_title")
    @Field(name = "tour_title")
    private String tourTitle;
    @JsonProperty("tour_description")
    @Field("tour_description")
    private String tourDescription;
    @JsonProperty("tour_reporting_time")
    @Field(name = "tour_reporting_time")
    private LocalTime tourReportingTime;
    @JsonProperty("tour_reporting_place")
    @Field(name = "tour_reporting_place")
    private String tourReportingPlace;
    @JsonProperty("tour_tag")
    @Field(name = "tour_tag")
    private String tourTag;
    @JsonProperty("included_in_tour")
    @Field(name = "included")
    private List<String> included;
    @JsonProperty("not_included_in_tour")
    @Field(name = "not_included")
    private List<String> notIncluded;

    @Field(name = "subscribed_tour_packages", type = FieldType.Nested, includeInParent = true)
    @JsonProperty("subscribed_tour_packages")
    private List<ESTourPackageData> esTourPackageDataList;

    @Field(name = "subscribed_tour_itinerary", type = FieldType.Nested, includeInParent = true)
    @JsonProperty("subscribed_tour_itinerary")
    private List<ESSubscribedTourItineraryData> esSubscribedTourItineraryDataList;


    public ESSubscribedTourData(SubscribedTourEntity subscribedTourEntity) {
        this.tourId = subscribedTourEntity.getId();
        this.tourName = subscribedTourEntity.getTourEntity().getAddedTourEntity().getTourName();
        this.shortAddress = subscribedTourEntity.getTourEntity().getAddedTourEntity().getShortAddress();
        this.numberOfReviews = subscribedTourEntity.getNumberOfReviews();
        this.ratingInStars = subscribedTourEntity.getRatingInStars();
        this.tourThumbImageUrl = subscribedTourEntity.getTourEntity().getThumbImageUrl();
        this.esTourSpecialityDataList = subscribedTourEntity.getTourEntity().getTourSpecialityEntities().stream()
                .map(tourSpecialityEntity -> new ESTourSpecialityData(tourSpecialityEntity))
                .toList();
        this.tourTitle = subscribedTourEntity.getTourEntity().getTitle();
        this.tourDescription = subscribedTourEntity.getTourEntity().getDescription();
        this.tourReportingTime = subscribedTourEntity.getTourReportingTime();
        this.tourReportingPlace = subscribedTourEntity.getTourReportingPlace();
        this.tourTag = subscribedTourEntity.getTourEntity().getAddedTourEntity().getTourTag();
        this.included = new LinkedList<>();
        this.notIncluded = new LinkedList<>();
        this.esTourPackageDataList = subscribedTourEntity.getTourPackageEntities().stream()
                .map(tourPackageEntity -> new ESTourPackageData(tourPackageEntity))
                .toList();
        this.esSubscribedTourItineraryDataList = subscribedTourEntity.getSubscribedTourItineraryEntities().stream()
                .map(subscribedTourItineraryEntity -> new ESSubscribedTourItineraryData(subscribedTourItineraryEntity))
                .toList();
    }
}