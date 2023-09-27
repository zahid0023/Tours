package com.ghuddy.backendapp.tours.model.data.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.activity.ActivityData;
import com.ghuddy.backendapp.tours.model.entities.TourEntity;
import com.ghuddy.backendapp.tours.model.entities.TourSpecialityEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CreatedTourData {
    @JsonProperty("created_tour_id")
    private Long createdTourId;
    @JsonProperty("created_tour_name")
    private String createdTourName;
    @JsonProperty("created_tour_thumb_image_url")
    private String createdTourThumbImageUrl;
    @JsonProperty("created_tour_title")
    private String createdTourTitle;
    @JsonProperty("created_tour_description")
    private String createdTourDescription;
    @JsonProperty("created_tour_activities")
    private List<TourItineraryData> activityDataList;
    @JsonProperty("created_tour_specialities")
    private List<CreatedTourSpecialityData> createdTourSpecialityDataList;

    public CreatedTourData(TourEntity tourEntity) {
        this.createdTourId = tourEntity.getId();
        this.createdTourName = tourEntity.getTourLocation().getTourName();
        this.createdTourThumbImageUrl = tourEntity.getThumbImageUrl();
        this.createdTourTitle = tourEntity.getTitle();
        this.createdTourDescription = tourEntity.getDescription();
        this.activityDataList = tourEntity.getTourItineraryEntities().stream()
                .map(tourItineraryEntity -> new TourItineraryData(tourItineraryEntity))
                .collect(Collectors.toList());
        this.createdTourSpecialityDataList = tourEntity.getTourSpecialityEntities().stream()
                .map(tourSpecialityEntity -> new CreatedTourSpecialityData(tourSpecialityEntity))
                .collect(Collectors.toList());
    }
}
