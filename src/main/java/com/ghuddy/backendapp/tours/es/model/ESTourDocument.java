package com.ghuddy.backendapp.tours.es.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.es.dto.data.ESTourPackageData;
import com.ghuddy.backendapp.tours.es.dto.data.ESTourSpecialityData;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.TourEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Document(indexName = "tour_details")
public class ESTourDocument { // only those tours will be indexed that have availability generated tour packages i.e. merchant subscribed the tour and created and made available the tour packages

    @Id
    @Field(name = "tour_id", type = FieldType.Long)
    private Long tourId;
    @JsonProperty("tour_name")
    @MultiField(mainField = @Field(name = "tour_name", type = FieldType.Text),
            otherFields = {
                    @InnerField(suffix = "keyword", type = FieldType.Keyword)
            })
    private String tourName;

    @Field(name = "short_address", type = FieldType.Text)
    private String shortAddress;

    @Field(name = "number_of_reviews", type = FieldType.Text)
    private Integer numberOfReviews;

    @Field(name = "rating_in_stars", type = FieldType.Double)
    private Double ratingInStars;

    @Field(name = "tour_thumb_image_url",type = FieldType.Text)
    private String tourThumbImageUrl;

    @Field(name = "tour_specialities", type = FieldType.Nested, includeInParent = true)
    private List<ESTourSpecialityDocument> esTourSpecialityDocumentList;

    @Field(name = "tour_title", type = FieldType.Text)
    private String tourTitle;

    @Field(name = "tour_description", type = FieldType.Text)
    private String tourDescription;

    @Field(name = "tour_reporting_time")
    private LocalTime tourReportingTime;

    @Field(name = "tour_reporting_place")
    private String tourReportingPlace;

    @Field(name = "tour_tag", type = FieldType.Text)
    private String tourTag;

    @Field(name = "available_tour_packages", type = FieldType.Nested, includeInParent = true)
    private List<ESTourPackageDocument> esTourPackageDataList;

    public ESTourDocument(TourEntity tourEntity) {
        this.tourId = tourEntity.getId();
        this.tourName = tourEntity.getAddedTourEntity().getTourName();
        this.shortAddress = tourEntity.getAddedTourEntity().getShortAddress();
        this.numberOfReviews = 0;
        this.ratingInStars = 0.0;
        this.tourThumbImageUrl = tourEntity.getThumbImageUrl();
        this.esTourSpecialityDocumentList = tourEntity.getTourSpecialityEntities().stream()
                .map(tourSpecialityEntity -> new ESTourSpecialityDocument(tourSpecialityEntity))
                .toList();
        this.tourTitle = tourEntity.getTitle();
        this.tourDescription = tourEntity.getDescription();
        this.tourTag = tourEntity.getAddedTourEntity().getTourTag();
        this.esTourPackageDataList = getAvailableTourPackages(tourEntity);
    }

    private List<ESTourPackageDocument> getAvailableTourPackages(TourEntity tourEntity) {
        return tourEntity.getSubscribedTourEntities().stream()
                .flatMap(subscribedTourEntity -> subscribedTourEntity.getTourPackageEntities().stream()
                        .flatMap(tourPackageEntity -> tourPackageEntity.getAvailabilityGeneratedTourPackages().stream()
                                .map(availabilityGeneratedTourPackageEntity ->
                                        new ESTourPackageDocument(availabilityGeneratedTourPackageEntity))))
                .collect(Collectors.toList());
    }

}
