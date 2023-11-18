package com.ghuddy.backendapp.tours.model.data.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageDetailsPageData;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.TourEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class TourDetailsPageData {
    @Schema(description = "the name of the tour")
    @JsonProperty("tour_name")
    private String tourName;
    @Schema(description = "The number of days")
    @JsonProperty("number_of_days")
    private Integer numberOfDays;
    @Schema(description = "The number of nights")
    @JsonProperty("number_of_nights")
    private Integer numberOfNights;
    @Schema(description = "The thumb image url")
    @JsonProperty("tour_thumb_image_url")
    private String tourThumbImageUrl;
    @Schema(description = "The title of the tour")
    @JsonProperty("tour_title")
    private String tourTitle;
    @Schema(description = "The tour description")
    @JsonProperty("tour_description")
    private String tourDescription;
    @Schema(description = "The list of tour specialities")
    @JsonProperty("tour_specialities")
    private List<TourSpecialityData> tourSpecialityDataList;
    @Schema(description = "The list of tour packages")
    @JsonProperty("available_tour_packages")
    private HashMap<Long, List<TourPackageDetailsPageData>> tourPackageDetailsPageDataList;

    public TourDetailsPageData(SubscribedTourEntity subscribedTourEntity){
        TourEntity tourEntity = subscribedTourEntity.getTourEntity();
        this.tourName = tourEntity.getAddedTourEntity().getTourName();
        this.numberOfDays = tourEntity.getAddedTourEntity().getNumberOfDays();
        this.numberOfNights = tourEntity.getAddedTourEntity().getNumberOfNights();
        this.tourThumbImageUrl = tourEntity.getThumbImageUrl();
        this.tourTitle = tourEntity.getTitle();
        this.tourDescription = tourEntity.getDescription();
        this.tourSpecialityDataList = tourEntity.getTourSpecialityEntities().stream()
                .map(tourSpecialityEntity -> new TourSpecialityData(tourSpecialityEntity))
                .toList();
    }

}
