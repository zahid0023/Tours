package com.ghuddy.backendapp.es.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.SubscribedTourEntity;
import lombok.Data;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Data
public class ESSubscribedTourData {
    @JsonProperty("tour_id")
    private Long tourId;
    @JsonProperty("tour_name")
    private String tourName;
    @JsonProperty("short_address")
    private String shortAddress;
    @JsonProperty("number_of_reviews")
    private Integer numberOfReviews;
    @JsonProperty("rating_in_stars")
    private Double ratingInStars;
    @JsonProperty("tour_thumb_image_url")
    private String tourThumbImageUrl;
    @JsonProperty("tour_specialities")
    private List<ESTourSpecialityData> esTourSpecialityDataList;
    @JsonProperty("tour_title")
    private String tourTitle;
    @JsonProperty("tour_description")
    private String tourDescription;
    @JsonProperty("tour_reporting_time")
    private LocalTime tourReportingTime;
    @JsonProperty("tour_reporting_place")
    private String tourReportingPlace;
    @JsonProperty("tour_tag")
    private String tourTag;
    @JsonProperty("included_in_tour")
    private List<String> included;
    @JsonProperty("not_included_in_tour")
    private List<String> notIncluded;

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
    }
}
