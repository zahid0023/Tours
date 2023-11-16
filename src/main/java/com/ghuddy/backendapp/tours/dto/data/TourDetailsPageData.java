package com.ghuddy.backendapp.tours.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.es.dto.data.ESSubscribedTourItineraryData;
import com.ghuddy.backendapp.tours.es.dto.data.ESTourPackageData;
import com.ghuddy.backendapp.tours.es.dto.data.ESTourSpecialityData;
import com.ghuddy.backendapp.tours.model.data.tour.TourSpecialityData;
import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageData;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalTime;
import java.util.List;

@Data
public class TourDetailsPageData {
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
    private List<TourSpecialityData> tourSpecialityDataList;
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

    @Field(name = "subscribed_tour_packages", type = FieldType.Nested, includeInParent = true)
    @JsonProperty("subscribed_tour_packages")
    private List<TourPackageData> esTourPackageDataList;

    @Field(name = "subscribed_tour_itinerary", type = FieldType.Nested, includeInParent = true)
    @JsonProperty("subscribed_tour_itinerary")
    private List<ESSubscribedTourItineraryData> esSubscribedTourItineraryDataList;
}
