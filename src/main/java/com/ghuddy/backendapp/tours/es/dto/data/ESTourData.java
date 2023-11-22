package com.ghuddy.backendapp.tours.es.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ESTourData { // only those tours will be indexed that have availability generated tour packages i.e. merchant subscribed the tour and created and made available the tour packages

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
    @JsonProperty("available_tour_packages")
    private HashMap<Long, List<ESTourPackageData>> esTourPackageDataList;

    public ESTourData(SubscribedTourEntity subscribedTourEntity) {
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
        this.esTourPackageDataList = getAvailableTourPackages(subscribedTourEntity);
    }

    private HashMap<Long, List<ESTourPackageData>> getAvailableTourPackages(SubscribedTourEntity subscribedTourEntity) {
        return subscribedTourEntity.getTourPackageEntities().stream()
                .flatMap(tourPackageEntity ->
                        tourPackageEntity.getAvailabilityGeneratedTourPackages().stream()
                                .map(availabilityGeneratedTourPackageEntity ->
                                        new ESTourPackageData(availabilityGeneratedTourPackageEntity)))
                .collect(Collectors.groupingBy(ESTourPackageData::getTourPackageTypeId, HashMap::new, Collectors.toList()));
    }
}
